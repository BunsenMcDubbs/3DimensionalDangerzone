package twoSpace;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class RidgeLine {
	
	RidgeLine left;
	RidgeLine right;
	
	float smooth;
	
	Point2D.Float start;
	Point2D.Float end;
	
	boolean split;
	
	public RidgeLine(float x1, float y1, float x2, float y2, float smooth){
		this(new Point2D.Float(x1,y1), new Point2D.Float(x2,y2), smooth);
	}
	
	public RidgeLine(Point2D.Float start, Point2D.Float end, float smooth){
		this.start = start;
		this.end = end;
		this.smooth = smooth;
		split = false;
	}
	
	void split(){
		if(split){
			left.split();
			right.split();
			return;
		}
		split = true;
		float h = ((float)Math.random() - 0.5f) * smooth + (start.y + end.y)/2;
		Point2D.Float mid = new Point2D.Float((start.x + end.x)/2, h);
		
		smooth *= 0.52;
		left = new RidgeLine(start, mid, smooth);
		right = new RidgeLine(mid, end, smooth);
	}
	
	void draw(Graphics2D g2){
		
		if(split){
			left.draw(g2);
			right.draw(g2);
		}
		else g2.drawLine((int)start.x, (int)start.y, (int)end.x, (int)end.y);
	}
	
}
