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

import java.nio.MappedByteBuffer;
import java.util.List;

import affymetrix.gcos.FileIO;
import affymetrix.portability.DataSizes;
import affymetrix.portability.UByte;

/** This class provides storage for a group of probes, also known as a block. */
public class CDFProbeGroupInformation {

	/** This is the size of the object in a binary CDF file. */
	public static final int PROBE_GROUP_SIZE = (CDFProbeSetNames.MAX_PROBE_SET_NAME_LENGTH + 4 + 4 + 4 + 4 + 1 + 1);

    /** This is the size of the object in a binary CDF file version 2. */
    public static final int PROBE_GROUP_SIZE_V2 = (CDFProbeSetNames.MAX_PROBE_SET_NAME_LENGTH + 4 + 4 + 4 + 4 + 1 + 1 + 2 + 2);

    /** This is the size of the object in a binary CDF file version 3. */
    public static final int PROBE_GROUP_SIZE_V3 = (CDFProbeSetNames.MAX_PROBE_SET_NAME_LENGTH + 4 + 4 + 4 + 4 + 1 + 1 + 2 + 2 + 1 + 1);

	/** The number of probe pairs or probe quartets in the group. */
	private int numLists;

	public int getNumLists() {
		return numLists;
	}

	public void setNumLists(int value) {
		numLists = value;
	}

	/** The number of probes in the group. */
	private int numCells;

	public int getNumCells() {
		return numCells;
	}

	public void setNumCells(int value) {
		numCells = value;
	}

	/** The first probes list index value. */
	private int start;

	public int getStart() {
		return start;
	}

	public void setStart(int value) {
		start = value;
	}

	/** The last probes list index value. */
	private int stop;

	public int getStop() {
		return stop;
	}

	public void setStop(int value) {
		stop = value;
	}

	/** The probe set index associated with the group. */
	private int probeSetIndex;

	public int getProbeSetIndex() {
		return probeSetIndex;
	}

	public void setProbeSetIndex(int value) {
		probeSetIndex = value;
	}

	/** The group index. */
	private int groupIndex;

	public int getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(int value) {
		groupIndex = value;
	}

