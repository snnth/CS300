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
 * Class for iterating over the order queue.
 */
public class OrderIterator implements Iterator<Order> {

  private LinkedOrder current;

  /**
   * Constructor. Creates an order iterator at the LinkedOrder passed in. does not care if
   * order is null.
   * 
   * @param start LinkedOrder to start the iterator at
   */
  public OrderIterator(LinkedOrder start) {
    this.current = start;
  }

  /**
   * Determines if a current order exists.
   */
  @Override
  public boolean hasNext() {
    return (this.current != null) ? true : false;
  }

  /**
   * Returns current order and advances iterator to next one.
   */
  @Override
  public Order next() throws NoSuchElementException {
    if (this.current == null) {
      throw new NoSuchElementException("Iterator has nothing more to return.");
    }
    Order returnOrder = this.current.getOrder();
    this.current = current.getNext();

    return returnOrder;
  }
  
}
