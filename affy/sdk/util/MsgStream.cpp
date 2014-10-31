////////////////////////////////////////////////////////////////
//
// Copyright (C) 2005 Affymetrix, Inc.
//
// This program is free software; you can redistribute it and/or modify 
// it under the terms of the GNU General Public License (version 2) as 
// published by the Free Software Foundation.
// 
// This program is distributed in the hope that it will be useful, 
// but WITHOUT ANY WARRANTY; without even the implied warranty of 
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU 
// General Public License for more details.
// 
// You should have received a copy of the GNU General Public License 
// along with this program;if not, write to the 
// 
// Free Software Foundation, Inc., 
// 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
//
////////////////////////////////////////////////////////////////

/**
 * @file   MsgStream.cpp
 * @author Chuck Sugnet
 * @date   Mon Jul 10 16:30:07 2006
 * 
 * @brief  File for handling messages and printing them to the stream.
 * 
 * 
 */

//
#include "util/MsgStream.h"
//
#include "util/Err.h"
#include "util/Util.h"

/** 
 * A message to be processed by the stream.
 * 
 * @param level - What level of verbosity is associated with this message, higher number == more verbosity.
 * @param msg - Message that is to be processed.
 * @param delimiter - Should a delimiter be emitted as well?
 */
void MsgStream::message(int level, const std::string &msg, bool delimiter) {
  if(level <= m_Verbosity && m_Out != NULL) {
    if(!m_Out->good()) {
      Err::errAbort("MsgStream::message() - problem writing to stream.");
    }
    (*m_Out) << msg;
    if(delimiter)
      (*m_Out) << std:: endl;
  }
}
