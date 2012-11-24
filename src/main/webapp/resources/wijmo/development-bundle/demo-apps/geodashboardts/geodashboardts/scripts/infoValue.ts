// declare externally defined objects (to keep TypeScript compiler happy)
declare var ko;

/***********************************************************************************
* InfoValue class: key, a name, and value.
*/
class InfoValue {
    
    key: string;
    name = ko.observable("");
    value = ko.observable(0);
    percent = ko.observable(0);
    percentMax = ko.observable(0);

    constructor (key: string, name = "") {
        var self = this;
        self.key = key;
        self.name = ko.observable(name || "");
    }
}
