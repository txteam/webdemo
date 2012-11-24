using System;
using System.IO;
using System.Collections.Generic;
using System.Net;
using System.Web;
using System.Text;

namespace StockInfo
{
    /// <summary>
    /// StockInfo returns two types of information:
    /// 
    /// Stock Prices:
    /// If the request contains a 'symbol' parameter, StockInfo returns a string
    /// with a list where each line contains a date and the closing value for the
    /// stock on that day. Dates are between 1/1/2000 and today.
    /// Values are obtained from the Yahoo finance service.
    /// 
    /// Company Names and Symbols
    /// If the request does not contain a 'symbol' parameter, StockInfo returns
    /// a string with a list where each line contains company symbols and company names.
    /// Values are loaded from resource file 'resources/symbolnames.txt'.
    /// 
    /// In both cases, lines are separated by '\r' characters and values within 
    /// each line are separated by '\t' characters.
    /// </summary>
    public class StockInfo : IHttpHandler
    {
        public void ProcessRequest(HttpContext context)
        {
            var symbol = context.Request["symbol"];
            var content = string.IsNullOrEmpty(symbol) ? GetSymbols() : GetPrices(symbol);

            context.Response.ContentType = "text/plain";
            context.Response.Write(content);
        }
        public bool IsReusable
        {
            get { return false; }
        }

        // ** implementation

        // get closing prices for a given stock between 1/1/2008 and today
        // (uses Yahoo finance service)
        string GetPrices(string symbol)
        {
            var fmt = "http://ichart.finance.yahoo.com/table.csv?s={0}&a={1}&b={2}&c={3}&d={4}&e={5}&f={6}&g=d";
            // s: 0: stock symbol
            // a,b,c: 1,2,3: start month, day, year
            // d,e,f: 4,5,6: end month, day, year
            var t = DateTime.Today;
            var url = string.Format(fmt, symbol, 1, 1, 2008, t.Month, t.Day, t.Year);

            // get content
            var sb = new StringBuilder();
            var wc = new WebClient();
            using (var sr = new StreamReader(wc.OpenRead(url)))
            {
                // skip headers
                sr.ReadLine();

                // skip first line (same date as the next!)
                sr.ReadLine();

                // read each line
                for (var line = sr.ReadLine(); line != null; line = sr.ReadLine())
                {
                    // append date (field 0) and adjusted close price (field 6)
                    var items = line.Split(',');
                    sb.AppendFormat("{0}\t{1}\r", items[0], items[6]);
                }
            }

            // done
            var content = sb.ToString().Trim();
            return content;
        }

        // gets 
        string GetSymbols()
        {
            var path = HttpContext.Current.Server.MapPath("resources/symbolNames.txt");
            using (var sr = new StreamReader(path))
            {
                var content = sr.ReadToEnd();
                content = content.Replace("\r\n", "\r").Trim();
                return content;
            }
        }
    }
}