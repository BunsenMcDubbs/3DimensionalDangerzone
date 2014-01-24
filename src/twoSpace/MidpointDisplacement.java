package twoSpace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class MidpointDisplacement extends JPanel {

	float[][] map;
	boolean set;
	int gen;
	int scale;

	public MidpointDisplacement(){

		scale = 1; //size of each point when drawn on screen
		int m = 513; //Length of side of array - MUST be (2^x) + 1
		map = new float[m][m];
		map[0][0] = 0.5f;
		map[0][m-1] = 0.5f;
		map[m-1][0] = 0.5f;
		map[m-1][m-1] = 0.5f;

		setSize(scale * m,scale* m);
		setPreferredSize(getSize());
		setBackground(Color.lightGray);
		setForeground(Color.black);

		set = false;

		displace();
		set = true;
		gen = 0; 
	}

	public void paint(Graphics g){
		//		if(!set) return;
		Graphics2D g2 = (Graphics2D)g;
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				float x = map[i][j];
				if(x > 1) x = 1;
				else if (x < 0) x = 0;

				g2.setColor(new Color(x,x,x));
				g2.fillRect(i*scale,j*scale,scale,scale);
				g2.setColor(Color.black);
			}
		}
	}

	private void print() {
		for(int i = 0; i < map.length; i++){
			for(int j = 0; j < map[0].length; j++){
				if(map[i][j] == 5f) System.out.print("X");
				else if(map[i][j] == 2f) System.out.print("#");
				else if(map[i][j] == 3f) System.out.print("@");
				else System.out.print("+");
			}
			System.out.println();
		}
	}

	void displace(){
		displace(new Point(0,0), new Point(map.length-1,0), new Point(0,map[0].length-1), new Point(map.length-1, map[0].length-1), 1);
	}

	class Displace implements Runnable{

		Point ul, ur, ll, lr;
		float smooth;

		public Displace(Point ul, Point ur, Point ll, Point lr, float smooth){
			this.ul = ul; this.ur = ur; this.ll = ll; this.lr = lr; this.smooth = smooth;
		}

		@Override
		public void run() {

			displace(ul,ur,ll,lr,smooth);

		}

	}

	/**
	 * 
	 * @param ul - Location of upper left
	 * @param ur - Location of upper right
	 * @param ll - Location of lower left
	 * @param lr - Location of lower right
	 * @return
	 */
	float displace1(Point ul, Point ur, Point ll, Point lr, float smooth){

		//		gen++;

		if(ll.y - ul.y <= 1) return 0;

		Point lmid = midpoint(ul,ll),
				rmid = midpoint(ur,lr),
				tmid = midpoint(ul,ur),
				bmid = midpoint(ll,lr),
				center = midpoint(midpoint(ul,ur),midpoint(ll,lr));

		float tlh = get(ul), trh = get(ur), blh = get(ll), brh = get(lr), mid = 0.5f;

		if(get(center) != 0.5f){
			mid = (tlh + trh + blh + brh)/4 + ((float)Math.random()-0.5f) * smooth;
			set(center,mid);
		}

		float lmidh = (tlh + blh)/2 + (float)(Math.random() - 0.5f) * smooth * 0.005f, //left midpoint height
				rmidh = (trh + brh)/2 + (float)(Math.random() - 0.5f) * smooth * 0.005f, //right midpoint height
				tmidh = (tlh + trh)/2 + (float)(Math.random() - 0.5f) * smooth * 0.005f, //top midpoint height
				bmidh = (blh + brh)/2 + (float)(Math.random() - 0.5f) * smooth * 0.005f; //bottom midpoint height

		set(lmid,lmidh);
		set(rmid,rmidh);
		set(tmid,tmidh);
		set(bmid,bmidh);

		smooth *= .5;

		new Thread(new Displace(ul,tmid,lmid,center, smooth)).start();
		new Thread(new Displace(tmid,ur,center,rmid, smooth)).start();
		new Thread(new Displace(lmid,center,ll,bmid, smooth)).start();
		new Thread(new Displace(center,rmid,bmid,lr, smooth)).start();

		return mid;

	}

	float displace(Point ul, Point ur, Point ll, Point lr, float smooth){

		//		gen++;

		if(ll.y - ul.y <= 1) return 0;

		Point lmid = midpoint(ul,ll),
				rmid = midpoint(ur,lr),
				tmid = midpoint(ul,ur),
				bmid = midpoint(ll,lr),
				center = midpoint(midpoint(ul,ur),midpoint(ll,lr));

		float tlh = get(ul), trh = get(ur), blh = get(ll), brh = get(lr), mid = 0.5f;

		if(get(center) != 0.5f){
			mid = (tlh + trh + blh + brh)/4 + ((float)Math.random()-0.5f) * smooth;
			set(center,mid);
		}

		float lmidh = (tlh + blh)/2 + (float)(Math.random() - 0.5f) * smooth * 0.005f, //left midpoint height
				rmidh = (trh + brh)/2 + (float)(Math.random() - 0.5f) * smooth * 0.005f, //right midpoint height
				tmidh = (tlh + trh)/2 + (float)(Math.random() - 0.5f) * smooth * 0.005f, //top midpoint height
				bmidh = (blh + brh)/2 + (float)(Math.random() - 0.5f) * smooth * 0.005f; //bottom midpoint height

		set(lmid,lmidh);
		set(rmid,rmidh);
		set(tmid,tmidh);
		set(bmid,bmidh);

		smooth *= .5;

		displace(ul,tmid,lmid,center, smooth);
		displace(tmid,ur,center,rmid, smooth);
		displace(lmid,center,ll,bmid, smooth);
		displace(center,rmid,bmid,lr, smooth);

		return mid;

	}

	float get(Point p){
		return map[p.x][p.y];
	}

	void set(Point p, float x){
		map[p.x][p.y] = x;
	}

	Point midpoint(Point a, Point b){
		return(new Point((a.x+b.x)/2,(a.y+b.y)/2));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GalaxyFrame f = new GalaxyFrame();
		f.initMD();
	}

}