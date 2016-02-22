package hu.interconnect.util;

import static org.apache.commons.io.IOUtils.toByteArray;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import hu.interconnect.exception.ProgramozasiHiba;

public final class FileUtils {

	private static final Log LOG = LogFactory.getLog(FileUtils.class);

	public static InputStream getInputStream(String path) {
		try {
			return new FileInputStream(new File(path));
		} catch (FileNotFoundException e) {
			throw new ProgramozasiHiba("Nem talalom a filet! " + path, e);
		}
	}
	
	public static byte[] getByteArray(File file) {
		return getByteArray(file.getAbsolutePath());
	}

	public static byte[] getByteArray(String path) {
		try {
			return toByteArray(getInputStream(path));
		} catch (IOException e) {
			throw new ProgramozasiHiba("Nem talalom a filet! " + path, e);
		}
	}

	public static byte[] getClasspathByteArray(String path) {
		try {
			return toByteArray(getClasspathInputStream(path));
		} catch (IOException e) {
			throw new ProgramozasiHiba("Nem tudtam az inputstreambol bytearrayt csinalni! " + path, e);
		}
	}

	public static InputStream getClasspathInputStream(String path) {
		InputStream is;
		try {
			is = FileUtils.class.getResourceAsStream(path);
			if (is == null) {
				throw new IOException("ClassPath resource was not found: " + path);
			}
		} catch (IOException e) {
			throw new ProgramozasiHiba("Nem talalom a filet! " + path, e);
		}
		return is;
	}

	public static void writeTo(String path, byte[] b) {
		try {
			File file = new File(path);
			mkdirs(path);
			boolean createNewFile = file.createNewFile();
			if (createNewFile) {
				LOG.trace("File created: " + path);
			} else {
				LOG.trace("File was already there: " + path);
			}
			try (FileOutputStream out = new FileOutputStream(file)) {
				out.write(b);
			}
		} catch (IOException e) {
			throw new ProgramozasiHiba("Nem sikerult kiirni a filet! Path: " + path + ", length: " + (b != null ? b.length : "NULL"), e);
		}
	}
	
	public static void mkdirs(String path) {
		File file = new File(path);
		File parentFile = file.getParentFile();
		if (parentFile == null) {
			throw new ProgramozasiHiba("Nem hozhato letre a konyvtar, mert a parentFile NULL: " + path);
		}
		boolean mkdirs = file.getParentFile().mkdirs();
		if (mkdirs) {
			LOG.trace("Directory created: " + path);
		} else {
			LOG.trace("Directory was already there: " + path);
		}
	}

	private FileUtils() {
	}
}
