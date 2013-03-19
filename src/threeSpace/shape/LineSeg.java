package threeSpace.shape;

import java.awt.Graphics2D;

import threeSpace.ThreeShape;


public class LineSeg implements ThreeShape{
	
	Vertice start;
	Vertice end;
	
	public LineSeg(Vertice start, Vertice end){
		this.start = start;
		this.end = end;
	}
	
	public LineSeg(double x, double y, double z, double x1, double y1, double z1){
		this(new Vertice(x,y,z), new Vertice(x1,y1,z1));
	}

	@Override
	public boolean contains(double x, double y, double z) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Vertice p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void rotateX(double theta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateY(double theta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotateZ(double theta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}
	
	
}
