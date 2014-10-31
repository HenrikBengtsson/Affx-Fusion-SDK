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
public class MarkerABSignals {

	/** The index to the probe set ids in the main data set. */
	private UInt index;

    /** Get the index value */
    public UInt getIndex() { return index; }

    /** Sets the index value. */
    public void setIndex(UInt i) { index = i; }

	/** The A signal. */
	private float aSignal;

    /** Gets the a signal. */
    public float getASignal() { return aSignal; }

    /** Sets the a signal. */
    public void setASignal(float s) { aSignal = s; }

	/** The B signal. */
	private float bSignal;

    /** Gets the b signal. */
    public float getBSignal() { return bSignal; }
    
    /** Sets the b signal. */
    public void setBSignal(float s) { bSignal = s; }

	/*! The SCAR value. */
	private float scar;

    /** Gets the scar. */
    public float getScar() { return scar; }

    /** Sets the scar. */
    public void setScar(float s) { scar = s; }

    /** Construct a new class */
    public MarkerABSignals() {
        index = UInt.ZERO;
        aSignal = 0.0f;
        bSignal = 0.0f;
        scar = 0.0f;
    }
}
