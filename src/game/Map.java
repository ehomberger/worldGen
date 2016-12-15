package game;

import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

// Map Class, just used for generating size of screen
public class Map 
{
	public 	int xResolution;
	public 	int yResolution;
	private Tile[][] world;
	
	public Map(int x, int y)
	{
		xResolution = x;
		yResolution = y;
		world = new Tile[xResolution][yResolution];
	}

	// Draw the map
	public void generateNoise()
	{
		Queue<Tile> q = new LinkedList();
		Random rand = new Random();
		int x = rand.nextInt(xResolution);
		int y = rand.nextInt(yResolution);
		int currentx;
		int currenty;

		// used for finding what value we should give a tile
		double sum;
		int total;
		int value;

		Tile current = new Tile(x, y);
		world[x][y] = current;

		q.add(current);

		while( !q.isEmpty() )
		{
			current = q.remove();
			currentx = current.x;
			currenty = current.y;

			sum = 0.5;
			total = 1;

			// check that we're on the map
			// We really shouldn't ever need to, but yea
			if( currentx > 0 &&
				currenty > 0 &&
				currentx < xResolution - 1 &&
				currenty < yResolution - 1 )
			{
			// if ( currentx >  0 )
			// {
				// check if the tile above us is initialized
				if ( world[currentx-1][currenty] == null )
				{
					world[currentx-1][currenty] = new Tile(currentx-1, currenty);
					q.add( world[currentx-1][currenty] );
				}
				else if ( world[currentx-1][currenty].value != 0 )
				{
					sum += world[currentx-1][currenty].value;
					total ++;
				}
			// }
			// if ( currentx < xResolution-1 )
			// {
				// check if the tile above us is initialized
				if ( world[currentx+1][currenty] == null )
				{
					world[currentx+1][currenty] = new Tile(currentx+1, currenty);
					q.add( world[currentx+1][currenty] );
				}
				else if ( world[currentx+1][currenty].value != 0 )
				{
					sum += world[currentx+1][currenty].value;
					total ++;
				}
			// }
			// if ( currenty > 0 )
			// {
				// check if the tile above us is initialized
				if ( world[currentx][currenty+1] == null )
				{
					world[currentx][currenty+1] = new Tile(currentx, currenty+1);
					q.add( world[currentx][currenty+1] );
				}
				else if ( world[currentx][currenty+1].value != 0 )
				{
					sum += world[currentx][currenty+1].value;
					total ++;
				}
			// }
			// if ( currenty < yResolution - 1 )
			// {
				// check if the tile above us is initialized
				if ( world[currentx][currenty-1] == null )
				{
					world[currentx][currenty-1] = new Tile(currentx, currenty-1);
					q.add( world[currentx][currenty-1] );
				}
				else if ( world[currentx][currenty-1].value != 0 )
				{
					sum += world[currentx][currenty-1].value;
					total ++;
				}
			}

			System.out.println("Added " + total + " tiles to the map");
			world[currentx][currenty].value = sum / total;
		}

		System.out.println("Finished creating tiles");
	}

	// Create this map object with dimension
	// This is old noise generation, doesn't do shit
	public void genMap()
	{
		Random r = new Random();
		
		for (int y = 0; y < yResolution; y++)
		{
			for (int x = 0; x < xResolution; x++)
			{
				int type = Math.abs(r.nextInt()) % 3;
				world[x][y] = new Tile(x, y, type);
			}
		}
	}
	
	public Tile getTile(int x, int y){
		return world[x][y];
	}
	public double getValue(int x, int y){
		return world[x][y].value;
	}
} // end of map class
