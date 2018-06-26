package com.test.string.string;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class Decode {

	public static String get(String raw) throws UnsupportedEncodingException {
		String aaa;
		aaa = URLDecoder.decode(raw, "UTF-8");
		return aaa;

	}
}
