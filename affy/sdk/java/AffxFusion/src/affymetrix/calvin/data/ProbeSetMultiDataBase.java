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

import java.util.ArrayList;
import java.util.List;

import affymetrix.calvin.parameter.ParameterNameValue;

public class ProbeSetMultiDataBase {

	/** Other metrics associated with the result. */
	private List<ParameterNameValue> metrics = new ArrayList<ParameterNameValue>();

	public List<ParameterNameValue> getMetrics() {
		return metrics;
	}

	public void addMetrics(List<ParameterNameValue> m) {
		this.metrics.addAll(m);
	}

	public void addMetric(ParameterNameValue m) {
		this.metrics.add(m);
	}

	public void clearMetrics() {
		metrics.clear();
	}
}
