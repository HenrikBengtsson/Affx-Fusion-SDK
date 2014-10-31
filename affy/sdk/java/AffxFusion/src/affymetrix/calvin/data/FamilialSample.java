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

/** Stores information about the sample for a familial file. */
public class FamilialSample {

    public FamilialSample()
    {
        sampleKey = UInt.ZERO;
        arrID = null;
        chpID = null;
        chpFilename = null;
        role = null;
        roleValidity = false;
        roleConfidence = 0.0f;
    }
    /** Local arbitrary unique sample identifier used within the file. */
    private UInt sampleKey;
    public UInt getSampleKey() { return sampleKey; }
    public void setSampleKey(UInt k) { sampleKey = k; }
    
    /** The identifier of the ARR file associated with the sample. If no ARR file was used in generating the associated CYCHP files, this value will be the empty string. */
    private String arrID;
    public String getArrID() { return arrID; }
    public void setArrID(String id) { arrID = id; }
    
    /** The identifier of the CYCHP file containing the sample data. */
    private String chpID;
    public String getChpID() { return chpID; }
    public void setChpID(String id) { chpID = id; }

    /** The filename (not the complete path) of the CYCHP file containing the sample data. */
    private String chpFilename;
    public String getChpFilename() { return chpFilename; }
    public void setChpFilename(String f) { chpFilename = f; }

    /** The role of the identified sample */
    private String role;
    public String getRole() { return role; }
    public void setRole(String r) { role = r; }
    
    /** The call of whether the assigned role is correct. */
    private boolean roleValidity;
    public boolean getRoleValidity() { return roleValidity; }
    public void setRoleValidity(boolean b) { roleValidity = b; }
    
    /** The confidence that the assigned role is correct */
    private float roleConfidence;
    public float getRoleConfidence() { return roleConfidence; }
    public void setRoleConfidence(float r) { roleConfidence = r; }
}
