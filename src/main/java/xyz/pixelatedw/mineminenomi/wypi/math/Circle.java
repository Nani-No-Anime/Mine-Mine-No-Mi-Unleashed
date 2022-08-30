/*    */ package xyz.pixelatedw.mineminenomi.wypi.math;
/*    */ 
/*    */ 
/*    */ public class Circle
/*    */ {
/*    */   public static void generate(int x0, int z0, int radius, ICircle callback) {
/*  7 */     int error = -radius;
/*  8 */     int x = radius;
/*  9 */     int z = 0;
/*    */     
/* 11 */     while (x >= z) {
/*    */       
/* 13 */       generateBlocks(x0, z0, x, z, callback);
/* 14 */       if (x != z)
/*    */       {
/* 16 */         generateBlocks(x0, z0, z, x, callback);
/*    */       }
/*    */       
/* 19 */       error += z;
/* 20 */       z++;
/* 21 */       error += z;
/*    */       
/* 23 */       if (error >= 0) {
/*    */         
/* 25 */         error -= x;
/* 26 */         x--;
/* 27 */         error -= x;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public static void generateFilled(int x0, int z0, int radius, ICircle callback) {
/* 34 */     int error = -radius;
/* 35 */     int x = radius;
/* 36 */     int z = 0;
/*    */     
/* 38 */     while (x >= z) {
/*    */       
/* 40 */       generateLines(x0, z0, x, z, callback);
/* 41 */       if (x != z)
/*    */       {
/* 43 */         generateLines(x0, z0, z, x, callback);
/*    */       }
/*    */       
/* 46 */       error += z;
/* 47 */       z++;
/* 48 */       error += z;
/*    */       
/* 50 */       if (error >= 0) {
/*    */         
/* 52 */         error -= x;
/* 53 */         x--;
/* 54 */         error -= x;
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private static void generateBlocks(int x0, int z0, int x, int z, ICircle callback) {
/* 61 */     callback.call(x0 + x, z0 + z);
/* 62 */     if (x != 0)
/*    */     {
/* 64 */       callback.call(x0 - x, z0 + z);
/*    */     }
/*    */     
/* 67 */     if (z != 0)
/*    */     {
/* 69 */       callback.call(x0 + x, z0 - z);
/*    */     }
/*    */     
/* 72 */     if (x != 0 && z != 0)
/*    */     {
/* 74 */       callback.call(x0 - x, z0 - z);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static void generateLines(int x0, int z0, int x, int z, ICircle callback) {
/* 80 */     line(x0 - x, z0 + z, x0 + x, callback);
/* 81 */     if (x != 0 && z != 0)
/*    */     {
/* 83 */       line(x0 - x, z0 - z, x0 + x, callback);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   private static void line(int x0, int z0, int x1, ICircle callback) {
/* 89 */     for (int x = x0; x <= x1; x++)
/*    */     {
/* 91 */       callback.call(x, z0);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\math\Circle.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */