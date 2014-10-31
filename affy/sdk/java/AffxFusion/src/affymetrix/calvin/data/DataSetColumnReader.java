/////////////////////////////////////////////////////////////////////
// (C) Copyright 2009, Affymetrix, Inc.
// All rights reserved. Confidential. Except as pursuant
// to a written agreement with Affymetrix, this software may
// not be used or distributed. This software may be covered
// by one or more patents.
//
// "GeneChip", "Affymetrix" and the Affymetrix logos, and
// Affymetrix user interfaces are trademarks used by Affymetrix.
//////////////////////////////////////////////////////////////////////
package affymetrix.calvin.data;

import affymetrix.calvin.data.ColumnInfo.DataSetColumnTypes;
import affymetrix.calvin.exception.ColumnIndexOutOfBoundsException;
import affymetrix.calvin.exception.RowIndexOutOfBoundsException;
import static affymetrix.calvin.data.ColumnInfo.DataSetColumnTypes.*;
import affymetrix.calvin.exception.UnsignedOutOfLimitsException;
import java.io.IOException;

/**
 * Utilities to read data from a {@link DataSet} in a column-based fashion.
 *
 * @author Garret Wilson
 * @author Ed Erwin
 */
public class DataSetColumnReader {

  /**
   * Retrieves the floats in a particular column from the given rows.
   * @param dataSet The data set from which to retrieve the data.
   * @param column the zero-based index of the column
   * @param rowStart the zero-based index of the first row, inclusive
   * @param rowEnd the zero-based index of the last row, exclusive
   * @return The float values of the data in the given column in the data set.
   * @throws NullPointerException if the given data set is <code>null</code>.
   * @throws IllegalArgumentException if the given column type is not a {@link DataSetColumnTypes#FloatColType}.
   * @throws IOException if there is an error reading from the data set.
   * @throws UnsignedOutOfLimitsException if an unsigned value was outside its allowed range.
   * @throws RowIndexOutOfBoundsException if either of the row values is
   *   not valid, or if rowEnd is greater than rowStart.
   * @throws ColumnIndexOutOfBoundsException if the column value is
   *   not valid.
   */
  public final static float[] getDataFloats(final DataSet dataSet, final int column,
      final int rowStart, final int rowEnd) throws IOException, UnsignedOutOfLimitsException, RowIndexOutOfBoundsException, ColumnIndexOutOfBoundsException {

    checkBounds(dataSet, rowStart, rowEnd, column);

    final float[] floats = new float[rowEnd - rowStart]; //create an array of floats
    final DataSetColumnTypes columnType = dataSet.getHeader().getColumnInfo(column).getColumnType(); //get type of column

    if (! FloatColType.equals(columnType)) {
      throw new IllegalArgumentException("Invalid column float type: " + columnType);
    }

    for (int row = rowStart; row < rowEnd; ++row) { //for each row
      floats[row - rowStart] = dataSet.getDataFloat(row, column); //read a float
    }

    return floats; //return the floats we read
  }

