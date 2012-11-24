///<reference path='infoSource.ts'/>
///<reference path='infoValue.ts'/>

// declare externally defined objects (to keep TypeScript compiler happy)
declare var $;
declare var ko;

/***********************************************************************************
* ViewModel class.
* This class provides various types of demographic information for a given 
* location.
* The information is exposed as a 'sources' property, and the location is
* exposed as an 'extent' property.
*/
class ViewModel {

    // fields
    sources: any;
    selectedLocation = ko.observable(""); // name of the location currently selected
    infoScale = ko.observable(4); // information scale (state = 4, county = 3, tract = 2, f(extent))
    extent = ko.observable(null); // location to retrieve information for

    constructor () {
        var self = this;

        // information sources available to views
        self.sources = {};
        self.sources.age = new InfoSource(self, "USA_Median_Age", "TOTPOP_CY,MEDAGE_CY,AGEBASE_CY,POPU5_CY,POP5_CY,POP10_CY,POP15_CY,POP20_CY,POP25_CY,POP30_CY,POP35_CY,POP40_CY,POP45_CY,POP50_CY,POP55_CY,POP60_CY,POP65_CY,POP70_CY,POP75_CY,POP80_CY,POP85_CY", "POPU5_CY,POP85_CY", "2010 Population, Age ");
        self.sources.householdIncome = new InfoSource(self, "USA_Median_Household_Income", "TOTPOP_CY,MEDHINC_CY,HINCBASECY,HINC0_CY,HINC10_CY,HINC15_CY,HINC20_CY,HINC25_CY,HINC30_CY,HINC35_CY,HINC40_CY,HINC45_CY,HINC50_CY,HINC60_CY,HINC75_CY,HINC100_CY,HINC125_CY,HINC150_CY,HINC200_CY,HINC250_CY,HINC500_CY", "HINC0_CY,HINC500_CY", "2010 Household Income ");
        self.sources.netWorth = new InfoSource(self, "USA_Median_Net_Worth", "TOTPOP_CY,MEDVAL_I,MEDHINC_I,MEDNW_CY,MEDNW_I,NWBASE_CY,NW0_CY,NW15_CY,NW35_CY,NW50_CY,NW75_CY,NW100_CY,NW150_CY,NW250_CY,NW500_CY,NW1M_CY", "NW0_CY,NW1M_CY", "2010 Households with Net Worth ");
        self.sources.homeValue = new InfoSource(self, "USA_Median_Home_Value", "TOTPOP_CY,MEDVAL_CY,MEDVAL_I,MEDHINC_I,MEDNW_I,VALBASE_CY,VALU10K_CY,VAL10K_CY,VAL15K_CY,VAL20K_CY,VAL25K_CY,VAL30K_CY,VAL35K_CY,VAL40K_CY,VAL50K_CY,VAL60K_CY,VAL70K_CY,VAL80K_CY,VAL90K_CY,VAL100K_CY,VAL125K_CY,VAL150K_CY,VAL175K_CY,VAL200K_CY,VAL250K_CY,VAL300K_CY,VAL400K_CY,VAL500K_CY,VAL750K_CY,VAL1M_CY", "VALU10K_CY,VAL1M_CY", "2010 Home Value ");
        self.sources.tapestry = new InfoSource(self, "USA_Tapestry", "DOMTAP,TAPSEGNAM");
        self.sources.daytimePopulation = new InfoSource(self, "USA_Daytime_Population", "TOTPOP_CY,DNRATIO_CY");
        self.sources.populationDensity = new InfoSource(self, "USA_Population_Density", "TOTPOP_CY,POPDENS_CY");
        self.sources.populationBySex = new InfoSource(self, "USA_Population_by_Sex", "TOTPOP_CY,PMALE_CY,PFEMALE_CY");
        self.sources.retailSpendingPotential = new InfoSource(self, "USA_Retail_Spending_Potential", "TOTPOP_CY,MEDHINC_CY,X15001_A,X15001_I");
        self.sources.unemploymentRate = new InfoSource(self, "USA_Unemployment_Rate", "TOTPOP_CY,MEDVAL_I_12,MEDHINC_I_12,MEDNW_I_12,UNEMP_CY_12,UNEMPRT_CY_12");

        // add summary information
        var list = [];
        list.push(new InfoValue("POPU5_CY POP5_CY POP10_CY POP15_CY POP20_CY POP25_CY", "under 30"));
        list.push(new InfoValue("POP30_CY POP35_CY POP40_CY POP45_CY POP50_CY POP55_CY", "30 to 59"));
        list.push(new InfoValue("POP60_CY POP65_CY POP70_CY POP75_CY POP80_CY POP85_CY", "60 and over"));
        self.sources.age.shortList = list;

        list = [];
        list.push(new InfoValue("HINC0_CY HINC10_CY HINC15_CY HINC20_CY HINC25_CY HINC30_CY HINC35_CY", "under 40k"));
        list.push(new InfoValue("HINC40_CY HINC45_CY HINC50_CY HINC60_CY HINC75_CY", "40k to 100k"));
        list.push(new InfoValue("HINC100_CY HINC125_CY HINC150_CY HINC200_CY HINC250_CY HINC500_CY", "100k and above"));
        self.sources.householdIncome.shortList = list;

        list = [];
        list.push(new InfoValue("NW0_CY NW15_CY NW35_CY", "under 50k"));
        list.push(new InfoValue("NW50_CY NW75_CY NW100_CY", "50k to 150k"));
        list.push(new InfoValue("NW150_CY NW250_CY NW500_CY NW1M_CY", "150k and above"));
        self.sources.netWorth.shortList = list;

        list = [];
        list.push(new InfoValue("VALU10K_CY VAL10K_CY VAL15K_CY VAL20K_CY VAL25K_CY VAL30K_CY VAL35K_CY VAL40K_CY VAL50K_CY VAL60K_CY VAL70K_CY VAL80K_CY VAL90K_CY VAL100K_CY VAL125K_CY", "under 150k"));
        list.push(new InfoValue("VAL150K_CY VAL175K_CY VAL200K_CY VAL250K_CY VAL300K_CY VAL400K_CY", "150k to 500k"));
        list.push(new InfoValue("VAL500K_CY VAL750K_CY VAL1M_CY", "500k and above"));
        self.sources.homeValue.shortList = list;

        // name of the location currently selected
        self.selectedLocation = ko.observable("");

        // information scale (state = 4, county = 3, tract = 2, f(extent))
        self.infoScale = ko.observable(4);

        // location to retrieve information for
        self.extent = ko.observable(null);
        self.extent.subscribe(function (newExtent) {
            self.updateValues();
        });
    }

