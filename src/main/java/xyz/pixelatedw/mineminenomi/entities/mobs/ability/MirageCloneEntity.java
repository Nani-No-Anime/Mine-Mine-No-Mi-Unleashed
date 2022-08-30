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
/*     */ public class MirageCloneEntity extends OPEntity {
/*  23 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(MirageCloneEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  24 */   private int tick = 0;
/*     */ 
/*     */   
/*     */   public MirageCloneEntity(World world) {
/*  28 */     super(ModEntities.MIRAGE_CLONE, world);
/*  29 */     this.experienceValue = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  35 */     super.registerAttributes();
/*  36 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  37 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  38 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(5.0D);
/*  39 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
/*  40 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  46 */     this.goalSelector.addGoal(1, (Goal)new PanicGoal(this, 1.25D));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  52 */     super.registerData();
/*  53 */     this.dataManager.register(OWNER, Optional.empty());
/*  54 */     EntityStatsCapability.get((LivingEntity)this).setHeart(false);
/*  55 */     EntityStatsCapability.get((LivingEntity)this).setShadow(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void remove() {
/*  61 */     if (!this.world.isRemote)
/*     */     {
/*  63 */       for (int i = 0; i < 10; i++) {
/*     */         
/*  65 */         double offsetX = WyHelper.randomDouble();
/*  66 */         double offsetY = WyHelper.randomDouble();
/*  67 */         double offsetZ = WyHelper.randomDouble();
/*     */         
/*  69 */         if (i % 2 == 0) {
/*  70 */           ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.CLOUD, getPosX() + offsetX, getPosY() + 1.5D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*     */         } else {
/*  72 */           ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.POOF, getPosX() + offsetX, getPosY() + 1.5D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.05D);
/*     */         } 
/*     */       }  } 
/*  75 */     super.remove();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  81 */     if (!this.world.isRemote && getOwner() == null) {
/*     */       
/*  83 */       remove();
/*     */       
/*     */       return;
/*     */     } 
/*  87 */     setRevengeTarget((LivingEntity)getOwner());
/*     */     
/*  89 */     if (this.tick > 200) {
/*  90 */       remove();
/*     */     }
/*  92 */     this.tick++;
/*  93 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/*  99 */     super.writeAdditional(compound);
/* 100 */     if (this.dataManager.get(OWNER) != null) {
/* 101 */       compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 107 */     super.readAdditional(compound);
/* 108 */     this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(UUID uuid) {
/* 113 */     this.dataManager.set(OWNER, Optional.of(uuid));
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getOwnerUUID() {
/* 118 */     return ((Optional<UUID>)getDataManager().get(OWNER)).get();
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getOwner() {
/* 123 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\MirageCloneEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */