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

    String tick;

    qoutes(String tick, int intervals, int days)
    {
        this.tick = tick;
    }

    private ArrayList <Float> rawdata()
    {
        ArrayList <Float> data = new ArrayList();

        try
        {
            URL url = new URL("https://www.google.com/finance/getprices?q=" + tick + "&i=60&p=12d&f=0,h,l,c");
            URLConnection con = url.openConnection();
            InputStreamReader stream = new InputStreamReader(con.getInputStream());
            BufferedReader buff = new BufferedReader(stream);

            String line = buff.readLine();
            while (line != null)
            {
                System.out.println(line);
                line = buff.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
            return (data);
    }

    public void test()
    {
        ArrayList <Float> x = new ArrayList();
        x = rawdata();
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

    public float getcurrent () throws IOException
    {
        String ticker = "[\"" + tick + "\",";
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


}
