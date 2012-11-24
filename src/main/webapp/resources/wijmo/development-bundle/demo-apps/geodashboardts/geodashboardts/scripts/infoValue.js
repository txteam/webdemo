var InfoValue = (function () {
    function InfoValue(key, name) {
        if (typeof name === "undefined") { name = ""; }
        this.name = ko.observable("");
        this.value = ko.observable(0);
        this.percent = ko.observable(0);
        this.percentMax = ko.observable(0);
        var self = this;
        self.key = key;
        self.name = ko.observable(name || "");
    }
    return InfoValue;
})();
