package threeSpace;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;

public class UniverseFrame extends JFrame{
	
	public static final Color bg = Color.DARK_GRAY;
	
	public UniverseFrame(){
		super("Galaxy");
		setTitle("Galaxy");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBackground(bg);
		init();

		setVisible(true);
	}

	private void init() {
		ThreeSpacePanel ts = new ThreeSpacePanel();
		add(ts);
		setSize();
		this.addMouseListener(ts);
		this.addMouseMotionListener(ts);
//		setBackground(bg);
	}
	
	private void setSize() {
		setResizable(true);
		pack();
	}
	
	public static void main(String [] args){
		UniverseFrame f = new UniverseFrame();
	}

}
