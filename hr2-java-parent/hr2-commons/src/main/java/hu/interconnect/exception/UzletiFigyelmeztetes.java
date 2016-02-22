package hu.interconnect.exception;

public class UzletiFigyelmeztetes extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UzletiFigyelmeztetes(String errorCode) {
		super(errorCode);
	}
	
	public UzletiFigyelmeztetes(String errorCode, Exception e) {
		super(errorCode, e);
	}
	
}
