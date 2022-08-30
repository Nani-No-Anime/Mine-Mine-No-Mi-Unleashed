/*    */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DenDenMushiTileEntity
/*    */   extends TileEntity
/*    */ {
/*    */   public DenDenMushiTileEntity() {
/* 15 */     super(ModTileEntities.DEN_DEN_MUSHI);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT getUpdateTag() {
/* 21 */     return write(new CompoundNBT());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public SUpdateTileEntityPacket getUpdatePacket() {
/* 28 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 29 */     write(nbttagcompound);
/* 30 */     return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\DenDenMushiTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */