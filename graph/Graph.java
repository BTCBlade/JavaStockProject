package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by klongrich on 2/23/17.
 */

import data.qoutes;

public class Graph extends JFrame implements ActionListener {

    private int width;
    private int height;
    private double max = 0;
    private double min = 0;
    private double xvalue = 0;
    private double yvalue = 0;
    private boolean draw = false;
    private ArrayList<Double> ypoints = new ArrayList();
    // Timer time;
    private float current;
    boolean init;

    Graph() {
        width = 1000;
        height = 550;

        // time = new Timer(100,this);
        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //time.start();
    }

    private double maxValue(ArrayList<Float> array) {
        double max;

        max = array.get(0);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }
        return (max);
    }

    private double minValue(ArrayList<Float> array) {
        double min;

        min = array.get(0);
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) < min) {
                min = array.get(i);
            }
        }
        return (min);
    }

    private ArrayList<Double> minmax(ArrayList<Float> data) {
        ArrayList<Double> x = new ArrayList();

        for (int i = 0; i < data.size(); i++)
            x.add((data.get(i) - min) / (max - min));
        return (x);
    }

    public void plotone(ArrayList<Float> data) {
        ArrayList<Double> convert = new ArrayList();

        max = maxValue(data);
        min = minValue(data);
        convert = minmax(data);

        for (int i = 0; i < data.size(); i++)
            ypoints.add((((400 * convert.get(i)) - 400) * -1) + 50);

        xvalue = width / data.size();
        yvalue = (maxValue(data) - minValue(data)) / 18;
        draw = true;
        super.repaint();
        setVisible(true);
    }

    private void putinfo(Graphics g) {
        int x;
        double price;

        x = 0;
        price = 0;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.white);
        for (int i = 50; i < height - 50; i += 20) {
            price = max -= yvalue;
            price = Math.round(price * 100.0) / 100.0;
            g.drawString(Double.toString((price)), 20, i + 8);
            g.fillRect(70, i, 5, 3);
        }

        g.setColor(Color.red);
        for (double i = 0; i < ypoints.size() - 1; i += xvalue) {
            g2.draw(new Line2D.Double(i + 80, ypoints.get(x), i + 80, ypoints.get(x + 1)));
            x++;
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.WHITE);
        g.fillRect(70, 50, 3, height - 100);
        g.fillRect(70, height - 50, width - 100, 3);
        putinfo(g);


        /**
         Graphics2D g2 = (Graphics2D) g;
         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

         double i;
         int x;
         double temp;

         i = ypoints.size() - 1 * xvalue;
         x = ypoints.size() - 1;
         temp = 0;

         qoutes data = new qoutes("SPY", 60, 1);
         temp = (data.getCurrentprice() - min) / (max - min);
         temp = (((400 * temp) - 400) * -1) + 50;
         g2.draw(new Line2D.Double(i + 80, ypoints.get(x), i + 80, temp));
         ypoints.add(temp);

         x++;
         **/
    }

    public void actionPerformed(ActionEvent e) {
        /**
         qoutes data = new qoutes("SPY", 60, 1);
         System.out.println(data.getCurrentprice());
         repaint();
         **/
    }
}