	/** The name of the group. */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String value) {
		name = value;
	}

	/** The number of cells per list (2 for expression and genotyping, 4 for resquencing). */
	private byte numCellsPerList;

	public byte getNumCellsPerList() {
		return numCellsPerList;
	}

	public void setNumCellsPerList(byte value) {
		numCellsPerList = value;
	}

	/** The direction of the target that the probes are interrogating. */
	private byte direction;

	public byte getDirection() {
		return direction;
	}

	public void setDirection(byte value) {
		direction = value;
	}

    /** The wobble situation. */
    private short wobbleSituation;

    /** The allele code. */
    private short alleleCode;

    /** The channel. */
    private byte channel;

    /** The probe replication type. */
    private byte repType;

    /** The wobble situation */
    public short getWobbleSituation() {
        return wobbleSituation;
    }

    /** The wobble situation */
    public void setWobbleSituation(short w) {
        wobbleSituation = w;
    }

    /** The allele code */
    public short getAlleleCode() {
        return alleleCode;
    }

    /** The allele code */
    public void setAlleleCode(short c) {
        alleleCode = c;
    }

    /** The channel */
    public byte getChannel() {
        return channel;
    }

    /** The channel */
    public void setChannel(byte c) {
        channel = c;
    }

    /** The probe replication type */
    public byte getRepType() {
        return repType;
    }

    /** The probe replication type */
    public void setRepType(byte r) {
        repType = r;
    }

	/** The probes in the group */
	private List<CDFProbeInformation> cells;

	/** A mapped byte buffer for XDA files. */
	private MappedByteBuffer xdaBuffer;

	/** The offset from the map buffer to the probe set information. */
	private int offset;

    /** The version of the CDF file */
    private int version;

	/**
	 * Sets the map and offset for memory mapping.
	 * 
	 * @param buf
	 *          The buffer.
	 * @param o
	 *          The offset to the probe set names.
	 */
	public void setMap(MappedByteBuffer buf, long o, int setIndex, int index, int v) {
		xdaBuffer = buf;
		offset = (int)o;
        version = v;

		int groupOffset = offset;

		probeSetIndex = setIndex;
		groupIndex = index;

		numLists = FileIO.getInt32(xdaBuffer, groupOffset);
		groupOffset += DataSizes.INT_SIZE;

		numCells = FileIO.getInt32(xdaBuffer, groupOffset);
		groupOffset += DataSizes.INT_SIZE;

		numCellsPerList = FileIO.getUInt8(xdaBuffer, groupOffset);
		groupOffset += DataSizes.CHAR_SIZE;

		direction = FileIO.getUInt8(xdaBuffer, groupOffset);
		groupOffset += DataSizes.CHAR_SIZE;

		start = FileIO.getInt32(xdaBuffer, groupOffset);
		groupOffset += DataSizes.INT_SIZE;

		stop = FileIO.getInt32(xdaBuffer, groupOffset);
		groupOffset += DataSizes.INT_SIZE;

		name = FileIO.getFixedString(xdaBuffer, groupOffset, CDFProbeSetNames.MAX_PROBE_SET_NAME_LENGTH);
		groupOffset += CDFProbeSetNames.MAX_PROBE_SET_NAME_LENGTH;

        if (version >= 2)
        {
            wobbleSituation = FileIO.getUInt16(xdaBuffer, groupOffset);
            groupOffset += DataSizes.SHORT_SIZE;
            alleleCode = FileIO.getUInt16(xdaBuffer, groupOffset);
            groupOffset += DataSizes.SHORT_SIZE;
        }

        if (version >= 3)
        {
            channel = FileIO.getUInt8(xdaBuffer, groupOffset);
            groupOffset += DataSizes.CHAR_SIZE;
            repType = FileIO.getUInt8(xdaBuffer, groupOffset);
            groupOffset += DataSizes.CHAR_SIZE;
        }

		// Reset the start/stop
		CDFProbeInformation probeInfo;
		probeInfo = getCell(0);
		start = probeInfo.getListIndex();
		probeInfo = getCell(numCells - 1);
		stop = probeInfo.getListIndex();
	}

	/**
	 * Gets the cell information for the given cell index.
	 * 
	 * @param index
	 *          The zero based index to the cell array.
	 * @return The cell or probe information.
	 */
	public CDFProbeInformation getCell(int index) {
		if (cells != null) {
			return cells.get(index);
		}
		else if (xdaBuffer != null) {
			CDFProbeInformation info = new CDFProbeInformation();
            int groupSize = PROBE_GROUP_SIZE;
            if (version == 2)
                groupSize = PROBE_GROUP_SIZE_V2;
            else if (version > 2)
                groupSize = PROBE_GROUP_SIZE_V3;
            int probeSize = (version < 2 ? CDFProbeInformation.PROBE_SIZE : CDFProbeInformation.PROBE_SIZE_V2);
			int cellOffset = offset + groupSize + (index * probeSize);

			info.setListIndex(FileIO.getInt32(xdaBuffer, cellOffset));
			cellOffset += DataSizes.INT_SIZE;

			info.setX(FileIO.getUInt16(xdaBuffer, cellOffset));
			cellOffset += DataSizes.SHORT_SIZE;

			info.setY(FileIO.getUInt16(xdaBuffer, cellOffset));
			cellOffset += DataSizes.SHORT_SIZE;

			info.setExpos(FileIO.getInt32(xdaBuffer, cellOffset));
			cellOffset += DataSizes.INT_SIZE;

			info.setPBase((char)FileIO.getInt8(xdaBuffer, cellOffset));
			cellOffset += DataSizes.CHAR_SIZE;

			info.setTBase((char)FileIO.getInt8(xdaBuffer, cellOffset));
			cellOffset += DataSizes.CHAR_SIZE;

            if (version >= 2)
            {
                info.setProbeLength(FileIO.getUInt16(xdaBuffer, cellOffset));
                cellOffset += DataSizes.SHORT_SIZE;
                info.setProbeGrouping(FileIO.getUInt16(xdaBuffer, cellOffset));
                cellOffset += DataSizes.SHORT_SIZE;
            }
                        
			return info;
		}
		return null;
	}

	public void setCells(List<CDFProbeInformation> value) {
		cells = value;
	}

	/** Creates a new instance of CDFProbeGroupInformation */
	public CDFProbeGroupInformation() {
		cells = null;
		numLists = 0;
		numCells = 0;
		start = 0;
		stop = 0;
		probeSetIndex = 0;
		groupIndex = 0;
		name = "";
		numCellsPerList = 0;
		direction = DirectionType.NoDirection;
		xdaBuffer = null;
		offset = 0;
        wobbleSituation = 0;
        alleleCode = 0;
        channel = 0;
        repType = 0;
	}

}
