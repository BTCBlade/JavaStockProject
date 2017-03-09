package graph;

import data.qoutes;
import indicators.listone;
import indicators.listtwo;
import javafx.beans.property.IntegerProperty;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.IntSummaryStatistics;

/**
 * Created by klongrich on 3/7/17.
 */
public class Indicators extends JPanel{

    String name = "STOCH";
    String parameters = "14";
    double width;
    ImageIcon icon;
    listtwo x;

    Indicators(String name)
    {
        x = new listtwo();
        qoutes data = new qoutes(name, 300, 10);
        width = 836;
        icon = init(x.stoch(data.close(), 20));

        setSize(807, 160);
        setVisible(true);
    }

    public void update(String name, String parameters, ArrayList <Double>data)
    {

        String temp[];
        int p1 = 0;
        int p2 = 0;
        int p3 = 0;

        if (parameters != null) {
            temp = parameters.split(",");
            if (temp.length == 1) {
                p1 = Integer.parseInt(temp[0]);
            } else if (temp.length == 2) {
                p1 = Integer.parseInt(temp[0]);
                p2 = Integer.parseInt(temp[1]);
            } else if (temp.length == 3) {
                p1 = Integer.parseInt(temp[0]);
                p2 = Integer.parseInt(temp[1]);
                p3 = Integer.parseInt(temp[2]);
            }
            this.name = name;
            this.parameters = parameters;
            if (name == "STOCH")
                icon = init(x.stoch(data, p1));
            else if (name == "MACD")
                icon = init(x.macd(data, p1, p2));

            repaint();
        }
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

    private ArrayList<Double> minmax(ArrayList<Double> data, double min, double max) {
        ArrayList<Double> x = new ArrayList <Double>();
        for (int i = 0; i < data.size(); i++)
            x.add((data.get(i) - min) / (max - min));
        return (x);
    }

    /*
    private void putprice(Graphics g2) {
        double price;

        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.white);
        maxtemp = max;
        for (int i = 0; i < 160; i += 10) {
            price = max;
            price = Math.round(price * 100.0) / 100.0;
            //g.drawString(Double.toString((price)), 823, i + 8);
            g.fillRect(802, i, 5, 2);
            max -= yvalue;
        }
        max = maxtemp;
    }
    */

    public ArrayList <Double> getconvertedpoints(ArrayList <Double> data)
    {
        ArrayList <Double> convert = new ArrayList <Double>();
        ArrayList <Double> points = new ArrayList <Double>();

        convert = minmax(data, minValue(data), maxValue(data));
        for (int i = 0; i < data.size(); i++)
            points.add(((100 * convert.get(i)) - 100) * -1);
        return (points);
    }

    public ImageIcon init(ArrayList<Double> data)
    {
        BufferedImage image = new BufferedImage(810, 450, BufferedImage.TYPE_3BYTE_BGR);
        ArrayList <Double> ypoints = new ArrayList<Double>();
        double xvalue;
        double yvalue;
        double price;
        double max;
        int x;

        max = maxValue(data);
        ypoints = getconvertedpoints(data);
        xvalue = width / data.size();
        yvalue = (maxValue(data) - minValue(data)) / 20;
        x = ypoints.size() - 1;
        Graphics2D g = (Graphics2D)image.getGraphics();

        //BackGround Lines
        for (int i = 0; i < 160; i += 10)
        {
            Color c =new Color(0.5803922f, 0.6f, 0.6392157f, 0.2f);
            g.setColor(c);
            g.drawLine(0, i, 900, i);
        }

        //Drawing the y-values and dashes
        g.setColor(Color.white);
        for (int i = 0; i < 160; i += 10) {
            price = Math.round(max * 100.0) / 100.0;
            //g.drawString(Double.toString((price)), 823, i + 8);
            g.fillRect(802, i, 5, 2);
            max -= yvalue;
        }

        //Drawing the acutal Chart
        if (xvalue > 1)
            xvalue = 1;
        g.setColor(Color.BLUE);
        for (double i = 800; i > 800 - ypoints.size() + 1 ; i -= xvalue) {
            x--;
            g.draw(new Line2D.Double(i, ypoints.get(x) + 35, i , ypoints.get(x + 1) + 35));
        }

        g.drawString(name + "(" + parameters + ")" , 10, 20);
        ImageIcon icon = new ImageIcon(image);
        return (icon);

    }

    public void paintComponent(Graphics g)
    {
        icon.paintIcon(this, g, 0, 0);
        //putprice(g);
    }


}
