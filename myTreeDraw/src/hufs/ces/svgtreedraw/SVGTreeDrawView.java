/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.svgtreedraw;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;


public class SVGTreeDrawView extends JFrame {

	final String FILE_SEP = System.getProperty("file.separator");

	private SVGTreeDrawView thisClass = this;
	private SVGTreeDrawModel model = null;
	private SVGTreeDrawController controller = null;

	JPanel contentPane;
	JMenuBar jMenuBar;
	JMenu menuFile;
	JMenuItem jmiOpen;
	JMenuItem jmiExit;
	JMenu menuHelp;
	JMenuItem jmiAbout;
	JSplitPane splitPane;
	JPanel statusLinePanel;
	JTree jtreeView;
	JTextArea jtaDataView;
	JScrollPane jspLeft;
	JScrollPane jspRight;

	JProgressBar progressBar;
	JPanel statusPanel;
	JLabel filePathText;
	JLabel actionStatus;

	JFileChooser jFileChooser1 = null;

	String currDir = "/home/cskim/temp";
	private JMenuItem jmiDraw;
	private JMenuItem jmiClear;


	//ADDED FOR DRAW PANEL
	DrawPanel drawPanel;
	GrimPanModel gmodel = null;

	/**
	 * Create the frame.
	 */
	public SVGTreeDrawView() {
		this.model = new SVGTreeDrawModel(this);
		this.controller = new SVGTreeDrawController(this.model);
		this.gmodel = new GrimPanModel(this);

		setTitle("XML Tree & Content");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);

		jMenuBar = new JMenuBar();
		setJMenuBar(jMenuBar);

		menuFile = new JMenu("File    ");
		jMenuBar.add(menuFile);

		jmiOpen = new JMenuItem("Open ");
		jmiOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openAction();
			}
		});
		menuFile.add(jmiOpen);

		jmiExit = new JMenuItem("Exit  ");
		jmiExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		jmiDraw = new JMenuItem("Draw ");
		jmiDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SVGTreeDrawController.drawState = true;
				controller.drawAction();
			}
		});
		menuFile.add(jmiDraw);

		//ADDDED ACTION LISTENR BY JUNG
		jmiClear = new JMenuItem("Clear ");
		jmiClear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				SVGTreeDrawController.drawState = false;
				controller.clearAction();
			}
		});
		menuFile.add(jmiClear);

		//ADDED ACTIONLISTER BY JUNG
		menuFile.add(jmiExit);
		jmiExit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});

		menuHelp = new JMenu("Help");
		jMenuBar.add(menuHelp);

		jmiAbout = new JMenuItem("About ");
		jmiAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane.showMessageDialog(thisClass,
						"File Explorer Ver0.1.1 \nProgrammed by cskim, cse, hufs.ac.kr", 
						"About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuHelp.add(jmiAbout);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		initialize();
	}

	private void initialize(){		

		splitPane = new JSplitPane();
		splitPane.setDividerLocation(200);
		splitPane.setResizeWeight(0);
		splitPane.setDividerSize(4);
		contentPane.add(splitPane, BorderLayout.CENTER);

		jspLeft = new JScrollPane(jtreeView);

		splitPane.setLeftComponent(jspLeft);

		jtaDataView = new JTextArea();
		//jtaDataView.setPreferredSize(new Dimension(500, 2000));
		jtaDataView.setFont(new Font("Monospaced", Font.PLAIN, 14));
		jspRight = new JScrollPane(jtaDataView);

		splitPane.setRightComponent(jspRight);

		statusLinePanel = new JPanel();
		contentPane.add(statusLinePanel, BorderLayout.SOUTH);
		statusLinePanel.setLayout(new BorderLayout(0, 0));

		progressBar = new JProgressBar();
		progressBar.setBorder(new EmptyBorder(0, 10, 0, 10));
		progressBar.setPreferredSize(new Dimension(200, 20));
		progressBar.setValue(0);
		progressBar.setIndeterminate(false);
		statusLinePanel.add(progressBar, BorderLayout.EAST);

		statusPanel = new JPanel();
		statusPanel.setPreferredSize(new Dimension(50, 20));
		statusLinePanel.add(statusPanel, BorderLayout.CENTER);
		statusPanel.setLayout(new BorderLayout(0, 0));

		filePathText = new JLabel("    File: ");
		filePathText.setFont(new Font("Microsoft Sans Serif", Font.PLAIN, 15));
		filePathText.setHorizontalAlignment(SwingConstants.LEFT);
		statusPanel.add(filePathText);

		actionStatus = new JLabel("      ");
		statusPanel.add(actionStatus, BorderLayout.EAST);

		jFileChooser1 = new JFileChooser(currDir);
		jFileChooser1.setDialogTitle("Open File");
		jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);


	}

	private void openAction(){
		if (jFileChooser1.showOpenDialog(this) ==
				JFileChooser.APPROVE_OPTION) {
			File selFile = jFileChooser1.getSelectedFile();
			String fileName = jFileChooser1.getName(selFile);
			String pathName = selFile.getAbsolutePath();
			filePathText.setText(pathName+FILE_SEP+fileName);

			model.setSelectedFile(selFile);
			if (selFile.isFile()){
				controller.buildXMLTree();

				//controller.readFile2View();
			}
		}
	}
}
