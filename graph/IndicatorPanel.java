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
    static ArrayList<ArrayList<Double>> indactors = new ArrayList <ArrayList<Double>>();

    boolean sma = false;

    IndicatorPanel(ArrayList<Double> data)
    {
        this.data = data;
        setSize(200, 200);
        //setResizable(false);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.black);
        setLayout(new GridLayout(2, 2));
        x = new listone();
        add(SMA(init()));
        //setVisible(true);
    }

    public void upadate(ArrayList<Double> data)
    {
        indactors.clear();
        if (sma)
            indactors.add(x.sma(data, 30));
    }

    public JButton init()
    {
        JButton button = new JButton("SMA");
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        button.setLocation(100, 100);
        button.setSize(100, 25);
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        return (button);
    }

    public JButton SMA(JButton button)
    {
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                sma = true;
            }
        });
        return (button);
    }

    public ArrayList <ArrayList<Double>> getinda()
    {
        return(indactors);
    }
}
