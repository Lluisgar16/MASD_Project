package masd.agent;

import java.util.ArrayList;
import java.util.List;

import jadex.bdiv3.annotation.Belief;
import jadex.bridge.IInternalAccess;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;
import jadex.micro.annotation.Description;

@Description("The WaiterBDI corresponds to the agents responsible for transporting food to the tables.")
@Arguments({@Argument(name = "orders", clazz = List.class)})

@Agent
public class WaiterBDI  {	

	
    @Agent
    private IInternalAccess agent;
    
    @Belief
    private List<String> orders = new ArrayList<>();
    
	private String name; 


    
	
	@AgentBody
	public void body()
	{
		String s = agent.getComponentIdentifier().toString();
		if(s.contains("@")){
			String[] splitS = s.split("@");	
			this.name = splitS[0];
		}
		else{
			this.name = "NoName";
		}
		System.out.println(name + " just arrived at work.");
		
	}


	@Override
	public String toString() {
		String s = "";
		s+= "Waiter " + name + " just arrived at work.\n";
		s+= "Number of orders: " + orders.size();
		return s;
	}
	
	
	
}
