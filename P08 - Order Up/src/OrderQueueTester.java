//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P08 - Order Up
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
 * Tester class for the Order Up! suite.
 */
public class OrderQueueTester {

  /**
   * Main method. Command-line arguments not supported.
   * @param args
   */
  public static void main(String[] args) {
    System.out.println("All tests pass: " + runAllTests());
  }
  
  /**
   * Tests the correctness of OrderIterator.hasNext() and OrderIterator.next() methods.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testOrderIteratorMethods() {
    boolean passesAllTests = true;
  
      { // Tests correctness of OrderIterator.hasNext() when current order is null
        try {
          OrderIterator iter = new OrderIterator(null);
          boolean expected = false;
          boolean actual = iter.hasNext();
          if (actual != expected) {
            System.out.println("OrderIterator.hasNext() not correctly implemented for " +
                "a LinkedOrder with no next.");
            passesAllTests = false;
          }
        } catch (NoSuchElementException e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        } catch (Exception e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        }
      }

      { // Tests correctness of OrderIterator.next() when current order is null
        try {
          OrderIterator iter = new OrderIterator(null);
          iter.next();
          System.out.println("OrderIterator.next() not correctly implemented for " +
          "a LinkedOrder with no next.");
          passesAllTests = false;
        } catch (NoSuchElementException e) {
          // works if here
        }
      }

      { // Tests correctness of OrderIterator.hasNext() when there is at least one element present
        try {
          LinkedOrder firstOrder = new LinkedOrder(new Order("firstDish", 1));
          LinkedOrder secondOrder = new LinkedOrder(new Order("secondDish", 2));
          firstOrder.setNext(secondOrder);
          OrderIterator iter = new OrderIterator(firstOrder);
          boolean expected = true;
          boolean actual = iter.hasNext();
          if (actual != expected) {
            System.out.println("OrderIterator.hasNext() not correctly implemented for " +
                "a LinkedOrder that has a next.");
            passesAllTests = false;
          }
        } catch (NoSuchElementException e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        } catch (Exception e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        }
      }

      { // Tests correctness of OrderIterator.next() when current order is not null
        try {
          LinkedOrder firstOrder = new LinkedOrder(new Order("firstDish", 1));
          Order expectedOrder = new Order("secondDish", 2);
          LinkedOrder secondOrder = new LinkedOrder(expectedOrder);
          firstOrder.setNext(secondOrder);
          OrderIterator iter = new OrderIterator(firstOrder);
          iter.next();
          Order actualOrder = iter.next();
          if (!actualOrder.equals(expectedOrder)) {
            System.out.println("OrderIterator.next() not correctly implemented for " +
                "a LinkedOrder that has a next");
            passesAllTests = false;
          }
        } catch (NoSuchElementException e) {
          System.out.println("NSEException: " + e.getMessage());
          passesAllTests = false;
        } catch (Exception e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        }
      }
  
    return passesAllTests;
  }
  
  /**
   * Tests the correctness of the OrderQueue.peek() and OrderQueue.isEmpty() methods.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testOrderQueuePeekAndIsEmptyMethods() {
    boolean passesAllTests = true;

    { // Tests correctness of peek() when list is empty
      try {
        OrderQueue queue = new OrderQueue();
        queue.peek();
        System.out.println("OrderQueue.peek() not correctly implemented for " +
              "a queue that is empty.");
        passesAllTests = false;
      } catch (NoSuchElementException e) {
        // works if here
      }
    }

    { // Tests correctness of peek() when list has at least one element
      try {
        OrderQueue queue = new OrderQueue();
        Order expectedOrder = new Order("expectedDish", 1);
        queue.enqueue(expectedOrder);
        queue.enqueue(new Order("someOtherDish", 2));
        Order actualOrder = queue.peek();
        if (!actualOrder.equals(expectedOrder)) {
          System.out.println("OrderQueue.peek() not correctly implemented for " +
              "a queue that has at least one element.");
          passesAllTests = false;
        }
      } catch (NoSuchElementException e) {
        System.out.println("Exception: " + e.getMessage());
        passesAllTests = false;
      } catch (Exception e) {
        System.out.println("Exception: " + e.getMessage());
        passesAllTests = false;
      }
    }

    { // Tests correctness of isEmpty() when queue is empty
      OrderQueue queue = new OrderQueue();
      boolean expected = true;
      boolean actual = queue.isEmpty();
      if (actual != expected) {
        System.out.println("OrderQueue.isEmpty() not correctly implemented for " +
            "an empty list.");
        passesAllTests = false;
      }
    }

    { // Tests correctness of isEmpty() when queue is not empty
      OrderQueue queue = new OrderQueue();
      queue.enqueue(new Order("firstDish", 1));
      queue.enqueue(new Order("secondDish", 2));
      boolean expected = false;
      boolean actual = queue.isEmpty();
      if (actual != expected) {
        System.out.println("OrderQueue.isEmpty() not correctly implemented for " +
            "a non-empty list.");
        passesAllTests = false;
      }
    }

    return passesAllTests;
  }

  /**
   * Tests the correctness of the OrderQueue.enqueue() method.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testOrderQueueEnqueueMethod() {
    boolean passesAllTests = true;
  
      { // Tests correctness of enqueue() to add LinkedOrder into empty queue
        try {
          OrderQueue queue = new OrderQueue();
          Order expectedOrder = new Order("expected", 1);
          queue.enqueue(expectedOrder);
          Order actualOrder = queue.peek();
          if (!actualOrder.equals(expectedOrder)) {
            System.out.println("OrderQueue.enqueue not correctly implemented for " +
                " adding an order to an empty list");
            passesAllTests = false;
          }
        } catch (NoSuchElementException e) {
          System.out.println("NSEException: " + e.getMessage());
          passesAllTests = false;
        } catch (Exception e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        }
      }

      { // Tests correctness of enqueue() to add orders in order if queue has more than one element
        try {
          OrderQueue queue = new OrderQueue();
          Order expectedOrder = new Order("expected", 5);
          queue.enqueue(new Order("firstDish", 1));
          queue.enqueue(new Order("secondDish", 2));
          queue.enqueue(new Order("thirdDish", 3));
          queue.enqueue(new Order("fourthDish", 4));
          queue.enqueue(expectedOrder);
          System.out.println(queue.toString());
          queue.dequeue();
          System.out.println(queue.toString());
          queue.dequeue();
          queue.dequeue();
          queue.dequeue();
          System.out.println(queue.toString());
          Order actualOrder = queue.dequeue();
          if (!actualOrder.equals(expectedOrder)) {
            System.out.println("OrderQueue.enqueue not correctly implemented for " +
                "adding an order to an empty list");
            passesAllTests = false;
          }
        } catch (NoSuchElementException e) {
          System.out.println("NSEException: " + e.getMessage());
          passesAllTests = false;
        } catch (Exception e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        }
      }
  
    return passesAllTests;
  }
  
  /**
   * Tests the correctness of the OrderQueue.dequeue() method.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean testOrderQueueDequeueMethod() {
    boolean passesAllTests = true;
  
      { // Tests correctness of dequeue() when queue is empty
        try {
          OrderQueue queue = new OrderQueue();
          queue.dequeue();
        } catch (NoSuchElementException e) {
          // works if here
        } catch (Exception e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        }
      }
      
      { // Tests correctness of dequeue() when queue is not empty
        try {
          OrderQueue queue = new OrderQueue();
          Order expectedOrder = new Order("expected", 3);
          queue.enqueue(new Order("firstDish", 1));
          queue.enqueue(new Order("secondDish", 2));
          queue.enqueue(expectedOrder);
          queue.enqueue(new Order("fourthDish", 4));
          queue.enqueue(new Order("fifthDish", 5));
          queue.dequeue();
          queue.dequeue();
          Order actualOrder = queue.peek();
          if (!actualOrder.equals(expectedOrder)) {
            System.out.println("OrderQueue.enqueue not correctly implemented for " +
                "adding an order to an empty list");
            passesAllTests = false;
          }
        } catch (NoSuchElementException e) {
          // works if here
        } catch (Exception e) {
          System.out.println("Exception: " + e.getMessage());
          passesAllTests = false;
        }
      }

    return passesAllTests;
  }

  /**
   * Responsible for running all tests.
   * 
   * @return true if all tests pass, false if otherwise
   */
  public static boolean runAllTests() {
    return testOrderIteratorMethods() 
        && testOrderQueuePeekAndIsEmptyMethods() 
        && testOrderQueueEnqueueMethod()
        && testOrderQueueDequeueMethod();
  }

}
