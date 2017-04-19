package masd.agent;

import jadex.micro.annotation.AgentBody;

public class ChefBDI {
	
	private int positionx;
	private int positiony;
	
	@AgentBody
	public void body(int row, int col)
	{
		this.positionx = row;
		this.positiony = col;
	}
}
