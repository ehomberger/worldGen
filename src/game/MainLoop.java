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
import javax.swing.JTextField;

import graphics.Surface;

// 
public class MainLoop extends JFrame
{
	private JButton redrawButton;
	
	private JTextField mapWidthTField;
	private JTextField mapHeightTField;
	
	private JLabel  mapHeightLabel;
	private JLabel  mapWidthLabel;

	private JPanel 	mapPanel;
	private JPanel  controlPanel;


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

	// Set up UI
	private void initUI() {
		// Set up window properites
		setTitle("WorldGen");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create a surface from that map
		surface = new Surface(MAP_HEIGHT, MAP_WIDTH);
		surface.generateNewPerlinMap(MAP_HEIGHT, MAP_WIDTH);

		// Create the JComponents we need
		controlPanel = new JPanel();
		redrawButton = new JButton("New Map");
		mapHeightLabel = new JLabel("Height Resolution");
		mapWidthLabel  = new JLabel("Width Resolution");
		mapHeightTField = new JTextField();
		mapWidthTField  = new JTextField();

		redrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// event to do here
				surface.generateNewPerlinMap(MAP_HEIGHT, MAP_WIDTH);
				surface.revalidate();
				surface.repaint();
			}
		});

		// Position all the JComponents
		controlPanel.setLayout(null);
		controlPanel.add(redrawButton);
		controlPanel.add(mapHeightLabel);
		controlPanel.add(mapWidthLabel);
		controlPanel.add(mapHeightTField);
		controlPanel.add(mapWidthTField);

		redrawButton.setSize(90, 35);
		redrawButton.setLocation(5, 40);

		// Height Box components		
		mapHeightLabel.setSize(90, 35);
		mapHeightLabel.setLocation(5, 100);
		mapHeightTField.setSize(90, 25);
		mapHeightTField.setLocation(5, 125);

		// Width Box components
		mapWidthLabel.setSize(90, 35);
		mapWidthLabel.setLocation(5, 150);
		mapWidthTField.setSize(90, 25);
		mapWidthTField.setLocation(5, 175);

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