package graph.watchlist;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by klongric on 3/22/17.
 */
public class selectmenu extends JPanel {

    File[] files;
    selectmenu()
    {
        setSize(250, 500);
        File folder = new File("./graph/watchlist/lists");
        File[] files = folder.listFiles();
        this.files = files;
    }

    public void paintComponent(Graphics g2)
    {
        Graphics2D g = (Graphics2D)g2;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(Color.gray);
        g.fillRect(0, 0, 250, 500);

        g.setColor(Color.white);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 13));
        for (int i = 0; i < files.length; i++) {
                g.drawString(files[i].getName(), 0, i * 30);
                System.out.println(files[i]);
            }
        }

}

