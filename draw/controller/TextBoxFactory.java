package eg.edu.alexu.csd.oop.draw.controller;

import java.awt.GridLayout;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eg.edu.alexu.csd.oop.draw.Shape;

/**
 * @author Merit Victor Text box factory.
 */
public class TextBoxFactory {

	/**
	 * Shows the prop. of the given shape.
	 * 
	 * @param shape
	 * @return
	 */
	public static Map<String, Double> showTextBox(Shape shape) {
		JPanel panel = new JPanel(new GridLayout(0, 1));

		Map<String, Double> properties = shape.getProperties();
		Set<String> keys = properties.keySet();
		String keyName = null;
		JTextField textField = null;

		@SuppressWarnings("rawtypes")

		Iterator iter = keys.iterator();
		while (iter.hasNext()) {
			keyName = (String) iter.next();
			textField = new JTextField();
			panel.add(new JLabel(keyName));
			panel.add(textField);
			int result = JOptionPane.showConfirmDialog(null,
					panel, "input", JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);
			if (result == JOptionPane.OK_OPTION) {
				try {
					properties.put(keyName, Double.parseDouble(
							textField.getText().trim()));
				} catch (NumberFormatException e) {
					JOptionPane.showConfirmDialog(null,
					"Invalid input", "error", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.PLAIN_MESSAGE);
					break;
				}
						
			} else {
				break;
			}
		}
		return properties;
	}
}
