package masd.gui;

public class Cell {
	
	private int row;
	private int col;
	private CellType type;
	
	public Cell(int row, int col, CellType type)
	{
		this.row = row;
		this.col = col;
		this.type = type;
	}
	
	public CellType getType()
	{
		return this.type;
	}
}
