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

/**
 * Stores data for a CNV result
 */
public class ProbeSetMultiDataCopyNumberVariationRegionData extends ProbeSetMultiDataBase {

    /** Construct an empty class. */
    public ProbeSetMultiDataCopyNumberVariationRegionData()
    {
        call = 0;
        name = null;
        confidenceScore = 0.0f;
    }
    
    /** The name of the region. */
    private String name;

    /** The name of the probe set. */
    public String getName() {
        return name;
    }

    /** Set the name */
    public void setName(String n) {
        name = n;
    }

    /** The signal for the region */
    float signal;

    /** The signal for the region.
     * @return The signal value
     */
    public float getSignal() {
        return signal;
    }
    
    /** The signal for the region.
     * @param s The signal value
     */
    public void setSignal(float s) {
        signal = s;
    }
    
    /** The call for the region. */
    private byte call;

    /** Get the call */
    public byte getCall() {
        return call;
    }

    /** Set the call. */
    public void setCall(byte c) {
        call = c;
    }

    /** The confidence score. */
    private float confidenceScore;

    /** Get the confidence score. */
    public float getConfidenceScore() {
        return confidenceScore;
    }

    /** Set the confidence score */
    public void setConfidenceScore(float c) {
        confidenceScore = c;
    }
}
