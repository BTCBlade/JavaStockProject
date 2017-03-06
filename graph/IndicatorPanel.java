package graph;

import indicators.listone;
import main.driver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by klongrich on 3/5/17.
 */
public class IndicatorPanel extends JFrame {

    listone x;
    ArrayList <Double> data = new ArrayList <Double> ();

    IndicatorPanel(ArrayList<Double> data)
    {
        this.data = data;
        setSize(200, 200);
        //setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setLayout(new GridLayout(2, 2));
        x = new listone();
        add(SMA());
        add(SMA());
        add(SMA());
        add(SMA());
        setVisible(true);
    }

    public JButton SMA()
    {
        JButton button = new JButton("SMA");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        button.setLocation(100, 100);
        button.setSize(100, 25);
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        return (button);
    }


}
