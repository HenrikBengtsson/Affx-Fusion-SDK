//
// k/apt-1/affy/sdk/util/AptErrno.h ---
//
// $Id$
//

#ifndef _UTIL_APTERRNO_H_
#define _UTIL_APTERRNO_H_

/// Return codes for methods.
enum AptErr_t {
  // dont use negative values as some systems mess up the signs.
  APT_OK=0,
  // a generic error, for when there isnt a more specific one.
  APT_ERR=1000,
  //
  APT_ERR_ISNULL,
  APT_ERR_NOTFOUND,
  APT_ERR_OUTOFBOUNDS,
  //
  APT_ERR_WRONGTYPE,
  //
  APT_ERR_ACCESS,
  APT_ERR_EXISTS,
  APT_ERR_FS_PERM,
  APT_ERR_FS_STAT,
  APT_ERR_NOTEXISTS,
  APT_ERR_NOTREMOVED,
};

#endif // _UTIL_APTERRNO_H_
