package data;

import java.io.*;
import java.net.*;

/**
   Created by klongrich on 2/23/17.
 **/

public class getcurrentprice {

    public float currentprice()
    {
        try
        {
            final String ticker = "[\"KO\",";
            URL url = new URL("https://www.google.com/finance?q=KO&ei=uZmvWNGABcax2AaQ1rvwDg");

            URLConnection urlConn = url.openConnection();
            InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
            BufferedReader buff = new BufferedReader(inStream);

            String sprice = "Not found";
            String line = buff.readLine();
            while (line != null)
            {
                if(line.contains(ticker))
                {
                    int target = line.indexOf(ticker);
                    int deci = line.indexOf(".", target);
                    int start = deci;
                    while(line.charAt(start) != '\"')
                    {
                        start--;
                    }
                    sprice = line.substring(start + 1, deci + 3);
                }
                line = buff.readLine();
            }
            return (Float.parseFloat(sprice));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (0);
    }

}
