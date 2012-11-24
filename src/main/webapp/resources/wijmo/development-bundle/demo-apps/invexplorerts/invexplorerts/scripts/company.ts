///<reference path='view-model.ts'/>

// declare externally defined objects (to keep TypeScript compiler happy)
declare var $;
declare var ko;
declare var Globalize;

/***********************************************************************************
* Company class.
*/
class Company {

    // fields
    viewModel: ViewModel;
    symbol: string;
    name: string;
    prices = ko.observable([]);
    chartSeries = ko.observable([]);

    constructor (viewModel: ViewModel, symbol: string, name: string) {
        this.viewModel = viewModel;
        this.symbol = symbol;
        this.name = name;
    }

    // get historical prices for this company
    updatePrices() {
        var self = this;

        // don't have prices yet? go get them now
        if (self.prices().length == 0) {

            // go get prices
            var vm = self.viewModel;
            vm.updating(vm.updating() + 1);
            $.get("StockInfo.ashx?symbol=" + self.symbol, function (result) {

                // got them
                vm.updating(vm.updating() - 1);

                // parse result
                var newPrices = [];
                var lines = result.split("\r");
                for (var i = 0; i < lines.length; i++) {
                    var items = lines[i].split("\t");
                    if (items.length == 2) {
                        var day = new Date($.trim(items[0]).replace(/-/g, "/"));
                        var price = $.trim(items[1]) * 1;
                        var item = { day: day, price: price };
                        newPrices.push(item);
                    }
                }

                // update properties
                self.prices(newPrices);

                // update chart series data
                self.updateChartData();
            });

        } else {
            self.updateChartData(); // same data, different min date
        }
    }

    // update data to chart for this company
    updateChartData() {
        var xData = [], yData = [];

        // loop through prices array
        var prices = this.prices();
        for (var i = 0; i < prices.length; i++) {

            // honor min date
            if (prices[i].day < this.viewModel.minDate()) {
                break;
            }

            // add this point
            xData.push(prices[i].day);
            yData.push(prices[i].price);
        }
        if (xData.length == 0) {
            xData.push(0);
            yData.push(0);
        }

        // convert to percentage change from first value
        var baseValue = yData[yData.length - 1];
        for (var i = 0; i < yData.length; i++) {
            yData[i] = yData[i] / baseValue - 1;
        }

        // return x and y values
        var series = {
            data: { x: xData, y: yData },
            label: this.symbol,
            legendEntry: false,
            markers: { visible: false }
        };
        return series;
    }
}
