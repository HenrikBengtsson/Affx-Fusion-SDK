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

/*
 * MIMEValue.java
 *
 * Created on October 20, 2005, 4:11 PM
 *
 * To change this template, choose Tools | Options and locate the template under
 * the Source Creation and Management node. Right-click the template and choose
 * Open. You can then make changes to the template in the Source Editor.
 */

package affymetrix.calvin.parameter;

/**
 * 
 * @author ljevon
 */
public class MIMEValue {

	/** Custom MIME types */
	public static final String Int8MIMEType = "text/x-calvin-integer-8";

	public static final String UInt8MIMEType = "text/x-calvin-unsigned-integer-8";

	public static final String Int16MIMEType = "text/x-calvin-integer-16";

	public static final String UInt16MIMEType = "text/x-calvin-unsigned-integer-16";

	public static final String Int32MIMEType = "text/x-calvin-integer-32";

	public static final String UInt32MIMEType = "text/x-calvin-unsigned-integer-32";

	public static final String FloatMIMEType = "text/x-calvin-float";

	public static final String TextMIMEType = "text/plain";

	public static final String AsciiMIMEType = "text/ascii";

	/** Type table. Order matches the ParameterType enum */
	public static final String[] TYPE_TABLE = { Int8MIMEType, UInt8MIMEType, Int16MIMEType, UInt16MIMEType,
			Int32MIMEType, UInt32MIMEType, FloatMIMEType, TextMIMEType, AsciiMIMEType };

	/** The buffer to hold the data. */
	private byte[] buf = null;

	/** Constructs a MIME value - default constructor */
	public MIMEValue() {
	}

	/**
	 * Constructs a MIME value
	 */
	public MIMEValue(byte[] value) {
		setValue(value);
	}

	/**
	 * Operator equals
	 * 
	 * @param lhs
	 *          The left hand side to compare.
	 * @return True if the same.
	 */
	public boolean equals(MIMEValue lhs) {
		if (size() != lhs.size()) {
			return false;
		}
		return (getBytes() == lhs.getBytes());
	}

	/**
	 * Set the controlled value
	 * 
	 * @param value A pointer to array containing the MIME encoded value
	 */
	public void setValue(byte[] value) {
		buf = value;
	}

	/**
	 * Get the controlled value
	 * 
	 * @return Const pointer to the MIME value array. (for writing)
	 */
	public byte[] getBytes() {
		return buf;
	}

	/** Gets the size of the array */
	public int size() {
		if (buf == null) {
			return 0;
		}
		return buf.length;
	}
}
