package threeSpace.shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Double;
import java.awt.geom.Rectangle2D;

import threeSpace.ThreeShape;

public class RectanglePrism implements ThreeShape {
	
	private Vertice[] v;
	double x, y, z;
	
	public RectanglePrism (Vertice[] s){
		setVertices(s);
		assert(getVertices().length == 8);
	}
	
	public void paint(Graphics2D g){
		
		for(int i = 0; i < 3; i++){
			g.drawLine((int)v[i].y, (int)v[i].z, (int)v[i+1].y, (int)v[i+1].z);
		}
		g.drawLine((int)v[3].y, (int)v[3].z, (int)v[0].y, (int)v[0].z);
		for(int i = 4; i < 7; i++){
			g.drawLine((int)v[i].y, (int)v[i].z, (int)v[i+1].y, (int)v[i+1].z);
		}
		g.drawLine((int)v[7].y, (int)v[7].z, (int)v[4].y, (int)v[4].z);
		for(int i = 0; i < 4; i++){
			g.drawLine((int)v[i].y, (int)v[i].z, (int)v[i+4].y, (int)v[i+4].z);
		}
		
		for(Vertice p:v){
			if (p.x > 0) g.setColor(Color.white);
			else g.setColor(Color.black);
			g.fillOval((int)p.y-4, (int)p.z-4, 8, 8);
		}
		
	}
	
	@Override
	public boolean contains(Vertice p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(double x, double y, double z) {
		return contains(new Vertice(x,y,z));
	}
	

	public Vertice[] getVertices() {
		return v;
	}

	public void setVertices(Vertice[] v) {
		this.v = v;
	}
	
	public Vertice getCenter(){
		double x = 0, y = 0, z = 0;
		for(int i = 0; i < v.length; i++){
			x += v[i].x;
			y += v[i].y;
			z += v[i].z;
		}
		return new Vertice (x/v.length, y/v.length, z/v.length);
	}
	
	@Override
	public void rotateX(double theta) {
		for(Vertice vs : v)
			vs.rotateX(theta);
	}

	@Override
	public void rotateY(double theta) {
		for(Vertice vs : v)
			vs.rotateY(theta);		
	}

	@Override
	public void rotateZ(double theta) {
		for(Vertice vs : v)
			vs.rotateZ(theta);
	}

	public void perspectivePoint(Graphics2D g, Double[] a) {
		for(int i = 0; i < 3; i++){
			g.drawLine((int)a[i].x, (int)a[i].y, (int)a[i+1].x, (int)a[i+1].y);
		}
		g.drawLine((int)a[3].x, (int)a[3].y, (int)a[0].x, (int)a[0].y);
		for(int i = 4; i < 7; i++){
			g.drawLine((int)a[i].x, (int)a[i].y, (int)a[i+1].x, (int)a[i+1].y);
		}
		g.drawLine((int)a[7].x, (int)a[7].y, (int)a[4].x, (int)a[4].y);
		for(int i = 0; i < 4; i++){
			g.drawLine((int)a[i].x, (int)a[i].y, (int)a[i+4].x, (int)a[i+4].y);
		}
		
		g.setColor(Color.white);
		for(Double p:a){
			g.fillOval((int)p.x-4, (int)p.y-4, 8, 8);
		}
	}

}
