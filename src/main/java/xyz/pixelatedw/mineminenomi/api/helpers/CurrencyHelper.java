/*    */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CurrencyHelper
/*    */ {
/*    */   public static final int BELLY_TO_EXTOL = 10000;
/*    */   
/*    */   public static long getExtolFromBelly(long belly) {
/* 12 */     return belly * 10000L;
/*    */   }
/*    */ 
/*    */   
/*    */   public static long getBellyFromExtol(long extol) {
/* 17 */     if (extol < 10000L)
/*    */     {
/* 19 */       return -1L;
/*    */     }
/* 21 */     return extol / 10000L;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\CurrencyHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */