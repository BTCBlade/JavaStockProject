package graph;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.FlatteningPathIterator;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.awt.image.*;

/**
 * Created by klongrich on 2/23/17.
 */

import data.qoutes;

public class Graph extends JFrame implements ActionListener, MouseListener, MouseMotionListener {

    JButton onemin;
    JButton fivemin;
    JButton tenmin;
    JButton fiftenmin;
    JButton thritymin;
    JButton addticker;
    JButton clearwatchlist;

    double yline = 0;
    double xline = 0;
    boolean newchart = true;
    private int width;
    private int height;
    private double max = 0;
    double maxtemp = 0;
    private double min = 0;
    private double xvalue = 0;
    private double yvalue = 0;
    private int numberofdays = 2;
    private int intervals = 60;
    String tick;
    private ArrayList<Double> ypoints = new ArrayList <Double>();
    ArrayList<ArrayList<Double>> indactors = new ArrayList <ArrayList<Double>>();
    Timer time;

    ImageIcon icon;
    watchlist list;

    public Graph(String tick)
    {
        width = 1250;
        height = 550;
        this.tick = tick;
        time = new Timer(100, this);
        time.start();
        setBackground(Color.black);
        list = new watchlist();
        list.addticker("SPY");
        list.addticker("KO");
        list.setLocation(960, 100);
        add(list);
        ArrayList <Double> x = new ArrayList <Double>();
        qoutes data = new qoutes(tick,60,2);
        x = data.smoothed();
        init(x);

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        onemin = createButton("1 Min", 790, 60, 2);
        fivemin = createButton("5 Min", 600, 300, 10);
        tenmin = createButton("10 Min", 420, 600, 20);
        fiftenmin = createButton("15 Min", 220, 900, 30);
        thritymin = createButton("30 Min", 30, 1800, 50);
        addticker = watchlistButton();
        clearwatchlist = clearButton();

        add(onemin, BorderLayout.PAGE_START);
        add(fivemin);
        add(tenmin);
        add(fiftenmin);
        add(thritymin);
        add(addticker);
        add(clearwatchlist);

        addMouseListener(this);
        addMouseMotionListener(this);
        setVisible(true);
    }

