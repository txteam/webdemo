var PortfolioItem = (function () {
    function PortfolioItem(portfolio, symbol, chart, shares, unitCost) {
        if (typeof chart === "undefined") { chart = false; }
        if (typeof shares === "undefined") { shares = 0; }
        if (typeof unitCost === "undefined") { unitCost = 0; }
        this.lastPrice = ko.observable(null);
        this.change = ko.observable(null);
        this.chart = ko.observable();
        this.shares = ko.observable();
        this.unitCost = ko.observable();
        this.name = null;
        this.value = null;
        this.cost = null;
        this.changePercent = null;
        this.gain = null;
        this.gainPercent = null;
        this.color = null;
        var self = this;
        self.portfolio = portfolio;
        self.symbol = symbol;
        self.chart = ko.observable(chart == null ? false : chart);
        self.chart.subscribe(function () {
            self.updateChartData();
        });
        self.shares = ko.observable(shares == null ? 0 : shares);
        self.unitCost = ko.observable(unitCost == null ? 0 : unitCost);
        self.shares.subscribe(function () {
            self.parametersChanged();
        });
        self.unitCost.subscribe(function () {
            self.parametersChanged();
        });
        self.company = portfolio.viewModel.findCompany(symbol);
        if(self.company != null) {
            self.company.prices.subscribe(function () {
                self.pricesChanged();
            });
            self.pricesChanged();
            self.company.updatePrices();
        }
        self.name = ko.computed(function () {
            return this.company.name;
        }, this);
        self.value = ko.computed(self.getValue, this);
        self.cost = ko.computed(self.getCost, this);
        self.changePercent = ko.computed(self.getChangePercent, this);
        self.gain = ko.computed(self.getGain, this);
        self.gainPercent = ko.computed(self.getGainPercent, this);
        self.color = ko.computed(self.getColor, this);
        self.updateChartData();
        self.parametersChanged();
    }
    PortfolioItem.prototype.getValue = function () {
        var value = this.lastPrice() * this.shares() * 1;
        if(value == 0) {
            return "";
        }
        return value;
    };
    PortfolioItem.prototype.getCost = function () {
        var value = this.unitCost() * this.shares() * 1;
        if(value == 0) {
            return "";
        }
        return value;
    };
    PortfolioItem.prototype.getChangePercent = function () {
        if(this.change() * 1 == 0 && this.lastPrice() * 1 == 0) {
            return "";
        }
        return this.change() / this.lastPrice();
    };
    PortfolioItem.prototype.getGain = function () {
        if(this.cost() * 1 == 0 || this.value() * 1 == 0) {
            return "";
        }
        return this.value() - this.cost();
    };
    PortfolioItem.prototype.getGainPercent = function () {
        if(this.cost() * 1 == 0 || this.value() * 1 == 0) {
            return "";
        }
        return this.gain() / this.cost();
    };
    PortfolioItem.prototype.getColor = function () {
        var palette = ViewModel.palette;
        var index = this.portfolio.items.indexOf(this);
        return index > -1 ? palette[index % palette.length] : "black";
    };
    PortfolioItem.prototype.pricesChanged = function () {
        var prices = this.company.prices();
        if(prices.length > 1) {
            this.lastPrice(prices[0].price);
            this.change(prices[0].price - prices[1].price);
            if(this.chart()) {
                this.updateChartData();
            }
        }
    };
    PortfolioItem.prototype.parametersChanged = function () {
        this.shares(this.shares() * 1 == 0 ? "" : Globalize.format(this.shares() * 1, 'n2'));
        this.unitCost(this.unitCost() * 1 == 0 ? "" : Globalize.format(this.unitCost() * 1, 'n2'));
    };
    PortfolioItem.prototype.updateChartData = function () {
        var vm = this.portfolio.viewModel;
        vm.updateChartData();
    };
    return PortfolioItem;
})();
