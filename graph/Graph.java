package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

/**
 * Created by klongrich on 2/23/17.
 */

public class Graph extends JFrame {

    int width;
    int height;
    double xvalue = 0;
    double yvalue = 0;
    Graph()
    {
        width = 1000;
        height = 550;

        setSize(width, height);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private double maxValue(ArrayList <Float> array)
    {
        double max;

        max = array.get(0);
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i) > max) {
                max = array.get(i);
            }
        }
        return (max);
    }

    private double minValue(ArrayList <Float> array)
    {
        double min;

        min = array.get(0);
        for (int i = 0; i < array.size(); i++)
        {
            if (array.get(i) < min)
            {
                min = array.get(i);
            }
        }
        return (min);
    }

    public void plotone(ArrayList<Float> data)
    {
        xvalue = width / data.size();
        yvalue = (maxValue(data) - minValue(data)) / 18;

        System.out.println(xvalue);
        System.out.println(yvalue);
        setVisible(true);
        super.repaint();

    }

    public void paint(Graphics g)
    {
        super.setBackground(Color.black);
        g.setColor(Color.WHITE);
        g.fillRect(50,50, 3,height - 100);
        g.fillRect(50,height - 50, width - 100, 3);

        for (int i = 50; i < height - 50; i += 20)
            g.fillRect(50, i, 5, 3);

        /**
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (double i = 0; i < 3; i += xvalue)
        {
            g2.draw(new Line2D.Double(50, height - 50, 10, 10));
        }
         **/
    }
}
