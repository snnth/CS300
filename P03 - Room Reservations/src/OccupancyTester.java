//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P02 - Tester for Room and Person Class
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

import java.util.Arrays;

/**
 * @author Matthew Smith
 */
public class OccupancyTester {

	public static void main(String[] args) {
		System.out.println("1) testPerson(): " + testPerson());
		System.out.println("2) testRoomConstructor(): " + testRoomConstructor());
		System.out.println("3) testRoomAccessors(): " + testRoomAccessors());
		System.out.println("4) testRoomCheckIn(): " + testRoomCheckIn());
		System.out.println("5) testRoomCheckOut(): " + testRoomCheckOut());
		System.out.println("6) testRoomToString(): " + testRoomToString());
	}

	private static boolean testPerson() {
		boolean passesTest = true;

		{ // Test constructor assigns name and getName returns correct name
			Person expected = new Person("unique");
			Person actual = new Person("unique");
			if (!expected.getName().equals(actual.getName())) {
				System.out.println(" - Constructor does not assign name properly.");
				passesTest = false;
			}
		}

		{ // Test constructor sets waiting status to true
			boolean expected = new Person("unique").isWaiting();
			boolean actual = new Person("unique").isWaiting();
			if (expected != actual) {
				System.out.println(" - Constructor does not set waiting status to true by default.");
				passesTest = false;
			}
		}

		{ // Test toggleWaiting method toggles waiting boolean
			boolean expected = false;
			Person actual = new Person("unique");
			actual.toggleWaiting();
			if (expected != actual.isWaiting()) {
				System.out.println(" - toggleWaiting function does not toggle waiting status.");
				passesTest = false;
			}
		}

		{ // Test equals function returns true if objects are equal
			Person expected = new Person("unique");
			Person actual = new Person("unique");
			if (!actual.equals(expected)) {
				System.out.println(" - equals function does not work as intended.");
				passesTest = false;
			}
		}

		return passesTest;
	}

	private static boolean testRoomConstructor() {
		boolean passesTest = true;

		{ // Test constructor sets room name when unique
			String expected = "room";
			String actual = new Room("room", 10).getName();
			if (!actual.equals(expected)) {
				System.out.println(" - Constructor does not set room name when unique.");
				passesTest = false;
			}
			Room.clearNameList();
		}

		{ // Test constructor throws IAE if room name is already in use
			try {
				Room firstRoom = new Room("aRoom", 10);
				Room secondRoom = new Room("aRoom", 10);
				System.out.println(" - IllegalArgumentException not thrown when name is used twice.");
			} catch (IllegalArgumentException e) {
				passesTest = true;
			}
			Room.clearNameList();
		}

		{ // Test constructor throws IAE if capacity is less than or equal to zero
			try {
				Room firstRoom = new Room("first", 0);
				System.out.println(" - IllegalArgumentException not thrown when capacity is zero or less.");
			} catch (IllegalArgumentException e) {
				passesTest = true;
			}
			Room.clearNameList();
		}

		return passesTest;
	}

	private static boolean testRoomAccessors() {
		boolean passesTest = true;

		{ // Test static method getNames returns accurate list of current rooms
			String[] expected = new String[]{"first", "second", "third"};
			Room room1 = new Room("first", 1);
			Room room2 = new Room("second", 1);
			Room room3 = new Room("third", 1);
			String[] actual = Room.getNames();
			if (!Arrays.equals(actual, expected)) {
				System.out.println(" - getNames does not return accurate list of rooms.");
				passesTest = false;
			}
			Room.clearNameList();
		}

		{ // Test instance method getName returns accurate name of room
			String expected = "some room";
			Room someRoom = new Room("some room", 1);
			String actual = someRoom.getName();
			if (!actual.equals(expected)) {
				System.out.println(" - getName does not return accurate name of room.");
				passesTest = false;
			}
			Room.clearNameList();
		}

		{ // Test instance method getOccupancy returns accurate amount of people currently in room
			int expected = 2;
			Room someRoom = new Room("different room", 4);
			someRoom.checkIn(new Person("first guy"));
			someRoom.checkIn(new Person("first girl"));
			int actual = someRoom.getOccupancy();
			if (actual != expected) {
				System.out.println(" - getOccupancy does not return accurate amount of people in a room.");
				passesTest = false;
			}
			Room.clearNameList();
		}

		{ // Test instance method getCOVIDCapacity returns accurate restricted capacity if even
			int expected = 5;
			Room someRoom = new Room("another room", 10);
			int actual = someRoom.getCOVIDCapacity();
			if (actual != expected) {
				System.out.println(" - getCOVIDCapacity does not return accurate even capacity.");
				passesTest = false;
			}
			Room.clearNameList();
		}

		{ // Test instance method getCOVIDCapacity returns accurate restricted capacity if odd
			int expected = 5;
			Room someRoom = new Room("aaanother room", 9);
			int actual = someRoom.getCOVIDCapacity();
			if (actual != expected) {
				System.out.println(" - getCOVIDCapacity does not return accurate odd capacity.");
				passesTest = false;
			}
			Room.clearNameList();
		}

		{ // Test instance method getCapacity returns accurate non-restricted capacity
			int expected = 9;
			Room someRoom = new Room("aaaaaaaaanother room", 9);
			int actual = someRoom.getCapacity();
			if (actual != expected) {
				System.out.println(" - getCapacity does not return accurate capacity.");
				passesTest = false;
			}
			Room.clearNameList();
		}

		return passesTest;
	}

	private static boolean testRoomCheckIn() {
		boolean passesTest = true;

		{ // Tests instance method checkIn returns false if at current occupancy
			Room someRoom = new Room("yet another room", 1);
			someRoom.checkIn(new Person("first"));
			boolean expected = false;
			boolean actual = someRoom.checkIn(new Person("second"));
			if (actual != expected) {
				System.out.println(" - checkIn does not return false when at max occupancy");
				passesTest = false;
			}
		}

		{ // Tests instance method checkIn throw IAE if argument is null
			try {
				Room someRoom = new Room("some other another room", 5);
				someRoom.checkIn(null); // throw here
				System.out.println(" - checkIn didnt throw IllegalArgumentException when person is null");
				passesTest = false;
			} catch (IllegalArgumentException e) {
				passesTest = true;
			}
		}

		{ // Tests instance method checkIn throw IAE if person is in room already
			try {
				Room someRoom = new Room("some other another room", 5);
				Person somePerson = new Person("first");
				someRoom.checkIn(somePerson);
				someRoom.checkIn(new Person("first")); // throw here
				System.out.println(" - checkIn didnt throw IllegalArgumentException when dup person added");
				passesTest = false;
			} catch (IllegalArgumentException e) {
				passesTest = true;
			}
		}

		return passesTest;
	}

	private static boolean testRoomCheckOut() {
		boolean passesTest = true;

		{ // Test instance method checkOut removes person
			Room someRoom = new Room("a different room", 2);
			Person somePerson = new Person("first");
			Person diffPerson = new Person("second");
			someRoom.checkIn(somePerson);
			someRoom.checkIn(diffPerson);
			boolean expected = true;
			boolean actual = someRoom.checkOut(somePerson);
			if (actual != expected) {
				System.out.println(" - checkOut did not remove person");
				passesTest = false;
			}
		}

		{ // Test instance method checkOut throws IAE if argument is null
			try {
				Room someRoom = new Room("a different room", 2);
				someRoom.checkOut(null); // throw here
				System.out.println(" - checkOut did not throw IllegalArgumentException on null arg");
				passesTest = false;
			} catch (IllegalArgumentException e) {
				passesTest = true;
			}
		}

		{ // Test instance method checkOut returns false if room does not contain person
			Room someRoom = new Room("aaaa different room", 2);
			Person somePerson = new Person("first");
			someRoom.checkIn(somePerson);
			boolean expected = false;
			boolean actual = someRoom.checkOut(new Person("second"));
			if (actual != expected) {
				System.out.println(" - checkOut return false when the specified person could not be found");
				passesTest = false;
			}
		}

		return passesTest;
	}

	private static boolean testRoomToString() {
		boolean passesTest = true;

		{ // tests if strings are accurate
			Room newRoom = new Room("room", 4);
			newRoom.checkIn(new Person("first"));
			newRoom.checkIn(new Person("second"));
			String expected = "room\n===\nfirst\n-\nsecond\n-\n";
			String actual = newRoom.toString();
			if (!actual.equals(expected)) {
				System.out.println(" - toString does not return correct string:");
				passesTest = false;
			}

		}

		return passesTest;
	}
}
