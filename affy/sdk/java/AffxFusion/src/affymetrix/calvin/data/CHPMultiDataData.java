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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import affymetrix.calvin.exception.UnsignedOutOfLimitsException;
import affymetrix.calvin.parameter.AffymetrixParameterConsts;
import affymetrix.calvin.parameter.ParameterNameValue;
import affymetrix.portability.UByte;
import affymetrix.portability.UInt;
import affymetrix.portability.UShort;

/** Holds data associated with a multi-data CHP files. */
public class CHPMultiDataData extends ChpDataBase {
	/** The identifier to identify a multi-data CHP file. */
	public static final String CHP_MULTI_DATA_TYPE = "affymetrix-multi-data-type-analysis";

	/** The group and data set to store the generic genotype data. */
	private static final String MULTI_DATA_NAME = "MultiData";

	/** The data set names for a multi-data chp file */
	public static final String[] MultiDataDataSetNames = {
            "Expression",
            "ExpressionControl",
            "Genotype",
            "GenotypeControl",
            "CopyNumber",
            "Cyto",
            "CopyNumberVariation",
            "DmetCopyNumber",
            "DmetMultiAllelic",
            "DmetBiAllelic",
            "Summary",
            "CN",
            "LOH",
            "CNNeutralLOH",
            "NormalDiploid",
            "Mosaicism",
            "NoCall",
            "SegmentOverlaps",
            "Samples",
            "GenotypeConcordance",
            "GenotypeDiscordance",
            "CNLossLOHConcordance",
            "CNNeutralLOHConcordance",
            "HeteroUPD",
            "IsoUPD",
            "DenovoCopyNumber",
            "HemizygousParentOfOrigin",
            "AllelePeaks",
            "MarkerABSignal",
            "Calls"
        };

    /** An enumerant to store the types of data stored in the file. */
    public enum MultiDataType {
            ExpressionMultiDataType,
            ExpressionControlMultiDataType,
            GenotypeMultiDataType,
            GenotypeControlMultiDataType,
            CopyNumberMultiDataType,
            CytoMultiDataType,
            CopyNumberVariationMultiDataType,
            DmetCopyNumberMultiDataType,
            DmetMultiAllelicMultiDataType,
            DmetBiAllelicMultiDataType,
            ChromosomeSummaryMultiDataType,
            SegmentCNMultiDataType,
            SegmentLOHMultiDataType,
            SegmentCNNeutralLOHMultiDataType,
            SegmentNormalDiploidMultiDataType,
            SegmentMosaicismMultiDataType,
            SegmentNoCallMultiDataType,
            FamilialSegmentOverlapsMultiDataType,
            FamilialSamplesMultiDataType,
            SegmentGenotypeConcordanceMultiDataType,
            SegmentGenotypeDiscordanceMultiDataType,
            SegmentCNLossLOHConcordanceMultiDataType,
            SegmentCNNeutralLOHConcordanceMultiDataType,
            SegmentHeteroUPDMultiDataType,
            SegmentIsoUPDMultiDataType,
            SegmentDenovoCopyNumberMultiDataType,
            SegmentHemizygousParentOfOriginMultiDataType,
            AllelePeaksMultiDataType,
            MarkerABSignalsMultiDataType,
            CytoGenotypingCallMultiDataType
    };

    /** The data types. */
    private static final MultiDataType MultiDataDataTypes[] =
    {
        MultiDataType.ExpressionMultiDataType,
        MultiDataType.ExpressionControlMultiDataType,
        MultiDataType.GenotypeMultiDataType,
        MultiDataType.GenotypeControlMultiDataType,
        MultiDataType.CopyNumberMultiDataType,
        MultiDataType.CytoMultiDataType,
        MultiDataType.CopyNumberVariationMultiDataType,
        MultiDataType.DmetCopyNumberMultiDataType,
        MultiDataType.DmetMultiAllelicMultiDataType,
        MultiDataType.DmetBiAllelicMultiDataType,
        MultiDataType.ChromosomeSummaryMultiDataType,
        MultiDataType.SegmentCNMultiDataType,
        MultiDataType.SegmentLOHMultiDataType,
        MultiDataType.SegmentCNNeutralLOHMultiDataType,
        MultiDataType.SegmentNormalDiploidMultiDataType,
        MultiDataType.SegmentMosaicismMultiDataType,
        MultiDataType.SegmentNoCallMultiDataType,
        MultiDataType.FamilialSegmentOverlapsMultiDataType,
        MultiDataType.FamilialSamplesMultiDataType,
        MultiDataType.SegmentGenotypeConcordanceMultiDataType,
        MultiDataType.SegmentGenotypeDiscordanceMultiDataType,
        MultiDataType.SegmentCNLossLOHConcordanceMultiDataType,
        MultiDataType.SegmentCNNeutralLOHConcordanceMultiDataType,
        MultiDataType.SegmentHeteroUPDMultiDataType,
        MultiDataType.SegmentIsoUPDMultiDataType,
        MultiDataType.SegmentDenovoCopyNumberMultiDataType,
        MultiDataType.SegmentHemizygousParentOfOriginMultiDataType,
        MultiDataType.AllelePeaksMultiDataType,
        MultiDataType.MarkerABSignalsMultiDataType,
        MultiDataType.CytoGenotypingCallMultiDataType
    };

	/** The column name for the probe set name. */
	private static final String PROBE_SET_NAME = "ProbeSetName";

	/** The column name for the call. */
	private static final String CALL = "Call";

    /** The column name for the call. */
    private static final String CN_CALL = "CN Call";

	/** The column name for the confidence value. */
	private static final String CONFIDENCE = "Confidence";

    /** The column name for the confidence value. */
    private static final String CN_CONFIDENCE = "CN Confidence";

	/** The column name for the quantification value. */
	private static final String EXPRESSION_QUANTIFICATION_NAME = "Quantification";

	/** The column name for the chromosome name. */
	private static final String CHR = "Chromosome";

	/** The column name for the physical position of the SNP. */
	private static final String POSITION = "Position";

	/** The column name for the physical position of the cyto region. */
	private static final String START_POSITION = "StartPosition";

	/** The column name for the physical position of the region. */
	private static final String STOP_POSITION = "StopPosition";

	/** The column name for the region. */
	private static final String REGION = "Region";
        
    /** The column name for the cnv signal. */
	private static final String SIGNAL = "Signal";
	
    /** The column name for the signal strength. */
	private static final String SIGNAL_STRENGTH = "SignalStrength";
	
    /** The column name for the contrast. */
	private static final String CONTRAST = "Contrast";

    /** The column name for the force. */
    private static final String FORCE = "Forced Call";

    /** The column name for the force. */
    private static final String CN_FORCE = "CN_force";

    /** The column name for the estimate. */
    private static final String CN_ESTIMATE = "CN_estim";

    /** The column name for the lower. */
    private static final String CN_LOWER = "CN_lower";

    /** The column name for the upper. */
    private static final String CN_UPPER = "CN_upper";

    /** The column name for the allele count. */
    private static final String ALLELE_COUNT = "Allele Count";
    
