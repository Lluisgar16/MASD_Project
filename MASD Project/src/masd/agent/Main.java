package masd.agent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jadex.base.PlatformConfiguration;
import jadex.base.Starter;
import jadex.bridge.IExternalAccess;
import jadex.bridge.service.RequiredServiceInfo;
import jadex.bridge.service.search.SServiceProvider;
import jadex.bridge.service.types.cms.CreationInfo;
import jadex.bridge.service.types.cms.IComponentManagementService;
import jadex.commons.future.IFuture;
import masd.gui.Cell;
import masd.gui.CellType;

public class Main {

	public static void main(String[] args) {
		
		PlatformConfiguration config = PlatformConfiguration.getDefaultNoGui();
		config.addComponent("masd.agent.ChefBDI.class");
		Starter.createPlatform(config).get();
	}

}
