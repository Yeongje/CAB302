package asgn2Tests;

import java.util.ArrayList;

import org.junit.*;

import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Daniel Nguyen
* 
*/
public class LogHandlerPizzaTests {
	
	ArrayList<Pizza> pizzas = new ArrayList<Pizza>();
	Pizza pizza1;
	Pizza pizza2;
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
	
	// Test for testPopulatePizzaDataset functions
	@Test
	public void testPopulatePizzaDataset1() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(goodFile1);
	}
	
	@Test
	public void testPopulatePizzaDataset2() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(goodFile2);
	}
	
	@Test
	public void testPopulatePizzaDataset3() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(goodFile3);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulatePizzaDatasetWithCorrupt1() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(corruptedFile1);
	}
	
	@Test(expected=LogHandlerException.class)
	public void testPopulatePizzaDatasetWithCorrupt2() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(corruptedFile2);
	}
	
	
	@Test(expected=PizzaException.class)
	public void testPopulatePizzaDatasetWithInvalid1() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(invalidFile1);
	}
	
	@Test(expected=PizzaException.class)
	public void testPopulatePizzaDatasetWithInvalid2() throws PizzaException, LogHandlerException {
		pizzas = LogHandler.populatePizzaDataset(invalidFile2);
	}
	
	
	// Tests for CreatePizza function
	@Test
	public void CreatePizza1() throws PizzaException, LogHandlerException {
		str = "19:00:00,19:20:00,Chandler Bing,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=PizzaException.class)
	public void CreatePizza2() throws PizzaException, LogHandlerException {
		str = "18:00:00,19:20:00,Chandler Bingg,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=LogHandlerException.class) 
	public void CreatePizza3() throws PizzaException, LogHandlerException {
		str = "18:00:00,19:20:00,Chandler Bingggg,0123456789,DVC,5,5,PZV";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=PizzaException.class)
	public void CreatePizza4() throws PizzaException, LogHandlerException {
		str = "18:00:00,17:20:00,Chandler Bingggggg,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=PizzaException.class)
	public void CreatePizza5() throws PizzaException, LogHandlerException {
		str = "18:00:00,18:20:00,Chandler Bing,0123456789,DVC,5,5,ZZZ,2";
		pizza1 = LogHandler.createPizza(str);
	}
	
	@Test(expected=PizzaException.class)
	public void CreatePizza6() throws PizzaException, LogHandlerException {
		str = "18:00:00,18:59:00,Chandler Binggggg,0123456789,DVC,5,5,PZV,2";
		pizza1 = LogHandler.createPizza(str);
	}

	@Test(expected=LogHandlerException.class)
	public void CreatePizza7() throws PizzaException, LogHandlerException {
		str = "18:00:00,19:20:00Chandler Bingg,0123456789,DVC,5,5,PZV";
		pizza1 = LogHandler.createPizza(str);
	}

	@Test(expected=LogHandlerException.class)
	public void testFileDoesntExist() throws PizzaException, LogHandlerException {
		LogHandler.populatePizzaDataset(nonexistFile);
	}
}
