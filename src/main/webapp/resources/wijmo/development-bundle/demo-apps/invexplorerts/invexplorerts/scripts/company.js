var Company = (function () {
    function Company(viewModel, symbol, name) {
        this.prices = ko.observable([]);
        this.chartSeries = ko.observable([]);
        this.viewModel = viewModel;
        this.symbol = symbol;
        this.name = name;
    }
    Company.prototype.updatePrices = function () {
        var self = this;
        if(self.prices().length == 0) {
            var vm = self.viewModel;
            vm.updating(vm.updating() + 1);
            $.get("StockInfo.ashx?symbol=" + self.symbol, function (result) {
                vm.updating(vm.updating() - 1);
                var newPrices = [];
                var lines = result.split("\r");
                for(var i = 0; i < lines.length; i++) {
                    var items = lines[i].split("\t");
                    if(items.length == 2) {
                        var day = new Date($.trim(items[0]).replace(/-/g, "/"));
                        var price = $.trim(items[1]) * 1;
                        var item = {
                            day: day,
                            price: price
                        };
                        newPrices.push(item);
                    }
                }
                self.prices(newPrices);
                self.updateChartData();
            });
        } else {
            self.updateChartData();
        }
    };
    Company.prototype.updateChartData = function () {
        var xData = [];
        var yData = [];

        var prices = this.prices();
        for(var i = 0; i < prices.length; i++) {
            if(prices[i].day < this.viewModel.minDate()) {
                break;
            }
            xData.push(prices[i].day);
            yData.push(prices[i].price);
        }
        if(xData.length == 0) {
            xData.push(0);
            yData.push(0);
        }
        var baseValue = yData[yData.length - 1];
        for(var i = 0; i < yData.length; i++) {
            yData[i] = yData[i] / baseValue - 1;
        }
        var series = {
            data: {
                x: xData,
                y: yData
            },
            label: this.symbol,
            legendEntry: false,
            markers: {
                visible: false
            }
        };
        return series;
    };
    return Company;
})();
