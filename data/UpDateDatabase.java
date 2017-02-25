package data;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
         try
         {
             FileReader fr = new FileReader("./src/data/spyticks.txt");
             BufferedReader buff = new BufferedReader(fr);

             String tick[] = new String[505];

             for (int i = 0; i < 505; i++)
             {
                 qoutes data = new qoutes(buff.readLine() ,60,1);
                 data.save();
                 System.out.println(tick[i]);
             }


         } catch (IOException e)
         {
             e.printStackTrace();
         }
     }


}
