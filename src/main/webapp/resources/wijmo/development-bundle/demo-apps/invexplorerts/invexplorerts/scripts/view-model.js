var ViewModel = (function () {
    function ViewModel() {
        this.companies = [];
        this.updating = ko.observable(0);
        this.minDate = ko.observable(null);
        this.chartSeries = ko.observable([]);
        this.chartStyles = ko.observable([]);
        this.chartHoverStyles = ko.observable([]);
        this.chartVisible = ko.observable(false);
        var self = this;
        self.portfolio = new Portfolio(this);
        self.setMinDate(6);
        self.minDate.subscribe(function () {
            self.updateChartData();
        });
        $.get("StockInfo.ashx", function (result) {
            var lines = result.split("\r");
            for(var i = 0; i < lines.length; i++) {
                var items = lines[i].split("\t");
                if(items.length == 2) {
                    var c = new Company(self, $.trim(items[0]), $.trim(items[1]));
                    self.companies.push(c);
                }
            }
            self.portfolio.loadItems();
        });
        $(window).unload(function () {
            self.portfolio.saveItems();
        });
    }
    ViewModel.palette = [
        "#FFBE00", 
        "#C8C800", 
        "#94D752", 
        "#00B652", 
        "#00B6EF", 
        "#0075C6", 
        "#002263", 
        "#73359C", 
        "#B53D9C", 
        "#F7E7EF", 
        "#BD3D6B", 
        "#AD65BD", 
        "#DE6D33", 
        "#FFB638", 
        "#CE6DA5", 
        "#FF8E38", 
        "#525D6B", 
        "#FFF39C", 
        "#FF8633", 
        "#739ADE", 
        "#B52B15", 
        "#F7CF2B", 
        "#ADBAD6", 
        "#737D84", 
        "#424452", 
        "#DEEBEF", 
        "#737DA5", 
        "#9CBACE", 
        "#D6DB7B", 
        "#FFDB7B", 
        "#BD8673", 
        "#8C726B", 
        "#424C22", 
        "#FFFBCE", 
        "#A5B694", 
        "#F7A642", 
        "#E7BE2B", 
        "#D692A5", 
        "#9C86C6", 
        "#849EC6", 
        "#4A2215", 
        "#E7DFCE", 
        "#3892A5", 
        "#FFBA00", 
        "#C62B2B", 
        "#84AA33", 
        "#944200", 
        "#42598C", 
        "#383838", 
        "#D6D3D6", 
        "#6BA2B5", 
        "#CEAE00", 
        "#8C8AA5", 
        "#738663", 
        "#9C9273", 
        "#7B868C", 
        "#15487B", 
        "#EFEFE7", 
        "#4A82BD", 
        "#C6504A", 
        "#9CBA5A", 
        "#8465A5", 
        "#4AAEC6", 
        "#F79642", 
        "#6B656B", 
        "#CEC3D6", 
        "#CEBA63", 
        "#9CB284", 
        "#6BB2CE", 
        "#6386CE", 
        "#7B69CE", 
        "#A578BD", 
        "#332E33", 
        "#E7DFD6", 
        "#F77D00", 
        "#382733", 
        "#15597B", 
        "#4A8642", 
        "#63487B", 
        "#C69A5A", 
        "#636984", 
        "#C6D3D6", 
        "#D6604A", 
        "#CEB600", 
        "#28AEAD", 
        "#8C7873", 
        "#8CB28C", 
        "#0E924A"
    ];
    ViewModel.prototype.findCompany = function (symbol) {
        var c = this.companies;
        for(var i = 0; i < c.length; i++) {
            if(c[i].symbol == symbol) {
                return c[i];
            }
        }
        return null;
    };
    ViewModel.prototype.updateChartData = function () {
        var seriesList = [];
        var stylesList = [];
        var hoverStylesList = [];

        var items = this.portfolio.items();
        for(var i = 0; i < items.length; i++) {
            var item = items[i];
            if(item.chart()) {
                var series = item.company.updateChartData();
                seriesList.push(series);
                var style = {
                    stroke: item.getColor(),
                    'stroke-width': 2
                };
                stylesList.push(style);
                var hoverStyle = {
                    stroke: item.getColor(),
                    'stroke-width': 4
                };
                hoverStylesList.push(hoverStyle);
            }
        }
        this.chartVisible(seriesList.length > 0);
        this.chartStyles(stylesList);
        this.chartHoverStyles(hoverStylesList);
        this.chartSeries(seriesList);
    };
    ViewModel.prototype.getAmountColor = function (amount) {
        return amount < 0.01 ? "#D84874" : amount > 0.01 ? "#279972" : "black";
    };
    ViewModel.prototype.getSymbolMatches = function (request) {
        var terms = request.term.toLowerCase().split(" ");
        var options = [];
        var maxItems = 12;
        for(var i = 0; i < this.companies.length && options.length < maxItems; i++) {
            var item = this.companies[i];
            var matchItem = (item.symbol + ": " + item.name).toLowerCase();
            var match = true;
            for(var j = 0; j < terms.length && match; j++) {
                if(matchItem.indexOf(terms[j]) < 0) {
                    match = false;
                }
            }
            if(match) {
                var label = item.symbol + ":   " + item.name;
                for(var j = 0; j < terms.length; j++) {
                    if(terms[j].length > 0) {
                        var rx = new RegExp(terms[j], "gi");
                        label = label.replace(rx, this.highlightMatch);
                    }
                }
                var ac = {
                    value: item.symbol,
                    label: label
                };
                options.push(ac);
            }
        }
        return options;
    };
    ViewModel.prototype.highlightMatch = function (match) {
        return "<span class='match'>" + match + "</span>";
    };
    ViewModel.prototype.setMinDate = function (months) {
        var date = new Date();
        if(months <= 0) {
            var year = date.getFullYear();
            date = new Date(year, 0, 1);
        } else {
            var month = date.getMonth() - months;
            date.setMonth(month);
        }
        this.minDate(date);
    };
    return ViewModel;
})();
