package threeSpace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import threeSpace.shape.RectanglePrism;
import threeSpace.shape.Vertice;

@SuppressWarnings("serial")
public class ThreeSpacePanel extends JPanel implements ActionListener, MouseListener, MouseMotionListener {

	double angle;
	Timer t;
	ArrayList<ThreeShape>s;

	public ThreeSpacePanel(){

		super(true);

		setSize(600,600);
		setPreferredSize(getSize());
		setBackground(Color.DARK_GRAY);
		setForeground(Color.CYAN);

		Vertice[] p = new Vertice[8];
		p[0] = new Vertice(-50,-50,-50);
		p[1] = new Vertice(-50,50,-50);
		p[2] = new Vertice(-50,50,50);
		p[3] = new Vertice(-50,-50,50);

		p[4] = new Vertice(50,-50,-50);
		p[5] = new Vertice(50,50,-50);
		p[6] = new Vertice(50,50,50);
		p[7] = new Vertice(50,-50,50);

		RectanglePrism r = new RectanglePrism(p);
		
		s = new ArrayList<ThreeShape>();
		s.add(r);

		t = new Timer(20, this);
		t.stop();
		
		mousePressed = false;

		angle = 0.05;
	}
	
	int mode = 1;

	@Override
	public void actionPerformed(ActionEvent e) {
		for(ThreeShape ts : s){
			switch(mode){
			case 1: ts.rotateX(angle); break;
			case 2: ts.rotateY(angle); break;
			case 3: ts.rotateZ(angle); break;
			}
		}
		repaint();
	}
	
	Vertice l = new Vertice(200,0,0);
	Vertice v = new Vertice(-1,0,0);
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		super.paint(g);

		g2.setColor(getForeground());

//		g2.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
//		g2.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);

		g2.translate(getWidth()/2, getHeight()/2);

//		g2.fill(new Ellipse2D.Float(-5, -5, 10, 10)); //centerPoint
		
		//Orthagonal
//		for(ThreeShape ts:s){
//			ts.paint(g2);
//		}
		
		//Perspective
		
		//focus point
		double t = 200;
		double[] temp = new double [3];
		temp[0] = l.x + t*v.x;
		temp[1] = l.y + t*v.y;
		temp[2] = l.z + t*v.z;
		Vertice fP = new Vertice (temp); //Focus point (center of focus plane)
		
		//Plane equation
		double D = v.x*fP.x + v.y*fP.y + v.z*fP.z;
//		System.out.println(D);
		double A = v.x, B = v.y, C = v.z;
		
		//Final point
		RectanglePrism r = (RectanglePrism)s.get(0);
		Vertice[] vs = r.getVertices();
		Point2D.Double[] twoD = new Point2D.Double[vs.length];
		for(int i = 0; i < vs.length; i++){
			Vertice a = vs[i];//point
			Vertice b = new Vertice(a.x - l.x, a.y - l.y, a.z - l.z);//vector
			
//			System.out.println(a);
//			System.out.println(b);
			
			double t2 = (D - (A*a.x + B*a.y + C*a.z))/(A*b.x + B*b.y + C*b.z);
			twoD[i] = new Point2D.Double(a.y + b.y*t2, a.z + b.z*t2);
//			System.out.println(t2);
		}
		r.perspectivePoint(g2, twoD);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(t.isRunning())
			t.stop();
		else{
			t.start();
			mode++;
			if(mode == 4) mode = 1;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	int x;
	int y;
	boolean mousePressed;
	
	@Override
	public void mousePressed(MouseEvent e) {
//		System.out.println("Pressed");
		mousePressed = true;
		x = e.getX();
		y = e.getY();
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
//		System.out.println("UNPressed");
		mousePressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if(mousePressed){
//			System.out.println("Drag");
			int cx = e.getX();
			int cy = e.getY();
			for(ThreeShape ts:s){
				ts.rotateZ(-(double)(x-cx)*0.01);
				ts.rotateY(-(double)(y-cy)*0.01);
			}
			x = cx;
			y = cy;
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {

	}
}
