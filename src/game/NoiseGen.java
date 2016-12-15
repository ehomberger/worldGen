package game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import util.Point;

public class NoiseGen 
{
	private int xSize;					// map size? screen size?
	private int ySize;					// probably map size
	private Point [][] noiseMap;		// here lie the noise values
	private final int DIFF = 5;			// out of 100
	private Queue<Point> flood;			// a queue becasue queues are cool

	public NoiseGen(int x, int y){
		xSize = x;
		ySize = y;
		noiseMap = new Point[xSize][ySize];
		flood = new LinkedList();
        System.out.println("Created new NoiseGen w/ size = " + xSize + "x" + ySize);
	}
	
	// where we generate the noise
	public void genMap()
	{
		// Pick a random x, y point
		Random r = new Random();
		int x = Math.abs(r.nextInt() % xSize);
		int y = Math.abs(r.nextInt() % ySize);
		
		// Add that x, y point to the array of points, ie the NoiseMap
		Point next = new Point(x, y);
		noiseMap[x][y] = next;
		
		// Some variables for some stuff
		double offset;
		double scaledOffset;
		
		// Add the first point to the queue
		flood.add(next);
		
		// Idk really
		double sum;
		int total;
		
		// Create some random values here for each x, y position
		// It's a queue of points, do this while the queue isn't empty
		// Erics comments: Pick a random point to start, check if its neighbors
		// 		 exist yet. If they don't, create them. This floodfills the map
		while (!flood.isEmpty())
		{	
			sum   = .5;
			total =  1;
			// next is a point being removed from the queue
			next = flood.remove();
			// take a looksy at its actual values
			System.out.println("Current Point: (" + next.x + ", " + next.y + ")");
			
			// Why is there a check here?
			if (next.x > 0) // If we're on the map
			{
				// If we're looking at an uninitialized point in the noisemap array, create a new point
				// at that position, print out that we've added it, and finally add it to the queueueue
				if (noiseMap[next.x - 1][next.y] == null)
				{
					noiseMap[next.x - 1][next.y] = new Point(next.x - 1, next.y);
					System.out.println("added " + (next.x - 1) + ", " + next.y);
					flood.add(noiseMap[next.x - 1][next.y]);
				}
				// If we're on a point that has been initialized increment the total number of points
				// also add it to the total (?)
				else if (noiseMap[next.x - 1][next.y].value != 0)
				{
					total++;
					sum += noiseMap[next.x - 1][next.y].value;
				}
				
			}
			if (next.x < xSize - 1)
			{
				if (noiseMap[next.x + 1][next.y] == null)
				{ 
					noiseMap[next.x + 1][next.y] = new Point(next.x + 1, next.y);
					System.out.println("added " + (next.x + 1) + ", " + next.y);
					flood.add(noiseMap[next.x + 1][next.y]);
				}
				else if (noiseMap[next.x + 1][next.y].value != 0)
				{
					total++;
					sum += noiseMap[next.x + 1][next.y].value;
				}
			}
			if (next.y > 0)
			{
				if (noiseMap[next.x][next.y - 1] == null)
				{ 
					noiseMap[next.x][next.y - 1] = new Point(next.x, next.y - 1);
					System.out.println("added " + next.x + ", " + (next.y - 1));
					flood.add(noiseMap[next.x][next.y - 1]);
				}
				else if (noiseMap[next.x][next.y - 1].value != 0)
				{
					total++;
					sum += noiseMap[next.x][next.y - 1].value;
				}
			}
			if (next.y < ySize - 1)
			{
				if (noiseMap[next.x][next.y + 1] == null){ 
					noiseMap[next.x][next.y + 1] = new Point(next.x, next.y + 1);
					
					flood.add(noiseMap[next.x][next.y + 1]);
				}
				else if (noiseMap[next.x][next.y + 1].value != 0){
					total++;
					sum += noiseMap[next.x][next.y + 1].value;
				}
			}
			
			
			offset = (Math.abs(r.nextInt() % 6)) - 2.5;
			noiseMap[next.x][next.y].value = (sum/total) + (offset/100);
			System.out.println(noiseMap[next.x][next.y].value);
		}

		System.out.println("Finished creating points");
	}
	
	public double getValue(int x, int y)
	{
		if(x < 0 || x >= xSize || y < 0 || y >= ySize)
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!\n");
		return noiseMap[x][y].value;
	}
}
