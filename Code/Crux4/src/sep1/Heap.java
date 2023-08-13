package sep1;

import java.util.ArrayList;

public abstract class Heap {
	protected ArrayList<Integer> data = new ArrayList<>();
	
	public int size(){
		return data.size();
	}
	
	public boolean isEmpty(){
		return data.isEmpty();
	}
	
	public void display(){
		System.out.println(data);
	}
	
	public int getHP(){
		return data.get(0);
	}
	
	public void addHP(int value){
		data.add(value);
		this.upheaipfy(data.size() - 1);
	}
	
	private void upheaipfy(int ci) {
		int pi = (ci - 1) / 2;
		
		if(this.hasHigherPriority(ci, pi)){ 
			swap(pi, ci);
			upheaipfy(pi);
		}
	}
	
	public int removeHP(){
		swap(0, data.size() - 1);
		int rv = data.remove(data.size() - 1);
		downheapify(0);
		return rv;
	}

	private void downheapify(int pi) {
		int lci = 2 * pi + 1;
		int rci = lci + 1;
		
		int maxi = pi;
		
		if(lci < data.size() && this.hasHigherPriority(lci, maxi)){
			maxi = lci;
		}
		
		if(rci < data.size() && this.hasHigherPriority(lci, maxi)){
			maxi = rci;
		}
		
		if(maxi != pi){
			swap(maxi, pi);
			downheapify(maxi);
		}
	}

	private void swap(int i, int j) {
		int ith = data.get(i);
		int jth = data.get(j);
		data.set(i, jth);
		data.set(j, ith);
	}
	
	public abstract boolean hasHigherPriority(int i, int j);
}
