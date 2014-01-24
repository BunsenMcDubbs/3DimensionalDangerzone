package twoSpace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimatedCircle extends JPanel implements ActionListener{

	ParticleController p;
	double angle;
	double interval;
	int count;
	int age;
	int generationrate;
	int particleRadius;
	int centerRadius;

	public AnimatedCircle(){
		setSize(800,800);
		setPreferredSize(getSize());
		setBackground(Color.DARK_GRAY);
		setForeground(Color.CYAN);
		Timer t = new Timer(1000/60, this);
		t.start();

		p = new ParticleController(getWidth(),getHeight());

		angle = 0;
		age = 0;
		count = 0;
		generationrate = 20;
		particleRadius = 7;
		centerRadius = 40;
		
		init1();
	}

	private void init1(){
		int points = 1;
		interval = (0.05*Math.PI)/(double)points;
		for(int i = 1; i <= points; i++){
			double x = Math.cos(angle);
			double y = Math.sin(angle);
			p.add(new Particle(particleRadius, x*centerRadius + getWidth()/2, y*centerRadius + getHeight()/2, x*i*2, y*i*2));
			angle += interval;
			count++;
		}
	}

	public void paint1(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(Color.LIGHT_GRAY);
		g2.fillRect(0, 0, getWidth(), getHeight());

		p.draw(g2);
		
//		for(int i = 0; i < count; i++){
//			double x = Math.cos(interval * i) *50 + getWidth()/2 - 10;
//			double y = Math.sin(interval * i) *50 + getHeight()/2 - 10;
//			g2.fillOval((int)x, (int)y, 20, 20);
//		}
		
		Color c = g2.getColor();
		int r = c.getRed();
		int gr = c.getGreen();
		int b = c.getBlue();
		double progression = ((double)age%generationrate)/generationrate/2 + 0.5;
		if(progression > 1d) progression = 1;
		int a = (int)(255 * progression);
		g2.setColor(new Color(r,gr,b,a));
		
		
		g2.fillOval(getWidth()/2-centerRadius, getHeight()/2-centerRadius, centerRadius*2, centerRadius*2);
	}

	public void paint(Graphics g){
		paint1(g);
	}

	public void update(){
		p.update();
		if(angle > 6.28) angle = 0;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		update();
		repaint();
		age++;
		if(age % generationrate == 0) init1();
	}
	
	public static void main(String [] args){
		GalaxyFrame f = new GalaxyFrame();
		f.initAC();
	}


}
