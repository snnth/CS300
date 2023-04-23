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
 * Tester class for InventoryList.java
 */
public class InventorySystemTester {
  
  public static void main(String args[]) {
    System.out.println("All tests passed: " + runAllTests());
  }

  /**
   * Checks for the correctness of LinkedBox(box, next), LinkedBox.getBox(), 
   * and LinkedBox.setNext() methods.
   * @return true if all tests pass, false if not
   */
  public static boolean testLinkedBox() {
    Box.restartNextInventoryNumber();
    boolean passesAllTests = true;

      { // Tests correctness of LinkedBox.getBox() method
        Box expected = new Box(Color.BLUE);
        LinkedBox linkedBlueBox = new LinkedBox(expected);
        Box actual = linkedBlueBox.getBox();
        if (!actual.equals(expected)) {
          passesAllTests = false;
          System.out.println("LinkedBox.getBox() method is not correctly implemented.");
        }
      }

      { // Tests correctness of LinkedBox.setNext() method
        LinkedBox expected = new LinkedBox(new Box(Color.YELLOW));
        LinkedBox notExpected = new LinkedBox(new Box(Color.BROWN));
        LinkedBox linkedBlueBox = new LinkedBox(new Box(Color.BLUE), notExpected);
        linkedBlueBox.setNext(expected);
        LinkedBox actual = linkedBlueBox.getNext();
        if (!actual.equals(expected)) {
          passesAllTests = false;
          System.out.println("LinkedBox.setNext() method is not correctly implemented.");
        }
      }
  
      { // Tests correctness of LinkedBox(box, next) constructor for posterity lol
        LinkedBox expectedNext = new LinkedBox(new Box(Color.YELLOW));
        Box expectedBox = new Box(Color.BLUE);
        LinkedBox linkedBlueBox = new LinkedBox(expectedBox, expectedNext);
        LinkedBox actualNext = linkedBlueBox.getNext();
        if (!actualNext.equals(expectedNext)) {
          passesAllTests = false;
          System.out.println("LinkedBox.constructor() method is not correctly implemented" + 
              " (next).");
        }
        Box actualBox = linkedBlueBox.getBox();
        if (!actualBox.equals(expectedBox)) {
          passesAllTests = false;
          System.out.println("LinkedBox.constructor() method is not correctly implemented" + 
              " (box).");
        }
      }

    return passesAllTests;
  }

