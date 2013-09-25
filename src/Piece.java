import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
private String myName;
protected Point middle;
protected ArrayList <Point> squares = new ArrayList <Point>(); 
public int rotation,changeNum;

public Piece (String name){
	myName = name;
	middle = new Point(5,2);
	changeNum = 0;
	rotation=0;
}

public int [][] draw(int[][] grid){
	for (int i = 0; i < squares.size(); i++)
	{
		Point p = squares.get(i);
		grid[p.y][p.x]=changeNum;
	}
	grid[middle.y][middle.x]=changeNum;

	return grid;	
}

public ArrayList<Point> getSquares ()
{
	return squares;
}

public Point getMiddle ()
{
	return middle;
}

public String getName(){
	return myName;
}
public int [][] down(int[][] grid){
	assert(canMove(grid,0));
	grid = delete(grid);
	for (int i = 0;i<squares.size();i++){
		Point p = squares.get(i);
		p.y = p.y+1;			
	}
	
	middle.y = middle.y+1;
	grid = draw(grid);
	return grid;
}

public int[][] delete (int [][]grid)
{	
	for (int i = 0; i < squares.size(); i++)
	{
		grid[squares.get(i).y][squares.get(i).x]=0;

	}
	
	grid[middle.y][middle.x]=0;
	
	return grid;			
}

public int [][] right(int[][] grid){
	assert(canMove(grid,1));
	grid = delete(grid);
	for (int i = 0;i<squares.size();i++){
		Point p = squares.get(i);
		p.x = p.x+1;				
	}
	
	middle.x = middle.x+1;
	grid = draw(grid);
	return grid;	
}
public int [][] left(int[][] grid){
	assert(canMove(grid,2));
	grid = delete(grid);
	for (int i = 0;i<squares.size();i++){
		Point p = squares.get(i);
		p.x = p.x-1;			
		
	}
	middle.x = middle.x-1;
	grid = draw(grid);
	return grid;
}
public abstract boolean canMove(int[][] grid, int direction);
public abstract void performRotate ();
public abstract int[][] drawInto(int [][] nextGrid);

public int[][] hardDrop(int [][] grid)
{
	while(canMove(grid,0))
	{
		grid = down(grid);			
	}
	
	return grid;
};

private boolean isValidAfterRotate (int [][] grid)
{
	for (Point checkPoint : squares)
	{
		if (checkPoint.x < 0 || checkPoint.x > Main.NUMBER_OF_COLUMNS - 1 || checkPoint.y < 0 || checkPoint.y > Main.NUMBER_OF_ROWS - 1 )
			return false;
		if (grid[checkPoint.y][checkPoint.x] != 0)
			return false;
	}
	return true;
}

public final int[][] rotate (int [][] grid)
{
	grid = delete(grid);
	ArrayList<Point> copyPoints = new ArrayList<Point>();
	
	for (Point p : squares)
	{
		copyPoints.add((Point)p.clone());
	}
	
	performRotate();	
	
	if (!isValidAfterRotate(grid))
	{
		squares = copyPoints;
		grid = draw(grid);
		return grid;
	}
	grid = draw(grid);
	rotation++;
	return grid;
}


//public abstract int[][] rotateLeft (int [][] grid, int x, int y);
}
