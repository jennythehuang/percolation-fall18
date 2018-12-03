import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{
	
	public PercolationBFS(int n) {
		super(n);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	//updated dfs with queue
	protected void dfs(int row, int col) {
		
		Queue<Integer> que= new LinkedList<>();
		myGrid[row][col] = FULL;
		
		int size = myGrid.length;
		que.add(row*size+col);


		while(que.size()>0) {
			int temp= que.remove();
			int trow= temp/size;
			int tcol= temp%size;
	
			if(helper(trow, tcol+1)) {
				myGrid[trow][tcol+1]=FULL;
				que.add(trow*size+(tcol+1));
			}
			if(helper(trow, tcol-1)) {
				myGrid[trow][tcol-1]=FULL;
				que.add(trow*size+(tcol-1));
			}
	
			if(helper(trow+1, tcol)) {
				myGrid[trow+1][tcol]=FULL;
				que.add((trow+1)*size+tcol);
			}
			if(helper(trow-1, tcol)) {
				myGrid[trow-1][tcol] = FULL;
				que.add((trow-1)*size+tcol);
			}



		}
		
	}
	
	protected boolean helper (int r,int c) {
		return (inBounds(r,c) && isOpen(r,c) && !isFull(r,c));
	
	}

}
