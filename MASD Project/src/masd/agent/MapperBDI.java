package masd.agent;

import jadex.bridge.IInternalAccess;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;

@Agent
public class MapperBDI {
	
    @Agent
    private IInternalAccess agent;
	
	@AgentBody
	public void body()
	{
		System.out.println("Created agent "+agent.getComponentIdentifier());
	}

}
