public class Grid {
	private final char[][] grid;
	private boolean unlocked;
	private char winner = 'E';

	public Grid() {
		grid = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) grid[i][j] = '.';
		}
		this.unlocked = true;
	}

	public boolean wonGrid(int row, int col) {
		if (wonRow(row, col) || wonColumn(row, col) || wonDiagonal(row, col)) {
			setWinner(grid[row][col]);
			unlocked = false;
			return true;
		}
		else return false;
	}

	private boolean wonRow(int row, int col) { return iterateGrid(row, col, "row") == 3; }

	private boolean wonColumn(int row, int col) { return (iterateGrid(row, col, "col") == 3); }

	private boolean wonDiagonal(int row, int col) {
		if (!isOnDiagonal(row, col)) return false;

		return (row == col && iterateGrid(row, col, "diag1") == 3) || row + col == 2 && iterateGrid(row, col, "diag2") == 3;
	}

	private int iterateGrid(int row, int col, String iteration) {
		int sum = 0, start;
		switch (iteration) {
			case "row" -> {
				start = (col / 3) * 3;
				for (int i = 0; i < 3; i++) if (grid[row][start + i] == grid[row][col]) sum++;
			}
			case "col" -> {
				start = (row / 3) * 3;
				for (int i = 0; i < 3; i++) if (grid[start + i][col] == grid[row][col]) sum++;
			}
			case "diag1" -> {
				for (int i = 0; i < 3; i++) if (grid[i][i] == grid[row][col]) sum++;
			}
			case "diag2" -> {
				for (int i = 0; i < 3; i++) if (grid[i][2 - i] == grid[row][col]) sum++;
			}
		}
		return sum;
	}

	private boolean isOnDiagonal(int row, int col) { return ((row == col) || (row + col == 2)); }

	public boolean canPlay(int row, int col) { return (grid[row][col] == '.'); }

	public void play(int row, int col, char player) { grid[row][col] = player; }

	public String getRow(int row) {
		StringBuilder rowStr = new StringBuilder();
		for (int i = 0; i < 3; i++) rowStr.append(grid[row][i]).append(" ");
		return rowStr.toString();
	}

	public boolean isUnlocked() { return unlocked; }

	private void setWinner(char player) {
		if (isUnlocked()) {
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
