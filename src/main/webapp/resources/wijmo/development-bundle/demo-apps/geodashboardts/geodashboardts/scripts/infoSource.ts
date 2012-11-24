///<reference path='view-model.ts'/>
///<reference path='infoValue.ts'/>

// declare externally defined objects (to keep TypeScript compiler happy)
declare var $;
declare var ko;
declare var esri;
declare var dojo;

/***********************************************************************************
* InfoSource class.
* The InfoSource belongs to a ViewModel and provides information about a specific 
* demographic for the parent ViewModel's current extent.
*/
class InfoSource {

    // fields    
    viewModel: ViewModel;   // owner ViewModel
    shortUrl: string;       // url used to retrieve data/tiles
    trimName: string;       // remove this part from item names
    values = {};            // object that contains the values
    list = [];              // list with range values
    shortList: any[] = null;

    constructor (model: ViewModel, shortUrl: string, keys: string, listKeys: string = null, trimName: string = null) {

        // store parameters
        this.viewModel = model;
        this.shortUrl = shortUrl;
        this.trimName = trimName;

        // get start/end list keys
        var arr = listKeys != null ? listKeys.split(',') : null;
        var startKey = arr != null ? arr[0] : null;
        var endKey = arr != null ? arr[1] : null;

        // keys used to query/store values
        keys = "NAME,ST_ABBREV," + keys;
        var arr = keys.split(',');
        var addToList = false;
        for (var i = 0; i < arr.length; i++) {
            var key = arr[i];

            // add to values object
            var infoValue = new InfoValue(key);
            this.values[key] = infoValue;

            // add to list
            if (!addToList && key == startKey) {
                addToList = true;
            }
            if (addToList) {
                this.list.push(infoValue);
            }
            if (addToList && key == endKey) {
                addToList = false;
            }
        }

        // use service to get key descriptions
        var url = this.getSchemaUrl();
        var self = this;
        $.ajax({
            url: url, dataType: "jsonp", success: function (data) {

                // got descriptions, assign them to the name property in our data items
                if (data.layers) {
                    var result = data.layers[1].fields;
                    for (var i = 0; i < result.length; i++) {
                        var entry = result[i];
                        var infoValue = self.values[entry.name];
                        if (infoValue != null) {
                            var name = entry.alias;
                            if (name.indexOf(self.trimName) == 0) {
                                name = name.substring(self.trimName.length);
                            }
                            infoValue.name(name);
                        }
                    }
                }
                //console.log("schema loaded for " + source.name);
            }
        });
    }

    // updates the information in this InfoValue if it has subscribers.
    updateValues() {
        var self = this;

        // see if our values have any subscribers
        var subscribers = 0;
        for (var key in self.values) {
            var infoValue = self.values[key];
            subscribers += infoValue.value.getSubscriptionsCount();
        }

        // if there are subscribers, go update the values
        if (subscribers > 0) {
            if (self.viewModel.extent()) {

                // build query/fields to retrieve
                var query = new esri.tasks.Query();
                query.geometry = this.viewModel.extent().getCenter();
                query.outFields = [];
                for (var key in this.values) {
                    query.outFields.push(key);
                }

                // run query
                var url = this.getQueryUrl();
                var queryTask = new esri.tasks.QueryTask(url);
                var self = this;
                dojo.connect(queryTask, "onComplete", function (featureSet) { self.gotInfoValues(featureSet); });
                queryTask.execute(query);
            }
        }
    }

    // after getting values from ESRI service, store them in this InfoSource.
    gotInfoValues(featureSet: any) {
        var self = this;

        if (featureSet.features.length > 0) {

            // get attributes
            var atts = featureSet.features[0].attributes;

            // update selected location in parent ViewModel
            self.viewModel.selectedLocation(atts["NAME"] + ", " + atts["ST_ABBREV"]);

            // update values in our data object
            for (var key in self.values) {
                var infoValue = self.values[key];
                infoValue.value(atts[key]);
            }

            // update summary info values
            if (self.shortList != null) {
                for (var i = 0; i < self.shortList.length; i++) {
                    var value = 0;
                    var keys = self.shortList[i].key.split(" ");
                    for (var key in keys) {
                        value += self.values[keys[key]].value();
                    }
                    self.shortList[i].value(value);
                }
            }

            // update Percent, PercentMax on lists
            self.updatePercent(self.list);
            self.updatePercent(self.shortList);
        } else {

            // no data available: clear values 
            self.viewModel.selectedLocation("Please select a location within the USA.");
            for (var key in this.values) {
                var infoValue = self.values[key];
                infoValue.value(null);
            }
        }
    }

    // after getting values into the lists, update percentages.
    updatePercent(list: any[]) {
        if (list != null) {

            // get sum, max
            var sum = 0, max = 0;
            for (var i = 0; i < list.length; i++) {
                var val = list[i].value();
                sum += val;
                if (i == 0 || val > max) {
                    max = val;
                }
            }

            // calculate percentages
            for (var i = 0; i < list.length; i++) {
                var iv = list[i];
                iv.percent(sum == 0 ? 0 : iv.value() / sum);
                iv.percentMax(max == 0 ? 0 : iv.value() / max);
            } 
        }
    }

    // format used to build service urls
    getUrl() {
        return "http://services.arcgisonline.com:80/ArcGIS/rest/services/Demographics/SHORT_URL/MapServer";
    }

    // build url for getting the tiles for this infoSource
    getTileUrl() {
        return this.getUrl().replace("SHORT_URL", this.shortUrl);
    }

    // build url for querying data for the current scale
    getQueryUrl() {
        return this.getTileUrl() + "/" + this.viewModel.infoScale();
    }

    // build url for querying data for the data schema
    getSchemaUrl() {
        return this.getTileUrl() + "/layers?f=json";
    }
}