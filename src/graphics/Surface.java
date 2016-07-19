package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import game.Map;
import game.Tile;

public class Surface extends JPanel{
    private Map map;

    public Surface(Map map) {
    	this.map = map;
    }
    
    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        Color square;
        
        int w = getWidth();
        int h = getHeight();

        int mapWidth = map.xSize;
        int mapHeight = map.ySize;
        
        int subX = w/mapWidth;
        int subY = h/mapHeight;
        
        Tile space;

        for (int i = 0; i < mapHeight; i++) {
        	for (int j = 0; j < mapWidth; j++){
        		space = map.getTile(j, i);
        		switch(space.getType()){
        			case 0:
        				square = Color.blue;
        				break;
        				
        			case 1:
        				square = Color.green;
        				break;
        				
        				
        			default:
        				square = Color.red;
        				break;
        		}
        		
        		g2d.setPaint(square);
        	
        		g2d.fillRect(j * subX, i * subY, subX, subY);
        	}
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}
