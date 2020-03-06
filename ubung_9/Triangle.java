package ubung_9;


import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Random;
import javax.swing.*; 

public class Triangle extends JPanel {

	private static int width = 900;
	private static int height = 950;
	private static int h;	
	private Dimension D = new Dimension(width, height);
	
	private static int recursion = 5;
	private static Color[] colors = new Color[recursion+1];

	  public static void main(String[] args) {
		  
		  JFrame frame = new JFrame();		    
		  frame.getContentPane().add(new Triangle());
		  frame.setSize(width,height);
		  frame.setVisible(true);
		  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

	    	randomColors();
	  }

	    public void paint(Graphics g) {

	    	Point p1;
			Point p2;
			Point p3;
			
	    	D.setSize(getParent().getSize());
			height = D.height;
			width = D.width;
			
	    	if (height >= width) {
	    		p1 = new Point (0,height);
	    		p2 = new Point (width,height);
	    		h = (int) (height-(width/2*Math.sqrt(3)));
	    		p3 = new Point (width/2,h);
	    	}else {
		    	p1 = new Point (width,0);
	    		p2 = new Point (width,height);
		        h = (int) (width-(height/2*Math.sqrt(3)));
		        p3 = new Point (h,height/2);
	    	}
			 int xCoords[] = {p1.getX(), p2.getX(), p3.getX()};
			 int yCoords[] = {p1.getY(), p2.getY(), p3.getY()};
			 
			Graphics2D g2 = (Graphics2D) g;
			drawTriangle(g2, p1, p2, p3, recursion); 
			g.drawPolygon(xCoords, yCoords, 3);
	    }

	  public void drawTriangle(Graphics2D g, Point p1, Point p2, Point p3, int recursion) {
		
		  	Color c = new Color(255-recursion*25,0,recursion*25);
			g.setColor(colors[recursion]);
			
			Point midP12 = getMidpoint (p1, p2);
			Point midP13 = getMidpoint (p1, p3);
			Point midP23 = getMidpoint (p2, p3);
		 
		 if (recursion != 0) {

			int xxCoords[] = {midP12.getX(), midP13.getX(), midP23.getX()};
			int yyCoords[] = {midP12.getY(), midP13.getY(), midP23.getY()};
			g.fillPolygon(xxCoords, yyCoords, 3);
			g.drawPolygon(xxCoords, yyCoords, 3);
			
			drawTriangle(g, p1, midP12, midP13, recursion-1);
			drawTriangle(g, midP12, p2, midP23, recursion-1);
			drawTriangle(g, midP13, midP23, p3, recursion-1);		

	    }
	    
	  }

	  public Point getMidpoint(Point p1, Point p2) {
		  
		Point p3 = new Point((p1.getX()+ p2.getX())/2,(p1.getY()+ p2.getY())/2);
		return p3;
		    
	  }

	  public static void randomColors()
	  {
		  Random rand = new Random();
		  float r;
		  float g;
		  float b;
		  for(int i = 0; i<recursion;i++) {
				r = rand.nextFloat();
				g = rand.nextFloat();
				b = rand.nextFloat();
				colors[i] = new Color(r,g,b);
		  }
	  }
} 
