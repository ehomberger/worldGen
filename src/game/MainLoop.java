package game;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import graphics.Surface;

// 
public class MainLoop extends JFrame
{
	private Map map;
	Surface surface;
	private static int MAP_HEIGHT = 50;
	private static int MAP_WIDTH  = 50;
	private final int WINDOW_HEIGHT = 800 + 16;
	private final int WINDOW_WIDTH  = 1000 + 39;
	
	// 
	public MainLoop() 
	{
        initUI();
    }
	
    // private void renderSurface(int x, int y) {
    // 	map = new Map(MAP_WIDTH, MAP_HEIGHT);
    // 	map.generatePerlinNoise();
    // 	surface.drawSurface(Graphics g);
    // 	surface.revalidate();
    // 	surface.repaint();
    // }

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
		surface = new Surface(map);
		JPanel 	controlPanel = new JPanel();
		JButton redrawButton = new JButton("Another");
		
		redrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// event to do here
				map.generatePerlinNoise();
				surface.revalidate();
				surface.repaint();
			}
		});

		controlPanel.setLayout(null);
		controlPanel.add(redrawButton);
		redrawButton.setSize(90, 35);
		redrawButton.setLocation(5, 50);

		surface.setBounds(101, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		controlPanel.setBounds(0, 0, WINDOW_HEIGHT, 100);
		
		// add that surface to the screen
		// surface is a JPanel, add it to myself, a JFrame
		add(surface);
		add(controlPanel);
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