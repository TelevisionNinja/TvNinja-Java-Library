package televisionninja.lib.fileutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import televisionninja.lib.stringutils.FileName;

/**
 * This version allows any file that doesn't have the .csv extenstion to be used with this class
 * 
 * @author TelevisionNinja
 *
 */
public class CSV2 {
	/**
	 * online method
	 * 
	 * @param cvsLine
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> parseLine(final String cvsLine) {
		return parseLine(cvsLine, ',', '"');
	}

	/**
	 * online method
	 * 
	 * @param cvsLine
	 * @param separator
	 * @param customQuote
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> parseLine(final String cvsLine, char separator, char customQuote) {
		final List<String> result = new ArrayList<>();

		//if empty, return
		if (cvsLine == null) {
			return result;
		}

		if (customQuote == ' ') {
			customQuote = '"';
		}

		if (separator == ' ') {
			separator = ',';
		}

		final StringBuilder curVal = new StringBuilder();
		boolean inQuotes = false,
				startCollectChar = false,
				doubleQuotesInColumn = false;

		final char[] chars = cvsLine.toCharArray();

		for (final char ch : chars) {
			if (inQuotes) {
				startCollectChar = true;
				if (ch == customQuote) {
					inQuotes = false;
					doubleQuotesInColumn = false;
				}
				else {
					if (ch == '\"') {
						if (!doubleQuotesInColumn) {
							curVal.append(ch);
							doubleQuotesInColumn = true;
						}
					}
					else {
						curVal.append(ch);
					}
				}
			}
			else {
				if (ch == customQuote) {
					inQuotes = true;
					if (chars[0] != '"' && customQuote == '\"') {
						curVal.append('"');
					}
					//double quotes in column will hit this
					if (startCollectChar) {
						curVal.append('"');
					}
				}
				else if (ch == separator) {
					result.add(curVal.toString());
					curVal.setLength(0);
					startCollectChar = false;
				}
				else if (ch == '\r') {
					//ignore LF characters
					continue;
				}
				else if (ch == '\n') {
					//the end, break
					break;
				}
				else {
					curVal.append(ch);
				}
			}
		}
		result.add(curVal.toString());
		return result;
	}

	/**
	 * online method
	 * 
	 * @param values
	 * @return
	 */
	public static String parseToCSV(final List<String> values) {
		return parseToCSV(values, ',', '"');
	}

	/**
	 * online method
	 * 
	 * @param values
	 * @param separator
	 * @param customQuote
	 * @return
	 */
	public static String parseToCSV(final List<String> values, char separator, char customQuote) {
		if (customQuote == ' ') {
			customQuote = new CSV2().getQuote();
		}

		if (separator == ' ') {
			separator = new CSV2().getSeparator();
		}

		boolean firstVal = true;
		final StringBuilder line = new StringBuilder();
		for (final String val : values)  {
			if (!firstVal) {
				line.append(separator);
			}
			line.append(customQuote);
			for (int i = 0; i < val.length(); i++) {
				final char ch = val.charAt(i);
				if (ch == customQuote) {
					line.append(customQuote); //extra quote
				}
				line.append(ch);
			}
			line.append(customQuote);
			firstVal = false;
		}
		return line.toString();
	}

	private char separator = ',',
			quote = '"';

	/**
	 * @author TelevisionNinja
	 */
	private File csv,
	temp;

	public CSV2() {

	}

	/**
	 * creates CSV file
	 * @author TelevisionNinja
	 */
	public CSV2(final File csv) {
		this.csv = csv;
		refreshTemp();
	}

