import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import netscape.javascript.JSException;
import netscape.javascript.JSObject;


public class Main extends JApplet implements KeyListener{

    private int [][] grid;
    private Piece current, next, hold;
    private BufferedImage tile, 
    						red_tile,
    						purple_tile, 
    						blue_tile,
    						green_tile,
    						yellow_tile,
    						orange_tile,
    						magenta_tile,
    						black_tile,
    						turquoise_tile,
    						tile_mini, 
    						red_tile_mini,
    						purple_tile_mini, 
    						blue_tile_mini,
    						green_tile_mini,
    						yellow_tile_mini,
    						orange_tile_mini,
    						magenta_tile_mini,
    						black_tile_mini,
    						turquoise_tile_mini;
    
    private boolean alreadyHold;
    private int score, lines, level;
    private String scoreString, levelString, linesString;
    private MyLabel scoreLabel, levelLabel, linesLabel;
    private Timer clock;
    private GamePanel gamePanel;
    private NextPanel nextPanel, holdPanel;
    private JPanel info;
    
    public static final int NUMBER_OF_ROWS = 20;
    public static final int NUMBER_OF_COLUMNS = 10;
    
    public void createApp(){
    	
    	JPanel pane = new JPanel(new GridBagLayout());
    	gamePanel = new GamePanel();
    	nextPanel = new NextPanel(true);
    	holdPanel = new NextPanel(false);
    	info = new JPanel(new GridBagLayout());
    	
    	scoreLabel = new MyLabel();
    	levelLabel = new MyLabel();
    	linesLabel = new MyLabel();
    	
    	MyLabel blocks = new MyLabel("BLOCKS");
    	Font f = new Font ("Myriad Pro", Font.BOLD, 30);
    	blocks.setFont(f);
    	
    	setUpGrid();
    	loadTiles();
    	
    	score = 0;
    	lines = 0;
    	level = 1;
    	alreadyHold = false;
    	hold = null;
    	scoreString = "Score: " + score;
    	linesString = "Lines: " + lines;
    	levelString = "Level: " + level;
    	
    	scoreLabel.setText(scoreString);
    	linesLabel.setText(linesString);
    	levelLabel.setText(levelString);

    	GridBagConstraints c = new GridBagConstraints();
    	c.gridx = 0;
    	c.gridy = 0;
    	c.gridwidth = 10;
    	c.gridheight = 10;
    	c.fill = GridBagConstraints.BOTH;
    	c.weightx = 1.0;
    	c.weighty = 1.0;
    	pane.add(gamePanel, c);    	
    	c.gridx = 10;
    	c.gridwidth = 1;
    	c.gridheight = 10;
    	c.weightx = 0.0;
    	pane.add(info, c);
    	
    	GridBagConstraints d = new GridBagConstraints();
    	d.gridx = 0;
    	d.gridy = 0;
    	d.gridwidth = 1;
    	d.gridheight = 1;
    	d.fill = GridBagConstraints.BOTH;
    	d.insets = new Insets(5, 5, 5, 5);
    	info.add (blocks, d);
    	d.gridy = 1;
    	info.add(scoreLabel, d);
    	d.gridy = 2;
    	info.add(linesLabel, d);
    	d.gridy = 3;
    	info.add(levelLabel, d);
    	d.gridy = 4;
    	info.add(new MyLabel("Next"), d);
    	d.gridy = 5;
    	d.weighty = 1.0;
    	info.add(nextPanel, d);
    	d.gridy = 6;
    	d.weighty = 0.0;
    	info.add(new MyLabel("Hold (C)"), d);
    	d.weighty = 1.0;
    	d.gridy = 7;
    	info.add(holdPanel, d);

    	gamePanel.addKeyListener(this);
    	gamePanel.setFocusable(true);
    	    	
    	this.setContentPane(pane);    	
    	this.setSize(450, 600);
    	this.setVisible(true);
    	
    	clock = new Timer (1100-(100*level), new Fall());
    	clock.start();
    }
    
    private void loadTiles ()
    {
    	try{
    		magenta_tile =loadImage("tile2.jpg");
    		tile = loadImage("tile.jpg");
    		purple_tile = loadImage("tile3.jpg");
    		blue_tile = loadImage("tile4.jpg");
    		green_tile = loadImage("tile5.jpg");
    		yellow_tile = loadImage("tile6.jpg");
    		orange_tile = loadImage("tile7.jpg");
    		red_tile = loadImage("tile8.jpg");
    		black_tile = loadImage("tile9.jpg");
    		turquoise_tile = loadImage("tile10.jpg");
    		
    		magenta_tile_mini =loadImage("tile2Mini.jpg");
    		tile_mini = loadImage("tileMini.jpg");
    		purple_tile_mini = loadImage("tile3Mini.jpg");
    		blue_tile_mini = loadImage("tile4Mini.jpg");
    		green_tile_mini = loadImage("tile5Mini.jpg");
    		yellow_tile_mini = loadImage("tile6Mini.jpg");
    		orange_tile_mini = loadImage("tile7Mini.jpg");
    		red_tile_mini = loadImage("tile8Mini.jpg");
    		black_tile_mini = loadImage("tile9Mini.jpg");
    		turquoise_tile_mini = loadImage("tile10Mini.jpg");
    	}
    	catch(Exception e){
    		System.out.println("COULD NOT LOAD FILE"+e.getMessage());
    	}    	
    }
    
