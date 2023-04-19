///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           P06 - Palindromes
// Course:          CS 300, Spring 2021
//
// Author:          Matthew Smith
// Email:           mjsmith44@wisc.edu
// Lecturer's Name: Hobbes Legault
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Person: NONE
// Source: NONE
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

/**
 * Class that builds differently styled palindromes consisting of space separated letters.
 *
 * @author Matthew Smith
 */
public class Palindrome {

	/**
	 * Returns a palindrome string where the first and last characters are the input character, and
	 * each character between them is the next character in the alphabet in descending order until A
	 * has been reached. If input character is A, A is returned; B, B A B; C, C B A B C; and so on...
	 *
	 * @param start character to start the palindrome on
	 * @return palindrome string
	 * @throws IllegalArgumentException if starting character is not uppercase letter
	 */
	public static String mirrorA(char start) throws IllegalArgumentException {
		if (start < 65 || start > 90)
			throw new IllegalArgumentException("Character argument must be an uppercase letter A-Z");

		if (start == 65)
			return "A";

		return "" + start + " " + mirrorA((char) (start - 1)) + " " + start;
	}

	/**
	 * Returns a palindrome string where the first and last characters are the input character, and
	 * each character between them is the next character in the alphabet in descending order until A
	 * has been reached or passed. If input character is A, A is returned. Algorithm descends alphabet
	 * skipping the amount of characters given via the step argument.
	 *
	 * @param start character to start the palindrome on
	 * @param step how many letters that will be sipped between each added letter
	 * @return palindrome string
	 * @throws IllegalArgumentException if starting character is not upppercase letter or step size
	 *    is not strictly positive
	 */
	public static String mirrorA(char start, int step) throws IllegalArgumentException {
		if (start < 65 || start > 90)
			throw new IllegalArgumentException("Character argument must be an uppercase letter A-Z");

		if (step < 1)
			throw new IllegalArgumentException("Step sizes must be strictly positive (no less than one)");

		if (start == 65)
			return "A";

		// dynamicEnd determines if the character spacing will need an extra space before the last
		// recursive case is called to make sure the entire string looks proper.
		String dynamicEnd = (((start - 65) % step) <= 1) ? " " + start : "" + start;
		if ((start - step) < 65)
			return "" + start + dynamicEnd;
		else
			return "" + start + " " + mirrorA((char) (start - step), step) + dynamicEnd;
	}

	/**
	 * Returns a palindrome string where the first and last characters are the input character, and
	 * each character between them is the next character in the alphabet in ascending order until Z
	 * has been reached. If input character is Z, Z is returned.
	 *
	 * @param start character to start the palindrome on
	 * @return palindrome string
	 * @throws IllegalArgumentException if starting character is not uppercase letter
	 */
	public static String mirrorZ(char start) throws IllegalArgumentException {
		if (start < 65 || start > 90)
			throw new IllegalArgumentException("Character argument must be an uppercase letter A-Z");

		if (start == 90)
			return "Z";

		return "" + start + " " + mirrorZ((char) (start + 1)) + " " + start;
	}

	/**
	 * Returns a palindrome string where the first and last characters are the input character, and
	 * each character between them is the next character in the alphabet in ascending order until A
	 * has been reached or passed. If input character is Z, Z is returned. Algorithm ascends alphabet
	 * skipping the amount of characters given via the step argument.
	 *
	 * @param start character to start the palindrome on
	 * @param step how many letters that will be sipped between each added letter
	 * @return palindrome string
	 * @throws IllegalArgumentException if starting character is not upppercase letter or step size
	 *    is not strictly positive
	 */
	public static String mirrorZ( char start, int step) throws IllegalArgumentException {
		if (start < 65 || start > 90)
			throw new IllegalArgumentException("Character argument must be an uppercase letter A-Z");

		if (step < 1)
			throw new IllegalArgumentException("Step sizes must be strictly positive (no less than one)");

		if (start == 90)
			return "Z";

		// dynamicEnd determines if the character spacing will need an extra space before the last
		// recursive case is called to make sure the entire string looks proper.
		String dynamicEnd = (((90 - start) % step) <= 1) ? " " + start : "" + start;
		if ((start + step) > 90)
			return "" + start + dynamicEnd;
		else
			return "" + start + " " + mirrorZ((char) (start + step), step) + dynamicEnd;
	}
}
