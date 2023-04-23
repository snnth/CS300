///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           P01 - Self Checkout Kiosk Tester
// Course:          CS 300, Spring 2021
//
// Author:          Matthew Smith
// Email:           mjsmith44@wisc.edu
// Lecturer's Name: Hobbes Legault
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Persons:         None
// Online Sources:  https://www.javainuse.com/java/getCurrentMethodName#:~:text
//      =Thread.&text=()%2D%20Call%20Thread.-,currentThread().,
//      the%20name%20of%20your%20method.
//       ~ The link above provided me with some understanding of how to get and
//         display the name of the method the JRE is currently in. I thought
//         this was a cool way to show what test method was running dynamically
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.util.Arrays;

/**
 * Tester class for SelfCheckoutKiosk class.
 * 
 * @author Matthew Smith
 */
public class SelfCheckoutKioskTester {

    /**
     * Main method to call testing methods. No supported command-line arguments.
     * 
     * @param args none
     */
    public static void main(String[] args) {
        testGetItemNameMethod();
        testGetItemPriceMethod();
        testAddItemToBaggingArea();
        testCount();
        testIndexOf();
        testRemove();
        testGetUniqueCheckedOutItems();
        testGetSubTotalPrice();
        testGetItemIdByName();
    }

    /**
     * Tests getItemName method. Description of individual tests found in method body.
     */
    private static void testGetItemNameMethod() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Tests if method returns null if item ID is not within range
        {
            testNum += 1;
            int itemID = -1;
            String expected = null;
            String actual = SelfCheckoutKiosk.getItemName(itemID);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 2. Tests if method returns correct item name
        {
            testNum += 1;
            int itemID = 5;
            String expected = SelfCheckoutKiosk.GROCERY_ITEMS[itemID][0];
            String actual = SelfCheckoutKiosk.getItemName(itemID);

            if (!expected.equalsIgnoreCase(actual)) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }

    /**
     * Tests getItemPrice method. Description of individual tests found in method body.
     */
    private static void testGetItemPriceMethod() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Tests if method returns -2.0 if item ID is not within range
        {
            testNum += 1;
            int itemID = -1;
            double expected = -2.0;
            double actual = SelfCheckoutKiosk.getItemPrice(itemID);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 2. Tests if method returns correct item price in primitive double type
        {
            testNum += 1;
            double expected = 1.79;
            double actual = SelfCheckoutKiosk.getItemPrice(5);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }

    /**
     * Tests addItemToBaggingArea method. Description of individual tests found in method body.
     */
    private static void testAddItemToBaggingArea() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Tests if method returns -2 if item id is not within range (no item found)
        {
            testNum += 1;
            int itemID = -1;
            int expected = -2;
            int actual = SelfCheckoutKiosk.addItemToBaggingArea(itemID, new String[1], 0);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 2. Tests if method returns -3 if amount of items in bagging area are
        // logically invalid or at capacity
        {
            testNum += 1;
            int itemID = 5;
            int expected = -3;
            int actual = SelfCheckoutKiosk.addItemToBaggingArea(itemID, new String[2], 3);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 3. Tests method correctly adds new item to bagging area in correct spot
        {
            testNum += 1;
            int itemID = 5;
            String expected = "Broccoli";
            String[] array = new String[]{"fill1", "fill2", "fill3", null, null};
            SelfCheckoutKiosk.addItemToBaggingArea(itemID, array, 3);
            String actual = array[3];

            if (!expected.equalsIgnoreCase(actual)) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 4. Tests method correctly returns new "size" of array
        {
            testNum += 1;
            int itemID = 5;
            int expected = 4;
            int actual = SelfCheckoutKiosk.addItemToBaggingArea(itemID, new String[5], 3);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }

    /**
     * Tests count method. Description of individual tests found in method body.
     */
    private static void testCount() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Tests if method returns -2 if item name is null
        {
            testNum += 1;
            String itemName = null;
            int expected = -2;
            int actual = SelfCheckoutKiosk.count(itemName, new String[5], 0);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 2. Tests if method returns -2 if item name is empty
        {
            testNum += 1;
            String itemName = "";
            int expected = -2;
            int actual = SelfCheckoutKiosk.count(itemName, new String[5], 0);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 3. Tests if method returns -3 if size is negative or outside capacity of array
        {
            testNum += 1;
            int expected = -3;
            int actual = SelfCheckoutKiosk.count("itemName", new String[2], 3);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 4. Tests if method correct positive match count
        {
            testNum += 1;
            String itemName = "match";
            int expected = 2;
            int actual = SelfCheckoutKiosk.count(itemName,
                    new String[]{"", "not it", "match", "could be it", "MaTcH ", null, null}, 5);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }

    /**
     * Tests indexOf method. Description of individual tests found in method body.
     */
    private static void testIndexOf() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Tests if method returns -2 if item name is null
        {
            testNum += 1;
            String itemName = null;
            int expected = -2;
            int actual = SelfCheckoutKiosk.indexOf(itemName, new String[5], 0);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 2. Tests if method returns -2 if item name is empty
        {
            testNum += 1;
            String itemName = "";
            int expected = -2;
            int actual = SelfCheckoutKiosk.indexOf(itemName, new String[5], 0);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 3. Test if method returns -3 if size is negative or beyond capacity of array
        {
            testNum += 1;
            int expected = -3;
            int actual = SelfCheckoutKiosk.indexOf("itemName", new String[2], 3);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 4. Tests if method returns correct index if match is found
        {
            testNum += 1;
            String itemName = "match";
            int expected = 2;
            int actual = SelfCheckoutKiosk.indexOf(itemName,
                    new String[]{"", "not it", " MatCH", "could be it", "match", null, null}, 5);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 5. Tests if method returns -1 if no match is found
        {
            testNum += 1;
            String itemName = "def not there";
            int expected = -1;
            int actual = SelfCheckoutKiosk.indexOf(itemName,
                    new String[]{"", "not it", " MatCH", "could be it", "match", null, null}, 5);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }

    /**
     * Tests remove method. Description of individual tests found in method body.
     */
    private static void testRemove() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Tests if method returns current size if no match is found to remove
        {
            testNum += 1;
            String itemName = "def not there";
            int expected = 5;
            int actual = SelfCheckoutKiosk.remove(itemName,
                    new String[]{"", "not it", " MatCH", "could be it", "match", null, null}, 5);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 2. Tests if method returns correct size (-1) if match is found
        {
            testNum += 1;
            String itemName = "match";
            int expected = 4;
            int actual = SelfCheckoutKiosk.remove(itemName,
                    new String[]{"", "not it", " MatCH", "could be it", "match", null, null}, 5);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 3. Tests if method removes first instance of match from array, shifts all following
        //    elements left, and replaces last item with null
        {
            testNum += 1;
            String itemName = "match";
            String[] expected = new String[]{"", "not it", "could be it", "match", null, null, null};
            String[] actual =
                    new String[]{"", "not it", " MatCH", "could be it", "match", null, null};
            SelfCheckoutKiosk.remove(itemName, actual, 5);

            if (!Arrays.equals(expected, actual)) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        Arrays.toString(expected) +  " | actual: " + Arrays.toString(actual));
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }

    /**
     * Tests getUniqueCheckedOutItems method. Description of individual tests found in method body.
     */
    private static void testGetUniqueCheckedOutItems() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Test if method returns -3 if size is negative or beyond capacity of array
        {
            testNum += 1;
            int expected = -3;
            int actual =
                    SelfCheckoutKiosk.getUniqueCheckedOutItems(new String[2], 3, new String[2]);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 2. Test if method only adds unique item names for set collection array
        {
            testNum += 1;
            String[] items = new String[]{"two-of", "two-of", "one-of", "three-of",
                    "three-of", "three-of"};
            String[] expected = new String[]{"two-of", "one-of", "three-of", null,
                    null, null, null, null, null, null};
            String[] actual = new String[10];
                    SelfCheckoutKiosk.getUniqueCheckedOutItems(items, 6, actual);

            if (!Arrays.equals(expected, actual)) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        Arrays.toString(expected) +  " | actual: " + Arrays.toString(actual));
            }
        }

        // 3. Test if method returns correct size of set collection array
        {
            testNum += 1;
            String[] items = new String[]{"two-of", "two-of", "one-of", "three-of",
                    "three-of", "three-of"};
            int expected = 3;
            int actual = SelfCheckoutKiosk.getUniqueCheckedOutItems(items, 6, new String[10]);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }

    /**
     * Tests getSubTotalPrice method. Description of individual tests found in method body.
     */
    private static void testGetSubTotalPrice() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Test if method returns -3.0 if size is negative or beyond capacity of array
        {
            testNum += 1;
            double expected = -3.0;
            double actual = SelfCheckoutKiosk.getSubTotalPrice(new String[2], 3);

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        // 2. Test if method returns correct sub-total before tax
        {
            testNum += 1;
            String[] items = new String[]{"apple", "banana", "beef", "apple", "beef",
                    "beef", null, null, null, null};
            double expected = 15.04;
            double actual = SelfCheckoutKiosk.getSubTotalPrice(items, 6);

            if (Math.abs(expected - actual) > 0.001) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }

    /**
     * Tests getItemIdByName method. Description of individual tests found in method body.
     */
    private static void testGetItemIdByName() {
        System.out.print(Thread.currentThread().getStackTrace()[1].getMethodName() + ": ");
        boolean allTestsPass = true;
        int testNum = 0;

        // 1. Test if method returns -2 if item could not be found
        {
            testNum += 1;
            int expected = -2;
            int actual = SelfCheckoutKiosk.getItemIdByName("def not in there");

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected + " | actual: " + actual);
            }
        }

        // 2. Test if method returns correct ID for found item
        {
            testNum += 1;
            int expected = 0;
            int actual = SelfCheckoutKiosk.getItemIdByName(" apple   ");

            if (expected != actual) {
                allTestsPass = false;
                System.out.print("\n --- Test " + testNum + " FAIL... ~ expected: " +
                        expected +  " | actual: " + actual);
            }
        }

        System.out.println(allTestsPass ? "passed..." : "");
    }
}
