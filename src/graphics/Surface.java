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
import game.NoiseGen;
import game.Tile;

public class Surface extends JPanel{
    private Map map;
    private NoiseGen noise;

    public Surface(Map map) {
    	this.map = map;
    	noise = new NoiseGen(100, 100);
    	noise.genMap();
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
        
        double space;

        for (int i = 0; i < mapHeight; i++) {
        	for (int j = 0; j < mapWidth; j++){
        		space = noise.getValue(j,  i);
        		if (space <= 1 && space > .5){
        			square = Color.green;
        		}
        		else if (space >= 0 && space <= .5){
        			square = Color.blue;
        		}
        		else{
        			square = Color.red;
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
