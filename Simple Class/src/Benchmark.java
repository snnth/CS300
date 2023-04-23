///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           P05 - Benchmark
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
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for SimpleBag and CleverBag benchmark.
 *
 * @author Matthew Smith
 */
public class Benchmark {

	/**
	 * Main method used to run method that creates benchmark file.
	 *
	 * @param args none
	 */
	public static void main(String[] args) {
		File loadFile = new File("frank.txt");
		File resultsFile = new File("benchmark_results.txt");
		int[] removeRunTimes = new int[]{10, 100, 1000};
		createResultsFile(loadFile, resultsFile, removeRunTimes);
	}

	/**
	 * Benchmarking method that measures run times for bag classes loadData methods.
	 *
	 * @param f file to be read
	 * @param s SimpleBag object
	 * @param c CleverBag object
	 * @return formatted string containing benchmark results
	 */
	public static String compareLoadData(File f, SimpleBag s, CleverBag c) {
		long simpleBagStartTime = System.currentTimeMillis();
		s.loadData(f);
		long simpleBagEndTime = System.currentTimeMillis();
		long simpleBagLoadTime = simpleBagEndTime - simpleBagStartTime;

		long cleverBagStartTime = System.currentTimeMillis();
		c.loadData(f);
		long cleverBagEndTime = System.currentTimeMillis();
		long cleverBagLoadTime = cleverBagEndTime - cleverBagStartTime;

		return "load:\t" + simpleBagLoadTime + "\t" + cleverBagLoadTime + "\n";
	}

	/**
	 * Benchmarking method that measures run times for bag classes removeRandom methods.
	 *
	 * @param n amount of times removeRandom method will be called for both bag classes
	 * @param s SimpleBag object
	 * @param c CleverBag object
	 * @return formatted string containing benchmark results
	 */
	public static String compareRemove(int n, SimpleBag s, CleverBag c) {
		long simpleBagRemoveTime = 0;
		for (int i = n; i > 0; i--) {
			long simpleBagStartTime = System.currentTimeMillis();
			s.removeRandom();
			long simpleBagEndTime = System.currentTimeMillis();
			simpleBagRemoveTime += simpleBagEndTime - simpleBagStartTime;
		}

		long cleverBagRemoveTime = 0;
		for (int i = n; i > 0; i--){
			long cleverBagStartTime = System.currentTimeMillis();
			c.removeRandom();
			long cleverBagEndTime = System.currentTimeMillis();
			cleverBagRemoveTime = cleverBagRemoveTime + (cleverBagEndTime - cleverBagStartTime);
		}

		return n + "\t" + simpleBagRemoveTime + "\t" + cleverBagRemoveTime + "\n";
	}

	/**
	 * Creates named file and writes benchmark times to it.
	 *
	 * @param in amount of times removeRandom method will be called for both bag classes
	 * @param out SimpleBag object
	 * @param values CleverBag object
	 */
	public static void createResultsFile(File in, File out, int[] values) {
		SimpleBag simpleBag = new SimpleBag(1);
		CleverBag cleverBag = new CleverBag(1);
		String stringToWrite = "";

		stringToWrite = stringToWrite.concat(compareLoadData(in, simpleBag, cleverBag));
		for (int i = 0; i < values.length; i++) {
			stringToWrite = stringToWrite.concat(compareRemove(values[i], simpleBag, cleverBag));
		}

		try {
			FileWriter fileWriter = new FileWriter(out);
			fileWriter.write(stringToWrite);
			fileWriter.close();
		} catch (IOException e) {
			System.out.println("IOException thrown in createResultsFile method");
			return;
		} catch (Exception e) {
			System.out.println("Other exception thrown in createResultsFile method");
			return;
		}
	}
}
