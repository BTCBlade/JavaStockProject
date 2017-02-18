import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Main
{

	static Main main;
	Render render;
	ArrayList <Integer> list = new ArrayList();

	Main()
	{
		render = new Render();
		JFrame frame = new JFrame("Chart!");

		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setSize(800,800);
		frame.add(render);
	}

	public	void render(Graphics g)
	{
		int x = 2;
		int y = 600;
		int orgin = 100;

		g.setColor(Color.black);
		g.fillRect(0, 0, 800, 800);
		
		g.setColor(Color.white);
		g.fillRect(orgin, orgin, x, y);
		g.fillRect(orgin, orgin + y , y, x);

		for (int z = 600; z > 0 ; z -= 25)
		{
			g.fillRect(orgin, z + orgin, 10, 2);
			g.fillRect(orgin + z, y + orgin - 10 , 2, 10);
		}
		putdata(g);
	}

	public void putdata(Graphics g)
	{
		g.setColor(Color.RED);

		g.drawLine(100, 700, 200, 200);
		g.drawLine(200, 200, 300, 400);
		g.drawLine(300, 400, 360, 100);
		g.drawLine(360, 100, 500, 500);

	}

	public static void main(String [] args)
	{
		main = new Main();
	}

}