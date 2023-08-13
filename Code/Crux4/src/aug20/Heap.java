package aug20;

import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {
	private ArrayList<T> data = new ArrayList<>();
	
	public int size(){
		return data.size();
	}
	
	public boolean isEmpty(){
		return data.isEmpty();
	}
	
	public void display(){
		System.out.println(data);
	}
	
	public T getHP(){
		return data.get(0);
	}
	
	public void addHP(T value){
		data.add(value);
		this.upheaipfy(data.size() - 1);
	}
	
	private void upheaipfy(int ci) {
		int pi = (ci - 1) / 2;
		
		if(data.get(pi).compareTo(data.get(ci)) > 0){ // parent ki value jyada to priority kamm
			swap(pi, ci);
			upheaipfy(pi);
		}
	}
	
	public T removeHP(){
		swap(0, data.size() - 1);
		T rv = data.remove(data.size() - 1);
		downheapify(0);
		return rv;
	}

	private void downheapify(int pi) {
		int lci = 2 * pi + 1;
		int rci = lci + 1;
		
		int mini = pi;
		
		if(lci < data.size() && data.get(lci).compareTo(data.get(mini)) < 0){
			mini = lci;
		}
		
		if(rci < data.size() && data.get(rci).compareTo(data.get(mini)) < 0){
			mini = rci;
		}
		
		if(mini != pi){
			swap(mini, pi);
			downheapify(mini);
		}
	}

	private void swap(int i, int j) {
		T ith = data.get(i);
		T jth = data.get(j);
		data.set(i, jth);
		data.set(j, ith);
	}
}
