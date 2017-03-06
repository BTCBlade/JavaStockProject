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

        //STCOH test
        //chart.addIndicator(inda.stoch(data.open(), 50));
        //chart.addIndicator(inda.stoch(data.open(), 40));
        //chart.addIndicator(inda.stoch(data.open(), 20));

        //BBlower test
        //chart.addIndicator(inda.bsma(data.open(), 60));
        chart.addIndicator(inda.bblower(data.open(), 60, 2));

        //BBupper test
        chart.addIndicator(inda.bbupper(data.open(), 60, 2));

        //chart.addIndicator(inda.bsma(data.open(), 60));

        //MACD test
        //chart.addIndicator(inda.macd(data.open(), 12, 26));

        //chart.addIndicator(inda.macd(data.smoothed(), 12, 26));

        //EMA test

        chart.addIndicator(inda.ema(data.smoothed(), 4));
        chart.addIndicator(inda.ema(data.smoothed(), 6));
        chart.addIndicator(inda.ema(data.smoothed(), 12));
        chart.addIndicator(inda.ema(data.smoothed(), 16));
        chart.addIndicator(inda.ema(data.smoothed(), 20));



        //SMA test
        //chart.addIndicator(inda.fsma(data.smoothed(), 20));
        //chart.addIndicator(inda.bsma(data.smoothed(), 20));


        /*
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
        */
    }

}
