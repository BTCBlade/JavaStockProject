package main;

/**
 * Created by klongrich on 2/27/17.
 */
import data.qoutes;
import data.UpDateDatabase;
import indicators.listone;
import graph.Graph;

import java.util.ArrayList;

public class driver {

    public static void main (String [] args)
    {
        listone inda = new listone();
        qoutes data = new qoutes("SPY", 60, 2);

        Graph chart = new Graph("SPY");
        //chart.addIndicator(inda.sma(data.low(), 50));

    }

}
