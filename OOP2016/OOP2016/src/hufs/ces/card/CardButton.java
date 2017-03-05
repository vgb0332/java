package hufs.ces.card;

import javax.swing.JButton;

public class CardButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = -17242697750013594L;

	private Card card = null;
	
	public CardButton(){
		card = new Card();
		this.setIcon(card.getImage());
	}
	public CardButton(int suit, int rank){
		card = new Card(suit, rank);
		this.setIcon(card.getImage());
	}
	public CardButton(int suit, int rank, boolean open){
		card = new Card(suit, rank, open);
		this.setIcon(card.getImage());
	}
	public CardButton(Card card){
		this.card = card;
		this.setIcon(card.getImage());
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
		this.setIcon(card.getImage());
	}
	public void setCardOpen(boolean open){
		this.card.setOpen(open);
		this.setIcon(card.getImage());
	}
	public boolean isCardOpen(){
		return this.card.isOpen();
	}
}
