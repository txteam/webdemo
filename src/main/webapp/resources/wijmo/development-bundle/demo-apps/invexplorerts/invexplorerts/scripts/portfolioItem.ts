///<reference path='portfolio.ts'/>
///<reference path='company.ts'/>

// declare externally defined objects (to keep TypeScript compiler happy)
declare var $;
declare var ko;
declare var Globalize;

/***********************************************************************************
* PortfolioItem class.
*/
class PortfolioItem {

    // fields
    portfolio: Portfolio;
    symbol: string;
    company: Company;
    lastPrice = ko.observable(null);
    change = ko.observable(null);
    chart = ko.observable();
    shares = ko.observable();
    unitCost = ko.observable();
    name = null;
    value = null;
    cost = null;
    changePercent = null;
    gain = null;
    gainPercent = null;
    color = null;

    constructor (portfolio: Portfolio, symbol: string, chart = false, shares = 0, unitCost = 0) {
        var self = this;
        self.portfolio = portfolio;
        self.symbol = symbol;

        // editable values
        self.chart = ko.observable(chart == null ? false : chart);
        self.chart.subscribe(function () { self.updateChartData() });
        self.shares = ko.observable(shares == null ? 0 : shares);
        self.unitCost = ko.observable(unitCost == null ? 0 : unitCost);
        self.shares.subscribe(function () { self.parametersChanged() });
        self.unitCost.subscribe(function () { self.parametersChanged() });

        // find company
        self.company = portfolio.viewModel.findCompany(symbol);
        if (self.company != null) {
            self.company.prices.subscribe(function () { self.pricesChanged() });
            self.pricesChanged();
            self.company.updatePrices();
        }

        // computed observables
        self.name = ko.computed(function () { return this.company.name; }, this);
        self.value = ko.computed(self.getValue, this);
        self.cost = ko.computed(self.getCost, this);
        self.changePercent = ko.computed(self.getChangePercent, this);
        self.gain = ko.computed(self.getGain, this);
        self.gainPercent = ko.computed(self.getGainPercent, this);
        self.color = ko.computed(self.getColor, this);

        // finish initialization
        self.updateChartData();
        self.parametersChanged();
    }

    // computed observables
    getValue() : any {
        var value = this.lastPrice() * this.shares() * 1;
        if (value == 0) return "";
        return value;
    }
    getCost(): any {
        var value = this.unitCost() * this.shares() * 1; 
        if (value == 0) return "";
        return value;
    }
    getChangePercent(): any {
        if (this.change() * 1 == 0 && this.lastPrice() * 1 == 0)  return "";
        return this.change() / this.lastPrice();
    }
    getGain(): any {
        if (this.cost() * 1 == 0 || this.value() * 1 == 0) return "";
        return this.value() - this.cost();
    }
    getGainPercent(): any {
        if (this.cost() * 1 == 0 || this.value() * 1 == 0) return "";
        return this.gain() / this.cost();
    }
    getColor() {
        var palette = ViewModel.palette;
        var index = this.portfolio.items.indexOf(this);
        return index > -1 ? palette[index % palette.length] : "black";
    }

    // update chart data when prices array changes
    pricesChanged() {
        var prices = this.company.prices();
        if (prices.length > 1) {
            this.lastPrice(prices[0].price);
            this.change(prices[0].price - prices[1].price);
            if (this.chart()) {
                this.updateChartData();
            }
        }
    }

    // update grid data when parameters change
    parametersChanged() {
        this.shares(this.shares() * 1 == 0 ? "" : Globalize.format(this.shares() * 1, 'n2'));
        this.unitCost(this.unitCost() * 1 == 0 ? "" : Globalize.format(this.unitCost() * 1, 'n2'));
    }

    // update chart data
    updateChartData() {
        var vm = this.portfolio.viewModel;
        vm.updateChartData();
    }
}
