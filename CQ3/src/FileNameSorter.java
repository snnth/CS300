///////////////////////// CUMULATIVE QUIZ FILE HEADER //////////////////////////
// Full Name: Matthew Smith
// Campus ID: 9081748536
// WiscEmail: mjsmith44@wisc.edu
////////////////////////////////////////////////////////////////////////////////

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * This FileSorter class implements a sorting algorithm: sortFileNames() method, along with
 * several helper methods and one simple test method. The sortFileNames() method sorts file names
 * in lexicographic order with respect to the provided specification.
 *
 * Complete the missing implementation marked by the //TODO tags.
 *
 */
public class FileNameSorter {

	/**
	 * This method returns the greater of two provided strings as input, with respect to the result of
	 * String.compareTo() method.
	 *
	 * @param s1 a string
	 * @param s2 another string
	 * @return the greatest of s1 and s2 with respect to the lexicographic order
	 */
	protected static String max(String s1, String s2) {
		// TODO #: implement this method
		if (s1.compareTo(s2) < 0)
			return s2;

		return s1; // CHANGE this (added to avoid compile errors)
	}

	/**
	 * Recursive method which returns the maximum element of an oversize array of strings
	 *
	 * @param data a non empty oversize array
	 * @param size number of elements stored in an oversize array
	 * @return the maximum element of the oversize array data of strings
	 */
	protected static String getMaximum(String[] data, int size) {
		// We assume that size is always non-zero positive integer. You do not need to check for that.
		// matt here -- i dont know what you mean by maximum... like the longest string in the array?
		// or the string thats at the end of the array? I am assuming the one at the end? but the
		// the solution to this problem seems a bit over the top for a simple pop...
		//
		// i figured it out albiet too late

		// TODO  #: base case (one-element oversize array)
		if (size == 1)
			return data[0]; // CHANGE this (added to avoid compile errors)

		// TODO  #: recursive case
		// Try to decompose problem into smaller problems of the same type as follows.
		// Given an oversize array defined by its reference data and its size, for instance:
		// data: [e1, e2, e3, e4, e5, e6]; size: 6
		// This array data can be divided into two partitions:
		// sub_array1: [e1, e2, e3, e4, e5] of size:5; and
		// sub_array2: [e6] (last element only)
		// To solve the original problem, you have to get the maximum of each partition. Some solution
		// is trivial and the other requires further decomposition into smaller sub-problems.
		int middleIndex = (size / 2);
		if (data[middleIndex - 1].equals(max(data[middleIndex], data[middleIndex - 1])))
			return getMaximum(Arrays.copyOfRange(data, 0, middleIndex), middleIndex);

		return getMaximum(Arrays.copyOfRange(data, middleIndex, size), middleIndex);
	}

	/**
	 * Swaps elements at indexes i and j of an array
	 *
	 * @param data array of strings
	 * @param i    an index
	 * @param j    another index
	 * @throws ArrayIndexOutOfBoundsException if i or j are out of bounds of the range of indexes from
	 *                                        0 .. data.length-1
	 */
	private static void swap(String[] data, int i, int j) {
		String x = data[i];
		data[i] = data[j];
		data[j] = x;
	}

	/**
	 * Returns the index of the first occurrence of a string within an oversize array
	 *
	 * @param s    string to search
	 * @param data reference to an array of strings
	 * @param size size of data
	 * @return the index of the first occurrence of s within the oversize array data, or -1 if no
	 *         match found.
	 */
	private static int indexOf(String s, String[] data, int size) {
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(s))
				return i;
		}
		return -1;
	}

	/**
	 * Sorts an array of file names in the lexicographic order using the recursive getMaximum()
	 * method. This sort operation operates in-place.
	 *
	 * @param names an unsorted array of file names
	 */
	public static void sortFileNames(String[] names) {
		// This is an in-place sorting algorithm
		// This method must use the getMaximum() method to operate

		// The algorithm maintains two subarrays in the given array names:
		// 1) The subarray which is unsorted from index 0 to index count-1.
		// 2) Remaining subarray which is sorted from index count to the end of the array.

		int count = names.length;
		// sort the array names in the lexicographic order
		while (count != 0) {
			// TODO #: get the maximum element of the oversize array defined by names and count
			String maximumElement = getMaximum(names, count);
			// TODO #: swap the maximum element and the element at index count-1
			swap(names, indexOf(maximumElement, names, count), count - 1);
			// TODO #: decrement count
			count--;
		}

	}



	/**
	 * One short and simple test for the sortFileNames() method.
	 *
	 * @return true when this test passes, otherwise false
	 */
	public static boolean test() {
		// TODO #: Implement this test method
		// This test method MUST NOT throw any exceptions

		// create an unsorted perfect size array which contains at least 6 different
		// file names.
		String[] testArray = new String[]{"this", "is", "an", "unsorted", "test", "array", "b"};
		// create a new array representing the expected output: a sorted version
		// of the unsorted one
		String[] expected = new String[]{"an", "array", "b", "is", "this", "test", "unsorted"};
		// call sortFileNames method to sort the unsorted array
		sortFileNames(testArray);
		// compare the result with the expected sorted array; return true if they are
		// equals and false otherwise.
		// You can use Arrays.deepEquals(String[], String[]) methods to check whether two arrays are
		// equals
		System.out.println(Arrays.toString(testArray));
		System.out.println(Arrays.toString(expected));
		if (!Arrays.deepEquals(expected, testArray))
			return false;

		return true; // CHANGE this (added to avoid compile errors)
	}

	/**
	 * Driver for the test method above.
	 *
	 * @param args is unused
	 */
	public static void main(String[] args) {
		System.out.println("test(): " + test());
	}

}