package eg.edu.alexu.csd.oop.draw.cs26;

import java.awt.Graphics;



public class Rectangle extends ShapeImp {
	
	
	@Override
	public void draw(Graphics canvas) {
		 if (canvas != null) {			 
		canvas.drawRect(5, 5,(this.getProperties().get("width")).intValue(), (this.getProperties().get("height")).intValue());
		
	}	 
	}
	
	

	
	
}
