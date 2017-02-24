package data;

/**
 * Created by klongrich on 2/23/17.
 */

import java.util.*;

public class driver {

    public static void main(String [] args)
    {
        ArrayList <Float> smooth = new ArrayList();

        qoutes data = new qoutes("SPY", 60, 1);

        smooth = data.smoothed();
        System.out.println(smooth);
        data.googleSearch("Porn", 5);

    }
}
