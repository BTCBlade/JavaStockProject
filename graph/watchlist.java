package graph;

import data.qoutes;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Created by klongrich on 3/2/17.
 */
public class watchlist extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener {

    int offset = 25;
    ArrayList <String> names = new ArrayList<String>();
    ArrayList <Double> change = new ArrayList<Double>();

    public watchlist()
    {
        JPanel x = new JPanel();

        setSize(200, 300);
        addMouseWheelListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
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

    public void addticker(String tick)
    {
        double move;
        double last;
        ArrayList <Double> data = new ArrayList<Double>();
        qoutes x = new qoutes(tick, 60, 1);
        data = x.close();

        move = ((data.get(0) / data.get(data.size() - 1)) - 1) * -100;
        names.add(tick);
        change.add(move);
    }

    public void paintComponent(Graphics g)
    {
        Color c =new Color(0x1E1E1E);
        g.setColor(c);
        g.fillRect(0,0,200,300);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));

        for (int i = 0; i < names.size(); i++) {
            g.setColor(Color.white);
            g.drawString(names.get(i), 30, offset + i * 50);
            if (change.get(i) > 0)
                g.setColor(Color.green);
            else
                g.setColor(Color.red);
            g.drawString(Double.toString(change.get(i)), 100, offset + i * 50);
            g.fillRect(0, offset + 20 + (i * 50), 200, 3);
        }
        putborder(200, 300, g);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println(offset);

        double morespace;

        morespace = 25 - ((names.size() - 6) * 55);
        System.out.println(morespace);
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

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}