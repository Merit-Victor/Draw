package eg.edu.alexu.csd.oop.draw.cs26;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import eg.edu.alexu.csd.oop.draw.Shape;





public class PaintGui {

	JButton RecBtn;
	DrawArea drawArea;
	
	 ShapeImp shape = new Rectangle();
	public static void main(String[] args) {
		new PaintGui().show();

	}

	
	public void show(){
		JFrame frame = new JFrame("swing paint");
		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		
		drawArea = new DrawArea();
		container.add(drawArea,BorderLayout.CENTER);
		
		JPanel controls = new JPanel();
		RecBtn = new JButton("Rectangle");
		 controls.add(RecBtn);
		 container.add(controls, BorderLayout.NORTH);
		 frame.setSize(600, 600);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
			RecBtn.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
			        JTextField mHeight = new JTextField();
			        JTextField mWidth = new JTextField();
			        JPanel panel = new JPanel(new GridLayout(0, 1));
			        panel.add(new JLabel("Height :"));
			        panel.add(mHeight);
			        panel.add(new JLabel("Width :"));
			        panel.add(mWidth);
			        Map<String, Double> properties = new HashMap<String,Double>() ;
			        int result = JOptionPane.showConfirmDialog(null, panel, "input",
			                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			            if (result == JOptionPane.OK_OPTION) {
			           
			               properties.put("height",Double.parseDouble(mHeight.getText().trim()));
			            
			               properties.put("width",  Double.parseDouble(mWidth.getText().trim()));
			            } else {
			                System.out.println("Cancelled");
			            }
			        shape.setProperties(properties);
					shape.draw(drawArea.getG2());
					drawArea.repaint();
				}
			});
	}
}