  /**
   * Checks for the correctness of the InventoryList.addYellow(),
   * InventoryList.addBlue(), and InventoryList.addBrown() methods
   * @return true if all tests pass, false if not
   */
  public static boolean testAddBoxes() {
    Box.restartNextInventoryNumber();
    boolean allTestsPass = true;

    { // Tests correctness of InventoryList.addYellow() when box is not yellow
      try {
        InventoryList list = new InventoryList();
        list.addYellow(new Box(Color.BLUE));
        System.out.println("InventoryList.addYellow() not implemented correctly for " +
          "a provided box that is not the color yellow.");
        allTestsPass = false;
      } catch (IllegalArgumentException e) {
        // works if here!
      }
    }
    
    { // Tests correctness of InventoryList.addYellow() when list is empty
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.YELLOW);
      list.addYellow(expected);
      Box actual = list.get(0);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addYellow() not implemented correctly for " +
            "an empty list.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.addYellow() when list has at least one box
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.YELLOW);
      list.addBrown(new Box(Color.BROWN));
      list.addBrown(new Box(Color.BROWN));
      list.addYellow(expected);
      Box actual = list.get(0);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addYellow() not implemented correctly for " +
            "a list with at least one box.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.addBrown() when box is not brown
      try {
        InventoryList list = new InventoryList();
        list.addBrown(new Box(Color.YELLOW));
        System.out.println("InventoryList.addBrown() not implemented correctly for " +
          "a provided box that is not the color brown.");
        allTestsPass = false;
      } catch (IllegalArgumentException e) {
        // works if here!
      }
    }
    
    { // Tests correctness of InventoryList.addBrown() when list is empty
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BROWN);
      list.addBrown(expected);
      Box actual = list.get(0);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addBrown() not implemented correctly for " +
            "an empty list.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.addBrown() when list has at least one box
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BROWN);
      list.addYellow(new Box(Color.YELLOW));
      list.addYellow(new Box(Color.YELLOW));
      list.addBrown(expected);
      Box actual = list.get(2);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addBrown() not implemented correctly for " +
            "a list with at least one box.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.addBlue() when box is not blue
      try {
        InventoryList list = new InventoryList();
        list.addBlue(new Box(Color.BROWN));
        System.out.println("InventoryList.addBlue() not implemented correctly for " +
          "a provided box that is not the color blue.");
        allTestsPass = false;
      } catch (IllegalArgumentException e) {
        // works if here!
      }
    }
    
    { // Tests correctness of InventoryList.addBlue() when list is empty
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BLUE);
      list.addBlue(expected);
      Box actual = list.get(0);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addBlue() not implemented correctly for " +
            "an empty list.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.addBlue() when list only has yellow boxes
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BLUE);
      list.addYellow(new Box(Color.YELLOW));
      list.addYellow(new Box(Color.YELLOW));
      list.addBlue(expected);
      Box actual = list.get(2);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addBlue not implemented correctly for " +
            "a list that only has yellow boxes.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.addBlue() when list only has brown boxes
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BLUE);
      list.addBrown(new Box(Color.BROWN));
      list.addBrown(new Box(Color.BROWN));
      list.addBlue(expected);
      Box actual = list.get(0);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addBlue not implemented correctly for " +
            "a list that only has brown boxes.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.addBlue() when list has both yellow and brown boxes
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BLUE);
      list.addBrown(new Box(Color.BROWN));
      list.addYellow(new Box(Color.YELLOW));
      list.addBrown(new Box(Color.BROWN));
      list.addYellow(new Box(Color.YELLOW));
      list.addBlue(expected);
      Box actual = list.get(2);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addBlue not implemented correctly for " +
            "a list that has yellow and brown boxes.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.addBlue() when list has at least one of each colored box
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BLUE);
      list.addBrown(new Box(Color.BROWN));
      list.addYellow(new Box(Color.YELLOW));
      list.addBlue(new Box(Color.BLUE));
      list.addBrown(new Box(Color.BROWN));
      list.addYellow(new Box(Color.YELLOW));
      list.addBlue(expected);
      Box actual = list.get(2);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.addBlue not implemented correctly for " +
            "a list that has at least one of each colored box.");
        allTestsPass = false;
      }
    }

    return allTestsPass;
  }

  /**
   * Checks for the correctness of the InventoryList.removeBox()
   * InventoryList.removeYellow(), and InventoryList.removeBrown()
   * methods.
   * @return true if all tests pass, false if not
   */
  public static boolean testRemoveBoxes() {
    Box.restartNextInventoryNumber();
    boolean allTestsPass = true;

    { // Tests correctness of InventoryList.removeYellow() when list has no yellow boxes
      try {
        InventoryList list = new InventoryList();
        list.addBlue(new Box(Color.BLUE));
        list.removeYellow();
        System.out.println("InventoryList.removeYellow() not implemeneted correctly for " +
            " a list that has no yellow boxes.");
        allTestsPass = false;
      } catch (NoSuchElementException e) {
        // works if here!
      }
    }

    { // Tests correctness of InventoryList.removeYellow() when there's only one yellow box in list
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.YELLOW);
      list.addYellow(expected);
      Box actual = list.removeYellow();
      if (!actual.equals(expected) && !list.isEmpty()) {
        System.out.println("InventoryList.removeYellow() not implemented correctly for " +
            "a list with only one yellow box.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.removeBrown() when list has no brown boxes
      try {
        InventoryList list = new InventoryList();
        list.addBlue(new Box(Color.BLUE));
        list.removeBrown();
        System.out.println("InventoryList.removeBrown() not implemeneted correctly for " +
            " a list that has no brown boxes.");
        allTestsPass = false;
      } catch (NoSuchElementException e) {
        // works if here!
      }
    }

    { // Tests correctness of InventoryList.removeBrown() when there's only one brown box in list
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BROWN);
      list.addBrown(expected);
      Box actual = list.removeBrown();
      if (!actual.equals(expected) && !list.isEmpty()) {
        System.out.println("InventoryList.removeBrown() not implemented correctly for " +
            "a list with only one brown box.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.removeBox() when list is empty
      try {
        InventoryList list = new InventoryList();
        list.removeBox(100);
        System.out.println("InventoryList.removeBox() not implemented correctly for " +
            " when the list is empty.");
        allTestsPass = false;
      } catch (NoSuchElementException e) {
        // works if here!
      }
    }

    { // Tests correctness of InventoryList.removeBox() when given inventory number is not found
      try {
        InventoryList list = new InventoryList();
        list.addBlue(new Box(Color.BLUE));
        list.removeBox(1000);
        System.out.println("InventoryList.removeBox() not implemented correctly for " +
          " a situation where the list does not contain a box with the given inventory number.");
        allTestsPass = false;
      } catch (NoSuchElementException e) {
        // works if here!
      }
    }

    { // Tests correctness of InventoryList.removeBox() when box with given inventory # is found
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.BLUE);
      list.addYellow(new Box(Color.YELLOW));
      list.addBrown(new Box(Color.BROWN));
      list.addBlue(new Box(Color.BLUE));
      list.addBrown(new Box(Color.BROWN));
      list.addBlue(expected);
      list.addYellow(new Box(Color.YELLOW));
      list.addBlue(new Box(Color.BLUE));
      // System.out.println(list.toString());
      Box actual = list.removeBox(6);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.removeBox() not implemented correctly for " +
        "a list where the index exists.\n\t" + list.toString());
        allTestsPass = false;
      }
    }

    return allTestsPass;
  }

  /**
   * Checks for the correctness of the InventoryList.get() method
   * @return true if all tests pass, false if not
   */
  public static boolean testGetBoxes() {
    Box.restartNextInventoryNumber();
    boolean allTestsPass = true;

    { // Tests correctness of InventoryList.get() when index is out of bounds
      try {
        InventoryList list = new InventoryList();
        list.get(0);
        System.out.println("InventoryList.get() not implemented correctly for " +
            "out of bounds index");
        allTestsPass = false;
      } catch (IllegalArgumentException e) {
        // works if exception was caught!
      }
    }

    { // Tests correctness of InventoryList.get() when list is not empty
      InventoryList list = new InventoryList();
      Box expected = new Box(Color.YELLOW);
      list.addYellow(expected);
      list.addYellow(new Box(Color.YELLOW));
      Box actual = list.get(1);
      if (!actual.equals(expected)) {
        System.out.println("InventoryList.get() not implemented correctly for " +
            "a non empty list (retrieved wrong box).");
        allTestsPass = false;
      }
    }

    return allTestsPass;
  }

  /**
   * Checks for the correctness of the InventoryList.clear() method
   * @return true if all tests pass, false if not
   */
  public static boolean testClear() {
    Box.restartNextInventoryNumber();
    boolean allTestsPass = true;

    { // Tests correctness of InventoryList.clear() when list is empty
      InventoryList list = new InventoryList();
      list.clear();
      boolean expected = true;
      boolean actual = list.isEmpty();
      if (actual != expected) {
        System.out.println("Inventory.clear() is not correctly implemented for an empty list.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.clear() when list has one box
      InventoryList list = new InventoryList();
      list.addYellow(new Box(Color.YELLOW));
      list.clear();
      boolean expected = true;
      boolean actual = list.isEmpty();
      if (actual != expected) {
        System.out.println("Inventory.clear() is not correctly implemented for " + 
            "a list with one box.");
        allTestsPass = false;
      }
    }

    { // Tests correctness of InventoryList.clear() when list has more than one box
      InventoryList list = new InventoryList();
      list.addYellow(new Box(Color.YELLOW));
      list.addYellow(new Box(Color.YELLOW));
      list.clear();
      boolean expected = true;
      boolean actual = list.isEmpty();
      if (actual != expected) {
        System.out.println("Inventory.clear() is not correctly implemented for " +
            "a list with more than one box.");
        allTestsPass = false;
      }
    }

    return allTestsPass;
  }

  /**
   * A test suite method to run all of my test methods
   * @return true if all tests pass, false if not
   */
  public static boolean runAllTests() {
    boolean truth = true;
    truth = truth && testLinkedBox();
    truth = truth && testAddBoxes();
    truth = truth && testRemoveBoxes();
    truth = truth && testGetBoxes();
    truth = truth && testClear();

    return truth;
  }
}
