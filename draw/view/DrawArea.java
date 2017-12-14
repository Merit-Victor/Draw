package eg.edu.alexu.csd.oop.draw.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.RenderingHints;

import javax.swing.JComponent;

/**
 * @author Merit Victor
 * The area to be drawn on.
 */
@SuppressWarnings("serial")
public class DrawArea extends JComponent{
	
	/**
	 * Image in which we're going to draw
	 */
	private Image image;
	
	/**
	 * 
	 */
	private static final int ZERO = 0;
	
	/**
	 * 
	 */
	private static final int THOUSAND = 1000;
	
	/**
	 * Graphics2D object ==> used to draw on
	 */
	private Graphics2D g2;
		  
	/**
	 * Constructor.
	 */
	public DrawArea(){
		setDoubleBuffered(false);	  
	}
	 
	/* (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	protected void paintComponent(Graphics g) {
		if (image == null) {		
			image = createImage(THOUSAND, THOUSAND);
			setG2((Graphics2D) image.getGraphics());
			this.getG2().setRenderingHint(
					RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		 g.drawImage(image, ZERO, ZERO, null);
	 }
		 
	/**
	 * Clears the drawing area.
	 */
	public void clear() {
		  getG2().setPaint(Color.white);
		  getG2().fillRect(ZERO, ZERO,
				  getSize().width, getSize().height);
		  getG2().setPaint(Color.black);
		  repaint();
	}
	
	/**
	 * Getter method
	 * @return Graphics2D
	 */
	public Graphics2D getG2() {
		return g2;
	}
	
	/**
	 * Setter method.
	 * @param g2
	 */
	public void setG2(Graphics2D g2) {
		this.g2 = g2;
	}
}
