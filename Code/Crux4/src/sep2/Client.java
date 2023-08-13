package sep2;

import java.util.ArrayList;
import java.util.Arrays;

public class Client {

	static long startTime = 0;

	public static void startWatch() {
		startTime = System.currentTimeMillis();
	}

	public static void endWatch(String algo, int n) {
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		System.out.println(algo + " took " + duration + " ms for size = " + n);
	}

	public static void main(String[] args) {
		int n = 15;
		// startWatch();
		// System.out.println(FibISW(n));
		// System.out.println(cbpslider(0, n));
		// System.out.println(cmpislider(n, n));
		// endWatch("cmpib", n);

		// int[] arr = { 10, 2, 8, 16, 3, 4, 18, 90, -5 };
		// System.out.println(lisib(arr));

		// int[] dims = { 10, 20, 30, 40, 50, 60 };
		// System.out.println(mcm(dims, 0, dims.length - 1, new int[dims.length
		// + 1][dims.length + 1]));
		System.out.println(getCodes("1001"));
		// int[] prices = {10, 15, 5, 17, 6};
		// int[] wts = {20, 5, 17, 10, 18};
		// System.out.println(knapsack(prices, wts, 40, 0, new
		// int[41][prices.length + 1]));

//		System.out.println(lcs("abcf", "acef"));
//		System.out.println(lcsib("abcf", "acef"));
		
//		System.out.println(editDistanceIB("abcf", "acef"));

		// System.out.println(getCodes("101"));
	}

	public static int Fib(int n) {
		if (n == 0 || n == 1) {
			return n;
		}

		int fibnm1 = Fib(n - 1);
		int fibnm2 = Fib(n - 2);
		int fibn = fibnm1 + fibnm2;

		return fibn;
	}

	public static int FibRB(int n, int[] strg) {
		if (n == 0 || n == 1) {
			return n;
		}

		if (strg[n] != 0) {
			return strg[n];
		}

		int fibnm1 = FibRB(n - 1, strg);
		int fibnm2 = FibRB(n - 2, strg);
		int fibn = fibnm1 + fibnm2;

		strg[n] = fibn;
		return fibn;
	}

	public static int FibIB(int n) {
		int[] strg = new int[n + 1];

		strg[0] = 0;
		strg[1] = 1;

		for (int i = 2; i <= n; i++) {
			strg[i] = strg[i - 1] + strg[i - 2];
		}

		return strg[n];
	}

	public static int FibISW(int n) {
		int[] strg = new int[2];

		strg[0] = 0;
		strg[1] = 1;

		for (int i = 0; i < n - 1; i++) {
			int nv = strg[0] + strg[1];
			strg[0] = strg[1];
			strg[1] = nv;
		}

		return strg[1];
	}

	public static int cbp(int curr, int end) {
		if (curr == end) {
			return 1;
		}

		int count = 0;

		for (int dice = 1; dice <= 6; dice++) {
			if (curr + dice <= end) {
				int cdice = cbp(curr + dice, end);
				count += cdice;
			}
		}

		return count;
	}

	public static int cbprb(int curr, int end, int[] strg) {
		if (curr == end) {
			return 1;
		}

		if (strg[curr] != 0) {
			return strg[curr];
		}

		int count = 0;

		for (int dice = 1; dice <= 6; dice++) {
			if (curr + dice <= end) {
				int cdice = cbprb(curr + dice, end, strg);
				count += cdice;
			}
		}

		strg[curr] = count;
		return count;
	}

	public static int cbpib(int curr, int end) {
		int[] strg = new int[end + 6];

		strg[end] = 1;

		for (int i = end - 1; i >= 0; i--) {
			strg[i] = strg[i + 1] + strg[i + 2] + strg[i + 3] + strg[i + 4] + strg[i + 5] + strg[i + 6];
		}

		return strg[0];
	}

	public static int cbpslider(int curr, int end) {
		int[] arr = new int[6];
		arr[0] = 1;

		for (int i = 0; i < end; i++) {
			int next = arr[0] + arr[1] + arr[2] + arr[3] + arr[4] + arr[5];
			arr[5] = arr[4];
			arr[4] = arr[3];
			arr[3] = arr[2];
			arr[2] = arr[1];
			arr[1] = arr[0];
			arr[0] = next;
		}

		return arr[0];
	}

