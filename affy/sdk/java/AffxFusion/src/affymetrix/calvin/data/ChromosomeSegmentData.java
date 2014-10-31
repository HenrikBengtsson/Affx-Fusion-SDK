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

/** This provides types to store results for a segment.
 */
public class ChromosomeSegmentData extends ProbeSetMultiDataBase {

    /** The segment id */
    private UInt segmentId;
    public UInt getSegmentId() { return segmentId; }
    public void setSegmentId(UInt s) { segmentId = s; }
    
    /** The chromosome number. */
    private UByte chr;
    public UByte getChr() { return chr; }
    public void setChr(UByte c) { chr = c; }

    /** The start position on the chromosome. */
    private UInt startPosition;
    public UInt getStartPosition() { return startPosition; }
    public void setStartPosition(UInt p) { startPosition = p; }

    /** The stop position on the chromosome. */
    private UInt stopPosition;
    public UInt getStopPosition() { return stopPosition; }
    public void setStopPosition(UInt p) { stopPosition = p; }

    /** The number of markers in the segment. */
    private int markerCount;
    public int getMarkerCount() { return markerCount; }
    public void setMarkerCount(int c) { markerCount = c; }

    /** The mean marker distance. */
    private UInt meanMarkerDistance;
    public UInt getMeanMarkerDistance() { return meanMarkerDistance; }
    public void setMeanMarkerDistance(UInt d) { meanMarkerDistance = d; }
    
    /** Construct an empty class */
    public ChromosomeSegmentData() {
        segmentId = UInt.ZERO;
        chr = UByte.ZERO;
        startPosition = UInt.ZERO;
        stopPosition = UInt.ZERO;
        meanMarkerDistance = UInt.ZERO;
        markerCount = 0;
    } 
}
