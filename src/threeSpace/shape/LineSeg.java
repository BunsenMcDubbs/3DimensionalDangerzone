package threeSpace.shape;

import java.awt.Graphics2D;

import threeSpace.ThreeShape;


public class LineSeg implements ThreeShape{
	
	Vertex start;
	Vertex end;
	
	public LineSeg(Vertex start, Vertex end){
		this.start = start;
		this.end = end;
	}
	
	public LineSeg(double x, double y, double z, double x1, double y1, double z1){
		this(new Vertex(x,y,z), new Vertex(x1,y1,z1));
	}

	@Override
	public boolean contains(double x, double y, double z) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(Vertex p) {
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
