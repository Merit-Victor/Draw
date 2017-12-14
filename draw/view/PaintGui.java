package eg.edu.alexu.csd.oop.draw.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;
import eg.edu.alexu.csd.oop.draw.controller.Controller;
import eg.edu.alexu.csd.oop.draw.controller.PopupActionListener;
import eg.edu.alexu.csd.oop.draw.cs68.DrawingEngineImp;

/**
 * @author Merit Victor
 *
 */
public class PaintGui {

	/**
	 * 
	 */
	private JFrame frame;
	
	/**
	 * 
	 */
	private static final int ZERO = 0;
	
	/**
	 * 
	 */
	private static final int FORTY = 40;
	
	/**
	 * 
	 */
	private static final int SIXTY = 60;

	/**
	 * 
	 */
	private static final int SEVEN_HUNDRED = 700;

	/**
	 * 
	 */
	private static final int ONE_HUNDRED_AND_TEN = 110;
	
	/**
	 * 
	 */
	private static final int THIRTY = 30;
	
	/**
	 * 
	 */
	private static final int TWELVE = 12;
	
	/**
	 * 
	 */
	private static final int TWENTY_EIGHT = 28;
	
	/**
	 * 
	 */
	private static final int TWENTY_ONE = 21;
	
	/**
	 * 
	 */
	private static final int TEN = 10;
	
	/**
	 * 
	 */
	private static final int TWENTY_FIVE = 25;
	
	/**
	 * 
	 */
	private static final int THREE = 3;
	/**
	 * 
	 */
	private static final int THIRTY_FIVE = 35;
	/**
	 * 
	 */
	private static final int MAX_NOM_CLASSES = 6;
	
	/**
	 * 
	 */
	protected static final 
	String NOT_SELECTABLE_OPTION = "Shapes";
	
	/**
	 * 
	 */
	protected String[] shapeNames;
	
	/**
	 * 
	 */
	private DrawingEngine drawingEngine = 
			new DrawingEngineImp();
	
	/**
	 * 
	 */
	private List<Class<? extends Shape>> 
	supportedShapes = new ArrayList<Class<? extends Shape>>();

	/**
	 * 
	 */
	public ActionListener actionListener;
	
	/**
	 * 
	 */
	public ActionListener selectOperationsListener;
	
	/**
	 * 
	 */
	public MouseListener mouseListener;
	
	/**
	 * 
	 */
	public MouseMotionListener mouseMotionListener;
	
	/**
	 * 
	 */
	public JComboBox<String> shapeMenu;
	
	/**
	 * 
	 */
	public JComboBox<String> modeOptions;

	/**
	 * 
	 */
	public JButton ClearBtn;
	
	/**
	 * 
	 */
	public JButton openPlugin;
	
	/**
	 * 
	 */
	public JButton save;
	
	/**
	 * 
	 */
	public JButton load;
	
	/**
	 * 
	 */
	public JButton resize;
	
	/**
	 * 
	 */
	public JButton move;
	
	/**
	 * 
	 */
	public JButton delete;
	
	/**
	 * 
	 */
	public JButton changeOutlineColor;
	
	/**
	 * 
	 */
	public JButton changeFillColor;
	
	/**
	 * 
	 */
	public JButton duplicate;
	
	/**
	 * 
	 */
	public DrawArea drawArea;

	/**
	 * 
	 */
	public JButton ColorBtn;
	
	/**
	 * 
	 */
	public JButton BrushBtn;
	
	/**
	 * 
	 */
	public JButton undoBtn;
	
	/**
	 * 
	 */
	public JButton redoBtn;

	/**
	 * 
	 */
	public JPopupMenu saveOption;
	
	/**
	 * 
	 */
	public JLabel feedback;

	/**
	 * 
	 */
	Box selectOptions = Box.createVerticalBox();
	
