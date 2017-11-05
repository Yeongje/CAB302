package asgn2Customers;

import asgn2Exceptions.CustomerException;

/**
 * An abstract class to represent a customer at the Pizza Palace restaurant. The
 * Customer class is used as a base class of PickUpCustomer,
 * DriverDeliveryCustomer and DroneDeliverCustomer. Each of these subclasses
 * overwrites the abstract method getDeliveryDistance. A description of the
 * class's fields and their constraints is provided in Section 5.2 of the
 * Assignment Specification.
 * 
 * @author Daniel Nguyen
 */
public abstract class Customer {

	/**
	 * This class represents a customer of the Pizza Palace restaurant. A
	 * detailed description of the class's fields and parameters is provided in
	 * the Assignment Specification, in particular in Section 5.2. A
	 * CustomerException is thrown if the any of the constraints listed in
	 * Section 5.2 of the Assignment Specification are violated.
	 * 
	 * <P>
	 * PRE: True
	 * <P>
	 * POST: All field values are set
	 * 
	 * @param name
	 *            - The Customer's name
	 * @param mobileNumber
	 *            - The customer mobile number
	 * @param locationX
	 *            - The customer x location relative to the Pizza Palace
	 *            Restaurant measured in units of 'blocks'
	 * @param locationY
	 *            - The customer y location relative to the Pizza Palace
	 *            Restaurant measured in units of 'blocks'
	 * @param type
	 *            - A human understandable description of this Customer type
	 * @throws CustomerException
	 *             if supplied parameters are invalid
	 * 
	 */
	private String CustomerName;
	private String MobileNumber;
	private int locationX;
	private int locationY;
	private String CustomerType;

	public Customer(String name, String mobileNumber, int locationX, int locationY, String type)
			throws CustomerException {
		this.CustomerName = name;
		this.MobileNumber = mobileNumber;
		this.locationX = locationX;
		this.locationY = locationY;
		this.CustomerType = type;
		// Check if name meets the requirement
		// Between 1 - 20 characters and is not just whitespace
		if (name.isEmpty() || name.trim().isEmpty() || name.length() > 20) {
			throw new CustomerException(
					"Invalid name.The name of the customer is only between 1 and 20 characters long and cannot be only whitespaces.");
		}
		// Check if mobileNumber meets the requirement
		// Must be exactly 10 digits and start with 0
		if (mobileNumber.length() != 10 || mobileNumber.charAt(0) != '0'){
			throw new CustomerException("Invalid phone number. The mobile number must be 10 digits long and begin with '0'.");
		}
		// Check if the location is 10 blocks away in terms of X and Y
		boolean isXTooFar = (locationX < -10) || (locationX > 10);
		boolean isYTooFar = (locationY < -10) || (locationY > 10);
		if (isXTooFar || isYTooFar) {
			throw new CustomerException("Location does is too far away from the restaurant");
		}

	}

	/**
	 * Returns the Customer's name.
	 * 
	 * @return The Customer's name.
	 */
	public final String getName() {
		return CustomerName;
	}

	/**
	 * Returns the Customer's mobile number.
	 * 
	 * @return The Customer's mobile number.
	 */
	public final String getMobileNumber() {
		return MobileNumber;
	}

	/**
	 * Returns a human understandable description of the Customer's type. The
	 * valid alternatives are listed in Section 5.2 of the Assignment
	 * Specification.
	 * 
	 * @return A human understandable description of the Customer's type.
	 */
	public final String getCustomerType() {
		return CustomerType;
	}

	/**
	 * Returns the Customer's X location which is the number of blocks East or
	 * West that the Customer is located relative to the Pizza Palace
	 * restaurant.
	 * 
	 * @return The Customer's X location
	 */
	public final int getLocationX() {
		return locationX;
	}

	/**
	 * Returns the Customer's Y location which is the number of blocks North or
	 * South that the Customer is located relative to the Pizza Palace
	 * restaurant.
	 * 
	 * @return The Customer's Y location
	 */
	public final int getLocationY() {
		return locationY;
	}

	/**
	 * An abstract method that returns the distance between the Customer and the
	 * restaurant depending on the mode of delivery.
	 * 
	 * @return The distance between the restaurant and the Customer depending on
	 *         the mode of delivery.
	 */
	public abstract double getDeliveryDistance();

	/**
	 * Compares *this* Customer object with an instance of an *other* Customer
	 * object and returns true if if the two objects are equivalent, that is, if
	 * the values exposed by public methods are equal. You do not need to test
	 * this method.
	 * 
	 * @return true if *this* Customer object and the *other* Customer object
	 *         have the same values returned for
	 *         getName(),getMobileNumber(),getLocationX(),getLocationY(),getCustomerType().
	 */
	@Override
	public boolean equals(Object other) {
		Customer otherCustomer = (Customer) other;

		return ((this.getName().equals(otherCustomer.getName()))
				&& (this.getMobileNumber().equals(otherCustomer.getMobileNumber()))
				&& (this.getLocationX() == otherCustomer.getLocationX())
				&& (this.getLocationY() == otherCustomer.getLocationY())
				&& (this.getCustomerType().equals(otherCustomer.getCustomerType())));
	}

}
