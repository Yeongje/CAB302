package asgn2Tests;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;
import asgn2Customers.Customer;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * A class that that tests the methods relating to the handling of Customer
 * objects in the asgn2Restaurant.PizzaRestaurant class as well as processLog
 * and resetDetails.
 * 
 * @author Park Yeongje
 */
public class RestaurantCustomerTests {
	// TO DO

	// Test getCustomerByIndex log1,2,3
	@Test
	public void TestgetCustomerByIndex_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		Customer Customer = LogHandler.createCustomer("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2");
		RestaurantCus.processLog("./logs/20170101.txt");
		assertEquals(Customer, RestaurantCus.getCustomerByIndex(0));
	}

	@Test
	public void TestgetCustomerByIndex_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		Customer Customer = LogHandler.createCustomer("21:17:00,21:27:00,Emma Brown,0602547760,DVC,-1,0,PZV,5");
		RestaurantCus.processLog("./logs/20170102.txt");
		assertEquals(Customer, RestaurantCus.getCustomerByIndex(0));
	}

	@Test
	public void TestgetCustomerByIndex_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		Customer Customer = LogHandler.createCustomer("20:05:00,20:26:00,Aiden Zhang,0161429209,DVC,-3,9,PZV,2");
		RestaurantCus.processLog("./logs/20170103.txt");
		assertEquals(Customer, RestaurantCus.getCustomerByIndex(0));
	}

	// Test getNumCustomerOrders - log1,2,3
	@Test
	public void TestgetNumCustomerOrders_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170101.txt");
		assertEquals(3, RestaurantCus.getNumPizzaOrders());
	}

	@Test
	public void TestgetNumCustomerOrders_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170102.txt");
		assertEquals(10, RestaurantCus.getNumPizzaOrders());
	}

	@Test
	public void TestgetNumCustomerOrders_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170103.txt");
		assertEquals(100, RestaurantCus.getNumPizzaOrders());
	}

	// Test getTotalDeliveryDistance - log1,2,3
	@Test
	public void TestgetTotalDeliveryDistance_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170101.txt");
		assertEquals(15.0, RestaurantCus.getTotalDeliveryDistance(), 1);
	}

	@Test
	public void TestgetTotalDeliveryDistance_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170102.txt");
		assertEquals(41.4, RestaurantCus.getTotalDeliveryDistance(), 1);
	}

	@Test
	public void TestgetTotalDeliveryDistance_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170103.txt");
		assertEquals(518.6, RestaurantCus.getTotalDeliveryDistance(), 1);
	}

	// Test resetDetails for Customer
	@Test(expected = CustomerException.class)
	public void TestresetDetails_CustomerIndex_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170101.txt");
		RestaurantCus.resetDetails();
		Customer testing = RestaurantCus.getCustomerByIndex(0);
	}

	@Test(expected = CustomerException.class)
	public void TestresetDetails_CustomerIndex_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170102.txt");
		RestaurantCus.resetDetails();
		Customer testing = RestaurantCus.getCustomerByIndex(0);
	}

	@Test(expected = CustomerException.class)
	public void TestresetDetails_CustomerIndex_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170103.txt");
		RestaurantCus.resetDetails();
		Customer testing = RestaurantCus.getCustomerByIndex(0);
	}

	@Test
	public void TestresetDetails_CustomerOrder_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170101.txt");
		RestaurantCus.resetDetails();
		assertEquals(0, RestaurantCus.getNumCustomerOrders());
	}

	@Test
	public void TestresetDetails_CustomerOrder_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170102.txt");
		RestaurantCus.resetDetails();
		assertEquals(0, RestaurantCus.getNumCustomerOrders());
	}

	@Test
	public void TestresetDetails_CustomerOrder_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170103.txt");
		RestaurantCus.resetDetails();
		assertEquals(0, RestaurantCus.getNumCustomerOrders());
	}

	@Test
	public void TestresetDetails_Distance_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170101.txt");
		RestaurantCus.resetDetails();
		assertEquals(0, RestaurantCus.getTotalDeliveryDistance(), 1);
	}

	@Test
	public void TestresetDetails_Distance_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170102.txt");
		RestaurantCus.resetDetails();
		assertEquals(0, RestaurantCus.getTotalDeliveryDistance(), 1);
	}

	@Test
	public void TestresetDetails_Distance_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantCus = new PizzaRestaurant();
		RestaurantCus.processLog("./logs/20170103.txt");
		RestaurantCus.resetDetails();
		assertEquals(0, RestaurantCus.getTotalDeliveryDistance(), 1);
	}

}