	/**
	 * 
	 */
	List<JButton> optionsBtns = new ArrayList<>();
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new PaintGui().show();
	}

	/**
	 * 
	 */
	public Controller controller;

	/**
	 * @param controller
	 */
	public void AddListener(Controller controller) {
		this.controller = controller;
	}

	/**
	 * 
	 */
	public PaintGui() {
		controller = new Controller(this);
		drawArea = new DrawArea();

		this.refreshDropList();
		modeOptions = new JComboBox<>();
		modeOptions.addItem("draw");
		modeOptions.addItem("select");
		modeOptions.addItem("mSelect");
		shapeMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.actionOnShapesList();
				} catch (InstantiationException 
						| IllegalAccessException e1) {
					e1.printStackTrace();
				}
			}
		});

		modeOptions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.actionOnModesList();
			}
		});

		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.actionOnButtons(e);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		};

		selectOperationsListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					controller.actionOnSelectOptions(e);
				} catch (InstantiationException e1) {
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
			}
		};
	}

	/**
	 * 
	 */
	public void refreshDropList() {
		shapeNames = new String[MAX_NOM_CLASSES];
		supportedShapes = drawingEngine.getSupportedShapes();
		for (int i = ZERO; i < supportedShapes.size(); i++) {
			shapeNames[i] = supportedShapes.get(i).getSimpleName();
		}
		shapeMenu = new JComboBox<>(shapeNames);
	}

	/**
	 * 
	 */
	public void show() {
		frame = new JFrame("Draw");

		Container container = frame.getContentPane();
		container.setLayout(new BorderLayout());
		container.setBackground(new Color(176,216,218));

		JPanel controls = new JPanel();
		Box labelAndZoomBar = Box.createHorizontalBox();

		saveOption = new JPopupMenu();
		ActionListener popActionListener =
				new PopupActionListener(this);
		JMenuItem jsonMenuItem = new JMenuItem("json");
		jsonMenuItem.addActionListener(popActionListener);
		saveOption.add(jsonMenuItem);
		JMenuItem xmlMenuItem = new JMenuItem("XML");
		xmlMenuItem.addActionListener(popActionListener);
		saveOption.add(xmlMenuItem);

		setupComponents();
		fillOptionsList();
		addListenerToComponents();
		controls = addComponentsToPanel(controls);

		labelAndZoomBar.add(feedback);
		labelAndZoomBar.add(new ZoomBar(this));

		fillBox();
		enableBoxButtons(false);

		controls.setBackground(new Color(176,216,218));
		container.add(controls, BorderLayout.NORTH);
		container.add(selectOptions, BorderLayout.EAST);
		container.add(drawArea, BorderLayout.CENTER);
		container.add(labelAndZoomBar, BorderLayout.SOUTH);

		drawArea.addMouseListener(mouseListener);
		drawArea.addMouseMotionListener(mouseMotionListener);
		
		addImages();

		Dimension size = new Dimension(ONE_HUNDRED_AND_TEN, FORTY);
		shapeMenu.setPreferredSize(size);
		size = new Dimension(SIXTY, FORTY);
		modeOptions.setPreferredSize(size);
		frame.setSize(SEVEN_HUNDRED, SEVEN_HUNDRED);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * 
	 */
	private void addImages() {
		ImageIcon frameIcon = new ImageIcon(
				"C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/frame.png");
		frame.setIconImage(frameIcon.getImage());

		ImageIcon icon = new ImageIcon(
				"C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/undo.png");
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(THIRTY_FIVE,
				THIRTY_FIVE, Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		undoBtn.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/redo.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(THIRTY_FIVE, THIRTY,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		redoBtn.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/delete.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(THIRTY, THIRTY_FIVE, 
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		delete.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/duplicate.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(TWELVE, THIRTY_FIVE,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		duplicate.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/move.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(THIRTY_FIVE, THIRTY_FIVE,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		move.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/resize.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(TWENTY_EIGHT, THIRTY_FIVE,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		resize.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/brush.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(TWENTY_ONE, THIRTY_FIVE,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		BrushBtn.setIcon(icon);
		changeFillColor.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/out.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(TWENTY_FIVE, THIRTY_FIVE, 
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		ColorBtn.setIcon(icon);
		changeOutlineColor.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/clear.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(TWENTY_FIVE, THIRTY_FIVE, 
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		ClearBtn.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/save.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(TWENTY_FIVE, THIRTY_FIVE,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		save.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/load.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(TWENTY_FIVE, THIRTY_FIVE,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		load.setIcon(icon);

		icon = new ImageIcon("C:/Users/HP/git/oop/oop/src/eg/edu/alexu/csd/oop/draw/view/images/open.png");
		img = icon.getImage();
		newimg = img.getScaledInstance(TEN, THIRTY_FIVE,
				Image.SCALE_DEFAULT);
		icon = new ImageIcon(newimg);
		openPlugin.setIcon(icon);

	}

	/**
	 * @return string.
	 */
	public String showFileOpenChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(
				new File(System.getProperty("user.home")));
		fileChooser.setDialogTitle("Open Plugin");
		int result = fileChooser.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			return selectedFile.getAbsolutePath();
		}
		return null;
	}

	/**
	 * @return string.
	 */
	public String showFileSaveChooser() {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(frame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			File fileToSave = fileChooser.getSelectedFile();
			return fileToSave.getAbsolutePath();
		}

		return null;
	}

	/**
	 * @param 
	 * 			bool.
	 */
	public void enableBoxButtons(boolean bool) {
		for (JButton btn : optionsBtns) {
			btn.setEnabled(bool);
		}
	}

	/**
	 * Setup.
	 */
	public void setupComponents() {
		ClearBtn = new JButton("Clear");
		openPlugin = new JButton("Open Plugin");
		ColorBtn = new JButton("Color");
		BrushBtn = new JButton("Brush");
		undoBtn = new JButton("Undo");
		redoBtn = new JButton("Redo");
		save = new JButton("save");
		load = new JButton("load");
		resize = new JButton("Resize");
		move = new JButton("Move");
		changeFillColor = new JButton("FillColor");
		changeOutlineColor = new JButton("Outline");
		duplicate = new JButton("Duplicate");
		delete = new JButton("Delete");
		feedback = new JLabel("Welcome");
	}

	/**
	 * Fill.
	 */
	public void fillOptionsList() {
		optionsBtns.add(delete);
		optionsBtns.add(duplicate);
		optionsBtns.add(move);
		optionsBtns.add(resize);
		optionsBtns.add(changeFillColor);
		optionsBtns.add(changeOutlineColor);
	}

	/**
	 * 
	 */
	public void fillBox() {
		selectOptions.add(openPlugin);
		selectOptions.add(Box.createVerticalStrut(20));
		selectOptions.add(undoBtn);
		selectOptions.add(Box.createVerticalStrut(20));
		selectOptions.add(redoBtn);
		for (JButton btn : optionsBtns) {
			selectOptions.add(Box.createVerticalStrut(20));
			selectOptions.add(btn);
		}
	}

	/**
	 * Add.
	 */
	public void addListenerToComponents() {
		openPlugin.addActionListener(actionListener);
		ClearBtn.addActionListener(actionListener);
		ColorBtn.addActionListener(actionListener);
		BrushBtn.addActionListener(actionListener);
		undoBtn.addActionListener(actionListener);
		redoBtn.addActionListener(actionListener);
		save.addActionListener(actionListener);
		load.addActionListener(actionListener);
		resize.addActionListener(selectOperationsListener);
		move.addActionListener(selectOperationsListener);
		changeFillColor.addActionListener(selectOperationsListener);
		changeOutlineColor.addActionListener(selectOperationsListener);
		duplicate.addActionListener(selectOperationsListener);
		delete.addActionListener(selectOperationsListener);
	}

	/**
	 * Add.
	 * 
	 * @param controls
	 * 					given.
	 * @return jpanel.
	 */
	public JPanel addComponentsToPanel(JPanel controls) {
		controls.add(shapeMenu);
		controls.add(Box.createHorizontalStrut(THREE));
		controls.add(modeOptions);
		controls.add(ColorBtn);
		controls.add(ClearBtn);
		controls.add(BrushBtn);
		controls.add(save);
		controls.add(Box.createHorizontalStrut(THREE));
		controls.add(load);
		return controls;
	}
}
