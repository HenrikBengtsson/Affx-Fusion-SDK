#include "util/SocketTextHandler.h"

#include "util/Convert.h"
#include "util/Err.h"
#include "util/Verbose.h"


SocketTextHandler::SocketTextHandler() {
  m_Channels.insert(VERBOSE);
  m_Channels.insert(PROGRESS);
  m_Channels.insert(KILLMSG);
  m_Types.insert(TEXT);
  m_Finished = false;
  m_SuccessfulRun = true;

}

bool SocketTextHandler::hasFinished() {
  return m_Finished;
}

bool SocketTextHandler::successfulRun() {
  return m_SuccessfulRun;
}

void SocketTextHandler::handleMessage(int type, int channel, const std::string &msg) {
  APT_ERR_ASSERT(m_Types.find(type) != m_Types.end(), "Don't support type: " + ToStr(type));
  APT_ERR_ASSERT(m_Channels.find(channel) != m_Channels.end(), "Don't support channel: " + ToStr(channel));
  std::map<std::string,std::string> param = SocketHandler::deserializeMap(msg);
  int verbosity = Convert::toInt(param["VERBOSITY"]);
  if (channel == VERBOSE) {
    Verbose::out(verbosity, param["MSG"], false);
  }
  else if (channel == COMPLETION) {
    Verbose::out(verbosity, "Sucessful Completion", true);
    m_Finished = true;
  }
  else if (channel == PROGRESS) {
    std::string value = param["VALUE"];
    if (value == "BEGIN") {
      int total = Convert::toInt(param["TOTAL"]);
      Verbose::progressBegin(verbosity, param["MSG"], total, 1, total);
    }
    else if(value == "STEP") {
      Verbose::progressStep(verbosity);
    }
    else if(value == "END") {
      Verbose::progressEnd(verbosity, param["MSG"]);
    }
  }
  else if (channel == KILLMSG){
	Verbose::out(verbosity, "Unsuccessful Completion", true);
    m_Finished = true;
	m_SuccessfulRun = false;
  }


  else {
    APT_ERR_ABORT("Don't recognize channel: " + ToStr(channel));
  }
    
}
