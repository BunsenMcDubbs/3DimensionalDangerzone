package threeSpace.shape;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;

import threeSpace.ThreeShape;

public class Vertex implements ThreeShape {
	
	public static final Vertex ORIGIN = new Vertex (0,0,0);

	public double x, y, z;

	public Vertex(double[] loc) {
		this(loc[0], loc[1], loc[2]);
	}

	public Vertex() {
		this(0, 0, 0);
	}

	public Vertex(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double angleToX(Vertex origin) {
		double y = this.y - origin.y;
		double z = this.z - origin.z;
		double r = Math.sqrt(y*y + z*z);
		
		double angle = Math.acos(y/r);
		if(z < origin.z) angle = -angle;
		
		return angle;
	}
	
	public double angleToY(Vertex origin) {
		double x = this.x - origin.x;
		double z = this.z - origin.z;
		double r = Math.sqrt(x*x + z*z);
		
		double angle = Math.acos(x/r);
		if(z < origin.z) angle = -angle;
		
		return angle;
	}
	
	public double angleToZ(Vertex origin) {
		double y = this.y - origin.y;
		double x = this.x - origin.x;
		double r = Math.sqrt(y*y + x*x);
		
		double angle = Math.acos(x/r);
		if(y < origin.y) angle = -angle;
		
		return angle;
	}

	public double angleToX() {
		return angleToX(ORIGIN);
	}

	public double angleToY() {
		return angleToY(ORIGIN);
	}
	
	public double angleToZ() {
		return angleToZ(ORIGIN);
	}
	
	public double distToXAxis() {
		return Math.sqrt(y * y + z * z);
	}

	public double distToYAxis() {
		return Math.sqrt(x * x + z * z);
	}

	public double distToZAxis() {
		return Math.sqrt(x * x + y * y);
	}

	public String toString() {
		return "" + x + ", " + y + ", " + z;
	}

	@Override
	public boolean equals(Object v) {
		if (v instanceof Vertex) {
			Vertex v2 = (Vertex) v;
			return v2.x == x && v2.y == y && v2.z == z;
		}
		return false;

	}

	@Override
	public boolean contains(double x, double y, double z) {
		return x == this.x && y == this.y && z == this.z;
	}

	@Override
	public boolean contains(Vertex p) {
		return this.equals(p);
	}

	@Override
	public void rotateX(double t) {
		rotateX(t, ORIGIN);
	}

	@Override
	public void rotateY(double t) {
		rotateY(t, ORIGIN);
	}

	@Override
	public void rotateZ(double t) {
		rotateZ(t, ORIGIN);
	}
	
	public void rotateX(double t, Vertex origin){
		double oldY = this.y - origin.y;
		double oldZ = this.z - origin.z;
		this.y = oldY * Math.cos(t) - oldZ * Math.sin(t) + origin.y;
		this.z = oldY * Math.sin(t) + oldZ * Math.cos(t) + origin.z;
	}
	
	public void rotateY(double t, Vertex origin){
		double oldX = this.x - origin.x;
		double oldZ = this.z - origin.z;
		this.x = oldX * Math.cos(t) - oldZ * Math.sin(t) + origin.x;
		this.z = oldX * Math.sin(t) + oldZ * Math.cos(t) + origin.z;
	}
	
	public void rotateZ(double t, Vertex origin){
		double oldX = this.x - origin.x;
		double oldY = this.y - origin.y;
		this.x = oldX * Math.cos(t) - oldY * Math.sin(t) + origin.x;
		this.y = oldX * Math.sin(t) + oldY * Math.cos(t) + origin.y;
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	
	
}
