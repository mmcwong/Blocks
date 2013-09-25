import java.awt.Point;


public class OPiece extends Piece {

	public OPiece() {
		super("O");
		squares.add(new Point(middle.x, middle.y-1));       
		squares.add(new Point(middle.x+1, middle.y-1));
		squares.add(new Point(middle.x+1, middle.y));
		changeNum = 3;
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean canMove(int[][] grid, int direction) {
		try{
			if (direction==0){//down				
				
				Point p = squares.get(2);
				if (grid[p.y+1][p.x]!=0||grid[middle.y+1][middle.x]!=0)
					return false;
				return true;	
			}						
			else if (direction==1){//right
				
					Point p = squares.get(1);
					Point r = squares.get(2);
					if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0)
						return false;
					return true;
				
			}
			
			else if (direction==2){//left
				Point p = squares.get(0);
				if (grid[p.y][p.x-1]!=0||grid[middle.y][middle.x-1]!=0)
						return false;
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
		
	}
	
	@Override
	public int[][] drawInto(int[][] nextGrid) {
		
		nextGrid[1][1]= changeNum;
		nextGrid[1][2]= changeNum;
		nextGrid[2][1]= changeNum;
		nextGrid[2][2]= changeNum;
	
		return nextGrid;
	}

}
