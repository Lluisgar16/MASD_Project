package masd.gui;

public enum CellType {
	
	FLOOR
	{
		public String getString()
		{
			return "Floor";
		}
	},
	
	KITCHEN
	{
		public String getString()
		{
			return "Kitchen";
		}
	},
	
	TABLE
	{
		public String getString()
		{
			return "Table";
		}
	},
	
}
