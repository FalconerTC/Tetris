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
		g.clearRect(0, 0, Tetris.WINDOW_WIDTH, Tetris.WINDOW_HEIGHT);
		g.setColor(Color.BLACK);
		drawGrid(g, game.getGrid());
		drawGrid(g, game.getCurrentPiece());
		drawNextPiece(g, game.getNextPiece());
		
		g.setColor(Color.BLACK);
		drawInterface(g);

	}
	private void drawNextPiece(Graphics g, Tile[][] piece)
	{
		for (int i = 0; i < piece.length; i++)
			for (int j = 0; j < piece[i].length; j++)
			{
				Tile current = piece[i][j];
				g.setColor(current.getColor());
				g.fillRect(i * 32 + 343, j * 32 +32, 32, 32);
			}
		
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
		g.drawString("Next piece:", 383, 16);
		g.setColor(Color.BLACK);
		g.drawRect(343, 32, 128, 128);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++)
				g.drawRect(i*32 + 343, j*32 + 32, 32, 32);
		//g.fillRect(350, 32, 100, 100);
		
		g.drawString("Lines remaining:", 358, 288);
	
		g.drawString("" + game.getLinesRemaining(), 358, 300);

	}

	// Called everytime the timer ticks
	public void actionPerformed(ActionEvent e) 
	{
		time++;
		// Once a second, if we are placing a peice, move it down
		if (game.getState() == game.GAMEOVER_VICTORY)
		{
			JOptionPane.showMessageDialog(this, "A WINNER IS YOU!");
			System.exit(0);
		}
		if (game.getState() == game.GAMEOVER_FAILURE)
		{
			JOptionPane.showMessageDialog(this, "YA DUN GOOFED");
			System.exit(0);
		}
		if (time % 10 == 0 && game.getState() == game.PLACEMENT)
		{
			game.moveCurrentPieceDown();
			
		}

		repaint();
	}

}
