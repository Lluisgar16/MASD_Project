package masd.agent;

import jadex.bdiv3.annotation.Belief;
import jadex.bridge.IInternalAccess;
import jadex.micro.annotation.Agent;
import jadex.micro.annotation.AgentBody;
import jadex.micro.annotation.Argument;
import jadex.micro.annotation.Arguments;
import jadex.micro.annotation.Description;
import masd.gui.Cell;
import masd.gui.CellType;
import masd.gui.MapGUI;

@Description("The WaiterBDI corresponds to the agents responsible for transporting food to the tables.")
@Arguments({@Argument(name = "Initialx", clazz = Integer.class), 
			@Argument(name = "Initialy", clazz = Integer.class) })

@Agent
public class WaiterBDI  {	
	
	private MapGUI gui;
	private final int[] positionsx = new int[]{6,6,7,8,8,8,7,6};
	private final int[] positionsy = new int[]{2,3,3,3,2,1,1,1};
	
    @Agent
    private IInternalAccess agent;
    	
	@Belief
	private int currentposition_x;
	@Belief
	private int currentposition_y;
	@Belief
	private Cell[][] map;
	@Belief
	private int[] destination = new int[2];
	
	@AgentBody
	public void body()
	{
		
		this.currentposition_x = (int) agent.getArgument("Initialx");
		this.currentposition_y = (int) agent.getArgument("Initialy");
		System.out.println("Created agent "+agent.getComponentIdentifier()+ " In position "+String.valueOf(this.currentposition_x)+","+String.valueOf(this.currentposition_y));
		
		// The map initialization is done by MapperBDI but is now here for testing.
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
		
		this.gui = new MapGUI(rows, cols, map);
		
		// This is a test for moving an agent, but should be done also by MapperBDI when the agent moves and sends to it the position.
		gui.moveAgent(currentposition_x, currentposition_y);
		int j = 0;
		while (j<8)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			gui.moveAgent(positionsx[j], positionsy[j]);
			j++;
		}
	}
}
