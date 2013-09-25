import java.awt.Point;
import java.util.ArrayList;
import java.util.List;


public class IPiece extends Piece {

	public IPiece() {
		super("I");
		squares.add(new Point(middle.x-1, middle.y));       
		squares.add(new Point(middle.x+1, middle.y));
		squares.add(new Point(middle.x+2, middle.y));
		changeNum = 2;
	}

	@Override
	public boolean canMove(int[][] grid, int direction) {
		try{
			if (direction==0){//down
				
				if(rotation%2==0){
				Point p = squares.get(0);
				Point r = squares.get(2);
				Point s = squares.get(1);
				if (grid[p.y+1][p.x]!=0||grid[r.y+1][r.x]!=0||grid[middle.y+1][middle.x]!=0||grid[s.y+1][s.x]!=0)
					return false;
				return true;	
				}		
				
				else{
					Point p = squares.get(2);					
					if (grid[p.y+1][p.x]!=0)
						return false;
					return true;	
					
				}				

			}
						
			else if (direction==1){//right
				
				if(rotation%2==0){
					Point p = squares.get(2);
					if (grid[p.y][p.x+1]!=0)
						return false;
					return true;	
				}
				
				else {
					Point p = squares.get(0);
					Point r = squares.get(2);
					Point s = squares.get(1);
					if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0||grid[middle.y][middle.x+1]!=0||grid[s.y][s.x+1]!=0)
						return false;
					return true;	
				}
			}
			
			else if (direction==2){//left
				
				if(rotation%2==0){
					Point p = squares.get(0);
					if (grid[p.y][p.x-1]!=0)
						return false;
					return true;	
				}
				
				else {
					Point p = squares.get(2);
					Point r = squares.get(1);
					Point s = squares.get(0);
					if (grid[p.y][p.x-1]!=0||grid[r.y][r.x-1]!=0||grid[s.y][s.x-1]!=0||grid[middle.y][middle.x-1]!=0)
						return false;
					return true;	
				}
			}
			else if (direction==3){//rotate right
				
			}
			
			else {//rotate left
				
			}
		}
		catch (ArrayIndexOutOfBoundsException e){
			return false;
		}
			
		return false;
	}
	@Override
	public void performRotate()
	{
Point p = squares.get(0);
		
		if (rotation%2==0)
		{
			p.x=p.x+1;
			p.y=p.y-1;
		}
		else
		{
			p.x=p.x-1;
			p.y=p.y+1;
		}
		
		p = squares.get(1);
		
		if (rotation%2==0)
		{
			p.x=p.x-1;
			p.y=p.y+1;
		}
		else
		{
			p.x=p.x+1;
			p.y=p.y-1;
		}
		
		p = squares.get(2);
		if (rotation%2==0)
		{
			p.x=p.x-2;
			p.y=p.y+2;
		}
		else
		{
			p.x=p.x+2;
			p.y=p.y-2;
		}		
	}

	@Override
	public int[][] drawInto(int[][] nextGrid) {
		
		nextGrid[0][2]= changeNum;
		nextGrid[1][2]= changeNum;
		nextGrid[2][2]= changeNum;
		nextGrid[3][2]= changeNum;
		
		return nextGrid;
	}
	
}
