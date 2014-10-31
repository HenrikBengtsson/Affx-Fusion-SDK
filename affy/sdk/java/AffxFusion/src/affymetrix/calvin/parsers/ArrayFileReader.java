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

package affymetrix.calvin.parsers;

import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import affymetrix.calvin.array.ArrayData;
import affymetrix.calvin.exception.SAXArrayStopParsingException;
import affymetrix.calvin.utils.AffymetrixGuidType;

public class ArrayFileReader {
	public ArrayFileReader() {
	}

	public boolean read(String fileName, ArrayData arrayData) {
		return read(fileName, arrayData, false);
	}

	/**
	 * Reads the entire ARR file or just the header using the XML SAX parser.
	 * 
	 * @param fileName		the fully qualified name of the ARR file.
	 * @param arrayData		the ArrayData object that will be populated with data from the ARR file.
	 * @param headerOnly	if false the whole file is read; if true only the header is read.
	 * 
	 * @return	true if successfully read; false otherwise.
	 */
	boolean read(String fileName, ArrayData arrayData, boolean headerOnly) {
		arrayData.clear();

		boolean status = false;
		XMLReader reader = null;
		try {
			reader = XMLReaderFactory.createXMLReader();
		}
		catch (SAXException se) {
			se.printStackTrace();
		}
		SAXArrayHandler handler = new SAXArrayHandler(arrayData, headerOnly);
		reader.setContentHandler(handler);

		try {
			reader.parse(fileName);
			status = true;
		}
		catch (SAXArrayStopParsingException se) {
			status = true;
		}
		catch (Exception e) {
			e.printStackTrace();
			status = false;
		}
		return status;
	}

	/**
	 * Check if the data type matches what is in the file.
	 */
	public static boolean isFileType(String fileName, AffymetrixGuidType dataTypeId) {
		return (getDataTypeIdentifier(fileName).equals(dataTypeId));
	}

	/**
	 * Read just the first few entries to determine if this file is of the right type. Check the magic number, version
	 * number and data type identifier. If they all match then this is the right type of file.
	 */
	public static AffymetrixGuidType getDataTypeIdentifier(String fileName) {
		ArrayFileReader reader = new ArrayFileReader();
		ArrayData arrayData = new ArrayData();
		reader.read(fileName, arrayData, true);
		return arrayData.getDataTypeIdentifier();
	}
}
