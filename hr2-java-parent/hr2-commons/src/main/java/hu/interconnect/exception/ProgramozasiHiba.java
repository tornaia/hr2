package hu.interconnect.exception;

public class ProgramozasiHiba extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ProgramozasiHiba(String errorCode) {
		super(errorCode);
	}
	
	public ProgramozasiHiba(String errorCode, Throwable e) {
		super(errorCode, e);
	}

}
