/*     */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ 
/*     */ 
/*     */ public class AblProtectionTileEntity
/*     */   extends TileEntity
/*     */ {
/*  17 */   private int protectedSize = 10;
/*     */   
/*     */   private UUID uuid;
/*     */   
/*     */   public AblProtectionTileEntity() {
/*  22 */     super(ModTileEntities.ABILITY_PROTECTION);
/*     */   }
/*     */ 
/*     */   
/*     */   public void resizeProtection(PlayerEntity player, BlockPos pos, int id, int size) {
/*  27 */     int posX = pos.getX();
/*  28 */     int posY = pos.getY();
/*  29 */     int posZ = pos.getZ();
/*     */     
/*  31 */     ExtendedWorldData worldData = ExtendedWorldData.get(this.world);
/*  32 */     this.protectedSize = size;
/*     */     
/*  34 */     int minPosX = posX - size;
/*  35 */     int minPosY = posY - size;
/*  36 */     int minPosZ = posZ - size;
/*  37 */     int maxPosX = posX + size;
/*  38 */     int maxPosY = posY + size;
/*  39 */     int maxPosZ = posZ + size;
/*     */     
/*  41 */     worldData.resizeRestrictedArea(id, new int[] { minPosX, minPosY, minPosZ }, new int[] { maxPosX, maxPosY, maxPosZ });
/*     */     
/*  43 */     this.uuid = player.getUniqueID();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setupProtection(PlayerEntity player, BlockPos pos, int size) {
/*  48 */     int posX = pos.getX();
/*  49 */     int posY = pos.getY();
/*  50 */     int posZ = pos.getZ();
/*     */     
/*  52 */     ExtendedWorldData worldData = ExtendedWorldData.get(this.world);
/*  53 */     this.protectedSize = size;
/*     */     
/*  55 */     int minPosX = posX - size;
/*  56 */     int minPosY = posY - size;
/*  57 */     int minPosZ = posZ - size;
/*  58 */     int maxPosX = posX + size;
/*  59 */     int maxPosY = posY + size;
/*  60 */     int maxPosZ = posZ + size;
/*     */     
/*  62 */     worldData.addRestrictedArea(new int[] { minPosX, minPosY, minPosZ }, new int[] { maxPosX, maxPosY, maxPosZ });
/*     */     
/*  64 */     this.uuid = player.getUniqueID();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/*  69 */     return this.protectedSize;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getOwnerUUID() {
/*  74 */     return this.uuid;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbtTag) {
/*  80 */     super.read(nbtTag);
/*     */     
/*  82 */     this.protectedSize = nbtTag.getInt("Size");
/*  83 */     this.uuid = nbtTag.getUniqueId("UUID");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT write(CompoundNBT nbtTag) {
/*  89 */     super.write(nbtTag);
/*     */     
/*  91 */     nbtTag.putInt("Size", this.protectedSize);
/*  92 */     nbtTag.putUniqueId("UUID", this.uuid);
/*     */     
/*  94 */     return nbtTag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getUpdateTag() {
/* 100 */     return write(new CompoundNBT());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SUpdateTileEntityPacket getUpdatePacket() {
/* 107 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 108 */     write(nbttagcompound);
/* 109 */     return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\AblProtectionTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */