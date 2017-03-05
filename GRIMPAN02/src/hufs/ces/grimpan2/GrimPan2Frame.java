package hufs.ces.grimpan2;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class GrimPan2Frame extends JFrame {

	private String defaultDir = "C:/Temp/";
	private final FileNameExtensionFilter grimFileModelFilter = 
			new FileNameExtensionFilter("Grim Files", "grm");
	private JFileChooser jFileChooser1 = null;
	private JFileChooser jFileChooser2 = null;
	
	private GrimPan2Frame thisClass = this;
	private GrimPanModel model = null;
	private DrawPanel drawPanel;
	
	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu jMenuFile;
	private JMenu jMenuShape;
	private JMenu jMenuSetting;
	private JMenu jMenuHelp;
	private JMenuItem jmiItem;
	private JMenuItem jmiNew;
	private JMenuItem jmiOpen;
	private JMenuItem jmiExit;
	private JRadioButtonMenuItem jrmiLine;
	private JRadioButtonMenuItem jrmiPencil;
	private JRadioButtonMenuItem jrmiPolygon;
	private JMenuItem jmiLineWidth;
	private JMenuItem jmiLineColor;
	private JMenuItem jmiFillColor;
	private JCheckBoxMenuItem jcmiFill;

	private ButtonGroup btnGroup = new ButtonGroup();
	private JMenuItem jmiSave;
	private JMenuItem jmiSaveAs;
	private JMenu jMenuEdit;
	private JMenuItem jmiMove;
	private JMenuItem jmiDelete;
	private JRadioButtonMenuItem jrmiRegular;
	private JRadioButtonMenuItem jrmiOval;

	/**
	 * Create the frame.
	 */
	public GrimPan2Frame() {
		
		model = new GrimPanModel();
		drawPanel = new DrawPanel(model);
		drawPanel.setBorder(null);

		initialize();
		
		contentPane.add(drawPanel, BorderLayout.CENTER);


	}
	
	void initialize(){
		setTitle("그림판");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 800, 600);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		jMenuFile = new JMenu("File  ");
		menuBar.add(jMenuFile);
		
		jmiNew = new JMenuItem("New ");
		jmiNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearDrawPanel();
			}
		});
		jMenuFile.add(jmiNew);
		
		jmiOpen = new JMenuItem("Open ");
		jmiOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openAction();
			}
		});
		jMenuFile.add(jmiOpen);
		
		jmiSave = new JMenuItem("Save  ");
		jmiSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAction();
			}
		});
		jMenuFile.add(jmiSave);
		
		jmiSaveAs = new JMenuItem("Save As ...");
		jmiSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveAsAction();
			}
		});
		jMenuFile.add(jmiSaveAs);
		
		jMenuFile.addSeparator();
		
		jmiExit = new JMenuItem("Exit ");
		jmiExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		jMenuFile.add(jmiExit);
		
		jMenuShape = new JMenu("Shape ");
		menuBar.add(jMenuShape);
		
		jrmiLine = new JRadioButtonMenuItem("Line ");
		jrmiLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setEditState(GrimPanModel.SHAPE_LINE);
			}
		});
		jrmiLine.setSelected(true);
		jMenuShape.add(jrmiLine);
		
		jrmiPencil = new JRadioButtonMenuItem("Pencil");
		jrmiPencil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setEditState(GrimPanModel.SHAPE_PENCIL);
			}
		});
		jMenuShape.add(jrmiPencil);
		
		jrmiPolygon = new JRadioButtonMenuItem("Polygon");
		jrmiPolygon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setEditState(GrimPanModel.SHAPE_POLYGON);
			}
		});
		jMenuShape.add(jrmiPolygon);
		
		jrmiRegular = new JRadioButtonMenuItem("Regular");
		jrmiRegular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setEditState(GrimPanModel.SHAPE_REGULAR);
				
				Object[] possibleValues = { 
						"3", "4", "5", "6", "7",
						"8", "9", "10", "11", "12"
				};
				Object selectedValue = JOptionPane.showInputDialog(thisClass,
						"Choose one", "Input",
						JOptionPane.INFORMATION_MESSAGE, null,
						possibleValues, possibleValues[0]);
				if (selectedValue!=null){
					model.setNPolygon(Integer.parseInt((String)selectedValue));
				}
				drawPanel.repaint();
			}
		});
		jMenuShape.add(jrmiRegular);
		
		jrmiOval = new JRadioButtonMenuItem("Oval");
		jrmiOval.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				model.setEditState(GrimPanModel.SHAPE_OVAL);
			}
		});
		jMenuShape.add(jrmiOval);
				
		btnGroup.add(jrmiLine);
		btnGroup.add(jrmiPencil);
		btnGroup.add(jrmiPolygon);
		btnGroup.add(jrmiRegular);
		btnGroup.add(jrmiOval);		

		jMenuEdit = new JMenu("Edit  ");
		menuBar.add(jMenuEdit);
		
		jmiMove = new JMenuItem("Move ");
		jmiMove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				moveAction();
			}
		});
		jMenuEdit.add(jmiMove);
		
		jmiDelete = new JMenuItem("Delete ");
		jmiDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteAction();
			}
		});
		jMenuEdit.add(jmiDelete);

		jMenuSetting = new JMenu("Setting ");
		menuBar.add(jMenuSetting);
		
		jmiLineWidth = new JMenuItem("Line Width");
		jmiLineWidth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String inputVal = JOptionPane.showInputDialog(thisClass, "선두께", "1");
				if (inputVal!=null){
					model.setShapeStroke(new BasicStroke(Integer.parseInt(inputVal)));
					drawPanel.repaint();
				}
			}
		});
		jMenuSetting.add(jmiLineWidth);
		
		jmiLineColor = new JMenuItem("Line Color");
		jmiLineColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = 
						JColorChooser.showDialog(thisClass, 
								"Choose a color",
								Color.black);					
					model.setStrokeColor(color);
					drawPanel.repaint();
			}
		});
		jMenuSetting.add(jmiLineColor);
		
		jmiFillColor = new JMenuItem("Fill Color");
		jmiFillColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = 
						JColorChooser.showDialog(thisClass, 
								"Choose a color",
								Color.black);					
					model.setFillColor(color);
					drawPanel.repaint();
			}
		});
		jMenuSetting.add(jmiFillColor);
		
		jcmiFill = new JCheckBoxMenuItem("Fill  ");
		jcmiFill.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				boolean fillState = jcmiFill.getState();
				model.setShapeFill(fillState);
				drawPanel.repaint();
			}
		});
		jMenuSetting.add(jcmiFill);
		
		jMenuHelp = new JMenu("Help  ");
		menuBar.add(jMenuHelp);
		
		jmiItem = new JMenuItem("About");
		jmiItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(thisClass,
						"GrimPan Ver0.0.1 \nProgrammed by cskim, ces, hufs.ac.kr", 
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		jMenuHelp.add(jmiItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jFileChooser1 = new JFileChooser(defaultDir);
		jFileChooser1.setDialogTitle("Open Saved GrimPan");
		jFileChooser1.setFileFilter(grimFileModelFilter);

		jFileChooser2 = new JFileChooser(defaultDir);
		jFileChooser2.setDialogType(JFileChooser.SAVE_DIALOG);
		jFileChooser2.setDialogTitle("Save As ...");
		jFileChooser2.setFileFilter(grimFileModelFilter);
	
	}
	void clearDrawPanel(){
		model.shapeList.clear();
		model.polygonPoints = new ArrayList<Point>();
		
		drawPanel.repaint();
	}
	void openAction(){
		if (jFileChooser1.showOpenDialog(this) ==
				JFileChooser.APPROVE_OPTION) {
				File selFile = jFileChooser1.getSelectedFile();
				readShapeFromSaveFile(selFile);
				model.setSaveFile(selFile);
				setTitle("그림판 - "+selFile.getName());
				drawPanel.repaint();
			}
	}
	void saveAction(){
		if (model.getSaveFile()==null){
			model.setSaveFile(new File(defaultDir+"noname.grm"));
			setTitle("그림판 - "+defaultDir+"noname.grm");
		}
		File selFile = model.getSaveFile();
		saveGrimPanData(selFile);	
	}
	void saveAsAction(){
		if (jFileChooser2.showSaveDialog(this) ==
				JFileChooser.APPROVE_OPTION) {
				File selFile = jFileChooser2.getSelectedFile();
				model.setSaveFile(selFile);
				setTitle("그림판 - "+selFile.getName());
				saveGrimPanData(selFile);
			}
	}
	void readShapeFromSaveFile(File saveFile) {
		model.setSaveFile(saveFile);
		try {
			ObjectInputStream input =
				new ObjectInputStream(new FileInputStream(model.getSaveFile()));
			model.shapeList = (ArrayList<GrimShape>) input.readObject();
			input.close();

		} catch (ClassNotFoundException e) {
			System.err.println("Class not Found");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void saveGrimPanData(File saveFile){
		ObjectOutputStream output;
		try {
			output = new ObjectOutputStream(new FileOutputStream(saveFile));
			output.writeObject(model.shapeList);
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void moveAction(){
		model.setEditState(GrimPanModel.EDIT_MOVE);
		if (model.curDrawShape != null){
			model.shapeList
			.add(new GrimShape(model.curDrawShape, 
					model.getShapeStroke().getLineWidth(),	
					model.getStrokeColor(), 
					model.getFillColor(), model.isShapeFill()));
			model.curDrawShape = null;
		}
		drawPanel.repaint();
	}
	void deleteAction(){
		
	}
}
