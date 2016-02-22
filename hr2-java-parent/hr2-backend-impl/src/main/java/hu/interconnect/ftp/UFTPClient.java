package hu.interconnect.ftp;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;

public class UFTPClient {

	private static final Log LOG = LogFactory.getLog(UFTPClient.class);

	private UFTPParameters uFTPParameters;
	
	private FTPClient ftp = new FTPClient();
	private FTPClientConfig config = new FTPClientConfig();

	public UFTPClient(UFTPParameters uFTPParameters) {
		this.uFTPParameters = uFTPParameters;
	}
	
	public void connect() throws IOException {
		ftp.configure(config);
		
		String host = uFTPParameters.getHost();
		int port = uFTPParameters.getPort();
		String username = uFTPParameters.getUsername();
		String password = uFTPParameters.getPassword();
		
		try {
			ftp.connect(host, port);
			LOG.info("Connected to " + host + ": " + ftp.getReplyString());
			ftp.login(username, password);

			// After connection attempt, you should check the reply code to verify success
			int reply = ftp.getReplyCode();

			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				LOG.error("FTP server refused connection: " + reply);
			}
		} catch (IOException e) {
			LOG.error("Connect failed!", e);
			throw e;
		}
	}
	
	public void store(String filenev, InputStream tartalom) throws IOException {
		// transfer files
		try {
			ftp.storeFile(filenev, tartalom);
		} catch (IOException e) {
			LOG.error("Store failed!", e);
			throw e;
		}
	}
	
	public void logout() {
		try {
			ftp.logout();
		} catch (IOException e) {
			LOG.error("Logout failed!", e);
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					// do nothing
				}
			}
		}
	}
}