  /**
   * Retrieves the integers in a particular column from the given rows. If an unsigned integer is larger than Java integers allow, an {@link IOException} will
   * be thrown.
   * @param dataSet The data set from which to retrieve the data.
   * @param column the zero-based index of the column
   * @param rowStart the zero-based index of the first row, inclusive
   * @param rowEnd the zero-based index of the last row, exclusive
   * @return The integers values of the data in the given column in the data set.
   * @throws NullPointerException if the given data set is <code>null</code>.
   * @throws IllegalArgumentException if the given column type is not a {@link DataSetColumnTypes#ByteColType}, {@link DataSetColumnTypes#IntColType},
   *           {@link DataSetColumnTypes#ShortColType}, {@link DataSetColumnTypes#UByteColType}, a {@link DataSetColumnTypes#UIntColType}, or a
   *           {@link DataSetColumnTypes#UShortColType}.
   * @throws IOException if there is an error reading from the data set.
   * @throws UnsignedOutOfLimitsException if an unsigned value was outside its allowed range.
   * @throws RowIndexOutOfBoundsException if either of the row values is
   *   not valid, or if rowEnd is greater than rowStart.
   * @throws ColumnIndexOutOfBoundsException if the column value is
   *   not valid.
   * @see Integer#MAX_VALUE
   */
  public final static int[] getDataIntegers(final DataSet dataSet, final int column,
      final int rowStart, final int rowEnd) throws IOException, UnsignedOutOfLimitsException, RowIndexOutOfBoundsException, ColumnIndexOutOfBoundsException {

    checkBounds(dataSet, rowStart, rowEnd, column);

    final int[] integers = new int[rowEnd - rowStart]; //create an array of ints
    final DataSetColumnTypes columnType = dataSet.getHeader().getColumnInfo(column).getColumnType(); //get type of column

    switch (columnType) { //see which type of column this is
      case ByteColType:
        for (int row = rowStart; row < rowEnd; ++row) { //for each row
          integers[row - rowStart] = dataSet.getDataByte(row, column); //read a byte
        }
        break;

      case IntColType:
        for (int row = rowStart; row < rowEnd; ++row) { //for each row
          integers[row - rowStart] = dataSet.getDataInt(row, column); //read an int
        }
        break;

      case ShortColType:
        for (int row = rowStart; row < rowEnd; ++row) { //for each row
          integers[row - rowStart] = dataSet.getDataShort(row, column); //read a short
        }
        break;

      case UByteColType:
        for (int row = rowStart; row < rowEnd; ++row) { //for each row
          integers[row - rowStart] = dataSet.getDataUByte(row, column).toShort(); //read an unsigned byte
        }
        break;

      case UIntColType:
        for (int row = rowStart; row < rowEnd; ++row) { //for each row
          final long uint = dataSet.getDataUInt(row, column).toLong(); //read an unsigned int

          if (uint > Integer.MAX_VALUE) {//if we can't fit this uint into a Java int
            throw new IOException("Column " + column + " row " + row + " value " + uint
                + " not supported; maximum supported integer value is " + Integer.MAX_VALUE);
          }
          integers[row - rowStart] = (int) uint; //save the uint as an int
        }
        break;

      case UShortColType:
        for (int row = rowStart; row < rowEnd; ++row) { //for each row
          integers[row - rowStart] = dataSet.getDataUShort(row, column).toInt(); //read an unsigned short
        }
        break;

      default: //if this is any other column type
        throw new IllegalArgumentException("Invalid column integer type: " + columnType);
    }
    return integers; //return the integers we read
  }


  /**
   * Retrieves the unsigned integer data in a particular column from the given rows
   * as an array of java "long".
   * @param dataSet The data set from which to retrieve the data.
   * @param column the zero-based index of the column
   * @param rowStart the zero-based index of the first row, inclusive
   * @param rowEnd the zero-based index of the last row, exclusive
   * @return The integers values of the data in the given column in the data set.
   * @throws NullPointerException if the given data set is <code>null</code>.
   * @throws IllegalArgumentException if the given column type is not a
   *    {@link DataSetColumnTypes#UIntColType}.
   * @throws IOException if there is an error reading from the data set.
   * @throws UnsignedOutOfLimitsException if an unsigned value was outside its allowed range.
   * @throws RowIndexOutOfBoundsException if either of the row values is
   *   not valid, or if rowEnd is greater than rowStart.
   * @throws ColumnIndexOutOfBoundsException if the column value is
   *   not valid.
   * @see Integer#MAX_VALUE
   */
  public final static long[] getDataLongs(final DataSet dataSet, final int column,
      final int rowStart, final int rowEnd) throws IOException, UnsignedOutOfLimitsException, RowIndexOutOfBoundsException, ColumnIndexOutOfBoundsException {

    checkBounds(dataSet, rowStart, rowEnd, column);

    final long[] longs = new long[rowEnd - rowStart]; //create an array
    final DataSetColumnTypes columnType = dataSet.getHeader().getColumnInfo(column).getColumnType(); //get type of column

    if (columnType != UIntColType) {
      throw new IllegalArgumentException("Invalid column type for 'long': " + columnType);
    }

    for (int row = rowStart; row < rowEnd; ++row) { //for each row
      longs[row - rowStart] = dataSet.getDataUInt(row, column).toLong(); //read an unsigned int
    }

    return longs;
  }

