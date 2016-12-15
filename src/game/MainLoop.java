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
	private final int MAP_HEIGHT = 20;
	private final int MAP_WIDTH  = 20;
	private final int WINDOW_HEIGHT = 520 + 16;
	private final int WINDOW_WIDTH  = 520 + 39;
	
	// 
	public MainLoop() 
	{
        initUI();
    }
	
	// Set up UI
	private void initUI() {
		// create a new map object
		map = new Map(MAP_WIDTH, MAP_HEIGHT);

		// create a surface from that map
		final Surface surface = new Surface(map);
		
		// generate noise in that map
		map.genMap();
		
		// add that surface to the screen
		add(surface);
		
		setTitle("NoiseMap");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
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
