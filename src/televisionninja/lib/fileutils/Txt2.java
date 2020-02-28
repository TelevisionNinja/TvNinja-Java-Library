package televisionninja.lib.fileutils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import televisionninja.lib.stringutils.FileName;

/**
 * @author TelevisionNinja
 *
 */
public class Txt2 {
	/**
	 * @author TelevisionNinja
	 */
	private File txt;

	/**
	 * creates txt file
	 * @author TelevisionNinja
	 */
	public Txt2(final File txt) {
		if (FileName.getExtension_2(txt.getAbsolutePath()).equalsIgnoreCase(".txt")) {
			this.txt = txt;
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
	 * makes a copy w/o the last line
	 * 
	 * @author TelevisionNinja
	 */
	public void deleteLastLn() {
		try {
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
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
	public void deleteLn1stInstance_1(String string) {
		try {
			string = string.toLowerCase();
			final File textFile = this.txt,
					temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final IOException io) {
			System.out.println("deleteLn1stInstance_1() io exception");
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
	public void deleteLn1stInstance_2(String string) {
		try {
			string = string.toLowerCase();
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteLn1stInstance_2() io exception");
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
	public void deleteLnAllInstances_1(String string) {
		try {
			string = string.toLowerCase();
			final File textFile = this.txt,
					temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
			String line = reader.readLine();
			while (line != null) {
				if (!(line.toLowerCase().contains(string))) {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final IOException io) {
			System.out.println("deleteLnAllInstances_1() io exception");
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
	public void deleteLnAllInstances_2(String string) {
		try {
			string = string.toLowerCase();
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteLnAllInstances_2() io exception");
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
	public void deleteLnNthInstance_1(String string, final long n) {
		try {
			string = string.toLowerCase();
			final File textFile = this.txt,
					temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final IOException io) {
			System.out.println("deleteLnNthInstance_1() io exception");
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
	public void deleteLnNthInstance_2(String string, final long n) {
		try {
			string = string.toLowerCase();
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteLnNthInstance_2() io exception");
		}
	}

	/**
	 * a copy is created w/ the nth line being omitted
	 * 
	 * @param string
	 * @param n
	 * @author TelevisionNinja
	 */
	public void deleteNthLn_1(String string, final long n) {
		try {
			string = string.toLowerCase();
			final File textFile = this.txt,
					temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final IOException io) {
			System.out.println("deleteNthLn_1() io exception");
		}
	}

	/**
	 * a copy is created w/ the nth line being omitted
	 * 
	 * @param string
	 * @param n
	 * @author TelevisionNinja
	 */
	public void deleteNthLn_2(String string, final long n) {
		try {
			string = string.toLowerCase();
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("deleteNthLn_2() io exception");
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
		try (BufferedReader reader = new BufferedReader(new FileReader(this.txt))) {
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
		try (BufferedReader reader = new BufferedReader(new FileReader(this.txt))) {
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
		try (BufferedReader reader = new BufferedReader(new FileReader(this.txt))) {
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
	 * reads lines
	 * 
	 * @return number of lines
	 * @author TelevisionNinja
	 */
	public long getNumberOfLines() {
		long lineNum = 0;
		try {
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			String line = reader.readLine();
			while (line != null) {
				lineNum++;
				line = reader.readLine();
			}
			reader.close();
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
	 * uses get1stInstance()
	 * 
	 * if txt has the line
	 *     returns true
	 * else
	 *     returns false
	 * 
	 * @param string
	 * @return
	 * @author TelevisionNinja
	 */
	public Boolean hasLn1stInstance_1(final String string) {
		if (getLn1stInstance(string) == null) {
			return false;
		}
		return true;
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
	public Boolean hasLn1stInstance_2(String string) {
		try (BufferedReader reader = new BufferedReader(new FileReader(this.txt))) {
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
			System.out.println("hasLn1stInstance_2() io exception");
		}
		return false;
	}

	/**
	 * uses getNthInstance()
	 * 
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
	public Boolean hasLnNthInstance_1(final String string, final long n) {
		if (getLnNthInstance(string, n) == null) {
			return false;
		}
		return true;
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
	public Boolean hasLnNthInstance_2(String string, final long n) {
		try (BufferedReader reader = new BufferedReader(new FileReader(this.txt))) {
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
			System.out.println("hasLnNthInstance_2() io exception");
		}
		return false;
	}

	/**
	 * if txt has the nth line
	 *     returns true
	 * else
	 *     returns false
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public boolean hasNthLn(final long n) {
		try (BufferedReader reader = new BufferedReader(new FileReader(this.txt))) {
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
		try {
			final PrintWriter txtFile = new PrintWriter(new BufferedWriter(new FileWriter(this.txt, true)));
			txtFile.print(string);
			txtFile.close();
		}
		catch (final IOException io) {
			System.out.println("print() io exception");
		}
	}

	/**
	 * prints a string to the end of the txt & starts a new line
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void printLn(final String string) {
		try {
			final PrintWriter txtFile = new PrintWriter(new BufferedWriter(new FileWriter(this.txt, true)));
			txtFile.println(string);
			txtFile.close();
		}
		catch (final IOException io) {
			System.out.println("printLn() io exception");
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
	public void replaceLn1stInstance_1(String string, final String newString) {
		try {
			string = string.toLowerCase();
			final File textFile = this.txt,
					temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final IOException io) {
			System.out.println("replaceLn1stInstance_1() io exception");
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
	public void replaceLn1stInstance_2(String string, final String newString) {
		try {
			string = string.toLowerCase();
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("replaceLn1stInstance_2() io exception");
		}
	}

	/**
	 * if txt has any lines containing the old str
	 *     a copy is created w/ the all lines w/ old str being replaced with the new str
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param newString
	 * @author TelevisionNinja
	 */
	public void replaceLnAllInstances_1(String string, final String newString) {
		try {
			string = string.toLowerCase();
			final File textFile = this.txt,
					temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final IOException io) {
			System.out.println("replaceLnAllInstances_1() io exception");
		}
	}

	/**
	 * if txt has any lines containing the old str
	 *     a copy is created w/ the all lines w/ old str being replaced with the new str
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param newString
	 * @author TelevisionNinja
	 */
	public void replaceLnAllInstances_2(String string, final String newString) {
		try {
			string = string.toLowerCase();
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("replaceLnAllInstances_2() io exception");
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
	public void replaceLnNthInstance_1(String string, final String newString, final long n) {
		try {
			string = string.toLowerCase();
			final File textFile = this.txt,
					temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final IOException io) {
			System.out.println("replaceLnNthInstance_1() io exception");
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
	public void replaceLnNthInstance_2(String string, final String newString, final long n) {
		try {
			string = string.toLowerCase();
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("replaceLnNthInstance_2() io exception");
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
	public void replaceNthLn_1(String string, final long n) {
		try {
			string = string.toLowerCase();
			final File textFile = this.txt,
					temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(textFile));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			textFile.delete();
			temp.renameTo(textFile);
		}
		catch (final IOException io) {
			System.out.println("replaceNthLn_1() io exception");
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
	public void replaceNthLn_2(String string, final long n) {
		try {
			string = string.toLowerCase();
			final File temp = new File(this.txt.getAbsolutePath() + "temp");
			final BufferedReader reader = new BufferedReader(new FileReader(this.txt));
			final PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(temp)));
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
			temp.renameTo(this.txt);
		}
		catch (final IOException io) {
			System.out.println("replaceNthLn_2() io exception");
		}
	}

	/**
	 * 
	 * @param txt
	 * @author TelevisionNinja
	 */
	public void setTxt(final File txt) {
		if (FileName.getExtension_2(txt.getAbsolutePath()).equalsIgnoreCase(".txt")) {
			this.txt = txt;
		}
		else {
			System.out.println("setTxt() not a txt file");
		}
	}
}