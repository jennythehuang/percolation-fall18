import java.util.Arrays;

public class PercolationUF {
	
	protected boolean[][] myGrid;
	protected int myOpenCount;
	protected QuickUWPC myFinder;
	
	private final int VTOP;
	private final int VBOTTOM;


	/**
	 * Initialize a grid so that all cells are blocked.
	 * 
	 * @param n
	 *            is the size of the simulated (square) grid
	 */
	public PercolationUF(int size, IUnionFind finder) {
		myGrid = new boolean[size][size];
		myOpenCount = 0;
		for (boolean[] row : myGrid)
			Arrays.fill(row, false);
		finder.initialize(size*size+2);
		VTOP=size;
		VBOTTOM=size+1;
	
	}

}

