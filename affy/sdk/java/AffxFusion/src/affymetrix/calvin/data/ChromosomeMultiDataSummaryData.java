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

import affymetrix.portability.*;

/** This class provides types to hold chromosome multi data results.
 */
public class ChromosomeMultiDataSummaryData extends ProbeSetMultiDataBase {

    /** The chromosome number. */
    private UByte chr;
    public UByte getChr() { return chr; }
    public void setChr(UByte c) { chr = c; }

    /** The chromosome display. */
    private String display;
    public String getDisplay() { return display; }
    public void setDisplay(String s) { display = s; }

    /** The starting index */
    private UInt startIndex;
    public UInt getStartIndex() { return startIndex; }
    public void setStartIndex(UInt s) { startIndex = s; }

    /** The number of markers */
    private UInt markerCount;
    public UInt getMarkerCount() { return markerCount; }
    public void setMarkerCount(UInt c) { markerCount = c; }

    /** The minimum signal */
    private float minSignal;
    public float getMinSignal() { return minSignal; }
    public void setMinSignal(float s) { minSignal = s; }

    /** The maximum signal */
    private float maxSignal;
    public float getMaxSignal() { return maxSignal; }
    public void setMaxSignal(float s) { maxSignal = s; }

    /** The median copy number state */
    private float medianCnState;
    public float getMedianCnState() { return medianCnState; }
    public void setMedianCnState(float s) { medianCnState = s; }

    /** The hom frequency */
    private float homFrequency;
    public float getHomFrequency() { return homFrequency; }
    public void setHomFrequency(float h) { homFrequency = h; }

    /** The het frequency */
    private float hetFrequency;
    public float getHetFrequency() { return hetFrequency; }
    public void setHetFrequency(float h) { hetFrequency = h; }

    public ChromosomeMultiDataSummaryData() {
        chr = UByte.ZERO;
        display = "";
        startIndex = UInt.ZERO;
        markerCount = UInt.ZERO;
        minSignal = 0.0f;
        maxSignal = 0.0f;
        medianCnState = 0.0f;
        homFrequency = 0.0f;
        hetFrequency = 0.0f;
    }

}
