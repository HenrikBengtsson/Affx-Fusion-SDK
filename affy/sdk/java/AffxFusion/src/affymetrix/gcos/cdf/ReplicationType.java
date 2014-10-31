/////////////////////////////////////////////////////////////////
//
// Copyright (C) 2005 Affymetrix, Inc.
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

package affymetrix.gcos.cdf;

/** Defines the type of probe replication of a group. */
public class ReplicationType {

	/** Unspecified replication type */
	public static final int UnknownRepType = 0;

	/** Different
	 * All probes in the probe group have different sequences
	 */
	public static final int DifferentRepType = 1;

	/** Mixed
	 * Some probes in the probe group have identical sequences
	 */
	public static final int MixedRepType = 2;

	/** Identical
	 * All probes in the probe group have identical sequences
	 */
	public static final int IdenticalRepType = 3;


	/** Creates a new instance of DirectionType */
	public ReplicationType() {
	}

}