	/**
	 * prints aan entry to the end of the csv
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void addEntry(final List<String> string) {
		final PrintWriter csv = FileUtils.createPrintWriter(this.csv, true);
		csv.print(parseToCSV(string));
		csv.close();
	}

	/**
	 * deletes the original csv file and creates a blank csv file
	 * 
	 * @author TelevisionNinja
	 */
	public void clearCSV() {
		deleteCSV();
		createCSV();
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	public void createCSV() {
		try {
			if (this.csv.createNewFile()) {
				System.out.println(this.csv + " created");
			}
			else {
				System.out.println(this.csv + " already exists");
			}
		}
		catch (final IOException e) {
			System.out.println("createCSV() io exception");
		}
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	private BufferedReader createReader() {
		return FileUtils.createBufferedReader(this.csv);
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
	 * list of unparsed CSV lines
	 * 
	 * @return number of lines
	 * @author TelevisionNinja
	 */
	public List<String> CSVToList() {
		final List<String> list = new ArrayList<>();
		try (BufferedReader reader = createReader()) {
			String line = reader.readLine();
			while (line != null) {
				list.add(line);
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("CSVToList() io exception");
		}
		return list;
	}

	/**
	 * table of csv values
	 * 
	 * @return number of lines
	 * @author TelevisionNinja
	 */
	public List<List<String>> CSVToTable() {
		final List<List<String>> arr = new ArrayList<>();
		try (BufferedReader reader = createReader()) {
			String line = reader.readLine();
			while (line != null) {
				arr.add(parseLine(line));
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("CSVToTable() io exception");
		}
		return arr;
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	public void deleteCSV() {
		if (this.csv.delete()) {
			System.out.println(this.csv + " deleted");
		}
		else {
			System.out.println(this.csv + " not deleted");
		}
	}

	/**
	 * if csv has the line (1st instance)
	 *     a copy is created w/ the line (1st instance) being omitted
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void deleteEntry1stInstance(String string) {
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
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("deleteEntry1stInstance() io exception");
		}
	}

	/**
	 * if csv has any lines containing the str
	 *     a copy is created w/ the all lines w/ str being omitted
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @author TelevisionNinja
	 */
	public void deleteEntryAllInstances(String string) {
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
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("deleteEntryAllInstances() io exception");
		}
	}

	/**
	 * if csv has the entry (nth instance)
	 *     a copy is created w/ the entry (nth instance) being omitted
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param n
	 * @author TelevisionNinja
	 */
	public void deleteEntryNthInstance(String string, final long n) {
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
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("deleteEntryNthInstance() io exception");
		}
	}

	/**
	 * makes a copy w/o the last entry
	 * 
	 * @author TelevisionNinja
	 */
	public void deleteLastEntry() {
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
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("deleteLastEntry() io exception");
		}
	}

	/**
	 * a copy is created w/ the nth entry being omitted
	 * 
	 * @param string
	 * @param n
	 * @author TelevisionNinja
	 */
	public void deleteNthEntry(String string, final long n) {
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
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("deleteNthEntry() io exception");
		}
	}

	/**
	 * if csv has entries containing the string
	 *     returns a list of the entries
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
	 * specified column of csv values
	 * 
	 * @return number of lines
	 * @author TelevisionNinja
	 */
	public List<String> getColumn(final int index) {
		final List<String> arr = new ArrayList<>();
		try (BufferedReader reader = createReader()) {
			String line = reader.readLine();
			while (line != null) {
				arr.add(parseLine(line).get(index));
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("CSVToTable() io exception");
		}
		return arr;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public File getCSV() {
		return this.csv;
	}

	/**
	 * if csv has the entry (1st instance)
	 *     returns the entry
	 * else
	 *     returns null
	 * 
	 * @param string
	 * @return
	 * @author TelevisionNinja
	 */
	public String getEntry1stInstance(String string) {
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
			System.out.println("getEntry1stInstance() io exception");
		}
		return null;
	}

	/**
	 * if csv has the entry (nth instance)
	 *     returns the entry
	 * else
	 *     returns null
	 * 
	 * @param string
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public String getEntryNthInstance(String string, final long n) {
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
			System.out.println("getEntryNthInstance() io exception");
		}
		return null;
	}

	/**
	 * if csv has the nth entry
	 *     returns the entry
	 * else
	 *     returns null
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public String getNthEntry(final long n) {
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
			System.out.println("getNthEntry() io exception");
		}
		return null;
	}

	/**
	 * returns number of columns
	 * 
	 * @return number of columns
	 * @author TelevisionNinja
	 */
	public long getNumberOfColumns() {
		try {
			return parseLine(createReader().readLine()).size();
		}
		catch (final IOException io) {
			System.out.println("getNumberOfEntries() io exception");
		}
		return 0;
	}

	/**
	 * reads entries
	 * 
	 * @return number of entries
	 * @author TelevisionNinja
	 */
	public long getNumberOfEntries() {
		long lineNum = 0;
		try (BufferedReader reader = createReader()) {
			String line = reader.readLine();
			while (line != null) {
				lineNum++;
				line = reader.readLine();
			}
		}
		catch (final IOException io) {
			System.out.println("getNumberOfEntries() io exception");
		}
		return lineNum;
	}

	/**
	 * 
	 * @param string
	 * @return
	 * 		number of instances of the string in the csv file
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
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public char getQuote() {
		return this.quote;
	}

	/**
	 * 
	 * @return
	 * @author TelevisionNinja
	 */
	public char getSeparator() {
		return this.separator;
	}

	/**
	 * if csv has the line
	 *     returns true
	 * else
	 *     returns false
	 * 
	 * @param string
	 * @return
	 * @author TelevisionNinja
	 */
	public Boolean hasEntry1stInstance(String string) {
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
			System.out.println("hasEntry1stInstance() io exception");
		}
		return false;
	}

	/**
	 * if csv has the entry (nth instance)
	 *     returns true
	 * else
	 *     returns false
	 * 
	 * @param string
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public Boolean hasEntryNthInstance(String string, final long n) {
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
			System.out.println("hasEntryNthInstance() io exception");
		}
		return false;
	}

	/**
	 * if csv has an nth entry
	 *     returns true
	 * else
	 *     returns false
	 * 
	 * @param n
	 * @return
	 * @author TelevisionNinja
	 */
	public boolean hasNthEntry(final long n) {
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
			System.out.println("hasNthEntry() io exception");
		}
		return false;
	}

	/**
	 * 
	 * 
	 * @author TelevisionNinja
	 */
	private void refreshTemp() {
		this.temp = new File(this.csv.getAbsolutePath() + "temp");
	}

	/**
	 * 
	 * @param newName
	 * 		- input only the file name w/ the extension
	 * @author TelevisionNinja
	 */
	public void rename(final String newName) {
		final File renamed = new File(this.csv.getParent() + newName);
		if (renamed.exists()) {
			System.out.println(renamed + " already exists");
		}
		else {
			this.csv.renameTo(renamed);
			refreshTemp();
		}
	}

	/**
	 * if csv has the entry (1st instance)
	 *     a copy is created w/ the entry (1st instance) being replaced with the new entry
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param newEntry
	 * @author TelevisionNinja
	 */
	public void replaceEntry1stInstance(String string, final List<String> newEntry) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			boolean check = true;
			while (line != null) {
				if (check && line.toLowerCase().contains(string)) {
					writer.println(parseToCSV(newEntry));
					check = false;
				}
				else {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("replaceEntry1stInstance() io exception");
		}
	}

	/**
	 * if csv has any entries containing the old str
	 *     a copy is created w/ all entries w/ old str being replaced with the new str
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param newEntry
	 * @author TelevisionNinja
	 */
	public void replaceEntryAllInstances(String string, final List<String> newEntry) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					writer.println(parseToCSV(newEntry));
				}
				else {
					writer.println(line);
				}
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("replaceEntryAllInstances() io exception");
		}
	}

	/**
	 * if csv has the entry (nth instance)
	 *     a copy is created w/ the entry (nth instance) being replaced with the new entry
	 * else
	 *     makes an exact copy
	 * 
	 * @param string
	 * @param newEntry
	 * @param n
	 * @author TelevisionNinja
	 */
	public void replaceEntryNthInstance(String string, final List<String> newEntry, final long n) {
		try {
			string = string.toLowerCase();
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			long instance = 1;
			while (line != null) {
				if (line.toLowerCase().contains(string)) {
					if (instance == n) {
						writer.println(parseToCSV(newEntry));
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
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("replaceEntryNthInstance() io exception");
		}
	}

	/**
	 * if csv has the nth entry
	 *     a copy is created w/ the nth entry being replaced with the new entry
	 * else
	 *     makes an exact copy
	 * 
	 * @param newEntry
	 * @param n
	 * @author TelevisionNinja
	 */
	public void replaceNthEntry(final List<String> newEntry, final long n) {
		try {
			final BufferedReader reader = createReader();
			final PrintWriter writer = createWriter();
			String line = reader.readLine();
			long lineNum = 1;
			while (line != null) {
				if (lineNum == n) {
					writer.println(parseToCSV(newEntry));
				}
				else {
					writer.println(line);
				}
				lineNum++;
				line = reader.readLine();
			}
			reader.close();
			writer.close();
			this.csv.delete();
			this.temp.renameTo(this.csv);
		}
		catch (final IOException io) {
			System.out.println("replaceNthEntry() io exception");
		}
	}

	/**
	 * 
	 * @param csv
	 * @author TelevisionNinja
	 */
	public void setCSV(final File csv) {
		this.csv = csv;
		refreshTemp();
	}

	/**
	 * 
	 * @param q
	 * @author TelevisionNinja
	 */
	public void setQuote(final char q) {
		this.quote = q;
	}

	/**
	 * 
	 * @param s
	 * @author TelevisionNinja
	 */
	public void setSeparator(final char s) {
		this.separator = s;
	}
}