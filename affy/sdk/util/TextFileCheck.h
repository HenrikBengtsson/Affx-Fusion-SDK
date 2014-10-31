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
 * @file   TextFileCheck.h
 * @brief  Class for checking two text files for equality.
 */
#ifndef TEXTFILECHECK_H
#define TEXTFILECHECK_H

#include "util/RegressionCheck.h"
//
#include <cstring>
#include <fstream>
#include <string>
//

/**
 * Class for testing that two text files are equal, except
 * for line endings.
 */
class TextFileCheck : public RegressionCheck
{
public:
  /**
   * Constructor.
   *
   * @param generatedFile File generated by application.
   * @param goldFile Comparison file, assumed to be correct.
   * @param skipLines Number of lines to skip.
   */
  TextFileCheck (const std::string &generatedFile, const std::string &goldFile, const unsigned int skipLines)
    : m_GeneratedFile (generatedFile), m_GoldFile (goldFile), m_SkipLines (skipLines)
  {
        m_Name = Util::fileRoot(generatedFile);
  }

  /**
   * Check that the two files are the same.
   *
   * @param errorMsg Error message generated if the test fails.
   * @return bool Returns true if files the same, else false.
   */
  bool check (std::string& errorMsg)
  {
    // Open files.
    std::ifstream generatedStream (m_GeneratedFile.c_str());
    if (! generatedStream )
    {
      errorMsg = "Unable to open generated file " + m_GeneratedFile;
      return false;
    }
    std::ifstream goldStream (m_GoldFile.c_str());
    if (! goldStream )
    {
      errorMsg = "Unable to open gold file " + m_GoldFile;
      return false;
    }
    unsigned int lineCount = 0;
    const char* lineEndings = "\r\n";
    std::string goldLine, generatedLine;
    while (! goldStream.eof() && ! goldStream.fail())
    {
      getline (goldStream, goldLine);
      getline (generatedStream, generatedLine);
      if (generatedStream.eof() && ! goldStream.eof())
      {
        errorMsg = "The generated file, " + m_GeneratedFile
  	+ ", has fewer lines than the gold file, " + m_GoldFile;
        return false;
      }
      // Skip header lines which need not be equal.
      if (++lineCount > m_SkipLines)
      {
        // Avoid line ending hassles.
        goldLine = goldLine.erase (goldLine.find_last_not_of (lineEndings) + 1);
        generatedLine = generatedLine.erase (generatedLine.find_last_not_of (lineEndings) + 1);
        if (goldLine != generatedLine)
        {
          errorMsg = "Mismatch reading generated file " + m_GeneratedFile
  	  + ":\ngold line: '" + goldLine
  	  + "'\ngenerated line: '" + generatedLine + "'";
          return false;
        }
      }
    }
    // The two files should reach eof at the same time.
    if (! generatedStream.eof())
    {
      errorMsg = "The generated file, " + m_GeneratedFile
        + ", has more lines than the gold file, " + m_GoldFile;
      return false;
    }

    return true;
  }

private:
  /// Name of file generated by application being tested.
  std::string m_GeneratedFile;
  /// Name of file assumed to be correct.
  std::string m_GoldFile;
  /// Number of lines to skip.
  const unsigned int m_SkipLines;
};

#endif /* TEXTFILECHECK_H */