    public BufferedImage loadImage (String file) throws IOException  // this method reads and loads the image
    {
      InputStream m = this.getClass().getResourceAsStream(file);
//      System.out.println(m);
	  return ImageIO.read(m); //In Deck folder  
    }
  
    
    private void setUpGrid() {
        grid = new int [NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        for (int i = 0;i<grid.length;i++){
    		for (int j = 0;j<grid[0].length;j++)
    			grid[i][j] = 0;
    	}
              
        current = getNewPiece();
        updateNext();
        updateHold();
        grid = current.draw(grid);        
      }
    
    
    private Piece getNewPiece(){
    	checkIfClear();
    	
    	alreadyHold = false;
    	Piece p;
    	int number = (int) (Math.random() * 9) + 1; 
    	if(number == 1){
    		p = new TPiece();
    	}
    	else if (number == 2){
    		p = new IPiece();
    	}
    	else if (number == 3){
    		p = new LPiece();
    	}
    	else if (number == 4){
    		p = new OPiece();
    	}
    	else if (number == 5){
    		p = new ZPiece();
    	}
    	else if (number == 6){
    		p = new RZPiece();
    	}
    	else if (number == 7){
    		p = new RLPiece();
    	}
    	else if (number == 8){
    		p = new BigOPiece();
    	}
    	else{
    		p = new DotPiece();
    	}
    	
    	if (isGameOver(p))
    	{
    		clock.stop();
    		finish();
    		return null;
    	}
    		
    	return p;
    }
    
    private void finish() {
    	
    	String userName = (String)JOptionPane.showInputDialog(
                this,
                "Score: " + score +"\n"+
                "Please enter in your name: ",
                "High Score",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "Guest");
    	
    	if (userName == null)
    	{
    		System.exit(0);
    	}
    	else if (userName.trim().equals(""))
    		userName = "Guest";
    	 
    	try
    	{
    		JSObject window = JSObject.getWindow(this);
    		window.setMember("score", score);
    		window.setMember("user", userName);
    		window.setMember("level", level);
    		window.setMember("lines", lines);
    		window.eval("finishGame()");
    	}
    	catch (JSException jse) 
    	{
    		jse.printStackTrace();
    	}
    	System.exit(0);
	}

	private boolean isGameOver(Piece piece) {
		
    	for(Point p : piece.getSquares())
    	{
    		if (grid[p.y][p.x] != 0)
    			return true;
    	}
    	
    	if (grid[piece.getMiddle().y][piece.getMiddle().x] != 0)
			return true;
    	
    	return false;		
	}

	private void checkIfClear() {
    	
		int numLinesCleared = 0;
		
    	for (int i = 0; i < grid.length; i++){
    		int j = 0;
    		for (j = 0; j < grid[0].length; j++){
    			if(grid[i][j] == 0){
    				j = 99;
    			}
    		}
    		if(j!=100){
    			numLinesCleared++;
    			shiftGrid(i);
    		}
    	}
    	updateScore(numLinesCleared);
		
	}

	private void updateScore(int numLinesCleared) {
		if(numLinesCleared==4){
			score += 700;
		}
		else if(numLinesCleared==3){
			score += 300;
		}
		else if (numLinesCleared==2){
			score += 200;
		}		
		else if (numLinesCleared==1){
			score += 50;
		}
		
		lines += numLinesCleared;
		
		int tempLevel = (score/1000)+1;
		
		if (tempLevel > 10)
			tempLevel = 10;
		
		if (tempLevel != level)
		{
			level = tempLevel;
			
			if(clock != null)
			{
				clock.stop();
				int delay = 1100-(100*level);
				clock.setDelay(delay);
				clock.start();
			}
		}
		
		scoreString = "Score: " + score;
		levelString = "level: " + level;
		linesString = "Lines: " + lines;
		
		scoreLabel.setText(scoreString);
		levelLabel.setText(levelString);
		linesLabel.setText(linesString);
		
		gamePanel.repaint();
	}

	private void shiftGrid(int i) {
//		System.out.println("i "+i);
		for (int x = i;x>2;x--){
			for (int y = 0;y<grid[0].length;y++)
			{
//				System.out.println("x "+x+" y "+y+" x-1 "+(x-1));
				grid[x][y] = grid[x-1][y];
			}
		}
		
	}
	    
    public void Down ()
   	{
   		if(current.canMove(grid,0)){
   			grid = current.down(grid);
   		}
   		
   		else{
//   			System.out.println("cannot move");
   			current = next;
   			updateNext();
   		}
   		gamePanel.repaint();
   		info.repaint();
   	}
    
    public void Left ()
    {
    	if(current.canMove(grid,2))
    	{
    		grid = current.left(grid);
        	gamePanel.repaint();        	
    	}   	
    	
    }
    
    public void HardDrop()
    {
    	grid = current.hardDrop(grid);
		current = next;
		updateNext();
    	gamePanel.repaint();
   		info.repaint();
    }
    
    public void Right ()
    {
//    	System.out.println("RIGHT");
//    	System.out.println(current);
    	if(current.canMove(grid,1))
    	{
    		grid = current.right(grid);
        	gamePanel.repaint();
    	}
    	else{
//    		System.out.println("CANNOT RIGHT");
    	}
    	
    }
   	
   	public void Rotate ()
   	{
   		grid = current.rotate(grid);
   		gamePanel.repaint();
   	}
   	
   	public void Store(){
   		if (hold == null && alreadyHold!=true){
   			System.out.println(current.getName());
   			if(current.getName().equals("Z"))
   				hold = new ZPiece();
   			else if (current.getName().equals("O"))
   				hold = new OPiece();
   			else if (current.getName().equals("L"))
   				hold = new LPiece();
   			else if (current.getName().equals("T"))
   				hold = new TPiece();
   			else if (current.getName().equals("RZ"))
   				hold = new RZPiece();
   			else if (current.getName().equals("I"))
   				hold = new IPiece();
   			else if (current.getName().equals("RL"))
   				hold = new RLPiece();
   			else if (current.getName().equals("BO"))
   				hold = new BigOPiece();
   			else
   				hold = new DotPiece();
   			
   			updateHold();
   			current.delete(grid);
   			current = next;
   			updateNext();
   			alreadyHold = true;
   		}
   		else if (alreadyHold != true){
   			Piece temp = current;
   			current.delete(grid);
   			current = hold;  
   			hold = temp;
  			
   			if(temp.getName().equals("Z"))
   				hold = new ZPiece();
   			else if (temp.getName().equals("O"))
   				hold = new OPiece();
   			else if (temp.getName().equals("L"))
   				hold = new LPiece();
   			else if (temp.getName().equals("T"))
   				hold = new TPiece();
   			else if (temp.getName().equals("RZ"))
   				hold = new RZPiece();
   			else if (temp.getName().equals("I"))
   				hold = new IPiece();
   			else if (temp.getName().equals("RL"))
   				hold = new RLPiece();
   			else if (temp.getName().equals("BO"))
   				hold = new BigOPiece();
   			else
   				hold = new DotPiece();

   			updateHold();
   			alreadyHold = true;
   		}
   		gamePanel.repaint();
   		info.repaint();
   	}

    
   	private void updateNext() {
   		nextPanel.clear();
		next = getNewPiece();
		nextPanel.update();
		nextPanel.repaint();				
	}

	private void updateHold ()
   	{
   		if (hold == null)
   			return;
   		
   		holdPanel.clear();
   		holdPanel.update();
		holdPanel.repaint();	
   	}

	//	public static void main(String[] args) {
//		Main game = new Main();
//		
//	}
//	
   	public void init(){
   		createApp();
   	}
   	
	class Fall implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			Down();			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
		if(arg0.getKeyCode()==KeyEvent.VK_DOWN)
		{
			Down();
		}
		else if (arg0.getKeyCode()==KeyEvent.VK_UP)
		{
			Rotate();
		}
		
		else if (arg0.getKeyCode()==KeyEvent.VK_LEFT){
			Left();
//			System.out.println("HERE");
		}
		
		else if (arg0.getKeyCode()==KeyEvent.VK_RIGHT){
			Right();
		}
		
		else if (arg0.getKeyCode()==KeyEvent.VK_SPACE){
			HardDrop();
		}
		
		else if (arg0.getKeyCode()==KeyEvent.VK_C || arg0.getKeyCode() == KeyEvent.VK_SHIFT){
			Store();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		
	}
	
	private class GamePanel extends JPanel 
	{
		private GamePanel ()
		{
			this.setSize(300, 600);
		}
		public void paint(Graphics g){
	    	for (int i = 0;i<grid.length;i++){
				for (int j = 0;j<grid[0].length;j++)
				{
//					System.out.print(grid[i][j]);
					if(grid[i][j]==1)
						g.drawImage((Image)magenta_tile,j*30,i*30,null);
					else if (grid[i][j]==2)
						g.drawImage((Image)purple_tile,j*30,i*30,null);
					else if (grid[i][j]==3)
						g.drawImage((Image)blue_tile,j*30,i*30,null);
					else if (grid[i][j]==4)
						g.drawImage((Image)green_tile,j*30,i*30,null);
					else if (grid[i][j]==5)
						g.drawImage((Image)yellow_tile,j*30,i*30,null);
					else if (grid[i][j]==6)
						g.drawImage((Image)orange_tile,j*30,i*30,null);
					else if (grid[i][j]==7)
						g.drawImage((Image)red_tile,j*30,i*30,null);
					else if (grid[i][j] == 8)
						g.drawImage((Image)black_tile, j*30, i*30, null);
					else if (grid[i][j] == 9)
						g.drawImage((Image)turquoise_tile, j*30, i*30, null);
					else
					g.drawImage((Image)tile, j*30, i*30,null);
				}
			}	    	
	    }
	}
	
	private class MyLabel extends JLabel
	{
		private MyLabel ()
		{
			Font f = new Font("Calibri", Font.PLAIN, 20);
			this.setFont(f);
			
			this.setHorizontalAlignment(SwingConstants.LEFT);
		}
		
		private MyLabel (String s)
		{
			this();
			this.setText(s);
		}
	}
	
	private class NextPanel extends JPanel
	{
		private int [][] myGrid;
		private boolean isNext;
		private NextPanel (boolean isNext)
		{
			myGrid = new int [5][5];
		    for (int i = 0;i < myGrid.length; i++){
		    	for (int j = 0;j < myGrid[0].length; j++)
		    		myGrid[i][j] = 0;
		    }
			this.setSize(75, 75);
			this.isNext = isNext;
		}
		
		public void update() {
			
			if (isNext)
				myGrid = next.drawInto(myGrid);
			else
				myGrid = hold.drawInto(myGrid);			
		}

		private void clear ()
		{
			for (int i = 0; i < myGrid.length; i++)
	   		{
	   			for (int j = 0; j < myGrid[0].length; j++)
	   			{
	   				myGrid[i][j] = 0;
	   			}
	   		}
		}
		
		public void paint(Graphics g){
			int size = 15;
	    	for (int i = 0;i<myGrid.length;i++){
				for (int j = 0;j<myGrid[0].length;j++)
				{
//					System.out.print(grid[i][j]);
					if(myGrid[i][j]==1)
						g.drawImage((Image)magenta_tile_mini,j*size,i*size,null);
					else if (myGrid[i][j]==2)
						g.drawImage((Image)purple_tile_mini,j*size,i*size,null);
					else if (myGrid[i][j]==3)
						g.drawImage((Image)blue_tile_mini,j*size,i*size,null);
					else if (myGrid[i][j]==4)
						g.drawImage((Image)green_tile_mini,j*size,i*size,null);
					else if (myGrid[i][j]==5)
						g.drawImage((Image)yellow_tile_mini,j*size,i*size,null);
					else if (myGrid[i][j]==6)
						g.drawImage((Image)orange_tile_mini,j*size,i*size,null);
					else if (myGrid[i][j]==7)
						g.drawImage((Image)red_tile_mini,j*size,i*size,null);
					else if (myGrid[i][j] == 8)
						g.drawImage((Image)black_tile_mini, j*size, i*size, null);
					else if (myGrid[i][j] == 9)
						g.drawImage((Image)turquoise_tile_mini, j*size, i*size, null);
					else
					g.drawImage((Image)tile_mini, j*size, i*size,null);
				}
			}	    	
	    }
	}

}
