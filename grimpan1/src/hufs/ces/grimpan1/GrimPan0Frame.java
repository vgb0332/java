package hufs.ces.grimpan1;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.border.EmptyBorder;

public class GrimPan0Frame extends JFrame {

	private GrimPan0Frame thisClass = this;
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

	/**
	 * Create the frame.
	 */
	public GrimPan0Frame() {
		
		model = new GrimPanModel();
		drawPanel = new DrawPanel(model);

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
		jMenuFile.add(jmiOpen);
		
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
		jrmiLine.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				model.setEditState(GrimPanModel.SHAPE_LINE);
			}
		});
		jrmiLine.setSelected(true);
		jMenuShape.add(jrmiLine);
		
		jrmiPencil = new JRadioButtonMenuItem("Pencil");
		jrmiPencil.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				model.setEditState(GrimPanModel.SHAPE_PENCIL);
			}
		});
		jMenuShape.add(jrmiPencil);
		
		jrmiPolygon = new JRadioButtonMenuItem("Polygon");
		jrmiPolygon.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				model.setEditState(GrimPanModel.SHAPE_POLYGON);
			}
		});
		jMenuShape.add(jrmiPolygon);
		
		btnGroup.add(jrmiLine);
		btnGroup.add(jrmiPencil);
		btnGroup.add(jrmiPolygon);

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
	}
	void clearDrawPanel(){
		model.shapeList.clear();
		model.polygonPoints = new ArrayList<Point>();
		
		drawPanel.repaint();
	}

}
