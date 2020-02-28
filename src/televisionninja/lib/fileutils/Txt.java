package televisionninja.lib.fileutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * old, don't use
 * 
 * @author TelevisionNinja
 *
 */
public class Txt {
	/**
	 * @author TelevisionNinja
	 */
	private PrintWriter txtFile;
	/**
	 * @author TelevisionNinja
	 */
	private String directory = "",
			fileName = "txt";
	private final String txt = ".txt";
	private final String temp = "temp" + this.txt;

	/**
	 * 
	 * @param directory
	 * @param fileName
	 * @author TelevisionNinja
	 */
	public Txt(final String directory, final String fileName) {
		this.directory = directory;
		this.fileName = fileName;
	}

	/**
	 * needs close()
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void add(final String string) {
		this.txtFile.append(string);
	}

	/**
	 * appends a string to the end of the txt
	 * doesn't need close()
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void append(final String string) {
		try (PrintWriter txtFile = new PrintWriter(new BufferedWriter(new FileWriter(this.directory + this.fileName + this.txt, true)))) {
			txtFile.append(string);
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("append() file not found");
		}
		catch (final IOException io) {
			System.out.println("append() io exception");
		}
	}

	/**
	 * @author TelevisionNinja
	 */
	public void close() {
		this.txtFile.close();
	}

	/**
	 * doesn't check if file already exists
	 * needs close()
	 * 
	 * @author TelevisionNinja
	 */
	public void create() {
		try {
			this.txtFile = new PrintWriter(this.directory + this.fileName + this.txt);
		}
		catch (final Exception e) {
			System.out.println("create() exception");
		}
	}

	/**
	 * creates txt file
	 * doesn't need close()
	 * 
	 * @author TelevisionNinja
	 */
	public void createTxt() {
		if (new File(this.directory + this.fileName + this.txt).exists()) {
			System.out.println(this.fileName + this.txt + " already exists");
		}
		else {
			try {
				new PrintWriter(this.directory + this.fileName + this.txt).close();
			}
			catch (final Exception e) {
				System.out.println("createTxt() exception");
			}
		}
	}

	/**
	 * if txt has the line
	 *     a copy is created w/o including the line
	 * else
	 *     makes an exact copy
	 * doesn't need close()
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void deleteLn(final String string) {
		try {
			final File textFile = new File(this.directory + this.fileName + this.txt),
					temp = new File(this.directory + this.temp);
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
			String line = reader.readLine(),
					tempLine;
			while (line != null) {
				tempLine = line.toLowerCase();
				if (!tempLine.contains(string.toLowerCase())) {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("deleteLn() file not found");
		}
		catch (final IOException io) {
			System.out.println("deleteLn() io exception");
		}
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public String getDirectory() {
		return this.directory;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public String getFileName() {
		return this.fileName;
	}

	/**
	 * if txt has the line
	 *     returns the line
	 * else
	 *     returns null
	 * doesn't need close()
	 * 
	 * @param string
	 * @return
	 * @author TelevisionNinja
	 */
	public String getLn(final String string) {
		try (BufferedReader reader = new BufferedReader(new FileReader(this.directory + this.fileName + this.txt))) {
			String line = reader.readLine(),
					tempLine;
			while (line != null) {
				tempLine = line.toLowerCase();
				if (tempLine.contains(string.toLowerCase())) {
					return line;
				}
				line = reader.readLine();
			}
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("getLn() file not found");
		}
		catch (final IOException io) {
			System.out.println("getLn() io exception");
		}
		return null;
	}

	/**
	 * if txt has the line
	 *     returns true
	 * else
	 *     returns false
	 * doesn't need close()
	 * 
	 * @param string
	 * @return
	 * @author TelevisionNinja
	 */
	public Boolean hasLn(final String string) {
		try (BufferedReader reader = new BufferedReader(new FileReader(this.directory + this.fileName + this.txt))) {
			String line = reader.readLine();
			while (line != null) {
				if (line.toLowerCase().contains(string.toLowerCase())) {
					return true;
				}
				line = reader.readLine();
			}
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("hasLn() file not found");
		}
		catch (final IOException io) {
			System.out.println("hasLn() io exception");
		}
		return false;
	}

	/**
	 * prints a string to the end of the txt
	 * doesn't need close()
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void print(final String string) {
		try (PrintWriter txtFile = new PrintWriter(new BufferedWriter(new FileWriter(this.directory + this.fileName + this.txt, true)))) {
			txtFile.print(string);
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("print() file not found");
		}
		catch (final IOException io) {
			System.out.println("print() io exception");
		}
	}

	/**
	 * prints a string with a new line to the end of the txt
	 * doesn't need close()
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void printLn(final String string) {
		try (PrintWriter txtFile = new PrintWriter(new BufferedWriter(new FileWriter(this.directory + this.fileName + this.txt, true)))) {
			txtFile.println(string);
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("printLn() file not found");
		}
		catch (final IOException io) {
			System.out.println("printLn() io exception");
		}
	}

	/**
	 * if txt has the line
	 *     a copy is created w/ the line being replaced with the new line
	 * else
	 *     makes an exact copy
	 * doesn't need close()
	 * 
	 * @param string
	 * @param newString
	 * @author TelevisionNinja
	 */
	public void replaceLn(final String string, final String newString) {
		try {
			final File textFile = new File(this.directory + this.fileName + this.txt),
					temp = new File(this.directory + this.temp);
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
			String line = reader.readLine(),
					tempLine;
			long count = 0;
			while (line != null) {
				tempLine = line.toLowerCase();
				if (tempLine.contains(string.toLowerCase()) && count == 0) {
					writer.println(newString);
					count++;
				}
				else {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("replaceLn() file not found");
		}
		catch (final IOException io) {
			System.out.println("replaceLn() io exception");
		}
	}

	/**
	 * 
	 * @param directory
	 * @author TelevisionNinja
	 */
	public void setDirectory(final String directory) {
		this.directory = directory;
	}

	/**
	 * 
	 * @param fileName
	 * @author TelevisionNinja
	 */
	public void setFileName(final String fileName) {
		this.fileName = fileName;
	}

	/**
	 * needs close()
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void write(final String string) {
		this.txtFile.print(string);
	}

	/**
	 * needs close()
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void writeLn(final String string) {
		this.txtFile.println(string);
	}
}