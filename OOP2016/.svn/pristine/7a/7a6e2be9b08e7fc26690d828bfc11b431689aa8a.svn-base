package hufs.ces.card;

import javax.swing.ImageIcon;

public class CardImages {

	private ImageIcon[] cardImage = null;
	private ImageIcon backImage = null;  //  @jve:decl-index=0:

	private volatile static CardImages uniqueInstance;  //  @jve:decl-index=0:

	private CardImages(){
		initialize();
	}

	public static CardImages getInstance() {
		if (uniqueInstance == null) {
			synchronized (CardImages.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new CardImages();
				}
			}
		}
		return uniqueInstance;
	}
	private void initialize(){
		cardImage = new ImageIcon[] {
				new ImageIcon(getClass().getResource("/cardsimage/c1.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c2.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c3.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c4.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c5.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c6.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c7.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c8.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c9.png")),
				new ImageIcon(getClass().getResource("/cardsimage/c10.png")),
				new ImageIcon(getClass().getResource("/cardsimage/cj.png")),
				new ImageIcon(getClass().getResource("/cardsimage/cq.png")),
				new ImageIcon(getClass().getResource("/cardsimage/ck.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d1.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d2.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d3.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d4.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d5.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d6.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d7.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d8.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d9.png")),
				new ImageIcon(getClass().getResource("/cardsimage/d10.png")),
				new ImageIcon(getClass().getResource("/cardsimage/dj.png")),
				new ImageIcon(getClass().getResource("/cardsimage/dq.png")),
				new ImageIcon(getClass().getResource("/cardsimage/dk.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h1.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h2.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h3.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h4.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h5.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h6.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h7.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h8.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h9.png")),
				new ImageIcon(getClass().getResource("/cardsimage/h10.png")),
				new ImageIcon(getClass().getResource("/cardsimage/hj.png")),
				new ImageIcon(getClass().getResource("/cardsimage/hq.png")),
				new ImageIcon(getClass().getResource("/cardsimage/hk.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s1.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s2.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s3.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s4.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s5.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s6.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s7.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s8.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s9.png")),
				new ImageIcon(getClass().getResource("/cardsimage/s10.png")),
				new ImageIcon(getClass().getResource("/cardsimage/sj.png")),
				new ImageIcon(getClass().getResource("/cardsimage/sq.png")),
				new ImageIcon(getClass().getResource("/cardsimage/sk.png")),
		};
		backImage = new ImageIcon(getClass().getResource("/cardsimage/b1fv.png"));
	}
	ImageIcon getBackImage(){
		return backImage;
	}
	ImageIcon getCardImage(int suit, int rank){
		return cardImage[13*suit+rank-1];
	}
}
