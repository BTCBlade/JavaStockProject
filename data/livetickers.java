package graph;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.SyncFailedException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by klongrich on 3/3/17.
 */
public class livetickers {

    double yesterdayclose;
    double cp;

    String tick;
    InputStreamReader stream;

    public livetickers(String tick)
    {
        this.tick = tick;

        try {
            URL url = new URL("http://www.nasdaq.com/symbol/" + tick + "/real-time");
            URLConnection con = url.openConnection();
            InputStreamReader stream = new InputStreamReader(con.getInputStream());
            this.stream = stream;
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }



    public String volume()
    {
        try {
            BufferedReader buff = new BufferedReader(stream);

            String line = buff.readLine();
            String temp[];

            while (line != null)
            {
                if (line.contains("<td>"))
                {
                    if (line.contains(":"))
                    {
                        //Getting time.
                        //temp = line.split(">");
                        //temp = temp[1].split("<");
                        //System.out.println(temp[0]);

                        //Getting the price
                        line = buff.readLine();
                        temp = line.split("&");
                        temp = temp[0].split("\\$");
                        cp = Double.parseDouble(temp[1]);

                        //Getting the volume.
                        line = buff.readLine();
                        temp = line.split(">");
                        temp = temp[1].split("<");
                        return (temp[0]);
                    }

                    //Getting the Perevious Close
                    if (line.contains("PreviousClose")) {
                        temp = line.split("/");
                        temp = temp[0].split(">");
                        temp = temp[2].split("<");
                        yesterdayclose = Double.parseDouble(temp[0]);
                    }
                }
                line = buff.readLine();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return (null);
    }

    public double price()
    {
        return (cp);
    }


    public double yesterdayClose()
    {
        return (yesterdayclose);
    }

}
