package hufs.ces.svg.svggen;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.apache.batik.svggen.SVGGraphics2DIOException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.awt.event.ActionEvent;

public class SVGGenFrame extends JFrame {

	final String FILE_SEP = System.getProperty("file.separator");
	String currDir = "/home/cskim/temp";

	JFileChooser jFileChooser1 = null;

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem mntmNewMenuItem;
	private JMenu mnNewMenu;
	private JMenuItem mntmExit;

	AppleClock drawPanel;
	/**
	 * Create the frame.
	 */
	public SVGGenFrame() {
		initialize();
	}

	void initialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 50, 800, 800);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("File  ");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Save SVG");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveSVGAction();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		mntmExit = new JMenuItem("Exit  ");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu.add(mntmExit);
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		jFileChooser1 = new JFileChooser(currDir);
		jFileChooser1.setDialogTitle("Save SVG");
		jFileChooser1.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		drawPanel = new AppleClock();
		contentPane.add(drawPanel, BorderLayout.CENTER);
		invalidate();

	}


	void saveSVGAction(){

		if (jFileChooser1.showSaveDialog(this) ==
				JFileChooser.APPROVE_OPTION) {
			File selFile = jFileChooser1.getSelectedFile();

			Writer svgOut = null;

			DOMImplementation domImpl = GenericDOMImplementation.getDOMImplementation();

			// Create an instance of org.w3c.dom.Document.
			String svgNS = "http://www.w3.org/2000/svg";
			Document document = domImpl.createDocument(svgNS, "svg", null);

			// Create an instance of the SVG Generator.
			SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

			drawPanel.paintPanel(svgGenerator);
			boolean useCSS = false;

			try {
				svgOut = new OutputStreamWriter(new FileOutputStream(selFile));
				svgGenerator.stream(svgOut, useCSS);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (SVGGraphics2DIOException e) {
				e.printStackTrace();
			}

		}

	}
}
