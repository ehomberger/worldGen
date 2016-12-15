package game;

// Tile is each square in the map
// the type is the type of terrain
public class Tile {
	private int type;
	
	public void setType(int newType){
		type = newType;
	}
	
	public int getType(){
		return type;
	}
}
