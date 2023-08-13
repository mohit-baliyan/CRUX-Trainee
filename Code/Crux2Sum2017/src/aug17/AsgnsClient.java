package aug17;

import java.util.Arrays;
import java.util.HashMap;

public class AsgnsClient {

	public static void main(String[] args) {
		// Q 10
		// int n = 4;
		//
		// boolean[] primes = soe(n * n);

		// int counter = 0;
		// HashMap<Integer, Boolean> minesandports = new HashMap<>();
		// for(int i = 0; i < primes.length; i++){
		// if(primes[i] == true){
		// counter++;
		//
		// if(counter % 2 == 0){
		// minesandports.put(i, true); // port
		// } else {
		// minesandports.put(i, false); // mine
		// }
		// }
		// }
		//
		// asgn8bq10(0, 0, n - 1, n - 1, "", minesandports);

		// Q 7
		// int end = 15;
		// boolean[] primes = soe(end);
		// HashMap<Integer, Integer> ladders = new HashMap<>();
		// int left = 0, right = primes.length - 1;
		// while (left < right) {
		// while (primes[left] == false) {
		// left++;
		// }
		//
		// while (primes[right] == false) {
		// right--;
		// }
		//
		// if (left < right) {
		// ladders.put(left, right);
		// left++;
		// right--;
		// }
		// }
		// asgn8bq7(0, end, "", ladders);

		// Q 8
		// int end = 15;
		// boolean[] primes = soe(end);
		// HashMap<Integer, Integer> snl = new HashMap<>();
		// int left = 0, right = primes.length - 1;
		// int counter = 0;
		// while (left < right) {
		// while (primes[left] == false) {
		// left++;
		// }
		//
		// while (primes[right] == false) {
		// right--;
		// }
		//
		// if (left < right) {
		// counter++;
		//
		// if (counter % 2 == 1) {
		// snl.put(left, right);
		// } else {
		// snl.put(right, left);
		// }
		// left++;
		// right--;
		// }
		// }
		// System.out.println(asgn8bq8(0, end, new int[] {1, 4, 4, 2, 4, 2}, 0,
		// snl));

		// knights
		boolean[][] board = new boolean[3][3];
		nknights(1, board, "", 0);
		
		printPermutations("abab", "");
		
	}

	public static boolean[] soe(int n) {
		boolean[] primes = new boolean[n + 1];
		Arrays.fill(primes, true);

		primes[0] = primes[1] = false;
		for (int i = 2; i * i <= n; i++) {
			if (primes[i] == false) {
				continue;
			}

			for (int ja = 2; i * ja <= n; ja++) {
				primes[i * ja] = false;
			}
		}

		return primes;
	}

	public static void asgn8bq10(int cr, int cc, int er, int ec, String psf, HashMap<Integer, Boolean> minesandports) {
		if (cr == er && cc == ec) {
			System.out.println(psf + ".");
			return;
		}

		if (cr > er || cc > ec) {
			return;
		}

		int cellno = (cr * (ec + 1)) + cc + 1;
		if (minesandports.containsKey(cellno)) {
			if (minesandports.get(cellno) == false) {
				return;
			} else {
				asgn8bq10(er, ec, er, ec, psf + "PORTED", minesandports);
			}
		}

		// a knight at any spot
		asgn8bq10(cr + 2, cc + 1, er, ec, psf + "K21 => ", minesandports);
		asgn8bq10(cr + 1, cc + 2, er, ec, psf + "K12 => ", minesandports);

		int moves = 1;

		// a rook on the 4 walls
		if (cr == 0 || cr == er || cc == 0 || cc == ec) {
			// horizontals moves
			moves = 1;
			while (cc + moves <= ec) {
				asgn8bq10(cr, cc + moves, er, ec, psf + "RH" + moves + " => ", minesandports);
				moves++;
			}

			// vertical moves
			moves = 1;
			while (cr + moves <= er) {
				asgn8bq10(cr + moves, cc, er, ec, psf + "RV" + moves + " => ", minesandports);
				moves++;
			}
		}

		// a bishop on the 2 diagonals
		if (cr == cc || cr + cc == ec) {
			moves = 1;
			while (cr + moves <= er && cc + moves <= ec) {
				asgn8bq10(cr + moves, cc + moves, er, ec, psf + "B" + moves + " => ", minesandports);
				moves++;
			}
		}
	}

	public static void asgn8bq7(int curr, int end, String psf, HashMap<Integer, Integer> ladders) {
		if (curr == end) {
			System.out.println(psf);
			return;
		}

		if (curr > end) {
			return;
		}

		if (ladders.containsKey(curr)) {
			asgn8bq7(ladders.get(curr), end, psf + "L[" + curr + " -> " + ladders.get(curr) + "]", ladders);
		} else {
			// throw the dice
			for (int dice = 1; dice <= 6; dice++) {
				asgn8bq7(curr + dice, end, psf + "D" + dice, ladders);
			}
		}
	}

	public static boolean asgn8bq8(int curr, int end, int[] dices, int diceidx, HashMap<Integer, Integer> snl) {
		if (curr == end) {
			return true;
		}

		if (snl.containsKey(curr)) {
			return asgn8bq8(snl.get(curr), end, dices, diceidx, snl);
		} else {
			if (diceidx == dices.length) {
				System.out.println(curr);
				return false;
			}
			return asgn8bq8(curr + dices[diceidx], end, dices, diceidx + 1, snl);
		}
	}

	public static void nknights(int cellno, boolean[][] board, String config, int kpsf) {
		if (kpsf == board.length) {
			System.out.println(config);
			return;
		}

		for (int i = cellno; i <= board.length * board.length; i++) {
			int cr = (i - 1) / board.length;
			int cc = (i - 1) % board.length;

			if (isitsafetoplacetheknight(board, cr, cc) == true) {
				board[cr][cc] = true;

				nknights(i + 1, board, config + "[" + cr + "-" + cc + "], ", kpsf + 1);

				board[cr][cc] = false;
			}
		}
	}

	private static boolean isitsafetoplacetheknight(boolean[][] board, int row, int col) {
		if (row - 2 >= 0 && col - 1 >= 0 && board[row - 2][col - 1] == true) {
			return false;
		}

		if (row - 1 >= 0 && col - 2 >= 0 && board[row - 1][col - 2] == true) {
			return false;
		}

		if (row - 2 >= 0 && col + 1 < board.length && board[row - 2][col + 1] == true) {
			return false;
		}

		if (row - 1 >= 0 && col + 2 < board.length && board[row - 1][col + 2] == true) {
			return false;
		}

		return true;
	}

	public static void printPermutations(String ques, String ans){
		if(ques.length() == 0){
			System.out.println(ans);
			return;
		}
		
		HashMap<Character, Boolean> dupli = new HashMap<>();
		
		for(int i = 0; i < ques.length(); i++){
			Character ch = ques.charAt(i);
			String roq = ques.substring(0, i) + ques.substring(i + 1);
		
			if(!dupli.containsKey(ch)){
				dupli.put(ch, true);
				printPermutations(roq, ans + ch);
			}
		}
	}
}
