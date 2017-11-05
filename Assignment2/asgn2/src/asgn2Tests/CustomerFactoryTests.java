package asgn2Tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Park YeongJe
 *
 */
public class CustomerFactoryTests {
	
	private Customer testCustomer;
	
	String name;
	String mobileNumber;
	int locationX;
	int locationY;
	String CustomerType;
	
	@Before
	public void setup() throws CustomerException{
		name = "DanielNguyen";
		mobileNumber = "0875980218";
		locationX = 5;
		locationY = 4;
	}
	
	@Test
	public void create_PickUpCustomer() throws CustomerException{
		CustomerType = "PUC";
		testCustomer = CustomerFactory.getCustomer(CustomerType, name, mobileNumber, 0, 0);
		assertEquals("Pick Up", testCustomer.getCustomerType());
	}
	
	@Test
	public void create_DroneCustomer() throws CustomerException{
		CustomerType = "DNC";
		testCustomer = CustomerFactory.getCustomer(CustomerType, name, mobileNumber, locationX, locationY);
		assertEquals("Drone Delivery", testCustomer.getCustomerType());
	}
	
	@Test
	public void create_DriverCustomer() throws CustomerException{
		CustomerType = "DVC";
		testCustomer = CustomerFactory.getCustomer(CustomerType, name, mobileNumber, locationX, locationY);
		assertEquals("Driver Delivery", testCustomer.getCustomerType());
	}
	
	@Test(expected=CustomerException.class)
	public void invalid_CustomerTypeWithLetter() throws CustomerException{
		CustomerType = "ASD";
		testCustomer = CustomerFactory.getCustomer(CustomerType, name, mobileNumber, locationX, locationY);
	}
	
	@Test(expected=CustomerException.class)
	public void invalid_CustomerTypeWithNumber() throws CustomerException{
		CustomerType = "696";
		testCustomer = CustomerFactory.getCustomer(CustomerType, name, mobileNumber, locationX, locationY);
	}
	
	@Test(expected=CustomerException.class)
	public void invalid_CustomerTypeWithEmptyString() throws CustomerException{
		CustomerType = "";
		testCustomer = CustomerFactory.getCustomer(CustomerType, name, mobileNumber, locationX, locationY);
	}
	
}