	public static int cmp(int cr, int cc, int er, int ec) {
		if (cr == er && cc == ec) {
			return 1;
		}
		int ch = 0, cv = 0, count = 0;
		if (cr < er) {
			cv = cmp(cr + 1, cc, er, ec);
		}

		if (cc < ec) {
			ch = cmp(cr, cc + 1, er, ec);
		}

		count = ch + cv;
		return count;
	}

	public static int cmprb(int cr, int cc, int er, int ec, int[][] strg) {
		if (cr == er && cc == ec) {
			return 1;
		}

		if (strg[cr][cc] != 0) {
			return strg[cr][cc];
		}

		int ch = 0, cv = 0, count = 0;
		if (cr < er) {
			cv = cmprb(cr + 1, cc, er, ec, strg);
		}

		if (cc < ec) {
			ch = cmprb(cr, cc + 1, er, ec, strg);
		}

		count = ch + cv;
		strg[cr][cc] = count;
		return count;
	}

	public static int cmpib(int er, int ec) {
		int[][] strg = new int[er + 1][ec + 1];

		for (int row = er; row >= 0; row--) {
			for (int col = ec; col >= 0; col--) {
				if (row == er || col == ec) {
					strg[row][col] = 1;
				} else {
					strg[row][col] = strg[row + 1][col] + strg[row][col + 1];
				}
			}
		}

		return strg[0][0];
	}

	public static ArrayList<String> getCodes(String str) {
		if (str.length() == 0) {
			ArrayList<String> baseresult = new ArrayList<>();
			baseresult.add("");
			return baseresult;
		}

		if (str.charAt(0) == '0') {
			ArrayList<String> baseresult = new ArrayList<>();
			return baseresult;
		}

		ArrayList<String> myresult = new ArrayList<>();

		String ch0 = str.substring(0, 1);
		String ros0 = str.substring(1);

		ArrayList<String> ros0rr = getCodes(ros0);
		for (String ros0val : ros0rr) {
			myresult.add(((char) ('a' + Integer.parseInt(ch0) - 1)) + ros0val);
		}

		if (str.length() >= 2) {
			String ch01 = str.substring(0, 2);
			String ros01 = str.substring(2);

			if (Integer.parseInt(ch01) <= 26) {
				ArrayList<String> ros01rr = getCodes(ros01);
				for (String ros01val : ros01rr) {
					myresult.add(((char) ('a' + Integer.parseInt(ch01) - 1)) + ros01val);
				}
			}
		}

		return myresult;
	}

	public static int cmpislider(int er, int ec) {
		int[] arr = new int[ec + 1];

		Arrays.fill(arr, 1);

		for (int slidecount = 0; slidecount < er; slidecount++) {
			for (int col = arr.length - 2; col >= 0; col--) {
				int nv = arr[col] + arr[col + 1];
				arr[col] = nv;
			}
		}

		return arr[0];
	}

	public static int lis(int[] arr, int vidx, String psf, int csf, int lastIncluded) {
		if (vidx == arr.length) {
			System.out.println(psf + ". -> " + csf);
			return csf;
		}

		int cinc = 0;
		int cexc = 0;

		if (arr[vidx] > lastIncluded) {
			cinc = lis(arr, vidx + 1, psf + ", " + arr[vidx], csf + 1, arr[vidx]); // included
		}

		cexc = lis(arr, vidx + 1, psf, csf, lastIncluded); // exclude
		return Math.max(cinc, cexc);
	}

	public static int lisib(int[] arr) {
		int[] strg = new int[arr.length];

		strg[0] = 1;

		for (int i = 1; i < strg.length; i++) {
			int max = 0;

			for (int j = 0; j < i; j++) {
				if (arr[j] < arr[i]) {
					if (strg[j] > max) {
						max = strg[j];
					}
				}
			}

			strg[i] = max + 1;
		}

		int max = strg[0];
		for (int i = 1; i < strg.length; i++) {
			if (strg[i] > max) {
				max = strg[i];
			}
		}
		return max;
	}

	public static int mcm(int[] dims, int si, int ei, int[][] strg) {
		if (ei == si + 1) {
			return 0;
		}

		if (strg[si][ei] != 0) {
			return strg[si][ei];
		}

		int mincost = Integer.MAX_VALUE;
		for (int i = si; i < ei - 1; i++) {
			int cost1 = mcm(dims, si, i + 1, strg);
			int cost2 = mcm(dims, i + 1, ei, strg);
			int rc = dims[si] * dims[ei] * dims[i + 1];

			int fc = cost1 + cost2 + rc;
			if (fc < mincost) {
				mincost = fc;
			}
		}

		strg[si][ei] = mincost;
		return mincost;
	}

	public static int knapsack(int[] prices, int[] wts, int bagCap, int vidx, int[][] strg) {
		if (vidx == prices.length) {
			return 0;
		}

		if (strg[bagCap][vidx] != 0) {
			return strg[bagCap][vidx];
		}

		int valInc = 0, valExc = 0;

		valExc = knapsack(prices, wts, bagCap, vidx + 1, strg);
		if (wts[vidx] <= bagCap) {
			valInc = prices[vidx] + knapsack(prices, wts, bagCap - wts[vidx], vidx + 1, strg);
		}

		int rv = Math.max(valInc, valExc);

		strg[bagCap][vidx] = rv;
		return rv;
	}

	public static int lcs(String s1, String s2) {
		if (s1.length() == 0 || s2.length() == 0) {
			return 0;
		}

		char ch1 = s1.charAt(0);
		String ros1 = s1.substring(1);
		char ch2 = s2.charAt(0);
		String ros2 = s2.substring(1);

		int rv = 0;

		if (ch1 == ch2) {
			rv = 1 + lcs(ros1, ros2);
		} else {
			int choice1 = lcs(ros1, s2);
			int choice2 = lcs(s1, ros2);

			rv = Math.max(choice1, choice2);
		}

		return rv;
	}

	public static int lcsib(String s1, String s2) {
		String[][] strg = new String[s1.length() + 1][s2.length() + 1];

		for (int row = 0; row < strg.length; row++) {
			for (int col = 0; col < strg[row].length; col++) {
				strg[row][col] = "";
			}
		}

		for (int row = s1.length() - 1; row >= 0; row--) {
			for (int col = s2.length() - 1; col >= 0; col--) {
				if (s1.charAt(row) == s2.charAt(col)) {
					strg[row][col] = s1.charAt(row) + strg[row + 1][col + 1];
				} else {
					if (strg[row + 1][col].length() > strg[row][col + 1].length()) {
						strg[row][col] = strg[row + 1][col];
					} else {
						strg[row][col] = strg[row][col + 1];
					}
				}
			}
		}

		System.out.println(strg[0][0]);
		return strg[0][0].length();
	}

	public static int editDistance(String s1, String s2){
		if(s1.length() == 0){
			return s2.length();
		}
		
		if(s2.length() == 0){
			return s1.length();
		}
		
		char ch1 = s1.charAt(0);
		String ros1 = s1.substring(1);
		char ch2 = s2.charAt(0);
		String ros2 = s2.substring(1);
		
		if(ch1 == ch2){
			return editDistance(ros1, ros2);
		} else {
			int repc = 1 + editDistance(ros1, ros2);
			int remc = 1 + editDistance(s1, ros2);
			int inc = 1 + editDistance(ros1, s2);
			
			return Math.min(remc, Math.min(repc, inc));
		}
	}
	
	public static int editDistanceIB(String s1, String s2){
		int[][] strg = new int[s1.length() + 1][s2.length() + 1];
		
		// last row
		for(int col = 0; col <= s2.length(); col++){
			strg[s1.length()][col] = s2.length() - col;
		}
		
		// last col
		for(int row = 0; row <= s1.length(); row++){
			strg[row][s2.length()] = s1.length() - row;
		}
		
		for(int row = s1.length() - 1; row >= 0; row--){
			for(int col = s2.length() - 1; col >= 0; col--){
				if(s1.charAt(row) == s2.charAt(col)){
					strg[row][col] = strg[row + 1][col + 1];
				} else {
					strg[row][col] = 1 + Math.min(strg[row + 1][col + 1], Math.min(strg[row + 1][col], strg[row][col + 1]));
				}
			}
		}
		
		return strg[0][0];
	}

}
