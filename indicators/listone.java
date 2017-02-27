package indicators;

import java.util.ArrayList;

/**
 * Created by klongrich on 2/27/17.
 */
public class listone {

    public ArrayList<Float> sma(ArrayList<Float> data, int period)
    {
        ArrayList <Float> answer = new ArrayList();
        float temp;

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

}
