/**
 * Created on 2014. 12. 4.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */

package hufs.ces.pdfread;

/**
 * @author cskim
 *
 */
public class WordFreqPair implements Comparable<WordFreqPair> {

	private String word = null;
	private Integer freq = null;
	
	public WordFreqPair(String word, Integer freq){
		this.word = word;
		this.freq = freq;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(WordFreqPair wfp) {
		// TODO Auto-generated method stub
		return wfp.freq - freq;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public Integer getFreq() {
		return freq;
	}
	public void setFreq(Integer freq) {
		this.freq = freq;
	}

}
