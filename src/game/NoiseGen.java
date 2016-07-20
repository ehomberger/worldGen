package game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import util.Point;

public class NoiseGen {
	private int xSize;
	private int ySize;
	private double [][] noiseMap;
	private final int DIFF = 5;			//out of 100
	private Queue<Point> flood;
	public NoiseGen(int x, int y){
		xSize = x;
		ySize = y;
		noiseMap = new double[xSize][ySize];
		flood = new LinkedList();
	}
	
	public void genMap(){
		Random r = new Random();
		int x = Math.abs(r.nextInt() % xSize);
		int y = Math.abs(r.nextInt() % ySize);
		
		Point next = new Point(x, y);
		
		int offset;
		double scaledOffset;
		
		flood.add(next);
		
		double sum;
		int total;
		
		while (!flood.isEmpty()){
			System.out.println(next.x + ", " + next.y);
			sum = .5;
			total = 1;
			next = flood.remove();
			
			if (next.x > 0){
				if (noiseMap[next.x - 1][next.y] != 0){ 
					total++;
					sum += noiseMap[next.x - 1][next.y];
				}
				else{
					flood.add(new Point(next.x - 1, next.y));
				}
			}
			if (next.x < xSize - 1){
				if (noiseMap[next.x + 1][next.y] != 0){
					total++;
					sum += noiseMap[next.x + 1][next.y];
				}
				else{
					flood.add(new Point(next.x + 1, next.y));
				}
			}
			if (next.y > 0){
				if (noiseMap[next.x][next.y - 1] != 0){
					total++;
					sum += noiseMap[next.x][next.y - 1];
				}
				else{
					flood.add(new Point(next.x, next.y - 1));
				}
			}
			if (next.y < ySize - 1){
				if (noiseMap[next.x][next.y + 1] != 0){
					total++;
					sum += noiseMap[next.x][next.y + 1];
				}
				else{
					flood.add(new Point(next.x, next.y + 1));
				}
			}
			
			
			offset = (Math.abs(r.nextInt() % 11)) - 5;
			noiseMap[next.x][next.y] = (sum/total) + (offset/100);
			System.out.println(noiseMap[next.x][next.y]);
		}
		
	}
	
	public double getValue(int x, int y){
		return noiseMap[x][y];
	}
}
