import java.awt.*;

public class Tile 
{
	Color color;
	// Location
	int X;
	int Y;
	
	public Tile(Color color)
	{
		this.color = color;
	}
	
	// Location get-set methods
	public Color getColor() { return color; }
	public void setColor(Color c) { this.color = c; }
	public int getX() { return X; }
	public int getY() { return Y; }
	public void setX(int x) { this.X = x; }
	public void setY(int y) { this.Y = y; }

}
