package aug17;

public class MaxSegmentOperator implements ISegmentOperator {

	@Override
	public int operation(int lsv, int rsv) {
		// TODO Auto-generated method stub
		return Math.max(lsv, rsv);
	}

	@Override
	public int defaultValue() {
		// TODO Auto-generated method stub
		return Integer.MIN_VALUE;
	}

}
