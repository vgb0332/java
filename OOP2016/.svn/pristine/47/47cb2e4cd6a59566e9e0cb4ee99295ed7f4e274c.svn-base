/**
 * Created on 2014. 12. 4.
 * @author cskim -- hufs.ac.kr, Dept of CSE
 * Copy Right -- Free for Educational Purpose
 */

package hufs.ces.pdfread;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author cskim
 *
 */
public class WordExtractTest {

	static final String INPUT_TEXT	= "result/TextBook.txt";
	static final String FILTER_TEXT = "data/stopwords_en.txt";
	static HashMap<String, Integer> wordFreqMap = new HashMap<String, Integer>();
	static ArrayList<WordFreqPair> wfList = new ArrayList<WordFreqPair>();
	static Set<String> filterSet = new HashSet<String>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {


		Scanner input = null;
		// Read all Text and Build keyword-frequency hash map
		try {
			input = new Scanner(new File(INPUT_TEXT));

			while (input.hasNext()){
				String textLine = input.nextLine();

				String[] words = textLine.split("(\\s|\\p{Punct})+");

				for (int i=0; i<words.length; ++i){
					//System.out.println(words[i]);
					if (words[i].matches("\\p{Alpha}+")){
						String lword = words[i].toLowerCase();
						//System.out.println(lword);
						Integer freq = wordFreqMap.get(lword);
						if (freq==null || freq.intValue()==0){
							wordFreqMap.put(lword, new Integer(1));
							//System.out.println(lword);
						}
						else {
							freq++;
							wordFreqMap.put(lword, freq);
						}
					}
				}

			}
			input.close();


		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Build Filter Set
		try {
			input = new Scanner(new File(FILTER_TEXT));
			while (input.hasNext()){
				String textLine = input.nextLine().trim();
				filterSet.add(textLine);
				
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println("filter size="+filterSet.size());
		input.close();

		// sort keyword by frequency
		Set<String> keys = wordFreqMap.keySet();
		for (String k:keys){
			//System.out.println(k+"--"+wordFreqMap.get(k));
			if (!filterSet.contains(k)){
				wfList.add(new WordFreqPair(k, wordFreqMap.get(k)));
			}
		}
		
		/*
		Object[] wfArray = wfList.toArray();
		Arrays.sort(wfArray);
		for (Object obj:wfArray){
			WordFreqPair wf = (WordFreqPair)obj;
			System.out.println(wf.getFreq()+"--"+wf.getWord());
			//System.out.println(wf.getWord());
		}
		*/
		
		Collections.sort(wfList);
		for (WordFreqPair wf:wfList){
			System.out.println(wf.getFreq()+"--"+wf.getWord());
			//System.out.println(wf.getWord());
		}


	}

}
