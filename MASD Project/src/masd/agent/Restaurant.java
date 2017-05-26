package masd.agent;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

public class Restaurant{
	
	private static Restaurant instance = null;
	private List<Order> waiterOrders;
	private List<Order> chefOrders;
	private int orderCount;
	private List<ChefBDI> observers = new ArrayList<>();
	
	protected Restaurant(){
		waiterOrders = new ArrayList<>();
		chefOrders = new ArrayList<>();
		orderCount = 1;
		addOrderForWaiter();
	}
	
	public Order generateOrder(int orderCount) {
		int timeToCook = generateRandomTime();
		int timeToDeliver = generateRandomTime();
		
		Order order = new Order(orderCount+"", timeToCook, timeToDeliver);
		// TODO Auto-generated method stub
		return order;
	}


	private static int generateRandomTime() {
		Random random = new Random();
		int r = random.nextInt((9-4)+4);
		return r;
	}

	public static Restaurant getInstance(){
		if(instance == null){
			instance = new Restaurant();
		}
		
		return instance;
	}

	public List<Order> getWaiterList() {
		return waiterOrders;
	}

	public void addOrderForWaiter() {
		waiterOrders.add(generateOrder(orderCount));
		orderCount++;		
	}

	private void notifyObserver() {
		for(ChefBDI o: observers){
			o.collectList();
		}
		
	}

	public List<Order> getChefList() {
		return chefOrders;
	}

	public void addToChefList(Order currentlyDelivering) {
		currentlyDelivering.setToKitchen(false);
		chefOrders.add(currentlyDelivering);
		notifyObserver();
	}

	public void addObserver(ChefBDI o){
		observers.add(o);
	}

	public void addOrderForWaiterBackToCustomer(Order currentlyCooking) {
		waiterOrders.add(currentlyCooking);	
	}
	
	

}
