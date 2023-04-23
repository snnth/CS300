///////////////////////// TOP OF FILE COMMENT BLOCK ////////////////////////////
//
// Title:           CQ2 - Bonus Part
// Course:          CS 300, Spring 2021
//
// Author:          Matthew Smith
// Email:           mjsmith44@wisc.edu
// Lecturer's Name: Hobbes Legault
//
///////////////////////////////// CITATIONS ////////////////////////////////////
//
// Persons: None
// Sources: None
//
/////////////////////////////// 80 COLUMNS WIDE ////////////////////////////////

/**
 * Represents a special type of bank account which earns extra interest, but has a penalty to
 * withdraw operations.
 *
 * @author Matthew Smith
 */
public class MoneyMarketAccount extends BankAccount{

	private final double PENALTY_RATE;
	private final double INTEREST_RATE;

	/**
	 * Creates a new bank account under the bank "Fantastic Bank" with an initial balance and assigns
	 * it a unique account number. Initial balance must be no less than $10.
	 *
	 * @param initialBalance amount equal to or greater than $10 to initially store in this bank
	 *                       account
	 */
	public MoneyMarketAccount(double initialBalance) {
		super(initialBalance);

		PENALTY_RATE = 0.1;
		INTEREST_RATE = 0.05;
	}

	/**
	 * Adds interest to the balance of this account with respect to the interest rate (10%).
	 */
	public void addInterest() {
		deposit(balance() * INTEREST_RATE);
	}

	/**
	 * Withdraws the given amount adjusted internally to fit the penalty rate.
	 *
	 * @param amount amount, which be greater than zero and equal to or less than the current balance
	 *               of the account, to withdraw from this bank account
	 * @return true if withdraw was an success, false if otherwise
	 */
	@Override
	public boolean withdraw(double amount) {
		return super.withdraw(amount + (amount * PENALTY_RATE));
	}

	/**
	 * Returns a formatted string detailing this money market bank account.
	 *
	 * @return a formatted string detailing this money market bank account
	 */
	public String toString() {
		return "Money Market Account #" + this.NUMBER + ": $" + balance();
	}
}
