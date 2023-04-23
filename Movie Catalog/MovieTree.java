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

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Movie Catalog implemented as a Binary Search Tree. Allows for adding movies, getting served a
 * recommendation, and looking up a movie.
 */
public class MovieTree {
  private BSTNode<Movie> root; // root of this movie BST
  private int size; // size of this movie tree

  /**
   * Checks whether this binary search tree (BST) is empty
   * 
   * @return true if this MovieTree is empty, false otherwise
   */
  public boolean isEmpty() {
    return (this.root == null || this.size == 0) ? true : false;
  }

  /**
   * Returns the number of movies stored in this BST.
   * 
   * @return the size of this MovieTree
   */
  public int size() {
    return this.size;
  }


  /**
   * Adds a new movie to this MovieTree
   * 
   * @param newMovie a new movie to add to this BST.
   * @return true if the newMovie was successfully added to this BST, and returns false if there is
   *         a match with this movie already stored in this BST.
   */
  public boolean addMovie(Movie newMovie) {
    if (isEmpty()) { // Add newMovie to an empty MovieTree
      this.root = new BSTNode<Movie>(newMovie);
      this.size = 1;
      return true;
    } 
    
    // Add newMovie to an non-empty MovieTree
    boolean addedMovie = addMovieHelper(newMovie, this.root);
    if (addedMovie) {
      ++this.size;
    }
    return addedMovie;
  }

  /**
   * Recursive helper method to add a new movie to a MovieTree rooted at current.
   * 
   * @param current  The "root" of the subtree we are inserting new movie into.
   * @param newMovie The movie to be added to a BST rooted at current.
   * @return true if the newMovie was successfully added to this MovieTree, false otherwise
   */
  protected static boolean addMovieHelper(Movie newMovie, BSTNode<Movie> current) {
    int comparison = current.getData().compareTo(newMovie);

    // if new movie is equal to current movie it can not add
    if (comparison == 0) {
      return false;
    }

    // if new movie is less than current movie move left and add unless theres a node present
    if (comparison > 0) {
      if (current.getLeft() == null) {
        current.setLeft(new BSTNode<Movie>(newMovie));
        return true;
      } else {
        return addMovieHelper(newMovie, current.getLeft());
      }
    }

    // if new movie is greater than current movie move right and add unless theres a node present
    if (comparison < 0) {
      if (current.getRight() == null) {
        current.setRight(new BSTNode<Movie>(newMovie));
        return true;
      } else {
        return addMovieHelper(newMovie, current.getRight());
      }
    }

    return false; // here for compliation
  }

  /**
   * Returns a String representation of all the movies stored within this BST in the increasing
   * order, separatingd by a newline "\n". For instance
   * 
   * "[(Year: 1988) (Rate: 7.0) (Name: Light Years)]" + "\n" + 
   * "[(Year: 2015) (Rate: 6.5) (Name: Cinderella)]" + "\n"
   * 
   * @return a String representation of all the movies stored within this BST sorted in an
   *         increasing order with respect to the result of Movie.compareTo() method (year, rating,
   *         name). Returns an empty string "" if this BST is empty.
   */
  @Override
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method which returns a String representation of the BST rooted at current. An
   * example of the String representation of the contents of a MovieTree is provided in the
   * description of the above toString() method.
   * 
   * @param current reference to the current movie within this BST (root of a subtree)
   * @return a String representation of all the movies stored in the sub-tree rooted at current in
   *         increasing order with respect to the result of Movie.compareTo() method (year, rating,
   *         name). Returns an empty String "" if current is null.
   */
  protected static String toStringHelper(BSTNode<Movie> current) {
    // base case
    if (current == null) {
      return "";
    }

    return toStringHelper(current.getLeft()) + "[(Year: " 
        + current.getData().getYear() + ") (Rate: " + current.getData().getRating() + ") (Name: "
        + current.getData().getName() + ")]\n" + toStringHelper(current.getRight());
  }

  /**
   * Computes and returns the height of this BST, counting the number of NODES from root to the
   * deepest leaf.
   * 
   * @return the height of this Binary Search Tree
   */
  public int height() {
    return heightHelper(root);
  }

