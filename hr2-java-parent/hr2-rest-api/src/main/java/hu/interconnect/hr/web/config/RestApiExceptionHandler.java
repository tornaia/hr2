package hu.interconnect.hr.web.config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;

import hu.interconnect.exception.UzletiFigyelmeztetes;
import hu.interconnect.exception.UzletiHiba;
import hu.interconnect.util.StringUtils;

@ControllerAdvice
public final class RestApiExceptionHandler {

	private static final Log LOG = LogFactory.getLog(RestApiExceptionHandler.class);
	
	private static final Map<Class<? extends Exception>, HttpStatusAndErrorcode> NORMAL_EXCEPTION_TO_RESPONSE = new HashMap<>();
	
	static {
		NORMAL_EXCEPTION_TO_RESPONSE.put(AuthenticationCredentialsNotFoundException.class, new HttpStatusAndErrorcode(HttpStatus.UNAUTHORIZED, "CLIENT_ERROR_CREDENTIAL_NOT_FOUND"));
		NORMAL_EXCEPTION_TO_RESPONSE.put(BadCredentialsException.class, new HttpStatusAndErrorcode(HttpStatus.UNAUTHORIZED, "CLIENT_ERROR_BAD_CREDENTIALS"));
		NORMAL_EXCEPTION_TO_RESPONSE.put(AccessDeniedException.class, new HttpStatusAndErrorcode(HttpStatus.FORBIDDEN, "CLIENT_ERROR_FORBIDDEN"));
		NORMAL_EXCEPTION_TO_RESPONSE.put(DisabledException.class, new HttpStatusAndErrorcode(HttpStatus.UNAUTHORIZED, "CLIENT_ERROR_DISABLED"));
		NORMAL_EXCEPTION_TO_RESPONSE.put(DataIntegrityViolationException.class, new HttpStatusAndErrorcode(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR_DATA_INTEGRITY_VIOLATION"));
		NORMAL_EXCEPTION_TO_RESPONSE.put(PersistenceException.class, new HttpStatusAndErrorcode(HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR_DATA_INTEGRITY_VIOLATION"));
		NORMAL_EXCEPTION_TO_RESPONSE.put(IllegalStateException.class, new HttpStatusAndErrorcode(HttpStatus.INTERNAL_SERVER_ERROR, "CLIENT_ERROR_STREAM_CLOSED"));
	}

    @ExceptionHandler(value = Throwable.class)
    public static ModelAndView handle(HttpServletResponse response, Exception e) {
    	if (e instanceof UzletiFigyelmeztetes || e instanceof UzletiHiba) {
    		writeResponseButIgnoreAlreadyClosedOutputStreamExpcetion(response, HttpStatus.CONFLICT, e.getMessage());
            return null;
    	}
    	
    	HttpStatusAndErrorcode httpStatusAndErrorcode = NORMAL_EXCEPTION_TO_RESPONSE.get(e.getClass());
    	boolean isNormalException = httpStatusAndErrorcode != null;
    	if (isNormalException) {
    		LOG.debug("Exception occured", e);
    		writeResponseButIgnoreAlreadyClosedOutputStreamExpcetion(response, httpStatusAndErrorcode.httpStatus, httpStatusAndErrorcode.errorcode);
    	} else if (e instanceof HttpMessageNotReadableException) {
    		LOG.error("Bad request. Client error?", e);
        	writeResponseButIgnoreAlreadyClosedOutputStreamExpcetion(response, HttpStatus.BAD_REQUEST, "CLIENT_ERROR_STREAM_CLOSED");
    	} else {
        	LOG.error("Unexpected exception occured", e);
        	writeResponseButIgnoreAlreadyClosedOutputStreamExpcetion(response, HttpStatus.INTERNAL_SERVER_ERROR, "SERVER_ERROR_INTERNAL");
    	}
    	return null;
    }
    
    private static void writeResponseButIgnoreAlreadyClosedOutputStreamExpcetion(HttpServletResponse response, HttpStatus status, String message) {
    	try {
    		response.getWriter().write(getErrorMessageAsJSONString(message));
            response.setStatus(status.value());
    	} catch (IllegalStateException e) {
    		LOG.debug("The output stream is already closed", e);
    	} catch (IOException e) {
    		LOG.error("Unexpected exception occured", e);
    	}
	}
    
    private static String getErrorMessageAsJSONString(String errorCode) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            new ObjectMapper().writeValue(baos, ImmutableMap.of("errorcode", errorCode));
        } catch (IOException e) {
            LOG.error("Unable to jsonize errorCode: " + errorCode, e);
        }

        return StringUtils.toString(baos);
    }
    
    private static final class HttpStatusAndErrorcode {
    	private final HttpStatus httpStatus;
    	private final String errorcode;
    	private HttpStatusAndErrorcode(HttpStatus httpStatus, String errorcode) {
    		this.httpStatus = httpStatus;
    		this.errorcode = errorcode;
    	}
    }
    
    private RestApiExceptionHandler() {
    }
}
