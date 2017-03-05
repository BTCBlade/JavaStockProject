package indicators;

import java.util.ArrayList;

/**
 * Created by klongrich on 2/27/17.
 */
public class listone {

    public ArrayList<Double> fsma(ArrayList<Double> data, int period)
    {
        ArrayList <Double> answer = new ArrayList <Double>();
        double temp;

        temp = 0;
        for (int i = 0; i < data.size() - period; i++)
        {
            temp = 0;
            for (int j = 0; j < period; j++)
            {
                 temp += data.get(i + j);
            }
            temp /= period;
            answer.add(temp);
        }
        return (answer);
    }

    public ArrayList<Double> bsma(ArrayList<Double> data, int period)
    {
        ArrayList <Double> answer = new ArrayList <Double>();
        double temp;

        for (int i = period; i < data.size(); i++)
        {
            temp = 0;
            for (int j = 0; j < period; j++)
            {
                temp += data.get(i - j);
            }
            temp /= period;
            answer.add(temp);
        }
        return (answer);
    }

    public ArrayList<Double> ema(ArrayList <Double> data, int period)
    {
        ArrayList <Double> answer = new ArrayList <Double>();
        double mulit;
        double temp;
        double start;

        temp = 0;
        start = 0;
        mulit = (double)2 / (period + 1);
        for (int j = 0; j < period; j++)
        {
            start += data.get(j);
        }
        start /= period;
        answer.add((data.get(0) - start) * mulit + start);
        temp = start;
        for (int i = 1; i < data.size() - period; i++)
        {
            temp = (data.get(i) - temp) * mulit + temp;
            answer.add(temp);
        }
        return (answer);
    }

    public ArrayList <Double> macd(ArrayList<Double> data, int period1, int period2)
    {
        ArrayList <Double> answer = new ArrayList <Double>();
        ArrayList <Double> ema1 = new ArrayList<Double>();
        ArrayList <Double> ema2 = new ArrayList<Double>();
        int len;

        ema1 = ema(data, period1);
        ema2 = ema(data, period2);
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
}
