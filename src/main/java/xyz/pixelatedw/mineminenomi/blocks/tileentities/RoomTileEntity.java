/*     */ package xyz.pixelatedw.mineminenomi.blocks.tileentities;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.NetworkManager;
/*     */ import net.minecraft.network.play.server.SUpdateTileEntityPacket;
/*     */ import net.minecraft.tileentity.ITickableTileEntity;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.ope.RoomAbility;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModTileEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ 
/*     */ public class RoomTileEntity
/*     */   extends TileEntity
/*     */   implements ITickableTileEntity {
/*  27 */   private Optional<UUID> ownerUUID = Optional.empty();
/*     */ 
/*     */   
/*     */   public RoomTileEntity() {
/*  31 */     super(ModTileEntities.ROOM);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  37 */     if (!this.world.isRemote) {
/*     */       
/*  39 */       LivingEntity owner = getOwner();
/*     */       
/*  41 */       if (owner != null) {
/*     */         
/*  43 */         RoomAbility ability = (RoomAbility)AbilityDataCapability.get(owner).getEquippedAbility((Ability)RoomAbility.INSTANCE);
/*     */         
/*  45 */         if (ability != null) {
/*     */           
/*  47 */           double distance = getDistanceSq(owner.getPosX(), owner.getPosY(), owner.getPosZ());
/*  48 */           if (MathHelper.sqrt(distance) > (ability.getROOMSize() + 2) || !ability.isContinuous()) {
/*  49 */             ability.endContinuity((PlayerEntity)owner);
/*     */           }
/*     */         } else {
/*  52 */           clearRoom();
/*     */         } 
/*     */       } else {
/*  55 */         clearRoom();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public void clearRoom() {
/*  61 */     for (int i = -45; i < 45; i++) {
/*  62 */       for (int k = -45; k < 45; k++)
/*  63 */       { for (int j = -45; j < 45; j++)
/*  64 */         { if (this.world.getBlockState(new BlockPos(getPos().getX() + i, getPos().getY() + k, getPos().getZ() + j)).getBlock() == ModBlocks.OPE)
/*  65 */             this.world.setBlockState(new BlockPos(getPos().getX() + i, getPos().getY() + k, getPos().getZ() + j), Blocks.AIR.getDefaultState());  }  } 
/*  66 */     }  this.world.setBlockState(new BlockPos(getPos().getX(), getPos().getY(), getPos().getZ()), Blocks.AIR.getDefaultState());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/*  71 */     this.ownerUUID = Optional.of(owner.getUniqueID());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public LivingEntity getOwner() {
/*  77 */     if (this.world instanceof ServerWorld && this.ownerUUID.isPresent()) {
/*     */       
/*  79 */       Entity owner = ((ServerWorld)this.world).getEntityByUuid(this.ownerUUID.get());
/*  80 */       if (owner instanceof LivingEntity) {
/*  81 */         return (LivingEntity)owner;
/*     */       }
/*  83 */       return null;
/*     */     } 
/*     */     
/*  86 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT write(CompoundNBT nbt) {
/*  92 */     super.write(nbt);
/*     */     
/*  94 */     if (this.ownerUUID.isPresent()) {
/*  95 */       nbt.putUniqueId("UUID", this.ownerUUID.get());
/*     */     }
/*  97 */     return nbt;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void read(CompoundNBT nbt) {
/* 103 */     super.read(nbt);
/*     */     
/* 105 */     this.ownerUUID = Optional.of(nbt.getUniqueId("UUID"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public CompoundNBT getUpdateTag() {
/* 111 */     return write(new CompoundNBT());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public SUpdateTileEntityPacket getUpdatePacket() {
/* 118 */     CompoundNBT nbttagcompound = new CompoundNBT();
/* 119 */     write(nbttagcompound);
/* 120 */     return new SUpdateTileEntityPacket(this.pos, 9, getUpdateTag());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
/* 126 */     read(pkt.getNbtCompound());
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\tileentities\RoomTileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */