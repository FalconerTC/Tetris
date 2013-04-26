import java.awt.*;

public class Tile 
{
	Color color;
	boolean active;
	// Location
	int X;
	int Y;
	
	public Tile(Color color)
	{
		this.color = color;
		active = true;
	}
	public Tile()
	{
		
	}
	
	// Location get-set methods
	public Color getColor() { return color; }
	public void setColor(Color c) { this.color = c; }
	public int getX() { return X; }
	public int getY() { return Y; }
	public void setX(int x) { this.X = x; }
	public void setY(int y) { this.Y = y; }
	public void setActive(boolean active) { this.active = active; }
	public boolean getActive() { return active; }

}
