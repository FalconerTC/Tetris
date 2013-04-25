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
	public static final int PLACEMENT = 1;
	public static final int TRANSITION = 2;
	
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
		nextPiece = generatePiece();
		//insert(nextPiece, 7, 7);
		state = PLACEMENT;
		//insert(currentPiece, 0, 0);
	}
	// Handles button events
	private void move(KeyEvent e)
	{
		// Can't move during a transition
		if (state == PLACEMENT)
		{
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
			if (key == KeyEvent.VK_SPACE)
			{
				if (state == PLACEMENT)
					state = TRANSITION;
				else
					state = PLACEMENT;
			}
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
			if (collision(new Piece().changeX(piece, (direction / 10))));
		}
		
		return true;
	}
	// Detects if a piece exists within another piece
	private boolean collision(Tile[][] piece)
	{
		for (int i = piece[0][0].getX(); i < grid.length && i < (piece.length + piece[0][0].getX()); i++)
		{
			for (int j = piece[0][0].getY(); j < grid[i].length && j < (piece[i].length + piece[0][0].getY()); j++)
			{
				if (grid[i][j] != null && grid[i][j].getActive())
				{
					return true;
				}
			}
		}
			
			
		return false;
	}
	// Randomly generate a piece
	private Tile[][] generatePiece()
	{
		Tile[][] piece = new Piece().generateLBlock();
		
		return piece;
	}
	// Inserts a matrix of tiles into the grid
	public void insert(Tile[][] piece, int X, int Y)
	{
		for (int i = 0; i < grid.length && i < piece.length; i++)
			for (int j = 0; j < grid[i].length && j < piece[i].length; j++)
			{		
				if (piece[i][j] != null)
					grid[i + X][j + X] = piece[i][j];
				
			}
	}
	public void insert(Tile[][] piece)
	{
		insert(piece, currentPiece[0][0].getX(), currentPiece[0][0].getY());
	}
	
	private void transition()
	{
		state = TRANSITION;
		System.out.println("HIT");
		//
		// Insert the piece into the grid
		insert(currentPiece);
		
		
		currentPiece = nextPiece;
		nextPiece = generatePiece();
		state = PLACEMENT;
	}
	
	// Moves the current piece down one
	public void moveCurrentPieceDown()
	{
		if (canMove(currentPiece, DOWN))
			currentPiece = new Piece().changeY(currentPiece, 1);
		else
			transition();
	}
	
	public Tile[][] getGrid() { return grid; }
	public int getState() { return state; }
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