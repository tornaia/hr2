package test.unit.hu.interconnect.hr.web.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import javax.persistence.PersistenceException;

import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;

import hu.interconnect.exception.UzletiFigyelmeztetes;
import hu.interconnect.hr.web.config.RestApiExceptionHandler;
import test.unit.AbstractUnitTest;

public class RestApiExceptionHandlerTest extends AbstractUnitTest {

	private MockHttpServletResponse response = new MockHttpServletResponse();

	@Test
	public void uzletiFigyelmeztetesIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new UzletiFigyelmeztetes("uzletiFigyelmeztetes")));
		expectedResponse(response, "uzletiFigyelmeztetes", HttpStatus.CONFLICT);
	}
	
	@Test
	public void uzletiHibaIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new UzletiFigyelmeztetes("uzletiHiba")));
		expectedResponse(response, "uzletiHiba", HttpStatus.CONFLICT);
	}
	
	@Test
	public void authenticationCredentialsNotFoundExceptionIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new AuthenticationCredentialsNotFoundException("authenticationCredentialsNotFoundException")));
		expectedResponse(response, "CLIENT_ERROR_CREDENTIAL_NOT_FOUND", HttpStatus.UNAUTHORIZED);
	}
	
	@Test
	public void badCredentialsExceptionIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new BadCredentialsException("badCredentialsException")));
		expectedResponse(response, "CLIENT_ERROR_BAD_CREDENTIALS", HttpStatus.UNAUTHORIZED);
	}
	
	@Test
	public void accessDeniedExceptionIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new AccessDeniedException("accessDeniedException")));
		expectedResponse(response, "CLIENT_ERROR_FORBIDDEN", HttpStatus.FORBIDDEN);
	}
	
	@Test
	public void disabledExceptionIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new DisabledException("disabledException")));
		expectedResponse(response, "CLIENT_ERROR_DISABLED", HttpStatus.UNAUTHORIZED);
	}
	
	@Test
	public void dataIntegrityViolationExceptionIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new DataIntegrityViolationException("dataIntegrityViolationException")));
		expectedResponse(response, "SERVER_ERROR_DATA_INTEGRITY_VIOLATION", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void persistenceExceptionIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new PersistenceException("persistenceException")));
		expectedResponse(response, "SERVER_ERROR_DATA_INTEGRITY_VIOLATION", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@Test
	public void httpMessageNotReadableExceptionIsHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new HttpMessageNotReadableException("httpMessageNotReadableException")));
		expectedResponse(response, "CLIENT_ERROR_STREAM_CLOSED", HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void otherExceptionsAreHandled() throws Exception {
		assertNull(RestApiExceptionHandler.handle(response, new NullPointerException("whateverException")));
		expectedResponse(response, "SERVER_ERROR_INTERNAL", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private static void expectedResponse(MockHttpServletResponse response, String expectedErrorcode, HttpStatus expectedStatus) throws Exception {
		assertEquals("{\"errorcode\":\"" + expectedErrorcode + "\"}", response.getContentAsString());
		assertEquals(expectedStatus.value(), response.getStatus());
	}
}
