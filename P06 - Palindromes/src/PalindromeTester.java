///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           P06 - Palindromes Tester
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
 * Testing class for Palindrome class.
 *
 * @author Matthew Smith
 */
public class PalindromeTester {

	/**
	 * Main method. One call to runAllTests. CLI arguments not supported.
	 *
	 * @param args none
	 */
	public static void main(String[] args) {
		runAllTests();
	}

	/**
	 * Tests mirrorA() method for any algorithmic faults and invalid arguments.
	 *
	 * @return true if all tests pass, false if not
	 */
	public static boolean testMirrorA() {
		boolean passes = true;

		{ // method throws IllegalArgumentException if char is not an uppercase letter
			try {
				String palindromeString = Palindrome.mirrorA('a');
				System.out.println("\t ! IllegalArgumentException not thrown.");
				passes = false;
			} catch (IllegalArgumentException e) {}
		}

		{ // method returns "A" when given A
			String expected = "A";
			String actual = Palindrome.mirrorA('A');
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: A / EXPECTED: A / ACTUAL: " + actual);
				passes = false;
			}
		}

		{ // method returns "C B A B C" when given C
			String expected = "C B A B C";
			String actual = Palindrome.mirrorA('C');
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: C / EXPECTED: C B A B C " +
								"/ ACTUAL: " + actual);
				passes = false;
			}
		}

		return passes;
	}

	/**
	 * Tests mirrorAStep() method for any algorithmic faults and invalid arguments.
	 *
	 * @return true if all tests pass, false if not
	 */
	public static boolean testMirrorAStep() {
		boolean passes = true;

		{ // method throws IllegalArgumentException if char is not an uppercase letter
			try {
				String palindromeString = Palindrome.mirrorA('a', 1);
				System.out.println("\t ! IllegalArgumentException not thrown.");
				passes = false;
			} catch (IllegalArgumentException e) {}
		}

		{ // method throws IllegalArgumentException if step is not strictly positive
			try {
				String palindromeString = Palindrome.mirrorA('A', 0);
				System.out.println("\t ! IllegalArgumentException not thrown.");
				passes = false;
			} catch (IllegalArgumentException e) {}
		}

		{ // method returns "A" when given A (steps does not matter)
			String expected = "A";
			String actual = Palindrome.mirrorA('A', 1000);
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: A / EXPECTED: A / ACTUAL: " + actual);
				passes = false;
			}
		}

		{ // method returns "B B" when given B with step size > 1
			String expected = "B B";
			String actual = Palindrome.mirrorA('B', 5);
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: B, 5 / EXPECTED: B B / ACTUAL: "
								+ actual);
				passes = false;
			}
		}

		{ // method returns "C B A B C" when given C with step size = 1
			String expected = "C B A B C";
			String actual = Palindrome.mirrorA('C', 1);
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: C, 1 / EXPECTED: C B A B C / ACTUAL: "
								+ actual);
				passes = false;
			}
		}

		{ // method returns "C A C" when given C with step size = 2
			String expected = "C A C";
			String actual = Palindrome.mirrorA('C', 2);
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: C, 2 / EXPECTED: C A C / ACTUAL: "
								+ actual);
				passes = false;
			}
		}

		return passes;
	}

	/**
	 * Tests mirrorZ() method for any algorithmic faults and invalid arguments.
	 *
	 * @return true if all tests pass, false if not
	 */
	public static boolean testMirrorZ() {
		boolean passes = true;

		{ // method throws IllegalArgumentException if char is not an uppercase letter
			try {
				String palindromeString = Palindrome.mirrorZ('z');
				System.out.println("\t ! IllegalArgumentException not thrown.");
				passes = false;
			} catch (IllegalArgumentException e) {}
		}

		{ // method returns "Z" when given Z
			String expected = "Z";
			String actual = Palindrome.mirrorZ('Z');
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: Z / EXPECTED: Z / ACTUAL: " + actual);
				passes = false;
			}
		}

		{ // method returns "X Y Z Y X" when given X
			String expected = "X Y Z Y X";
			String actual = Palindrome.mirrorZ('X');
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: X / EXPECTED: X Y Z Y X " +
								"/ ACTUAL: " + actual);
				passes = false;
			}
		}

		return passes;
	}

	/**
	 * Tests mirrorZStep() method for any algorithmic faults and invalid arguments.
	 *
	 * @return true if all tests pass, false if not
	 */
	public static boolean testMirrorZStep() {
		boolean passes = true;

		{ // method throws IllegalArgumentException if char is not an uppercase letter
			try {
				String palindromeString = Palindrome.mirrorZ('z', 1);
				System.out.println("\t ! IllegalArgumentException not thrown.");
				passes = false;
			} catch (IllegalArgumentException e) {}
		}

		{ // method throws IllegalArgumentException if step is not strictly positive
			try {
				String palindromeString = Palindrome.mirrorZ('Z', 0);
				System.out.println("\t ! IllegalArgumentException not thrown.");
				passes = false;
			} catch (IllegalArgumentException e) {}
		}

		{ // method returns "Z" when given Z (steps does not matter)
			String expected = "Z";
			String actual = Palindrome.mirrorZ('Z', 1000);
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: Z / EXPECTED: Z / ACTUAL: " + actual);
				passes = false;
			}
		}

		{ // method returns "Y Y" when given Y with step size > 1
			String expected = "Y Y";
			String actual = Palindrome.mirrorZ('Y', 5);
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: Y, 5 / EXPECTED: Y Y / ACTUAL: "
								+ actual);
				passes = false;
			}
		}

		{ // method returns "X Y Z Y X" when given C with step size = 1
			String expected = "X Y Z Y X";
			String actual = Palindrome.mirrorZ('X', 1);
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: X, 1 / EXPECTED: X Y Z Y X / ACTUAL: "
								+ actual);
				passes = false;
			}
		}

		{ // method returns "X Z X" when given X with step size = 2
			String expected = "X Z X";
			String actual = Palindrome.mirrorZ('X', 2);
			if (!actual.equals(expected)) {
				System.out.println("\t ! Strings do not match ! ARG: X, 2 / EXPECTED: X Z X / ACTUAL: "
								+ actual);
				passes = false;
			}
		}

		return passes;
	}


	/**
	 * Runs all tester methods in this class their resulting output of true of false (pass or not)
	 *
	 * @return true if all tests pass, false if not
	 */
	public static boolean runAllTests() {
		boolean t1, t2, t3, t4;

		System.out.println("mirrorA: " + (t1 = testMirrorA()));
		System.out.println("mirrorAStep: " + (t2 = testMirrorAStep()));
		System.out.println("mirrorZ: " + (t3 = testMirrorZ()));
		System.out.println("mirrorZStep: " + (t4 = testMirrorZStep()));
		System.out.println("All tests pass? " + ((t1 && t2 && t3 && t4) ? "yes" : "no"));

		return (t1 && t2 && t3 && t4);
	}
}