    /** The column name for the mean marker distance. */
    private static final String MEAN_MARKER_DISTANCE = "MeanMarkerDistance";

    /** The column name for signals. */
    private static final String SIGNAL_A = "Signal A";
    private static final String SIGNAL_B = "Signal B";
    private static final String SIGNAL_C = "Signal C";
    private static final String SIGNAL_D = "Signal D";
    private static final String SIGNAL_E = "Signal E";
    private static final String SIGNAL_F = "Signal F";

    /** The column name for contexts. */
    private static final String CONTEXT_A = "Context A";
    private static final String CONTEXT_B = "Context B";
    private static final String CONTEXT_C = "Context C";
    private static final String CONTEXT_D = "Context D";
    private static final String CONTEXT_E = "Context E";
    private static final String CONTEXT_F = "Context F";

    /** Columns for chromosome summary - start index.  */
    private static final String START_INDEX = "StartIndex";

    /** Columns for the chromosome display */
    private static final String CHR_DISPLAY = "Display";

    /** Columns for chromosome summary - min signal.  */
    private static final String MIN_SIGNAL = "MinSignal";

    /** Columns for chromosome summary - max signal.  */
    private static final String MAX_SIGNAL = "MaxSignal";

    /** Columns for chromosome summary - median cn state.  */
    private static final String MEDIAN_CN_STATE = "MedianCnState";

    /** Columns for chromosome summary - hom frequency.  */
    private static final String HOM_FREQ = "HomFrequency";

    /** Columns for chromosome summary - het frequency.  */
    private static final String HET_FREQ = "HetFrequency";

    /** Columns for chromosome summary - mosaicism.  */
    private static final String MOSAICISM = "Mosaicism";

    /** The segment id tag. */
    private static final String SEGMENT_ID = "SegmentID";

    /** The marker count tag. */
    private static final String MARKER_COUNT = "MarkerCount";

    /** The mixture tag. */
    private static final String MIXTURE = "Mixture";

    /** The calibrated CN tag. */
    private static final String CALIBRATED_CN = "CalibratedCN";

    /** Familial tags - segment type. */
    private static final String SEGMENT_TYPE = "SegmentType";

    /** Familial tags - reference sample key. */
    private static final String REFERENCE_SAMPLE_KEY = "ReferenceSampleKey";

    /** Familial tags - reference segment id. */
    private static final String REFERENCE_SEGMENT_ID = "ReferenceSegmentID";

    /** Familial tags - sample key. */
    private static final String FAMILIAL_SAMPLE_KEY = "FamilialSampleKey";

    /** Familial tags - sample id. */
    private static final String FAMILIAL_SEGMENT_ID = "FamilialSegmentID";

    private static final String SAMPLE_KEY = "SampleKey";
    private static final String ARRID = "ARRID";
    private static final String CHPID = "CHPID";
    private static final String CHP_FILENAME = "CHPFilename";
    private static final String ROLE = "Role";
    private static final String ROLE_VALIDITY = "RoleValidity";
    private static final String ROLE_CONFIDENCE = "RoleConfidence";

    private static final String CN = "CN";
    private static final String LOH = "LOH";
    private static final String CNNEUTRALLOH = "CNNeutralLOH";
    private static final String NORMALDIPLOID = "NormalDiploid";
    private static final String NOCALL = "NoCall";
    private static String HOMOZYGOSITY = "Homozygosity";
    private static String HETEROZYGOSITY = "Heterozygosity";

    private static final String PROBE_SET_INDEX = "Index";
    private static final String A_SIGNAL = "ASignal";
    private static final String B_SIGNAL = "BSignal";
    private static final String SCAR = "SCAR";


	/** a map of chp data sets. */
	public Map<MultiDataType, DataSetInfo> dataSetInfo = new HashMap<MultiDataType, DataSetInfo>();

        /** data groups */
        public Map<MultiDataType, String> dataTypeGroupNames = new HashMap<MultiDataType, String>();

	/** Constructor */
	public CHPMultiDataData() {
		clear();
	}

	/**
	 * Constructor with file name.
	 * 
	 * @param filename The name of the CHP file.
	 */
	public CHPMultiDataData(String filename) {
		this(filename, null);
	}

	/**
	 * Constructor with file name and group names
	 * 
	 * @param filename The name of the CHP file.
         * @param groupNames A list of names for the data groups.
	 */
	public CHPMultiDataData(String filename, String[] groupNames) {
		this();
		setFilename(filename);
                try
                {
                    if (groupNames == null)
                    {
                        DataGroupHeader dcHdr = new DataGroupHeader(MULTI_DATA_NAME);
                        genericData.getHeader().addDataGroupHdr(dcHdr);
                        genericData.getHeader().setNumDataGroups(new UInt(1));
                    }
                    else
                    {
                        for (int i=0; i<groupNames.length; i++)
                        {
                            DataGroupHeader dcHdr = new DataGroupHeader(groupNames[i]);
                            genericData.getHeader().addDataGroupHdr(dcHdr);
                        }
                        genericData.getHeader().setNumDataGroups(new UInt(groupNames.length));
                    }
                }
                catch(UnsignedOutOfLimitsException t)
                {
                }
		genericData.getHeader().getGenericDataHdr().setFileTypeId(CHP_MULTI_DATA_TYPE);
	}

	/** Get the data set information map */
	public Map<MultiDataType, DataSetInfo> getDataSetInfo() {
		return dataSetInfo;
	}
        
        /** Get the group name given the data type
         * 
         * @param dataType The data type
         * @return The group name
         */
        public String getGroupName(MultiDataType dataType) {
            return dataTypeGroupNames.get(dataType);
        }

	/**
	 * The maximum length of a probe set name.
	 * 
	 * @param dataType
	 *          The data type
	 */
	public int getMaxProbeSetName(MultiDataType dataType) {
		DataSetInfo info = openMultiDataDataSet(dataType);
		if (info != null) {
			return info.getMaxName();
		}
		return 0;
	}
        
        /** The maximum length of a segment id.
        * @param dataType The data type
        * @return The maximum length
        */
        public int getMaxSegmentId(MultiDataType dataType) {
            	DataSetInfo info = openMultiDataDataSet(dataType);
		if (info != null) {
			return info.getMaxName();
		}
		return 0;
        }


	/** Clears the members. */
	public void clear() {
		dataSetInfo.clear();
                dataTypeGroupNames.clear();
		genericData.getHeader().clear();
	}

	/**
	 * sets the file name.
	 * 
	 * @param p
	 *          The name of the CHP file
	 */
	public void setFilename(String p) {
		genericData.getHeader().setFilename(p);
	}

	/**
	 * gets the file name.
	 * 
	 * @return The file name.
	 */
	public String getFilename() {
		return genericData.getHeader().getFilename();
	}

	/** sets the array type */
	public String getArrayType() {
		return getStringFromGenericHdr(AffymetrixParameterConsts.ARRAY_TYPE_PARAM_NAME);
	}

	public void setArrayType(String value) {
		setStringToGenericHdr(AffymetrixParameterConsts.ARRAY_TYPE_PARAM_NAME, value,
				AffymetrixParameterConsts.ARRAY_TYPE_MAX_LEN);
	}

