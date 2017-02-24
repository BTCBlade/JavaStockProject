package data;

import java.io.*;
import java.net.*;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import static org.jsoup.Jsoup.*;

/**
   Created by klongrich on 2/23/17.
 **/

public class getcurrentprice {

    String tick;

    getcurrentprice(String tick)
    {
        this.tick = tick;
    }

    public void googleSearch(String searchTerm)
    {
        int numberofResults = 5;
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

    public float currentprice()
    {
        try
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
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (0);
    }

}
