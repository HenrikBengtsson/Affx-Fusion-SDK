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

package affymetrix.portability;

import java.util.List;

import affymetrix.calvin.exception.UnsignedOutOfLimitsException;

public abstract class UnsignedBase {

	protected UnsignedBase() {
	}

	protected static byte[] toUnsignedByteArray(long value, int sz) {
		byte[] b = new byte[sz];
		for (int i = 0; i < sz; i++) {
			int offset = (sz - 1 - i) * 8;
			b[i] = (byte)((value >>> offset) & 0xFF);
		}
		return b;
	}

	protected static long unsignedBytesToLong(byte[] b) {
		long result = 0;
		result |= b[0] & 0xFF;
		for (int i = 1; i < b.length; i++) {
			result <<= 8;
			result |= b[i] & 0xFF;
		}
		return result;
	}

	protected static void addBytes(byte[] bytes, List<Byte> buf) {
		for (int i = 0; i < bytes.length; i++) {
			buf.add(bytes[i]);
		}
	}

	/**
	 * In limits.
	 * 
	 * @param i
	 *          the i
	 * 
	 * @return true, if successful
	 * 
	 * @throws UnsignedOutOfLimitsException
	 *           the unsigned out of limits exception
	 */
	protected boolean inLimits(long i, long max) throws UnsignedOutOfLimitsException {
		if (i < 0) {
			throw new UnsignedOutOfLimitsException("An unsigned number can't be less than zero.", 1);
		}
		if (i >= max) {
			throw new UnsignedOutOfLimitsException("An unsigned number must be less than " + max, 1);
		}
		return true;
	}

	protected boolean inLimits(byte[] b, int size) throws UnsignedOutOfLimitsException {
		if (b.length == 0) {
			throw new UnsignedOutOfLimitsException("Can't have zero bytes.", 1);
		}
		if (b.length > size) {
			throw new UnsignedOutOfLimitsException("Can't have more than " + size + " bytes.", 1);
		}
		return true;
	}

	public abstract void zero();

	public abstract byte[] getBytes();
}
