var InfoSource = (function () {
    function InfoSource(model, shortUrl, keys, listKeys, trimName) {
        if (typeof listKeys === "undefined") { listKeys = null; }
        if (typeof trimName === "undefined") { trimName = null; }
        this.values = {
        };
        this.list = [];
        this.shortList = null;
        this.viewModel = model;
        this.shortUrl = shortUrl;
        this.trimName = trimName;
        var arr = listKeys != null ? listKeys.split(',') : null;
        var startKey = arr != null ? arr[0] : null;
        var endKey = arr != null ? arr[1] : null;
        keys = "NAME,ST_ABBREV," + keys;
        var arr = keys.split(',');
        var addToList = false;
        for(var i = 0; i < arr.length; i++) {
            var key = arr[i];
            var infoValue = new InfoValue(key);
            this.values[key] = infoValue;
            if(!addToList && key == startKey) {
                addToList = true;
            }
            if(addToList) {
                this.list.push(infoValue);
            }
            if(addToList && key == endKey) {
                addToList = false;
            }
        }
        var url = this.getSchemaUrl();
        var self = this;
        $.ajax({
            url: url,
            dataType: "jsonp",
            success: function (data) {
                if(data.layers) {
                    var result = data.layers[1].fields;
                    for(var i = 0; i < result.length; i++) {
                        var entry = result[i];
                        var infoValue = self.values[entry.name];
                        if(infoValue != null) {
                            var name = entry.alias;
                            if(name.indexOf(self.trimName) == 0) {
                                name = name.substring(self.trimName.length);
                            }
                            infoValue.name(name);
                        }
                    }
                }
            }
        });
    }
    InfoSource.prototype.updateValues = function () {
        var self = this;
        var subscribers = 0;
        for(var key in self.values) {
            var infoValue = self.values[key];
            subscribers += infoValue.value.getSubscriptionsCount();
        }
        if(subscribers > 0) {
            if(self.viewModel.extent()) {
                var query = new esri.tasks.Query();
                query.geometry = this.viewModel.extent().getCenter();
                query.outFields = [];
                for(var key in this.values) {
                    query.outFields.push(key);
                }
                var url = this.getQueryUrl();
                var queryTask = new esri.tasks.QueryTask(url);
                var self = this;
                dojo.connect(queryTask, "onComplete", function (featureSet) {
                    self.gotInfoValues(featureSet);
                });
                queryTask.execute(query);
            }
        }
    };
    InfoSource.prototype.gotInfoValues = function (featureSet) {
        var self = this;
        if(featureSet.features.length > 0) {
            var atts = featureSet.features[0].attributes;
            self.viewModel.selectedLocation(atts["NAME"] + ", " + atts["ST_ABBREV"]);
            for(var key in self.values) {
                var infoValue = self.values[key];
                infoValue.value(atts[key]);
            }
            if(self.shortList != null) {
                for(var i = 0; i < self.shortList.length; i++) {
                    var value = 0;
                    var keys = self.shortList[i].key.split(" ");
                    for(var key in keys) {
                        value += self.values[keys[key]].value();
                    }
                    self.shortList[i].value(value);
                }
            }
            self.updatePercent(self.list);
            self.updatePercent(self.shortList);
        } else {
            self.viewModel.selectedLocation("Please select a location within the USA.");
            for(var key in this.values) {
                var infoValue = self.values[key];
                infoValue.value(null);
            }
        }
    };
    InfoSource.prototype.updatePercent = function (list) {
        if(list != null) {
            var sum = 0;
            var max = 0;

            for(var i = 0; i < list.length; i++) {
                var val = list[i].value();
                sum += val;
                if(i == 0 || val > max) {
                    max = val;
                }
            }
            for(var i = 0; i < list.length; i++) {
                var iv = list[i];
                iv.percent(sum == 0 ? 0 : iv.value() / sum);
                iv.percentMax(max == 0 ? 0 : iv.value() / max);
            }
        }
    };
    InfoSource.prototype.getUrl = function () {
        return "http://services.arcgisonline.com:80/ArcGIS/rest/services/Demographics/SHORT_URL/MapServer";
    };
    InfoSource.prototype.getTileUrl = function () {
        return this.getUrl().replace("SHORT_URL", this.shortUrl);
    };
    InfoSource.prototype.getQueryUrl = function () {
        return this.getTileUrl() + "/" + this.viewModel.infoScale();
    };
    InfoSource.prototype.getSchemaUrl = function () {
        return this.getTileUrl() + "/layers?f=json";
    };
    return InfoSource;
})();
