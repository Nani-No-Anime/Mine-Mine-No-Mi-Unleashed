package xyz.pixelatedw.mineminenomi.wypi.debug;

import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;


public class WyDebug
{
  private static Logger logger = Logger.getLogger(APIConfig.projectId);

  
  public static boolean isDebug() {
    return (ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0);
  }



  
  public static String getCallerClassName() {
    StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
    for (int i = 1; i < stElements.length; i++) {
      
      StackTraceElement ste = stElements[i];
      if (!ste.getClassName().equals(WyDebug.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0)
      {
        return ste.getClassName();
      }
    } 
    return null;
  }

  
  public static void debug(Object msg) {
    if (isDebug())
      logger.log(Level.INFO, getCallerClassName() + ": " + String.valueOf(msg)); 
  }
}


