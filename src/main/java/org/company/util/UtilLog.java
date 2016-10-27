package org.company.util;

import java.util.logging.Logger;

public class UtilLog {
  
  private static final Logger LOGGER = Logger.getLogger("SystemLog"); 

  public static void logError(String msg, Exception e) {
    StringBuffer log = new StringBuffer();
    log.append(msg);
    log.append(e.getMessage());
    LOGGER.severe(log.toString());
  }
}