    // Updates the information in all InfoValues that have subscribers attached to them
    // based on the location defined by a given map extent.
    updateValues() {
        var self = this;

        // update info scale
        self.infoScale(4); // state level
        if (self.extent()) {
            if (self.extent().getWidth() < 6e6) {
                self.infoScale(3); // county level
            }
            if (self.extent().getWidth() < 2e5) {
                self.infoScale(2); // tract level
            }
        }

        // update all infoValues
        for (var member in this.sources) {
            var source = this.sources[member];
            source.updateValues();
        }
    }


    // gets a textual description for an "index" value. Index values represent a comparison
    // where the value "100" represents the national average, "50" represents half, and "200"
    // represents twice the national average.
    getIndexDescription(index: number) {
        var desc =
            index < 50 ? "Substantially lower" :
            index < 80 ? "Lower" :
            index < 100 ? "Slightly lower" :
            index < 120 ? "Slightly higher" :
            index < 200 ? "Higher" : "Substantially higher";
        return desc + " than the national average.";
    }

    // gets the position of an index slider as a percentage (0 to 1).
    getSliderPosition(index: number) {
        index = Math.log(index / 100);
        if (index > +1.5) index = +1.5;
        if (index < -1.5) index = -1.5;
        return (index + 1.5) / 3;
    }

    // extracts values from a list and builds a 'seriesList' that can be used as a
    // data source for a wijmo chart control.
    formatChartSeriesList(list : InfoValue[]) {
        var seriesList = [];
        var xData = [];
        var yData = [];

        for (var i = 0; i < list.length; i++) {
            xData.push(list[i].name());
            yData.push(list[i].value());
        }

        seriesList.push({
            label: "Homes",
            data: { x: xData, y: yData }
        });

        return seriesList;
    }
}