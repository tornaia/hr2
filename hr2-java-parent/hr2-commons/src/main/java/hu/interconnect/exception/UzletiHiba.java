package hu.interconnect.exception;

public class UzletiHiba extends UzletiFigyelmeztetes {

	private static final long serialVersionUID = 1L;
	
	public UzletiHiba(String errorCode) {
		super(errorCode, null);
	}

	public UzletiHiba(String errorCode, Exception e) {
		super(errorCode, e);
	}
	
}
