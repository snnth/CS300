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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Main class in the Order Up! suite. Keeps track of orders in a queue.
 */
public class OrderQueue implements QueueADT<Order>, Iterable<Order> {

  private LinkedOrder front;
  private LinkedOrder back;
  private int size;

  /**
   * Creates and returns an iterator for this queue.
   */
  @Override
  public Iterator<Order> iterator() {
    return new OrderIterator(front);
  }

  /**
   * Adds an order to the back of the queue.
   */
  @Override
  public void enqueue(Order newElement) {
    // create new linked order
    LinkedOrder newOrder = new LinkedOrder(newElement);

    // if front and back are null assign both to newOrder.
    if (this.front == null && this.back == null) {
      this.front = newOrder;
      this.back = newOrder;
    }

    // if not assign backs next to newOrder and assign back to newOrder.
    else {
      this.back.setNext(newOrder);
      this.back = newOrder;
    }

    // and update size
    this.size++;
  }

  /**
   * Removes and returns the order at the front of the queue.
   * 
   * @throws NoSuchElementException if there is nothing to dequeue
   */
  @Override
  public Order dequeue() {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Nothing in queue to dequeue.");
    }

    Order returnOrder = this.front.getOrder();

    // if there is only one element in queue
    if (this.size == 1) {
      this.front = null;
      this.back = null;
    }

    // otherwise
    else {
      this.front = this.front.getNext();
    }

    this.size--;
    return returnOrder;
  }

  /**
   * Returns the order at the front of the queue without removing it.
   * 
   * @return Order the order at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  @Override
  public Order peek() throws NoSuchElementException {
    if (this.isEmpty()) {
      throw new NoSuchElementException("Nothing in queue to peek at.");
    }

    return this.front.getOrder();
  }

  /**
   * Determines if this queue is empty. 
   * 
   * @return true if queue is empty, false if otherwise
   */
  @Override
  public boolean isEmpty() {
    return this.front == null && this.back == null && this.size < 1;
  }
  
  /**
  * Creates and returns a String representation of this OrderQueue
  * using an enhanced-for loop. For example, a queue with three Orders
  * might look like this:
  * 1001: fries (2) -> 1002: shake (1) -> 1003: burger (3) -> END
  *
  * @return A String representation of the queue
  */
  @Override
  public String toString() {
    if (this.size == 0) return "";
    String qString = "";
    for (Order o : this) {
      qString += o.toString();
      qString += " -> ";
    }
    qString += "END";
    return qString;
  }
}
