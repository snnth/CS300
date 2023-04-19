//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 - Inventory Storage System
// Course:   CS 300 Spring 2021
//
// Author:   Matthew Smith
// Email:    mjsmith44@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: None
// Online Sources: None
//
///////////////////////////////////////////////////////////////////////////////

import java.util.NoSuchElementException;

/**
 * This class maintains a singly-linked list of colored boxes. Generally speaking, yellow boxes
 * are always added to the head of the list, browns are always added to the tail of the list and
 * blues are always added to the end of yellows. List operations range from adding individual 
 * colored boxes to removes ones by thier specific inventory numbers thata are assigned to them
 * upon creation. 
 * 
 * @author Matthew Smith
 */
public class InventoryList {
  
  private LinkedBox head;
  private LinkedBox tail;
  private int size;
  private int yellowCount;
  private int blueCount;
  private int brownCount;

  /**
   * Adds a new blue box at the top of blue boxes if the list contains any blue box. Blue boxes
   * must be added at the buttom of yellow boxes and at the top of all the brown boxes if any. 
   * This means that a new blue box must be added at index yellowCount.
   * @param blueBox new box to be added to this list
   * @throws IllegalArgumentException a descriptive error message if blueBox is null or if
   *    it does not have a Color.BLUE color
   */
  public void addBlue(Box blueBox) throws IllegalArgumentException {
    if (!blueBox.getColor().equals(Color.BLUE) || blueBox == null) {
      throw new IllegalArgumentException("Box is either not blue or is null.");
    }
    
    // list IS empty
    //    point head and tail at insertBox, update count and size, and call it a day
    if (this.isEmpty()) {
      LinkedBox insertBox = new LinkedBox(blueBox);
      this.head = insertBox;
      this.tail = insertBox;
      this.blueCount++;
      this.size++;
      return;
    }

    // list is NOT empty but NO yellows
    //    point insertBox at head, point head at it, update count and size, and call it a day
    if (this.yellowCount == 0) {
      LinkedBox insertBox = new LinkedBox(blueBox, this.head);
      this.head = insertBox;
      this.blueCount++;
      this.size++;
      return;
    }

    // MAIN ALGORITHM
    // list is NOT empty but HAS yellows
    //    search for last yellow in list
    LinkedBox currentBox = this.head;
    for (int currentIndex = 0; currentIndex < (this.yellowCount - 1); currentIndex++) {
      currentBox = currentBox.getNext();
    }

    //    point insertBox at yellows next, point yellow at insertBox
    LinkedBox insertBox = new LinkedBox(blueBox, currentBox.getNext());
    currentBox.setNext(insertBox);

    //    update tail if only yellows were present
    if (this.yellowCount == this.size) { // if only yellows 
      this.tail = insertBox;
    }

    this.blueCount++;
    this.size++;
  }

  /**
   * Adds a brown box at the end of this inventory list.
   * @param brownBox new brown box to be added to this list
   * @throws IllegalArgumentException a descriptive error message if brownBox is null or if 
   *    the color of the specific brownBox is not equal to Color.BROWN
   */
  public void addBrown(Box brownBox) throws IllegalArgumentException {
    if (!brownBox.getColor().equals(Color.BROWN) || brownBox == null) {
      throw new IllegalArgumentException("Box is either not brown or is null.");
    }
    
    LinkedBox insertBox = new LinkedBox(brownBox);

    if (this.isEmpty()) {
      this.head = insertBox;
      this.tail = insertBox;
    }

    else {
      this.tail.setNext(insertBox);
      this.tail = insertBox;
    }

    this.brownCount++;
    this.size++;
  }

  /**
   * Adds a yellow box at the end of this inventory list.
   * @param yellowBox new yellow box to be added to this list
   * @throws IllegalArgumentException a descriptive error message if yellowBox is null or if 
   *    its color does not equal to Color.YELLOW
   */
  public void addYellow(Box yellowBox) throws IllegalArgumentException {
    if (!yellowBox.getColor().equals(Color.YELLOW)) {
      throw new IllegalArgumentException("Box is either not yellow or is null.");
    }
    
    if (this.isEmpty()) {
      LinkedBox insertBox = new LinkedBox(yellowBox);
      this.head = insertBox;
      this.tail = insertBox;
    }

    else {
      LinkedBox insertBox = new LinkedBox(yellowBox, this.head);
      this.head = insertBox;
    }

    this.yellowCount++;
    this.size++;
  }

  /**
   * Removes all of the elements from this list. The list will be empty after this call returns.
   */
  public void clear() {
    this.head = null;
    this.tail = null;
    this.size = 0;
    this.yellowCount = 0;
    this.blueCount = 0;
    this.brownCount = 0;
  }

  /**
   * Returns the element stored at position index of this list without removing it.
   * @param index position within this list
   * @return the box stored at position index of this list
   * @throws IllegalArgumentException a descriptive error message if the index is out of bounds 
   *    (index < 0 || index >= size())
   */
  public Box get(int index) throws IllegalArgumentException {
    if (index < 0 || index >= this.size)
      throw new IllegalArgumentException("Index is out of bounds.");

    Box returnBox = null;
    LinkedBox currentBox = this.head;
    for (int i = 0; i <= index; i++) {
      returnBox = currentBox.getBox();
      currentBox = currentBox.getNext();
    }
    return returnBox;
  }

