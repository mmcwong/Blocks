import java.awt.Point;


public class BigOPiece extends Piece {

	public BigOPiece() {
		super("BO");
		squares.add(new Point(middle.x-1, middle.y-1));
		squares.add(new Point(middle.x, middle.y-1));       
		squares.add(new Point(middle.x+1, middle.y-1));
		
		squares.add(new Point(middle.x-1, middle.y));
		squares.add(new Point(middle.x+1, middle.y));       

		squares.add(new Point(middle.x-1, middle.y+1));
		squares.add(new Point(middle.x, middle.y+1));       
		squares.add(new Point(middle.x+1, middle.y+1));
		changeNum = 8;
	}

	@Override
	public boolean canMove(int[][] grid, int direction) {
		try{
			if (direction==0){//down				
				for(int i = 5; i <= 7; i++)
				{
					Point p = squares.get(i);
					if (grid[p.y+1][p.x]!=0)
						return false;
				}
				return true;				
			}						
			else if (direction==1){//right
				Point p = squares.get(2);
				Point r = squares.get(4);
				Point q = squares.get(7);
				if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0||grid[q.y][q.x+1]!=0)
					return false;
				return true;
				
			}
			
			else if (direction==2){//left
				Point p = squares.get(0);
				Point r = squares.get(3);
				Point q = squares.get(5);
				if (grid[p.y][p.x-1]!=0||grid[r.y][r.x-1]!=0||grid[q.y][q.x-1]!=0)
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
		nextGrid[1][3]= changeNum;
		
		nextGrid[2][1]= changeNum;
		nextGrid[2][2]= changeNum;
		nextGrid[2][3]= changeNum;

		nextGrid[3][1]= changeNum;
		nextGrid[3][2]= changeNum;
		nextGrid[3][3]= changeNum;
		
		return nextGrid;
	}

}
