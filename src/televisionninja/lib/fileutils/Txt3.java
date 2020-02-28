package televisionninja.lib.fileutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import televisionninja.lib.stringutils.FileName;

/**
 * @author TelevisionNinja
 *
 */
public class Txt3 {
	/**
	 * @author TelevisionNinja
	 */
	private File txt,
	temp;

	private final String extension = ".txt";

	/**
	 * creates txt file
	 * @author TelevisionNinja
	 */
	public Txt3(final File txt) {
		if (FileName.getExtension_2(txt.getAbsolutePath()).equalsIgnoreCase(this.extension)) {
			this.txt = txt;
			refreshTemp();
		}
		else {
			System.out.println("Txt() not a txt file");
		}
	}

	/**
	 * deletes the original txt file and creates a blank txt file
	 * 
	 * @author TelevisionNinja
	 */
	public void clearTxt() {
		deleteTxt();
		createTxt();
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	private BufferedReader createReader() {
		return FileUtils.createBufferedReader(this.txt);
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	public void createTxt() {
		try {
			if (this.txt.createNewFile()) {
				System.out.println(this.txt + " created");
			}
			else {
				System.out.println(this.txt + " already exists");
			}
		}
		catch (final IOException e) {
			System.out.println("createTxt() io exception");
		}
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	private PrintWriter createWriter() {
		return FileUtils.createPrintWriter(this.temp, false);
	}

	/**
	 * makes a copy w/o the last line
	 * 
	 * @author TelevisionNinja
	 */
	public void deleteLastLn() {
		try {
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String lineBool = reader.readLine(),
					line = lineBool;
			while (lineBool != null) {
				lineBool = reader.readLine();
				if (lineBool != null) {
					writer.println(line);
				}
				line = lineBool;
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteLastLn() io exception");
		}
	}

	/**
	 * if txt has the line (1st instance)
	 *     a copy is created w/ the line (1st instance) being omitted
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void deleteLn1stInstance(String string) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			boolean check = true;
			while (line != null) {
				if (check && line.toLowerCase().contains(string)) {
					check = false;
				}
				else {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteLn1stInstance() io exception");
		}
	}

	/**
	 * if txt has any lines containing the str
	 *     a copy is created w/ the all lines w/ str being omitted
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void deleteLnAllInstances(String string) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			while (line != null) {
				if (!(line.toLowerCase().contains(string))) {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteLnAllInstances() io exception");
		}
	}

	/**
	 * if txt has the line (nth instance)
	 *     a copy is created w/ the line (nth instance) being omitted
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param n
	 * @author TelevisionNinja
	 */
	public void deleteLnNthInstance(String string, final long n) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			long instance = 1;
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					if (instance != n) {
						writer.println(line);
					}
					instance++;
				}
				else {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteLnNthInstance() io exception");
		}
	}

	/**
	 * a copy is created w/ the nth line being omitted
	 * 
	 * @param string
	 * @param n
	 * @author TelevisionNinja
	 */
	public void deleteNthLn(String string, final long n) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			long lineNum = 1;
			while (line != null) {
				if (lineNum != n) {
					writer.println(line);
				}
				lineNum++;
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteNthLn() io exception");
		}
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	public void deleteTxt() {
		if (this.txt.delete()) {
			System.out.println(this.txt + " deleted");
		}
		else {
			System.out.println(this.txt + " not deleted");
		}
	}

	/**
	 * if txt has lines containing the string
	 *     returns a list of the lines
	 * else
	 *     returns an empty list
	 * 
	 * @param string
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public List<String> getAllInstances(String string) {
		final List<String> list = new ArrayList<>();
		try (BufferedReader reader = createReader()) {
			string = string.toLowerCase();
			String line = reader.readLine();
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					list.add(line);
				}
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("getAllInstances() io exception");
		}
		return list;
	}

	/**
	 * if txt has the line (1st instance)
	 *     returns the line
	 * else
	 *     returns null
	 * 
	 * @param string
	 * @return
	 * @author TelevisionNinja
	 */
	public String getLn1stInstance(String string) {
		try (BufferedReader reader = createReader()) {
			string = string.toLowerCase();
			String line = reader.readLine();
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					return line;
				}
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("getLn1stInstance() io exception");
		}
		return null;
	}

	/**
	 * if txt has the line (nth instance)
	 *     returns the line
	 * else
	 *     returns null
	 * 
	 * @param string
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public String getLnNthInstance(String string, final long n) {
		try (BufferedReader reader = createReader()) {
			string = string.toLowerCase();
			String line = reader.readLine();
			long instance = 1;
			while (line != null) {
				if (instance == n && line.toLowerCase().contains(string)) {
					return line;
				}
				instance++;
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("getLnNthInstance() io exception");
		}
		return null;
	}

	/**
	 * if txt has the nth line
	 *     returns the line
	 * else
	 *     returns null
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public String getNthLn(final long n) {
		try (BufferedReader reader = createReader()) {
			String line = reader.readLine();
			long lineNum = 1;
			while (line != null) {
				if (lineNum == n) {
					return line;
				}
				lineNum++;
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("getNthLn() io exception");
		}
		return null;
	}

	/**
	 * 
	 * @param string
	 * @return
	 * 		number of instances of the string in the txt file
	 * @author TelevisionNinja
	 */
	public long getNumberOfInstances(String string) {
		long instance = 0;
		try (BufferedReader reader = createReader()) {
			string = string.toLowerCase();
			String line = reader.readLine();
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					instance++;
				}
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("getNumberOfInstances() io exception");
		}
		return instance;
	}

	/**
	 * reads lines
	 * 
	 * @return number of lines
	 * @author TelevisionNinja
	 */
	public long getNumberOfLines() {
		long lineNum = 0;
		try (BufferedReader reader = createReader()) {
			String line = reader.readLine();
			while (line != null) {
				lineNum++;
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("getNumberOfLines() io exception");
		}
		return lineNum;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public File getTxt() {
		return this.txt;
	}

	/**
	 * if txt has the line
	 *     returns true
	 * else
	 *     returns false
	 * 
	 * @param string
	 * @return
	 * @author TelevisionNinja
	 */
	public Boolean hasLn1stInstance(String string) {
		try (BufferedReader reader = createReader()) {
			string = string.toLowerCase();
			String line = reader.readLine();
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					return true;
				}
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("hasLn1stInstance() io exception");
		}
		return false;
	}

	/**
	 * if txt has the line (nth instance)
	 *     returns true
	 * else
	 *     returns false
	 * 
	 * @param string
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public Boolean hasLnNthInstance(String string, final long n) {
		try (BufferedReader reader = createReader()) {
			string = string.toLowerCase();
			String line = reader.readLine();
			long instance = 1;
			while (line != null) {
				if (instance == n && line.toLowerCase().contains(string)) {
					return true;
				}
				instance++;
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("hasLnNthInstance() io exception");
		}
		return false;
	}

	/**
	 * if txt has an nth line
	 *     returns true
	 * else
	 *     returns false
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public boolean hasNthLn(final long n) {
		try (BufferedReader reader = createReader()) {
			String line = reader.readLine();
			long lineNum = 1;
			while (line != null) {
				if (lineNum == n) {
					return true;
				}
				lineNum++;
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("hasNthLn() io exception");
		}
		return false;
	}

	/**
	 * prints a string to the end of the txt
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void print(final String string) {
		final PrintWriter txtFile = FileUtils.createPrintWriter(this.txt, true);
		txtFile.print(string);
		txtFile.close();
	}

	/**
	 * prints a string to the end of the txt & starts a new line
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void printLn(final String string) {
		final PrintWriter txtFile = FileUtils.createPrintWriter(this.txt, true);
		txtFile.println(string);
		txtFile.close();
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	private void refreshTemp() {
		this.temp = new File(this.txt.getAbsolutePath() + "temp");
	}

	/**
	 * 
	 * @param newName
	 * 		- input only the file name w/ the extension
	 * @author TelevisionNinja
	 */
	public void rename(final String newName) {
		if (FileName.getExtension_2(newName).equalsIgnoreCase(this.extension)) {
			final File renamed = new File(this.txt.getParent() + newName);
			if (renamed.exists()) {
				System.out.println(renamed + " already exists");
			}
			else {
				this.txt.renameTo(renamed);
				refreshTemp();
			}
		}
		else {
			System.out.println("rename() not a txt file");
		}
	}

	/**
	 * if txt has the line (1st instance)
	 *     a copy is created w/ the line (1st instance) being replaced with the new line
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param newString
	 * @author TelevisionNinja
	 */
	public void replaceLn1stInstance(String string, final String newString) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			boolean check = true;
			while (line != null) {
				if (check && line.toLowerCase().contains(string)) {
					writer.println(newString);
					check = false;
				}
				else {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("replaceLn1stInstance() io exception");
		}
	}

	/**
	 * if txt has any lines containing the old str
	 *     a copy is created w/ all lines w/ old str being replaced with the new str
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param newString
	 * @author TelevisionNinja
	 */
	public void replaceLnAllInstances(String string, final String newString) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					writer.println(newString);
				}
				else {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("replaceLnAllInstances() io exception");
		}
	}

	/**
	 * if txt has the line (nth instance)
	 *     a copy is created w/ the line (nth instance) being replaced with the new line
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param newString
	 * @param n
	 * @author TelevisionNinja
	 */
	public void replaceLnNthInstance(String string, final String newString, final long n) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			long instance = 1;
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					if (instance == n) {
						writer.println(newString);
					}
					else {
						writer.println(line);
					}
					instance++;
				}
				else {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("replaceLnNthInstance() io exception");
		}
	}

	/**
	 * if txt has the nth line
	 *     a copy is created w/ the nth line being replaced with the new line
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param n
	 * @author TelevisionNinja
	 */
	public void replaceNthLn(final String string, final long n) {
		try {
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			long lineNum = 1;
			while (line != null) {
				if (lineNum == n) {
					writer.println(string);
				}
				else {
					writer.println(line);
				}
				lineNum++;
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.txt.delete();
			this.temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("replaceNthLn() io exception");
		}
	}

	/**
	 * 
	 * @param txt
	 * @author TelevisionNinja
	 */
	public void setTxt(final File txt) {
		if (FileName.getExtension_2(txt.getAbsolutePath()).equalsIgnoreCase(this.extension)) {
			this.txt = txt;
			refreshTemp();
		}
		else {
			System.out.println("setTxt() not a txt file");
		}
	}

	/**
	 * reads lines
	 * 
	 * @return number of lines
	 * @author TelevisionNinja
	 */
	public List<String> txtToList() {
		final List<String> list = new ArrayList<>();
		try (BufferedReader reader = createReader()) {
			String line = reader.readLine();
			while (line != null) {
				list.add(line);
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("txtToList() io exception");
		}
		return list;
	}
}