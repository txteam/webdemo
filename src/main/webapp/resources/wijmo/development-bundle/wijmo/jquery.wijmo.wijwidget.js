/// <reference path="declarations/jquery.d.ts"/>
/// <reference path="declarations/jquery.ui.d.ts"/>
/// <reference path="declarations/wijmo.d.ts"/>
/*globals Globalize window jQuery wijInputResult document wijmo */
/*
*
* Wijmo Library 2.3.0
* http://wijmo.com/
*
* Copyright(c) GrapeCity, Inc.  All rights reserved.
*
* Dual licensed under the Wijmo Commercial or GNU GPL Version 3 licenses.
* licensing@wijmo.com
* http://wijmo.com/license
*
*
* * Wijmo Widget base class.
*
*/

var wijmo;
(function (wijmo) {
    "use strict";
    // Declarations to support TypeScript type system
    var WijmoWidget = (function () {
        function WijmoWidget() { }
        WijmoWidget.prototype.destroy = function () {
        };
        WijmoWidget.prototype.disable = function () {
        };
        WijmoWidget.prototype._trigger = function (name, eventObj, data) {
        };
        WijmoWidget.prototype._setOption = function (name, value) {
        };
        WijmoWidget.prototype.option = function (name, value) {
        };
        WijmoWidget.prototype.widget = function () {
            return null;
        };
        return WijmoWidget;
    })();
    wijmo.WijmoWidget = WijmoWidget;    
    WijmoWidget.prototype = {
    };
})(wijmo || (wijmo = {}));

