import static java.lang.Character.toUpperCase;

public class Board {
	private final Grid[][] board;
	private final int size;

	public Board() {
		board = new Grid[1][1];
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 1; j++) board[i][j] = new Grid();
		}
		this.size = 1;
	}

	public Board(int size) {
		board = new Grid[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) board[i][j] = new Grid();
		}
		this.size = size;
	}

	public int referee(int row, int col) {
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		Grid currentGrid = board[boardX][boardY];
		char player = currentGrid.getPlayer(gridX, gridY);
		if (currentGrid.getPlayer(gridX, gridY) == '.') return 0;

		if (size == 1) {
			if (currentGrid.wonGrid(gridX, gridY)) {
				if (player == 'x') return 1;
				else return -1;
			}
			else return currentGrid.isFull() ? -2 : 0;
		}
		else  {
			if (currentGrid.wonGrid(gridX, gridY) && wonBoard(row, col)) {
				if (player == 'x') return 1;
				else return -1;
			}
			else return isFull()? -2 : 0;
		}
	}


	private boolean wonBoard(int row, int col) { return (wonRow(row, col) || wonColumn(row, col) || wonDiagonal(row, col)); }

	private boolean isOnDiagonal(int row, int col) {
		int boardX = (row/3), boardY = col/3;
		return ((boardX == boardY) || (boardX + boardY == 2));
	}

	private int iterateGrid(int row, int col, String iteration) {
		int sum = 0;
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		char player = toUpperCase(board[boardX][boardY].getPlayer(gridX, gridY));

		switch (iteration) {
			case "col" -> {
				for (int i = 0; i < 3; i++) if (board[i][boardY].getWinner() == player) sum++;
			}
			case "row" -> {
				for (int i = 0; i < 3; i++) if (board[boardX][i].getWinner() == player) sum++;
			}
			case "diag1" -> {
				for (int i = 0; i < 3; i++) if (board[i][i].getWinner() == player) sum++;
			}
			case "diag2" -> {
				for (int i = 0; i < 3; i++) if (board[i][2 - i].getWinner() == player) sum++;
			}
		}
		return sum;
	}

	private boolean wonDiagonal(int row, int col) {
		if (!isOnDiagonal(row, col)) return false;
		int boardX = (row/3), boardY = col/3;

		return (boardX == boardY && iterateGrid(row, col, "diag1") == 3) ||
				(boardX + boardY == 2 && iterateGrid(row, col, "diag2") == 3);
	}

	private boolean wonColumn(int row, int col) { return (iterateGrid(row, col, "col") == 3); }

	private boolean wonRow(int row, int col) { return (iterateGrid(row, col, "row") == 3) ;
	}

	public void play(int row, int col, char player) {
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		if (canPlay(row, col)) board[boardX][boardY].play(gridX, gridY, player);
	}

	public boolean canPlay(int row, int col) {
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		return board[boardX][boardY].canPlay(gridX, gridY) && board[boardX][boardY].isUnlocked();
	}

	public void showBoard() {
		String line = "-------------------------";
		for (int row = 0; row < size; row++) {
			System.out.println(line);
			for (int i = 0; i < 3; i++) {
				StringBuilder rowStr = new StringBuilder("| ");
				for (int col = 0; col < size; col++) {
					rowStr.append(board[row][col].getRow(i)).append("| ");
				}
				System.out.println(rowStr);
			}
		}
		System.out.println(line);
	}

	private boolean isFull() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) if (!board[i][j].isFull()) return false;
		}
		return true;
	}

	public int getSize() { return size; }
}
