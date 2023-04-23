///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           P01 - Self Checkout Kiosk
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

/**
 * This class is a fairly abstract model of a self checkout kiosk in a grocery store.
 *
 * @author Matthew Smith
 */
public class SelfCheckoutKiosk {

	public static final double TAX_RATE = 0.05;
	public static final String[][] GROCERY_ITEMS = new String[][] {{"Apple", "$1.59"},
			{"Avocado", "$0.59"}, {"Banana", "$0.49"}, {"Beef", "$3.79"}, {"Blueberry", "$6.89"},
			{"Broccoli", "$1.79"}, {"Butter", "$4.59"}, {"Carrot", "$1.19"}, {"Cereal", "$3.69"},
			{"Cheese", "$3.49"}, {"Chicken", "$5.09"}, {"Chocolate", "$3.19"}, {"Cookie", "$9.5"},
			{"Cucumber", "$0.79"}, {"Eggs", "$3.09"}, {"Grape", "$2.29"}, {"Ice Cream", "$5.39"},
			{"Milk", "$2.09"}, {"Mushroom", "$1.79"}, {"Onion", "$0.79"}, {"Pepper", "$1.99"},
			{"Pizza", "$11.5"}, {"Potato", "$0.69"}, {"Spinach", "$3.09"}, {"Tomato", "$1.79"}};

	/**
	 * Returns the name of the item by item index.
	 *
	 * @param index index of item to be looked up
	 * @return name of item if found or null if item index is not valid
	 */
	public static String getItemName(int index) {
		if (index < 0 || index >= GROCERY_ITEMS.length)
			return null;

		return GROCERY_ITEMS[index][0];
	}

	/**
	 * Returns the price of the item by item index
	 *
	 * @param index index of item to be looked up
	 * @return price of item if found or -2.0 if item index is not valid
	 */
	public static double getItemPrice(int index) {
		if (index < 0 || index >= GROCERY_ITEMS.length)
			return -2.0;

		return Double.parseDouble(GROCERY_ITEMS[index][1].trim().replace("$", ""));
	}

	/**
	 *  Prints the Catalog of the grocery store (item identifiers, names, and prices)
	 */
	public static void printCatalog() {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Item id \tName \t Price");
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
		for (int i = 0; i < GROCERY_ITEMS.length; i++) {
			System.out.println(i + "\t\t" + GROCERY_ITEMS[i][0] +
					" \t " + GROCERY_ITEMS[i][1]);
		}
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++");
	}

	/**
	 * Adds the name of an item to the bagging area by its associated item ID. It bagging area
	 * is at its capacity "Error! No additional item can be scanned. Please wait for assistance."
	 * is printed to the console and originally provided size is returned.
	 *
	 * @param id index of item to be added to bagging area
	 * @param items array representing the bagging area, and storing the names of the items scanned
	 * @param size number of items currently in array representing bagging area
	 * @return number of items in bagging area after successful add or if bagging area is at capacity,
	 *    but will return -2 if item id is invalid or -3 if number of items in bag is invalid
	 *    (over capacity or below zero).
	 */
	public static int addItemToBaggingArea(int id, String[] items, int size) {
		if (id < 0 || id >= GROCERY_ITEMS.length)
			return -2;

		if (size < 0 || size > items.length)
			return -3;

		if (size == items.length) {
			System.out.println("Error! No additional item can be scanned. Please wait for assistance.");
			return size;
		}

		items[size++] = SelfCheckoutKiosk.getItemName(id);
		return size;
	}

	/**
	 * Counts how many of those items are currently in the bagging area based on provided item name.
	 *
	 * @param item item name of items to count
	 * @param items array representing the bagging area, and storing the names of the items scanned
	 * @param size number of items currently in array representing bagging area
	 * @return total number of items in bagging area that match given item name,
	 *    but will return -2 if item name is null or empty or -3 if number of items in bag is invalid
	 *    (over capacity or below zero).
	 */
	public static int count(String item, String[] items, int size) {
		if (item == null || item.isEmpty() || item.isBlank())
			return -2;

		if (size < 0 || size > items.length)
			return -3;

		int count = 0;
		for (int i = 0; i < size; i++) {
			if (items[i].trim().equalsIgnoreCase(item))
				count++;
		}

		return count;
	}

