package asgn2Tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;
import asgn2Customers.DroneDeliveryCustomer;
import asgn2Customers.PickUpCustomer;
import asgn2Exceptions.CustomerException;



/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Park YeongJe
 * 
 *
 */
public class CustomerTests {
	Customer driverCustomer;
	Customer droneCustomer;
	Customer pickupCustomer;
	
	String test_name1;
	String test_name2;
	String test_name3;
	
	String mobileNumber1;
	String mobileNumber2;
	String mobileNumber3;
	
	int locationX1;
	int locationY1;
	int locationX2;
	int locationY2;
	int locationX3;
	int locationY3;
		
	@Before
	public void setup() throws CustomerException{
		//setup testing object variables
		test_name1 = "ChandlerBing";
		test_name2 = "JoeyTribbiani";
		test_name3 = "RossGeller";
		
		mobileNumber1 = "0101234567";
		mobileNumber2 = "0102345678";
		mobileNumber3 = "0103456789";
		
		locationX1 = 5;
		locationY1 = 6;
		locationX2 = 4; 
		locationY2 = 3;
		locationX3 = 0;
		locationY3 = 0;
		
		//create new objects
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3, locationY3);
	}
	
	
	@Test
	//create 3 types of customers with normal name that meet the requirements
	public void CreateCustomer() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("ChandlerBing", mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer("JoeyTribbiani", mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer("RossGeller", mobileNumber3, locationX3, locationY3);
	}
	
	@Test
	//create customer with short names that meet the requirements
	public void ShortName() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("cb", mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer("jt", mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer("rg", mobileNumber3, locationX3, locationY3);
	}
	
	@Test(expected=CustomerException.class)
	//create customers with empty names
	public void emptyName() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("", mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer("", mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer("", mobileNumber3, locationX3, locationY3);
	}
		
	@Test(expected=CustomerException.class)
	//create customer with name longer than requirements
	public void TooLongName() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("adgdfdzvswerwertgdzscsrthsdtr", mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer("bdgvtrhtyjydvhsvgsersthsrh", mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer("etveyvrtytrshvsrtshgcstrghcshts", mobileNumber3, locationX3, locationY3);
	}
	
	@Test 
	//create customer with name that has white space in between
	public void whitespaceWithName() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer("Chandler Bing", mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer("Joey Tribbiani", mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer("Ross Geller", mobileNumber3, locationX3, locationY3);
	}
	
	@Test
	//create customer with valid phone number
	public void valid_mobileDigits() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, "0101234567", locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, "0000111122", locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, "0001112223", locationX3, locationY3);
	}
	
	@Test(expected=CustomerException.class)
	//create customer with phone number that start with zero
	public void notStartWithZero() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, "1101234567", locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, "2000111122", locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, "3001112223", locationX3, locationY3);
	}
	
	@Test(expected=CustomerException.class)
	//create customer with phone number that has more than 10 digits
	public void overTenDigits() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, "0101234567123", locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, "0000111122123", locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, "0001112223121", locationX3, locationY3);
	}
	
	@Test(expected=CustomerException.class)
	//create customer with phone number that has less than 10 digits
	public void lessTenDigits() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, "03453", locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, "05645", locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, "07867867", locationX3, locationY3);
		driverCustomer = new DriverDeliveryCustomer(test_name1, "0567657", locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, "02342", locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, "05654646", locationX3, locationY3);
	}
	
	@Test 
	//create customer with valid location
	public void Valid_Location() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, 5, 5);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, 2, 2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, 0, 0);
	}
	
	@Test(expected=CustomerException.class) 
	//create pickup customer with diff location
	public void pickUpCustomerSetsToDifferentLocations() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, 3, 3);
	}
	
	@Test(expected=CustomerException.class) 
	//create a delivery with locationX more than 10
	public void locationXIsTooFarPositiveNumber() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, 11, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3, locationY3);
	}
	
	@Test(expected=CustomerException.class) 
	//create a delivery with locationX less than -10
	public void locationXIsTooFarNegativeNumber() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX2, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, -13, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3, locationY3);
	}
	
	@Test(expected=CustomerException.class) 
	//create a delivery with locationY more than 10
	public void locationYIsTooFarPositiveNumber() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, 12);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3, locationY3);
	}
	
	@Test(expected=CustomerException.class) 
	//create a delivery with locationY less than -10
	public void locationYIsTooFarNegativeNumber() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, -12);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3,locationY3);
	}
	
	@Test(expected=CustomerException.class) 
	//create a delivery with locationX and Y more than 10 and less than -10
	public void locationXAndYAreTooFar() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, 11, 11);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, -11, -11);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3,locationY3);
	}
	
	@Test
	//test get customer name method
	public void getCustomerNames() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3,locationY3);
		
		assertEquals("ChandlerBing", driverCustomer.getName());
		assertEquals("JoeyTribbiani", droneCustomer.getName());
		assertEquals("RossGeller", pickupCustomer.getName());	
	}
	
	@Test
	//test get customer mobile number method
	public void getCustomerMobileNumbers() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3,locationY3);
		
		assertEquals("0101234567", driverCustomer.getMobileNumber());
		assertEquals("0102345678", droneCustomer.getMobileNumber());
		assertEquals("0103456789", pickupCustomer.getMobileNumber());	
	}
	
	@Test
	//test get type method
	public void getCustomerTypes() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3,locationY3);
		
		assertEquals("Driver Delivery", driverCustomer.getCustomerType());
		assertEquals("Drone Delivery", droneCustomer.getCustomerType());
		assertEquals("Pick Up", pickupCustomer.getCustomerType());	
	}
	
	@Test
	//test get location method
	public void getLocationX() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3,locationY3);
		
		assertEquals(5, driverCustomer.getLocationX());
		assertEquals(4, droneCustomer.getLocationX());
		assertEquals(0, pickupCustomer.getLocationX());	
	}
	
	@Test
	//test get location method
	public void getLocationY() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3,locationY3);
		
		assertEquals(6, driverCustomer.getLocationY());
		assertEquals(3, droneCustomer.getLocationY());
		assertEquals(0, pickupCustomer.getLocationY());	
	}
	
	@Test
	//test get delivery distance method
	public void getDeliveryDistance() throws CustomerException{
		driverCustomer = new DriverDeliveryCustomer(test_name1, mobileNumber1, locationX1, locationY1);
		droneCustomer = new DroneDeliveryCustomer(test_name2, mobileNumber2, locationX2, locationY2);
		pickupCustomer = new PickUpCustomer(test_name3, mobileNumber3, locationX3,locationY3);
		
		double driver = driverCustomer.getDeliveryDistance();
		double drone = droneCustomer.getDeliveryDistance();
		double pickup = pickupCustomer.getDeliveryDistance();
		
		assertEquals(11, driver, 0.0001);
		assertEquals(5, drone, 0.0001);
		assertEquals(0, pickup, 0.0001);
					
	}
	
}
