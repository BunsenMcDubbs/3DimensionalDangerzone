package twoSpace;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Fractal extends JPanel implements ActionListener, MouseListener, ComponentListener{

	ArrayList<Line> l;
	Line line;
	int time;
	Timer t;
//	boolean clear; //for use with component listener for resizing issues when optimizing line drawing
	//performance (overlapping/clearing)
	
	public Fractal(){
		setSize(1200,800);
		setPreferredSize(getSize());
		setBackground(Color.darkGray);
		setForeground(Color.CYAN);
		
		time = 0;
		t = new Timer(750, this);
		init();
//		clear = false;
	}

	
	public void init(){
		l = new ArrayList<Line>();
		int sides = 3;
		float interval = (float)Math.PI*2/sides;
		float shift = 0.001f;
		float r = 600f;
		for(int i = 0; i < sides; i++){
			l.add(new Line(r, (float)interval*i + shift, r, (float)interval*(i+1) + shift));
			l.get(i).center = new Point2D.Float(0,0);
		}
	}
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		
//		if(time == 0)
			super.paint(g2);
			
		g2.setColor(Color.cyan);
		g2.translate(600, 400);
		for (Line line : l)
			line.draw(g2);
	}
	
	public static Point2D.Float cartToPol(Point2D.Float p){
		return cartToPol(p, new Point2D.Float(0,0));
	}
	
	public static Point2D.Float cartToPol(Point2D.Float p, Point2D.Float center){
		float x = p.x - center.x;
		float y = p.y - center.y;
		boolean q3 = (y < 0 && x < 0);
		boolean q2 = (y > 0 && x < 0);
		
		float r = (float) Math.sqrt(x*x + y*y);
		float theta = (float) Math.atan(y/x);
		
		if(q3)
			theta += Math.PI;
		else if (q2)
			theta -= Math.PI;
		
		return new Point2D.Float(r,theta);
	}
	
	public static Point2D.Float polToCart(Point2D.Float p){
		return polToCart(p, new Point2D.Float(0,0));
	}
	
	public static Point2D.Float polToCart(Point2D.Float p, Point2D.Float center){
		float r = p.x;
		float theta = p.y;
		
		while (theta < 0)
			theta += (2 * Math.PI);
		
		float x = r * (float) Math.cos(theta) + center.x;
		float y = r * (float) Math.sin(theta) + center.y;
		
		return new Point2D.Float(x,y);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(time == 7) t.stop();
		System.out.println();
		time++;
		for(Line line : l)
			line.split();
		repaint();
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.print("Generation: " + time + "\n\tStart");
//		for(int i = 0; i < 8; i++)
			for(Line line : l)
				line.split();
		repaint();
		
		System.out.println("\tEnd");
		
		time++;
//		t.start();
		
		
	}

	
	public static void main(String [] args){
		GalaxyFrame f = new GalaxyFrame();
		f.initTF();
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
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void componentResized(ComponentEvent e) {
//		System.out.println("Resized");
//		clear = true;
//		repaint();
	}


	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}
