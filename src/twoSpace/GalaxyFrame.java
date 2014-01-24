package twoSpace;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class GalaxyFrame extends JFrame{

	public static final Color bg = Color.DARK_GRAY;

	public GalaxyFrame(){
		super("Galaxy");
		setTitle("Galaxy");
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setVisible(true);
	}

	public void initAC() {
		add(new AnimatedCircle());

		setSize();
	}

	public void initTF(){
		Fractal f = new Fractal();
		add(f);
		addMouseListener(f);
		addComponentListener(f);

		setSize();
	}

	public void initRL(){
		Ridge r = new Ridge();
		addMouseListener(r);
		add(r);

		setSize();
	}

	private void setSize() {
		setResizable(true);
		pack();
	}

	public static void main(String [] args){
		GalaxyFrame f = new GalaxyFrame();
	}

	public void initMD() {
		MidpointDisplacement m = new MidpointDisplacement();
		add(m);
		
		setSize();
	}
	
	public void initDS() {
		DiamondSquare m = new DiamondSquare();
		add(m);
		
		setSize();
	}

}