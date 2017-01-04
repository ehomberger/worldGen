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
import java.io.File;

import util.Noise;

// Map Class, just used for generating size of screen
public class Map 
{
	public 	int xResolution;
	public 	int yResolution;
	private Tile[][] world;
	
	// create map from map file
	public Map(String fileName){
		loadFromFile(fileName);
		System.out.println("Created map from " + fileName);
	}
	
	public Map(int x, int y)
	{
		xResolution = x;
		yResolution = y;
		world = new Tile[xResolution][yResolution];
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
	

	public Tile getTile(int x, int y){
		return world[x][y];
	}

	public double getValue(int x, int y){
		return world[x][y].value;
	}

	public Color getColor(int x, int y){
		return world[x][y].getColor();
	}

	// Call this to save to 'saved' file
	public void saveToFile(){
			saveToFile("saved");
	}

	// Save this map to file with name
	public void saveToFile(String fileName){
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
				out.print(world[i][j].value + " ");
			}
			out.print('\n');
		}

		out.close();

		System.out.println("Saved");
	}

	// Import map from file
	public void loadFromFile(String fileName) {
		Scanner in;

		try {
			in = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return;
		}



		// FIX this first
		xResolution = in.nextInt();
		yResolution = in.nextInt();
		world = new Tile[xResolution][yResolution];
		
		// here's the resolution
		System.out.println(xResolution + " " + yResolution);
		
		// import this map
		for(int i = 0; i < xResolution; i++){
			for(int j = 0; j < yResolution; j++){
				world[i][j] = new Tile(i, j, in.nextDouble());
				// System.out.println(world[i][j].value + " at " + i + ", " + j);
			}
		}

		System.out.println("Loaded");
	}
} // end of map class
