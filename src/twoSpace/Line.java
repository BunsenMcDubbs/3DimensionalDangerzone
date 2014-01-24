package twoSpace;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Point2D.Float;

public class Line {

	Point2D.Float start;
	Point2D.Float end;
	Point2D.Float center;

	Line mLeft, mRight, left, right, bottom;

	boolean split;
	boolean pol;

//	int gen;

	public Line(int x1, int y1, int x2, int y2){
		this(new Point2D.Float(x1,y1), new Point2D.Float(x2,y2), new Point2D.Float(0,0));
	}

	public Line(float r, float theta, float r2, float theta2){
		this (Fractal.polToCart(new Point2D.Float(r, theta)), Fractal.polToCart(new Point2D.Float(r2, theta2)), new Point2D.Float(0,0));
	}

	public Line(Point2D.Float start, Point2D.Float end, Point2D.Float center) {
		this.start = start;
		this.end = end;
		this.center = center;
		pol = false;
		split = false;
//		gen = 0;
	}

	public void split(){
		if (split == true) {
			mLeft.split();
			mRight.split();
			bottom.split();
			left.split();
			right.split();
			return;
		}
		split = true;
//		gen++;
		polToCart();

		Point2D.Float mid = midpoint(start, end);

		Point2D.Float leftTemp = midpoint(start, mid);
		Point2D.Float rightTemp = midpoint(mid, end);

		float dist = (float) Math.sqrt(Math.pow((leftTemp.x - rightTemp.x),2) + Math.pow((leftTemp.y - rightTemp.y),2));
		dist = 1.7320508075688772f * dist / 2;
		mid = Fractal.cartToPol(mid, center);
		mid.x += dist;

		Point2D.Float cMid = (Float) mid.clone();
		mid = Fractal.polToCart(mid, center);
		Point2D.Float cLeft = Fractal.cartToPol(start, center);
		Point2D.Float cRight = Fractal.cartToPol(end, center);

		cMid.x -= dist/1.5;

		float sidedist = (float) Math.sqrt((leftTemp.x - start.x) * (leftTemp.x - start.x) + (leftTemp.y - start.y) * (leftTemp.y - start.y));
		sidedist = 1.7320508075688772f * sidedist / 3;

		cLeft.x -= sidedist;
		cRight.x -= sidedist;

		cMid = Fractal.polToCart(cMid, center);
		cLeft = Fractal.polToCart(cLeft, center);
		cRight = Fractal.polToCart(cRight, center);

		mLeft = new Line(leftTemp, mid, cMid);
		mRight = new Line(mid, rightTemp, cMid);
		left = new Line(start, leftTemp, cLeft);
		right = new Line(rightTemp, end, cRight);
		bottom = new Line(leftTemp, rightTemp, cMid);

	}

	public static Point2D.Float midpoint(Point2D.Float start, Point2D.Float end){
		return new Point2D.Float(start.x/2 + end.x/2, start.y/2 + end.y/2);
	}

	public boolean cartToPol(){
		if(pol == true) return false;
		start = Fractal.cartToPol(start, center);
		end = Fractal.cartToPol(end, center);
		pol = true;
		return true;
	}

	public boolean polToCart(){
		if(pol == false) return false;
		start = Fractal.polToCart(start, center);
		end = Fractal.polToCart(end, center);
		pol = false;
		return true;
	}

	public void draw(Graphics2D g2){

		g2.setColor(Color.lightGray);
		//		g2.setStroke(new BasicStroke(2));
		polToCart();
		if(!split) g2.draw(new Line2D.Float(start, end));
		else{
			left.draw(g2);
			right.draw(g2);
			bottom.draw(g2);
			mLeft.draw(g2);
			mRight.draw(g2);
			/*if (!left.split){
				g2.setColor(Color.green);
				g2.setStroke(new BasicStroke(1));
				Point2D.Float ltemp = midpoint(this.left.start, this.left.end);
				g2.drawLine((int)ltemp.x, (int)ltemp.y, (int)left.center.x, (int)left.center.y);
				g2.setColor(Color.red);
				Point2D.Float rtemp = midpoint(this.right.start, this.right.end);
				g2.drawLine((int)rtemp.x, (int)rtemp.y, (int)right.center.x, (int)right.center.y);
				g2.setColor(Color.orange);
				Point2D.Float mrtemp = midpoint(this.mRight.start, this.mRight.end);
				g2.drawLine((int)mrtemp.x, (int)mrtemp.y, (int)mRight.center.x, (int)mRight.center.y);
				g2.setColor(Color.magenta);
				Point2D.Float mltemp = midpoint(this.mLeft.start, this.mLeft.end);
				g2.drawLine((int)mltemp.x, (int)mltemp.y, (int)mLeft.center.x, (int)mLeft.center.y);
			}*/
		}

	}

}
