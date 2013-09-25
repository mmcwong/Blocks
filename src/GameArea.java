import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;



public class GameArea extends JPanel {
	
	private int [][] grid;
    private Piece current;
    private BufferedImage tile, red_tile;
    
	
	public GameArea (){
		this.setSize(350, 600);
    	setUpGrid();
    	try{
    	red_tile =  ImageIO.read(new File("C:/Users/PuiHung/workspace/Test/Resources/tile2.jpg"));
    	tile =  ImageIO.read(new File("C:/Users/PuiHung/workspace/Test/Resources/tile.jpg"));

    	}
    	catch(Exception e){
    		
    	}
    	
    	Timer clock = new Timer ();
    	clock.schedule (new Fall(),1,1*2000);	
    	
	}

    public void paint(Graphics g){
    	for (int i = 0;i<grid.length;i++){
			for (int j = 0;j<grid[0].length;j++)
			{
//				System.out.print(grid[i][j]);
				if(grid[i][j]==1)
					g.drawImage((Image)red_tile,j*30,i*30,null);
				else
				g.drawImage((Image)tile, j*30, i*30,null);
			}
		}
    }
    
    
    
    private void setUpGrid() {
        grid = new int [20][10];
        for (int i = 0;i<grid.length;i++){
    			for (int j = 0;j<grid[0].length;j++)
    			{
    				grid[i][j] = 0;
    			}
    		}
        int level = 1;
        current = new TPiece();
        grid = current.draw(grid);
        
      }
 
    public void Down ()
   	{
   		if(current.canMove(grid,0)){
   			grid = current.down(grid);
   		}
   		
   		else{
   			System.out.println("cannot move");
   			current = new TPiece();
   		}
   		repaint();
}
   	
   	public  void Rotate ()
   	{
   		grid = current.rotate(grid);
   		repaint();
   	}
   	
    
    class Fall extends TimerTask {

		public void run() {
			Down();
//			MainApp.Rotate();
		}
		
	}
    
}
