package twoSpace;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Particle {
	double radius;
	int age;
	int lifespan;
	boolean dead;
	Point2D.Double loc;
	Point2D.Double dir;
	double angle;
	int phase;
	
	public Particle(double radius, double x, double y, double dirx, double diry){
		this(radius, new Point2D.Double(x,y), new Point2D.Double(dirx, diry));
	}
	
	public Particle(double radius, Point2D.Double loc, Point2D.Double dir){
		this.radius = radius;
		this.loc = loc;
		this.dir = dir;
//		System.out.println(dir);
		age = 0;
		dead = false;
		phase = 0;
	}
	
	public void update(){
		age++;
		loc.x += dir.x;
		loc.y += dir.y;
		if(phase == 0 && age > 80){
			phase = 1;
		}
		
		switch(phase){
		case 0: break;
		case 1: phase1(); break;
		case 2: phase2(); break;
		case 3: phase3(); break;
		default: break;
		}
	}
	
	private void phase1() {
		dir.x /= 1.01d;
		dir.y /= 1.01d;
		
		if(age > 200){
			phase = 2;
		}
	}
	
	private void phase2(){
		dir.x /= 1.02d;
		dir.y /= 1.02d;
		
		if(age > 700){
			dir.x = 0;
			dir.y = 0;
			phase = 3;
		}
	}
	
	private void phase3(){
		dir.x = 0;
		dir.y = 0;
		dead = true;
	}

	public void draw(Graphics2D g2){
		g2.fillOval((int)(loc.x - radius), (int)(loc.y - radius), (int)(2*radius), (int)(2*radius));
	}
	
	public boolean isDead(){return dead;}
}
