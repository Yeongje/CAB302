package asgn2Pizzas;

import java.time.LocalTime;
import java.util.LinkedList;
import asgn2Exceptions.PizzaException;

/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant.
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza
 * and MeatLoversPizza. Each of these subclasses have a different set of
 * toppings. A description of the class's fields and their constraints is
 * provided in Section 5.1 of the Assignment Specification.
 * 
 * @author Park Yeongje
 *
 */
public abstract class Pizza {

	/**
	 * This class represents a pizza produced at the Pizza Palace restaurant. A
	 * detailed description of the class's fields and parameters is provided in
	 * the Assignment Specification, in particular in Section 5.1. A
	 * PizzaException is thrown if the any of the constraints listed in Section
	 * 5.1 of the Assignment Specification are violated.
	 *
	 * PRE: TRUE POST: All field values except cost per pizza are set
	 * 
	 * @param quantity
	 *            - The number of pizzas ordered
	 * @param orderTime
	 *            - The time that the pizza order was made and sent to the
	 *            kitchen
	 * @param deliveryTime
	 *            - The time that the pizza was delivered to the customer
	 * @param type
	 *            - A human understandable description of this Pizza type
	 * @param price
	 *            - The price that the pizza is sold to the customer
	 * @throws PizzaException
	 *             if supplied parameters are invalid
	 * 
	 */
	private int quantity;
	private LocalTime orderTime;
	private LocalTime deliveryTime;
	private String type;
	private double price;

	private LocalTime open = LocalTime.of(19, 00);
	private LocalTime close = LocalTime.of(23, 00);
	private double toppingCost;

