package televisionninja.lib.fileutils.encryption;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import televisionninja.lib.fileutils.FileUtils;
import televisionninja.lib.stringutils.FileName;

/**
 * @author TelevisionNinja
 *
 */
public class SimpleEncryption {
	/**
	 * @author TelevisionNinja
	 */
	private String extension = ".encrypted";

	/**
	 * @author TelevisionNinja
	 */
	public SimpleEncryption() {

	}

	/**
	 * @author TelevisionNinja
	 */
	public SimpleEncryption(final String extension) {
		this.extension = extension;
	}

	/**
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 * @param key
	 * @author TelevisionNinja
	 */
	public void decryptDirectory_1(final String inputDirectory, final String outputDirectory, final String key) {
		for (final String file : new File(inputDirectory).list()) {
			if (file.endsWith(this.extension)) {
				decryptFile_1(inputDirectory, outputDirectory, file, key);
			}
		}
	}

	/**
	 * 
	 * @param dir
	 * @param destination
	 * @param key
	 * @author TelevisionNinja
	 */
	public void decryptDirectory_2(final File dir, final File destination, final String key) {
		try {
			for (final File file : dir.listFiles()) {
				if (file.isDirectory()) {
					decryptDirectory_2(file, destination, key);
				}
				else if (file.getName().endsWith(this.extension)){
					decryptFile_2(file, destination, key);
				}
			}
		}
		catch (final NullPointerException e) {
			System.out.println("decryptDirectory_2() NullPointerException");
		}
	}

	/**
	 * FileUtils
	 * 
	 * @param dir
	 * @param key
	 * @author TelevisionNinja
	 */
	public void decryptDirectory_3(final File dir, final String key) {
		new FileUtils() {
			@Override
			public void iterateDirFileAction(final File file) {
				decryptFile_2(file, new File(file.getParent()), key);
			}
		}.iterateThroughDirectoryRecursion(dir);
	}

