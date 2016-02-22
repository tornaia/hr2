package hu.interconnect.hr.web.rest;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.net.BCodec;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.hr.backend.api.dto.FileResponseDTO;

public abstract class AbstractDownloadController {

	private static final Log LOG = LogFactory.getLog(AbstractDownloadController.class);
	
	protected static void downloadFile(HttpServletRequest request, HttpServletResponse response, FileResponseDTO filenameAndContent) {
		setHeader(filenameAndContent.filename, request, response);
		try {
			IOUtils.write(filenameAndContent.content, response.getOutputStream());
		} catch (IOException e) {
			throw new ProgramozasiHiba("Nem tudtam kiirni a filet", e);
		}
	}
	
	protected static void setHeader(String filename, HttpServletRequest request, HttpServletResponse response) {
		// set default value to the given filename
		String encodedFileName = filename;
	
		try {
			String userAgent = request.getHeader("User-Agent");
			if (StringUtils.isEmpty(userAgent) || userAgent.contains("MSIE") || userAgent.contains("Opera")) {
				// encode
				encodedFileName = URLEncoder.encode(encodedFileName, "utf8");
				// insert space
				encodedFileName = encodedFileName.replaceAll("\\+", "%20");
			} else {
				// Firefox branch
				// encode using Base64 encoding
				BCodec encoder = new BCodec("utf8");
				encodedFileName = encoder.encode(encodedFileName);
			}
		} catch (Exception ex) {
			LOG.error("Error during creation of the header (response):", ex);
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFileName + "\"");
		response.setContentType("application/x-download");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-control", "must-revalidate");
	}
}