  /**
   * Recursive helper method that computes the height of the subtree rooted at current counting the
   * number of nodes and NOT the number of edges from current to the deepest leaf
   * 
   * @param current pointer to the current BSTNode within a MovieTree (root of a subtree)
   * @return height of the subtree rooted at current
   */
  protected static int heightHelper(BSTNode<Movie> current) {
    if (current == null) {
      return 0;
    }

    int leftHeight = heightHelper(current.getLeft());
    int rightHeight = heightHelper(current.getRight());
    return (leftHeight >= rightHeight) ? leftHeight + 1 : rightHeight + 1;
  }

  /**
   * Checks whether this MovieTree contains a movie given its name, production year, and rating.
   * 
   * @param year   year of production of the movie to search
   * @param rating rating of the movie to search
   * @param name   name of the movie to search
   * @return true if there is a match with this movie in this BST, and false otherwise
   */
  public boolean contains(int year, double rating, String name) {
    if (this.root == null || this.size == 0) {
      return false;
    }

    return containsHelper(new Movie(year, rating, name), this.root);
  }

  /**
   * Recursive helper method to search wether there is a match with a given movie in the subtree
   * rooted at current
   * 
   * @param target  a reference to a movie we are searching for a match in the BST rooted at
   *                current.
   * @param current "root" of the subtree we are checking whether it contains a match to target.
   * @return true if match found and false otherwise
   */
  protected boolean containsHelper(Movie target, BSTNode<Movie> current) {
    if (current == null) {
      return false;
    }

    int comparison = current.getData().compareTo(target);

    // if searched movie is equal to current movie we have a match
    if (comparison == 0) {
      return true;
    }

    // if searched movie is less than current movie move left and search more (2000 - 1900 = 10)
    if (comparison > 0) {
      return containsHelper(target, current.getLeft());
    }

    // if searched movie is greater than current movi move right and search more(1990 - 2000 = -10)
    if (comparison < 0) {
      return containsHelper(target, current.getRight());
    }

    return false; // here for compliation
  }


  /**
   * Gets the best (maximum) movie in this BST
   * 
   * @return the best (largest) movie (the most recent, highest rated, and having the largest name)
   *         in this MovieTree, and null if this tree is empty.
   */
  public Movie getBestMovie() {
    if (this.root == null || this.size == 0) {
      return null;
    }

    return getBestMovieHelper(this.root);
  }

  /**
   * Recursive helper method to search for the best movie (the most recent, 
   * highest rated, and having the largest name) in the subtree starting current
   * 
   * @return the best (largest) movie (the most recent, highest rated, and having the largest name)
   *         in this MovieTree.
   */
  private Movie getBestMovieHelper(BSTNode<Movie> current) {
    if (current.getRight() == null) {
      return current.getData();
    }

    return getBestMovieHelper(current.getRight());
  }


  /**
   * Search for movies given the year and minimum rating as lookup key.
   * 
   * @param year   production year of a movie
   * @param rating rating value of a movie
   * @return a list of movies whose year equals our lookup year key and having a rating greater or
   *         equal to a given rating.
   * @throws a NoSuchElementException with a descriptive error message if there is no Movie found in
   *           this BST having the provided year and a rating greater or equal to the provided
   *           rating
   */
  public ArrayList<Movie> lookup(int year, double rating) {
    ArrayList<Movie> matchedMovies = new ArrayList<Movie>();
    lookupHelper(year, rating, this.root, matchedMovies);

    if (matchedMovies.isEmpty()) {
      throw new NoSuchElementException("Unfortunately, there are no movies given from " + year 
          + " with a rating of " + rating + " in this catalog.");
    }
    
    return matchedMovies;
  }

  /**
   * Recursive helper method to lookup the list of movies given their year of production and a
   * minimum value of rating
   * 
   * @param year      the year we would like to search for a movie
   * @param rating    the minimum rating we would like to search for a movie
   * @param movieList an arraylist which stores the list of movies matching our search criteria
   *                  (exact year of production and having at least the provided rating) within the
   *                  subtree rooted at current
   * @param current   "root" of the subtree we are looking for a match to find within it.
   */
  protected void lookupHelper(int year, double rating, BSTNode<Movie> current,
      ArrayList<Movie> movieList) {
    if (current == null) {
      return;
    }

    // if movie matches paramaters, add it.
    if (current.getData().getYear() == year 
        && Math.abs(current.getData().getRating() - rating) < 0.0001) 
    {
      movieList.add(current.getData());
    }

    // pre-order traversal algorithm
    lookupHelper(year, rating, current.getLeft(), movieList);
    lookupHelper(year, rating, current.getRight(), movieList);
  }
}
