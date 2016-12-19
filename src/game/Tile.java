package game;

import java.awt.Color;
import java.util.HashMap;

// Tile is each square in the map
// the type is the type of terrain
public class Tile {
	public int x;
	public int y;
	public double value;
	private Color color;
	private String type;

	// Static Variables
	private static HashMap<String,Color> TileMap = new HashMap();
	public static final String[] TypeNames = 
	{ 
		"Undefined",
		"Water",
		"Sand",
		"Stone",
		"Grass"
	};
	public static final double[] TypeValues = 
	{
		0.0,
		0.5,
		0.55,
		0.55, // this is just going to disable the stone/gray altogether
		0.7
	};
	public static final Color[] TypeColors = 
	{
		Color.red,
		Color.blue,
		Color.yellow,
		Color.gray,
		Color.green
	};

	// Create an "Undefined" red tile at x, y
	public Tile(int x, int y)
	{
		this.x = x;
		this.y = y;
		type = TypeNames[0];
		value = 0;
		setColor();
	}

	// Set the tile's x, y and type. Sets color cased on type
	public Tile(int x, int y, String type)
	{
		this.x = x;
		this.y = y;
		this.type = type;
		setColor();
		setValue();
	}
	
	// Set the Type/Color with an integer
	// Don't like this too much, feel like it's
	// inconsistent and spaghetti
	public Tile(int x, int y, double value)
	{
		this.x = x;
		this.y = y;
		this.value = value;
		setColor(this.value);
	}

	public Tile(int x, int y, Color color, double value)
	{
		this.x = x;
		this.y = y;
		this.value = value;
		this.color = color;
	}



	public Color getColor()
	{
		return this.color;
	}

	public void setType(String set)
	{
		type = set;
	}

	public void setValue(double set)
	{
		value = set;
	}

	// relies on value
	private void setType()
	{
		if( this.value <= TypeValues[0] )
			this.type = TypeNames[0];
		else if( this.value <= TypeValues[1] )
			this.type = TypeNames[1];
		else if( this.value <= TypeValues[2] )
			this.type = TypeNames[2];
		else
			this.type = TypeNames[3];
	}
	// relies on type
	private void setColor()
	{
		switch(this.type)
		{
			case "Undefined" :
				this.color = TypeColors[0];
				break;
			case "Water" :
				this.color = TypeColors[1];
				break;
			case "Sand" :
				this.color = TypeColors[2];
				break;
			case "Stone" :
				this.color = TypeColors[3];
				break;
			case "Grass" :
				this.color = TypeColors[4];
				break;
			default :
				this.color = Color.pink;
				break;
		}
	}
	// relies on type
	private void setValue()
	{
		switch(this.type)
		{
			case "Undefined" :
				this.value = TypeValues[0];
				break;
			case "Water" :
				this.value = TypeValues[1];
				break;
			case "Sand" :
				this.value = TypeValues[2];
				break;
			case "Grass" :
				this.value = TypeValues[3];
				break;
			default :
				this.value = 0;
		}
	}

	public void setColor(double value)
	{
		double tileValue = value;
		Color tileColor;

        if( tileValue < TypeValues[0] )
            tileColor = TypeColors[0];
        else if( tileValue < TypeValues[1] )
            tileColor = TypeColors[1];
        else if( tileValue < Tile.TypeValues[2] )
            tileColor = Tile.TypeColors[2];
        else if( tileValue < Tile.TypeValues[3] )
            tileColor = Tile.TypeColors[3];
        else
        	tileColor = Tile.TypeColors[4];

        color = tileColor;
	}

	// ======================================================================
	// Static Functions

	// Run some stuff at startup for this class
	public static void TileSetUp()
	{
		CreateHashMap();
	}

	// Set up the TileMap hash map
	private static void CreateHashMap()
	{
		TileMap.put("Undefined", Color.red);
		TileMap.put("Water", Color.blue);
		TileMap.put("Sand", Color.yellow);
		TileMap.put("Grass", Color.green);
	}
}
