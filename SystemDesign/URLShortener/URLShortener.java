package SystemDesign.URLShortener;

import java.util.concurrent.ThreadLocalRandom;

/**
 * @author ssadasivan
 * @since 4/3/2017.
 */
public class URLShortener {
	private int base;
	private static int min = 0;
	private static int max = 61;

	//This array is a lookup table that translates positive integer index values into their "Base62 Alphabet" equivalents as specified in
	private static final char[] toBase62 = {
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
			'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
	};

	private URLShortener(int base) {
		this.base = base;
	}

	public static String createShortURL(String longURL) {
		URLShortener urlShortener = new URLShortener(toBase62.length);
		//hash long url string into a number and convert number to short url.
		return urlShortener.convertToBase62(Math.abs(longURL.hashCode()));
	}

	/**
	 * Generate a 6 letter string based on base operated on hash code.
	 * @param hashCode
	 * @return
	 */
	private String convertToBase62(int hashCode) {
		StringBuilder resultShortURL = new StringBuilder();
		while (hashCode > 0) {
			int digit = hashCode % base;
			resultShortURL.append(toBase62[digit]);
			hashCode /= base;
		}
		while (resultShortURL.length() < 6)
			resultShortURL.append(toBase62[ThreadLocalRandom.current().nextInt(min, max)]);
		return resultShortURL.reverse().toString();
	}
}
