public class PercolationUF implements IPercolate{

	protected boolean[][] myGrid;
	protected int myOpenCount;
	protected IUnionFind myFinder;
	
	private final int VTOP;
	private final int VBOTTOM;
	
	//constructor setting array, finder, and opencount, also sets final ints
	PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		VTOP = size*size;
		VBOTTOM = VTOP+1;
		myFinder = finder;
		myFinder.initialize(VBOTTOM+1);
		myOpenCount = 0;
	}
	
	@Override
	//marks cell as open and connects with neighbors if it isnt already open
	public void open(int row, int col) {
		int size = myGrid.length;
		if (!valid(row,col)) 
		throw new IndexOutOfBoundsException(String.format("(%d,%d) not in bounds", row,col));
		
		if (isOpen(row,col))
		return;
		
		myGrid[row][col] = true;
		myOpenCount += 1;
		
		int a=row*size + col;
		 
		if (isOpen(row,col)) {
			if (row == 0) 
				myFinder.union(a, VTOP);
			if (row==size-1) 
				myFinder.union(a, VBOTTOM);
			
			if (validAndOpen(row+1,col)) 
				myFinder.union(a, (row+1)*size+col);
			if (validAndOpen(row-1,col)) 
				myFinder.union(a, (row-1)*size+col);
			
			if (validAndOpen(row,col+1)) 
				myFinder.union(a, a+1);}
		if (validAndOpen(row,col-1)) 
			myFinder.union(a, a-1);
		
	}
	
	
	@Override
	//returns appropriate myGrid value if in bound
	public boolean isOpen(int row, int col) {
		if (!valid(row,col)) 
		throw new IndexOutOfBoundsException(String.format("(%d,%d) out of bounds", row,col));
		boolean ans=myGrid[row][col];
		return ans;
	}
	
	
	@Override
	//checks if cell is connected to VTOP
	public boolean isFull(int row, int col) {
		if (!valid(row,col)) 
		throw new IndexOutOfBoundsException(String.format("(%d,%d) out of bounds", row,col));
		int a=row*myGrid.length + col;
		return myFinder.connected(a, VTOP);
	}
	
	@Override
	//sees is VTOP and VBOTTOM are connected
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	
	@Override
	//returns open site number
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	//helper method checking if value is in bounds
	public boolean valid(int row, int col) {
		if (col >= myGrid[0].length||col < 0) return false;
		if (row >= myGrid.length||row < 0) return false;
		return true;
	}
	
	//helper checking is in bounds and open
	public boolean validAndOpen(int row, int col) {
		return (valid(row,col)&&(isOpen(row,col)));
	}
}
