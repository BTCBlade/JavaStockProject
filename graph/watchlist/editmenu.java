package graph.watchlist;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by klongric on 3/22/17.
 */
public class editmenu extends JPanel {

    ArrayList <String> tickers = new ArrayList <String>();


    public editmenu(ArrayList<String> tickers)
    {
        setSize(250, 500);
        setVisible(true);
        this. tickers = tickers;
    }

    public void paintComponent(Graphics g2)
    {
        Graphics2D g = (Graphics2D)g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.gray);
        g.fillRect(0, 0, 250, 500);

        g.setColor(Color.white);
        g.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        for (int i = 0; i < tickers.size(); i++)
        {
            g.drawString(tickers.get(i), 30, i * 20);
            System.out.println(tickers.get(i));
        }
        System.out.println("Wokring");

    }

}
