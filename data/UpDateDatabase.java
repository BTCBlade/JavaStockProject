package data;

/**
 * Created by klongrich on 2/25/17.
 */
public class UpDateDatabase {

    String indexs[] = {"DJI", "SPX", "NASDQ", "PENNY"};

     String ticks[] = {  "AAPL", "BA", "CAT", "CSCO", "CVX", "KO", "DD",
     "XOM", "GE", "GS", "HD", "IBM", "INTC", "JNJ", "JPM",
     "MCD", "MMM", "MRK", "MSFT", "NKE", "PFE", "PG",
     "TRV", "UNH", "UTX", "V", "VZ", "WMT", "DIS"};

    public void update()
    {
        for (int i = 0; i < ticks.length; i++)
        {
            qoutes data = new qoutes(ticks[i] ,60,1);
            data.save();
        }
    }
}