	/**
	 * gets the number of entries (probe sets)
	 * 
	 * @param dataType
	 *          The data type
	 */
	public int getEntryCount(MultiDataType dataType) {
		DataSetHeader h = getDataSetHeader(dataType);
		return (h == null ? 0 : h.getRowCnt());
	}
	
	public void setEntryCount(MultiDataType dataType, int ln, int maxln) {
		setEntryCount(dataType, ln, maxln, null);
	}

	public void setEntryCount(MultiDataType dataType, int ln, int maxln, String groupName) {
		List<ColumnInfo> empty = new ArrayList<ColumnInfo>();
		setEntryCount(dataType, ln, maxln, empty, groupName);
	}
        
        /** Sets the number of entries specifically for the familial file.
        * @param dataType The data type
        * @param ln The number of probe sets.
        * @param segmentTypeMax The maximum length of the familial seg type column
        * @param referenceSegmentIDMax The maximum length of the familial ref seg id column
        * @param familialSegmentIDMax The maximum length of the familial seg id column
        * @param groupName The group name
        */
        public void setEntryCount(MultiDataType dataType, int ln, int segmentTypeMax, int referenceSegmentIDMax, int familialSegmentIDMax, String groupName) {
                DataSetInfo info = new DataSetInfo();
                info.setMaxSegmentType(segmentTypeMax);
                info.setMaxReferenceSegmentID(referenceSegmentIDMax);
                info.setMaxFamilialSegmentID(familialSegmentIDMax);
                info.setEntries(null);
                info.setDataType(dataType);
                info.setDataSetIndex(dataSetInfo.size());
                dataSetInfo.put(dataType, info);

                DataSetHeader dsHdr = new DataSetHeader();
                dsHdr.setRowCnt(ln);
                dsHdr.setName(MultiDataDataSetNames[dataType.ordinal()]);
                addColumns(info, dsHdr);

                if (groupName == null || (groupName != null && groupName.equals("") == true))
                    dataTypeGroupNames.put(dataType, MULTI_DATA_NAME);
                else
                    dataTypeGroupNames.put(dataType, groupName);
                DataGroupHeader dgHdr = getDataGroupHeader(dataTypeGroupNames.get(dataType));
                dgHdr.addDataSetHdr(dsHdr);
        }

	public void setEntryCount(MultiDataType dataType, int ln, int maxln, List<ColumnInfo> columns, String groupName) {
		DataSetInfo info = new DataSetInfo();
		info.setMaxName(maxln);
		info.setMetricColumns(columns);
		info.setEntries(null);
		info.setDataType(dataType);
		info.setDataSetIndex(dataSetInfo.size());
		dataSetInfo.put(dataType, info);

		DataSetHeader dsHdr = new DataSetHeader();
		dsHdr.setRowCnt(ln);
		dsHdr.setName(MultiDataDataSetNames[dataType.ordinal()]);
		addColumns(info, dsHdr);

                if (groupName == null || (groupName != null && groupName.equals("") == true))
                    dataTypeGroupNames.put(dataType, MULTI_DATA_NAME);
                else
                    dataTypeGroupNames.put(dataType, groupName);
		DataGroupHeader dgHdr = getDataGroupHeader(dataTypeGroupNames.get(dataType));
		dgHdr.addDataSetHdr(dsHdr);
	}

