package test.unit.hu.interconnect.util;

import static org.apache.commons.lang3.StringEscapeUtils.escapeJava;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.Random;

import org.junit.Test;

import hu.interconnect.exception.ProgramozasiHiba;
import hu.interconnect.util.FileUtils;
import test.unit.AbstractUnitTest;

public class FileUtilsTest extends AbstractUnitTest {

	@Test
	public void getInputStreamFileNotFound() {
		expectedException.expect(ProgramozasiHiba.class);
		expectedException.expectMessage("Nem talalom a filet! nemLetezoFile");
		
		FileUtils.getInputStream("nemLetezoFile");
	}
	
	@Test
	public void getInputStream() throws Exception {
		File tempFile = File.createTempFile("prefix", "suffix");
		String absolutePathToTempFile = tempFile.getAbsolutePath();
		
		try (InputStream inputStream = FileUtils.getInputStream(absolutePathToTempFile)) {
			assertNotNull(inputStream);
		}
	}
	
	@Test
	public void getByteArrayFromFile() throws Exception {
		File tempFile = File.createTempFile("prefix", "suffix");
		try (FileWriter fileWriter = new FileWriter(tempFile)) {
			fileWriter.write("teszt");
		}
		byte[] tempFileContent = FileUtils.getByteArray(tempFile);
		assertArrayEquals("teszt".getBytes(), tempFileContent);
	}
	
	@Test
	public void getByteArrayFromFileException() {
		expectedException.expect(ProgramozasiHiba.class);
		expectedException.expectMessage("Nem talalom a filet! nemLetezoFile");
		
		FileUtils.getByteArray("nemLetezoFile");
	}
	
	@Test
	public void getClasspathByteArray() {
		String pathToThisClass = "/" + FileUtilsTest.class.getCanonicalName().replaceAll("\\.", escapeJava(java.io.File.separator)) + ".class";
		FileUtils.getClasspathByteArray(pathToThisClass);
	}
	
	@Test
	public void getClasspathByteArrayException() {
		expectedException.expect(ProgramozasiHiba.class);
		expectedException.expectMessage("Nem talalom a filet! nemLetezoFile");
		
		FileUtils.getClasspathByteArray("nemLetezoFile");
	}
	
	@Test
	public void getClasspathInputStream() throws Exception {
		String pathToThisClass = "/" + FileUtilsTest.class.getCanonicalName().replaceAll("\\.", escapeJava(java.io.File.separator)) + ".class";
		
		try (InputStream inputStream = FileUtils.getClasspathInputStream(pathToThisClass)) {
			assertNotNull(inputStream);
		}
	}
	
	@Test
	public void getClasspathInputStreamException() {
		expectedException.expect(ProgramozasiHiba.class);
		expectedException.expectMessage("Nem talalom a filet! nemLetezoFile");
		
		FileUtils.getClasspathInputStream("nemLetezoFile");
	}
	
	@Test
	public void writeToExistingFile() throws Exception {
		File file = File.createTempFile("prefix", "suffix");
		String path = file.getAbsolutePath();
		
		FileUtils.writeTo(path, new byte[]{'1', '2'});
		assertArrayEquals(new byte[]{'1', '2'}, FileUtils.getByteArray(file));
		
		FileUtils.writeTo(path, new byte[]{'1', '2'});
		assertArrayEquals(new byte[]{'1', '2'}, FileUtils.getByteArray(file));
	}
	
	@Test
	public void writeToNewFile() throws Exception {
		File file = File.createTempFile("prefix", "suffix");
		String path = file.getAbsolutePath() + new Random().nextInt(Integer.MAX_VALUE);
		FileUtils.writeTo(path, new byte[]{'1', '2'});
		
		byte[] fileContent = FileUtils.getByteArray(path);
		assertArrayEquals(new byte[]{'1', '2'}, fileContent);
	}
	
	@Test
	public void writeToNoParentDirectoryException() {
		expectedException.expect(ProgramozasiHiba.class);
		expectedException.expectMessage("Nem hozhato letre a konyvtar, mert a parentFile NULL: nemLetezoFile");
		
		FileUtils.writeTo("nemLetezoFile", new byte[]{'1', '2'});
	}
	
	@Test
	public void mkDirsNewDir() throws Exception {
		String pathToThisClass = "/" + FileUtilsTest.class.getCanonicalName().replaceAll("\\.", escapeJava(java.io.File.separator)) + ".class";
		String parhToNewDirectory = new File(pathToThisClass).getParentFile().getAbsolutePath() + "/" + new Random().nextInt(Integer.MAX_VALUE) + "/newDirectory";
		FileUtils.mkdirs(parhToNewDirectory);
	}
}
