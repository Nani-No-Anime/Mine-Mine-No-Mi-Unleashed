/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.PanicGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class WaxCloneEntity extends OPEntity {
/*  23 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(WaxCloneEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  24 */   private static final DataParameter<Boolean> IS_TEXTURED = EntityDataManager.createKey(WaxCloneEntity.class, DataSerializers.BOOLEAN);
/*  25 */   private int tick = 0;
/*     */ 
/*     */   
/*     */   public WaxCloneEntity(World world) {
/*  29 */     super(ModEntities.WAX_CLONE, world);
/*  30 */     this.experienceValue = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  36 */     super.registerAttributes();
/*  37 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  38 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  39 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
/*  40 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
/*  41 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  47 */     this.goalSelector.addGoal(1, (Goal)new PanicGoal(this, 1.25D));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  53 */     super.registerData();
/*  54 */     this.dataManager.register(OWNER, Optional.empty());
/*  55 */     this.dataManager.register(IS_TEXTURED, Boolean.valueOf(false));
/*  56 */     EntityStatsCapability.get((LivingEntity)this).setHeart(false);
/*  57 */     EntityStatsCapability.get((LivingEntity)this).setShadow(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  63 */     if (!this.world.isRemote && getOwner() == null) {
/*     */       
/*  65 */       remove();
/*     */       
/*     */       return;
/*     */     } 
/*  69 */     setRevengeTarget((LivingEntity)getOwner());
/*     */     
/*  71 */     if (this.tick > 300) {
/*  72 */       remove();
/*     */     }
/*  74 */     this.tick++;
/*  75 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/*  81 */     if (!this.world.isRemote)
/*     */     {
/*  83 */       for (int i = 0; i < 10; i++) {
/*     */         
/*  85 */         double offsetX = WyHelper.randomDouble();
/*  86 */         double offsetY = WyHelper.randomDouble();
/*  87 */         double offsetZ = WyHelper.randomDouble();
/*     */         
/*  89 */         if (i % 2 == 0) {
/*  90 */           ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.CLOUD, getPosX() + offsetX, getPosY() + 1.5D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*     */         } else {
/*  92 */           ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.POOF, getPosX() + offsetX, getPosY() + 1.5D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*     */         } 
/*     */       }  } 
/*  95 */     super.remove();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 101 */     super.writeAdditional(compound);
/* 102 */     if (this.dataManager.get(OWNER) != null)
/* 103 */       compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString()); 
/* 104 */     compound.putBoolean("isTextured", ((Boolean)this.dataManager.get(IS_TEXTURED)).booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 110 */     super.readAdditional(compound);
/* 111 */     this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
/* 112 */     this.dataManager.set(IS_TEXTURED, Boolean.valueOf(compound.getBoolean("isTextured")));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(UUID uuid) {
/* 117 */     this.dataManager.set(OWNER, Optional.of(uuid));
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getOwnerUUID() {
/* 122 */     return ((Optional<UUID>)getDataManager().get(OWNER)).get();
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getOwner() {
/* 127 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTextured() {
/* 132 */     this.dataManager.set(IS_TEXTURED, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTextured() {
/* 137 */     return ((Boolean)getDataManager().get(IS_TEXTURED)).booleanValue();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\WaxCloneEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */