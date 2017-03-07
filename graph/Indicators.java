package graph;

import data.qoutes;
import indicators.listone;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by klongrich on 3/7/17.
 */
public class Indicators extends JPanel{

    String name;
    double min;
    double max;
    double maxtemp;
    double yvalue;
    double xvalue;
    double width;
    ArrayList <Double> ypoints = new ArrayList <Double>();
    ImageIcon icon;
    qoutes data;
    listone x;


    Indicators(String name)
    {
        this.name = name;

        x = new listone();
        data = new qoutes(name, 300, 10);
        width = 836;
        icon = init(x.stoch(data.close(), 20));

        setSize(836, 160);
        setVisible(true);
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

    private ArrayList<Double> minmax(ArrayList<Double> data) {
        ArrayList<Double> x = new ArrayList <Double>();
        for (int i = 0; i < data.size(); i++)
            x.add((data.get(i) - min) / (max - min));
        return (x);
    }

    private void putprice(Graphics g2) {
        double price;

        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.white);
        maxtemp = max;
        System.out.println(max);
        for (int i = 0; i < 160; i += 10) {
            price = max;
            price = Math.round(price * 100.0) / 100.0;
            System.out.println(price);
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

        //max = maxValue(data);
        //min = minValue(data);
        convert = minmax(data);

        for (int i = 0; i < data.size(); i++)
            points.add(((160 * convert.get(i)) - 160) * -1);

        return (points);
    }

    public ImageIcon init(ArrayList<Double> data)
    {
        min = minValue(data);
        max = maxValue(data) + 2;
        ypoints = points(data);
        xvalue = width / data.size();
        yvalue = (maxValue(data) - minValue(data)) / 20;
        int x;

        x = ypoints.size() - 1;

        BufferedImage image = new BufferedImage(800, 450, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g = (Graphics2D)image.getGraphics();

        //BackGround Lines
        for (int i = 0; i < 160; i += 10)
        {
            Color c =new Color(0.5803922f, 0.6f, 0.6392157f, 0.2f);
            g.setColor(c);
            g.drawLine(0, i, 900, i);
        }

        //Drawing the acutal Chart
        if (xvalue > 1)
            xvalue = 1;
        g.setColor(Color.BLUE);
        for (double i = 800; i > 800 - ypoints.size() + 1 ; i -= xvalue) {
            x--;
            g.draw(new Line2D.Double(i, ypoints.get(x), i , ypoints.get(x + 1)));
        }

        ImageIcon icon = new ImageIcon(image);
        return (icon);

    }

    public void paintComponent(Graphics g)
    {
        icon.paintIcon(this, g, 0, 0);
        putprice(g);
    }


}
