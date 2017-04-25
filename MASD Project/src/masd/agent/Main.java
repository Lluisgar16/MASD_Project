package masd.agent;

import java.util.HashMap;
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
		
		int rows = 9;
		int cols = 9;
		Cell[][] map = new Cell[rows][cols];
		for (int i = 0; i < rows; i += 1)
		{
			for (int j = 0; j < cols; j += 1)
			{
				if((i == 1 || i == 4) && (j == 1 || j == 4 || j == 7)) { map[i][j] = new Cell(i,j,CellType.TABLE); }
				else if((i == 7) && (j == 2 || j == 6)) { map[i][j] = new Cell(i,j,CellType.KITCHEN); }
				else { map[i][j] = new Cell(i,j,CellType.FLOOR); }
			}
		}
		
		//MapGUI gui = new MapGUI(rows, cols, map);
		
		

		PlatformConfiguration configuration = PlatformConfiguration.getDefaultNoGui();
		configuration.setPlatformName("Masd");
		IFuture<IExternalAccess> platfut = Starter.createPlatform(configuration);
		IExternalAccess platform = platfut.get();
		IComponentManagementService cms = SServiceProvider.getService(platform.getServiceProvider(), IComponentManagementService.class, RequiredServiceInfo.SCOPE_PLATFORM).get();
		
		Map<String, Object> agentparameters = new HashMap<String, Object>();
		agentparameters.put("Initialx", 6);
		agentparameters.put("Initialy", 2);
		CreationInfo info = new CreationInfo(agentparameters);
		cms.createComponent("Waiter1","masd.agent.WaiterBDI.class", info);
		agentparameters.put("Initialx", 6);
		agentparameters.put("Initialy", 7);
		info.setArguments(agentparameters);
		//cms.createComponent("Waiter2","masd.agent.WaiterBDI.class", info);
		cms.createComponent("Mapper", "masd.agent.MapperBDI.class", new CreationInfo());
	}

}
