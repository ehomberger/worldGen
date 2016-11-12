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
	private final int MAPHEIGHT = 101;
	private final int MAPWIDTH  = 101;
	
	// 
	public MainLoop() 
	{
        initUI();
    }
	
	// Set up UI
	private void initUI() {
		// create a new map object 
		map = new Map(MAPWIDTH, MAPHEIGHT);
		// generate noise in that map
		map.genMap();
		// create a surface from that map
		final Surface surface = new Surface(map);
		// add that surface to the screen
		add(surface);
		
		setTitle("map");	 // window name
		setSize(1500, 1000); // window size
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
