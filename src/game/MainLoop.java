package game;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
	private JButton saveButton;
	
	private JTextField mapWidthTField;
	private JTextField mapHeightTField;
	
	private JLabel  mapHeightLabel;
	private JLabel  mapWidthLabel;

	private JLabel  mapHeightDisplayLabel;
	private JLabel  mapWidthDisplayLabel;

	private JPanel 	mapPanel;
	private JPanel  controlPanel;

	Surface surface;
	private Map map;

	private static int MAP_HEIGHT = 400;
	private static int MAP_WIDTH  = 400;
	
	private final int MENU_HEIGHT = 200;
	private final int MENU_WIDTH  = 400;
	
	private final int WINDOW_HEIGHT = 800;
	private final int WINDOW_WIDTH  = 1000;

	
	// 
	public MainLoop() 
	{
		surface = new Surface(MAP_HEIGHT, MAP_WIDTH);
		initUI();
    }

	// Set up UI
	private void initUI() {
		// normal window requirements
		setTitle("WorldGen Menu");
		setSize(MENU_WIDTH, MENU_HEIGHT);		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel menu = new JPanel(new GridBagLayout());
		
		// New world button
		JButton create = new JButton("Create World");
		create.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove(menu);
				surface.generateNewPerlinMap(MAP_WIDTH, MAP_HEIGHT);
				surface.revalidate();
				surface.repaint();
				deployMainWindow();
			}
		});
		
		// load world button
		JButton load = new JButton("Load World");
		load.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//map = new Map("saved");
				remove(menu);
				surface.loadFromFile("saved");
				surface.revalidate();
				surface.repaint();
				deployMainWindow();
			}
		});
		
		// add the buttons to the panel
		menu.add(create);
		menu.add(load);
		
		// add the panel to the frame	
		add(menu);
    }
	
	
	private void newMap(){
		
		// create a new map object
		// map = new Map(MAP_WIDTH, MAP_HEIGHT);
		// map.generatePerlinNoise();
		
		deployMainWindow();
	}
	
	// This creates the main screen with map and control panel
	private void deployMainWindow(){
		setTitle("WorldGen");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// create a surface from that map
		// surface = new Surface(MAP_HEIGHT, MAP_WIDTH);
		// surface.generateNewPerlinMap(MAP_HEIGHT, MAP_WIDTH);

		// Create the JComponents we need
		controlPanel = new JPanel();
		redrawButton = new JButton("New Map");
		saveButton 	 = new JButton("Save");
		mapHeightLabel = new JLabel("Height Resolution");
		mapWidthLabel  = new JLabel("Width Resolution");
		mapHeightTField = new JTextField("" + MAP_HEIGHT);
		mapWidthTField  = new JTextField("" + MAP_WIDTH);

		// redraw button
		redrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// event to do here
				surface.generateNewPerlinMap(MAP_HEIGHT, MAP_WIDTH);
				surface.revalidate();
				surface.repaint();
			}
		});

		// save button
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// event to do here
				surface.saveToFile();
				surface.saveToImage();
			}
		});

		// Position all the JComponents
		controlPanel.setLayout(null);
		controlPanel.add(redrawButton);
		controlPanel.add(saveButton);
		controlPanel.add(mapHeightLabel);
		controlPanel.add(mapWidthLabel);
		controlPanel.add(mapHeightTField);
		controlPanel.add(mapWidthTField);

		// redraw button in upper left corner
		redrawButton.setSize(90, 35);
		redrawButton.setLocation(5, 40);

		// save button in lowe right corner
		saveButton.setSize(90, 35);
		saveButton.setLocation(5, WINDOW_HEIGHT - 100);

		// Height Box components		
		mapHeightLabel.setSize(90, 35);
		mapHeightLabel.setLocation(5, 100);
		mapHeightTField.setSize(90, 25);
		mapHeightTField.setLocation(5, 125);
		mapHeightTField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// do nothing
			}

			@Override
			public void focusLost(FocusEvent e) {
				MAP_HEIGHT = Integer.parseInt( mapHeightTField.getText() );
				mapHeightTField.setText("" + MAP_HEIGHT);
			}
		});

		// Width Box components
		mapWidthLabel.setSize(90, 35);
		mapWidthLabel.setLocation(5, 150);
		mapWidthTField.setSize(90, 25);
		mapWidthTField.setLocation(5, 175);
		mapWidthTField.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				// do nothing
			}

			@Override
			public void focusLost(FocusEvent e) {
				MAP_WIDTH = Integer.parseInt( mapWidthTField.getText() );
				mapWidthTField.setText("" + MAP_WIDTH);
			}
		});

		// set size of panels
		surface.setBounds(101, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		controlPanel.setBounds(0, 0, WINDOW_HEIGHT, 100);
		
		// add that surface to the screen
		// surface is a JPanel, add it to myself, a JFrame
		add(surface);
		add(controlPanel);

		System.out.println("Map Panel Size: " + surface.getSize());
		System.out.println("Menu Panel Size: " + controlPanel.getSize());
	}
	
	// just the main loop
    public static void main(String[] args) 
	{
		if( args.length > 1 )
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