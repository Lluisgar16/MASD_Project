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
public class ChefBDI {

	
	@Belief
	protected List<Order> orders;
	
	@Belief
	protected Order currentlyCooking;
	
	@AgentFeature
	protected IBDIAgentFeature bdiFeature;
	
	@AgentFeature
	protected IExecutionFeature execFeature;
	
	@AgentCreated
	public void init(){
		//Things that should happen when the agent is created. 
		System.out.println("Chef arrived at work.Œ");
		
		//Set beliefs etc. 
		this.currentlyCooking = null;
		
		//No orders to begin with
		this.orders = new ArrayList<>();

		
		//Test data
		Order order = new Order("1", 1000);
		orders.add(order);
		order = new Order("2", 5000);
		orders.add(order);
		order = new Order("3", 100);
		orders.add(order);
		order = new Order("4", 500);
		orders.add(order);
		order = new Order("5", 2000);
		orders.add(order);
	}
	
	@AgentBody
	public void body(){
		//bdiFeature.adoptPlan("cookFood");
	}
	
	/**
	 * Plan to cook order. 
	 */
	@Plan
	public void cookFood(){
		
		//Work for time it takes to prepare order
		execFeature.waitForDelay(this.currentlyCooking.getTimeToCook()).get();
		
		//The order should be returned to a customer, not back to the kitchen
		currentlyCooking.setToKitchen(false);
		System.out.println("Finished preparing meal: " + currentlyCooking.getName() + ", now send it to waiter.");
		//TODO: Send the order to a waiter so he can deliver it to a waiter. 
		this.orders.remove(this.currentlyCooking);
		
		if(!orders.isEmpty()){
			this.currentlyCooking = orders.get(0);
			System.out.println("Remaining orders: " + orders.size());
		}
		else{
			this.currentlyCooking = null;			
			//Wait 5 seconds then add another order
			/*
			execFeature.waitForDelay(5000).get();
			Order order = new Order("6", 9000);
			this.orders.add(order);
			*/
		}
	}

	/**
	 * Plan that gets triggered when the order that the chef is currently cooking gets changed.
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@Plan(trigger=@Trigger(factchangeds="currentlyCooking"))
	public void currentlyCookingOrder(ChangeEvent event){
		ChangeInfo<Order> change = ((ChangeInfo<Order>)event.getValue());
		
		Order currentlyCooking = change.getValue();
		
		if(this.currentlyCooking == null){
			
			if(currentlyCooking != null){
				this.currentlyCooking = currentlyCooking;
			}
			else{
				System.out.println("Chef is out of orders to prepare. Alert waiters");
				bdiFeature.adoptPlan(SendMessagePlan.class);
			}

		}
		else{
			System.out.println("Chef is preparing " + this.currentlyCooking.getName());
			bdiFeature.adoptPlan("cookFood");
		}
	}
	
	/**
	 * Plan that gets triggered when additional orders are added. 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@Plan(trigger=@Trigger(factaddeds="orders"))
	public void cookOrder(ChangeEvent event){
		ChangeInfo<Order> change = ((ChangeInfo<Order>)event.getValue()); 
		Order newOrder = change.getValue();
		
		if(this.currentlyCooking == null){
			currentlyCooking = newOrder;
		}
		else{
			if(currentlyCooking != null){
				System.out.println("Chef is busy, " + newOrder.getName() + " is added to queue.");
			}
		}		
	}
	
	/**
	 * Need a plan that gets triggered by incoming message.
	 * The plan will then extract an Order object from the message and add it to the list of orders. 
	 */
	
	/**
	 * Need a plan to send a message that includes and Order to a Waiter.
	 */
	
	
	

}
