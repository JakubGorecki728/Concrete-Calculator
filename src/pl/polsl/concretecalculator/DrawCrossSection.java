package pl.polsl.concretecalculator;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;



public class DrawCrossSection extends JPanel {
	


	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;




	

	Calculations calculations = new Calculations();
	
	public static final int JPANELWIDTH = 300;
	public static final int JPANELHEIGHT = 400;
	
	private double b;
	private double h;
	private double dm;
	private double ds;
	private double cnom;
	private double lsrz;
	private double lsmin;
	
	private double nRebars;
	private double nrzMaxInRow;
	private double nRows;
	
	
	
	public void setValues(double b, double h, double dm, double ds, double cnom, double lsrz, double lsmin, double nRebars, double nrzMaxInRow, double nRows ) {
		this.b = b;
		this.h = h;
		this.dm = dm;
		this.ds = ds;
		this.cnom = cnom;
		this.lsrz = lsrz;
		this.lsmin = lsmin;
		this.nRebars = nRebars;
		this.nrzMaxInRow = nrzMaxInRow;
		this.nRows = nRows;
	}

	
	
	
	public void drawCrossSection(Object panel) {
	//	drawOutlineAndStirrups (panel);
	//	drawMainRebars (panel);
	
	}

	
	
	
	private double setScale() {
		double xyScale;
		if (h>b*(1.5)) {xyScale = 330/h;} else {xyScale = 220/b;}
		return xyScale;
	}
	

	 
	  public void paintComponent(Graphics g)
	    {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D)g;

	        double xCenterAxis = (JPANELWIDTH/2)/setScale();
	 		double yCenterAxis = (JPANELHEIGHT/2)/setScale();
	 	
	 		Rectangle2D outline = new Rectangle2D.Double(xCenterAxis-b/2, yCenterAxis-h/2, b, h);
	 	
	 		RoundRectangle2D stirrup = new RoundRectangle2D.Double(xCenterAxis-b/2+cnom+0.5*ds, yCenterAxis-h/2+cnom+0.5*ds, b-2*cnom-ds, h-2*cnom-ds, 10, 10);

	 	
	 		g2.setColor(Color.black);
	 		g2.scale(setScale(), setScale());
	 		g2.draw(outline);
	 		
	 		g2.setColor(Color.gray);
	 		g2.setStroke(new BasicStroke((int)ds));
	 		g2.draw(stirrup);
	 		
	 		g2.setColor(Color.black);
	 		g2.setStroke(new BasicStroke((int)1));
	 		
	 		drawMainRebars(g2);
	 		
	 		g2.setColor(Color.blue);
	 		//drawDimensionLines(g2);
	 		drawHorizontalLine(g2,xCenterAxis-b/2,yCenterAxis+h/2+(25/setScale()),b);
	 		
