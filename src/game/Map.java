package game;

import java.util.Random;

// Map Class, just used for generating size of screen
public class Map 
{
	public 	int xSize;
	public 	int ySize;
	private Tile[][] world;
	
	public Map(int x, int y)
	{
		xSize = x;
		ySize = y;
		world = new Tile[xSize][ySize];
	}

	// Create this map object with dimension
	public void genMap()
	{
		Random r = new Random();
		int rand;
		for (int i = 0; i < ySize; i++)
		{
			for (int j = 0; j < xSize; j++)
			{
				world[i][j] = new Tile();
				world[i][j].setType(Math.abs(r.nextInt()) % 2);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		Tile requested = world[x][y];
		return requested;
	}
} // end of map class
