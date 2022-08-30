/*    */ package xyz.pixelatedw.mineminenomi.wypi.math;
/*    */ 
/*    */ import java.util.Hashtable;
/*    */ 
/*    */ public class Sphere
/*    */ {
/*  7 */   private static SphereGenerator instance = new SphereGenerator();
/*    */ 
/*    */   
/*    */   public static void generate(int x0, int y0, int z0, int radius, ISphere callback) {
/* 11 */     instance.pregenerate(x0, y0, z0, callback, false);
/* 12 */     Circle.generate(x0, z0, radius, instance);
/*    */   }
/*    */ 
/*    */   
/*    */   public static void generateFilled(int x0, int y0, int z0, int radius, ISphere callback) {
/* 17 */     instance.pregenerate(x0, y0, z0, callback, true);
/* 18 */     Circle.generate(x0, z0, radius, instance);
/*    */   }
/*    */   
/*    */   private static class SphereGenerator
/*    */     implements ICircle {
/* 23 */     private Hashtable<String, Boolean> mTouched = new Hashtable<>();
/*    */     
/*    */     private ISphere mCallback;
/*    */     private int mX0;
/*    */     private int mY0;
/*    */     private int mZ0;
/*    */     private int mTemp;
/*    */     private boolean mFilled;
/*    */     
/*    */     public void pregenerate(int x0, int y0, int z0, ISphere callback, boolean filled) {
/* 33 */       this.mX0 = x0;
/* 34 */       this.mY0 = y0;
/* 35 */       this.mZ0 = z0;
/* 36 */       this.mCallback = callback;
/* 37 */       this.mTouched.clear();
/* 38 */       this.mFilled = filled;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void call(int x1, int z1) {
/* 44 */       this.mTemp = z1;
/* 45 */       Circle.generate(0, this.mY0, x1 - this.mX0, new ICircle()
/*    */           {
/*    */             
/*    */             public void call(int x2, int y2)
/*    */             {
/* 50 */               Sphere.SphereGenerator.this.updateBlock(Sphere.SphereGenerator.this.mX0 + x2, y2, Sphere.SphereGenerator.this.mTemp);
/*    */             }
/*    */           });
/* 53 */       this.mTemp = x1;
/* 54 */       if (this.mFilled) {
/*    */         
/* 56 */         Circle.generateFilled(0, this.mY0, z1 - this.mZ0, new ICircle()
/*    */             {
/*    */               
/*    */               public void call(int x2, int y2)
/*    */               {
/* 61 */                 Sphere.SphereGenerator.this.updateBlock(Sphere.SphereGenerator.this.mTemp, y2, Sphere.SphereGenerator.this.mZ0 + x2);
/*    */               }
/*    */             });
/*    */       }
/*    */       else {
/*    */         
/* 67 */         Circle.generate(0, this.mY0, z1 - this.mZ0, new ICircle()
/*    */             {
/*    */               
/*    */               public void call(int x2, int y2)
/*    */               {
/* 72 */                 Sphere.SphereGenerator.this.updateBlock(Sphere.SphereGenerator.this.mTemp, y2, Sphere.SphereGenerator.this.mZ0 + x2);
/*    */               }
/*    */             });
/*    */       } 
/*    */     }
/*    */ 
/*    */     
/*    */     private void updateBlock(int x, int y, int z) {
/* 80 */       String key = x + " " + y + "  " + z;
/* 81 */       if (this.mTouched.containsKey(key)) {
/*    */         return;
/*    */       }
/*    */ 
/*    */       
/* 86 */       this.mTouched.put(key, Boolean.valueOf(true));
/*    */       
/* 88 */       this.mCallback.call(x, y, z);
/*    */     }
/*    */     
/*    */     private SphereGenerator() {}
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\math\Sphere.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */