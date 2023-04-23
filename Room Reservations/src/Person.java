//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P02 - Person Class
// Course:   CS 300 Fall 2020
//
// Author:   Matthew Smith
// Email:    mjsmith44@wisc.edu
// Lecturer: Hobbes LeGault
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         None
// Online Sources:  None
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Person object in respect to a room. Create instance via naming and can toggle if this person
 * is in a room or not.
 *
 * @author Matthew Smith
 */
public class Person {

    private String name;
    private boolean isWaiting;

    public Person(String name) {
        this.name = name;
        this.isWaiting = true;
    }

    /**
     * Returns name of person.
     *
     * @return name of person
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns waiting status of person.
     *
     * @return true if person is waiting for room, false if person is already in a room
     */
    public boolean isWaiting() {
        return this.isWaiting;
    }

    /**
     * Toggle waiting status of person.
     */
    public void toggleWaiting() {
        this.isWaiting = !this.isWaiting;
    }

    /**
     * Checks to see if objects is an instance of Person and the names match.
     *
     * @param o
     * @return true if equal, false if not
     */
    public boolean equals(Object o) {
        if (o instanceof Person) {
            return this.name.equals(((Person) o).name);
        }
        return false;
    }
}
