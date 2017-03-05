/**
 * Created on 2014. 11. 25.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */
package hufs.ces.dom2tree;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JFileChooser;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Dom2TreeView extends JFrame {

	private Dom2TreeView thisClass = this;
	private Dom2TreeModel model = null;
	private Dom2TreeController controller = null;
	String currDir = "c:/home/cskim/temp";

	private JPanel contentPane;
	private JMenuBar jMenuBar;
	private JMenu menuFile;
	private JMenuItem jmiOpen;
	private JMenuItem jmiExit;
	private JMenu menuHelp;
	private JMenuItem jmiAbout;
	private JSplitPane splitPane;
	private JPanel statusLinePanel;
	JTree jtreeView;
	JScrollPane jspLeft;
	JScrollPane jspRight;

	private JProgressBar progressBar;
	private JPanel statusPanel;
	private JLabel filePathText;
	private JLabel actionStatus;
	
	private JFileChooser jFileChooser1 = null;
	
	private String defaultDir = "C:/home/cskim/temp";
	private final String FILE_SEP = System.getProperty("file.separator");

	public JTextArea jtaDataView;
	/**
	 * Create the frame.
	 */
	public Dom2TreeView() {
		this.model = new Dom2TreeModel(this);
		this.controller = new Dom2TreeController(this.model);
				
		initialize();
	}
	
	private void initialize(){		
		
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
		menuFile.add(jmiExit);
		
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
		splitPane = new JSplitPane();
		splitPane.setDividerLocation(200);
		splitPane.setResizeWeight(0.5);
		splitPane.setDividerSize(4);
		contentPane.add(splitPane, BorderLayout.CENTER);
		
		jtreeView = new JTree(new DefaultMutableTreeNode("root"));
		//jtreeView = new JTree();
		//jtreeView.setPreferredSize(new Dimension(500, 800));
		jspLeft = new JScrollPane(jtreeView);

		jtaDataView = new JTextArea();
		//jtaDataView.setPreferredSize(new Dimension(500, 800));
		jtaDataView.setFont(new Font("Monospaced", Font.BOLD, 18));
		jspRight = new JScrollPane(jtaDataView);

		splitPane.setLeftComponent(jspLeft);

		splitPane.setRightComponent(jspRight);

		statusLinePanel = new JPanel();
		contentPane.add(statusLinePanel, BorderLayout.SOUTH);
		statusLinePanel.setLayout(new BorderLayout(0, 0));
		
		progressBar = new JProgressBar();
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

		jFileChooser1 = new JFileChooser(defaultDir);
		jFileChooser1.setDialogTitle("Open File");
		jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
		
		
	}

	private void openAction(){
		jFileChooser1.setCurrentDirectory(new File(currDir));

		if (jFileChooser1.showOpenDialog(this) ==
				JFileChooser.APPROVE_OPTION) {
			File selFile = jFileChooser1.getSelectedFile();
			String fileName = jFileChooser1.getName(selFile);
			String pathName = selFile.getAbsolutePath();
			currDir = pathName;
			filePathText.setText(pathName+FILE_SEP+fileName);

			model.setSelectedFile(selFile);
			if (selFile.isFile()){
				
				controller.buildXMLTree();
				
				controller.readFile2View();
			}
		}
	}
}
