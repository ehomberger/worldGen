package graphics;

// Dealing with the color
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
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
    private Map map;    // Currently trying to use this as intended

    // Create a new map
    public Surface(Map map) 
    {
    	this.map = map;
    }
    
    // assign the colors to the map
    private void doDrawing(Graphics g) 
    {
        // java stuff
        Graphics2D g2d = (Graphics2D) g;

        Color square;
        Color tileColor;
        
        // Probably something in JPanel
        int screenWidth  = getWidth();
        int screenHeight = getHeight();

        // the size of the map we said we wanted to make
        double mapWidth  = map.xResolution;
        double mapHeight = map.yResolution;
        
        // Size of the rectangles
        double tileDrawWidth  = screenWidth  / mapWidth;
        double tileDrawHeight = screenHeight / mapHeight;
        
        double space;
        double tileValue;

        for (int y = 0; y < mapHeight; y++)
        {
            for (int x = 0; x < mapWidth; x++)
            {
                tileValue = map.getValue(x, y);

                if( tileValue < 0.5 )
                    tileColor = Color.blue;
                else if( tileValue < 0.6 )
                    tileColor = Color.yellow;
                else if( tileValue < 0.7 )
                    tileColor = Color.green;
                else
                    tileColor = Color.red;
             
                g2d.setPaint(tileColor);
                
                g2d.fill(new Rectangle2D.Double(
                    x * tileDrawWidth, 
                    y * tileDrawHeight, 
                    tileDrawWidth, 
                    tileDrawHeight)
                );
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
