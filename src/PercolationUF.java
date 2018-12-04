public class PercolationUF implements IPercolate{

	protected boolean[][] myGrid;
	protected int myOpenCount;
	protected IUnionFind myFinder;
	
	private final int VTOP;
	private final int VBOTTOM;
	
	//constructor
	PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		VTOP = size*size;
		VBOTTOM = VTOP+1;
		myFinder = finder;
		myFinder.initialize(VBOTTOM+1);
		myOpenCount = 0;
	}
	
	@Override
	public void open(int row, int col) {
		int size = myGrid.length;
		if (! inBounds(row,col)) {
		throw new IndexOutOfBoundsException(
		String.format("(%d,%d) not in bounds", row,col));
		}
		
		if (isOpen(row,col)) {
		return;
		}
		
		myGrid[row][col] = true;
		myOpenCount += 1;
		 
		if (inBounds(row+1,col) && isOpen(row+1,col) && isOpen(row,col)) {
		myFinder.union(row*size + col, (row+1) * size + col);
		
		}
		if (inBounds(row-1,col) && isOpen(row-1,col) && isOpen(row,col)) {
		myFinder.union(row*size + col, (row-1) * size + col);
		
		}
		if (inBounds(row,col+1) && isOpen(row,col+1) && isOpen(row,col)) {
		myFinder.union(row*size + col, row*size + (col+1));
		
		}
		if (inBounds(row,col-1) && isOpen(row,col-1) ) {
		myFinder.union(row*size + col, row*size + (col-1));
		}
		if (row == 0 && isOpen(row,col)) {
		myFinder.union(row*size + col, VTOP);
		}
		if (row == size - 1 && isOpen(row,col)) {
		myFinder.union(row*size + col, VBOTTOM);
		}
	}
	
	
	@Override
	public boolean isOpen(int row, int col) {
		if (! inBounds(row,col)) 
		throw new IndexOutOfBoundsException(String.format("(%d,%d) out of bounds", row,col));
		boolean ans=myGrid[row][col];
		return ans;
	}
	
	
	@Override
	public boolean isFull(int row, int col) {
		if (!inBounds(row,col)) 
		throw new IndexOutOfBoundsException(String.format("(%d,%d) out of bounds", row,col));
		int a=row*myGrid.length + col;
		return myFinder.connected(a, VTOP);
	}
	
	@Override
	public boolean percolates() {
		return myFinder.connected(VTOP, VBOTTOM);
	}
	
	
	@Override
	public int numberOfOpenSites() {
		return myOpenCount;
	}
	
	
	public boolean inBounds(int row, int col) {
		if (col >= myGrid[0].length||col < 0) return false;
		if (row >= myGrid.length||row < 0) return false;
		return true;
	}
}
