package game;

import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import util.Noise;

// Map Class, just used for generating size of screen
public class Map 
{
	public 	int xResolution;
	public 	int yResolution;
	private Tile[][] world;
	
	// create map from map file
	public Map(String fileName){

		Scanner in = null;
		try {
			in = new Scanner(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// FIX this first
		xResolution = in.nextInt() - 5;
		yResolution = in.nextInt();
		world = new Tile[xResolution][yResolution];
		
		// here's the resolution
		System.out.println(xResolution);
		
		// import this map
		for(int i = 0; i < xResolution; i++){
			for(int j = 0; j < yResolution; j++){
				world[i][j] = new Tile(i, j, in.nextDouble());
				System.out.println(world[i][j].value + " at " + i + ", " + j);
			}
		}

		// 
		System.out.println("leaving");
	}
	
	public Map(int x, int y)
	{
		xResolution = x;
		yResolution = y;
		world = new Tile[xResolution][yResolution];
	}
	
	public void save(){
		PrintWriter out = null;
		
		try {
			out = new PrintWriter("saved");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println(xResolution + " " + yResolution);
		
		for(int i = 0; i < xResolution; i++){
			for(int j = 0; j < yResolution; j++){
				out.print(world[i][j] + " ");
			}
			out.print('\n');
		}
			
	}

	// Create map with all zeros
	public void fillWithZeros()
	{
		for(int i = 0; i < xResolution; i++)
			for(int j = 0; j < yResolution; j++)
				world[i][j] = new Tile(i, j, 0);
	}

	// Use Noise class's smoothNoise function
	public void generatePerlinNoise()
	{
		double average = 0;
		double tiles = xResolution * yResolution;
		float[][] whiteNoise  = Noise.generateWhiteNoise(xResolution, yResolution);
		float[][] perlinNoise = Noise.generatePerlinNoise(whiteNoise, 8);

		for(int i = 0; i < xResolution; i++)
		{
			for(int j = 0; j < yResolution; j++)
			{
				float pVal = perlinNoise[i][j];
				
				// world[i][j] = new Tile(i, j, 
				// 	new Color(pVal, 0, 0),
				// 	pVal);

				world[i][j] = new Tile(i, j, pVal);
				
				average += pVal;
				
				// System.out.println("(" + i + ", " + j + "): " + world[i][j].value);
			}
		}
		average = average / tiles;
		System.out.println("Average of " + tiles + " tiles is " + average + ".");
	}	

	// Use Noise class's smoothNoise function
	// Should probably just delete this, not really useful at all
	public void generateSmoothNoise()
	{
		float[][] whiteNoise  = Noise.generateWhiteNoise(xResolution, yResolution);
		float[][] smoothNoise = Noise.generateSmoothNoise(whiteNoise, 0);

		for(int i = 0; i < xResolution; i++)
		{
			for(int j = 0; j < yResolution; j++)
			{
				// if you're reading this, it's too late and this is probably why everything is red
				world[i][j].setValue((double)smoothNoise[i][j]);
			}
		}
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
	public Color getColor(int x, int y){
		return world[x][y].getColor();
	}
} // end of map class