  /**
   * Retrieves the String data in a particular column from the given rows.
   * The data is stored in java Strings, which use 2 bytes per character even
   * to hold data which could fit in a single byte per character.
   * @param dataSet The data set from which to retrieve the data.
   * @param column the zero-based index of the column
   * @param rowStart the zero-based index of the first row, inclusive
   * @param rowEnd the zero-based index of the last row, exclusive
   * @return The CharSequence values of the data in the given column in the data set.
   * @throws NullPointerException if the given data set is <code>null</code>.
   * @throws IllegalArgumentException if the given column type is not a {@link DataSetColumnTypes#ASCIICharColType} or a
   *           {@link DataSetColumnTypes#UnicodeCharColType}.
   * @throws IOException if there is an error reading from the data set.
   * @throws UnsignedOutOfLimitsException if an unsigned value was outside its allowed range.
   * @throws RowIndexOutOfBoundsException if either of the row values is
   *   not valid, or if rowEnd is greater than rowStart.
   * @throws ColumnIndexOutOfBoundsException if the column value is
   *   not valid.
   */
  public final static String[] getDataStrings(final DataSet dataSet, final int column,
      final int rowStart, final int rowEnd) throws IOException, UnsignedOutOfLimitsException, RowIndexOutOfBoundsException, ColumnIndexOutOfBoundsException {

    checkBounds(dataSet, rowStart, rowEnd, column);

    final String[] strings = new String[rowEnd - rowStart]; //create an array of Strings
    final DataSetColumnTypes columnType = dataSet.getHeader().getColumnInfo(column).getColumnType(); //get type of column
    switch (columnType) { //see which type of column this is
      case ASCIICharColType: //String8
        for (int row = rowStart; row < rowEnd; ++row) { //for each row
          strings[row - rowStart] = dataSet.getDataString8(row, column);
        }
        break;

      case UnicodeCharColType: //String16
        for (int row = rowStart; row < rowEnd; ++row) { //for each row
          strings[row - rowStart] = dataSet.getDataString16(row, column); //read a UTF-16BE string
        }
        break;

      default: //if this is any other column type
        throw new IllegalArgumentException("Invalid column string type: " + columnType);
    }
    return strings; //return the strings we read
  }

  /**
   * Retrieves the signed or unsigned bytes in a particular column from the given rows.
   * The returned values are bytes, but should be interpreted as signed or 
   * unsigned depending on whether the column type is
   * {@link DataSetColumnTypes#ByteColType} or
   * {@link DataSetColumnTypes#UByteColType}.
   *
   * @param dataSet The data set from which to retrieve the data.
   * @param column the zero-based index of the column
   * @param rowStart the zero-based index of the first row, inclusive
   * @param rowEnd the zero-based index of the last row, exclusive
   * @return The unsigned bytes values of the data in the given column in the data set.
   * @throws NullPointerException if the given data set is <code>null</code>.
   * @throws IllegalArgumentException if the given column type is not
   * {@link DataSetColumnTypes#ByteColType} or {@link DataSetColumnTypes#UByteColType}.
   * @throws IOException if there is an error reading from the data set.
   * @throws UnsignedOutOfLimitsException if an unsigned value was outside its allowed range.
   * @throws RowIndexOutOfBoundsException if either of the row values is
   *   not valid, or if rowEnd is greater than rowStart.
   * @throws ColumnIndexOutOfBoundsException if the column value is
   *   not valid.
   */
  public final static byte[] getDataBytes(final DataSet dataSet, final int column,
      final int rowStart, final int rowEnd) throws IOException, UnsignedOutOfLimitsException, RowIndexOutOfBoundsException, ColumnIndexOutOfBoundsException {

    checkBounds(dataSet, rowStart, rowEnd, column);

    final DataSetColumnTypes columnType = dataSet.getHeader().getColumnInfo(column).getColumnType(); //get type of column
    if(columnType != UByteColType && columnType != ByteColType) {  //if this is not a byte type
      throw new IllegalArgumentException("Invalid column byte type: " + columnType);
    }

    final byte[] bytes = new byte[rowEnd - rowStart]; //create an array
    for (int row = rowStart; row < rowEnd; ++row) { //for each row
      bytes[row - rowStart] = (byte)dataSet.getDataUByte(row, column).toShort(); //read an unsigned byte, but store it in a signed variable
    }
    return bytes; //return the bytes we read
  }

  /**
   * Check that the given column and range of rows is in bounds.
   * Do nothing if it is in bounds, otherwise throw an exception.
   * @param dataSet the DataSet
   * @param rowStart the zero-based index of the first row, inclusive
   * @param rowEnd the zero-based index of the last row, exclusive
   * @param column the zero-based index of the column
   * @throws RowIndexOutOfBoundsException if either of the row values is
   *   not valid, or if rowEnd is greater than rowStart.
   * @throws ColumnIndexOutOfBoundsException if the column value is
   *   not valid.
   */
  private static void checkBounds(DataSet dataSet, int rowStart, int rowEnd, int column) 
    throws RowIndexOutOfBoundsException, ColumnIndexOutOfBoundsException {
    final DataSetHeader header = dataSet.getHeader();
    if (rowStart < 0 || rowStart > rowEnd || rowEnd > header.getRowCnt()) {
      throw new RowIndexOutOfBoundsException();
    }
    if (column < 0 || column > header.getColumnCnt()) {
      throw new ColumnIndexOutOfBoundsException();
    }
  }
}
