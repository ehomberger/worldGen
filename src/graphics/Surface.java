package graphics;

// Dealing with the color
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

// Windows business
import javax.swing.JPanel;
import javax.swing.Timer;

import game.Map;
import game.NoiseGen;
import game.Tile;

// we're just drawing the pixels here
public class Surface extends JPanel
{
    private Map map;    // this is used only for x*y size
    private NoiseGen noise; // noise gen does the work

    // Create a new map
    public Surface(Map map) 
    {
    	this.map = map;
    	noise = new NoiseGen(map.xSize, map.ySize);
    	noise.genMap();
    }
    
    // assign the colors to the map
    private void doDrawing(Graphics g) 
    {
        // java stuff
        Graphics2D g2d = (Graphics2D) g;

        Color square;
        
        // Probably something in JPanel
        int w = getWidth();
        int h = getHeight();

        // the size of the map we said we wanted to make
        int mapWidth  = map.xSize;
        int mapHeight = map.ySize;
        
        // Size of the rectangles
        int subX = w/mapWidth;
        int subY = h/mapHeight;
        
        double space;

        for (int i = 0; i < mapHeight; i++) 
        {
        	for (int j = 0; j < mapWidth; j++)
            {
        		space = noise.getValue(j,  i);
        		if (space <= 1 && space > .505)
                {
        			square = Color.green;
        		}
        		else if (space <= .505 && space > .495)
                {
        			square = Color.yellow;
        		}
        		else if (space <= .495 && space > 0)
                {
        			square = Color.blue;
        		}
        		else
                {
        			square = Color.red;
        		}
        		
        		g2d.setPaint(square);
        	
        		g2d.fillRect(j * subX, i * subY, subX, subY);
        	}
        }
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        doDrawing(g);
    }
}
