package threeSpace;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import threeSpace.shape.*;

public interface ThreeShape{

	public abstract boolean contains(double x, double y, double z);

	public abstract boolean contains(Vertice p);
	
	public abstract void rotateX(double theta);
	public abstract void rotateY(double theta);
	public abstract void rotateZ(double theta);
	
	public abstract void paint(Graphics2D g);
	
}