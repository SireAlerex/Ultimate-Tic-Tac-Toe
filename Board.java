import static java.lang.Character.toUpperCase;

public class Board {
	private char[][] board;
	private Grid[][] gloup;
	private int size;

	public Board() {
		board = new char[9][9];
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) board[i][j] = '0';
		}
		size = 1;
	}

	public Board(int size) {
		gloup = new Grid[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) gloup[i][j] = new Grid();
		}
		this.size = size;
	}

	public int referee(int row, int col) {
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		Grid currentGrid = gloup[boardX][boardY];
		char player = currentGrid.getPlayer(gridX, gridY);
		if (currentGrid.getPlayer(gridX, gridY) == '.') return 0;

		if (size == 1) {
			if (currentGrid.wonGrid(gridX, gridY)) {
				if (player == 'x') return 1;
				else if (player == 'o') return -1;
			}
			else return currentGrid.isFull() ? -2 : 0;
		}
		else if (size == 3) {
			if (currentGrid.wonGrid(gridX, gridY) && wonBoard(row, col)) {
				if (player == 'x') return 1;
				else if (player == 'o') return -1;
			}
			else return isFull()? -2 : 0;
		}

		return 5;
	}


	private boolean wonBoard(int row, int col) { return (wonRow(row, col) || wonColumn(row, col) || wonDiagonal(row, col)); }

	private boolean isOnDiagonal(int row, int col) {
		int boardX = (row/3), boardY = col/3;
		return ((boardX == boardY) || (boardX + boardY == 2));
	}

	private boolean wonDiagonal(int row, int col) {
		if (!isOnDiagonal(row, col)) return false;
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		char player = toUpperCase(gloup[boardX][boardY].getPlayer(gridX, gridY));

		if (boardX == boardY) {
			int sum = 0;
			for (int i = 0; i < 3; i++) if (gloup[i][i].getWinner() == player) sum++;
			if (sum == 3) return true;
		}
		else if (boardX + boardY == 2) {
			int sum = 0;
			for (int i = 0; i < 3; i++) if (gloup[i][2-i].getWinner() == player) sum++;
			if (sum == 3) return true;
		}

		return false;
	}

	private boolean wonColumn(int row, int col) {
		int sum = 0;
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		char player = toUpperCase(gloup[boardX][boardY].getPlayer(gridX, gridY));
		for (int i = 0; i < 3; i++) if (gloup[i][boardY].getWinner() == player) sum++;

		return (sum == 3);
	}

	private boolean wonRow(int row, int col) {
		int sum = 0;
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		char player = toUpperCase(gloup[boardX][boardY].getPlayer(gridX, gridY));
		for (int i = 0; i < 3; i++) if (gloup[boardX][i].getWinner() == player) sum++;

		return (sum == 3);
	}

	public void play(int row, int col, char player) {
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		if (canPlay(row, col)) gloup[boardX][boardY].play(gridX, gridY, player);
	}

	public boolean canPlay(int row, int col) {
		int boardX = (row/3), boardY = col/3;
		int gridX = row-3*boardX, gridY = col-3*boardY;
		return gloup[boardX][boardY].canPlay(gridX, gridY) && !gloup[boardX][boardY].isLocked();
	}

	public void showBoard() {
		String line = "-------------------------";
		for (int row = 0; row < size; row++) {
			System.out.println(line);
			for (int i = 0; i < 3; i++) {
				String rowStr = "| ";
				for (int col = 0; col < size; col++) {
					rowStr += gloup[row][col].getRow(i) + "| ";
				}
				System.out.println(rowStr);
			}
		}
		System.out.println(line);
	}

	private boolean isFull() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) if (!gloup[i][j].isFull()) return false;
		}
		return true;
	}

	public int getSize() { return size; }
}