package testing;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;

/**
 * Created by klongrich on 3/10/17.
 */
public class zoom extends JPanel implements MouseWheelListener {

    int x = 100;
    int y = 100;
    zoom()
    {
        JFrame frame = new JFrame();
        this.setSize(100, 100);
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.add(this);
        frame.addMouseWheelListener(this);
    }

    public Image init()
    {
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();

        g.setColor(Color.red);
        g.drawString("Blah", 10, 10);

        return (image);
    }

    public void paintComponent(Graphics g)
    {

        g.setColor(Color.black);
        g.fillRect(0, 0, 500 , 500);
        g.drawImage(init(), 50, 50, x, y , 0, 0, 100, 100, null);
        g.dispose();
    }



    public static void main(String [] args)
    {
        zoom x = new zoom();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        System.out.println(e.getWheelRotation());

        if (e.getWheelRotation() == 1)
        {
            //zoom in
            x += 50;
            y += 50;
            repaint();
        }
        else
        {
            //zoom out
            x -= 50;
            y -= 50;
            repaint();
        }
    }
}
