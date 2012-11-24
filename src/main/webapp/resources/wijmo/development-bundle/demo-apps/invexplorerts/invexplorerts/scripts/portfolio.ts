///<reference path='view-model.ts'/>

// declare externally defined objects (to keep TypeScript compiler happy)
declare var $;
declare var ko;
declare var Globalize;

/***********************************************************************************
* Portfolio class.
*/
class Portfolio {

    // fields
    viewModel: ViewModel;
    items = ko.observableArray([]);
    newSymbol = ko.observable("");
    canAddNewSymbol = ko.observable(false);

    // ** ctor
    constructor (viewModel: ViewModel) {
        var self = this;
        this.viewModel = viewModel;
        this.newSymbol.subscribe(function () { self.newSymbolChanged() });
    }

    // saves the portfolio to local storage
    saveItems() {
        if (localStorage != null && JSON != null) {

            // build array with items
            var items = [];
            for (var i = 0; i < this.items().length; i++) {
                var item = this.items()[i];
                var newItem = {
                    symbol: item.symbol,
                    chart: item.chart(),
                    shares: item.shares(),
                    unitCost: item.unitCost()
                };
                items.push(newItem);
            }

            // save array to local storage
            localStorage["items"] = JSON.stringify(items);
        }
    }

    // loads the portfolio from local storage (or initializes it with a few items)
    loadItems() {

        // try loading from local storage
        var items = localStorage != null ? localStorage["items"] : null;
        if (items != null && JSON != null) {

            try {
                items = JSON.parse(items);
                for (var i = 0; i < items.length; i++) {
                    var item = items[i];
                    this.addItem(item.symbol, item.chart, item.shares, item.unitCost);
                }
            }
            catch (err) {
                // ignore errors while loading...
            }
        }

        // no items? add a few now
        if (this.items().length == 0) {
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
    }

    // add a new item to the portfolio
    addItem(symbol: string, chart = false, shares = 0, unitCost = 0) {
        var item = new PortfolioItem(this, symbol, chart, shares, unitCost);
        this.items.push(item);
    }

    // update canAddNewSymbol when newSymbol changes
    newSymbolChanged() {

        // get the company that matches the newSymbol
        var newCompany = this.viewModel.findCompany(this.newSymbol());

        // no company? can't add...
        if (newCompany == null) {
            this.canAddNewSymbol(false);
            return;
        }

        // company already in portfolio? can't add
        var items = this.items();
        for (var i = 0; i < items.length; i++) {
            if (items[i].company == newCompany) {
                this.canAddNewSymbol(false);
                return;
            }
        }

        // seems OK
        this.canAddNewSymbol(true);
    }

    // add a new item based on the value of newSymbol
    addNewSymbol() {
        var item = new PortfolioItem(this, this.newSymbol());
        this.items.push(item);
        this.canAddNewSymbol(false);
    }

    // remove an item from the portfolio
    removeItem(item: PortfolioItem) {
        var p = item.portfolio;
        var index = p.items.indexOf(item);
        p.items.splice(index, 1);
    }
}
