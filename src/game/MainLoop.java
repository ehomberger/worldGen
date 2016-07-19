package game;

import java.awt.EventQueue;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.Timer;

import graphics.Surface;

public class MainLoop extends JFrame {
	private Map map;
	private final int MAPHEIGHT = 100;
	private final int MAPWIDTH = 100;
	
	public MainLoop() {
        initUI();
    }
	
	

	  private void initUI() {
		    map = new Map(MAPWIDTH, MAPHEIGHT);
		    map.genMap();
		    final Surface surface = new Surface(map);
	        add(surface);
	        
		    setTitle("map");
		    setSize(1400, 1000);
		    setLocationRelativeTo(null);
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    }


    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	            MainLoop ex = new MainLoop();
	            ex.setVisible(true);
	        }
	    });

	}

}
