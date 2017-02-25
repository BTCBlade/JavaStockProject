package graph;

/**
 * Created by klongrich on 2/23/17.
 */
import data.qoutes;

import java.util.ArrayList;



public class driver {

    public static void main(String [] args)
    {
        ArrayList<Float> close = new ArrayList();
        Graph chart = new Graph();
        qoutes data = new qoutes("SPY" ,60,2);

        close = data.close();
        chart.plotone(close);
    }
}
