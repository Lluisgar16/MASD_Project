package masd.agent;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;

public class Main {

	public static void main(String[] args) {
		
		PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();
		config.addComponent("masd.agent.ChefBDI.class");
		config.addComponent("masd.agent.WaiterBDI.class");
		Starter.createPlatform(config).get();
	}

}
