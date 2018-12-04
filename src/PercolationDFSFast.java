
public class PercolationDFSFast extends PercolationDFS{

	//auto constructor
	public PercolationDFSFast(int n) {
		super(n);
	}
	
	//more efficient update method that doesn't clear all cells first
	//marks cells as full
	@Override
	protected void updateOnOpen(int row, int col) {
		//top row
		if(row==0) 
			dfs(row, col);
		
		//check adjacent
		if((isFull(row, col+1)) && (col+1 < myGrid[0].length)) 
			dfs(row, col);
		if((isFull(row, col-1)) && (col-1 >= 0)) 
			dfs(row, col);
		
		if((isFull(row+1,col)) && (row+1<myGrid[0].length))
			dfs(row, col);
		if(isFull(row-1, col)) 
			dfs(row, col);
		

	
	}

}
