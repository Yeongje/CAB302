package asgn2Tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalTime;

import org.junit.Test;

import asgn2Customers.CustomerFactory;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;
import asgn2Pizzas.VegetarianPizza;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Daniel Nguyen
 * 
 */
public class PizzaFactoryTests {
	// TO DO
	private Pizza testPizza;
	
//	margherita(��PZM��), vegetarian (��PZV��) or meat lovers (��PZL��). 
	
	private String MargheritaPizza = "PZM";
	private String vegetarianPizza = "PZV";
	private String MeatloversPizza = "PZL";
	private String letterPizza = "ABC";
	private String numberPizza = "123";
	
	LocalTime open = LocalTime.of(19, 00);
	LocalTime runningtime = LocalTime.of(19, 15);

		
	@Test
	public void TestMargherita() throws PizzaException {
		testPizza = PizzaFactory.getPizza(MargheritaPizza, 1, open, runningtime);
		assertEquals("Margherita", testPizza.getPizzaType());
	}
	
	@Test
	public void TestVegetarian() throws PizzaException {
		testPizza = PizzaFactory.getPizza(vegetarianPizza, 1, open, runningtime);
		assertEquals("Vegetarian", testPizza.getPizzaType());
	}
	@Test
	public void TestMeatLovers() throws PizzaException {
		testPizza = PizzaFactory.getPizza(MeatloversPizza, 1, open, runningtime);
		assertEquals("Meat Lovers", testPizza.getPizzaType());
	}
	@Test (expected=PizzaException.class)
	public void TestInvalidCode_letter() throws PizzaException {
		testPizza = PizzaFactory.getPizza(letterPizza, 1, open, runningtime);
	}
	
	@Test (expected=PizzaException.class)
	public void TestInvalidCode_number() throws PizzaException {
		testPizza = PizzaFactory.getPizza(numberPizza, 1, open, runningtime);
	}
	
	@Test (expected=PizzaException.class)
	public void TestInvalidCode_empty() throws PizzaException {
		testPizza = PizzaFactory.getPizza("", 1, open, runningtime);
	}
	
	
	
}