	/**
	 * Searches provided array for first occurrence of item based on provided item name and returns
	 * its index number.
	 *
	 * @param item element to search for
	 * @param items an array of string elements
	 * @param size number of elements stored in items
	 * @return int the index of the first occurrence of item, -1 if item is not found,
	 *     -2 if given item name is null or empty, -3 if number of items in bag is invalid
	 *     (over capacity or below zero).
	 */

	public static int indexOf(String item, String[] items, int size) {
		if (item == null || item.isEmpty() || item.isBlank())
			return -2;

		if (size < 0 || size > items.length)
			return -3;

		for (int currentIndex = 0; currentIndex < size; currentIndex++) {
			if (items[currentIndex] == null)
				continue;

			if (items[currentIndex].trim().equalsIgnoreCase(item))
				return currentIndex;
		}

		return -1;
	}

	/**
	 * Searches bagging area for item by item name and removes it. If item count not be found
	 * in bagging area "WARNING: item not found." is printed to the console and originally provided
	 * size is returned.
	 *
	 * @param itemToRemove item name of item to remove from bagging area
	 * @param items array representing the bagging area, and storing the names of the items scanned
	 * @param size number of items currently in array representing bagging area
	 * @return new size of items in bagging area after a successful removal or provided size of
	 *    items in bagging area if unsuccessful. will also return -2 if given item name is null or
	 *    empty or -3 if number of items in bag is invalid (over capacity or below zero).
	 */
	public static int remove(String itemToRemove, String[] items, int size) {
		int indexOfRemoval = indexOf(itemToRemove, items, size);
		if (indexOfRemoval == -1) {
			System.out.println("WARNING: item not found.");
			return size;
		}

		if (indexOfRemoval < -1)
			return indexOfRemoval;

		for (int i = indexOfRemoval; i < size; i++) {
			if (i == size - 1) {
				items[i] = null;
				continue;
			}
			items[i] = items[i + 1];
		}
		return size - 1;
	}

	/**
	 * Creates a grocery items set collection array in itemsSet array from items array.
	 *
	 * @param items array representing the bagging area, and storing the names of the items scanned
	 * @param size number of items currently in array representing bagging area
	 * @param itemsSet reference to an empty array which is going to contain set collection
	 * @return the new size of the itemsSet array but will return -3 if number of items in bag is
	 * invalid (over capacity or below zero).
	 */
	public static int getUniqueCheckedOutItems(String[] items, int size, String[] itemsSet){
		if (size < 0 || size > items.length)
			return -3;

		int positionForSetAdd = 0; // this variable doubles for set count and will be returned
		for (int i = 0; i < size; i++) {
			String currentItem = items[i];
			if (SelfCheckoutKiosk.indexOf(currentItem, itemsSet, size) == -1)
				itemsSet[positionForSetAdd++] = currentItem;
		}

		return positionForSetAdd;
	}

	/**
	 * Calculates the price, without tax, of all items in bagging area and returns it.
	 *
	 * @param items array representing the bagging area, and storing the names of the items scanned
	 * @param size number of items currently in array representing bagging area
	 * @return price of items in bagging area prior to application of tax but will return -3.0 if
	 * the number of items in bag is invalid (over capacity or below zero).
	 */
	public static double getSubTotalPrice(String[] items, int size) {
		if (size < 0 || size > items.length)
			return -3.0;

		double subTotalPrice = 0.0;

		// get set collection array
		String[] itemsSet = new String[size];
		int itemsSetSize = getUniqueCheckedOutItems(items, size, itemsSet);

		// iterate through set collection array
		for (int i = 0; i < itemsSetSize; i++) {
			if (itemsSet[i] == null) // ignore nulls in oversized array
				continue;

			// get price of this unique item
			double itemPrice = getItemPrice(getItemIdByName(itemsSet[i]));

			// count how many of this unique item there are in bag
			int itemCount = count(itemsSet[i], items, size);

			// calculate price of all unique items in bag and add to subtotal
			subTotalPrice += itemPrice * itemCount;
		}

		return subTotalPrice;
	}

	/**
	 * Internal helper method that looks up and returns item ID by item name.
	 *
	 * @param itemName name of item to look up
	 * @return item id but will return -2 if item is not found.
	 */
	public static int getItemIdByName(String itemName) {
		for (int i = 0; i < GROCERY_ITEMS.length; i++) {
			if (itemName.trim().equalsIgnoreCase(GROCERY_ITEMS[i][0]))
				return i;
		}

		return -2;
	}
}
