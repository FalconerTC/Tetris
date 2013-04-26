import java.awt.*;
// A framework for pieces
import java.util.*;
public class Piece
{
 
       
        // Change the X of every tile in piece by the offset amount
        public Tile[][]changeX(Tile[][] piece, int offset)
        {
                for (int i = 0; i < piece.length; i++)
                        for (int j = 0; j < piece[i].length; j++)
                        {
                                if (piece[i][j] != null)
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
                                if (piece[i][j] != null)
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
                Tile[][] piece = new Tile[5][2];
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
                        {
                                if (i == 0 || (i == 1 && j == 2))
                                {
                                        piece[i][j] = new Tile(Color.ORANGE);
                                        piece[i][j].setX(i);
                                        piece[i][j].setY(j);
                                }
                                else
                                {
                                	piece[i][j] = new Tile(Color.white);
                                	piece[i][j].setX(i);
                                	piece[i][j].setY(j);
                                	piece[i][j].setActive(false);
                                }
                        }
                //piece[0][0].setX(0);
                //piece[0][0].setY(0);
                //piece[0][0].setActive(false);
                return piece;
        }
        public Tile[][] generateZBlock()
        {
                Tile[][] piece = new Tile[3][2];
                for (int i = 0; i < piece.length; i++)
                        for (int j = 0; j < piece[i].length; j++)
                        {
                                if ((i == 0 && j==0) || (i==1) || (i==2 && j==1))
                                {
                                	piece[i][j] = new Tile(Color.GREEN);
                                	piece[i][j].setX(i);
                                	piece[i][j].setY(j);
                                }
                                else
                                {
                                	piece[i][j] = new Tile(Color.white);
                                	piece[i][j].setX(i);
                                	piece[i][j].setY(j);
                                	piece[i][j].setActive(false);
                                }
                               
                        }
                return piece;
        }
        public Tile[][] generateReverseZBlock()
        {
                Tile[][] piece = new Tile[3][2];
                for (int i = 0; i < piece.length; i++)
                        for (int j = 0; j < piece[i].length; j++)
                        {
                                if ((i == 0 && j==1) || (i==1) || (i==2 && j==0))
                                {
                                piece[i][j] = new Tile(Color.RED);
                                piece[i][j].setX(i);
                                piece[i][j].setY(j);
                                }
                                else
                                {
                                	piece[i][j] = new Tile(Color.white);
                                	piece[i][j].setX(i);
                                	piece[i][j].setY(j);
                                	piece[i][j].setActive(false);
                                }
                        }
                return piece;
        }
        public Tile[][] generateReverseLBlock()
        {
                Tile[][] piece = new Tile[2][3];
                for (int i = 0; i < piece.length; i++)
                        for (int j = 0; j < piece[i].length; j++)
                        {
                                if (i == 1 || (i == 0 && j == 2))
                                {
                                        piece[i][j] = new Tile(Color.PINK);
                                        piece[i][j].setX(i);
                                        piece[i][j].setY(j);
                                }
                                else
                                {
                                	piece[i][j] = new Tile(Color.white);
                                	piece[i][j].setX(i);
                                	piece[i][j].setY(j);
                                	piece[i][j].setActive(false);
                                }
 
                        }
                return piece;
        }
        public Tile[][] generateTBlock()
        {
                Tile[][] piece = new Tile[3][2];
                for (int i = 0; i < piece.length; i++)
                        for (int j = 0; j < piece[i].length; j++)
                        {
                                if (j == 0 || (j == 1 && i == 1))
                                {
                                	piece[i][j] = new Tile(Color.MAGENTA);
                                	piece[i][j].setX(i);
                                	piece[i][j].setY(j);
                                }
                                else
                                {
                                	piece[i][j] = new Tile(Color.white);
                                	piece[i][j].setX(i);
                                	piece[i][j].setY(j);
                                	piece[i][j].setActive(false);
                                }
                        }
                return piece;
        }
        public Tile[][] generateRandomBlock()
        {
                Tile[][] piece = new Tile[0][0];
                 Random generator = new Random();
                int randomIndex = 1; //generator.nextInt(7);
               switch(randomIndex)
               {
	                case 0:
	                         piece = generateLine();
               				break;
	                case 1:
	                        piece = generateSquare();
	                        break;
	                case 2:
	                         piece = generateLBlock();
	                         break;
	                case 3:
	                         piece = generateZBlock();
	                         break;
	                case 4:
	                        piece = generateReverseZBlock();
	                        break;
	                case 5:
	                         piece = generateReverseLBlock();
	                         break;
	                case 6:
	                        piece = generateTBlock();
	                        break;
               }
                return piece;
        }
       
}