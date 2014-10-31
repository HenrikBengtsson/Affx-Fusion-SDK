////////////////////////////////////////////////////////////////
//
// Copyright (C) 2011 Affymetrix, Inc.
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

/** Holds cyto genotyping result for a probe set */
public class CytoGenotypingCall extends ProbeSetMultiDataBase {

	/** The index of the probe set. */
  private int index;
  
  /** The call AA/AB/BB/no call. */
  private byte call;
  
	/** Call confidence from BRLMM-P . */
	private float confidence;
	
  /** The forced call AA/AB/BB regardless of confidence. */
  private byte forcedCall;

 	/** The A Signal value. */
	private float aSignal; 

	/** The B Signal value. */
	private float bSignal;	
	
 	/** Signal Strength value. */
	private float signalStrength;
	
	/** Contrast value. */
	private float contrast;
	
	/** Gets the index of the probe set. */
  public int getIndex() {
  	return index ;
  }

  /** Sets the index */
  public void setIndex(int idx) {
  	index = idx;
  }
  
	/** Gets the call. */
	public byte getCall() {
		return call;
	}

	/** Sets the call. */
	public void setCall(byte c) {
		call = c;
	}
	
	/** Gets the forced call. */
	public byte getForcedCall() {
		return forcedCall;
	}

	/** Sets the forced call. */
	public void setForcedCall(byte c) {
		forcedCall = c;
	}
  
  /** Gets the call confidence. */
  public float getConfidence() { 
  	return confidence; 
  }

  /** Sets the the call confidence. */
  public void setConfidence(float s) { 
  	confidence = s; 
  }
  
  /** Gets the A signal. */
  public float getASignal() { 
  	return aSignal; 
  }

  /** Sets the A signal. */
  public void setASignal(float s) { 
  	aSignal = s; 
  }

  /** Gets the B signal. */
  public float getBSignal() {
  	return bSignal; 
  }
  
  /** Sets the B signal. */
  public void setBSignal(float s) { 
  	bSignal = s; 
  }
  
  /** Gets the signal strength. */
  public float getSignalStrength() { 
  	return signalStrength; 
  }

  /** Sets the signal strength. */
  public void setSignalStrength(float s) { 
  	signalStrength = s; 
  }

  /** Gets the contrast. */
  public float getContrast() { 
  	return contrast; 
  }

  /** Sets the contrast. */
  public void setContrast(float s) { 
  	contrast = s; 
  }

  /** Constructor initializes all values to 0. */
  public CytoGenotypingCall() {
      index = 0;
      call = 0;
      forcedCall = 0;
      aSignal = 0.0f;
      bSignal = 0.0f;
      signalStrength = 0.0f;
      contrast = 0.0f;
  }
}
