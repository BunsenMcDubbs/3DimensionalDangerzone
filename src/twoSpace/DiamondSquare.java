package twoSpace;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class DiamondSquare extends JPanel {

	float[][] map;
	float[][] map1;
	boolean set;
	int gen;
	int scale;
	
	public DiamondSquare(){

		scale = 10; //size of each point when drawn on screen
		int m = 17; //Length of side of array - MUST be (2^x) + 1
		map = new float[m][m];
		map[0][0] = 0.5f;
		map[0][m-1] = 0.5f;
		map[m-1][0] = 0.5f;
		map[m-1][m-1] = 0.5f;
		
		map1 = new float[m][m];

		setSize(scale * m,scale* m);
		setPreferredSize(getSize());
		setBackground(Color.lightGray);
		setForeground(Color.black);
		
		square();
		
		gen = 0; 
		
		for(int i = 0; i < map.length; i++)
			if(map[i][0] != map[i][m-1]) System.out.println("fail");
		for(int i = 0; i < map.length; i++)
			if(map[0][i] != map[m-1][i]) System.out.println("fail");
		
		print();
	}

	private void square() {
		square(new Point(0,0), new Point(map.length-1,0), new Point(0,map[0].length-1), new Point(map.length-1, map[0].length-1), 1);

	}

	public void paint1(Graphics g){
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
				if(map1[i][j] == 5f) System.out.print("X");
				else if(map1[i][j] == 2f) System.out.print("#");
				else if(map1[i][j] == 3f) System.out.print("@");
				else System.out.print("+");
			}
			System.out.println();
		}
		
		System.out.println();
	}

	float square(Point ul, Point ur, Point ll, Point lr, float smooth){
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
			set1(center, 5f);
		}

		int len = center.x - ul.x;
		
		diamond(lmid, len, smooth);
		diamond(rmid, len, smooth);
		diamond(tmid, len, smooth);
		diamond(bmid, len, smooth);
		
		set1(lmid,2f);
		set1(rmid,2f);
		set1(tmid,2f);
		set1(bmid,2f);
		
		print();
		
		smooth *= .5;

		square(ul,tmid,lmid,center, smooth);
		square(tmid,ur,center,rmid, smooth);
		square(lmid,center,ll,bmid, smooth);
		square(center,rmid,bmid,lr, smooth);

		return mid;

	}
	
	float square1(float smooth, Point center, Point... p){
		
		float sum = 0f;
		int n = 0;
		for(int i = 0; i < p.length; i++){
			float curr= get(p[i]);
			if (curr != -1f){
				sum += curr;
				n++;
			}
		}
		
		float mid = sum/(float)n + ((float)Math.random()-0.5f) * smooth;
		set(center,mid);
			
		return mid;

	}
	
	void diamond(Point p, int len, float smooth){
		
		Point top = new Point(p.x, p.y + len),
				left = new Point(p.x + len, p.y),
				bottom = new Point(p.x, p.y - len),
				right = new Point(p.x - len, p.y);
		
		square1(smooth,p,top,left,bottom,right);
		
	}

	float get(Point p){
		try{return map[p.x][p.y];}
		catch (ArrayIndexOutOfBoundsException e){return -1f;}
	}
	
	float getWrap(Point p){
		int x = p.x, y = p.y;
		if (x < 0){
			x++;
			while (x < 0) x += map.length;
		}
		else if (x >= map.length){
			x--;
			while (x >= map.length) x -= map.length;
		}
		if (y < 0){
			y++;
			while (y < 0) y += map[0].length;
		}
		else if (y >= map[0].length){
			y--;
			while (y >= map[0].length) y -= map[0].length;
		}
		return map[x][y];
	}

	void set(Point p, float x){
		
		if(p.x == map.length-1) map[0][p.y] = x;
		if(p.y == map[0].length-1) map[p.x][0] = x;
		
		map[p.x][p.y] = x;
	}
	
	void set1(Point p, float x){
		
		if(p.x == map.length-1) map[0][p.y] = x;
		if(p.y == map[0].length-1) map[p.x][0] = x;
		
		map1[p.x][p.y] = x;
	}

	Point midpoint(Point a, Point b){
		return(new Point((a.x+b.x)/2,(a.y+b.y)/2));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GalaxyFrame f = new GalaxyFrame();
		f.initDS();
	}

}