/////////////////////////////////////////////////////////////////
//
// Copyright (C) 2009 Affymetrix, Inc.
//
// This library is free software; you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License,
// or (at your option) any later version.
//
// This library is distributed in the hope that it will be useful, but
// WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
// or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License
// for more details.
//
// You should have received a copy of the GNU Lesser General Public License
// along with this library; if not, write to the Free Software Foundation, Inc.,
// 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA 
//
/////////////////////////////////////////////////////////////////

package affymetrix.calvin.utils;

public class IOUtils {

	public static final String EMPTY = "";

	public static final String UNICODE_CHARSET = "UTF-16BE";

	public static final String ASCII_CHARSET = "US-ASCII";

	private IOUtils() {
	}

	public static boolean isNullOrEmpty(String s) {
		if ((s == null) || (s.length() == 0)) {
			return true;
		}
		return false;
	}

	public static String getVal(String val) {
		return getVal(val, true);
	}

	public static String getVal(String val, boolean trim) {
		return getVal(val, EMPTY, trim);
	}

	public static String getVal(String val, String defaultVal, boolean trim) {
		if (isNullOrEmpty(val)) {
			return defaultVal;
		}
		if (trim) {
			return val.trim();
		}
		return val;
	}

}
