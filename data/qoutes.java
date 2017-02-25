package data;

/**
  Created by klongrich on 2/23/17.
 **/

import static org.jsoup.Jsoup.connect;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class qoutes {

    ArrayList <Float> Open = new ArrayList();
    ArrayList <Float> High = new ArrayList();
    ArrayList <Float> Low = new ArrayList();
    ArrayList <Float> Close = new ArrayList();
    ArrayList <Float> Volume = new ArrayList();
    String tick;

    public qoutes(String tick, int intervals, int days)
    {
        this.tick = tick;

        try
        {
            URL url = new URL("https://www.google.com/finance/getprices?q=" + tick + "&i=" + intervals + "&p=" + days + "d&f=o,h,l,c,v,");
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
                Volume.add(Float.parseFloat(temp[4]));
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

    public void save()
    {
        int temp = 0;
        String o,h,l,c,v;
        DateFormat df = new SimpleDateFormat("MM-dd-yy");
        Date dateobj = new Date();

        String indexs[] = {"DJI", "SPX", "NASDQ", "PENNY"};

        String ticks[] = {  "APPL", "BA", "CAT", "CSCO", "CVX", "KO", "DD",
                            "XOM", "GE", "GS", "HD", "IBM", "INTC", "JNJ", "JPM",
                            "MCD", "MMM", "MRK", "MSFT", "NKE", "PFE", "PG",
                            "TRV", "UNH", "UTX", "V", "V2", "WMT", "DIS"};
            try {

                FileWriter fw = new FileWriter("./src/data/pastdata/" + indexs[0] + "/" + tick + "/" + df.format(dateobj) + ".csv", true);
                fw.write("Open,High,Low,Close,Volume\n");
                for (int i = 0; i < Open.size(); i++) {
                    o = String.valueOf(Open.get(i));
                    h = String.valueOf(High.get(i));
                    l = String.valueOf(Low.get(i));
                    c = String.valueOf(Close.get(i));
                    v = String.valueOf((Volume.get(i)));

                    fw.write(o + "," + h + "," + l + "," + c + "," + v + "\n");
                    temp += 1;
                }
                fw.close();
                System.out.println(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    public float getCurrentprice()
    {
        try
        {
            final String ticker = "[\"" + tick + "\",";
            URL url = new URL("https://www.google.com/finance?q=" + tick + "&ei=uZmvWNGABcax2AaQ1rvwDg");
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
}
