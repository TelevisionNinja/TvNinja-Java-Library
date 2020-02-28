package televisionninja.lib.numberutils;

import televisionninja.lib.stringutils.StringUtils;

/**
 * @author TelevisionNinja
 *
 */
public class TwosComplement {
	/**
	 * 
	 * @param bi
	 *  	32 bit
	 * @return
	 * @author TelevisionNinja
	 */
	public static long binaryToDec_1(final String bi) {
		if (bi.charAt(0) == '1') {
			final String str = bi.substring(1, bi.length());
			final StringBuilder flipped = new StringBuilder();
			for (final char c : str.toCharArray()) {
				if (c == '0') {
					flipped.append("1");
				}
				else {
					flipped.append("0");
				}
			}
			return -(Long.parseLong(flipped.toString(), 2) + 1);
		}
		return BaseConversion.baseToDec_5(bi, 2);
	}

	/**
	 * 
	 * @param bi
	 *  	32 bit
	 * @return
	 * @author TelevisionNinja
	 */
	public static long binaryToDec_2(final String bi) {
		if (bi.charAt(0) == '1') {
			final String str = bi.substring(1, bi.length());
			return -(Long.parseLong(NumberUtils.invertBits_3(str).toString(), 2) + 1);
		}
		return BaseConversion.baseToDec_5(bi, 2);
	}

	/**
	 * 
	 * @param bi
	 * 		32 bit
	 * @return
	 * @author TelevisionNinja
	 */
	public static long binaryToDec_3(final String bi) {
		if (bi.charAt(0) == '1') {
			return -(Long.parseLong(NumberUtils.invertBits_3(bi.substring(1, bi.length())).toString(), 2) + 1);
		}
		return BaseConversion.baseToDec_5(bi, 2);
	}

	/**
	 * 
	 * @param bi
	 * 		64 bit
	 * @return
	 * @author TelevisionNinja
	 */
	public static long binaryToDec_4(final String bi) {
		if (bi.charAt(0) == '1') {
			return -(BaseConversion.baseToDec_5(NumberUtils.invertBits_2(bi.substring(1, bi.length())).toString(), 2) + 1);
		}
		return BaseConversion.baseToDec_5(bi, 2);
	}

	/**
	 * 
	 * @param dec
	 * @param bits
	 * @return
	 * @author TelevisionNinja
	 */
	public static String decToBinary_1(final long dec, final long bits) {
		if (dec < 0) {
			return StringUtils.addLeadingToString_2(NumberUtils.invertBits_3(BaseConversion.decToBinary(-dec - 1)), '1', bits);
		}
		return StringUtils.addLeadingToString_2(BaseConversion.decToBinary(dec), '0', bits);
	}

	/**
	 * max:
	 * 		bits: 55
	 * 		dec: 18014398509481982
	 * 		bi: 0111111111111111111111111111111111111111111111111111110
	 * min:
	 * 		dec: -18014398509481981
	 * 		bi: 1000000000000000000000000000000000000000000000000000011
	 * 
	 * @param dec
	 * @param bits
	 * @return
	 * @author TelevisionNinja
	 */
	public static String decToBinary_2(final long dec, final long bits) {
		if (dec < 0) {
			return StringUtils.addLeadingToString_2(NumberUtils.invertBits_2(BaseConversion.decToBase_2(-(dec + 1), 2)), '1', bits);
		}
		return StringUtils.addLeadingToString_2(BaseConversion.decToBase_2(dec, 2), '0', bits);
	}
}