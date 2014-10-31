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

import java.util.*;
import affymetrix.portability.*;

/** Stores data for a copy number result. */
public class DmetCopyNumberData  extends ProbeSetMultiDataBase {

    /** Creates a new instance of DmetCopyNumberData */
    public DmetCopyNumberData() {
        name = null;
        call = 0;
        confidence = 0.0f;
        force = 0;
        estimate = 0.0f;
        lower = 0.0f;
        upper = 0.0f;
    }

    /** The name of the probe set. */
    private String name;
    
    /** The name of the probe set. */
    public String getName() { return name; }
    
    /** Set the name */
    public void setName(String n) { name = n; }

    /** The call for the region. */
    private short call;

    /** Get the call for the region.
     * @return The call
     */
    public short getCall() { return call; }
    
    /** The call for the region.
     * @param c The call
     */
    public void setCall(short c) { call = c; }
    
    /** The confidence score. */
    private float confidence;
    
    /** Get the confidence.
     * @return The confidence
     */
    public float getConfidence() { return confidence; }
    
    /** Set the confidence
     * @param c The confidence value
     */
    public void setConfidence(float c) { confidence = c; }
    
    /** The copy number force*/
    private short force;

    /** Get the force for the region.
     * @return The force
     */
    public short getForce() { return force; }
    
    /** The force for the region.
     * @param f The force
     */
    public void setForce(short f) { force = f; }
    
    /** copy number estimate */
    private float estimate;

    /** Get the estimate.
     * @return The estimate
     */
    public float getEstimate() { return estimate; }
    
    /** Set the estimate
     * @param e The estimate value
     */
    public void setEstimate(float e) { estimate = e; }
    
    /** lower bounds */
    private float lower;

    /** Get the lower.
     * @return The lower
     */
    public float getLower() { return lower; }
    
    /** Set the lower
     * @param l The lower value
     */
    public void setLower(float l) { lower = l; }
    
    /** upper bounds */
    private float upper;

    /** Get the upper.
     * @return The upper
     */
    public float getUpper() { return upper; }
    
    /** Set the upper
     * @param u The upper value
     */
    public void setUpper(float u) { upper = u; }
    
}
