package data;

/**
  Created by klongrich on 2/23/17.
 **/

import static org.jsoup.Jsoup.connect;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class qoutes {

    ArrayList <Float> Open = new ArrayList();
    ArrayList <Float> High = new ArrayList();
    ArrayList <Float> Low = new ArrayList();
    ArrayList <Float> Close = new ArrayList();

    public qoutes(String tick, int intervals, int days)
    {
        try
        {
            URL url = new URL("https://www.google.com/finance/getprices?q=" + tick + "&i=" + intervals + "&p=" + days + "d&f=o,h,l,c");
            URLConnection con = url.openConnection();
            InputStreamReader stream = new InputStreamReader(con.getInputStream());
            BufferedReader buff = new BufferedReader(stream);

            String line = buff.readLine();
            String temp[];

            for (int i = 0; i < 7; i++)
                line = buff.readLine();

            while (line != null)
            {
                temp = line.split(",");
                Open.add(Float.parseFloat(temp[0]));
                High.add(Float.parseFloat(temp[1]));
                Low.add(Float.parseFloat(temp[2]));
                Close.add(Float.parseFloat(temp[3]));
                line = buff.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Float> smoothed()
    {
        ArrayList <Float> temp = new ArrayList();
        for (int i = 0; i < Open.size(); i++)
            temp.add((Open.get(i) + Close.get(i) + High.get(i) + Low.get(i)) / 4);
        return (temp);
    }

    public ArrayList<Float> open()
    {
        return (Open);
    }

    public ArrayList<Float> high()
    {
        return (High);
    }

    public ArrayList<Float> low()
    {
        return (Low);
    }

    public ArrayList<Float> close()
    {
        return (Close);
    }

    public void googleSearch(String searchTerm, int numberofResults)
    {
        String URL = "https://www.google.com/search";
        String searchURL = URL + "?q="+searchTerm+"&num="+numberofResults;

        try {
            Document doc =connect(searchURL).userAgent("Mozilla/5.0").get();
            Elements results = doc.select("h3.r > a");

            for (Element result : results) {
                String linkHref = result.attr("href");
                String linkText = result.text();
                System.out.println("Text::" + linkText + ", URL::" + linkHref.substring(6, linkHref.indexOf("&")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
