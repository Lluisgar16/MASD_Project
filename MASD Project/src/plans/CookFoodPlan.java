package plans;

import jadex.bdiv3.annotation.Plan;
import jadex.bdiv3.annotation.PlanBody;

/**
 * Plan to cook the food in an order
 * @author Eirik
 *
 */
@Plan
public class CookFoodPlan{
	
	public CookFoodPlan(){
		//init beliefs
	}
	
	@PlanBody
	public void cookFood(){
		//cook food
	}
	
	
}
