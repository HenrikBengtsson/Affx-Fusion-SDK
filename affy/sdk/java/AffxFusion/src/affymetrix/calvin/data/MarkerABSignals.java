////////////////////////////////////////////////////////////////
//
// Copyright (C) 2009 Affymetrix, Inc.
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

/** Holds the marker AB signal results */
public class MarkerABSignals extends ProbeSetMultiDataBase {

	/** The index to the probe set ids in the main data set. */
	private UInt index;

    /** Get the index value */
    public UInt getIndex() { return index; }

    /** Sets the index value. */
    public void setIndex(UInt i) { index = i; }

    /** Construct a new class */
    public MarkerABSignals() {
        index = UInt.ZERO;
    }
}
