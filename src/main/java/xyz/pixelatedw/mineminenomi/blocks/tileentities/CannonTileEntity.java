/*    */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*    */ 
/*    */ public class CannonTileEntity
/*    */   extends TileEntity
/*    */ {
/* 12 */   private int gunpowderLoaded = 0;
/*    */   
/*    */   private boolean hasCannonBall = false;
/*    */   
/*    */   public CannonTileEntity() {
/* 17 */     super(ModTileEntities.CANNON);
/*    */   }
/*    */ 
/*    */   
/*    */   public int getGunpowederLoaded() {
/* 22 */     return this.gunpowderLoaded;
/*    */   }
/*    */ 
/*    */   
/*    */   public void addGunpoweder() {
/* 27 */     this.gunpowderLoaded++;
/*    */   }
/*    */ 
/*    */   
/*    */   public void emptyGunpoweder() {
/* 32 */     this.gunpowderLoaded = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasCannonBall() {
/* 37 */     return this.hasCannonBall;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHasCannonBall(boolean hasCannonBall) {
/* 42 */     this.hasCannonBall = hasCannonBall;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT write(CompoundNBT nbt) {
/* 48 */     super.write(nbt);
/* 49 */     nbt.putInt("loadedGunpowder", this.gunpowderLoaded);
/* 50 */     nbt.putBoolean("hasCannonBall", this.hasCannonBall);
/*    */     
/* 52 */     return nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void read(CompoundNBT nbt) {
/* 58 */     super.read(nbt);
/* 59 */     this.gunpowderLoaded = nbt.getInt("loadedGunpowder");
/* 60 */     this.hasCannonBall = nbt.getBoolean("hasCannonBall");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT getUpdateTag() {
/* 66 */     return write(new CompoundNBT());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public SUpdateTileEntityPacket getUpdatePacket() {
/* 73 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 74 */     write(nbttagcompound);
/* 75 */     return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\CannonTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */