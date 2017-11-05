package asgn2Tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;
import asgn2Restaurant.PizzaRestaurant;

/**
 * A class that tests the methods relating to the handling of Pizza objects in
 * the asgn2Restaurant.PizzaRestaurant class as well as processLog and
 * resetDetails.
 * 
 * @author Daniel Nguyen
 *
 */
public class RestaurantPizzaTests {
	
	// Test getNumPizzaOrders
	@Test
	public void TestGetNumPizzaOrders_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170101.txt");
		assertEquals(3, RestaurantPizza.getNumPizzaOrders());
	}

	@Test
	public void TestGetNumPizzaOrders_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170102.txt");
		assertEquals(10, RestaurantPizza.getNumPizzaOrders());

	}

	@Test
	public void TestGetNumPizzaOrders_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170103.txt");
		assertEquals(100, RestaurantPizza.getNumPizzaOrders());
	}

	@Test
	public void getNumPizzaOrders_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170101.txt");
		if (RestaurantPizza.getNumPizzaOrders() < 0) {
			throw new PizzaException("Order number is negative");
		}
	}

	@Test
	public void getNumPizzaOrders_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170102.txt");
		if (RestaurantPizza.getNumPizzaOrders() < 0) {
			throw new PizzaException("Order number is negative");
		}
	}

	@Test
	public void getNumPizzaOrders_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170103.txt");
		if (RestaurantPizza.getNumPizzaOrders() < 0) {
			throw new PizzaException("Order number is negative");
		}
	}

	// Test getPizzaByIndex
	@Test
	public void TestgetPizzaByIndex_log1_1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		Pizza Pizza = LogHandler.createPizza("19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2");
		RestaurantPizza.processLog("./logs/20170101.txt");
		assertEquals(Pizza, RestaurantPizza.getPizzaByIndex(0));
	}

	@Test
	public void TestgetPizzaByIndex_log1_2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170101.txt");
		Pizza Pizza = LogHandler.createPizza("20:00:00,20:25:00,April O'Neal,0987654321,DNC,3,4,PZM,1");
		assertEquals(Pizza, RestaurantPizza.getPizzaByIndex(1));
	}

	@Test
	public void TestgetPizzaByIndex_log2_1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170102.txt");
		Pizza Pizza = LogHandler.createPizza("20:43:00,20:53:00,Sophia Singh,0193102468,DNC,1,8,PZV,2");
		assertEquals(Pizza, RestaurantPizza.getPizzaByIndex(2));
	}

	@Test
	public void TestgetPizzaByIndex_log2_2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170102.txt");
		Pizza Pizza = LogHandler.createPizza("21:08:00,21:30:00,Emma Chen,0678585543,DNC,-4,2,PZL,4");
		assertEquals(Pizza, RestaurantPizza.getPizzaByIndex(7));
	}

	@Test
	public void TestgetPizzaByIndex_log3_1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170103.txt");
		Pizza Pizza = LogHandler.createPizza("20:59:00,21:18:00,Eli Wang,0768653527,PUC,0,0,PZV,8");
		assertEquals(Pizza, RestaurantPizza.getPizzaByIndex(58));
	}

	@Test
	public void TestgetPizzaByIndex_log3_2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170103.txt");
		Pizza Pizza = LogHandler.createPizza("19:21:00,19:42:00,Mia Smith,0417243141,DNC,-8,9,PZL,5");
		assertEquals(Pizza, RestaurantPizza.getPizzaByIndex(69));
	}

	// Test getTotalProfit
	@Test
	public void TestgetTotalProfit_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170101.txt");
		assertEquals(36.5, RestaurantPizza.getTotalProfit(), 1);
	}

	@Test
	public void TestgetTotalProfit_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170102.txt");
		assertEquals(316.5, RestaurantPizza.getTotalProfit(), 1);
	}

	// Test resetDetails for Pizza
	@Test(expected = PizzaException.class)
	public void TestresetDetails_PizzaIndex_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170101.txt");
		RestaurantPizza.resetDetails();
		Pizza testing = RestaurantPizza.getPizzaByIndex(0);
	}

	@Test(expected = PizzaException.class)
	public void TestresetDetails_PizzaIndex_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170102.txt");
		RestaurantPizza.resetDetails();
		Pizza testing = RestaurantPizza.getPizzaByIndex(0);
	}

	@Test(expected = PizzaException.class)
	public void TestresetDetails_PizzaIndex_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170103.txt");
		RestaurantPizza.resetDetails();
		Pizza testing = RestaurantPizza.getPizzaByIndex(0);
	}

	@Test
	public void TestresetDetails_PizzaOrder_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170101.txt");
		RestaurantPizza.resetDetails();
		assertEquals(0, RestaurantPizza.getNumPizzaOrders());
	}

	@Test
	public void TestresetDetails_PizzaOrder_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170102.txt");
		RestaurantPizza.resetDetails();
		assertEquals(0, RestaurantPizza.getNumPizzaOrders());
	}

	@Test
	public void TestresetDetails_PizzaOrder_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170103.txt");
		RestaurantPizza.resetDetails();
		assertEquals(0, RestaurantPizza.getNumPizzaOrders());
	}

	@Test
	public void TestresetDetails_PizzaTotalProfit_log1() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170101.txt");
		RestaurantPizza.resetDetails();
		assertEquals(0, RestaurantPizza.getTotalProfit(), 1);
	}

	@Test
	public void TestresetDetails_PizzaTotalProfit_log2() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170102.txt");
		RestaurantPizza.resetDetails();
		assertEquals(0, RestaurantPizza.getTotalProfit(), 1);
	}

	@Test
	public void TestresetDetails_PizzaTotalProfit_log3() throws CustomerException, PizzaException, LogHandlerException {
		PizzaRestaurant RestaurantPizza = new PizzaRestaurant();
		RestaurantPizza.processLog("./logs/20170103.txt");
		RestaurantPizza.resetDetails();
		assertEquals(0, RestaurantPizza.getTotalProfit(), 1);
	}
}
