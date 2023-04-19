//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P03 - Room Class
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

import java.util.ArrayList;

/**
 * Room class that holds persons. Defaults to COVID restrictions: each person added must be
 * one spot away from each other.
 *
 * @author Matthew Smith
 */
public class Room {

	private static ArrayList<String> names = new ArrayList<String>();
	private String name;
	private Person[] occupants;
	private int currentOccupancy;

	/**
	 * Constructor method that creates a room with the given name and Person capacity.
	 *
	 * @param name
	 * @param capacity
	 */
	public Room(String name, int capacity) {
		for (String uniqueName : names) {
			if (name.equals(uniqueName))
				throw new IllegalArgumentException("The name \"" + name + "\" is already in use!");
		}

		if (capacity <= 0)
			throw new IllegalArgumentException("Capacity can not be zero or less");

		this.occupants = new Person[capacity];
		this.name = name;
		names.add(name);
	}

	/**
	 * Checks Person into room.
	 *
	 * @param in person to be added
	 * @return true if person is added to room, false if not
	 */
	public boolean checkIn(Person in) {
		if (currentOccupancy == this.getCOVIDCapacity())
			return false;

		if (in == null || this.contains(in))
			throw new IllegalArgumentException("Argument is NULL or person is already in room!");

		for (int i = 0; i < occupants.length; i =+ 2) {
			if (occupants[i] == null) {
				occupants[i] = in;
				in.toggleWaiting();
				currentOccupancy++;
				return true;
			}
		}

		return false;
	}

	/**
	 * Checks Person out of room.
	 *
	 * @param out person to be removed
	 * @return true of person is removed from room, false if not
	 */
	public boolean checkOut(Person out) {
		if (out == null)
			throw new IllegalArgumentException("Argument is NULL!");

		if (!this.contains(out))
			return false;

		for (int i = 0; i < occupants.length; i =+ 2) {
			if (out.equals(occupants[i])) {
				out.toggleWaiting();
				currentOccupancy--;
				occupants[i] = null;
				return true;
			}
		}

		return false;
	}

	/**
	 * Returns the current list of Room names as an array of Strings. Names will appear in order
	 * they were created.
	 *
	 * @return list of names in room
	 */
	public static String[] getNames() {
		String[] arrayNames = new String[names.size()];
		for (int i = 0; i < names.size(); i++) {
			arrayNames[i] = names.get(i);
		}

		return arrayNames;
	}

	/**
	 * Returns name of room.
	 *
	 * @return name of room
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Returns how many Persons are currently in the room.
	 *
	 * @return number of people in the room
	 */
	public int getOccupancy() {
		return this.currentOccupancy;
	}

	/**
	 * Returns how many Persons could be in the room with COVID restrictions.
	 *
	 * @return capacity of room with restriction
	 */
	public int getCOVIDCapacity() {
		return this.occupants.length % 2 == 1 ? (this.occupants.length/2) + 1 : this.occupants.length/2;
	}

	/**
	 * Returns how many Persons could be in the room without COVID restrictions.
	 *
	 * @return
	 */
	public int getCapacity() {
		return this.occupants.length;
	}

	/**
	 * Determines if Person is in this room.
	 *
	 * @param p person in question
	 * @return true if person is in this room, false if not
	 */
	public boolean contains(Person p) {
		if (occupants.length == 0)
			return false;

		for (int i = 0; i < occupants.length; i++) {
			if (occupants[i] == null)
				continue;

			if (occupants[i].getName().equals(p.getName()))
				return true;
		}

		return false;
	}

	/**
	 * Returns String version of room name, size, and Persons contained.
	 *
	 * @return string of room name, size and persons contained
	 */
	public String toString() {
		String roomString = "";

		roomString += this.name + "\n===\n";
		for (int i = 0; i < this.occupants.length; i++) {
			if (this.occupants[i] != null)
				roomString += this.occupants[i].getName() + "\n";
			else
				roomString += "-\n";
		}

		return roomString;
	}

	/**
	 * Clears class static array list. !! USED ONLY FOR TESTING CLASS !!
	 */
	public static void clearNameList() {
		for (int i = 0; i < names.size(); i++) {
			names.remove(i);
		}
	}
}
