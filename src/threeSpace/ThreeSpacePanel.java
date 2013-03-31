package threeSpace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import threeSpace.shape.Vertex;

@SuppressWarnings("serial")
public class ThreeSpacePanel extends JPanel implements ActionListener, 
MouseListener, MouseMotionListener, KeyListener {

	double angle;
	Timer t;
	ArrayList<ThreeShape>s;

	public ThreeSpacePanel(){

		super(true);

		setSize(600,600);
		setPreferredSize(getSize());
		setBackground(Color.DARK_GRAY);
		setForeground(Color.CYAN);

		Vertex[] p = new Vertex[8];
		p[0] = new Vertex(-50,-50,-50);
		p[1] = new Vertex(-50,50,-50);
		p[2] = new Vertex(-50,50,50);
		p[3] = new Vertex(-50,-50,50);

		p[4] = new Vertex(50,-50,-50);
		p[5] = new Vertex(50,50,-50);
		p[6] = new Vertex(50,50,50);
		p[7] = new Vertex(50,-50,50);

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
//			case 4: ts.rotateX(angle); ts.rotateY(angle); break;
			}
		}
		repaint();
	}
	
	Vertex l = new Vertex(750,0,0); //camera's position
	Vertex v = new Vertex(-1,0,0);
	
	public void paint(Graphics g){
		Graphics2D g2 = (Graphics2D) g;

		super.paint(g);

		g2.setColor(getForeground());
		g2.translate(getWidth()/2, getHeight()/2);

//		g2.drawLine(getWidth()/2, 0, getWidth()/2, getHeight());
//		g2.drawLine(0, getHeight()/2, getWidth(), getHeight()/2);

		perspectivePaint(g2);

//		s.get(0).paint(g2);
	}
	
	public void perspectivePaint(Graphics2D g2){
		
		g2.setColor(getForeground());
		
		//Perspective
		
		//focus point
		double t = 1000;
		double[] temp = new double [3];
		temp[0] = l.x + t*v.x;
		temp[1] = l.y + t*v.y;
		temp[2] = l.z + t*v.z;
		Vertex fP = new Vertex (temp); //Focus point (center of focus plane)
		
		g2.translate(fP.y, fP.z);

		//Plane equation
		double D = v.x*fP.x + v.y*fP.y + v.z*fP.z;
//		System.out.println(D);
		double A = v.x, B = v.y, C = v.z;
		
		//Final point
		RectanglePrism r = (RectanglePrism)s.get(0);
		
		Vertex[] vs = r.getVertices();
		Point2D.Double[] twoD = new Point2D.Double[vs.length +1];
		for(int i = 0; i < vs.length; i++){
			Vertex a = vs[i];//point
			Vertex b = new Vertex(a.x - l.x, a.y - l.y, a.z - l.z);//vector
			
//			System.out.println(a);
//			System.out.println(b);
			
			double t2 = (D - (A*a.x + B*a.y + C*a.z))/(A*b.x + B*b.y + C*b.z);
			twoD[i] = new Point2D.Double(a.y + b.y*t2, -(a.z + b.z*t2));
//			System.out.println(t2);
		}
		
		Vertex a = r.getCenter();//point
		Vertex b = new Vertex(a.x - l.x, a.y - l.y, a.z - l.z);//vector
		
//		System.out.println(a);
//		System.out.println(b);
		
		double t2 = (D - (A*a.x + B*a.y + C*a.z))/(A*b.x + B*b.y + C*b.z);
		twoD[8] = new Point2D.Double(a.y + b.y*t2, -(a.z + b.z*t2));
//		System.out.println(t2);
		
		r.perspectivePoint(g2, twoD);
//		r.paint(g2);
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
				ts.rotateY((double)(y-cy)*0.01);
			}
			x = cx;
			y = cy;
			repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e){}

	@Override
	public void keyPressed(KeyEvent e) {
		
//		double angle = .01;
//		switch(e.getKeyCode()){
//		case KeyEvent.VK_UP: v.rotateY(angle, l); break;
//		case KeyEvent.VK_DOWN: v.rotateY(-angle, l); break;
//		case KeyEvent.VK_RIGHT: v.rotateZ(angle, l); break;
//		case KeyEvent.VK_LEFT: v.rotateZ(-angle, l); break;
//		}
		
		double shift = 5;
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP: l.z += shift; break;
		case KeyEvent.VK_DOWN: l.z -= shift; break;
		case KeyEvent.VK_RIGHT: l.y += shift; break;
		case KeyEvent.VK_LEFT: l.y -= shift; break;
		}
		
//		RectanglePrism r = (RectanglePrism) s.get(0);
//		double shift = 5;
//		switch(e.getKeyCode()){
//		case KeyEvent.VK_UP: r.shiftZ(shift); break;
//		case KeyEvent.VK_DOWN: r.shiftZ(-shift); break;
//		case KeyEvent.VK_RIGHT: r.shiftY(shift); break;
//		case KeyEvent.VK_LEFT: r.shiftY(-shift); break;
//		}
		
		repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}
