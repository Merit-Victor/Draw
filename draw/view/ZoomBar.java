package eg.edu.alexu.csd.oop.draw.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import eg.edu.alexu.csd.oop.draw.controller.Controller;
import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Merit Victor
 *
 */
@SuppressWarnings("serial")
public class ZoomBar extends JPanel implements ActionListener, ChangeListener {

	/**
	 * 
	 */
	private JLabel zoomAmount;

	/**
	 * 
	 */
	private JButton minus;

	/**
	 * 
	 */
	private JButton plus;

	/**
	 * 
	 */
	private JSlider slider;

	/**
	 * 
	 */
	private static final int MIN_ZOOM = 10;

	/**
	 * 
	 */
	private static final int MAX_ZOOM = 200;

	/**
	 * 
	 */
	private static final int DEFAULT_ZOOM = 100;

	/**
	 * 
	 */
	private static final int MAJOR_ZOOM_SPACING = 50;

	/**
	 * 
	 */
	private static final int MINOR_ZOOM_SPACING = 10;
	
	/**
	 * 
	 */
	private static final int TEN = 10;

	/**
	 * 
	 */
	private int value;

	/**
	 * 
	 */
	private Controller control;

	/**
	 * 
	 */
	private PaintGui paintGui;

	/**
	 * 
	 */
	private boolean flag = true;

	/**
	 * 
	 */
	boolean flag1 = true;

	/**
	 * @param paintGui
	 */
	public ZoomBar(PaintGui paintGui) {
		super();

		minus = new JButton("-");
		plus = new JButton("+");
		slider = new JSlider(MIN_ZOOM, MAX_ZOOM, DEFAULT_ZOOM);

		slider.setMinorTickSpacing(MINOR_ZOOM_SPACING);
		slider.setMajorTickSpacing(MAJOR_ZOOM_SPACING);
		slider.setPaintTicks(true);
		slider.setSnapToTicks(true);

		zoomAmount = new JLabel(slider.getValue() + "%");

		add(zoomAmount);
		add(minus);
		add(slider);
		add(plus);

		setBackground(new Color(176,216,218));

		plus.addActionListener(this);
		minus.addActionListener(this);
		slider.addChangeListener(this);
		slider.setEnabled(false);
		this.paintGui = paintGui;
		control = new Controller(paintGui);
	}

	public void stateChanged(ChangeEvent e) {

		
		value = slider.getValue();

		zoomAmount.setText(value + "%");
	}
	
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == plus) {
//			if(control.selectedShape == null) {
//				paintGui.feedback.setText("No shape was selected");
//			}

			flag1 = true;
			slider.setValue(slider.getValue() + MINOR_ZOOM_SPACING);
			if (slider.getValue() != 200) {
				this.maximizeSize();
			}
			if (slider.getValue() == 200 && flag == true) {
				this.maximizeSize();
				flag = false;
			}
		} else if (e.getSource() == minus) {
//			if(control.selectedShape == null) {
//				paintGui.feedback.setText("No shape was selected");
//			}

			flag = true;
			slider.setValue(slider.getValue() - MINOR_ZOOM_SPACING);
			if (slider.getValue() != 10) {
				try {
					this.minimizeSize();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (slider.getValue() == 10 && flag1 == true) {
				try {
					this.minimizeSize();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				flag1 = false;
			}
		}

	}

	/**
	 * Max.
	 */
	public void maximizeSize() {
		Set<String> keys;
		String keyName = null;
		if(control.mode == "mSelect") {
			control.freeShape.resize(+TEN);
			control.drawingEngine.addFreeShape(control.freeShape);	
		} else {
			try {
				Shape newShape =  (Shape)control.selectedShape.clone();
				keys = newShape.getProperties().keySet();
				@SuppressWarnings("rawtypes")
				Iterator iter = keys.iterator();
				while (iter.hasNext()) {
					keyName = (String) iter.next();
		
					newShape.getProperties().put(keyName, 
							newShape.getProperties().get(keyName) 
							+ TEN);
				}
				control.drawingEngine.updateShape(control.selectedShape, newShape);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
		}
		paintGui.drawArea.clear();
		control.drawingEngine.refresh(paintGui.drawArea.getG2());
		paintGui.drawArea.repaint();
	}

	/**
	 * Mini.
	 * @throws CloneNotSupportedException 
	 */
	public void minimizeSize() throws CloneNotSupportedException {
		Set<String> keys;
		String keyName = null;
	
		if(control.mode == "mSelect") {
			control.freeShape.resize(-TEN);
			control.drawingEngine.addFreeShape(control.freeShape);
		} else {
			try {
				Shape newShape =  (Shape)control.selectedShape.clone();
				keys = newShape.getProperties().keySet();
				@SuppressWarnings("rawtypes")
				Iterator iter = keys.iterator();
				while (iter.hasNext()) {
					keyName = (String) iter.next();
		
					newShape.getProperties().put(keyName, 
							newShape.getProperties().get(keyName) 
							- TEN);
				}
				control.drawingEngine.updateShape(control.selectedShape, newShape);
			} catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}
	}
		paintGui.drawArea.clear();
		control.drawingEngine.refresh(paintGui.drawArea.getG2());
		paintGui.drawArea.repaint();
  }
}