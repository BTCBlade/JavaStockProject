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

}
