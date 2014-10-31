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

/** Stores the segment overlap from a familial file. */
public class FamilialSegmentOverlap {

    public FamilialSegmentOverlap()
    {
        segmentType = null;
        referenceSampleKey = UInt.ZERO;
        referenceSegmentID = null;
        familialSampleKey = UInt.ZERO;
        familialSegmentID = null;
    }
    
    /** The type of segment; the name of the data set in which the segment appears in the CYCHP file. */
    private String segmentType;
    public String getSegmentType() { return segmentType; }
    public void setSegmentType(String s) { segmentType = s; }

    /** The key identifying the sample from the Samples data set. */
    private UInt referenceSampleKey;
    public UInt getReferenceSampleKey() { return referenceSampleKey; }
    public void setReferenceSampleKey(UInt k) { referenceSampleKey = k; }
    
    /** The ID of the segment of the reference sample. */
    private String referenceSegmentID;
    public String getReferenceSegmentID() { return referenceSegmentID; }
    public void setReferenceSegmentID(String id) { referenceSegmentID = id; }

    /** The key identifying the sample from the Samples data set. */
    private UInt familialSampleKey;
    public UInt getFamilialSampleKey() { return familialSampleKey; }
    public void setFamilialSampleKey(UInt key) { familialSampleKey = key; }

    /** The ID of the segment of the compare sample. */
    private String familialSegmentID;
    public String getFamilialSegmentID() { return familialSegmentID; }
    public void setFamilialSegmentID(String id) { familialSegmentID = id; }
}
