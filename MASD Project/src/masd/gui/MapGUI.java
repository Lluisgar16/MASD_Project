package masd.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MapGUI {
	
	private final int rows;
	private final int cols;
	private final Cell[][] map;
	private final int cellwidth = 75;
	private final int cellheight = 50;
	private int x,y;
	private Pane pane = new Pane();
	
	public MapGUI(int rows, int cols, Cell[][] map)
	{
		this.rows = rows;
		this.cols = cols;
		this.map = map;
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("MAP");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                pane.setBorder(BorderFactory.createLineBorder(Color.black));
        		frame.add(pane);             
                frame.pack();
                frame.setLocation(100,100);
                frame.setVisible(true);
            }
        });
    }
	
	public void moveAgent(int x, int y)
	{
		this.x = x;
		this.y = y;
		pane.paint();
	}
	
	public class Pane extends JPanel
	{		
		public void paint()
		{
			repaint();
		}
		
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(cellwidth*rows, cellheight*cols);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            // Waiter paintings should go here, they are not fix and will be going to change.
            // I have find a way to send a message to the MapperBDI who will be responsible of moving the agents in the GUI
            g.setColor(Color.BLUE);
            g.fillOval(x*cellwidth+10, y*cellheight+5, cellwidth-20, cellheight-10);
            g.setColor(Color.WHITE);
            g.drawString("W", x*cellwidth+30, y*cellheight+30);
            
            
            // Chef and tables are painted always on the same position.
            g.setColor(Color.BLACK);
            for (int i = 0; i < rows; i += 1) {
                for (int j = 0; j < cols; j += 1) {
                	if(map[i][j].getType() == CellType.TABLE)
                	{
                		g.setColor(Color.RED);
                		g.fillRect(i*cellwidth, j*cellheight, cellwidth, cellheight);
                		g.setColor(Color.WHITE);
                        g.drawString("T", i*cellwidth+30, j*cellheight+30);
                		g.setColor(Color.BLACK);
                	}
                	
                	else if(map[i][j].getType() == CellType.KITCHEN)
                	{
                        g.setColor(Color.MAGENTA);
                        g.fillOval(i*cellwidth+10,j*cellheight+5,cellwidth-20,cellheight-10);
                        g.setColor(Color.BLACK);
                        g.drawString("C", i*cellwidth+30, j*cellheight+30);
                	}
                	
                	g.drawRect(i*cellwidth, j*cellheight, cellwidth, cellheight);
                }
             }
        }
	}
}
