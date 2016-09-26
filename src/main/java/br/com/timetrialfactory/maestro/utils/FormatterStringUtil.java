package br.com.timetrialfactory.maestro.utils;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;

public class FormatterStringUtil {

	private FormatterStringUtil() {

	}

	public static String formatToUTF8(String string) {
		if (string != null) {
			byte ptext[] = string.getBytes(ISO_8859_1);
			return new String(ptext, UTF_8);
		}
		return string;
	}

}
