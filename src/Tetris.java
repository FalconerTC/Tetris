import java.awt.Toolkit;

import javax.swing.*;

public class Tetris 
{
	// Constant fields
	
	// Offset of 7 to make the grid fit
	public static final int WINDOW_WIDTH = (10 * 32) +200;
	// Offset of 29 to make the grid fit
	public static final int WINDOW_HEIGHT = (20 * 32) + 29;
	public static final int SCREEN_WIDTH = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int SCREEN_HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	// Add states
	
	// Declaration for our graphics class
	public static Board graphics = new Board();
	
	public static void main(String[] args)
	{
		// Create a frame, change some properties
		JFrame frame = new JFrame("Tetris");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setLocation((SCREEN_WIDTH - WINDOW_WIDTH) / 2, (SCREEN_HEIGHT - WINDOW_HEIGHT) / 2);
		frame.setResizable(false);
		frame.setVisible(true);
		// Add an instance of our graphics class to the frame
		frame.add(graphics);
	}
	

}
