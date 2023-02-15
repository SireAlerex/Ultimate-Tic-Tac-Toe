public class Grid {
	private char[][] grid;
	private boolean locked;
	private char winner = 'E';

	public Grid() {
		grid = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) grid[i][j] = '.';
		}
		this.locked = false;
	}

	public boolean wonGrid(int row, int col) {
		if (wonRow(row, col) || wonColumn(row, col) || wonDiagonal(row, col)) {
			setWinner(grid[row][col]);
			locked = true;
			return true;
		}
		else return false;
	}

	private boolean wonRow(int row, int col) {
		int start = (col/3)*3, sum = 0;
		for (int i = 0; i < 3; i++) if (grid[row][start+i] == grid[row][col]) sum++;
		return (sum == 3);
	}

	private boolean wonColumn(int row, int col) {
		int start = (row/3)*3, sum = 0;
		for (int i = 0; i < 3; i++) if (grid[start+i][col] == grid[row][col]) sum++;
		return (sum == 3);
	}

	private boolean wonDiagonal(int row, int col) {
		if (!isOnDiagonal(row, col)) return false;
		if (row == col) {
			int sum = 0;
			for (int i = 0; i < 3; i++) if (grid[i][i] == grid[row][col]) sum++;
			if (sum == 3) return true;
		}
		else if (row + col == 2) {
			int sum = 0;
			for (int i = 0; i < 3; i++) if (grid[i][2-i] == grid[row][col]) sum++;
			if (sum == 3) return true;
		}

		return false;
	}

	private boolean isOnDiagonal(int row, int col) {
		return ((row == col) || (row + col == 2));
	}

	public boolean canPlay(int row, int col) { return (grid[row][col] == '.'); }

	public void play(int row, int col, char player) { grid[row][col] = player; }

	public String getRow(int row) {
		String rowStr = "";
		for (int i = 0; i < 3; i++) rowStr += grid[row][i] + " ";
		return rowStr;
	}

	public boolean isLocked() { return locked; }

	private void setWinner(char player) {
		if (!isLocked()) {
			if (player == 'x') this.winner = 'X';
			else if (player == 'o') this.winner = 'O';
			else this.winner = 'E';
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) grid[i][j] = this.winner;
			}
		}
	}
	public char getWinner() { return this.winner; }

	public char getPlayer(int row, int col) { return grid[row][col]; }

	public boolean isFull() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) if (grid[i][j] == '.') return false;
		}
		return true;
	}
}
