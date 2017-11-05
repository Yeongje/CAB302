package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.VegetarianPizza;

/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Daniel Nguyen
 *
 */
public class PizzaTests {
	// TO DO
	
	Pizza MargheritaPizza;
	Pizza MeatloversPizza;
	Pizza vegetarianPizza;
	
	
	
	LocalTime beforetime = LocalTime.of(18, 50);
	LocalTime opentime = LocalTime.of(19, 00);
	LocalTime runningtime = LocalTime.of(19, 30);
	LocalTime runningtime_5mins = LocalTime.of(19, 35);
	LocalTime runningtime2 = LocalTime.of(19, 45);
	LocalTime runningtime3 = LocalTime.of(20, 00);
	LocalTime runningtime4 = LocalTime.of(20, 15);
	LocalTime runningtime5 = LocalTime.of(20, 32);
	LocalTime closetime= LocalTime.of(23, 00);
	LocalTime overtime= LocalTime.of(23, 15);
	
	//Test Quantity options
	@Test
	public void TestValidQuantity() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(2, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(5, runningtime3, runningtime4);
	}
	@Test(expected=PizzaException.class)
	public void TestinValidQuantity_0() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(0, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(0, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(0, runningtime3, runningtime4);
	}
	@Test(expected=PizzaException.class)
	public void TestinValidQuantity_Negative() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(-4, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(-5, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(-14, runningtime3, runningtime4);
	}
	@Test(expected=PizzaException.class)
	public void TestinValidQuantity_Over() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(11, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(20, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(22, runningtime3, runningtime4);
	}
	
	//Test Time for order and delivery
	@Test(expected=PizzaException.class)
	public void TestTime_beforetime_validtime() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(4, beforetime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(2, beforetime, runningtime2);
		vegetarianPizza = new VegetarianPizza(6, beforetime, runningtime2);
	}
	@Test(expected=PizzaException.class)
	public void TestTime_validtime_beforetime() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(4, runningtime2, beforetime);
		MeatloversPizza = new MeatLoversPizza(2, runningtime2, beforetime);
		vegetarianPizza = new VegetarianPizza(6, runningtime2, beforetime);
	}
	
	@Test(expected=PizzaException.class)
	public void TestTime_validtime_overtime() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(4, runningtime2, overtime);
		MeatloversPizza = new MeatLoversPizza(2, runningtime2, overtime);
		vegetarianPizza = new VegetarianPizza(6, runningtime2, overtime);
	}
	@Test(expected=PizzaException.class)
	public void TestTime_overtime_validtime() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(4, overtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(2, overtime, runningtime2);
		vegetarianPizza = new VegetarianPizza(6, overtime, runningtime2);
	}
	@Test(expected=PizzaException.class)
	public void TestTime_deliverytime_ordertime() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(4, runningtime3, runningtime2);
		MeatloversPizza = new MeatLoversPizza(2, runningtime3, runningtime2);
		vegetarianPizza = new VegetarianPizza(6, runningtime3, runningtime2);
	}
	@Test(expected=PizzaException.class)
	public void TestTime_overOneHour() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(4, runningtime, runningtime5);
		MeatloversPizza = new MeatLoversPizza(2, runningtime, runningtime5);
		vegetarianPizza = new VegetarianPizza(6, runningtime, runningtime5);
	}
	@Test(expected=PizzaException.class)
	public void TestTime_cookingtime() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(4, runningtime, runningtime_5mins);
		MeatloversPizza = new MeatLoversPizza(2, runningtime, runningtime_5mins);
		vegetarianPizza = new VegetarianPizza(6, runningtime, runningtime_5mins);
	}

	
	
	// Test price
	@Test
	public void TestGetCostPerPizza() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(1.5, MargheritaPizza.getCostPerPizza(),1);
		assertEquals(5, MeatloversPizza.getCostPerPizza(),1);
		assertEquals(5.5, vegetarianPizza.getCostPerPizza(),1);
	}
	
	@Test
	public void TestGetPricePerPizza() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(8, MargheritaPizza.getPricePerPizza(), 1);
		assertEquals(12, MeatloversPizza.getPricePerPizza(), 1);
		assertEquals(10, vegetarianPizza.getPricePerPizza(), 1);
	}

	@Test
	public void pizzaGetOrderCost_Maximum_10() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(5, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(10, runningtime3, runningtime4);
	
		assertEquals(1.5, MargheritaPizza.getOrderCost(),1);
		assertEquals(25, MeatloversPizza.getOrderCost(),1);
		assertEquals(55, vegetarianPizza.getOrderCost(),1);
	}

	@Test
	public void TestGetOrderPrice() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(8, MargheritaPizza.getOrderPrice(),1);
		assertEquals(12, MeatloversPizza.getOrderPrice(),1);
		assertEquals(10, vegetarianPizza.getOrderPrice(),1);
	}
	
	@Test
	public void TestaGetOrderPrice_Maximum_10() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(5, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(10, runningtime3, runningtime4);
	
		assertEquals(8, MargheritaPizza.getOrderPrice(),1);
		assertEquals(60, MeatloversPizza.getOrderPrice(),1);
		assertEquals(100, vegetarianPizza.getOrderPrice(),1);
	}
	
	@Test
	public void TestaGetOrderProfit() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(6.5, MargheritaPizza.getOrderProfit(), 1);
		assertEquals(7, MeatloversPizza.getOrderProfit(), 1);
		assertEquals(4.5, vegetarianPizza.getOrderProfit(), 1);
	}
	
	@Test
	public void TestaGetOrderProfit_Maximum_10() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(5, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(10, runningtime3, runningtime4);
		
		assertEquals(6.5, MargheritaPizza.getOrderProfit(), 1);
		assertEquals(35, MeatloversPizza.getOrderProfit(), 1);
		assertEquals(45, vegetarianPizza.getOrderProfit(), 1);
	}
	//Test to check topping
	@Test
	public void TestContainsTopping_CHEESE() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(true, MargheritaPizza.containsTopping(PizzaTopping.CHEESE));
		assertEquals(true, MeatloversPizza.containsTopping(PizzaTopping.CHEESE));
		assertEquals(true, vegetarianPizza.containsTopping(PizzaTopping.CHEESE));
	}
	
	@Test
	public void TestContainsTopping_TOMATO() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(true, MargheritaPizza.containsTopping(PizzaTopping.TOMATO));
		assertEquals(true, MeatloversPizza.containsTopping(PizzaTopping.TOMATO));
		assertEquals(true, vegetarianPizza.containsTopping(PizzaTopping.TOMATO));
	}
	
	@Test
	public void TestContainsTopping_BACON() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(false, MargheritaPizza.containsTopping(PizzaTopping.BACON));
		assertEquals(true, MeatloversPizza.containsTopping(PizzaTopping.BACON));
		assertEquals(false, vegetarianPizza.containsTopping(PizzaTopping.BACON));
	}
	
	@Test
	public void TestContainsTopping_SALAMI() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(false, MargheritaPizza.containsTopping(PizzaTopping.SALAMI));
		assertEquals(true, MeatloversPizza.containsTopping(PizzaTopping.SALAMI));
		assertEquals(false, vegetarianPizza.containsTopping(PizzaTopping.SALAMI));
	}
	
	@Test
	public void TestContainsTopping_PEPPERONI() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(false, MargheritaPizza.containsTopping(PizzaTopping.PEPPERONI));
		assertEquals(true, MeatloversPizza.containsTopping(PizzaTopping.PEPPERONI));
		assertEquals(false, vegetarianPizza.containsTopping(PizzaTopping.PEPPERONI));
	}
	@Test
	public void TestContainsTopping_CAPSICUM() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(false, MargheritaPizza.containsTopping(PizzaTopping.CAPSICUM));
		assertEquals(false, MeatloversPizza.containsTopping(PizzaTopping.CAPSICUM));
		assertEquals(true, vegetarianPizza.containsTopping(PizzaTopping.CAPSICUM));
	}
	@Test
	public void TestContainsTopping_MUSHROOM() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(false, MargheritaPizza.containsTopping(PizzaTopping.MUSHROOM));
		assertEquals(false, MeatloversPizza.containsTopping(PizzaTopping.MUSHROOM));
		assertEquals(true, vegetarianPizza.containsTopping(PizzaTopping.MUSHROOM));
	}
	@Test
	public void TestContainsTopping_EGGPLANT() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(false, MargheritaPizza.containsTopping(PizzaTopping.EGGPLANT));
		assertEquals(false, MeatloversPizza.containsTopping(PizzaTopping.EGGPLANT));
		assertEquals(true, vegetarianPizza.containsTopping(PizzaTopping.EGGPLANT));
	}
	
	//Test Quantity
	@Test
	public void TestgetQuantity_1() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(1, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(1, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(1, runningtime3, runningtime4);
		
		assertEquals(1, MargheritaPizza.getQuantity());
		assertEquals(1, MeatloversPizza.getQuantity());
		assertEquals(1, vegetarianPizza.getQuantity());
	}
	
	@Test
	public void TestgetQuantity_5() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(5, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(5, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(5, runningtime3, runningtime4);
		
		assertEquals(5, MargheritaPizza.getQuantity());
		assertEquals(5, MeatloversPizza.getQuantity());
		assertEquals(5, vegetarianPizza.getQuantity());
	}
	
	@Test
	public void TestgetQuantity_10() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(10, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(10, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(10, runningtime3, runningtime4);
		
		assertEquals(10, MargheritaPizza.getQuantity());
		assertEquals(10, MeatloversPizza.getQuantity());
		assertEquals(10, vegetarianPizza.getQuantity());
	}
	
	//Test Pizza Type
	
	@Test
	public void TestGetPizzaType() throws PizzaException {
		MargheritaPizza = new MargheritaPizza(10, runningtime, runningtime2);
		MeatloversPizza = new MeatLoversPizza(10, runningtime2, runningtime3);
		vegetarianPizza = new VegetarianPizza(10, runningtime3, runningtime4);
		
		assertEquals("Margherita", MargheritaPizza.getPizzaType());
		assertEquals("Vegetarian", vegetarianPizza.getPizzaType());
		assertEquals("Meat Lovers", MeatloversPizza.getPizzaType());
	}
}
