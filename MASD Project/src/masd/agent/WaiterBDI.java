package masd.agent;

import java.util.ArrayList;
import java.util.List;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.Trigger;
import jadex.bdiv3.features.IBDIAgentFeature;
import jadex.bdiv3.runtime.ChangeEvent;
import jadex.bridge.component.IExecutionFeature;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.AgentCreated;
import jadex.micro.annotation.AgentFeature;
import jadex.rules.eca.ChangeInfo;

@Agent
public class WaiterBDI {
	
	@Belief
	protected List<Order> orders;
		
	@Belief
	protected Order currentlyDelivering;
	
	@AgentFeature
	protected IBDIAgentFeature bdiFeature;
	
	@AgentFeature
	protected IExecutionFeature execFeature;
	
	@AgentCreated
	public void init(){
		
		System.out.println("Waiter arrived at work.");
		
		currentlyDelivering = null;
		orders = new ArrayList<>();
		
		//Test data
		/*
		Order order = new Order("1", 1000);
		order.setToKitchen(false);
		orders.add(order);
		order = new Order("2", 5000);
		order.setToKitchen(false);
		orders.add(order);
		order = new Order("3", 100);
		order.setToKitchen(true);
		orders.add(order);
		order = new Order("4", 500);
		order.setToKitchen(true);
		orders.add(order);
		order = new Order("5", 2000);
		order.setToKitchen(false);
		orders.add(order);
		*/
	}
	
	@AgentBody
	public void body(){
		//bdiFeature.adoptPlan("cookFood");
	}
	
	/**
	 * Plan that gets triggered when the order the waiter is currently delivering gets changed.
	 */
	@Plan(trigger=@Trigger(factchangeds="currentlyDelivering"))
	public void changedCurrentlyDelivering(ChangeEvent event){
		ChangeInfo<Order> change = ((ChangeInfo<Order>)event.getValue());
		Order orderToDeliver = change.getValue();
		
		if(currentlyDelivering == null){
			
			if(orderToDeliver != null){
				this.currentlyDelivering = orderToDeliver;
			}
			else{
				System.out.println("Waiter has no more orders to deliver.");
			}	
		}
		else{
			if(currentlyDelivering.isToKitchen()){
				System.out.println("Waiter is on his way to the kitchen with order " + currentlyDelivering.getName());
			}
			else{
				System.out.println("Waiter is on his way to a customer with order " + currentlyDelivering.getName());
			}
			
			bdiFeature.adoptPlan("deliverOrder");
			
		}
	}
	
	/**
	 * Plan that gets triggered when additional orders are added. 
	 * @param event
	 */
	@Plan(trigger=@Trigger(factaddeds="orders"))
	public void deliverOrders(ChangeEvent event){
		ChangeInfo<Order> change = ((ChangeInfo<Order>)event.getValue()); 
		Order newOrder = change.getValue();
		
		if(this.currentlyDelivering == null){
			currentlyDelivering = newOrder;
		}
		else{
			if(currentlyDelivering != null){
				System.out.println("Waiter is busy, " + newOrder.getName() + " is added to queue.");
			}
		}		
	}
	
	@Plan
	public void deliverOrder(){
		
		//Work for time it takes to deliver order
		execFeature.waitForDelay(this.currentlyDelivering.getTimeToDeliver()).get();
		
		//If currently giving order to customer, then set delivered to true
		if(!currentlyDelivering.isToKitchen()){
			currentlyDelivering.setDelivered(true);
			System.out.println("Finished serving order: " + currentlyDelivering.getName() + " to customer.");
		}
		else{
			System.out.println("Finished delivering order: " + currentlyDelivering.getName() + " to kitchen.");
			//TODO: Send message to chef including order
		}
		
		this.orders.remove(currentlyDelivering);
		
		if(!orders.isEmpty()){
			this.currentlyDelivering = orders.get(0);
			System.out.println("Remaining orders: " + orders.size());
		}
		else{
			this.currentlyDelivering = null;
		}
	}
	
	/**
	 * Need a plan that gets triggered by incoming message.
	 * The plan will then extract an Order object from the message and add it to the list of orders. 
	 */
	
	/**
	 * Need a plan to send a message that includes and Order to a Chef.
	 */
	
	

}
