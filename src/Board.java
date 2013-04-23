import java.awt.*;
public class Board extends Component
{
	
	Game game = new Game();
	
	public Board()
	{
		
	}
	
	public void paint(Graphics g)
	{
		drawGrid(g);
	}
	
	public void drawGrid(Graphics g)
	{
		// 
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 20; j++)
				g.drawRect(i*32, j*32, 32, 32);
			
	}

}
