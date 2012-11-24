var Portfolio = (function () {
    function Portfolio(viewModel) {
        this.items = ko.observableArray([]);
        this.newSymbol = ko.observable("");
        this.canAddNewSymbol = ko.observable(false);
        var self = this;
        this.viewModel = viewModel;
        this.newSymbol.subscribe(function () {
            self.newSymbolChanged();
        });
    }
    Portfolio.prototype.saveItems = function () {
        if(localStorage != null && JSON != null) {
            var items = [];
            for(var i = 0; i < this.items().length; i++) {
                var item = this.items()[i];
                var newItem = {
                    symbol: item.symbol,
                    chart: item.chart(),
                    shares: item.shares(),
                    unitCost: item.unitCost()
                };
                items.push(newItem);
            }
            localStorage["items"] = JSON.stringify(items);
        }
    };
    Portfolio.prototype.loadItems = function () {
        var items = localStorage != null ? localStorage["items"] : null;
        if(items != null && JSON != null) {
            try  {
                items = JSON.parse(items);
                for(var i = 0; i < items.length; i++) {
                    var item = items[i];
                    this.addItem(item.symbol, item.chart, item.shares, item.unitCost);
                }
            } catch (err) {
            }
        }
        if(this.items().length == 0) {
            this.addItem("AMZN", false, 100, 200);
            this.addItem("AAPL", true, 100, 560);
            this.addItem("GE");
            this.addItem("GOOG", true, 100, 600);
            this.addItem("HPQ");
            this.addItem("IBM");
            this.addItem("MSFT");
            this.addItem("ORCL");
            this.addItem("V", true, 100, 85);
            this.addItem("YHOO", false, 100, 15);
        }
    };
    Portfolio.prototype.addItem = function (symbol, chart, shares, unitCost) {
        if (typeof chart === "undefined") { chart = false; }
        if (typeof shares === "undefined") { shares = 0; }
        if (typeof unitCost === "undefined") { unitCost = 0; }
        var item = new PortfolioItem(this, symbol, chart, shares, unitCost);
        this.items.push(item);
    };
    Portfolio.prototype.newSymbolChanged = function () {
        var newCompany = this.viewModel.findCompany(this.newSymbol());
        if(newCompany == null) {
            this.canAddNewSymbol(false);
            return;
        }
        var items = this.items();
        for(var i = 0; i < items.length; i++) {
            if(items[i].company == newCompany) {
                this.canAddNewSymbol(false);
                return;
            }
        }
        this.canAddNewSymbol(true);
    };
    Portfolio.prototype.addNewSymbol = function () {
        var item = new PortfolioItem(this, this.newSymbol());
        this.items.push(item);
        this.canAddNewSymbol(false);
    };
    Portfolio.prototype.removeItem = function (item) {
        var p = item.portfolio;
        var index = p.items.indexOf(item);
        p.items.splice(index, 1);
    };
    return Portfolio;
})();
