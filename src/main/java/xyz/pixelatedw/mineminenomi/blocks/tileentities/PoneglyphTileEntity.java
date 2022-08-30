/*    */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.network.NetworkManager;
/*    */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*    */ import net.minecraft.tileentity.TileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*    */ 
/*    */ public class PoneglyphTileEntity
/*    */   extends TileEntity
/*    */ {
/* 13 */   private Type entryType = Type.CHALLENGE;
/* 14 */   private String entryCategory = "";
/*    */ 
/*    */   
/*    */   public PoneglyphTileEntity() {
/* 18 */     super(ModTileEntities.PONEGLYPH);
/*    */   }
/*    */ 
/*    */   
/*    */   public Type getEntryType() {
/* 23 */     return this.entryType;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEntryType(Type entryType) {
/* 28 */     this.entryType = entryType;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getEntryCategory() {
/* 33 */     return this.entryCategory;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setEntryCategory(String category) {
/* 38 */     this.entryCategory = category;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT write(CompoundNBT nbt) {
/* 44 */     super.write(nbt);
/*    */     
/* 46 */     nbt.putInt("entryType", this.entryType.ordinal());
/* 47 */     nbt.putString("entryCategory", this.entryCategory);
/*    */     
/* 49 */     return nbt;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void read(CompoundNBT nbt) {
/* 55 */     super.read(nbt);
/*    */     
/* 57 */     this.entryType = Type.values()[nbt.getInt("entryType")];
/* 58 */     this.entryCategory = nbt.getString("entryCategory");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public CompoundNBT getUpdateTag() {
/* 64 */     return write(new CompoundNBT());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public SUpdateTileEntityPacket getUpdatePacket() {
/* 71 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 72 */     write(nbttagcompound);
/* 73 */     return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
/* 79 */     read(pkt.getNbtCompound());
/*    */   }
/*    */   
/*    */   public enum Type
/*    */   {
/* 84 */     CHALLENGE;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\PoneglyphTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */