package aug17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

public class TestClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scn = new Scanner(System.in);
        
        int t = scn.nextInt();
        int[] in = new int[t];
        
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < t; i++){
            in[i] = scn.nextInt();
            if(in[i] > max){
                max = in[i];
            }
        }
        
        boolean[] primes = new boolean[max + 1];
        Arrays.fill(primes, true);
        
        primes[0] = primes[1] = false;
        for(int i = 2; i * i <= max; i++){
            if(primes[i] == false){
                continue;
            }
            
            for(int j = i; i * j <= max; j++){
                primes[i * j] = false;
            }
        }
        
        ArrayList<Integer> list = new ArrayList();
        HashMap<Integer, Integer> map = new HashMap<>();
        int counter = 0;
        for(int i = 0; i < primes.length; i++){
            if(primes[i]){
                list.add(i);
                map.put(i, counter);
                counter++;
            }
            
        }
        
        for(int i = 0; i < in.length; i++){
            int count = 0;
            
            int j = 0;
            while(list.get(j) * list.get(j) < in[i]){
            	int num = in[i] / list.get(j);
            	
            	while(map.containsKey(num) == false){
            		num--;
            	}
            	
            	count += map.get(num) - j + 1;
                j++;
            }
            
            System.out.println(count);
        }
	}

}
