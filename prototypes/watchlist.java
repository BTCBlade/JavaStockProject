package testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by klongrich on 3/2/17.
 */
public class watchlist extends JPanel implements MouseWheelListener, MouseMotionListener, MouseListener {

    int offset = 0;
    watchlist()
    {
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

    }


    public void paintComponent(Graphics g)
    {
        Color c =new Color(0x1E1E1E);
        g.setColor(c);
        g.fillRect(0,0,200,300);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 15));

        g.setColor(Color.white);
        g.drawString("SPY", 30, offset);
        g.setColor(Color.green);
        g.drawString("33 %", 100, offset);
        g.fillRect(0, offset + 20, 200, 3);

        g.setColor(Color.white);
        g.drawString("DOW", 30, offset + 50);
        g.setColor(Color.red);
        g.drawString("12 %", 100, offset + 50);
        g.fillRect(0, offset + 70, 200, 3);


        g.setColor(Color.white);
        g.drawString("KO", 30, offset + 100);
        g.setColor(Color.green);
        g.drawString("4 %", 100, offset + 100);
        g.fillRect(0, offset + 120, 200, 3);

        putborder(200, 300, g);
        //g.setColor(Color.black);
        // g.fillRect(0,offset,50,50);
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println(e.getWheelRotation());

        if (e.getWheelRotation() == 1)
            offset -= 2;
        else
            offset += 2;
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