	private double MargheritaCost = 8.00;
	private double MeatLoverCost = 12.00;
	private double VegetarianCost = 10.00;

	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price)
			throws PizzaException {
		// TO DO
		
		this.quantity = quantity;
		this.orderTime = orderTime;
		this.deliveryTime = deliveryTime;
		this.type = type;
		this.price = price;
	
		
		// ||type != "margherita" || type != "vegetarian" || type != "meat
		// Lovers" || type != "meat lovers"
		if (type != "Margherita" && type != "Vegetarian" && type != "Meat Lovers") {
			throw new PizzaException("This type is not valid");
		} else if (quantity > 10 || quantity < 1) {
			throw new PizzaException("The quantity value is not valid");
		} else if (orderTime.isBefore(open) || orderTime.isAfter(close) || deliveryTime.isBefore(open)) {
			throw new PizzaException("Now, Our pizza restaurant is not open");
		} else if (deliveryTime.getHour() - orderTime.getHour() > 1
				|| (deliveryTime.getHour() - orderTime.getHour() == 1
						&& deliveryTime.getMinute() - orderTime.getMinute() >= 0)) {
			throw new PizzaException("Time is over 1 hour");
		} else if (deliveryTime.getHour() - orderTime.getHour() < 0 || (deliveryTime.getHour() == orderTime.getHour()
				&& deliveryTime.getMinute() < orderTime.getMinute())) {
			throw new PizzaException("Invalid order and delivery time");
		} else if (deliveryTime.getHour() == orderTime.getHour()
				&& deliveryTime.getMinute() - orderTime.getMinute() < 10) {
			throw new PizzaException("This cooking time is less than 10 mins");
		}

		

	}

	/**
	 * Calculates how much a pizza would cost to make calculated from its
	 * toppings.
	 * 
	 * <P>
	 * PRE: TRUE
	 * <P>
	 * POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza() {
		// TO DO
		if (type == "Margherita") {
			toppingCost = PizzaTopping.CHEESE.getCost() + PizzaTopping.TOMATO.getCost();
		} else if (type == "Vegetarian") {
			toppingCost = PizzaTopping.CHEESE.getCost() + PizzaTopping.TOMATO.getCost()
					+ PizzaTopping.EGGPLANT.getCost() + PizzaTopping.MUSHROOM.getCost()
					+ PizzaTopping.CAPSICUM.getCost();
		} else if (type == "Meat Lovers") {
			toppingCost = PizzaTopping.CHEESE.getCost() + PizzaTopping.TOMATO.getCost() + PizzaTopping.BACON.getCost()
					+ PizzaTopping.PEPPERONI.getCost() + PizzaTopping.SALAMI.getCost();
		}

	}

	/**
	 * Returns the amount that an individual pizza costs to make.
	 * 
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza() {
		// TO DO
		calculateCostPerPizza();
		return toppingCost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * 
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza() {
		// TO DO
		if (type == "Margherita") {
			return MargheritaCost;
		} else if (type == "Meat Lovers") {
			return MeatLoverCost;
		} else {
			return VegetarianCost;
		}

	}

	/**
	 * Returns the amount that the entire order costs to make, taking into
	 * account the type and quantity of pizzas.
	 * 
	 * @return The amount that the entire order costs to make, taking into
	 *         account the type and quantity of pizzas.
	 */
	public final double getOrderCost() {
		// TO DO

		return getCostPerPizza() * quantity;
	}

	/**
	 * Returns the amount that the entire order is sold to the customer, taking
	 * into account the type and quantity of pizzas.
	 * 
	 * @return The amount that the entire order is sold to the customer, taking
	 *         into account the type and quantity of pizzas.
	 */
	public final double getOrderPrice() {
		// TO DO
		return price * quantity;
	}

	/**
	 * Returns the profit made by the restaurant on the order which is the order
	 * price minus the order cost.
	 * 
	 * @return Returns the profit made by the restaurant on the order which is
	 *         the order price minus the order cost.
	 */
	public final double getOrderProfit() {
		// TO DO
		return getOrderPrice() - getOrderCost();
	}

	/**
	 * Indicates if the pizza contains the specified pizza topping or not.
	 * 
	 * @param topping
	 *            - A topping as specified in the enumeration PizzaTopping
	 * @return Returns true if the instance of Pizza contains the specified
	 *         topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping) {
		// TO DO
		if (type == "Margherita") {
			if (topping.equals(PizzaTopping.CHEESE) || topping.equals(PizzaTopping.TOMATO)) {
				return true;
			} else {
				return false;
			}
		} else if (type == "Vegetarian") {
			if (PizzaTopping.CHEESE.equals(topping) || PizzaTopping.TOMATO.equals(topping)
					|| PizzaTopping.EGGPLANT.equals(topping) || PizzaTopping.MUSHROOM.equals(topping)
					|| PizzaTopping.CAPSICUM.equals(topping)) {
				return true;
			} else {
				return false;
			}
		} else {
			if (PizzaTopping.CHEESE.equals(topping) || PizzaTopping.TOMATO.equals(topping)
					|| PizzaTopping.BACON.equals(topping) || PizzaTopping.PEPPERONI.equals(topping)
					|| PizzaTopping.SALAMI.equals(topping)) {
				return true;
			} else {
				return false;
			}
		}

	}

	/**
	 * Returns the quantity of pizzas ordered.
	 * 
	 * @return the quantity of pizzas ordered.
	 */
	public final int getQuantity() {
		// TO DO
		return quantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. The valid
	 * alternatives are listed in Section 5.1 of the Assignment Specification.
	 * 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType() {
		// TO DO
		return type;
	}

	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object
	 * and returns true if if the two objects are equivalent, that is, if the
	 * values exposed by public methods are equal. You do not need to test this
	 * method.
	 * 
	 * @return true if *this* Pizza object and the *other* Pizza object have the
	 *         same values returned for getCostPerPizza(), getOrderCost(),
	 *         getOrderPrice(), getOrderProfit(), getPizzaType(),
	 *         getPricePerPizza() and getQuantity().
	 * 
	 */
	@Override
	public boolean equals(Object other) {
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza())
				&& (this.getOrderCost()) == (otherPizza.getOrderCost()))
				&& (this.getOrderPrice()) == (otherPizza.getOrderPrice())
				&& (this.getOrderProfit()) == (otherPizza.getOrderProfit())
				&& (this.getPizzaType() == (otherPizza.getPizzaType())
						&& (this.getPricePerPizza()) == (otherPizza.getPricePerPizza())
						&& (this.getQuantity()) == (otherPizza.getQuantity()));
	}

}
