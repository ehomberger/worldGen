package game;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import graphics.Surface;

// 
public class MainLoop extends JFrame 
{
	private Map map;
	private final int MAP_HEIGHT = 10;
	private final int MAP_WIDTH  = 10;
	
	// 
	public MainLoop() 
	{
        initUI();
    }
	
	// Set up UI
	private void initUI() {
		// create a new map object
		// mapwidth, mapheight are number of squares per x and y 
		map = new Map(MAP_WIDTH, MAP_HEIGHT);

		// generate noise in that map
		// this completely does nothing important right now
		map.genMap();
		
		// create a surface from that map
		final Surface surface = new Surface(map);
		
		// add that surface to the screen
		add(surface);
		
		setTitle("NoiseMap");	 // window name
		setSize(500, 500); // window size
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// just the main loop
    public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
	        @Override 
			public void run() {
	            MainLoop ex = new MainLoop();
	            ex.setVisible(true);
	        }
	    }
		);
	} // end of main

}
