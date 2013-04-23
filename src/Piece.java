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
				//if (piece[i][j].getX() + offset < 0 || piece[i][j].getX() + (piece.length) + offset > Game.GRID_WIDTH)
					//return piece;
				piece[i][j].setX(piece[i][j].getX() + offset);
			}
		return piece;
	} 
	// Change the Y of every tile in piece by the offset amount
	public Tile[][]changeY(Tile[][] piece, int offset)
	{
		for (int i = 0; i < piece.length; i++)
			for (int j = 0; j < piece[i].length; j++)
			{
				//if (piece[i][0].getY() + offset < 0 || piece[i][0].getY() + (piece[i].length) + offset > Game.GRID_HEIGHT)
					//return piece;
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

}
