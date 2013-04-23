import java.awt.event.*;
import java.awt.*;

public class Game implements KeyListener
{
	// Constant fields
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 20;
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	public static final int DOWN = 10;
	public static final int UP = -10;
	public static final int LINES_TO_CLEAR = 50;
	
	// Private variables
	private Tile[][] grid;
	private int linesRemaining;
	private Tile[][] currentPiece;
	private Tile[][] nextPiece;
	private int state;
	
	public Game()
	{
		
		grid = new Tile[GRID_WIDTH][GRID_HEIGHT];
		linesRemaining = LINES_TO_CLEAR;
		currentPiece = generatePiece();
		//insert(currentPiece, 0, 0);
	}
	private void move(KeyEvent e)
	{
		//new Piece().incrementX(currentPiece);
		//insert(currentPiece, );
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT)
		{
			if (canMove(currentPiece, RIGHT))
				currentPiece = new Piece().changeX(currentPiece, 1);
		}
		if (key == KeyEvent.VK_LEFT)
		{
			if (canMove(currentPiece, LEFT))
				currentPiece = new Piece().changeX(currentPiece, -1);
		}
		// Debugging only
		if (key == KeyEvent.VK_UP)
		{
			if (canMove(currentPiece, UP))
				currentPiece = new Piece().changeY(currentPiece, -1);
		}
		if (key == KeyEvent.VK_DOWN)
		{
			if (canMove(currentPiece, DOWN))
				currentPiece = new Piece().changeY(currentPiece, 1);
		}
	}
	// Determines if a move is legal
	private boolean canMove(Tile[][] piece, int direction)
	{
		if (direction == RIGHT || direction == LEFT)
		{
			int X = piece[0][0].getX() + direction;
			if (X < 0 || X + piece.length > GRID_WIDTH)
				return false;
		}
		if (direction == UP || direction == DOWN)
		{
			int Y = piece[0][0].getY() + (direction / 10);
			if (Y < 0 || Y + piece[0].length > GRID_HEIGHT)
				return false;
		}
		
		return true;
	}
	private Tile[][] generatePiece()
	{
		Tile[][] piece = new Piece().generateLine();
		
		return piece;
	}
	// Inserts a matrix of tiles into the grid
	public void insert(Tile[][] piece, int X, int Y)
	{
		
		for (int i = X; i < grid.length && i < piece.length; i++)
			for (int j = Y; j < grid[i].length && j < piece[i].length; j++)
			{
				grid[i][j] = piece[i][j];
			}
	}
	
	public Tile[][] getGrid() { return grid; }
	public int getLinesRemaining(){ return linesRemaining; }
	public Tile[][] getCurrentPiece() { return currentPiece; }
	public Tile[][] getNextPiece() { return nextPiece; }

	public void keyPressed(KeyEvent e)
	{
		move(e);		
	}
	
	public void keyReleased(KeyEvent e) { return; }
	public void keyTyped(KeyEvent e) { return; } 
		
}