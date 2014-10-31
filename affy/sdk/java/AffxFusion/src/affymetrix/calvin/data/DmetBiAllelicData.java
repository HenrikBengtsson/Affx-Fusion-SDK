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

/** Stores data for a DMET genotype probe set */
public class DmetBiAllelicData  extends ProbeSetMultiDataBase {

    /** Creates a new instance of DmetBiAllelicData */
    public DmetBiAllelicData() {
        name = null;
        call = UByte.ZERO;
        confidence = 0.0f;
        force = UByte.ZERO;
        signalA = 0.0f;
        signalB = 0.0f;
        contextA = UByte.ZERO;
        contextB = UByte.ZERO;
    }

    /** The name of the probe set. */
    private String name;
    
    /** The name of the probe set. */
    public String getName() { return name; }
    
    /** Set the name */
    public void setName(String n) { name = n; }

    /** The call for the region. */
    private UByte call;

    /** Get the call for the region.
     * @return The call
     */
    public UByte getCall() { return call; }
    
    /** The call for the region.
     * @param c The call
     */
    public void setCall(UByte c) { call = c; }
    
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
    private UByte force;

    /** Get the force for the region.
     * @return The force
     */
    public UByte getForce() { return force; }
    
    /** The force for the region.
     * @param f The force
     */
    public void setForce(UByte f) { force = f; }
    
    /** copy number signal */
    private float signalA;

    /** Get the signal.
     * @return The signal
     */
    public float getSignalA() { return signalA; }
    
    /** Set the signal
     * @param e The value
     */
    public void setSignalA(float s) { signalA = s; }
    
    /** copy number signal */
    private float signalB;

    /** Get the signal.
     * @return The signal
     */
    public float getSignalB() { return signalB; }
    
    /** Set the signal
     * @param e The value
     */
    public void setSignalB(float s) { signalB = s; }
    
    /** The context. */
    private UByte contextA;

    /** Get the context.
     * @return The context
     */
    public UByte getContextA() { return contextA; }
    
    /** The context.
     * @param c The context
     */
    public void setContextA(UByte c) { contextA = c; }
        
    /** The context. */
    private UByte contextB;

    /** Get the context.
     * @return The context
     */
    public UByte getContextB() { return contextB; }
    
    /** The context.
     * @param c The context
     */
    public void setContextB(UByte c) { contextB = c; }
    
}
