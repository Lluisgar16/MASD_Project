package masd.agent;

import jadex.bdiv3x.runtime.Plan;

public class SendOrderPlan extends Plan {

	@Override
	public void body() {
		System.out.println("Plan is triggered");
		
	}

}
