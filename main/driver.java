package main;

/**
 * Created by klongrich on 2/27/17.
 */
import data.qoutes;
import data.UpDateDatabase;
import graph.livetickers;
import indicators.listone;
import graph.Graph;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class driver {

    public static void main (String [] args)
    {
        listone inda = new listone();
        qoutes data = new qoutes("SPY", 300, 10);

        Graph chart = new Graph("SPY");
        //chart.addIndicator(inda.fsma(data.smoothed(), 20));
        //chart.addIndicator(inda.bsma(data.smoothed(), 20));

        livetickers x = new livetickers("SPY");
        //livetickers y = new livetickers("DOW");

        while (true) {
            double startTime = System.currentTimeMillis();
            x.volume();
            System.out.println(x.price());
            double endTime = System.currentTimeMillis();
            double totalTime = endTime - startTime;

            DecimalFormat dfseconds = new DecimalFormat("#.00");
            DecimalFormat dfminutes = new DecimalFormat("#");
            double minutes;
            double seconds;

            if ((totalTime / 1000) < 60) {
                System.out.println("Calculated in " + dfseconds.format(totalTime / 1000) + " seconds");
            } else {
                minutes = (totalTime / 1000) / 60;
                seconds = (totalTime / 1000) % 60;

                System.out.println("Calculated in " + dfminutes.format(minutes) + " minutes " + dfseconds.format(seconds) + " seconds");
            }
        }
        //System.out.println(x.yesterdayClose("spy"));
    }

}
