package asgn2Restaurant;

import java.util.ArrayList;
import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;

import java.io.*;
import java.time.LocalTime;
import asgn2Pizzas.PizzaFactory;
import asgn2Customers.CustomerFactory;

/**
 *
 * A class that contains methods that use the information in the log file to
 * return Pizza and Customer object - either as an individual Pizza/Customer
 * object or as an ArrayList of Pizza/Customer objects.
 * 
 * @author Park Yeongje and Daniel Nguyen
 *
 */
public class LogHandler {

	/**
	 * Returns an ArrayList of Customer objects from the information contained
	 * in the log file ordered as they appear in the log file.
	 * 
	 * @param filename
	 *            The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained
	 *         in the log file ordered as they appear in the log file.
	 * @throws CustomerException
	 *             If the log file contains semantic errors leading that violate
	 *             the customer constraints listed in Section 5.3 of the
	 *             Assignment Specification or contain an invalid customer code
	 *             (passed by another class).
	 * @throws LogHandlerException
	 *             If there was a problem with the log file not related to the
	 *             semantic errors above
	 * 
	 */
	public static ArrayList<Customer> populateCustomerDataset(String filename)
			throws CustomerException, LogHandlerException {
		// TO DO
		ArrayList<Customer> arrayCustomer = new ArrayList<Customer>();

		try {
			BufferedReader read;
			read = new BufferedReader(new FileReader(filename));
			String line = null;

			while ((line = read.readLine()) != null) {
				arrayCustomer.add(createCustomer(line));
			}
			read.close();
		} catch (LogHandlerException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw new LogHandlerException("There was a problem with the log file");
		} catch (CustomerException e) {
			throw new CustomerException("invalid log file");
		}
		return arrayCustomer;
	}

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in
	 * the log file ordered as they appear in the log file. .
	 * 
	 * @param filename
	 *            The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in
	 *         the log file ordered as they appear in the log file. .
	 * @throws PizzaException
	 *             If the log file contains semantic errors leading that violate
	 *             the pizza constraints listed in Section 5.3 of the Assignment
	 *             Specification or contain an invalid pizza code (passed by
	 *             another class).
	 * @throws LogHandlerException
	 *             If there was a problem with the log file not related to the
	 *             semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException {
		// TO DO
		ArrayList<Pizza> arrayPizza = new ArrayList<Pizza>();

		try {
			BufferedReader read;
			read = new BufferedReader(new FileReader(filename));
			String line = null;
			while ((line = read.readLine()) != null) {
				arrayPizza.add(createPizza(line));
			}
			read.close();

		} catch (LogHandlerException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
			throw new LogHandlerException("There was a problem with the log file");
		} catch (PizzaException e) {
			throw new PizzaException("invalid log file");
		}
		return arrayPizza;
	}

	/**
	 * Creates a Customer object by parsing the information contained in a
	 * single line of the log file. The format of each line is outlined in
	 * Section 5.3 of the Assignment Specification.
	 * 
	 * @param line
	 *            - A line from the log file
	 * @return- A Customer object containing the information from the line in
	 *          the log file
	 * @throws CustomerException
	 *             - If the log file contains semantic errors leading that
	 *             violate the customer constraints listed in Section 5.3 of the
	 *             Assignment Specification or contain an invalid customer code
	 *             (passed by another class).
	 * @throws LogHandlerException
	 *             - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException {
		// TO DO
		String[] result;
		String customerCode;
		String name;
		String mobileNumber;
		int locationX;
		int locationY;
		try {
			result = line.split(",");
		} catch (Exception e) {
			throw new LogHandlerException("There was a problem parsing the line from the log file");
		}
		try {
			customerCode = result[4];
			name = result[2];
			mobileNumber = result[3];
			locationX = Integer.parseInt(result[5]);
			locationY = Integer.parseInt(result[6]);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			throw new LogHandlerException("Some parts are failed for customer data");
		}
		return CustomerFactory.getCustomer(customerCode, name, mobileNumber, locationX, locationY);

	}

	/**
	 * Creates a Pizza object by parsing the information contained in a single
	 * line of the log file. The format of each line is outlined in Section 5.3
	 * of the Assignment Specification.
	 * 
	 * @param line
	 *            - A line from the log file
	 * @return- A Pizza object containing the information from the line in the
	 *          log file
	 * @throws PizzaException
	 *             If the log file contains semantic errors leading that violate
	 *             the pizza constraints listed in Section 5.3 of the Assignment
	 *             Specification or contain an invalid pizza code (passed by
	 *             another class).
	 * @throws LogHandlerException
	 *             - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException {
		// TO DO
		String[] results;
		int quantity;
		String pizzaCode;
		LocalTime orderTime;
		LocalTime deliveryTime;
		try {
			results = line.split(",");
		} catch (Exception e) {
			throw new LogHandlerException("There was a problem parsing the line from the log file");
		}

		try {
			quantity = Integer.parseInt(results[8]);
			pizzaCode = results[7];
			orderTime = LocalTime.parse(results[0]);
			deliveryTime = LocalTime.parse(results[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new LogHandlerException("Some parts are failed for pizza data");
		}

		return PizzaFactory.getPizza(pizzaCode, quantity, orderTime, deliveryTime);
	}

}
