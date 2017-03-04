package graph;

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
    String tick;

    public livetickers(String tick)
    {
        this.tick = tick;
    }

    public void test()
    {
        try {

            URL url = new URL("http://www.nasdaq.com/symbol/" + tick + "/real-time");
            URLConnection con = url.openConnection();
            InputStreamReader stream = new InputStreamReader(con.getInputStream());
            BufferedReader buff = new BufferedReader(stream);

            String line = buff.readLine();
            String temp[];

            while (line != null)
            {
                if (line.contains("<td>"))
                {
                    if (line.contains(":"))
                    {
                        temp = line.split(">");
                        temp = temp[1].split("<");
                        System.out.println(temp[0]);


                        line = buff.readLine();
                        temp = line.split("&");
                        temp = temp[0].split("\\$");
                        System.out.println(temp[1]);


                        line = buff.readLine();
                        temp = line.split(">");
                        temp = temp[1].split("<");
                        System.out.println(temp[0]);

                        System.out.println();

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
    }


    public double yesterdayClose()
    {
        return (yesterdayclose);
    }

}