	private void addColumns(DataSetInfo info, DataSetHeader hdr) {
		switch (info.getDataType()) {
		case ExpressionMultiDataType:
		case ExpressionControlMultiDataType:
			hdr.addAsciiColumn(PROBE_SET_NAME, info.getMaxName());
			hdr.addFloatColumn(EXPRESSION_QUANTIFICATION_NAME);
			break;

		case GenotypeMultiDataType:
		case GenotypeControlMultiDataType:
			hdr.addAsciiColumn(PROBE_SET_NAME, info.getMaxName());
			hdr.addUByteColumn(CALL);
			hdr.addFloatColumn(CONFIDENCE);
			break;

		case CopyNumberMultiDataType:
			hdr.addAsciiColumn(PROBE_SET_NAME, info.getMaxName());
			hdr.addUByteColumn(CHR);
			hdr.addUIntColumn(POSITION);
			break;

		case CytoMultiDataType:
			hdr.addAsciiColumn(REGION, info.getMaxName());
			hdr.addUByteColumn(CHR);
			hdr.addUIntColumn(START_POSITION);
			hdr.addUIntColumn(STOP_POSITION);
			hdr.addUByteColumn(CALL);
			hdr.addFloatColumn(CONFIDENCE);
			break;
			
		case CytoGenotypingCallMultiDataType:
			hdr.addUIntColumn(PROBE_SET_INDEX);
			hdr.addUByteColumn(CALL);
			hdr.addFloatColumn(CONFIDENCE);
			hdr.addUByteColumn(FORCE);
			hdr.addFloatColumn(A_SIGNAL);
			hdr.addFloatColumn(B_SIGNAL);
			hdr.addFloatColumn(SIGNAL_STRENGTH);
			hdr.addFloatColumn(CONTRAST);

        case CopyNumberVariationMultiDataType:
                hdr.addAsciiColumn(REGION, info.getMaxName());
                hdr.addFloatColumn(SIGNAL);
                hdr.addUByteColumn(CALL);
                hdr.addFloatColumn(CONFIDENCE);
                break;

        case DmetCopyNumberMultiDataType:
                hdr.addAsciiColumn(PROBE_SET_NAME, info.getMaxName());
                hdr.addShortColumn(CN_CALL);
                hdr.addFloatColumn(CN_CONFIDENCE);
                hdr.addShortColumn(CN_FORCE);
                hdr.addFloatColumn(CN_ESTIMATE);
                hdr.addFloatColumn(CN_LOWER);
                hdr.addFloatColumn(CN_UPPER);
                break;

        case DmetMultiAllelicMultiDataType:
                hdr.addAsciiColumn(PROBE_SET_NAME, info.getMaxName());
                hdr.addUByteColumn(CALL);
                hdr.addFloatColumn(CONFIDENCE);
                hdr.addUByteColumn(FORCE);
                hdr.addUByteColumn(ALLELE_COUNT);
                hdr.addFloatColumn(SIGNAL_A);
                hdr.addFloatColumn(SIGNAL_B);
                hdr.addFloatColumn(SIGNAL_C);
                hdr.addFloatColumn(SIGNAL_D);
                hdr.addFloatColumn(SIGNAL_E);
                hdr.addFloatColumn(SIGNAL_F);
                hdr.addUByteColumn(CONTEXT_A);
                hdr.addUByteColumn(CONTEXT_B);
                hdr.addUByteColumn(CONTEXT_C);
                hdr.addUByteColumn(CONTEXT_D);
                hdr.addUByteColumn(CONTEXT_E);
                hdr.addUByteColumn(CONTEXT_F);
                break;

        case DmetBiAllelicMultiDataType:
                hdr.addAsciiColumn(PROBE_SET_NAME, info.getMaxName());
                hdr.addUByteColumn(CALL);
                hdr.addFloatColumn(CONFIDENCE);
                hdr.addUByteColumn(FORCE);
                hdr.addFloatColumn(SIGNAL_A);
                hdr.addFloatColumn(SIGNAL_B);
                hdr.addUByteColumn(CONTEXT_A);
                hdr.addUByteColumn(CONTEXT_B);
                break;


        case ChromosomeSummaryMultiDataType:
                hdr.addUByteColumn(CHR);
                hdr.addAsciiColumn(CHR_DISPLAY, info.getMaxName());
                hdr.addUIntColumn(START_INDEX);
                hdr.addUIntColumn(MARKER_COUNT);
                hdr.addFloatColumn(MIN_SIGNAL);
                hdr.addFloatColumn(MAX_SIGNAL);
                hdr.addFloatColumn(MEDIAN_CN_STATE);
                hdr.addFloatColumn(HOM_FREQ);
                hdr.addFloatColumn(HET_FREQ);
                break;

        case SegmentCNMultiDataType:
                hdr.addUIntColumn(SEGMENT_ID);
                hdr.addUByteColumn(CHR);
                hdr.addUIntColumn(START_POSITION);
                hdr.addUIntColumn(STOP_POSITION);
                hdr.addIntColumn(MARKER_COUNT);
                hdr.addUIntColumn(MEAN_MARKER_DISTANCE);
                break;

        case SegmentLOHMultiDataType:
                hdr.addUIntColumn(SEGMENT_ID);
                hdr.addUByteColumn(CHR);
                hdr.addUIntColumn(START_POSITION);
                hdr.addUIntColumn(STOP_POSITION);
                hdr.addIntColumn(MARKER_COUNT);
                hdr.addUIntColumn(MEAN_MARKER_DISTANCE);
                break;

        case SegmentCNNeutralLOHMultiDataType:
                hdr.addUIntColumn(SEGMENT_ID);
                hdr.addUByteColumn(CHR);
                hdr.addUIntColumn(START_POSITION);
                hdr.addUIntColumn(STOP_POSITION);
                hdr.addIntColumn(MARKER_COUNT);
                hdr.addUIntColumn(MEAN_MARKER_DISTANCE);
                break;

        case SegmentNormalDiploidMultiDataType:
                hdr.addUIntColumn(SEGMENT_ID);
                hdr.addUByteColumn(CHR);
                hdr.addUIntColumn(START_POSITION);
                hdr.addUIntColumn(STOP_POSITION);
                hdr.addIntColumn(MARKER_COUNT);
                hdr.addUIntColumn(MEAN_MARKER_DISTANCE);
                break;

        case SegmentNoCallMultiDataType:
                hdr.addUIntColumn(SEGMENT_ID);
                hdr.addUByteColumn(CHR);
                hdr.addUIntColumn(START_POSITION);
                hdr.addUIntColumn(STOP_POSITION);
                hdr.addIntColumn(MARKER_COUNT);
                hdr.addUIntColumn(MEAN_MARKER_DISTANCE);
                break;

        case SegmentMosaicismMultiDataType:
                hdr.addUIntColumn(SEGMENT_ID);
                hdr.addUByteColumn(CHR);
                hdr.addUIntColumn(START_POSITION);
                hdr.addUIntColumn(STOP_POSITION);
                hdr.addIntColumn(MARKER_COUNT);
                hdr.addUIntColumn(MEAN_MARKER_DISTANCE);
                break;

        case SegmentGenotypeConcordanceMultiDataType:
        case SegmentGenotypeDiscordanceMultiDataType:
        case SegmentCNLossLOHConcordanceMultiDataType:
        case SegmentCNNeutralLOHConcordanceMultiDataType:
        case SegmentHeteroUPDMultiDataType:
        case SegmentIsoUPDMultiDataType:
        case SegmentDenovoCopyNumberMultiDataType:
        case SegmentHemizygousParentOfOriginMultiDataType:
                hdr.addUIntColumn(SEGMENT_ID);
                hdr.addUIntColumn(REFERENCE_SAMPLE_KEY);
                hdr.addUIntColumn(FAMILIAL_SAMPLE_KEY);
                hdr.addUByteColumn(CHR);
                hdr.addUIntColumn(START_POSITION);
                hdr.addUIntColumn(STOP_POSITION);
                hdr.addUByteColumn(CALL);
                hdr.addFloatColumn(CONFIDENCE);
                hdr.addIntColumn(MARKER_COUNT);
                hdr.addFloatColumn(HOMOZYGOSITY);
                hdr.addFloatColumn(HETEROZYGOSITY);
                break;

        case FamilialSegmentOverlapsMultiDataType:
                hdr.addAsciiColumn(SEGMENT_TYPE, info.getMaxSegmentType());
                hdr.addUIntColumn(REFERENCE_SAMPLE_KEY);
                hdr.addAsciiColumn(REFERENCE_SEGMENT_ID, info.getMaxReferenceSegmentID());
                hdr.addUIntColumn(FAMILIAL_SAMPLE_KEY);
                hdr.addAsciiColumn(FAMILIAL_SEGMENT_ID, info.getMaxFamilialSegmentID());
                break;

        case FamilialSamplesMultiDataType:
                hdr.addUIntColumn(SAMPLE_KEY);
                hdr.addAsciiColumn(ARRID, info.getMaxFamilialARRID());
                hdr.addAsciiColumn(CHPID, info.getMaxFamilialCHPID());
                hdr.addAsciiColumn(CHP_FILENAME, info.getMaxFamilialCHPFile());
                hdr.addAsciiColumn(ROLE, info.getMaxFamilialRole());
                hdr.addUByteColumn(ROLE_VALIDITY);
                hdr.addFloatColumn(ROLE_CONFIDENCE);
                break;

        case AllelePeaksMultiDataType:
			hdr.addAsciiColumn(PROBE_SET_NAME, info.getMaxName());
			hdr.addUByteColumn(CHR);
			hdr.addUIntColumn(POSITION);
            break;

        case MarkerABSignalsMultiDataType:
            hdr.addUIntColumn(PROBE_SET_INDEX);
            break;

        default:
            break;
		}
		Iterator<ColumnInfo> it = info.getMetricColumns().iterator();
		while (it.hasNext()) {
			hdr.addColumn(it.next());
		}
	}

        /** Get the data set header.
         * 
         * @param dataType The data type
         * @return The header
         */
	private DataSetHeader getDataSetHeader(MultiDataType dataType) {
            int ng = (int)genericData.getHeader().getNumDataGroups().toLong();
            for (int ig=0; ig<ng; ig++)
            {
		DataGroupHeader dcHdr = genericData.getHeader().getDataGroup(ig);
		int n = dcHdr.getDataSetCnt();
		for (int i = 0; i < n; i++) {
			DataSetHeader dpHdr = dcHdr.getDataSet(i);
			if (dpHdr.getName().equals(MultiDataDataSetNames[dataType.ordinal()])) {
				return dpHdr;
			}
		}
            }
            return null;
	}

        /** Returns the data group header.
        * @param name The name of the group.
        * @return The group header
        */
        public DataGroupHeader getDataGroupHeader(String name) {
            int ng = (int)genericData.getHeader().getNumDataGroups().toLong();
            for (int ig=0; ig<ng; ig++)
            {
                DataGroupHeader dh = genericData.getHeader().getDataGroup(ig);
                if (dh.getName().equals(name) == true)
                    return genericData.getHeader().getDataGroup(ig);
            }
            return null;
        }

