GeoDashboardTs
---------------------------------------------------
An interactive geographic dashboard for analyzing demographic data.

This is the HTML5/TypeScript version of the sample written to demonstrate 
MVVM patterns in Javascript/HTML5 and in Silverlight. 

This sample is similar to the GeoDashboardJS sample, except the ViewModel
classes were re-written using TypeScript, a compiled version of JavaScript
that adds support for strongly-typed variables, classes, Intellisense, and
more. For details about TypeScript, visit http://www.typescriptlang.org/.

The sample creates a ViewModel that has an Extent property and several
information sources. Changing the Extent causes the ViewModel to update 
the information using ArcGIS online.

The information is displayed in tiles bound to the ViewModel. Clicking
a tile will display a corresponding layer on the map.

The sample also has a link that selects the user's current location using
HTML5 location services. This is available in most modern browsers 
(but not in IE8 and earlier). 

This sample uses KnockoutJS for MVVM support in JavaScript. KnockoutJS 
provides declarative bindings in HTML elements. It also provides 
observable support for object, properties and arrays. This enables "live" 
bindings in HTML and JavaScript similar to what is found in Silverlight.

The map in this application is an ESRI ArcGIS map widget. 

It also uses Wijmo for UI controls that support MVVM in JavaScript. This 
sample utilizes the Wijmo Bar Chart and Radial Gauge widgets to visualize
the data. 

GlobalizeJS is used to format the numeric values as currency, percentages
or formatted numbers.

<product>Wijmo;HTML5</product>