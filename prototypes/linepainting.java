package testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Line2D;

/**
 * Created by klongrich on 2/28/17.
 */
public class linepainting extends JFrame implements MouseMotionListener {

    //Render render;
    double xpoint;
    double ypoint;

    public linepainting()
    {
      //  render = new Render();

        setSize(500,500);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add(render);
        addMouseMotionListener(this);

    }

    public void paint(Graphics g2)
    {
        g2.setColor(Color.black);
        g2.fillRect(0,0,500,500);

        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.magenta);
        g.draw(new Line2D.Double(0, ypoint, 500, ypoint));
        g.draw(new Line2D.Double(xpoint, 0, xpoint, 500));

    }

    public static void main(String [] args)
    {
        linepainting x = new linepainting();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        xpoint  = (double)e.getX();
        ypoint = (double)e.getY();
        System.out.println(ypoint);

        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {}
}
