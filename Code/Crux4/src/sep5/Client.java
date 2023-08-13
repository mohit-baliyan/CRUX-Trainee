package sep5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Client {

	public static void main(String[] args) {
		// int steps = toh("A", "B", "C", 3);
		// System.out.println(steps);
		 int[] arr = {1, 3, 5, 7, 0};
		 ArrayList<String> result = getequalsplit2(arr, 0, "", "", 0, 0, new HashMap<>());
		 System.out.println(result);
		// printpermutations("bcad", "", "bcad");
		// printlexico(1, 5143);

//		HashMap<Integer, Integer> ladder = new HashMap<>();
//		boolean[] primes = soe(15);
//		int left = 0, right = primes.length - 1;
//
//		while (left < right) {
//			while (primes[left] == false) {
//				left++;
//			}
//
//			while (primes[right] == false) {
//				right--;
//			}
//
//			if (left < right) {
//				ladder.put(left, right);
//				left++;
//				right--;
//			}
//		}
//
//		printladderboardpath(0, 15, ladder, "");
		
		nknights(new boolean[2][2], 0, 0, 0, "");

	}

	public static int toh(String src, String dest, String help, int n) {
		if (n == 0) {
			return 0;
		}

		int cno = toh(src, help, dest, n - 1); // capable of printing steps
												// required for the said call

		System.out.println("Move " + n + "th disc from " + src + " to " + dest);

		int cyes = toh(help, dest, src, n - 1);

		return cno + cyes + 1;
	}

	public static void printequalsplit(int[] arr, int vidx, String g1, String g2, int sumg1, int sumg2,
			HashMap<String, String> dupli) {
		if (vidx == arr.length) {
			if (sumg1 == sumg2 && dupli.containsKey(g1) == false && dupli.containsKey(g2) == false) {
				dupli.put(g1, g2);
				System.out.println(g1 + " and " + g2);
			}
			return;
		}

		printequalsplit(arr, vidx + 1, g1 + " " + arr[vidx], g2, sumg1 + arr[vidx], sumg2, dupli);
		printequalsplit(arr, vidx + 1, g1, g2 + " " + arr[vidx], sumg1, sumg2 + arr[vidx], dupli);
	}

	public static void printequalsplit2(int[] arr, int vidx, String g1, String g2, int sumg1, int sumg2,
			HashMap<String, String> dupli, ArrayList<String> results) {
		if (vidx == arr.length) {
			if (sumg1 == sumg2 && dupli.containsKey(g1) == false && dupli.containsKey(g2) == false) {
				dupli.put(g1, g2);
//				System.out.println(g1 + " and " + g2);
				results.add(g1 + " and " + g2);
			}
			return;
		}

		printequalsplit2(arr, vidx + 1, g1 + " " + arr[vidx], g2, sumg1 + arr[vidx], sumg2, dupli, results);
		printequalsplit2(arr, vidx + 1, g1, g2 + " " + arr[vidx], sumg1, sumg2 + arr[vidx], dupli, results);
	}
	
	public static ArrayList<String> getequalsplit2(int[] arr, int vidx, String g1, String g2, int sumg1, int sumg2,
			HashMap<String, String> dupli) {
		if (vidx == arr.length) {
			ArrayList<String> br = new ArrayList<>();
			if (sumg1 == sumg2 && dupli.containsKey(g1) == false && dupli.containsKey(g2) == false) {
				dupli.put(g1, g2);
				br.add(g1 + " and " + g2);
//				System.out.println(g1 + " and " + g2);
			}
			return br;
		}

		ArrayList<String> rrg1 = getequalsplit2(arr, vidx + 1, g1 + " " + arr[vidx], g2, sumg1 + arr[vidx], sumg2, dupli);
		ArrayList<String> rrg2 = getequalsplit2(arr, vidx + 1, g1, g2 + " " + arr[vidx], sumg1, sumg2 + arr[vidx], dupli);
		ArrayList<String> mr = new ArrayList<>();
		
		mr.addAll(rrg1);
		mr.addAll(rrg2);
		
		return mr;
	}

	
	public static void printpermutations(String ques, String ans, String oq) {
		if (ques.length() == 0) {
			if (ans.compareTo(oq) > 0) {
				System.out.println(ans);
			}
			return;
		}

		for (int i = 0; i < ques.length(); i++) {
			char ch = ques.charAt(i);
			String ros = ques.substring(0, i) + ques.substring(i + 1);
			printpermutations(ros, ans + ch, oq);
		}
	}

	public static void printlexico(int num, int maxnum) {
		if (num > maxnum) {
			return;
		}

		System.out.println(num);
		for (int i = 0; i <= 9; i++) {
			printlexico(num * 10 + i, maxnum);
		}
		if (num < 9) {
			printlexico(num + 1, maxnum);
		}
	}

	public static void printladderboardpath(int curr, int end, HashMap<Integer, Integer> map, String psf) {
		if (curr == end) {
			System.out.println(psf);
			return;
		}

		if (curr > end) {
			return;
		}

		if (map.containsKey(curr)) {
			printladderboardpath(map.get(curr), end, map, psf + "[" + curr + " =>" + map.get(curr) + "]");
		} else {
			for (int dice = 1; dice <= 6; dice++) {
				printladderboardpath(curr + dice, end, map, psf + dice);
			}
		}
	}

	public static boolean[] soe(int n) {
		boolean[] result = new boolean[n + 1];

		Arrays.fill(result, true);

		result[0] = result[1] = false;
		for (int i = 2; i * i <= n; i++) {
			if (result[i] == false) {
				continue;
			}

			for (int j = 2; i * j <= n; j++) {
				result[i * j] = false;
			}
		}

		return result;
	}

	public static void nknights(boolean[][] board, int row, int col, int kpsf, String psf) {
		if (kpsf == board.length) {
			System.out.println(psf);
			return;
		}

		if (row == board.length) {
			return;
		}

		int nr = 0, nc = 0;
		if (col == board.length - 1) {
			nr = row + 1;
			nc = 0;
		} else {
			nr = row;
			nc = col + 1;
		}

		// no
		nknights(board, nr, nc, kpsf, psf);

		// yes
		if (isitsafe(board, row, col)) {
			board[row][col] = true;
			nknights(board, nr, nc, kpsf + 1, psf + "[" + row + "-" + col + "] ");
			board[row][col] = false;
		}
	}

	private static boolean isitsafe(boolean[][] board, int row, int col) {
		int rm1 = row - 1, rm2 = row - 2;
		int cm1 = col - 1, cm2 = col - 2, cp1 = col + 1, cp2 = col + 2;
		
		if(rm1 >= 0 && cm2 >= 0 && board[rm1][cm2] == true){
			return false;
		}
		
		if(rm2 >= 0 && cm1 >= 0 && board[rm2][cm1] == true){
			return false;
		}
		
		if(rm1 >= 0 && cp2 < board.length && board[rm1][cp2] == true){
			return false;
		}
		
		if(rm2 >= 0 && cp1 < board.length && board[rm2][cp1] == true){
			return false;
		}
		
		return true;
	}
}
