package eg.edu.alexu.csd.oop.draw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import eg.edu.alexu.csd.oop.draw.view.PaintGui;

/**
 * @author Merit Victor
 * Class.
 */
public class PopupActionListener implements ActionListener {
	
	/**
	 * 
	 */
	private String fileSavePath = "";

	/**
	 * 
	 */
	PaintGui paintGui = new PaintGui();

	/**
	 * @param paint
	 */
	public PopupActionListener(PaintGui paint) {
		this.paintGui = paint;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.
	 * ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent actionEvent) {
		if (actionEvent.getActionCommand().equals("json")) {
			fileSavePath = paintGui.showFileOpenChooser();
			if(fileSavePath != null) {
				paintGui.controller.drawingEngine.save(
						fileSavePath + ".json");	
			}
		} else if (actionEvent.getActionCommand().equals("XML")) {
			fileSavePath = paintGui.showFileOpenChooser();
			if(fileSavePath != null) {
				System.out.println(fileSavePath);
				paintGui.controller.drawingEngine.save(
						fileSavePath + ".xml");	
			}
			
		}
	}
}
