package sep1;

public class MaxHeap extends Heap {

	@Override
	public boolean hasHigherPriority(int i, int j) {
		int ith = data.get(i);
		int jth = data.get(j);
		
		if(ith > jth){
			return true;
		} else {
			return false;
		}
	}

}
