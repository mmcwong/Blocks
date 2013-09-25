import java.awt.Point;


public class RZPiece extends Piece {

	public RZPiece() {
		super("RZ");
		squares.add(new Point(middle.x+1, middle.y-1));       
		squares.add(new Point(middle.x, middle.y-1));
		squares.add(new Point(middle.x-1, middle.y));
		changeNum = 6;
	}

	@Override
	public boolean canMove(int[][] grid, int direction) {
		try{
			if (direction==0){//down
				
				if(rotation%2==0){
				Point p = squares.get(0);
				Point r = squares.get(2);							
				if (grid[p.y+1][p.x]!=0||grid[r.y+1][r.x]!=0||grid[middle.y+1][middle.x]!=0)
					return false;
				return true;	
				}				
				else {
					Point p = squares.get(0);
					Point r = middle;							
					if (grid[p.y+1][p.x]!=0||grid[r.y+1][r.x]!=0)
						return false;
					return true;	
				}			
			}
						
			else if (direction==1){//right
				if(rotation%2==0){
					Point p = squares.get(0);
					Point r = middle;		
					if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0)
						return false;
					return true;	
				}
				else {
					Point p = squares.get(0);
					Point r = squares.get(1);
					Point s = squares.get(2);
					if (grid[p.y][p.x+1]!=0||grid[r.y][r.x+1]!=0|| grid[s.y][s.x+1]!=0)
						return false;
					return true;	
				}			
				
			}
			
			else if (direction==2){//left
				
				if(rotation%2==0){
					Point p = squares.get(2);
					Point r = squares.get(1);
					if (grid[p.y][p.x-1]!=0||grid[r.y][r.x-1]!=0)
						return false;
					return true;	
				}
				else {
					Point p = squares.get(0);
					Point r = squares.get(2);
					Point s = middle;
					if (grid[p.y][p.x-1]!=0||grid[r.y][r.x-1]!=0|| grid[s.y][s.x-1]!=0)
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
	public void performRotate() {
		
		Point p = squares.get(0);
		if (rotation%2==0){
			p.y+=2;
		}
		else{
			p.y-=2;
		}
		p = squares.get(1);
		
		if(rotation%2==0){
			p.x+=1;
			p.y+=1;
		}
		else{
			p.x-=1;
			p.y-=1;
		}
		
		p = squares.get(2);
		if(rotation%2==0){
			p.x+=1;
			p.y-=1;
		}
		
		else{
			p.x-=1;
			p.y+=1;
		}		
	}
	
	@Override
	public int[][] drawInto(int[][] nextGrid) {
		
		nextGrid[2][2]= changeNum;
		nextGrid[2][3]= changeNum;
		nextGrid[3][1]= changeNum;
		nextGrid[3][2]= changeNum;
	
		return nextGrid;
	}

}
