package tile;

import java.awt.EventQueue;

public class BaseTileDesignMain {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaseTileFrame frame = new BaseTileFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
