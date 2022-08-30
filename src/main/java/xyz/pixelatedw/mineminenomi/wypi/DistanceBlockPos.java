/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ class DistanceBlockPos
/*    */   extends BlockPos
/*    */ {
/*    */   public int hash;
/*    */   
/*    */   public DistanceBlockPos(double xIn, double yIn, double zIn, int hash) {
/* 43 */     super(xIn, yIn, zIn);
/* 44 */     this.hash = hash;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 50 */     return this.hash;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\DistanceBlockPos.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */