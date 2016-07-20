package game;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import util.Point;

public class NoiseGen {
	private int xSize;
	private int ySize;
	private Point [][] noiseMap;
	private final int DIFF = 5;			//out of 100
	private Queue<Point> flood;
	public NoiseGen(int x, int y){
		xSize = x;
		ySize = y;
		noiseMap = new Point[xSize][ySize];
		flood = new LinkedList();
	}
	
	public void genMap(){
		Random r = new Random();
		int x = Math.abs(r.nextInt() % xSize);
		int y = Math.abs(r.nextInt() % ySize);
		
		Point next = new Point(x, y);
		noiseMap[x][y] = next;
		
		double offset;
		double scaledOffset;
		
		flood.add(next);
		
		double sum;
		int total;
		
		while (!flood.isEmpty()){
			
			sum = .5;
			total = 1;
			next = flood.remove();
			System.out.println(next.x + ", " + next.y);
			
			if (next.x > 0){
				if (noiseMap[next.x - 1][next.y] == null){ 
					noiseMap[next.x - 1][next.y] = new Point(next.x - 1, next.y);
					System.out.println("added " + (next.x - 1) + ", " + next.y);
					flood.add(noiseMap[next.x - 1][next.y]);
				}
				else if (noiseMap[next.x - 1][next.y].value != 0){
					total++;
					sum += noiseMap[next.x - 1][next.y].value;
				}
				
			}
			if (next.x < xSize - 1){
				if (noiseMap[next.x + 1][next.y] == null){ 
					noiseMap[next.x + 1][next.y] = new Point(next.x + 1, next.y);
					System.out.println("added " + (next.x + 1) + ", " + next.y);
					flood.add(noiseMap[next.x + 1][next.y]);
				}
				else if (noiseMap[next.x + 1][next.y].value != 0){
					total++;
					sum += noiseMap[next.x + 1][next.y].value;
				}
			}
			if (next.y > 0){
				if (noiseMap[next.x][next.y - 1] == null){ 
					noiseMap[next.x][next.y - 1] = new Point(next.x, next.y - 1);
					System.out.println("added " + next.x + ", " + (next.y - 1));
					flood.add(noiseMap[next.x][next.y - 1]);
				}
				else if (noiseMap[next.x][next.y - 1].value != 0){
					total++;
					sum += noiseMap[next.x][next.y - 1].value;
				}
			}
			if (next.y < ySize - 1){
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
		
	}
	
	public double getValue(int x, int y){
		return noiseMap[x][y].value;
	}
}
