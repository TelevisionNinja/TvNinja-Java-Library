package televisionninja.lib.fileutils.convert;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import televisionninja.lib.fileutils.FileUtils;
import televisionninja.lib.fileutils.Txt;
import televisionninja.lib.numberutils.BaseConversion;
import televisionninja.lib.stringutils.FileName;

/**
 * old, don't use
 * 
 * @author TelevisionNinja
 *
 */
public class FileListConversion {
	/**
	 * output name removes the .txt extension
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 * @param fileName
	 * @author TelevisionNinja
	 */
	public static void base32TxtToFile(final String inputDirectory, final String outputDirectory, final String fileName) {
		try {
			final Scanner sc = new Scanner(new FileInputStream(inputDirectory + fileName));
			final FileOutputStream outputStream = new FileOutputStream(outputDirectory + FileName.removeExtension_2(fileName));
			while (sc.hasNextLine()) {
				outputStream.write((int) BaseConversion.baseToDec_5(sc.nextLine(), 32));
			}
			sc.close();
			outputStream.close();
			System.out.println("base32TxtToFile() done");
			FileUtils.deleteFile(new File(inputDirectory + fileName));
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("base32TxtToFile() file not found");
		}
		catch (final IOException io) {
			System.out.println("base32TxtToFile() io exception");
		}
	}

	/**
	 * output name is file name.orignial extension.txt
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 * @param fileName
	 * @author TelevisionNinja
	 */
	public static void fileToBase32Txt(final String inputDirectory, final String outputDirectory, final String fileName) {
		try {
			final FileInputStream inputStream = new FileInputStream(inputDirectory + fileName);
			final Txt txt = new Txt(outputDirectory, fileName);
			txt.create();
			for (int x; (x = inputStream.read()) != -1;) {
				txt.writeLn(BaseConversion.decToBase_2(x, 32));
			}
			inputStream.close();
			txt.close();
			System.out.println("fileToBase32Txt() done");
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("fileToBase32Txt() file not found");
		}
		catch (final IOException io) {
			System.out.println("fileToBase32Txt() io exception");
		}
	}


	/**
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> fileToBinaryList(final String file) {
		final List<String> binaryList = new ArrayList<>();

		try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
			for (int x; (x= inputStream.read()) != -1;) {
				binaryList.add(Integer.toBinaryString(x));
			}
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("fileToBinaryList() file not found");
		}
		catch (final IOException io) {
			System.out.println("fileToBinaryList() io exception");
		}

		return binaryList;
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> fileToDecimalList(final String file) {
		final List<String> binaryList = new ArrayList<>();

		try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
			for (int x; (x= inputStream.read()) != -1;) {
				binaryList.add(Integer.toString(x));
			}
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("fileToDecimalList() file not found");
		}
		catch (final IOException io) {
			System.out.println("fileToDecimalList() io exception");
		}

		return binaryList;
	}

	/**
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static List<String> fileToHexList(final String file) {
		final List<String> hexList = new ArrayList<>();

		try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
			for (int x; (x = inputStream.read()) != -1;) {
				hexList.add(Integer.toHexString(x));
			}
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("fileToHexList() file not found");
		}
		catch (final IOException io) {
			System.out.println("fileToHexList() io exception");
		}

		return hexList;
	}

	/**
	 * output name is file name.orignial extension.txt
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 * @param fileName
	 * @author TelevisionNinja
	 */
	public static void fileToHexTxt(final String inputDirectory, final String outputDirectory, final String fileName) {
		try {
			final FileInputStream inputStream = new FileInputStream(inputDirectory + fileName);
			final Txt txt = new Txt(outputDirectory, fileName);
			txt.create();
			for (int x; (x = inputStream.read()) != -1;) {
				txt.writeLn(Integer.toHexString(x));
			}
			inputStream.close();
			txt.close();
			System.out.println("fileToHexTxt() done");
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("fileToHexTxt() file not found");
		}
		catch (final IOException io) {
			System.out.println("fileToHexTxt() io exception");
		}
	}



	/**
	 * output name removes the .txt extension
	 * 
	 * @param inputDirectory
	 * @param outputDirectory
	 * @param fileName
	 * @author TelevisionNinja
	 */
	public static void hexTxtToFile(final String inputDirectory, final String outputDirectory, final String fileName) {
		try {
			final Scanner sc = new Scanner(new FileInputStream(inputDirectory + fileName));
			final FileOutputStream outputStream = new FileOutputStream(outputDirectory + FileName.removeExtension_2(fileName));
			while (sc.hasNextLine()) {
				outputStream.write(Integer.parseInt(sc.nextLine(), 16));
			}
			sc.close();
			outputStream.close();
			System.out.println("hexTxtToFile() done");
			FileUtils.deleteFile(new File(inputDirectory + fileName));
		}
		catch (final FileNotFoundException noFile) {
			System.out.println("hexTxtToFile() file not found");
		}
		catch (final IOException io) {
			System.out.println("hexTxtToFile() io exception");
		}
	}

	/**
	 * 
	 * @param list
	 * @param base
	 * 		base of data
	 * @param fileDirectory
	 * @author TelevisionNinja
	 */
	public static void listToFile(final List<String> list, final int base, final String fileDirectory) {
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fileDirectory))) {
			for (final String string : list) {
				out.write(Integer.parseInt(string, base));
			}
			out.close();
			System.out.println("listToFile() done");
		} catch (final FileNotFoundException noFile) {
			System.out.println("listToFile() file not found");
		} catch (final IOException io) {
			System.out.println("listToFile() io exception");
		}
	}
}