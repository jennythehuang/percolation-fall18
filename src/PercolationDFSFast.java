
public class PercolationDFSFast extends PercolationDFS{

	//auto constructor
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	//more efficient update method that doesn't clear all cells first
	//marks cells as full
	@Override
	protected void updateOnOpen(int row, int col) {
		if (row == 0) 
			dfs(row,col);

		if (inBounds(row-1,col)) {
			if(test(row-1, col)) 
				dfs(row,col); 
			}

		if (inBounds(row,col-1)) {
			if(test(row, col-1)) 
				dfs(row,col); 
			}

		if (inBounds(row,col+1)) {
			if(test(row, col+1)) 
				dfs(row,col); 
			}


		if (inBounds(row+1,col)) {
			if(test(row+1,col)) 
				dfs(row,col); 
			}

	
	}
	
	protected boolean test (int row, int col) {
		return (myGrid[row][col] == FULL && myGrid[row][col] == OPEN);
	}

}
