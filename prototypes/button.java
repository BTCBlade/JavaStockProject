package testing;

import oracle.jrockit.jfr.JFR;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by klongrich on 2/26/17.
 */
public class button extends JFrame{

    JButton x;
    JButton y;

    button()
    {
        setLayout(null);

        setSize(600, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        x = createButton("10 Min", 50, 450);
        y = createButton("1 Min", 350, 450);

        add(x, BorderLayout.PAGE_START);
        add(y);

        setVisible(true);
    }

    public JButton createButton(String name, int x, int y) {
        JButton button = new JButton(name);
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Button1();
                repaint();
            }
        });

        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        button.setLocation(x, y);
        button.setSize(120, 35);
        button.setBackground(Color.gray);
        button.setForeground(Color.white);

        return (button);
    }

    public void Button1()
    {
        System.out.println("Getting There");
    }


    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0,0,600,600);

    }


    /*
    public void paint(Graphics g)
    {
        g.setColor(Color.blue);
        g.fillRect(50,50,50,50);
    }
    */

    public static void main (String [] args)
    {
        button x = new button();
    }

}
