////////////////////////////////////////////////////////////////
//
// Copyright (C) 2008 Affymetrix, Inc.
//
// This library is free software; you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License 
// (version 2.1) as published by the Free Software Foundation.
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
////////////////////////////////////////////////////////////////

package affymetrix.calvin.data;

import affymetrix.portability.UInt;

/** Stores data for a allele peaks result. */
public class AllelePeaks extends ProbeSetMultiDataBase {

	/** The name of the probe set. */
	private String name;

	/** The chromosome. */
	private byte chr;

	/** The genomic position. */
	private UInt position;

	/** Creates a new instance of AllelePeaks */
	public AllelePeaks() {
		name = null;
		chr = 0;
		position = UInt.ZERO;
	}

	/** The name of the probe set. */
	public String getName() {
		return name;
	}

	/** Set the name */
	public void setName(String n) {
		name = n;
	}

	/** Get the chromosome. */
	public byte getChr() {
		return chr;
	}

	/** Set the chromosome. */
	public void setChr(byte c) {
		chr = c;
	}

	/** Get the position. */
	public UInt getPosition() {
		return position;
	}

	/** Set the position */
	public void setPosition(UInt p) {
		position = p;
	}

}
