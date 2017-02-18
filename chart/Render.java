import javax.swing.*;
import java.awt.*;

public	class Render extends JPanel
{
	public void paintComponent(Graphics g)
	{
		super.paintComponents(g);
		Main.main.render((Graphics)g);
	}
}