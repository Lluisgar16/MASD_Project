package masd.agent;

import jadex.base.PlatformConfiguration;
import jadex.base.RootComponentConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
		
		PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();
		RootComponentConfiguration rootComp = config.getRootConfig();
		
		//Adds logging
		//rootComp.setLogging(true);
		rootComp.setWelcome(false);
		rootComp.setPrintPass(false);
		config.setPlatformName("Masd");
		
		config.addComponent("masd.agent.ChefBDI.class");
		config.addComponent("masd.agent.WaiterBDI.class");
		Starter.createPlatform(config).get();
	
	}
}
