package masd.agent;

import java.util.List;

import jadex.bdiv3.annotation.Belief;
import jadex.bdiv3x.runtime.IMessageEvent;
import jadex.bridge.IInternalAccess;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
public class ChefBDI {
	
    @Agent
    private IInternalAccess agent;
	
	@Belief
	List<Order> orders;
	
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
		
		IMessageEvent me = createMessageEvent("query_ref");
		  me.getParameterSet(SFipa.RECEIVERS).addValue(cid);
		  // Set/change content if necessary
		  me.getParameter(SFipa.CONTENT).setValue("ping 2"); 
		  sendMessage(me);
		
	}

}
