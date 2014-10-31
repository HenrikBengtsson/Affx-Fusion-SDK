/////////////////////////////////////////////////////////////////
//
// Copyright (C) 2009 Affymetrix, Inc.
//
// This library is free software; you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License,
// or (at your option) any later version.
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
/////////////////////////////////////////////////////////////////

package affymetrix.calvin.data;

public class CHPReseqCalls {

	/** The force call was made due to no signal threshold. */
	public static final byte CC_NO_SIGNAL_THR_FORCE_CALL = 'N';

	/** The force call was made due to weak signal threshold. */
	public static final byte CC_WEAK_SIGNAL_THR_FORCE_CALL = 'W';

	/** The force call was made due to saturation level. */
	public static final byte CC_SATURATION_LEVEL_FORCE_CALL = 'S';

	/** The force call was made due to quality score threshold. */
	public static final byte CC_QUALITY_SCORE_THR_FORCE_CALL = 'Q';

	/** The force call was made due to failed both trace and sequence profiles. */
	public static final byte CC_TRACE_AND_SEQUENCE_PROFILES_FORCE_CALL = 'F';

	/** The force call was made due to base reliability threshold. */
	public static final byte CC_RELIABILITY_THR_FORCE_CALL = 'B';

	private CHPReseqCalls() {
	}

}
