/*    */ package xyz.pixelatedw.mineminenomi.wypi.debug;
/*    */ 
/*    */ import java.lang.management.ManagementFactory;
/*    */ import java.util.logging.Level;
/*    */ import java.util.logging.Logger;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ 
/*    */ 
/*    */ public class WyDebug
/*    */ {
/* 11 */   private static Logger logger = Logger.getLogger(APIConfig.projectId);
/*    */ 
/*    */   
/*    */   public static boolean isDebug() {
/* 15 */     return (ManagementFactory.getRuntimeMXBean().getInputArguments().toString().indexOf("-agentlib:jdwp") > 0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static String getCallerClassName() {
/* 22 */     StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
/* 23 */     for (int i = 1; i < stElements.length; i++) {
/*    */       
/* 25 */       StackTraceElement ste = stElements[i];
/* 26 */       if (!ste.getClassName().equals(WyDebug.class.getName()) && ste.getClassName().indexOf("java.lang.Thread") != 0)
/*    */       {
/* 28 */         return ste.getClassName();
/*    */       }
/*    */     } 
/* 31 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void debug(Object msg) {
/* 36 */     if (isDebug())
/* 37 */       logger.log(Level.INFO, getCallerClassName() + ": " + String.valueOf(msg)); 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\debug\WyDebug.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */