package televisionninja.lib.stringutils;

import java.io.File;

/**
 * @author TelevisionNinja
 *
 */
public class FileName {
	/**
	 * gets file extension
	 * 
	 * @param fileName
	 * @return
	 * 		file extension
	 * @author TelevisionNinja
	 */
	public static String getExtension_1(final String fileName) {
		final String[] fileNameArr = fileName.split("\\.");
		return "." + fileNameArr[fileNameArr.length - 1];
	}

	/**
	 * 
	 * @param fileName
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getExtension_2(final String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * substring()
	 * 
	 * @param path
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getPath_1(final String path) {
		return path.substring(0, path.lastIndexOf(File.separator));
	}

	/**
	 * File.getParent()
	 * 
	 * @param file
	 * @return
	 * @author TelevisionNinja
	 */
	public static String getPath_2(final File file) {
		return file.getParent();
	}

	/**
	 * removes file extension
	 * 
	 * @param fileName
	 * @return
	 * @author TelevisionNinja
	 */
	public static String removeExtension_1(final String fileName) {
		final String[] fileNameArr = fileName.split("\\.");
		final StringBuilder removed = new StringBuilder();
		final int length = fileNameArr.length;
		for (int y = 0; y < length - 1; y++) {
			removed.append(fileNameArr[y]);
			if (y != length - 2) {
				removed.append(".");
			}
		}
		return removed.toString();
	}

	/**
	 * substring()
	 * 
	 * @param fileName
	 * @return
	 * @author TelevisionNinja
	 */
	public static String removeExtension_2(final String fileName) {
		return fileName.substring(0, fileName.lastIndexOf("."));
	}

	/**
	 * 
	 * @param name
	 * @param replacement
	 * 		-".replacement"
	 * @return
	 * @author TelevisionNinja
	 */
	public static String replaceExtension(final String name, final String replacement) {
		return removeExtension_2(name) + replacement;
	}
}