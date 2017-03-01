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

    public Graph(String tick)
    {
        width = 1000;
        height = 550;
        this.tick = tick;
        time = new Timer(100, this);
        time.start();
        setBackground(Color.black);

        ArrayList <Double> x = new ArrayList <Double>();
        qoutes data = new qoutes(tick,60,2);
        x = data.smoothed();
        init(x);

        setLayout(null);
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        onemin = createButton("1 Min", 70, 60, 2);
        fivemin = createButton("5 Min", 265, 300, 10);
        tenmin = createButton("10 Min", 465, 600, 20);
        fiftenmin = createButton("15 Min", 645, 900, 30);
        thritymin = createButton("30 Min", 845, 1800, 50);
        add(onemin, BorderLayout.PAGE_START);
        add(fivemin);
        add(tenmin);
        add(fiftenmin);
        add(thritymin);

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

        BufferedImage image = new BufferedImage(800, 450, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = (Graphics2D)image.getGraphics();
        int x;

        x = 0;
        if (xvalue > 1)
        {
            xvalue = 1;
        }
        g.setColor(Color.red);
        for (double i = 0; i < ypoints.size() - 1; i += xvalue) {
            g.draw(new Line2D.Double(i, ypoints.get(x), i , ypoints.get(x + 1)));
            x++;
        }
        ImageIcon icon = new ImageIcon(image);
        return (icon);

    }

    private void putprice(Graphics g2) {
        double price;
        double temp;

        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.white);
        maxtemp = max;
        for (int i = 50; i < height - 50; i += 20) {
            price = max;
            price = Math.round(price * 100.0) / 100.0;
            g.drawString(Double.toString((price)), 920, i + 8);
            g.fillRect(896, i, 5, 3);
            max -= yvalue;
        }
        max = maxtemp;
    }


    public void putline(Graphics2D g)
    {
        /*
        int x;

        x = 0;
        double test;

        ArrayList <Double> convert = new ArrayList <Double> ();
        g.setColor(Color.green);
        for (int ii = 0; ii < indactors.size(); ii++)
        {
            convert = points(indactors.get(ii));
            x = 0;
            if (ii == 1)
            {
                test = 20;
                g.setColor(Color.blue);
            }
            else
            {
                test = 0;
            }
            for (double i = test; i < convert.size() - 1 + test; i += (width / ypoints.size())) {
                g.draw(new Line2D.Double(i + 80, convert.get(x), i + 80, convert.get(x + 1)));
                x++;
            }
        }
        //ypoints.clear();
        */
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
            g.setFont(new Font("TimesRoman", Font.PLAIN, 15));
            g.drawString("Ticker: " + tick, 80, 25);
            g.drawString("Days : " + numberofdays, 800, 25);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 12));

            //y-axis
            g.fillRect(900, 50, 3, height - 100);

            //x-axis
            g.fillRect(20, height - 50, width - 120, 3);

            putprice(g);
        }

        if (icon != null)
            icon.paintIcon(this, g, 95, 50);

        g.setColor(Color.magenta);
        if (xline > 75 && xline < 875 && yline < 500 && yline > 50)
        {
            g.draw(new Line2D.Double(75.0, yline, 875, yline));
            g.draw(new Line2D.Double(xline, 50, xline, 498));
        }
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
        if (e.getX() > 30 && e.getX() < 850 && e.getY() < 500 && e.getY() > 50) {
            yline = (double) e.getY();
            xline = (double) e.getX();
            repaint();
        }
        newchart = false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {}
}