  /**
   * Returns the number of blue boxes stored in this list.
   * @return the count of blue boxes in this list
   */
  public int getBlueCount() {
    return this.blueCount;
  }

  /**
   * Returns the number of brown boxes stored in this list.
   * @return the count of brown boxes in this list
   */
  public int getBrownCount() {
    return this.brownCount;
  }

  /**
   * Returns the number of yellow boxes stored in this list.
   * @return the count of yellow boxes in this list
   */
  public int getYellowCount() {
    return this.yellowCount;
  }

  /**
   * Checks whether this LinkedBoxList is empty
   * @return true if this LinkedBoxList is empty, false otherwise
   */
  public boolean isEmpty() {
    if (this.head == null || this.tail == null || this.size == 0)
      return true;

    return false;
  }

  /**
   * Removes and returns a box given its inventory number from this list
   * @param inventoryNumber inventory number of the box to be removed from this list
   * @return a reference to the removed element
   * @throws NoSuchElementException with a descriptive error message if no box is found with the 
   *    provided inventory number in the list.
   */
  public Box removeBox(int inventoryNumber) throws NoSuchElementException {
    if (this.size == 0) {
      throw new NoSuchElementException("No boxes to search through.");
    }

    boolean inventoryFound = false;
    LinkedBox currentBox = this.head;
    LinkedBox previousBox = null;
    while (currentBox != null) {
      if (currentBox.getBox().getInventoryNumber() == inventoryNumber) {
        inventoryFound = true;
        break;
      }
      previousBox = currentBox;
      currentBox = currentBox.getNext();
    }
    
    if (inventoryFound == false) {
      throw new NoSuchElementException("No box with inventory number \"" 
          + inventoryNumber + "\" found.");
    }

    // if only box in list
    if (previousBox == null && currentBox.getNext() == null) {
      this.head = null;
      this.tail = null;
    }

    // if first box in list with other boxes
    else if (previousBox == null && currentBox.getNext() != null) {
      this.head = currentBox.getNext();
      currentBox.setNext(null);
    }

    // if last box in list with other boxes
    else if (previousBox != null && currentBox.getNext() == null) {
      this.tail = previousBox;
      previousBox.setNext(null);
    }

    // if somewhere in middle of the list
    else if (previousBox != null && currentBox.getNext() != null) {
      previousBox.setNext(currentBox.getNext());
      currentBox.setNext(null);
    }

    // decrement size and color count
    this.size--;
    switch (currentBox.getBox().getColor()) {
      case BLUE:
        this.blueCount--;
        break;
      case BROWN:
        this.brownCount--;
        break;
      case YELLOW:
        this.yellowCount--;
        break;
    }

    return currentBox.getBox();
  }

  /**
   * Removes and returns the element at the tail of this list if it has a brown color.
   * @return a reference to the removed element
   * @throws NoSuchElementException with a descriptive error message if this list does not contain
   *    any brown box (brownCount == 0)
   */
  public Box removeBrown() throws NoSuchElementException {
    if (brownCount == 0 || !this.tail.getBox().getColor().equals(Color.BROWN)) {
      throw new NoSuchElementException("This list does not contain any brown boxes!");
    }

    Box returnBox;
    LinkedBox currentBox = this.head;
    LinkedBox previousBox = null;
    for (int currentIndex = 0; currentIndex < (size - 1); currentIndex++) {
      previousBox = currentBox;
      currentBox = currentBox.getNext();
    }
    returnBox = currentBox.getBox();

    if (previousBox == null) {
      this.clear();
      brownCount--;
      size--;
      return returnBox;
    }

    else {
      this.tail = previousBox;
      previousBox.setNext(null);
      brownCount--;
      size--;
      return returnBox;
    }
  }

  /**
   * Removes and returns the box at the head of this list if its color is yellow.
   * @return a reference to the removed box
   * @throws NoSuchElementException with a descriptive error message if this list does not contain
   *    any yellow boxes
   */
  public Box removeYellow() throws NoSuchElementException {
    if (yellowCount == 0 || !this.head.getBox().getColor().equals(Color.YELLOW)) {
      throw new NoSuchElementException("This list does not contain any yellow boxes!");
    }

    Box returnBox = this.head.getBox();
    this.head = this.head.getNext();
    if (this.head == null) {
      this.tail = null;
    }

    yellowCount--;
    size--;
    return returnBox;
  }

  /**
   * Returns the size of this list.
   * @return the size of this LinkedBoxList
   */
  public int size() {
    return this.size;
  }

  /**
   * Returns a String representation of the contents of this list
   * @return return a String representation of the content of this list. If this list is empty, 
   *    an empty String ("") will be returned.
   */
  public String toString() {
    if (this.isEmpty()) {
      return "";
    }
    
    String returnString = "";
    LinkedBox currentBox = this.head;
    while(currentBox != null) {
      returnString = returnString.concat(currentBox.toString());
      currentBox = currentBox.getNext();
    }

    return returnString;
  }
}
