package indicators;

import java.util.ArrayList;

/**
 * Created by klongrich on 3/9/17.
 */
public class listtwo {

    listone x = new listone();

    public ArrayList<Double> macd(ArrayList<Double> data, int period1, int period2)
    {
        ArrayList <Double> answer = new ArrayList <Double>();
        ArrayList <Double> ema1 = new ArrayList<Double>();
        ArrayList <Double> ema2 = new ArrayList<Double>();
        int len;

        ema1 = x.ema(data, period1);
        ema2 = x.ema(data, period2);
        if (ema1.size() < ema2.size())
            len = ema1.size();
        else
            len = ema2.size();
        for (int i = 0; i < len; i++)
        {
            answer.add(ema1.get(i) - ema2.get(i));
        }
        return (answer);
    }

    //Room for speed up.
    public ArrayList <Double> stoch(ArrayList <Double> data, int period)
    {
        ArrayList <Double> answer = new ArrayList <Double>();
        double ll = data.get(0);
        double hh = data.get(0);
        double temp;

        for (int i = period; i < data.size(); i++)
        {
            for (int x = 0; x < period; x++)
            {
                temp = data.get(i - x);
                if (temp > hh)
                    hh = temp;
                if (temp < ll)
                    ll = temp;
            }
            answer.add(((data.get(i) - ll) / (hh - ll)) * 100);
        }
        return (answer);
    }
}