	 		drawVerticalLine(g2,xCenterAxis+b/2+(25/setScale()),yCenterAxis+h/2,h);
	 		
	    }
	  
	  
	  
	  
	  
	  public void drawHorizontalLine (Graphics2D g2, double x, double y, double length) {
		  double textSize = 13/setScale();
		  double lineSize = 10/setScale();
		  
		  Line2D dimensionLine1 = new Line2D.Double(x,y,x+length,y);
		  Line2D dimensionLine1leftside = new Line2D.Double(x,y-lineSize/2,x,y+lineSize/2);
		  Line2D dimensionLine1rightside = new Line2D.Double(x+length,y-lineSize/2,x+length,y+lineSize/2);
		  Line2D dimensionLine1leftside45 = new Line2D.Double(x-lineSize/2,y-lineSize/2,x+lineSize/2,y+lineSize/2);
		  Line2D dimensionLine1rightside45 = new Line2D.Double(x+length-lineSize/2,y-lineSize/2,x+length+lineSize/2,y+lineSize/2);
		  
		  g2.draw(dimensionLine1);
	 		g2.draw(dimensionLine1leftside);
	 		g2.draw(dimensionLine1rightside);
	 		g2.draw(dimensionLine1leftside45);
	 		g2.draw(dimensionLine1rightside45);
	 		
	 		 Font stringFont = new Font( "Arial", Font.PLAIN, (int)textSize);
			 
			  String lengthString = String.valueOf((int)Math.round(length));;
			  g2.setFont(stringFont);
			  g2.drawString(lengthString, (int)(x+length/2 - (lengthString.length()*(textSize/3))), (int)(y-textSize/10));
	  }
	  
	  
	  
	  public void drawVerticalLine (Graphics2D g2, double x, double y, double length) {
		  double textSize = 13/setScale();
		  double lineSize = 10/setScale();
		  
		  Line2D dimensionLine1 = new Line2D.Double(x,y,x,y-length);
		  Line2D dimensionLine1leftside = new Line2D.Double(x-lineSize/2,y,x+lineSize/2,y);
		  Line2D dimensionLine1rightside = new Line2D.Double(x-lineSize/2,y-length,x+lineSize/2,y-length);
		  Line2D dimensionLine1leftside45 = new Line2D.Double(x-lineSize/2,y-lineSize/2,x+lineSize/2,y+lineSize/2);
		  Line2D dimensionLine1rightside45 = new Line2D.Double(x-lineSize/2,y-lineSize/2-length,x+lineSize/2,y+lineSize/2-length);
		  
		  g2.draw(dimensionLine1);
	 		g2.draw(dimensionLine1leftside);
	 		g2.draw(dimensionLine1rightside);
	 		g2.draw(dimensionLine1leftside45);
	 		g2.draw(dimensionLine1rightside45);
	 		
	 		 Font stringFont = new Font( "Arial", Font.PLAIN, (int)textSize);
			 
			  String lengthString = String.valueOf((int)Math.round(length));
			  g2.setFont(stringFont);
			  
			  AffineTransform original = g2.getTransform();
			  g2.rotate(-Math.PI/2);
			  g2.drawString(lengthString, (int)(-y+length/2-(lengthString.length()*(textSize/3))), (int)(x-textSize/10));
			  g2.setTransform(original);
		  
	  }
	  
	  
	  
	  
	  
	  public void drawDimensionLines (Graphics2D g2) {
		double xCenterAxis = (JPANELWIDTH/2)/setScale();
	 	double yCenterAxis = (JPANELHEIGHT/2)/setScale();
		  Line2D dimensionLine1 = new Line2D.Double(xCenterAxis-b/2,yCenterAxis+h/2+(25/setScale()),xCenterAxis+b/2,yCenterAxis+h/2+(25/setScale()));
		  Line2D dimensionLine1leftside = new Line2D.Double(xCenterAxis-b/2,yCenterAxis+h/2+(20/setScale()),xCenterAxis-b/2,yCenterAxis+h/2+(30/setScale()));
		  Line2D dimensionLine1rightside = new Line2D.Double(xCenterAxis+b/2,yCenterAxis+h/2+(20/setScale()),xCenterAxis+b/2,yCenterAxis+h/2+(30/setScale()));
		  Line2D dimensionLine1leftside45 = new Line2D.Double(xCenterAxis-b/2-(5/setScale()),yCenterAxis+h/2+(20/setScale()),xCenterAxis-b/2+(5/setScale()),yCenterAxis+h/2+(30/setScale()));
		  Line2D dimensionLine1rightside45 = new Line2D.Double(xCenterAxis+b/2-(5/setScale()),yCenterAxis+h/2+(20/setScale()),xCenterAxis+b/2+(5/setScale()),yCenterAxis+h/2+(30/setScale()));
		  
		  Line2D dimensionLine2 = new Line2D.Double(xCenterAxis+b/2+(25/setScale()),yCenterAxis-h/2,xCenterAxis+b/2+(25/setScale()),yCenterAxis+h/2);
		  Line2D dimensionLine2leftside = new Line2D.Double(xCenterAxis-b/2,yCenterAxis+h/2+(20/setScale()),xCenterAxis-b/2,yCenterAxis+h/2+(30/setScale()));
		  Line2D dimensionLine2rightside = new Line2D.Double(xCenterAxis+b/2,yCenterAxis+h/2+(20/setScale()),xCenterAxis+b/2,yCenterAxis+h/2+(30/setScale()));
		  Line2D dimensionLine2leftside45 = new Line2D.Double(xCenterAxis-b/2-(5/setScale()),yCenterAxis+h/2+(20/setScale()),xCenterAxis-b/2+(5/setScale()),yCenterAxis+h/2+(30/setScale()));
		  Line2D dimensionLine2rightside45 = new Line2D.Double(xCenterAxis+b/2-(5/setScale()),yCenterAxis+h/2+(20/setScale()),xCenterAxis+b/2+(5/setScale()),yCenterAxis+h/2+(30/setScale()));
		  Font stringFont = new Font( "Arial", Font.PLAIN, (int)(15/setScale()) );
		 
		  String width = String.valueOf((int)b);
		  g2.setFont(stringFont);
		  
	 		g2.draw(dimensionLine1);
	 		g2.draw(dimensionLine1leftside);
	 		g2.draw(dimensionLine1rightside);
	 		g2.draw(dimensionLine1leftside45);
	 		g2.draw(dimensionLine1rightside45);
	 		
	 		g2.draw(dimensionLine2);
	 		g2.draw(dimensionLine2leftside);
	 		g2.draw(dimensionLine2rightside);
	 		g2.draw(dimensionLine2leftside45);
	 		g2.draw(dimensionLine2rightside45);
	 		g2.drawString(width, (int)(xCenterAxis - (width.length()*(5/setScale()))), (int)(yCenterAxis+h/2+(23/setScale())));
	 		
	  }
	
	 
		public void drawMainRebars (Graphics2D g2) {
			double ax = (JPANELWIDTH/2)/setScale();
	 		double ay = (JPANELHEIGHT/2)/setScale();
	 		double lx = 0.5*b-cnom-ds-0.5*dm;
	 		double ly = 0.5*h-cnom-ds-0.5*dm;
	 		double side;
	 		double lreb;
	 		if (nRows == 1) {
	 			lreb = (b-2*(cnom+ds+0.5*dm))/(nRebars-1);
	 		} else {lreb = lsrz+dm;}
	 		
	 		for (int j=0; j<nRows; j++) {
	 		for (int i=0; i<Math.min(nrzMaxInRow, nRebars-j*nrzMaxInRow); i++) {
	 			if (i%2 == 0) {
	 				side = 1;
	 			} else {side = -1;}
	 			
	 		
	 			Ellipse2D elipse = new Ellipse2D.Double(ax-(lx-lreb*Math.floor((double)i/2))*side-0.5*dm,ay+ly-0.5*dm-(dm+lsmin)*j, dm, dm);
	 			g2.draw(elipse);
	 			g2.fill(elipse);
	 		
	 		}
	 		
		 	} 
		}

	

}