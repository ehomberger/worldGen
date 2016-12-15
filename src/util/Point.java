package util;

// Land if point > .505
// Water if point < .495
// Sand everywhere else
public class Point {
	public int x;
	public int y;
	public double value;
	
	public Point(int x, int y){
		this.x = x;
		this.y = y;
		value = 0;
	}

	public double getValue(){
		return value;
	}
}
