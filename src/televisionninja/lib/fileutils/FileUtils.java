package televisionninja.lib.fileutils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

/**
 * @author TelevisionNinja
 *
 */
public class FileUtils {
	/**
	 * file channel byte copy method
	 * 
	 * @param source
	 * @param dest
	 * @author TelevisionNinja
	 */
	public static void copy_1(final File source, final File dest) {
		try {
			final FileInputStream inputStream = new FileInputStream(source);
			final FileOutputStream outputStream = new FileOutputStream(dest);
			for (int x; (x = inputStream.read()) != -1;) {
				outputStream.write(x);
			}
			inputStream.close();
			outputStream.close();
			System.out.println("copy_1() done");
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("copy_1() file not found");
		}
		catch (final IOException io) {
			System.out.println("copy_1() io exception");
		}
	}

	/**
	 * file stream buffer copy method
	 * 
	 * @param source
	 * @param dest
	 * @author TelevisionNinja
	 */
	public static void copy_2(final File source, final File dest) {
		try {
			final FileInputStream inputStream = new FileInputStream(source);
			final FileOutputStream outputStream = new FileOutputStream(dest);
			final byte[] buffer = new byte[1024];
			int length = 0;
			while ((length = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, length);
			}
			inputStream.close();
			outputStream.close();
			System.out.println("copy_2() done");
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("copy_2() file not found");
		}
		catch (final IOException io) {
			System.out.println("copy_2() io exception");
		}
	}

	/**
	 * file channel copy method
	 * 
	 * @param source
	 * @param dest
	 * @author TelevisionNinja
	 */
	public static void copy_3(final File source, final File dest) {
		try {
			final FileInputStream inputStream = new FileInputStream(source);
			final FileOutputStream outputStream = new FileOutputStream(dest);

			final FileChannel sourceChannel = inputStream.getChannel();
			final FileChannel destChannel = outputStream.getChannel();

			destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

			inputStream.close();
			outputStream.close();
			sourceChannel.close();
			destChannel.close();

			System.out.println("copy_3() done");
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("copy_3() file not found");
		}
		catch (final IOException io) {
			System.out.println("copy_3() io exception");
		}
	}

	/**
	 * Files.copy() method
	 * 
	 * @param source
	 * @param dest
	 * @author TelevisionNinja
	 */
	public static void copy_4(final File source, final File dest) {
		try {
			Files.copy(source.toPath(), dest.toPath());
			System.out.println("copy_4() done");
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("copy_4() file not found");
		}
		catch (final IOException io) {
			System.out.println("copy_4() io exception");
		}
	}

	/**
	 * read bytes
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static BufferedInputStream createBufferedInputStream(final File file) {
		try {
			return new BufferedInputStream(new FileInputStream(file));
		}
		catch (final IOException io) {
			System.out.println("createDataInputStream() io exception");
		}
		return null;
	}

	/**
	 * write bytes
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static BufferedOutputStream createBufferedOutputStream(final File file) {
		try {
			return new BufferedOutputStream(new FileOutputStream(file));
		}
		catch (final IOException io) {
			System.out.println("createDataOutputStream() io exception");
		}
		return null;
	}

	/**
	 * read text
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static BufferedReader createBufferedReader(final File file) {
		try {
			return new BufferedReader(new FileReader(file));
		}
		catch (final IOException io) {
			System.out.println("createBufferedReader() io exception");
		}
		return null;
	}

	/**
	 * read primitives
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static DataInputStream createDataInputStream(final File file) {
		try {
			return new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
		}
		catch (final IOException io) {
			System.out.println("createDataInputStream() io exception");
		}
		return null;
	}

	/**
	 * write primitives
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static DataOutputStream createDataOutputStream(final File file) {
		try {
			return new DataOutputStream(new BufferedOutputStream(new FileOutputStream(file)));
		}
		catch (final IOException io) {
			System.out.println("createDataOutputStream() io exception");
		}
		return null;
	}

	/**
	 * File().createNewFile() method
	 * 
	 * creates file
	 * prints the file name and "created"
	 * 
	 * @param file
	 * @author TelevisionNinja
	 */
	public static void createFile(final File file) {
		try {
			if (file.createNewFile()) {
				System.out.println(file.getName() + " created");
			}
			else {
				System.out.println(file.getName() + " already exists");
			}
		}
		catch (final IOException e) {
			System.out.println("createFile() io exception");
		}
	}

	/**
	 * write text
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static PrintWriter createPrintWriter(final File file, final boolean append) {
		try {
			return new PrintWriter(new BufferedWriter(new FileWriter(file, append)));
		}
		catch (final IOException io) {
			System.out.println("createPrintWriter() io exception");
		}
		return null;
	}

	/**
	 * File().delete() method
	 * 
	 * deletes file
	 * prints "deleted " and the file name
	 * 
	 * @param directory
	 * @param fileName
	 * @author TelevisionNinja
	 */
	public static void deleteFile(final File file) {
		if (file.delete()) {
			System.out.println("deleteFile() deleted " + file.getName());
		}
		else {
			System.out.println("deleteFile() can't delete " + file.getName());
		}
	}

	public FileUtils() {

	}

	/**
	 * override this
	 */
	public void iterateDirDirAction(final File file) {

	}

	/**
	 * override this
	 */
	public void iterateDirFileAction(final File file) {

	}

	/**
	 * 
	 * @param dir
	 * @author TelevisionNinja
	 */
	public void iterateThroughDirectoryRecursion(final File dir) {
		try {
			for (final File file : dir.listFiles()) {
				if (file.isDirectory()) {
					iterateDirDirAction(file);
					iterateThroughDirectoryRecursion(file);
				}
				else {
					iterateDirFileAction(file);
				}
			}
		}
		catch (final NullPointerException e) {
			System.out.println("iterateThroughDirectoryRecursion() NullPointerException");
		}
	}
}