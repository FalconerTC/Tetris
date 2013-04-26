import java.awt.event.*;
import java.util.Random;
import java.awt.Color;

public class Game implements KeyListener
{
	// Constant fields
	public static final int GRID_WIDTH = 10;
	public static final int GRID_HEIGHT = 20;
	public static final int RIGHT = 1;
	public static final int LEFT = -1;
	public static final int DOWN = 10;
	public static final int UP = -10;
	public static final int LINES_TO_CLEAR = 6;
	public static final int PLACEMENT = 1;
	public static final int TRANSITION = 2;
	public static final int GAMEOVER_FAILURE = 9000;
	public static final int GAMEOVER_VICTORY = 9001;
	
	// Private variables
	private Tile[][] grid;
	private Tile[][] currentPiece;
	private Tile[][] nextPiece;
	private int state;
	private int linesToClear;
	Random generator;
	
	public Game()
	{
		
		grid = new Tile[GRID_WIDTH][GRID_HEIGHT];
		currentPiece = generatePiece();
		for (int i = 0; i < 4; i++)
			currentPiece = new Piece().changeX(currentPiece, 1);
		nextPiece = generatePiece();
		for (int i = 0; i < 4; i++)
			nextPiece = new Piece().changeX(nextPiece, 1);
		//insert(nextPiece, 7, 7);
		state = PLACEMENT;
		generator = new Random();
		
		for (int i = 0; i < grid.length; i++)
			for (int j = 0; j < grid[i].length; j++)
			{
				grid[i][j] = new Tile();
				grid[i][j].setActive(false);
			}
		linesToClear = LINES_TO_CLEAR;
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
			
			if (key == KeyEvent.VK_UP)
			{
				while(canMove(currentPiece, DOWN))
					currentPiece = new Piece().changeY(currentPiece, 1);
			}
			if (key == KeyEvent.VK_DOWN)
			{
				if (canMove(currentPiece, DOWN))
					currentPiece = new Piece().changeY(currentPiece, 1);
			}
			if (key == KeyEvent.VK_SPACE)
			{
				// You may pause, but you may not unpause. Deal with it.
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
			{
				return false;
			}
			
			boolean collision = collision(new Piece().changeX(piece, direction));
			new Piece().changeX(piece, -direction);
			if (collision)
			{
				return false;
			}
			
		}
		if (direction == UP || direction == DOWN)
		{
			int Y = piece[0][0].getY() + (direction / 10);
			if (Y < 0 || Y + piece[0].length > GRID_HEIGHT)
				return false;
			
			boolean collision = collision(new Piece().changeY(piece, (direction / 10)));
			new Piece().changeY(piece, -(direction / 10));
			if (collision)
			{
				return false;
			}
			
		}
		
		return true;
	}
	// Detects if a piece exists within another piece
	private boolean collision(Tile[][] piece)
	{
		if (piece == null)
			return false;
		Tile origin = piece[0][0];
		for (int i = 0; (i + origin.getX()) < grid.length && i < piece.length; i++)
		{
			for (int j = 0; (j + origin.getY()) < grid[i].length && j < piece[i].length; j++)
			{
				if ((grid[i + origin.getX()][j + origin.getY()] != null))
				{
					if (grid[i + origin.getX()][j + origin.getY()].getActive() && piece[i][j].getActive())
					{
					return true;
					}
				}
			}
		}
			
			
		return false;
	}
	// Randomly generate a piece
	private Tile[][] generatePiece()
	{
		Tile[][] piece = new Piece().generateRandomBlock();
		
		return piece;
	}
	// Inserts a matrix of tiles into the grid
	public void insert(Tile[][] piece, int X, int Y)
	{
		for (int i = 0; i < grid.length && i < piece.length; i++)
			for (int j = 0; j < grid[i].length && j < piece[i].length; j++)
			{		
				if (piece[i][j] != null && piece[i][j].getActive())
					grid[i + X][j + Y] = piece[i][j];
				
			}
	}
	public void insert(Tile[][] piece)
	{
		insert(piece, currentPiece[0][0].getX(), currentPiece[0][0].getY());
	}
	
	private void transition()
	{
		state = TRANSITION;
		// Insert the piece into the grid		
		insert(currentPiece);
		int rowsCleared = clearRows();
		linesToClear -= rowsCleared;
		currentPiece = nextPiece;
		nextPiece = generatePiece();
		for (int i = 0; i < 4; i++)
			nextPiece = new Piece().changeX(nextPiece, 1);
		state = PLACEMENT;
		
		if (linesToClear <= 0)
		{
			state = GAMEOVER_VICTORY;
			System.out.println(state);
		}
	}
	
	public void printGrid()
	{
		for (int i = 0; i < grid.length; i++)
		{
			for (int j = 0; j < grid[i].length; j++)
				{
				if (grid[i][j] != null)
				System.out.print(grid[i][j].getActive() + " " + i + " " + j);
				}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	// Check if there are any complete lines and delete them if so. Returns the number of rows deleted
	private int clearRows()
	{
		boolean check = true; // Used to determine if the row is full
		boolean deletionMode = false; 
		int rowsCleared = 0;
		// Navigate through the grid, starting with columns
		for (int j= 0; j < GRID_HEIGHT; j++)
		{
			// Navigate through the current row
			for (int i = 0; i < GRID_WIDTH; i++)
			{
				// If we are in deletion mode
				if (deletionMode)
				{
					// Deactivate every tile in the row
					if (grid[i][j] != null)
					{
						grid[i][j].setActive(false);
						grid[i][j].setColor(Color.white);
					}
					// If this is the last tile, go back to normal mode
					if (i + 1 == GRID_WIDTH)
					{
						deletionMode = false;
						check = false;
						reAllign(j);
					}
				}
				// If we are in normal mode, check the row for any empty spot
				else if (!deletionMode && (grid[i][j] == null || !grid[i][j].getActive()))
				{
					// If we found an empty spot, start checking the next row
					check = false;
					break;
				}
			}
			// If we never found an empty spot in the row
			if (check)
			{
				// Re-test the row, but this time use deletion mode
				j--;
				rowsCleared++;
				deletionMode = true;
			}
			check = true;
		}
		return rowsCleared;
		
	}
	// Move all pieces down as much as possible
	private void reAllign(int deletedRow)
	{		
		// Navigate through the columns
		for (int i = 0; i < GRID_WIDTH; i++)
		{
			// Navigate through the rows starting at the bottom
			for (int j = deletedRow - 1; j > -1; j--)
			{
				
				if (grid[i][j] != null)
				{
					
					grid[i][j+1].setColor(grid[i][j].getColor());
					grid[i][j+1].setActive(grid[i][j].getActive());
					grid[i][j+1].setY(j+1);
					
					grid[i][j].setActive(false);
				}
				
			}
		}
		
	}
	// Moves the current piece down one
	public void moveCurrentPieceDown()
	{
		if (canMove(currentPiece, DOWN))
			currentPiece = new Piece().changeY(currentPiece, 1);
		else
		{
			if (currentPiece[0][0].getY() <= 0)
				state = GAMEOVER_FAILURE;
			else
				transition();
		}
	}
	
	public Tile[][] getGrid() { return grid; }
	public int getState() { return state; }
	public int getLinesRemaining(){ return linesToClear; }
	public Tile[][] getCurrentPiece() { return currentPiece; }
	public Tile[][] getNextPiece() { return nextPiece; }

	public void keyPressed(KeyEvent e)
	{
		move(e);		
	}
	
	public void keyReleased(KeyEvent e) { return; }
	public void keyTyped(KeyEvent e) { return; } 
		
}