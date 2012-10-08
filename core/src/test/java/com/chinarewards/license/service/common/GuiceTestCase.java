//package com.chinarewards.license.service.common;
//
//import java.io.BufferedOutputStream;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//import junit.framework.TestCase;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.google.inject.Injector;
//
//public abstract class GuiceTestCase extends TestCase {
//
//	protected Logger logger = LoggerFactory.getLogger(getClass());
//
//	final protected Injector injector;
//
//	List<File> fileDeleteOnExit;
//
//	public GuiceTestCase() {
//		this.injector = TestInjectorUtil.getTestInjector();
//	}
//
//	@Override
//	protected void setUp() throws Exception {
//		super.setUp();
//
//		fileDeleteOnExit = new ArrayList<File>();
//	}
//
//	@Override
//	protected void tearDown() throws Exception {
//		removeTempFiles();
//		super.tearDown();
//	}
//
//	/**
//	 * Sleep for the specified milliseconds.
//	 * 
//	 * @param millis
//	 */
//	protected void sleep(long millis) {
//		try {
//			Thread.sleep(millis);
//		} catch (InterruptedException e) {
//			throw new RuntimeException("Problem while sleep for " + millis
//					+ " msec", e);
//		}
//	}
//
//	/**
//	 * Create a temporary file using the given input stream. Return the temp
//	 * file location.
//	 * <p>
//	 * 
//	 * This method also store the created temp file, and will delete all created
//	 * files at the end of test.
//	 * 
//	 * @param is
//	 * @return
//	 * @throws IOException
//	 */
//	protected File createTempFile(InputStream is) throws IOException {
//
//		if (is == null) {
//			throw new IllegalArgumentException(
//					"input stream should not be null");
//		}
//
//		File tmpFile = File.createTempFile("bsc-import-", null);
//		tmpFile.deleteOnExit();
//
//		// write the resource to a temp file.
//		BufferedOutputStream os = new BufferedOutputStream(
//				new FileOutputStream(tmpFile));
//		int len = 0;
//		byte[] buf = new byte[4096];
//		try {
//			while ((len = is.read(buf)) != -1) {
//				os.write(buf, 0, len);
//			}
//		} finally {
//			os.close();
//		}
//
//		logger.debug("Temporary file created at {}", tmpFile);
//
//		// remember that we need to delete this file.
//		this.fileDeleteOnExit.add(tmpFile);
//		return tmpFile;
//	}
//
//	protected void removeTempFiles() {
//		if (fileDeleteOnExit != null && !fileDeleteOnExit.isEmpty()) {
//			for (File f : fileDeleteOnExit) {
//				boolean ok = f.delete();
//				logger.debug("Deleteing temp file {}, deleted? {}", f, ok);
//				if (f.exists()) {
//					logger.warn("Unable to delete tmp file {}", f);
//				}
//			}
//		}
//		fileDeleteOnExit = null;
//	}
//
//}
