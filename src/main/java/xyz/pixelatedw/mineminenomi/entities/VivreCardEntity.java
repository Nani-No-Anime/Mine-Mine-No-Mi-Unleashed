/*     */ package xyz.pixelatedw.mineminenomi.entities;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MoverType;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.IPacket;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraftforge.fml.network.NetworkHooks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.items.VivreCardItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class VivreCardEntity extends Entity {
/*     */   @Nullable
/*     */   private LivingEntity owner;
/*  31 */   private final double speedLimit = 0.001D;
/*     */   private UUID ownerUUID;
/*     */   
/*     */   public VivreCardEntity(World worldIn) {
/*  35 */     super(ModEntities.VIVRE_CARD, worldIn);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(UUID uuid) {
/*  40 */     this.ownerUUID = uuid;
/*  41 */     this.owner = (LivingEntity)((ServerWorld)this.world).getEntityByUuid(this.ownerUUID);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  47 */     super.tick();
/*     */     
/*  49 */     if (this.world.isRemote) {
/*     */       return;
/*     */     }
/*  52 */     if (!this.onGround) {
/*  53 */       move(MoverType.SELF, new Vec3d(0.0D, -0.1D, 0.0D));
/*     */     }
/*  55 */     if (this.ownerUUID == null || isBurning()) {
/*     */       
/*  57 */       remove();
/*     */       
/*     */       return;
/*     */     } 
/*  61 */     if (this.owner == null) {
/*     */       return;
/*     */     }
/*  64 */     if (this.owner.getHealth() <= 0.0F) {
/*     */       
/*  66 */       WyHelper.spawnParticles(ParticleTypes.FLAME, (ServerWorld)this.world, getPosX(), getPosY() + 0.3D, getPosZ());
/*  67 */       remove();
/*     */     } 
/*     */     
/*  70 */     double posX = getPosX() - this.owner.getPosX();
/*  71 */     double posZ = getPosZ() - this.owner.getPosZ();
/*     */     
/*  73 */     Objects.requireNonNull(this); if (posX > 0.0D && posX > 0.001D)
/*  74 */     { Objects.requireNonNull(this); posX = 0.001D; }
/*  75 */     else { Objects.requireNonNull(this); if (posX < 0.0D && posX < -0.001D) {
/*  76 */         Objects.requireNonNull(this); posX = -0.001D;
/*     */       }  }
/*  78 */      Objects.requireNonNull(this); if (posZ > 0.0D && posZ > 0.001D)
/*  79 */     { Objects.requireNonNull(this); posZ = 0.001D; }
/*  80 */     else { Objects.requireNonNull(this); if (posZ < 0.0D && posZ < -0.001D) {
/*  81 */         Objects.requireNonNull(this); posZ = -0.001D;
/*     */       }  }
/*  83 */      move(MoverType.SELF, new Vec3d(-posX, 0.0D, -posZ));
/*     */     
/*  85 */     lookAt(EntityAnchorArgument.Type.EYES, this.owner.getPositionVec());
/*     */     
/*  87 */     if (this.ticksExisted > 40) {
/*     */       
/*  89 */       List<PlayerEntity> players = WyHelper.getEntitiesNear(getPosition(), this.world, 0.5D, new Class[] { PlayerEntity.class });
/*     */       
/*  91 */       Iterator<PlayerEntity> iterator = players.iterator(); if (iterator.hasNext()) { PlayerEntity player = iterator.next();
/*     */         
/*  93 */         ItemStack stack = new ItemStack((IItemProvider)ModItems.VIVRE_CARD);
/*     */         
/*  95 */         ((VivreCardItem)stack.getItem()).setOwner(stack, this.owner);
/*     */         
/*  97 */         player.addItemStackToInventory(stack);
/*  98 */         remove(); }
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void writeAdditional(CompoundNBT compound) {
/* 110 */     compound.putString("OwnerUUID", this.ownerUUID.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readAdditional(CompoundNBT compound) {
/* 116 */     if (compound.contains("OwnerUUID", 8)) {
/* 117 */       setOwner(UUID.fromString(compound.getString("OwnerUUID")));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public IPacket<?> createSpawnPacket() {
/* 123 */     return NetworkHooks.getEntitySpawningPacket(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\VivreCardEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */