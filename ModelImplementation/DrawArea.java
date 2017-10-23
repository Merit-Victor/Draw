package eg.edu.alexu.csd.oop.draw.cs26;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;

import javax.swing.JComponent;

public class DrawArea extends JComponent{
	// Image in which we're going to draw
		  private Image image;
		  // Graphics2D object ==> used to draw on
		  private Graphics2D g2;
	
	public DrawArea(){
		  setDoubleBuffered(false);
		   
		   
		  
	  }
	 protected void paintComponent(Graphics g) {
		    if (image == null) {
		      // image to draw null ==> we create
		      image = createImage(getSize().width, getSize().height);
		      setG2((Graphics2D) image.getGraphics());
		      // enable antialiasing
		      getG2().setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		      // clear draw area
		      clear();
		    }
		 
		    g.drawImage(image, 0, 0, null);
		  }
		 
		  // now we create exposed methods
		  public void clear() {
		    getG2().setPaint(Color.white);
		    // draw white on entire draw area to clear
		    getG2().fillRect(0, 0, getSize().width, getSize().height);
		    getG2().setPaint(Color.black);
		    repaint();
		  }
		public Graphics2D getG2() {
			return g2;
		}
		public void setG2(Graphics2D g2) {
			this.g2 = g2;
		}

}
