import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener
{
	public static final int TIMER_VALUE = 100;
	javax.swing.Timer timer;
	double time;
	Game game;
	
	public Board()
	{
		super();
		game = new Game();
		timer = new Timer(TIMER_VALUE, this);
		timer.start();
		time = 0;
		// Add game to control keyboard
		this.addKeyListener(game);
		setFocusable(true);
	}
	
	public void paint(Graphics g)
	{
		// Clear screen
		g.clearRect(0, 0, 320, 640);
		g.setColor(Color.BLACK);
		drawGrid(g, game.getGrid());
		drawGrid(g, game.getCurrentPiece());
		g.setColor(Color.BLACK);
		drawInterface(g);

	}
	// Draws the current grid
	private void drawGrid(Graphics g, Tile[][] grid)
	{
		for (int i = 0; grid != null && i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++)
			{
				if (grid[i][j] != null && grid[i][j].getActive())
				{
					Tile current = grid[i][j];
					g.setColor(current.getColor());
					g.fillRect(current.getX()*32, current.getY()*32, 32, 32);
				}
			}	
	}
	// Draws the default structure for the game
	private void drawInterface(Graphics g)
	{
		// Draw a basic 10 X 20 grid
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 20; j++)
				g.drawRect(i*32, j*32, 32, 32);
		g.drawString("Next piece:", 362, 16);
		g.setColor(Color.BLACK);
		g.drawRect(350, 32, 100, 100);
		g.fillRect(350, 32, 100, 100);
		g.drawString("Lines remaining:", 358, 288);
	
		g.drawString("" + game.getLinesRemaining(), 358, 300);

	}

	// Called everytime the timer ticks
	public void actionPerformed(ActionEvent e) 
	{
		time++;
		// Once a second, if we are placing a peice, move it down
		if (time % 10 == 0 && game.getState() == game.PLACEMENT)
		{
			game.moveCurrentPieceDown();
			
		}
		repaint();
	}

}
