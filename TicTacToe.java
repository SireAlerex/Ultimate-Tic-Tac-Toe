import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToe {

	private static void play(Board game) throws IOException {
		System.out.println("Start of game");
		char player = 'x';
		int x = 0, y = 0;
		int ref = game.referee(x, y);
		int upperBound = game.getSize()*3;

		while (ref == 0) {
			game.showBoard();

			boolean correctInput = false;
			while (!correctInput) {
				System.out.println("Input as 'x y' (player " + player + ") : ");
				BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
				String input = reader.readLine();
				if (input.length() == 3) {
					x = input.charAt(0) - '0';
					y = input.charAt(2) - '0';
					if (x >= 0 && y >= 0 && x < upperBound && y < upperBound && game.canPlay(x, y)) correctInput = true;
				}
			}

			//error if x, y < 0 | x,y > size*size-1
			game.play(x, y, player);
			player = (player == 'x')? 'o' : 'x';
			ref = game.referee(x, y);
		}
		game.showBoard();
		char winner = 'e';
		if (ref == -2) System.out.println("Draw");
		else {
			if (ref == 1) winner = 'X';
			else if (ref == -1) winner = 'O';
			System.out.println("Victory of "+ winner + " player !");
		}
	}
	public static void main(String[] args) {
		Board game = new Board(3);
		try {
			play(game);
		}
		catch (IOException e) {
			System.out.println("error IO");
		}
	}
}
