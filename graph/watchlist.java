package graph;

import data.UpDateDatabase;
import data.qoutes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by klongrich on 3/2/17.
 */
public class watchlist extends JPanel implements MouseWheelListener, ActionListener {

    private int offset = 25;
    private ArrayList <String> names = new ArrayList<String>();
    private ArrayList <Double> change = new ArrayList<Double>();
    private Timer time;
    public String filename;

    public watchlist(String name)
    {
        time = new Timer(10000, this);
        time.start();
        filename = name;
        setSize(180, 300);
        setVisible(true);
        addMouseWheelListener(this);

        try {
            FileReader fr = new FileReader("./src/graph/lists/" + name + ".txt");
            BufferedReader buff = new BufferedReader(fr);

            String line = buff.readLine();
            while (line != null)
            {
                names.add(line);
                livetickers currentprice = new livetickers(line);
                change.add(((currentprice.price()/ currentprice.yesterdayclose) - 1) * 100);
                line = buff.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void updatelist(String name)
    {
        filename = name;
        try {
            FileReader fr = new FileReader("./src/graph/lists/" + name + ".txt");
            BufferedReader buff = new BufferedReader(fr);

            String line = buff.readLine();
            while (line != null)
            {
                names.add(line);
                livetickers currentprice = new livetickers(line);
                change.add(((currentprice.price()/ currentprice.yesterdayclose) - 1) * 100);
                line = buff.readLine();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        clear();
    }

    public void putborder(int x, int y, Graphics g)
    {
        int width;

        width = 2;
        g.setColor(Color.black);
        g.fillRect(0, 0, width, y);
        g.fillRect(0,0, x, width);
        g.fillRect(0, y - width, x, width);
        g.fillRect(x - width, 0, width, y);
    }

    public void clear()
    {
        names.clear();
        change.clear();
        offset = 25;
        repaint();
    }

    public void addticker(String tick)
    {
        DateFormat df = new SimpleDateFormat("MM-dd-yy");
        Date dateobj = new Date();

        try {
            FileWriter fw = new FileWriter("./src/graph/lists/" + df.format(dateobj) + ".txt", true);
            fw.write(tick + "\n");
            System.out.println("Writing " + tick);
            fw.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        double move;
        livetickers currentprice = new livetickers(tick);
        move = ((currentprice.price()/ currentprice.yesterdayclose) - 1) * 100;
        names.add(tick);
        change.add(move);
    }

    public void update()
    {
        change.clear();
        for (int i = 0; i < names.size(); i++)
        {
            livetickers currentprice = new livetickers(names.get(i));
            change.add(((currentprice.price()/ currentprice.yesterdayclose) - 1) * 100);
        }
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //update();
    }

    public void paintComponent(Graphics g2)
    {
        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        String pchange;
        Color c =new Color(0x1E1E1E);
        g.setColor(c);
        g.fillRect(0,0,200,300);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
        NumberFormat formatter = new DecimalFormat("#0.00");

        for (int i = 0; i < names.size(); i++) {
            g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            g.setColor(Color.white);
            g.drawString(names.get(i), 20, offset + i * 50);
            if (change.get(i) > 0)
                g.setColor(Color.green);
            else
                g.setColor(Color.red);
            pchange = formatter.format(change.get(i));
            g.drawString(pchange, 115, offset + i * 50);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
            g.drawString(" %", 155, offset + i * 50);
            g.fillRect(0, offset + 20 + (i * 50), 200, 3);
        }
        putborder(200, 300, g);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double morespace;

        morespace = 25 - ((names.size() - 6) * 55);
        if (e.getWheelRotation() == 1) {
            if (names.size() > 6)
            {
                if (offset > morespace)
                {
                    offset -= 5;
                }
            }
        }
        else if(e.getWheelRotation() == -1)
        {
            if (offset <= 20) {
                offset += 5;
            }
        }
        repaint();
    }
}