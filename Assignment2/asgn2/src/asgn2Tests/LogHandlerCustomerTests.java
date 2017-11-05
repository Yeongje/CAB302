package asgn2Tests;
import java.util.ArrayList;

import org.junit.*;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Exceptions.CustomerException;
import asgn2Customers.Customer;
import asgn2Restaurant.LogHandler;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Park YeongJe
 */
public class LogHandlerCustomerTests {
	ArrayList<Customer> customers = new ArrayList<Customer>();
	Customer customer1;
	Customer customer2;
	String goodFile1;
	String goodFile2;
	String goodFile3;
	//wrong format file
	String corruptedFile1;
	String corruptedFile2;

	//invalid data file
	String invalidFile1;
	String invalidFile2;
	String nonexistFile;

	String str;
	
	@Before
	public void setup() {
		String log = "logs/";
		
		// Initialize good files
		goodFile1 = log + "20170101.txt";
		goodFile2 = log + "20170102.txt";
		goodFile3 = log + "20170103.txt";
		
		// Initialize corrupted files
		corruptedFile1 = log + "corrupt1.txt";
		corruptedFile2 = log + "corrupt2.txt";

		
		// Initialize invalid files
		invalidFile1 = log + "invalid1.txt";
		invalidFile2 = log + "invalid2.txt";
		
		nonexistFile = log + "sfdkjhdskjfdsds.txt";

	}
	
	// Test for testPopulateCustomerDataset functions
	@Test
	public void testPopulateCustomerDataset1() throws CustomerException, LogHandlerException {
		customers = LogHandler.populateCustomerDataset(goodFile1);
	}
	
	@Test
	public void testPopulateCustomerDataset2() throws CustomerException, LogHandlerException {
		customers = LogHandler.populateCustomerDataset(goodFile2);
	}
	
	@Test
	public void testPopulateCustomerDataset3() throws CustomerException, LogHandlerException {
		customers = LogHandler.populateCustomerDataset(goodFile3);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithCorrupted1() throws CustomerException, LogHandlerException {
		customers = LogHandler.populateCustomerDataset(corruptedFile1);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulateCustomerDatasetWithCorrupted2() throws CustomerException, LogHandlerException {
		customers = LogHandler.populateCustomerDataset(corruptedFile2);
	}
	
	
	@Test(expected=CustomerException.class)
	public void testPopulateCustomerDatasetWithInvalid1() throws CustomerException, LogHandlerException {
		customers = LogHandler.populateCustomerDataset(invalidFile1);
	}
	
	@Test(expected=CustomerException.class)
	public void testPopulateCustomerDatasetWithInvalid2() throws CustomerException, LogHandlerException {
		customers = LogHandler.populateCustomerDataset(invalidFile1);
	}
	
	
	// Tests for CreateCustomer function
	@Test
	public void CreateCustomer1() throws CustomerException, LogHandlerException {
		str = "19:00:00,19:20:00,Chandler Bing,0123456789,DVC,5,5,PZV,2";
		customer1 = LogHandler.createCustomer(str);
	}
	
	@Test(expected=CustomerException.class)
	public void CreateCustomer2() throws CustomerException, LogHandlerException {
		str = "18:00:00,19:20:00,Chandler Bingg,0123456789,DVC,15,5,PZV,2";
		customer1 = LogHandler.createCustomer(str);
	}
	
	@Test(expected=CustomerException.class) 
	public void CreateCustomer3() throws CustomerException, LogHandlerException {
		str = "18:00:00,19:20:00,Chandler Bingggg,0123456789,DVC,5,15,PZV";
		customer1 = LogHandler.createCustomer(str);
	}
	
	@Test(expected=LogHandlerException.class)
	public void CreateCustomer4() throws CustomerException, LogHandlerException {
		str = "18:00:00,17:20:00,Chandler Bingggggg,0123456789,DVC,5,,PZV,2";
		customer1 = LogHandler.createCustomer(str);
	}
	
	@Test(expected=CustomerException.class)
	public void CreateCustomer5() throws CustomerException, LogHandlerException {
		str = "18:00:00,18:20:00,Chandler Bing,01234569,DVC,5,5,PZV,2";
		customer1 = LogHandler.createCustomer(str);
	}


	@Test(expected=LogHandlerException.class)
	public void CreateCustomer6() throws CustomerException, LogHandlerException {
		str = "18:00:00,19:20:00Chandler Bingg,0123456789,DVC,5,5,PZV";
		customer1 = LogHandler.createCustomer(str);
	}

	@Test(expected=LogHandlerException.class)
	public void testFileDoesntExist() throws CustomerException, LogHandlerException {
		LogHandler.populateCustomerDataset(nonexistFile);
	}
}
