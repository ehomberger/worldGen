package graphics;

// Dealing with the color
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
// Windows business
import javax.swing.JPanel;
import javax.swing.Timer;

import game.Map;
import game.NoiseGen;
import game.Tile;

// we're just drawing the pixels here
// JPanel is the thing inside the jframe that the world is drawn on
public class Surface extends JPanel
{
    private Map map;    // Currently trying to use this as intended
    private BufferedImage image;
    Graphics2D g2d;
    private boolean show = true;

    // Create a new surface from a pre-existing map
    public Surface(Map map) 
    {
    	this.map = map;
    }

    // Create surface with custom resolution, creates new map object same size
    public Surface(int xResolution, int yResolution)
    {
        this.map = new Map(xResolution, yResolution);
    }

    public void generateNewPerlinMap(int xResolution, int yResolution)
    {
        // if(map == null)
        map = new Map(xResolution, yResolution);
        
        map.generatePerlinNoise();
    }

    // Rename this and drawMap()
    private void drawSurface(Graphics g) 
    {
        // java stuff
        g2d = (Graphics2D) g;
        
        // what why
        if (!show){
        	
        }
        else{
        	drawMap();
        }
        
    }
    
    // Assign colors to this Jpanel with Graphics2D
    private void drawMap(){
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
                // Get the tile's color
                tileColor = map.getColor(x, y);

                // Paint with that color
                g2d.setPaint(tileColor);
                
                // Put that rectangle on the panel
                g2d.fill(new Rectangle2D.Double(
                    x * tileDrawWidth, 
                    y * tileDrawHeight, 
                    tileDrawWidth, 
                    tileDrawHeight)
                );
            }
        }
    }
    
    /**
     * This function uses g2d to
     * create a png file of the 
     * current world
     */
	public void save(){
		System.out.println("saving");
		BufferedImage bImg = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
	    g2d = bImg.createGraphics();
	    this.paintAll(g2d);
	    try {
	            if (ImageIO.write(bImg, "png", new File("./latest.png")))
	            {
	                System.out.println("-- saved");
	            }
	    } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	    }
	}

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        drawSurface(g);
    }
}
