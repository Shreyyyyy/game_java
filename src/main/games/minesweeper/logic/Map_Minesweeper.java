package games.minesweeper.logic;

import general.logic.GraphicCell;
import general.logic.Map;
import general.utilities.NRandom;

public class Map_Minesweeper extends Map{
	protected Cell_Minesweeper[][]matrix;
	protected Game_Minesweeper game;
	protected GraphicCell flag;
	protected GraphicCell bomb;
	protected int size;
	protected int bombs;
	
	public Map_Minesweeper(Game_Minesweeper game) {
		super(game, false);
		this.game = game;
		this.matrix = new Cell_Minesweeper[COLUMN][ROW];
		this.flag = new GraphicCell(this.game.getImageFactory().getTriangle(), freeCell.getBackground());
		this.bomb = new GraphicCell(this.game.getImageFactory().getCircle(), freeCell.getBackground());
		
		this.bombs = 15;
		this.size = (matrix.length - 2) * matrix[0].length - bombs;
		
		for(int r = 0; r < matrix.length; r++) 
			for(int c = 0; c < matrix[0].length; c++) {
				matrix[r][c] = new Cell_Minesweeper(r, c, this);
				matrix[r][c].clear();
			}
		
		for(int r = 0; r < 2; r++) 
			for(int c = 0; c < matrix[0].length; c++) {
				matrix[r][c].setUneditable();
				matrix[r][c].put(freeCell);
			}
		charge(bombs);
		createInfo();
	}
	
	private void charge(int count) {
		int r = 0;
		int c = 0;
		int p = 0;
		
		while(p < count) {
			r = Math.abs(NRandom.getInstance().nextInt() % (matrix.length - 2)) + 2;
			c = Math.abs(NRandom.getInstance().nextInt() % matrix[0].length);
			if(!matrix[r][c].isBomb()) {
				matrix[r][c].setBomb();
				setNumber(matrix[r][c]);
				p++;
			}
		}
	}
	
	private void setNumber(Cell_Minesweeper cell) {
		int r = 0;
		int c = 0;
		
		r = cell.getRow() - 1;
		c = cell.getColumn();
		if(r >= 2 && !matrix[r][c].isBomb()) {
			matrix[r][c].setNumber(matrix[r][c].getNumber()+1);
		}
			
		r = cell.getRow() + 1;
		c = cell.getColumn();
		if(r <= 15 && !matrix[r][c].isBomb()){
			matrix[r][c].setNumber(matrix[r][c].getNumber()+1);
		}
		
		r = cell.getRow();
		c = cell.getColumn() - 1;
		if(c >= 0 && !matrix[r][c].isBomb()){
			matrix[r][c].setNumber(matrix[r][c].getNumber()+1);
		}
		
		r = cell.getRow();
		c = cell.getColumn() + 1;
		if(c <= 8 && !matrix[r][c].isBomb()){
			matrix[r][c].setNumber(matrix[r][c].getNumber()+1);
		}
		
		r = cell.getRow() - 1;
		c = cell.getColumn() - 1;
		if(r >= 2 && c >= 0 && !matrix[r][c].isBomb()){
			matrix[r][c].setNumber(matrix[r][c].getNumber()+1);
		}
		
		r = cell.getRow() + 1;
		c = cell.getColumn() + 1;
		if(r <= 15 && c <= 8 && !matrix[r][c].isBomb()){
			matrix[r][c].setNumber(matrix[r][c].getNumber()+1);
		}
		
		r = cell.getRow() - 1;
		c = cell.getColumn() + 1;
		if(c <= 8 && r >= 2 && c >= 0 && !matrix[r][c].isBomb()){
			matrix[r][c].setNumber(matrix[r][c].getNumber()+1);
		}
		
		r = cell.getRow() + 1;
		c = cell.getColumn() - 1;
		if(c >= 0 && r <= 15 && c <= 8 && !matrix[r][c].isBomb()){
			matrix[r][c].setNumber(matrix[r][c].getNumber()+1);
		}
	}
	
	public void changeCell(Cell_Minesweeper minesweeperCell) {
		game.changeCell(minesweeperCell);
	}

	public void click(int row, int column) {
		if(matrix[row][column].isBomb())
			lose();
		else {
			if(matrix[row][column].isEditable()) {
				show(row, column);
			}
		}
		if(size == 0)
			win();
	}
	
	public void show(int row, int column) {
		GraphicCell gc = new GraphicCell(null, this.game.getImageFactory().getColorDefault().brighter());
		mark(row, column, gc);
	}

	private void mark(int row, int column, GraphicCell gc) { //I don't know if there is a more efficient way.
		if((row >= 2 && row <= 15 && column >= 0 && column <= 8) && matrix[row][column].isEditable()) {
			gc.setText("");
			size--;
			if(matrix[row][column].getNumber() != 0) {
				gc.setText(matrix[row][column].getNumber()+"");
				matrix[row][column].setUneditable();
				matrix[row][column].put(gc);
			}
			else {
				matrix[row][column].setUneditable();
				matrix[row][column].put(gc);
				gc.setText("");
				
				mark(row - 1, column - 1, gc);
				mark(row + 1, column + 1, gc);
				mark(row - 1, column + 1, gc);
				mark(row + 1, column - 1, gc);
				mark(row, column + 1, gc);
				mark(row + 1, column, gc);
				mark(row, column - 1, gc);
				mark(row - 1, column, gc);
			}
		}

	}

	public void putFlag(int row, int column) {
		if(matrix[row][column].isEditable()) {
			if(!matrix[row][column].isFlag()) {
				matrix[row][column].put(flag);
				matrix[row][column].setFlag(true);
				bombs--;
			}
			else {
				matrix[row][column].put(freeCell);
				matrix[row][column].setFlag(false);
				bombs++;
			}
			updateBombs();
		}
	}
	
	private void updateBombs() {
		GraphicCell gc = new GraphicCell(null, freeCell.getBackground());
		int n = bombs;
		if(n < 0) {
			gc.setText("-");
			matrix[0][4].put(gc);
			n*=-1;
		}
		else {
			gc.setText("");
			matrix[0][4].put(gc);
		}
		
		gc.setText( (n/10) % 10 +"");
		matrix[0][5].put(gc);
		gc.setText(n % 10 +"");
		matrix[0][6].put(gc);
	}
	
	private void createInfo() {
		matrix[0][2].put(bomb);
		GraphicCell gc = new GraphicCell(null, freeCell.getBackground());
		
		matrix[0][4].put(gc);
		gc.setText("=");
		matrix[0][3].put(gc);
		gc.setText( (bombs/10) % 10 +"");
		matrix[0][5].put(gc);
		gc.setText(bombs % 10 +"");
		matrix[0][6].put(gc);
	}
}
