package game;

import java.awt.EventQueue;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

import graphics.Menu;
import graphics.Surface;

// 
public class MainLoop extends JFrame
{
	private Map map;
	private static int MAP_HEIGHT = 200;
	private static int MAP_WIDTH  = 200;
	private final int MENU_HEIGHT = 200 + 16;
	private final int MENU_WIDTH  = 400 + 39;
	private final int WINDOW_HEIGHT = 800 + 16;
	private final int WINDOW_WIDTH  = 1000 + 39;
	
	// 
	public MainLoop() 
	{
		initUI();
    }
	
	// Set up UI
	private void initUI() {
		setTitle("WorldGen Menu");
		setSize(MENU_WIDTH, MENU_HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel menu = new JPanel(new GridBagLayout());
		
		JButton create = new JButton("Create New World");
		create.addActionListener(new ActionListener()
		{
			

			@Override
			public void actionPerformed(ActionEvent arg0) {
				remove(menu);
				newMap();
				
			}
		});
		
		
		menu.add(create);
		
		add(menu);
    }
	
	private void newMap(){
		
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