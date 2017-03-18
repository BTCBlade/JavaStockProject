package graph;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by klongrich on 3/16/17.
 */
public class watchlistbox extends JPanel {

    watchlist list;
    int width = 200;
    int height = 430;
    String title = "Watchlist";

    watchlistbox()
    {
        setLayout(null);
        list = new watchlist("Watchlist-03-17-17");
        add(list);
        list.setLocation(0, 50);
        setSize(width, height);
        setVisible(true);
        add(AddButton(init(), "Add"));
        add(watchlistButton(init(), "+"));
        add(EditButton(init(), "Edit"));
        add(ClearButton(init(), "Clear"));
        add(SelectButton(init(), "Select"));
    }

    public JButton init()
    {
        JButton button = new JButton();
        button.setFont(new Font("Times New Roman", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.darkGray, 2));
        button.setBackground(Color.gray);
        button.setForeground(Color.white);
        return (button);
    }

    public JButton AddButton(JButton init, String name)
    {
        init.setText(name);
        init.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String name = JOptionPane.showInputDialog("Ticker", "");
                    list.addticker(name);
                    repaint();
                }
            });
        init.setLocation(2, 365);
        init.setSize(80, 25);
        return (init);
    }

    public JButton EditButton(JButton init, String name)
    {
        init.setText(name);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Tickers");
                frame.setLayout(new GridLayout(5, 5));
                frame.setSize(250, 500);
                frame.setVisible(true);

            }
        });
        init.setLocation(95, 365);
        init.setSize(80, 25);
        return (init);
    }

    public JButton watchlistButton(JButton init, String name)
    {
        init.setText(name);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Enter Name", "");
                title = name;
                list.updatelist(name);
                repaint();
            }
        });
        init.setLocation(155, 8);
        init.setSize(25, 25);
        return (init);
    }

    public JButton ClearButton(JButton init, String name)
    {
        init.setText(name);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    list.clear();
            }
        });
        init.setLocation(2, 405);
        init.setSize(80, 25);
        return (init);
    }

    public JButton SelectButton(JButton init, String name)
    {
        init.setText(name);
        init.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        init.setLocation(95, 405);
        init.setSize(80, 25);
        return (init);
    }

    public void paintComponent(Graphics g2)
    {
        Graphics2D g = (Graphics2D) g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(Color.black);
        g.fillRect(0,0,width, 50);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
        g.setColor(Color.white);
        g.drawString(title, 0, 30);
    }

    public void addticker(String name)
    {
        list.addticker(name);
    }

}
