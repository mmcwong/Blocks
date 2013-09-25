import java.awt.Point;
import java.util.ArrayList;


public class DotPiece extends Piece {

	public DotPiece() {
		super("DP");
		squares.add(new Point(middle.x + 1, middle.y));
		changeNum = 9;
	}

	@Override
	public boolean canMove(int[][] grid, int direction) {
		try{
			if (direction==0){//down
				
				Point p = squares.get(0);
				if (rotation % 2 == 0)
				{
					if (grid[p.y+1][p.x]!= 0 || grid[middle.y+1][middle.x] != 0)
						return false;
				}
				else
				{
					if (grid[p.y+1][p.x] != 0)
						return false;
				}
				return true;				
			}						
			else if (direction==1){//right
				
				Point p = squares.get(0);
				if (rotation % 2 == 0)
				{
					if (grid[p.y][p.x+1]!=0)
						return false;
				}
				else
				{
					if (grid[p.y][p.x+1]!=0 || grid[middle.y][middle.x+1] != 0)
						return false;
				}
				return true;				
			}
			
			else if (direction==2){//left
				
				Point p = squares.get(0);
				if(rotation % 2 == 0)
				{
					if (grid[middle.y][middle.x-1]!=0)
						return false;
				}
				else
				{
					if (grid[p.x][p.x - 1] != 0 || grid[middle.y][middle.x-1]!=0)
						return false;
				}
					return true;				
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
	public void performRotate() {
		
		Point p = squares.get(0);
		
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
	
	}
	
	@Override
	public int[][] drawInto(int[][] nextGrid) {
		
		nextGrid[2][1]= changeNum;
		nextGrid[2][2]= changeNum;
	
		return nextGrid;
	}
	

}
