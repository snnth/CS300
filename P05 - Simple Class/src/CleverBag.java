///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           P05 - Clever Bag
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
import java.util.Scanner;

/**
 * A class that creates an array of all words typed in a text file and does other operations on it.
 *
 * @author Matthew Smith
 */
public class CleverBag extends SimpleBag {

	private int size;

	/**
	 * Constructor. Calls parent constructor and initializes size field to zero.
	 *
	 * @param seed seed for Random object
	 */
	public CleverBag(int seed) {
		super(seed);
		size = 0;
	}

	/**
	 * Inserts each new space-seperated word at the end of the oversize class array from the text
	 * contents of the provide file.
	 *
	 * Complexity: O(N)
	 *
	 * @param f File to be parsed
	 */
	@Override
	public void loadData(File f) {
		try {
			Scanner fileScanner = new Scanner(f);
			fileScanner.next(); // removes word count from top of file

			while(fileScanner.hasNext()) {
				String currentWord = fileScanner.next().trim();

				data[size++] = currentWord;
			}
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException in CleverBag");
			return;
		} catch (Exception e) {
			System.out.println("Some other Exception in CleverBag");
			return;
		}
	}

	/**
	 * Removes a random word from the class array and returns it. Returns null if there are no words
	 * in class array.
	 *
	 * Complexity: O(1)
	 *
	 * @return word that was removed, null if no words to remove
	 */
	@Override
	public String removeRandom() {
		if (size == 0)
			return null;

		int randomRemoveNum = random.nextInt(size);
		String returnWord = data[randomRemoveNum];
		data[randomRemoveNum] = data[size - 1];
		data[size - 1] = null;
		size--;

		return returnWord;
	}
}
