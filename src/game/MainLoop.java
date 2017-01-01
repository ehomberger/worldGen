package game;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import graphics.Surface;

// 
public class MainLoop extends JFrame
{
	private Map map;
	private static int MAP_HEIGHT = 200;
	private static int MAP_WIDTH  = 200;
	private final int WINDOW_HEIGHT = 800 + 16;
	private final int WINDOW_WIDTH  = 1000 + 39;
	
	// 
	public MainLoop() 
	{
        initUI();
    }
	
	// Set up UI
	private void initUI() {
		setTitle("WorldGen");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create a new map object
		map = new Map(MAP_WIDTH, MAP_HEIGHT);
		map.generatePerlinNoise();

		// create a surface from that map
		Surface surface = new Surface(map);
		
		// add that surface to the screen
		add(surface);
	}

	// just the main loop
    public static void main(String[] args) 
	{
		if( args.length != 0 )
		{
			MAP_WIDTH  = Integer.parseInt( args[0] );
			MAP_HEIGHT = Integer.parseInt( args[1] ); 
		}

		EventQueue.invokeLater( new Runnable() 
		{
	        @Override
			public void run()
			{
	            MainLoop ex = new MainLoop();
	            ex.setVisible(true);
	        }
	    }
		);
	} // end of main
}