        /*! Returns the data group index.
        * @param dataType The data type.
        * @return The group index
        */
        public int getDataGroupIndex(MultiDataType dataType) {
            String name = dataTypeGroupNames.get(dataType);
            int ng = (int)genericData.getHeader().getNumDataGroups().toLong();
            for (int ig=0; ig<ng; ig++)
            {
                DataGroupHeader dh = genericData.getHeader().getDataGroup(ig);
                if (dh.getName().equals(name) == true)
                    return ig;
            }
            return -1;
        }

	/**
	 * gets the name of the algorithm.
	 * 
	 * @return The algorithm name.
	 */
	public String getAlgName() {
		return getStringFromGenericHdr(AffymetrixParameterConsts.ALGORITHM_NAME_PARAM_NAME);
	}

	public void setAlgName(String value) {
		setStringToGenericHdr(AffymetrixParameterConsts.ALGORITHM_NAME_PARAM_NAME, value);
	}

	/**
	 * gets the algorithm version.
	 * 
	 * @return The version.
	 */
	public String getAlgVersion() {
		return getStringFromGenericHdr(AffymetrixParameterConsts.ALG_VERSION_PARAM_NAME);
	}

	public void setAlgVersion(String value) {
		setStringToGenericHdr(AffymetrixParameterConsts.ALG_VERSION_PARAM_NAME, value);
	}

	/**
	 * gets the algorithm parameters
	 * 
	 * @return The algoirhtm parameters.
	 */
	public List<ParameterNameValue> getAlgParams() {
		return getParams(AffymetrixParameterConsts.ALGORITHM_PARAM_NAME_PREFIX);
	}

	public void addSummaryParams(List<ParameterNameValue> params) {
		super.addParams(AffymetrixParameterConsts.CHIP_SUMMARY_PARAM_NAME_PREFIX, params);
	}

	public void addAlgParams(List<ParameterNameValue> params) {
		super.addParams(AffymetrixParameterConsts.ALGORITHM_PARAM_NAME_PREFIX, params);
	}

	/**
	 * gets the summary parameters
	 * 
	 * @return The summary parameters.
	 */
	public List<ParameterNameValue> getSummaryParams() {
		return getParams(AffymetrixParameterConsts.CHIP_SUMMARY_PARAM_NAME_PREFIX);
	}

	/**
	 * gets the file header.
	 * 
	 * @return The file header.
	 */
	public FileHeader getFileHeader() {
		return genericData.getHeader();
	}

	/**
	 * gets the generic data object.
	 * 
	 * @return The data object.
	 */
	public GenericData getGenericData() {
		return genericData;
	}

	/**
	 * gets the probe set data.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 * @return The genotype results.
	 */
	public ProbeSetMultiDataGenotypeData getGenotypeEntry(MultiDataType dataType, int index) throws IOException,
			UnsignedOutOfLimitsException {
		return getGenericGenotypeEntry(dataType, index);
	}

	/**
	 * gets the probe set data.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 * @return The results.
	 */
	public ProbeSetMultiDataExpressionData getExpressionEntry(MultiDataType dataType, int index) throws IOException,
			UnsignedOutOfLimitsException {
		return getGenericExpressionEntry(dataType, index);
	}

	/**
	 * Gets the copy number data.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 * @return The results.
	 */
	public ProbeSetMultiDataCopyNumberData getCopyNumberEntry(MultiDataType dataType, int index) throws IOException,
			UnsignedOutOfLimitsException {
		return getGenericCopyNumberEntry(dataType, index);
	}

	public ProbeSetMultiDataCytoRegionData getCytoEntry(MultiDataType dataType, int index) throws IOException,
			UnsignedOutOfLimitsException {
		return getGenericCytoRegionEntry(dataType, index);
	}

	private ProbeSetMultiDataCytoRegionData getGenericCytoRegionEntry(MultiDataType dataType, int index)
			throws IOException, UnsignedOutOfLimitsException {
		DataSetInfo ds = openMultiDataDataSet(dataType);
		ProbeSetMultiDataCytoRegionData entry = null;
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			int colIndex = 0;
			entry = new ProbeSetMultiDataCytoRegionData();
			entry.setName(ds.getEntries().getDataString8(index, colIndex++));
			entry.setChr(ds.getEntries().getDataByte(index, colIndex++));
			entry.setStartPosition(ds.getEntries().getDataUInt(index, colIndex++));
			entry.setStopPosition(ds.getEntries().getDataUInt(index, colIndex++));
			entry.setCall(ds.getEntries().getDataByte(index, colIndex++));
			entry.setConfidence(ds.getEntries().getDataFloat(index, colIndex++));
			List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
			if (extraMetrics != null) {
				entry.addMetrics(extraMetrics);
			}
		}
		return entry;
	}

        /** Gets the probe set data.
        * @param dataType The data type
        * @param index The row index.
        * @return The copy number variation results
        */
        public ProbeSetMultiDataCopyNumberVariationRegionData getCopyNumberVariationEntry(MultiDataType dataType, int index)
                throws IOException, UnsignedOutOfLimitsException {
		return getGenericCopyNumberVariationEntry(dataType, index);
	}

        /** Gets the probe set data.
        * @param dataType The data type
        * @param index The row index.
        * @return The copy number variation results.
        */
        private ProbeSetMultiDataCopyNumberVariationRegionData getGenericCopyNumberVariationEntry(MultiDataType dataType, int index)
            throws IOException, UnsignedOutOfLimitsException {
		DataSetInfo ds = openMultiDataDataSet(dataType);
		ProbeSetMultiDataCopyNumberVariationRegionData entry = null;
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			int colIndex = 0;
			entry = new ProbeSetMultiDataCopyNumberVariationRegionData();
			entry.setName(ds.getEntries().getDataString8(index, colIndex++));
                        entry.setSignal(ds.getEntries().getDataFloat(index, colIndex++));
			entry.setCall(ds.getEntries().getDataByte(index, colIndex++));
			entry.setConfidenceScore(ds.getEntries().getDataFloat(index, colIndex++));
			List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
			if (extraMetrics != null) {
				entry.addMetrics(extraMetrics);
			}
		}
		return entry;
        }

	/**
	 * Gets the probe set data.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 * @return The genotype results.
	 */
	private ProbeSetMultiDataGenotypeData getGenericGenotypeEntry(MultiDataType dataType, int index) throws IOException,
			UnsignedOutOfLimitsException {
		DataSetInfo ds = openMultiDataDataSet(dataType);
		ProbeSetMultiDataGenotypeData entry = null;
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			int colIndex = 0;
			entry = new ProbeSetMultiDataGenotypeData();
			entry.setName(ds.getEntries().getDataString8(index, colIndex++));
			entry.setCall(ds.getEntries().getDataByte(index, colIndex++));
			entry.setConfidence(ds.getEntries().getDataFloat(index, colIndex++));
			List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
			if (extraMetrics != null) {
				entry.addMetrics(extraMetrics);
			}
		}
		return entry;
	}

	/**
	 * Gets the probe set data.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 */
	private ProbeSetMultiDataExpressionData getGenericExpressionEntry(MultiDataType dataType, int index)
			throws IOException, UnsignedOutOfLimitsException {
		DataSetInfo ds = openMultiDataDataSet(dataType);
		ProbeSetMultiDataExpressionData entry = null;
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			int colIndex = 0;
			entry = new ProbeSetMultiDataExpressionData();
			entry.setName(ds.getEntries().getDataString8(index, colIndex++));
			entry.setQuantification(ds.getEntries().getDataFloat(index, colIndex++));
			List<ParameterNameValue> metrics = getExtraMetricEntries(ds, index, colIndex);
			if (metrics != null) {
				entry.addMetrics(metrics);
			}
		}
		return entry;
	}

	private ProbeSetMultiDataCopyNumberData getGenericCopyNumberEntry(MultiDataType dataType, int index)
			throws IOException, UnsignedOutOfLimitsException {
		DataSetInfo ds = openMultiDataDataSet(dataType);
		ProbeSetMultiDataCopyNumberData entry = null;
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			int colIndex = 0;
			entry = new ProbeSetMultiDataCopyNumberData();
			entry.setName(ds.getEntries().getDataString8(index, colIndex++));
			entry.setChr(ds.getEntries().getDataByte(index, colIndex++));
			entry.setPosition(ds.getEntries().getDataInt(index, colIndex++));
			List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
			if (extraMetrics != null) {
				entry.addMetrics(extraMetrics);
			}
		}
		return entry;
	}

        public ChromosomeSegmentData getChromosomeSegmentEntry(MultiDataType dataType, int index)
			throws IOException, UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            ChromosomeSegmentData entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                    int colIndex = 0;
                    entry = new ChromosomeSegmentData();
                    entry.setSegmentId(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setChr(ds.getEntries().getDataUByte(index, colIndex++));
                    entry.setStartPosition(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setStopPosition(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setMarkerCount(ds.getEntries().getDataInt(index, colIndex++));
                    entry.setMeanMarkerDistance(ds.getEntries().getDataUInt(index, colIndex++));
                    List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
                    if (extraMetrics != null) {
                            entry.addMetrics(extraMetrics);
                    }
            }
            return entry;
        }

        public ChromosomeSegmentDataEx getChromosomeSegmentEntryEx(MultiDataType dataType, int index)
			throws IOException, UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            ChromosomeSegmentDataEx entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                    int colIndex = 0;
                    entry = new ChromosomeSegmentDataEx();
                    entry.setSegmentId(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setReferenceSampleKey(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setFamilialSampleKey(ds.getEntries().getDataUInt(index, colIndex++));                
                    entry.setChr(ds.getEntries().getDataUByte(index, colIndex++));
                    entry.setStartPosition(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setStopPosition(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setCall(ds.getEntries().getDataUByte(index, colIndex++));
                    entry.setConfidence(ds.getEntries().getDataFloat(index, colIndex++));
                    entry.setMarkerCount(ds.getEntries().getDataInt(index, colIndex++));
                    entry.setHomozygosity(ds.getEntries().getDataFloat(index, colIndex++));
                    entry.setHeterozygosity(ds.getEntries().getDataFloat(index, colIndex++));
                    List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
                    if (extraMetrics != null) {
                            entry.addMetrics(extraMetrics);
                    }
            }
            return entry;
        }

        public ChromosomeMultiDataSummaryData getChromosomeSummaryEntry(MultiDataType dataType, int index)
			throws IOException, UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            ChromosomeMultiDataSummaryData entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                    int colIndex = 0;
                    entry = new ChromosomeMultiDataSummaryData();
                    entry.setChr(ds.getEntries().getDataUByte(index, colIndex++));
                    entry.setDisplay(ds.getEntries().getDataString8(index, colIndex++));
                    entry.setStartIndex(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setMarkerCount(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setMinSignal(ds.getEntries().getDataFloat(index, colIndex++));
                    entry.setMaxSignal(ds.getEntries().getDataFloat(index, colIndex++));
                    entry.setMedianCnState(ds.getEntries().getDataFloat(index, colIndex++));
                    entry.setHomFrequency(ds.getEntries().getDataFloat(index, colIndex++));
                    entry.setHetFrequency(ds.getEntries().getDataFloat(index, colIndex++));
                    List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex++);
                    if (extraMetrics != null) {
                            entry.addMetrics(extraMetrics);
                    }
            }
            return entry;
        }

        public FamilialSample getFamilialSampleEntry(MultiDataType dataType, int index)        
			throws IOException, UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            FamilialSample entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                    int colIndex = 0;
                    entry = new FamilialSample();
                    entry.setSampleKey(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setArrID(ds.getEntries().getDataString8(index, colIndex++));
                    entry.setChpID(ds.getEntries().getDataString8(index, colIndex++));
                    entry.setChpFilename(ds.getEntries().getDataString16(index, colIndex++));
                    entry.setRole(ds.getEntries().getDataString8(index, colIndex++));
                    UByte rv = ds.getEntries().getDataUByte(index, colIndex++);
                    entry.setRoleValidity(rv.equals(1));
                    entry.setRoleConfidence(ds.getEntries().getDataFloat(index, colIndex++));
            }
            return entry;
        }

        public FamilialSegmentOverlap getFamilialSegmentOverlapEntry(MultiDataType dataType, int index)
			throws IOException, UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            FamilialSegmentOverlap entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                    int colIndex = 0;
                    entry = new FamilialSegmentOverlap();
                    entry.setSegmentType(ds.getEntries().getDataString8(index, colIndex++));
                    entry.setReferenceSampleKey(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setReferenceSegmentID(ds.getEntries().getDataString8(index, colIndex++));
                    entry.setFamilialSampleKey(ds.getEntries().getDataUInt(index, colIndex++));
                    entry.setFamilialSegmentID(ds.getEntries().getDataString8(index, colIndex++));
            }
            return entry;
        }

        public AllelePeaks getAllelePeaksEntry(MultiDataType dataType, int index)
			throws IOException, UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            AllelePeaks entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                int colIndex = 0;
                entry = new AllelePeaks();
                entry.setName(ds.getEntries().getDataString8(index, colIndex++));
                entry.setChr(ds.getEntries().getDataByte(index, colIndex++));
                entry.setPosition(ds.getEntries().getDataUInt(index, colIndex++));
                List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
                if (extraMetrics != null) {
                    entry.addMetrics(extraMetrics);
                }
            }
            return entry;
        }

        public MarkerABSignals getMarkerABSignalsEntry(MultiDataType dataType, int index)
                throws IOException, UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            MarkerABSignals entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                int colIndex = 0;
                entry = new MarkerABSignals();
                entry.setIndex(ds.getEntries().getDataUInt(index, colIndex++));
                List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
                if (extraMetrics != null) {
                    entry.addMetrics(extraMetrics);
                }
            }
            return entry;
        }
        
    /**
     * Returns cyto genotyping call data for the given row.
     * @throws IOException
     * @throws UnsignedOutOfLimitsException
     */
  	public CytoGenotypingCall getCytoGenotypingCall(MultiDataType dataType,int rowIndex) throws IOException, UnsignedOutOfLimitsException {
			DataSetInfo ds = openMultiDataDataSet(dataType);
			CytoGenotypingCall entry = null;
		    if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			    int colIndex = 0;
			    entry = new CytoGenotypingCall();
			    entry.setIndex(ds.getEntries().getDataInt(rowIndex, colIndex++));
			    entry.setCall(ds.getEntries().getDataByte(rowIndex, colIndex++));
			    entry.setConfidence(ds.getEntries().getDataFloat(rowIndex, colIndex++));
			    entry.setForcedCall(ds.getEntries().getDataByte(rowIndex, colIndex++));
			    entry.setASignal(ds.getEntries().getDataFloat(rowIndex, colIndex++));
			    entry.setBSignal(ds.getEntries().getDataFloat(rowIndex, colIndex++));
			    entry.setSignalStrength(ds.getEntries().getDataFloat(rowIndex, colIndex++));
			    entry.setContrast(ds.getEntries().getDataFloat(rowIndex, colIndex++));
			    List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, rowIndex, colIndex);
			    if (extraMetrics != null) {
			        entry.addMetrics(extraMetrics);
			    }
			}
    		return entry;
		}
        
	/**
	 * Get the extra metric columns.
	 * 
	 * @param ds
	 *          The data set info.
	 * @param rowIndex
	 *          The row index.
	 * @param colIndex
	 *          The column index
	 */
	private List<ParameterNameValue> getExtraMetricEntries(DataSetInfo ds, int rowIndex, int colIndex)
			throws IOException, UnsignedOutOfLimitsException {
		int ncols = ds.getMetricColumns().size();
		if (ncols == 0) {
			return null;
		}
		List<ParameterNameValue> metrics = new ArrayList<ParameterNameValue>();
		for (int icol = 0; icol < ncols; icol++) {
			ParameterNameValue nv = new ParameterNameValue();
			ColumnInfo cinfo = ds.getMetricColumns().get(icol);
			nv.setName(cinfo.getName());
			switch (cinfo.getColumnType()) {
			case ByteColType: {
				byte val = ds.getEntries().getDataByte(rowIndex, colIndex++);
				nv.setValueInt8(val);
			}
				break;

			case UByteColType: {
				UByte val = new UByte((short)(0xFF & (int)ds.getEntries().getDataByte(rowIndex, colIndex++)));
				nv.setValueUInt8(val);
			}
				break;

			case ShortColType: {
				short val = ds.getEntries().getDataShort(rowIndex, colIndex++);
				nv.setValueInt16(val);
			}
				break;

			case UShortColType: {
				UShort val = new UShort(0xFFFF & (int)ds.getEntries().getDataShort(rowIndex, colIndex++));
				nv.setValueUInt16(val);
			}
				break;

			case IntColType: {
				int val = ds.getEntries().getDataInt(rowIndex, colIndex++);
				nv.setValueInt32(val);
			}
				break;

			case UIntColType: {
				UInt val = new UInt(0xFFFFFFFFL & (long)ds.getEntries().getDataInt(rowIndex, colIndex++));
				nv.setValueUInt32(val);
			}
				break;

			case FloatColType: {
				float val = ds.getEntries().getDataFloat(rowIndex, colIndex++);
				nv.setValueFloat(val);
			}
				break;

			case ASCIICharColType: {
				String val = ds.getEntries().getDataString8(rowIndex, colIndex++);
				nv.setValueAscii(val);
			}
				break;

			case UnicodeCharColType: {
				String val = ds.getEntries().getDataString16(rowIndex, colIndex++);
				nv.setValueText(val);
			}
				break;
			}
			metrics.add(nv);
		}
		return metrics;
	}

	/**
	 * gets the call of the probe set.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 * @return The call.
	 */
	public byte getGenoCall(MultiDataType dataType, int index) throws IOException, UnsignedOutOfLimitsException {
		byte call = 0;
		DataSetInfo ds = openMultiDataDataSet(dataType);
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			call = ds.getEntries().getDataByte(index, 1);
		}
		return call;
	}

	/**
	 * gets the confidence in the call of the probe set.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 * @return The confidence.
	 */
	public float getGenoConfidence(MultiDataType dataType, int index) throws IOException, UnsignedOutOfLimitsException {
		float conf = 0.0f;
		DataSetInfo ds = openMultiDataDataSet(dataType);
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			conf = ds.getEntries().getDataFloat(index, 2);
		}
		return conf;
	}

	/**
	 * Gets the quantification of the probe set.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 * @return The quantification.
	 */
	public float getExpressionQuantification(MultiDataType dataType, int index) throws IOException, UnsignedOutOfLimitsException {
		float quant = 0.0f;
		DataSetInfo ds = openMultiDataDataSet(dataType);
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			quant = ds.getEntries().getDataFloat(index, 1);
		}
		return quant;
	}

	/**
	 * get the probe set name.
	 * 
	 * @param dataType
	 *          The data type
	 * @param index
	 *          The row index.
	 * @return The probe set name.
	 */
	public String getProbeSetName(MultiDataType dataType, int index) throws IOException, UnsignedOutOfLimitsException {
		String name = null;
		DataSetInfo ds = openMultiDataDataSet(dataType);
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			name = ds.getEntries().getDataString8(index, 0);
		}
		return name;
	}

	/**
	 * Opens a group for reading.
	 * 
	 * @param dataType
	 *          The data type
	 */
	public DataSetInfo openMultiDataDataSet(MultiDataType dataType) {
		if (dataSetInfo.containsKey(dataType)) {
			return dataSetInfo.get(dataType);
		}
		else {
			try {
				DataSetInfo ds = new DataSetInfo();
                                
        if (dataTypeGroupNames.isEmpty() == true)
        {
            Map<String, MultiDataType> nameTypeMap = new HashMap<String, MultiDataType>();
            for (int iname=0; iname<MultiDataDataSetNames.length; iname++)
                nameTypeMap.put(MultiDataDataSetNames[iname], MultiDataDataTypes[iname]);
            int ng = genericData.getHeader().getDataGroupCnt();
            for (int ig=0; ig<ng; ig++)
            {
                DataGroupHeader dh = genericData.getHeader().getDataGroup(ig);
                int ns = dh.getDataSetCnt();
                for (int is=0; is<ns; is++)
                {
                    DataSetHeader h = dh.getDataSet(is);
                    dataTypeGroupNames.put(nameTypeMap.get(h.getName()), dh.getName());
                }
            }
        }
                                
				ds.setEntries(genericData.getDataSet(dataTypeGroupNames.get(dataType), MultiDataDataSetNames[dataType.ordinal()]));
				if (ds.getEntries() != null) {
					ds.getEntries().open();
					int ncols = ds.getEntries().getHeader().getColumnCnt();
					ds.clearMetricColumns();
					int startCol = 0;
					if ((dataType == MultiDataType.ExpressionMultiDataType)
							|| (dataType == MultiDataType.ExpressionControlMultiDataType)) {
						startCol = 2;
					}
					else if ((dataType == MultiDataType.GenotypeMultiDataType)
							|| (dataType == MultiDataType.GenotypeControlMultiDataType)) {
						startCol = 3;
					}
					else if (dataType == MultiDataType.CopyNumberMultiDataType) {
						startCol = 3;
					}
					else if (dataType == MultiDataType.CytoMultiDataType) {
						startCol = 6;
					}
					else if (dataType == MultiDataType.CopyNumberVariationMultiDataType) {
						startCol = 4;
					}
          else if (dataType == MultiDataType.DmetCopyNumberMultiDataType) {
                  startCol = 7;
          }
          else if (dataType == MultiDataType.DmetMultiAllelicMultiDataType) {
                  startCol = 17;
          }
          else if (dataType == MultiDataType.DmetBiAllelicMultiDataType ||
          				 dataType == MultiDataType.CytoGenotypingCallMultiDataType) 
          {
                  startCol = 8;
          }

          else if (dataType == MultiDataType.ChromosomeSummaryMultiDataType) {
                  startCol = 9;
          }
          else if (dataType == MultiDataType.SegmentCNMultiDataType ||
              dataType == MultiDataType.SegmentLOHMultiDataType ||
              dataType == MultiDataType.SegmentCNNeutralLOHMultiDataType ||
              dataType == MultiDataType.SegmentNormalDiploidMultiDataType ||
              dataType == MultiDataType.SegmentNoCallMultiDataType ||
              dataType == MultiDataType.SegmentMosaicismMultiDataType)
          {
                  startCol = 6;
          }
          else if (dataType == MultiDataType.SegmentGenotypeConcordanceMultiDataType ||
              dataType == MultiDataType.SegmentGenotypeDiscordanceMultiDataType ||
              dataType == MultiDataType.SegmentCNLossLOHConcordanceMultiDataType ||
              dataType == MultiDataType.SegmentCNNeutralLOHConcordanceMultiDataType ||
              dataType == MultiDataType.SegmentHeteroUPDMultiDataType ||
              dataType == MultiDataType.SegmentIsoUPDMultiDataType ||
              dataType == MultiDataType.SegmentDenovoCopyNumberMultiDataType ||
              dataType == MultiDataType.SegmentHemizygousParentOfOriginMultiDataType)
          {
                  startCol = 11;
          }
          else if (dataType == MultiDataType.FamilialSegmentOverlapsMultiDataType)
          {
                  startCol = 5;
          }
          else if (dataType == MultiDataType.FamilialSamplesMultiDataType)
          {
                  startCol = 7;
          }
          else if (dataType == MultiDataType.AllelePeaksMultiDataType)
          {
              startCol = 3;
          }
          else if (dataType == MultiDataType.MarkerABSignalsMultiDataType)
          {
              startCol = 1;
          }
          for (int icol = startCol; icol < ncols; icol++) {
                  ds.addMetricColumn((ds.getEntries().getHeader().getColumnInfo(icol)));
          }
          dataSetInfo.put(dataType, ds);
          return ds;
        }
			}
			catch (Throwable t)
			{
			}
		}
		return null;
	}

	/**
	 * get the length of the metric columns.
	 * 
	 * @param col
	 *          The column index (of the metric columns)
	 * @return The length.
	 */
	public int getMetricColumnLength(MultiDataType dataType, int col) {
		DataSetInfo ds = openMultiDataDataSet(dataType);
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			ColumnInfo info = ds.getMetricColumns().get(col);
			return info.getLength();
		}
		return 0;
	}

	/**
	 * Get the length of the metric columns.
	 * 
	 * @param dataType
	 *          The data type
	 * @return The number of columns.
	 */
	public int getNumMetricColumns(MultiDataType dataType) {
		DataSetInfo ds = openMultiDataDataSet(dataType);
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			return ds.getMetricColumns().size();
		}
		return 0;
	}

	/**
	 * Get the metric column name.
	 * 
	 * @param dataType
	 *          The data type
	 * @param colIndex
	 *          the metric column index
	 * @return The column name
	 */
	public String getMetricColumnName(MultiDataType dataType, int colIndex) {
		DataSetInfo ds = openMultiDataDataSet(dataType);
		if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
			ColumnInfo col = ds.getMetricColumns().get(colIndex);
			return col.getName();
		}
		return null;
	}
        
        /** Gets the probe set data.
        * @param dataType The data type
        * @param index The row index.
        */
        public DmetCopyNumberData getDmetCopyNumberEntry(MultiDataType dataType, int index) throws IOException,
			UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            DmetCopyNumberData entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                int colIndex = 0;
                entry = new DmetCopyNumberData();
                entry.setName(ds.getEntries().getDataString8(index, colIndex++));
                entry.setCall(ds.getEntries().getDataShort(index, colIndex++));
                entry.setConfidence(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setForce(ds.getEntries().getDataShort(index, colIndex++));
                entry.setEstimate(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setLower(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setUpper(ds.getEntries().getDataFloat(index, colIndex++));
                List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
                if (extraMetrics != null) {
                        entry.addMetrics(extraMetrics);
                }
            }
            return entry;
        }

        /** Gets the probe set data.
        * @param dataType The data type
        * @param index The row index.
        */
        public DmetMultiAllelicData getDmetMultiAllelicEntry(MultiDataType dataType, int index) throws IOException,
			UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            DmetMultiAllelicData entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                int colIndex = 0;
                entry = new DmetMultiAllelicData();
                entry.setName(ds.getEntries().getDataString8(index, colIndex++));
                entry.setCall(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setConfidence(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setForce(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setAlleleCount(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setSignalA(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setSignalB(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setSignalC(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setSignalD(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setSignalE(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setSignalF(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setContextA(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setContextB(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setContextC(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setContextD(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setContextE(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setContextF(ds.getEntries().getDataUByte(index, colIndex++));
                List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
                if (extraMetrics != null) {
                        entry.addMetrics(extraMetrics);
                }
            }
            return entry;
        }

        /** Gets the probe set data.
        * @param dataType The data type
        * @param index The row index.
        */
        public DmetBiAllelicData getDmetBiAllelicEntry(MultiDataType dataType, int index) throws IOException,
			UnsignedOutOfLimitsException {
            DataSetInfo ds = openMultiDataDataSet(dataType);
            DmetBiAllelicData entry = null;
            if ((ds != null) && (ds.getEntries() != null) && ds.getEntries().isOpen()) {
                int colIndex = 0;
                entry = new DmetBiAllelicData();
                entry.setName(ds.getEntries().getDataString8(index, colIndex++));
                entry.setCall(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setConfidence(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setForce(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setSignalA(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setSignalB(ds.getEntries().getDataFloat(index, colIndex++));
                entry.setContextA(ds.getEntries().getDataUByte(index, colIndex++));
                entry.setContextB(ds.getEntries().getDataUByte(index, colIndex++));
                List<ParameterNameValue> extraMetrics = getExtraMetricEntries(ds, index, colIndex);
                if (extraMetrics != null) {
                        entry.addMetrics(extraMetrics);
                }
            }
            return entry;
        }

};
