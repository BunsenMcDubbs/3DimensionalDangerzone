package twoSpace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Ridge extends JPanel implements ActionListener, MouseListener{
	
	RidgeLine r1, r2, r3, r4; //Four hardcoded lines to seed the mountain shape
	
	BufferedImage canvas; //not yet implemented - ignore all references to "canvas"
	
	public Ridge(){
		setSize(800,400);
		setPreferredSize(getSize());
		setBackground(Color.lightGray);
		setForeground(Color.black);
		
		canvas = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		init();
	}
	
	
	//Hardcoding the seeds
	void init(){
		r1 = new RidgeLine(-400,0,-300,-200,100);
		r2 = new RidgeLine(-300,-200,0,-50,100);
		r3 = new RidgeLine(0,-50,200,-200,100);
		r4 = new RidgeLine(200,-200,400,0,100);
	}
	
	void split(){
		r1.split();
		r2.split();
		r3.split();
		r4.split();
	}
	
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		
		g2.translate(400,300);
		r1.draw(g2);
		r2.draw(g2);
		r3.draw(g2);
		r4.draw(g2);
		
		canvas = getScreen();
	}
	
	private BufferedImage getScreen() {
		return getScreen(0,0,getWidth(),getHeight());
	}

	private BufferedImage getScreen(int x, int y, int width, int height) {
		BufferedImage screen = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		for(int i = x, j = y; i <= width; i++);
		return null;
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		split();
		repaint();
	}
	
	public static void main(String [] args){
		GalaxyFrame f = new GalaxyFrame();
		f.initRL();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