	/**
	 * BufferedStream method
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 * @param fileName
	 * @param key
	 * @author TelevisionNinja
	 */
	public void decryptFile_1(final String inputDirectory, final String outputDirectory, final String fileName, final String key) {
		if (new File(outputDirectory + fileName.substring(0, fileName.length() - this.extension.length())).exists()) {
			System.out.println(FileName.removeExtension_2(fileName) + " already exists");
		}
		else {
			try {

				final byte[] bytes = key.getBytes();
				int count = 0;
				final long length = bytes.length;
				final BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputDirectory + fileName));
				final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputDirectory + FileName.removeExtension_2(fileName)));
				for (int x; (x = inputStream.read()) != -1;) {
					outputStream.write(x - bytes[count++]);
					if (count >= length) {
						count = 0;
					}
				}
				inputStream.close();
				outputStream.close();
				System.out.println("decrypted " + fileName);
				FileUtils.deleteFile(new File(inputDirectory + fileName));
			}
			catch (final FileNotFoundException noFile) {
				System.out.println("decryptFile_1() file not found");
			}
			catch (final IOException io) {
				System.out.println("decryptFile_1() io exception");
			}
		}
	}

	/**
	 * FileStream buffer method
	 * 
	 * destination has to be a dir
	 * 
	 * @param source
	 * @param dest
	 * @param key
	 * @author TelevisionNinja
	 */
	public void decryptFile_2(final File source, File dest, final String key) {
		dest = new File(dest.getAbsoluteFile() + File.separator + FileName.removeExtension_2(source.getName()));
		if (dest.exists()) {
			System.out.println(dest.getName() + " already exists");
		}
		else {
			try {
				int count = 0,
						length = 0;
				final int bufferLength = 1024;
				final byte[] bytes = key.getBytes(),
						buffer = new byte[bufferLength];
				final long keyLength = bytes.length;
				final FileInputStream inputStream = new FileInputStream(source);
				final FileOutputStream outputStream = new FileOutputStream(dest);

				while ((length = inputStream.read(buffer)) > 0) {
					for (int x = 0; x < bufferLength; x++) {
						buffer[x] = (byte) (buffer[x] - bytes[count++]);
						if (count == keyLength) {
							count = 0;
						}
					}
					outputStream.write(buffer, 0, length);
				}

				inputStream.close();
				outputStream.close();

				System.out.println("decrypted " + source.getName());
				source.delete();
			}
			catch (final FileNotFoundException noFile) {
				System.out.println("decryptFile_2() file not found");
			}
			catch (final IOException io) {
				System.out.println("decryptFile_2() io exception");
			}
		}
	}

	/**
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 * @param key
	 * @author TelevisionNinja
	 */
	public void encryptDirectory_1(final String inputDirectory, final String outputDirectory, final String key) {
		for (final String file : new File(inputDirectory).list()) {
			final File aFile = new File(inputDirectory + file);
			if (aFile.isFile() && !aFile.isHidden()) {
				encryptFile_1(inputDirectory, outputDirectory, file, key);
			}
		}
	}

	/**
	 * 
	 * @param dir
	 * @param destination
	 * @param key
	 * @author TelevisionNinja
	 */
	public void encryptDirectory_2(final File dir, final File destination, final String key) {
		try {
			for (final File file : dir.listFiles()) {
				if (file.isDirectory()) {
					encryptDirectory_2(file, destination, key);
				}
				else if (file.getName().endsWith(this.extension)){
					encryptFile_2(file, destination, key);
				}
			}
		}
		catch (final NullPointerException e) {
			System.out.println("encryptDirectory_2() NullPointerException");
		}
	}

	/**
	 * FileUtils
	 * 
	 * @param dir
	 * @param key
	 * @author TelevisionNinja
	 */
	public void encryptDirectory_3(final File dir, final String key) {
		new FileUtils() {
			@Override
			public void iterateDirFileAction(final File file) {
				encryptFile_2(file, new File(file.getParent()), key);
			}
		}.iterateThroughDirectoryRecursion(dir);
	}

	/**
	 * BufferedStream method
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 * @param fileName
	 * @param key
	 * @author TelevisionNinja
	 */
	public void encryptFile_1(final String inputDirectory, final String outputDirectory, final String fileName, final String key) {
		if (new File(outputDirectory + fileName + this.extension).exists()) {
			System.out.println(fileName + this.extension + " already exists");
		}
		else {
			try {
				int count = 0;
				final byte[] bytes = key.getBytes();
				final long length = bytes.length;
				final BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(inputDirectory + fileName));
				final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(outputDirectory + fileName + this.extension));
				for (int x; (x = inputStream.read()) != -1;) {
					outputStream.write(x + bytes[count++]);
					if (count >= length) {
						count = 0;
					}
				}
				inputStream.close();
				outputStream.close();
				System.out.println("encrypted " + fileName);
				FileUtils.deleteFile(new File(inputDirectory + fileName));
			}
			catch (final FileNotFoundException noFile) {
				System.out.println("encryptFile_1() file not found");
			}
			catch (final IOException io) {
				System.out.println("encryptFile_1() io exception");
			}
		}
	}

	/**
	 * FileStream buffer method
	 * 
	 * destination has to be a dir
	 * 
	 * @param source
	 * @param dest
	 * @param key
	 * @author TelevisionNinja
	 */
	public void encryptFile_2(final File source, File dest, final String key) {
		dest = new File(dest.getAbsoluteFile() + File.separator + source.getName() + this.extension);
		if (dest.exists()) {
			System.out.println(dest.getName() + " already exists");
		}
		else {
			try {
				int count = 0,
						length = 0;
				final int bufferLength = 1024;
				final byte[] bytes = key.getBytes(),
						buffer = new byte[bufferLength];
				final long keyLength = bytes.length;

				final FileInputStream inputStream = new FileInputStream(source);
				final FileOutputStream outputStream = new FileOutputStream(dest);
				while ((length = inputStream.read(buffer)) > 0) {
					for (int x = 0; x < bufferLength; x++) {
						buffer[x] = (byte) (buffer[x] + bytes[count++]);
						if (count == keyLength) {
							count = 0;
						}
					}
					outputStream.write(buffer, 0, length);
				}
				inputStream.close();
				outputStream.close();

				System.out.println("encrypted " + source.getName());
				source.delete();
			}
			catch (final FileNotFoundException noFile) {
				System.out.println("encryptFile_2() file not found");
			}
			catch (final IOException io) {
				System.out.println("encryptFile_2() io exception");
			}
		}
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public String getExtension() {
		return this.extension;
	}

	/**
	 * 
	 * @param extension
	 * @author TelevisionNinja
	 */
	public void setExtension(final String extension) {
		this.extension = extension;
	}
}