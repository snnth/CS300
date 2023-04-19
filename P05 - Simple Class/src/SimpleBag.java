///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           P05 - Simple Bag
// Course:          CS 300, Spring 2021
//
// Author:          Matthew Smith
// Email:           mjsmith44@wisc.edu
// Lecturer's Name: Hobbes Legault
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Persons:         None
// Online Sources:  None
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * A class that creates an array of all words typed in a text file and does other operations on it.
 *
 * @author Matthew Smith
 */
public class SimpleBag {

	protected String[] data;
	protected Random random;

	/**
	 * Constructor. Initializes large array of strings and creates a Random object with specified
	 * seed.
	 *
	 * @param seed seed for Random object
	 */
	public SimpleBag(int seed) {
		data = new String[80000];
		random = new Random(seed);
	}

	/**
	 * Inserts each new space-seperated word into the beginning of the class array from the text
	 * contents of the provide file and shifts current contents right by one.
	 *
	 * Complexity: O(N^2)
	 *
	 * @param f File to be parsed
	 */
	public void loadData(File f) {
		try {
			Scanner fileScanner = new Scanner(f);
			fileScanner.next(); // removes word count from top of file

			while(fileScanner.hasNext()) {
				String currentWord = fileScanner.next().trim();

				for (int i = data.length - 1; i >= 0; i--) {
					if (data[i] == null)
						continue;

					data[i + 1] = data[i];
				}
				data[0] = currentWord;
			}

			fileScanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException in SimpleBag");
			return;
		} catch (Exception e) {
			System.out.println("Some other Exception in SimpleBag");
			return;
		}
	}

	/**
	 * Removes a random word from the class array and returns it. Returns null if there are no words
	 * in class array.
	 *
	 * Complexity: O(N)
	 *
	 * @return word that was removed, null if no words to remove
	 */
	public String removeRandom() {
		int wordCount = 0;
		for (int i = 0; i < data.length; i++) {
			if (data[i] != null)
				wordCount++;
		}

		if (wordCount == 0)
			return null;

		int randomRemoveNum = random.nextInt(wordCount);
		String returnWord = data[randomRemoveNum];
		data[randomRemoveNum] = null;

		for (int i = randomRemoveNum; i < data.length; i++) {
			if (i == data.length - 1) {
				data[i] = null;
				return returnWord;
			}

			data[i] = data[i + 1];
		}

		return returnWord;
	}

}
