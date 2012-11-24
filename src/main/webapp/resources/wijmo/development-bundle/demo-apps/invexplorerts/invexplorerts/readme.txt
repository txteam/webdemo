InvexplorerTS
--------------------------------------------------------------------------------------------
Implements a stock browser application similar to Google Finance.

This sample is similar to the InvexplorerJS sample, except the ViewModel classes were 
re-written using TypeScript, a compiled version of JavaScript that adds support for 
strongly-typed variables, classes, Intellisense, and more. For details about TypeScript, 
visit http://www.typescriptlang.org/.

This sample follows the MVVM pattern. The ViewModel contains a Portfolio class with a
list of items which correspond to stocks. Each item contains historical information 
about the stock as well as quantity and purchase price information.

This data allows the application to show the gain realized for each item as well as a 
chart that allows users to compare the performance of each stock.

Users can add or remove items from the portfolio using a jQueryUI auto-search control.
Items in the portfolio can be charted using a Wijmo line chart control. Both of these
controls are bound to the ViewModel using KnockoutJS.

The portfolio information is persisted to local storage using the HTML5 localStorage
object, which is supported by all modern browsers.

This application is the HTML5/JavaScript version of the Silverlight Invexplorer 
application. Both version use the same architecture and similar ViewModel classes.

NOTE: The application retrieves stock information using Yahoo Finance services. These
services are not free and cannot be used in commercial applications without permission
from Yahoo.