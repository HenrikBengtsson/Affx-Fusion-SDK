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
public class ChromosomeSegmentDataEx extends ProbeSetMultiDataBase {

    /** The segment id */
    private UInt segmentId;
    public UInt getSegmentId() { return segmentId; }
    public void setSegmentId(UInt s) { segmentId = s; }

    /** The reference sample key. */
    private UInt referenceSampleKey;
    public UInt getReferenceSampleKey() { return referenceSampleKey; }
    public void setReferenceSampleKey(UInt k) { referenceSampleKey = k; }

    /** The familial sample key. */
    private UInt familialSampleKey;
    public UInt getFamilialSampleKey() { return familialSampleKey; }
    public void setFamilialSampleKey(UInt k) { familialSampleKey = k; }

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

    /** The segment call */
    private UByte call;
    public UByte getCall() { return call; }
    public void setCall(UByte c) { call = c; }

    /** The confidence in the call */
    private float confidence;
    public float getConfidence() { return confidence; }
    public void setConfidence(float c) { confidence = c; }
    
    /** The number of markers in the segment. */
    private int markerCount;
    public int getMarkerCount() { return markerCount; }
    public void setMarkerCount(int c) { markerCount = c; }

    /** The homozygosity */
    private float homozygosity;
    public float getHomozygosity() { return homozygosity; }
    public void setHomozygosity(float h) { homozygosity = h; }

    /** The heterozygosity */
    private float heterozygosity;
    public float getHeterozygosity() { return heterozygosity; }
    public void setHeterozygosity(float h) { heterozygosity = h; }
    
    /** Construct an empty class */
    public ChromosomeSegmentDataEx() {
        segmentId = UInt.ZERO;
        referenceSampleKey = UInt.ZERO;
        familialSampleKey = UInt.ZERO;
        chr = UByte.ZERO;
        startPosition = UInt.ZERO;
        stopPosition = UInt.ZERO;
        call = UByte.ZERO;
        confidence = 0.0f;
        markerCount = 0;
        homozygosity = 0.0f;
        heterozygosity = 0.0f;
    } 
}
