////////////////////////////////////////////////////////////////
//
// Copyright (C) 2006 Affymetrix, Inc.
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

import java.util.ArrayList;
import java.util.List;

import affymetrix.calvin.data.CHPMultiDataData.MultiDataType;

/** Holds data set information for a multi-data chp file data set. */
public class DataSetInfo {

	/** The data type. */
	private MultiDataType dataType;

	/** chp data sets */
	private DataSet entries;

	/** The maximum length of a probe set name. */
	private int maxName;

        /** The maximum length of the familial seg type column. */
        private int maxSegmentType;

        /** The maximum length of the familial ref seg id column. */
        private int maxReferenceSegmentID;

        /** The maximum length of the familial seg id column. */
        private int maxFamilialSegmentID;

        /** The maximum length of the familial sample ARR id */
        private int maxFamilialARRID;

        /** The maximum length of the familial sample CHP id */
        private int maxFamilialCHPID;

        /** The maximum length of the familial sample CHP file name */
        private int maxFamilialCHPFile;

        /** The maximum length of the familial sample role */
        private int maxFamilialRole;

	/** The data set index. */
	private int dataSetIndex;

	/** An array of extra metric columns. */
	private List<ColumnInfo> metricColumns = new ArrayList<ColumnInfo>();

	/** constructor */
	public DataSetInfo() {
		entries = null;
		maxName = -1;
                maxSegmentType = -1;
                maxReferenceSegmentID = -1;
                maxFamilialSegmentID = -1;
                maxFamilialARRID = -1;
                maxFamilialCHPID = -1;
                maxFamilialCHPFile = -1;
                maxFamilialRole = -1;
                maxFamilialCHPFile = -1;
		dataSetIndex = -1;
	}

	public int getDataSetIndex() {
		return dataSetIndex;
	}

	public void setDataSetIndex(int dataSetIndex) {
		this.dataSetIndex = dataSetIndex;
	}

	public MultiDataType getDataType() {
		return dataType;
	}

	public void setDataType(MultiDataType dataType) {
		this.dataType = dataType;
	}

	public DataSet getEntries() {
		return entries;
	}

	public void setEntries(DataSet entries) {
		this.entries = entries;
	}

	public int getMaxName() {
		return maxName;
	}

	public void setMaxName(int mName) {
		this.maxName = mName;
	}

	public void addMetricColumn(ColumnInfo mColumn) {
		this.metricColumns.add(mColumn);
	}

	public void setMetricColumns(List<ColumnInfo> mColumns) {
		this.metricColumns.addAll(mColumns);
	}

	public List<ColumnInfo> getMetricColumns() {
		return metricColumns;
	}

	public void clearMetricColumns() {
		this.metricColumns.clear();
	}
        
        public int getMaxSegmentType() { return maxSegmentType; }
        public void setMaxSegmentType(int m) { maxSegmentType = m; }

        /** The maximum length of the familial ref seg id column. */
        public int getMaxReferenceSegmentID() { return maxReferenceSegmentID; }
        public void setMaxReferenceSegmentID(int m) { maxReferenceSegmentID = m; }

        /** The maximum length of the familial seg id column. */
        public int getMaxFamilialSegmentID() { return maxFamilialSegmentID; }
        public void setMaxFamilialSegmentID(int m) { maxFamilialSegmentID = m; }

        /** The maximum length of the familial sample ARR id */
        public int getMaxFamilialARRID() { return maxFamilialARRID; }
        public void setMaxFamilialARRID(int m) { maxFamilialARRID = m; }

        /** The maximum length of the familial sample CHP id */
        public int getMaxFamilialCHPID() { return maxFamilialCHPID; }
        public void setMaxFamilialCHPID(int m) { maxFamilialCHPID = m; }

        /** The maximum length of the familial sample CHP file name */
        public int getMaxFamilialCHPFile() { return maxFamilialCHPFile; }
        public void setMaxFamilialCHPFile(int m) { maxFamilialCHPFile = m; }

        /** The maximum length of the familial sample role */
        public int getMaxFamilialRole() { return maxFamilialRole; }
        public void setMaxFamilialRole(int m) { maxFamilialRole = m; }
}
