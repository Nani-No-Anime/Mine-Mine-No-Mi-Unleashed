/*    */ package xyz.pixelatedw.mineminenomi.wypi;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ 
/*    */ 
/*    */ public class BlockPlacingHelper
/*    */ {
/*  9 */   private HashSet<BlockPos> blockList = new HashSet<>();
/*    */ 
/*    */   
/*    */   public void setBlockList(HashSet<BlockPos> list) {
/* 13 */     this.blockList = list;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addBlockPos(BlockPos pos, int hash) {
/* 18 */     this.blockList.add(new DistanceBlockPos(pos.getX(), pos.getY(), pos.getZ(), hash));
/*    */   }
/*    */ 
/*    */   
/*    */   public void addBlockPos(double x, double y, double z, int hash) {
/* 23 */     this.blockList.add(new DistanceBlockPos(x, y, z, hash));
/*    */   }
/*    */ 
/*    */   
/*    */   public HashSet<BlockPos> getBlockList() {
/* 28 */     return this.blockList;
/*    */   }
/*    */ 
/*    */   
/*    */   public void clearList() {
/* 33 */     this.blockList = new HashSet<>();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\BlockPlacingHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */