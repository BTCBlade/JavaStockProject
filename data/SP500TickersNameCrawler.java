package testing;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by klongrich on 2/25/17.
 */
public class SP500TickersNameCrawler {

    public static void main(String [] args) {
        try {
            URL url = new URL("https://en.wikipedia.org/wiki/List_of_S%26P_500_companies");
            URLConnection con = url.openConnection();
            InputStreamReader stream = new InputStreamReader(con.getInputStream());
            BufferedReader buff = new BufferedReader(stream);
            FileWriter fw = new FileWriter("./src/data/spyticks.txt", true);

            String line = buff.readLine();
            String temp[];
            String temp2[];

            while (line != null) {
                line = buff.readLine();
                if (line.contains("<tr>"))
                {
                    line = buff.readLine();
                    temp = line.split(">");
                    temp2 = temp[2].split("<");
                    fw.write(temp2[0] + "\n");
                    System.out.println(temp2[0]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

