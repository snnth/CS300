//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P09 - Movie Catalog
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
 * This class checks the correctness of the implementation of the methods defined in the class
 * MovieTree.
 */
public class MovieTreeTester {

  /**
   * Checks the correctness of the implementation of both addMovie() and toString() methods
   * implemented in the MovieTree class. This unit test considers at least the following scenarios.
   * (1) Create a new empty MovieTree, and check that its size is 0, it is empty, and that its
   * string representation is an empty string "". (2) try adding one movie and then check that the
   * addMovie() method call returns true, the tree is not empty, its size is 1, and the .toString()
   * called on the tree returns the expected output. (3) Try adding another movie which is smaller
   * that the movie at the root, (4) Try adding a third movie which is greater than the one at the
   * root, (5) Try adding at least two further movies such that one must be added at the left
   * subtree, and the other at the right subtree. For all the above scenarios, and more, double
   * check each time that size() method returns the expected value, the add method call returns
   * true, and that the .toString() method returns the expected string representation of the
   * contents of the binary search tree in an increasing order from the smallest to the greatest
   * movie with respect to year, rating, and then name. (6) Try adding a movie already stored in the
   * tree. Make sure that the addMovie() method call returned false, and that the size of the tree
   * did not change.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testAddMovieToStringSize() {
    // Tests correctness of creating a new MovieTree
    MovieTree catalog = new MovieTree();
    if (catalog.size() != 0 || !catalog.isEmpty() || !catalog.toString().isEmpty()) {
      System.out.println("Empty movie tree was not correctly instantiated: " +  catalog.size() 
          + catalog.isEmpty() + catalog.toString());
      return false;
    }
    
    // Tests correctness of addMovie() when catalog is empty.
    catalog.addMovie(new Movie(2003, 9.0, "The Matrix: Reloaded"));
    if (catalog.size() != 1 || catalog.isEmpty() || catalog.toString().isEmpty()) {
      System.out.println("Adding movie to empty catalog not correctly instantiated: \n"
          + catalog.size() + "\t" + catalog.isEmpty() + "\n" + catalog.toString());
      return false;
    }

    // Tests correctness of addMovie when adding a node smaller than root.
    catalog.addMovie(new Movie(1991, 9.9, "The Matrix"));
    if (catalog.size() != 2 || catalog.isEmpty()) {
      System.out.println("Adding movie less than node not correctly instantiated: \n"
          + catalog.size() + "\t" + catalog.isEmpty() + "\n" + catalog.toString());
      return false;
    }
    
    // Tests correctness of addMovie when adding a node larger than root.
    catalog.addMovie(new Movie(2003, 9.5, "The Matrix: Revolutions"));
    if (catalog.size() != 3 || catalog.isEmpty()) {
      System.out.println("Adding movie greater than node not correctly instantiated: \n"
          + catalog.size() + "\t" + catalog.isEmpty() + "\n" + catalog.toString());
      return false;
    }
  
    // Tests correctness of addMovie when adding a node > than roots left child but < than root.
    catalog.addMovie(new Movie(2001, 1.1, "The Matrix: DC Comics Adaptation"));
    if (catalog.size() != 4 || catalog.isEmpty()) {
      System.out.println("Adding movie > than roots left child but < root not correctly"
          + " instantiated: \n" + catalog.size() + "\t" + catalog.isEmpty() + "\n" 
          + catalog.toString());
      return false;
    }
      
    // Tests correctness of addMovie when adding a node < than roots right child but > than root.
    catalog.addMovie(new Movie(2003, 9.2, "The Matrix: Marvel Comics Adaptation"));
    if (catalog.size() != 5 || catalog.isEmpty()) {
      System.out.println("Adding movie < than roots left child but > root not correctly"
          + " instantiated: \n" + catalog.size() + "\t" + catalog.isEmpty() + "\n" 
          + catalog.toString());
      return false;
    }
    
    return true;
  }

  /**
   * This method checks mainly for the correctness of the MovieTree.contains() method. It must
   * consider at least the following test scenarios. (1) Create a new MovieTree. Then, check that
   * calling the contains() method on an empty MovieTree returns false. (2) Consider a MovieTree of
   * height 3 which contains at least 5 movies. Then, try to call contains() method to search for the
   * movie having a match at the root of the tree. (3) Then, search for a movie at the right and
   * left subtrees at different levels considering successful and unsuccessful search operations.
   * Make sure that the contains() method returns the expected output for every method call.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testContains() {
    // Tests correctness of contains() to return false when catalog is empty
    MovieTree catalog = new MovieTree();
    boolean expected = false;
    boolean actual = catalog.contains(2000, 9.9, "some movie");
    if (actual != expected) {
      System.out.println("Calling contains() on empty catalog return true.");
      return false;
    }
     
    // Tests correctness of contains() to return true at root with tree height of 4 and size of 5
    catalog.addMovie(new Movie(2003, 9.0, "The Matrix: Reloaded"));
    catalog.addMovie(new Movie(1991, 9.9, "The Matrix"));
    catalog.addMovie(new Movie(2003, 9.5, "The Matrix: Revolutions"));
    catalog.addMovie(new Movie(2001, 1.1, "The Matrix: DC Comics Adaptation"));
    catalog.addMovie(new Movie(2003, 9.2, "The Matrix: Marvel Comics Adaptation"));
    catalog.addMovie(new Movie(2005, 9.2, "The Animatrix"));
    catalog.addMovie(new Movie(2007, 9.2, "The wannabe Matrix"));
    expected = true;
    actual = catalog.contains(2003, 9.0, "The Matrix: Reloaded");
    if (actual != expected) {
      System.out.println("Calling contains() on catalog when match is at root returns false.");
      return false;
    }

    // Tests correctness of contains() to return true when movie is deep right in tree
    expected = true;
    actual = catalog.contains(2003, 9.2, "The Matrix: Marvel Comics Adaptation");
    if (actual != expected) {
      System.out.println("Calling contains() on catalog when match is deep right return false.");
      return false;
    }

    // Tests correctness of contains() to return true when movie is deep left in tree
    expected = true;
    actual = catalog.contains(1991, 9.9, "The Matrix");
    if (actual != expected) {
      System.out.println("Calling contains() on catalog when match is deep left returns false.");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.height() method. This test must consider several
   * scenarios such as, (1) ensures that the height of an empty movie tree is zero. (2) ensures that
   * the height of a tree which consists of only one node is 1. (3) ensures that the height of a
   * MovieTree with the following structure for instance, is 4. 
   *       (*)
   *     /    \
   *  (*)      (*)
   *    \     /  \
   *    (*) (*)  (*)
   *             /
   *           (*)
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testHeight() {
    // Tests correctness of height() to return 0 when catalog is empty
    MovieTree catalog = new MovieTree();
    int expected = 0;
    int actual = catalog.height();
    if (actual != expected) {
      System.out.println("Calling height() on empty catalog does not return 0.");
      return false;
    }

    // Tests correctness of height() to return 1 when catalog only has one node
    catalog.addMovie(new Movie(2003, 9.0, "The Matrix: Reloaded"));
    expected = 1;
    actual = catalog.height();
    if (actual != expected) {
      System.out.println("Calling height() on empty catalog does not return 1.");
      return false;
    }

    // Tests correctness of height() to return 4 when catalog has height of 4
    catalog.addMovie(new Movie(1991, 9.9, "The Matrix"));
    catalog.addMovie(new Movie(2003, 9.5, "The Matrix: Revolutions"));
    catalog.addMovie(new Movie(2001, 1.1, "The Matrix: DC Comics Adaptation"));
    catalog.addMovie(new Movie(2003, 9.2, "The Matrix: Marvel Comics Adaptation"));
    catalog.addMovie(new Movie(2005, 9.2, "The Animatrix"));
    catalog.addMovie(new Movie(2007, 9.2, "The wannabe Matrix"));
    expected = 4;
    actual = catalog.height();
    if (actual != expected) {
      System.out.println("Calling height() on empty catalog does not return 4.");
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.getBestMovie() method.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testGetBestMovie() {

    try { 
      // Tests correctness of getBestMovie() when catalog is empty
      MovieTree catalog = new MovieTree();
      Movie expected = null;
      Movie actual = catalog.getBestMovie();
      if (actual != expected) {
        System.out.println("Calling getBestMovie() on an empty tree does not funciton as intended");
        return false;
      }

      // Tests correctness of getBestMovie() when catalog only has one node
      expected = new Movie(2000, 8.0, "movie");
      catalog.addMovie(expected);
      actual = catalog.getBestMovie();
      if (!actual.equals(expected)) {
        System.out.println("Calling getBestMovie() on an empty tree does not funciton as intended");
        return false;
      }

      // Tests correctness of getBestMovie() when catalog only has multiples nodes
      catalog.addMovie(new Movie(1991, 9.9, "The Matrix"));
      catalog.addMovie(new Movie(2003, 9.5, "The Matrix: Revolutions"));
      catalog.addMovie(new Movie(2001, 1.1, "The Matrix: DC Comics Adaptation"));
      catalog.addMovie(new Movie(2003, 9.2, "The Matrix: Marvel Comics Adaptation"));
      expected = new Movie(2007, 9.8, "The wannabe Matrix");
      catalog.addMovie(new Movie(2007, 9.6, "The dummy Matrix"));
      catalog.addMovie(expected);
      catalog.addMovie(new Movie(2005, 9.2, "The Animatrix"));
      actual = catalog.getBestMovie();
      if (!actual.equals(expected)) {
        System.out.println("Calling getBestMovie() on an empty tree does not funciton as intended");
        return false;
      }
    } catch (NoSuchElementException e) {
      System.out.println("A NoSuchElementException was thrown: \n" + e.getStackTrace());
      return false;
    } catch (Exception e) {
      System.out.println("An Exception was thrown: \n" + e.getStackTrace());
      return false;
    }

    return true;
  }

  /**
   * Checks for the correctness of MovieTree.lookup() method. This test must consider at least 3
   * test scenarios. (1) Ensures that the MovieTree.lookup() method throws a NoSuchElementException
   * when called on an empty tree. (2) Ensures that the MovieTree.lookup() method returns an array
   * list which contains all the movies satisfying the search criteria of year and rating, when called
   * on a non empty movie tree with one match, and two matches and more. Vary your search criteria
   * such that the lookup() method must check in left and right subtrees. (3) Ensures that the
   * MovieTree.lookup() method throws a NoSuchElementException when called on a non-empty movie tree
   * with no search results found.
   * 
   * @return true when this test verifies a correct functionality, and false otherwise
   */
  public static boolean testLookup() {
    
    try {
      try { // Test correctness of lookup() to throw NoSuchElementException when tree is empty
        MovieTree catalog = new MovieTree();
        catalog.lookup(2000, 1.0); 
        System.out.println("A NoSuchElementException was NOT thrown when tree is empty.");
        return false;
      } catch (NoSuchElementException e) {
        // works if here
      }

      // Test correctness of lookup() to find all matches within tree
      MovieTree catalog = new MovieTree();
      catalog.addMovie(new Movie(2001, 9.9, "The Matrix"));
      catalog.addMovie(new Movie(1999, 9.5, "The Matrix: Revolutions"));
      catalog.addMovie(new Movie(2002, 1.1, "The Matrix: DC Comics Adaptation"));
      catalog.addMovie(new Movie(1999, 9.5, "The Matrix: Marvel Comics Adaptation"));
      catalog.addMovie(new Movie(1999, 9.5, "The Matrix: trix"));
      catalog.addMovie(new Movie(2007, 9.5, "The wannabe Matrix"));
      catalog.addMovie(new Movie(1995, 9.5, "The wannabe Matrix"));
      int expected = 3;
      int actual = catalog.lookup(1999, 9.5).size();
      if (actual != expected) {
        System.out.println("Lookup() does add the correct amount of movies that match the query.");
        return false;
      }

      try { // Test correctness of lookup() to throw NoSuchElementException when tree is empty
        MovieTree otherCatalog = new MovieTree();
        catalog.addMovie(new Movie(2001, 9.9, "The Matrix"));
        catalog.addMovie(new Movie(1999, 9.5, "The Matrix: Revolutions"));
        catalog.addMovie(new Movie(2002, 1.1, "The Matrix: DC Comics Adaptation"));
        catalog.addMovie(new Movie(1999, 9.5, "The Matrix: Marvel Comics Adaptation"));
        catalog.addMovie(new Movie(1999, 9.5, "The Matrix: trix"));
        catalog.addMovie(new Movie(2007, 9.5, "The wannabe Matrix"));
        catalog.addMovie(new Movie(1995, 9.5, "The wannabe Matrix"));
        otherCatalog.lookup(2019, 5.0); 
        System.out.println("A NoSuchElementException was NOT thrown when no movies were found.");
        return false;
      } catch (NoSuchElementException e) {
        // works if here
      }
    } catch (NoSuchElementException e) {
      System.out.println("A NoSuchElementException was thrown: \n" + e.getStackTrace());
      return false;
    } catch (Exception e) {
      System.out.println("An Exception was thrown: \n" + e.getStackTrace());
      return false;
    }

    return true;
  }

  /**
   * Calls the test methods
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testAddMovieToStringSize: " + testAddMovieToStringSize());
    System.out.println("testContains: " + testContains());
    System.out.println("testHeight: " + testHeight());
    System.out.println("testGetBestMovie: " + testGetBestMovie());
    System.out.println("testLookup: " + testLookup());
  }
}