    public JButton createButton(String name, int x, int interval, int days) {
        JButton button = new JButton(name);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    newchart = true;
                    ArrayList<Double> x = new ArrayList <Double>();
                    qoutes data = new qoutes(tick, interval, days);
                    x = data.smoothed();
                    numberofdays = days;
                    intervals = interval;
                    icon = init(x);
                    repaint();
            }
        });
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        button.setLocation(x, 515);
        button.setSize(110, 25);
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        return (button);
    }

    public JButton watchlistButton()
    {
        JButton button = new JButton("+");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Ticker", "");
                list.addticker(name);
                list.addticker("DOW");
                list.addticker("KO");
                list.addticker("FB");
                list.addticker("GOOG");
                repaint();
            }
        });
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        button.setLocation(1120, 60);
        button.setSize(25, 25);
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        return (button);

    }

    public JButton clearButton()
    {
        JButton button = new JButton("Clear");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (list.names != null)
                    list.clear();
            }
        });
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        button.setLocation(1000, 430);
        button.setSize(100, 25);
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        return (button);
    }

    private double maxValue(ArrayList<Double> array) {
        double max;

        max = array.get(0);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }
        return (max);
    }

    private double minValue(ArrayList<Double> array) {
        double min;

        min = array.get(0);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) < min) {
                min = array.get(i);
            }
        }
        return (min);
    }

    public void addIndicator( ArrayList<Double> data)
    {
        indactors.add(data);
    }

    private ArrayList<Double> minmax(ArrayList<Double> data) {
        ArrayList<Double> x = new ArrayList <Double>();

        for (int i = 0; i < data.size(); i++)
            x.add((data.get(i) - min) / (max - min));
        return (x);
    }

    public ImageIcon init(ArrayList<Double> data)
    {
        ypoints = points(data);
        xvalue = width / data.size();
        yvalue = (maxValue(data) - minValue(data)) / 20;
        int x;

        x = ypoints.size() - 1;

        BufferedImage image = new BufferedImage(800, 450, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = (Graphics2D)image.getGraphics();

        for (int i = 0; i < 450; i += 20)
        {
            Color c =new Color(0.5803922f, 0.6f, 0.6392157f, 0.2f);
            g.setColor(c);
            g.drawLine(0, i, 900, i);
        }

        if (xvalue > 1)
            xvalue = 1;
        g.setColor(Color.red);
        for (double i = 800; i > 800 - ypoints.size() + 1 ; i -= xvalue) {
            x--;
            g.draw(new Line2D.Double(i, ypoints.get(x), i , ypoints.get(x + 1)));
        }

        double test;

        ArrayList <Double> convert = new ArrayList <Double> ();
        g.setColor(Color.green);
        for (int ii = 0; ii < indactors.size(); ii++)
        {
            convert = points(indactors.get(ii));
            x = convert.size() - 1;
            if (ii == 1)
            {
                test = 20;
                g.setColor(Color.blue);
            }
            else
            {
                test = 0;
            }
            System.out.println(x);
            for (double i = 780 + test; i > 780 - convert.size() + 1 + test; i -= (width / convert.size())) {
                x--;
                g.draw(new Line2D.Double(i, convert.get(x), i , convert.get(x + 1)));
            }
        }
        ImageIcon icon = new ImageIcon(image);
        return (icon);

    }

    private void putprice(Graphics g2) {
        double price;

        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.white);
        maxtemp = max;
        for (int i = 50; i < height - 50; i += 20) {
            price = max;
            price = Math.round(price * 100.0) / 100.0;
            g.drawString(Double.toString((price)), 855, i + 8);
            g.fillRect(831, i, 5, 3);
            max -= yvalue;
        }
        max = maxtemp;
    }


    public ArrayList <Double> points(ArrayList <Double> data)
    {
        ArrayList <Double> convert = new ArrayList <Double>();
        ArrayList <Double> points = new ArrayList <Double>();

        max = maxValue(data);
        min = minValue(data);
        convert = minmax(data);

        for (int i = 0; i < data.size(); i++)
            points.add(((400 * convert.get(i)) - 400) * -1);

        return (points);
    }

    public void paint(Graphics g2) {

        g2.setColor(Color.black);
        g2.fillRect(80,50,750,450);

        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (newchart == true)
        {
            g.setColor(Color.black);
            g.fillRect(0, 0, width, height);

            //Putting the ticker name and period
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString("Watchlist", 960, 80);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            g.drawString("Ticker: " + tick, 50, 25);
            g.drawString("Days : " + numberofdays, 730, 25);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 12));

            //y-axis
            g.fillRect(835, 50, 3, height - 100);

            //x-axis
            g.fillRect(20, height - 50, width - 117, 3);

            putprice(g);
        }

        if (icon != null)
            icon.paintIcon(this, g, 30, 50);

        g.setColor(Color.magenta);
        if (xline > 15 && xline < 825 && yline < 500 && yline > 50)
        {
            g.draw(new Line2D.Double(30.0, yline, 825, yline));
            g.draw(new Line2D.Double(xline, 50, xline, 498));
        }
        list.repaint();
    }

    public void actionPerformed(ActionEvent e) {;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        ArrayList <Double> data = new ArrayList <Double>();
        int x;
        int y;

        x = e.getX();
        y = e.getY();

        if (x > 70 && y < 500 && x < 970 && y > 50)
        {
            String name = JOptionPane.showInputDialog(this, "Ticker");
            qoutes stuff = new qoutes(name, intervals, numberofdays);
            data = stuff.smoothed();
            if (!data.isEmpty())
            {
                ypoints.clear();
                tick = name;
                icon = init(data);
                newchart = true;
                repaint();
            }
        }
    }

    public void mouseMoved(MouseEvent e) {
       // System.out.println("X : " + e.getX() * xvalue);
        //System.out.println("Y : " + (((((double)e.getY() - 50) / (500 - 50)) * max) - max) * -1);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getX() > 30 && e.getX() < 825 && e.getY() < 500 && e.getY() > 50) {
            yline = (double) e.getY();
            xline = (double) e.getX();
            repaint();
        }
        newchart = false;

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
        xline = -10;
        yline = -10;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {}
}

