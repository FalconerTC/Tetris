import java.awt.*;
// A framework for pieces
public class Piece 
{

	
	// Change the X of every tile in piece by the offset amount
	public Tile[][]changeX(Tile[][] piece, int offset)
	{
		for (int i = 0; i < piece.length; i++)
			for (int j = 0; j < piece[i].length; j++)
			{
				if (piece[i][j] != null) // && piece[i][j].getActive()
					piece[i][j].setX(piece[i][j].getX() + offset);
			}
		return piece;
	} 
	// Change the Y of every tile in piece by the offset amount
	public Tile[][]changeY(Tile[][] piece, int offset)
	{
		for (int i = 0; piece != null && i < piece.length; i++)
			for (int j = 0; j < piece[i].length; j++)
			{
				if (piece[i][j] != null) //&& piece[i][j].getActive()
					piece[i][j].setY(piece[i][j].getY() + offset);
			}
		return piece;
	} 
	// Rotate a piece
	public Tile[][] rotate(Tile[][] piece)
	{
		
		return piece;
	}
	// Creates a matrix of tiles representing a line
	public Tile[][] generateLine()
	{
		Tile[][] piece = new Tile[1][4];
		for (int i = 0; i < piece.length; i++)
			for (int j = 0; j < piece[i].length; j++)
			{
				piece[i][j] = new Tile(Color.cyan);
				piece[i][j].setX(i);
				piece[i][j].setY(j);
			}
		return piece;
	}
	
	public Tile[][] generateSquare()
	{
		Tile[][] piece = new Tile[2][2];
		for (int i = 0; i < piece.length; i++)
			for (int j = 0; j < piece[i].length; j++)
			{
				piece[i][j] = new Tile(Color.YELLOW);
				piece[i][j].setX(i);
				piece[i][j].setY(j);
			}
		return piece;
	}
	public Tile[][] generateLBlock()
	{
		Tile[][] piece = new Tile[2][3];
		
		for (int i = 0; i < piece.length; i++)
			for (int j = 0; j < piece[i].length; j++)
				if (i == 0 || (i == 1 && j == 2))
				{
					piece[i][j] = new Tile(Color.ORANGE);
					piece[i][j].setX(i);
					piece[i][j].setY(j);
				}
		piece[0][0].setX(0);
		piece[0][0].setY(0);
		//piece[0][0].setActive(false);
		return piece;
	}
}
