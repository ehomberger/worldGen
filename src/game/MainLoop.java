package game;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
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
	private JButton loadButton;

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

	private static int CONTROL_PANEL_WIDTH = 100;

	// 
	public MainLoop() 
	{
		surface = new Surface(MAP_HEIGHT, MAP_WIDTH);
		initUI();
    }

	// Show the main menu panel
	private void initUI() {
		// normal window requirements
		setTitle("WorldGen Menu");
		setSize(MENU_WIDTH, MENU_HEIGHT);		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create new jpanel for this menu
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
				remove(menu);

				surface.loadFromFile("saved");
				surface.revalidate();
				surface.repaint();
				setResolutions();
				deployMainWindow();
			}
		});
		
		// add the buttons to the panel
		menu.add(create);
		menu.add(load);
		
		// add the panel to the frame	
		add(menu);
    }
		
	// Set up the control panel jpanel and all of its features
	private void initControlPanel(){
		// Create all the Jcomponents
		controlPanel = new JPanel();
		redrawButton = new JButton("New Map");
		saveButton 	 = new JButton("Save");
		loadButton 	 = new JButton("Load");
		mapHeightLabel = new JLabel("Height Resolution");
		mapWidthLabel  = new JLabel("Width Resolution");
		mapHeightTField = new JTextField("" + MAP_HEIGHT);
		mapWidthTField  = new JTextField("" + MAP_WIDTH);

		// Redraw button
		redrawButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// event to do here
				surface.generateNewPerlinMap(MAP_HEIGHT, MAP_WIDTH);
				surface.revalidate();
				surface.repaint();
			}
		});

		// Save button
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				// event to do here
				surface.saveToFile();
				surface.saveToImage();
			}
		});

		// Load button
		loadButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//map = new Map("saved");
				surface.loadFromFile("saved");
				surface.revalidate();
				surface.repaint();
				setResolutions();
				mapHeightTField.setText("" + MAP_HEIGHT);
				mapWidthTField.setText("" + MAP_WIDTH);
			}
		});

		// Position all the JComponents
		controlPanel.setLayout(null);
		controlPanel.add(redrawButton);
		controlPanel.add(saveButton);
		controlPanel.add(loadButton);
		controlPanel.add(mapHeightLabel);
		controlPanel.add(mapWidthLabel);
		controlPanel.add(mapHeightTField);
		controlPanel.add(mapWidthTField);

		// Redraw button in upper left corner
		redrawButton.setSize(90, 35);
		redrawButton.setLocation(5, 40);

		// Save button in lower right corner
		saveButton.setSize(90, 35);
		saveButton.setLocation(5, WINDOW_HEIGHT - 100);

		// Load button in lower right corner
		loadButton.setSize(90, 35);
		loadButton.setLocation(5, WINDOW_HEIGHT - 150);

		// Height Box / Label components		
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

		// Width Box / Label components
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


		// Set the bounds of this panel
		controlPanel.setBounds(0, 0, WINDOW_HEIGHT, CONTROL_PANEL_WIDTH);
	}

	// set up the surface jpanel
	private void initSurfacePanel(){
		// Set the bounds of this panel
		surface.setBounds(CONTROL_PANEL_WIDTH, 0, WINDOW_WIDTH, WINDOW_HEIGHT);

	}

	// This creates the main screen with map and control panel
	private void deployMainWindow(){
		setTitle("WorldGen");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set up the control panel
		initControlPanel();
		initSurfacePanel();

		// add that surface to the screen
		// surface is a JPanel, add it to myself, a JFrame
		add(surface);
		add(controlPanel);

		// Debug stuff
		System.out.println("Map Panel Size: " + surface.getSize());
		System.out.println("Menu Panel Size: " + controlPanel.getSize());

	}

	// Set the x/yResolutions here from the surface
	// NOTE: This is probably pretty janky and could use fixing/rethinking
	public void setResolutions(){
		MAP_WIDTH = surface.getMap().yResolution;
		MAP_HEIGHT = surface.getMap().xResolution;
	}
	
	// Main loop, as expected
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