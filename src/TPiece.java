
import java.awt.Point;

public class TPiece extends Piece {
	
	
	public TPiece ()
	{
		super("T");
		changeNum=1;
		squares.add(new Point(middle.x-1, middle.y));       
		squares.add(new Point(middle.x, middle.y-1));
		squares.add(new Point(middle.x+1, middle.y));
		
	}	


	
	public boolean canMove (int [][] grid, int direction){//0 down, 1 right, 2 up, 3 rotate right, 4 rotate left
		try{
			if (direction==0){//down
				
				if(rotation%4==0){
				Point p = squares.get(0);
				Point r = squares.get(2);							
				if (grid[p.y+1][p.x]!=0||grid[r.y+1][r.x]!=0||grid[middle.y+1][middle.x]!=0)
					return false;
				return true;	
				}
				
				else if (rotation%4==1){
					Point p = squares.get(1);
					Point r = squares.get(2);							
					if (grid[p.y+1][p.x]!=0||grid[r.y+1][r.x]!=0)
						return false;
					return true;	
				}
				
				else if (rotation%4==2){
					Point p = squares.get(0);
					Point r = squares.get(2);
					Point q = squares.get(1);
					if (grid[p.y+1][p.x]!=0||grid[r.y+1][r.x]!=0||grid[q.y+1][q.x]!=0)
						return false;
					return true;	
				}
				
				else{
					Point p = squares.get(0);
					Point r = squares.get(1);
					if (grid[p.y+1][p.x]!=0||grid[r.y+1][r.x]!=0)
						return false;
					return true;	
					
				}				

			}
						
			else if (direction==1){//right
				System.out.println(rotation);
				if(rotation%4==0){
					Point p = squares.get(1);
					Point r = squares.get(2);		
					if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0)
						return false;
					return true;	
				}
				else if (rotation%4==1){
					System.out.println("ls");
					Point p = squares.get(0);
					Point r = squares.get(1);
					Point s = squares.get(2);
					if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0|| grid[s.y][s.x+1]!=0)
						return false;
					return true;	
				}
				
				else if (rotation%4==2){
					Point p = squares.get(0);
					Point r = squares.get(1);			
					if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0)
						return false;
					return true;	
				}
				
				else {
					Point p = squares.get(0);
					Point r = squares.get(2);			
					if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0||grid[middle.y][middle.x+1]!=0)
						return false;
					return true;	
				}
			}
			
			else if (direction==2){//left
				
				System.out.println(rotation);
				if(rotation%4==0){
					Point p = squares.get(0);
					Point r = squares.get(1);		
					if (grid[p.y][p.x-1]!=0||grid[r.y][r.x-1]!=0)
						return false;
					return true;	
				}
				else if (rotation%4==1){
					System.out.println("ls");
					Point p = squares.get(0);
					Point r = squares.get(2);
					Point s = middle;
					if (grid[p.y][p.x-1]!=0||grid[r.y][r.x-1]!=0|| grid[s.y][s.x-1]!=0)
						return false;
					return true;	
				}
				
				else if (rotation%4==2){
					Point p = squares.get(2);
					Point r = squares.get(1);			
					if (grid[p.y][p.x-1]!=0||grid[r.y][r.x-1]!=0)
						return false;
					return true;	
				}
				
				else {
					Point p = squares.get(2);
					Point r = squares.get(1);
					Point s = squares.get(0);
					if (grid[p.y][p.x-1]!=0||grid[r.y][r.x-1]!=0||grid[s.y][s.x-1]!=0)
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
	public void performRotate(){

		Point p = squares.get(0);
		
		if (rotation%4==0||rotation%4==1)
		{
			p.x=p.x+1;
		}
		else
		{
			p.x=p.x-1;
		}
		
		if (rotation%4==0||rotation%4==3)
		{
			p.y=p.y-1;
		}
		else
		{
			p.y=p.y+1;
		}
				
		p = squares.get(1);
		
		if (rotation%4==0||rotation%4==3)
		{
			p.x=p.x+1;
		}
		else
		{
			p.x=p.x-1;
		}
		
		if (rotation%4==2||rotation%4==3)
		{
			p.y=p.y-1;
		}
		else
		{
			p.y=p.y+1;
		}
		
		p = squares.get(2);
		if (rotation%4==2||rotation%4==3)
		{
			p.x=p.x+1;
		}
		else
		{
			p.x=p.x-1;
		}
		
		if (rotation%4==1||rotation%4==2)
		{
			p.y=p.y-1;
		}
		else
		{
			p.y=p.y+1;
		}		
	}

	@Override
	public int[][] drawInto(int[][] nextGrid) {
		
		nextGrid[2][1]= changeNum;
		nextGrid[2][2]= changeNum;
		nextGrid[2][3]= changeNum;
		nextGrid[1][2]= changeNum;
	
		return nextGrid;
	}
}
