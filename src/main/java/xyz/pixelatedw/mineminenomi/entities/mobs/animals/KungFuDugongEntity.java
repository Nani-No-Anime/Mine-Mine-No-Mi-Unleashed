/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.command.arguments.EntityAnchorArgument;
/*     */ import net.minecraft.entity.AgeableEntity;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.attributes.AttributeModifier;
/*     */ import net.minecraft.entity.ai.attributes.IAttributeInstance;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.passive.AnimalEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.particles.IParticleData;
/*     */ import net.minecraft.particles.ParticleTypes;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class KungFuDugongEntity extends AnimalEntity {
/*  43 */   private static final AttributeModifier RAGE_MODIFIER = (new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Rage Mode Multiplier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  44 */   private static final Item[] FOOD = new Item[] { Items.COOKED_BEEF, Items.COOKED_CHICKEN, Items.COOKED_COD, Items.COOKED_MUTTON, Items.COOKED_PORKCHOP, Items.COOKED_RABBIT, Items.COOKED_SALMON };
/*     */   
/*  46 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*  47 */   private static final DataParameter<Boolean> IS_ENRAGED = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
/*  48 */   private static final DataParameter<Boolean> IS_SITTING = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
/*  49 */   private static final DataParameter<Boolean> IS_HAPPY = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
/*  50 */   private static final DataParameter<Boolean> IS_TRAINING = EntityDataManager.createKey(KungFuDugongEntity.class, DataSerializers.BOOLEAN);
/*     */ 
/*     */   
/*     */   public KungFuDugongEntity(World world) {
/*  54 */     super(ModEntities.KUNG_FU_DUGONG, world);
/*  55 */     this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this));
/*  56 */     this.goalSelector.addGoal(0, (Goal)new MeleeAttackGoal(this, 1.0D, false));
/*  57 */     this.goalSelector.addGoal(1, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  58 */     this.goalSelector.addGoal(2, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  59 */     this.goalSelector.addGoal(2, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  61 */     this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  67 */     super.registerAttributes();
/*  68 */     getAttributes().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(8.0D);
/*  69 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(40.0D);
/*  70 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
/*  71 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  77 */     super.registerData();
/*  78 */     getDataManager().register(OWNER, Optional.empty());
/*  79 */     getDataManager().register(IS_ENRAGED, Boolean.valueOf(false));
/*  80 */     getDataManager().register(IS_SITTING, Boolean.valueOf(false));
/*  81 */     getDataManager().register(IS_HAPPY, Boolean.valueOf(false));
/*  82 */     getDataManager().register(IS_TRAINING, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void livingTick() {
/*  88 */     super.livingTick();
/*     */     
/*  90 */     if (!this.world.isRemote) {
/*     */       
/*  92 */       boolean flagOwnerNearby = (getOwner() != null && getDistance((Entity)getOwner()) < 10.0F);
/*  93 */       boolean flagTamed = isTamed();
/*  94 */       boolean flagHasNoTarget = (getAttackTarget() == null);
/*  95 */       boolean flagSitting = (flagHasNoTarget && isSitting());
/*  96 */       boolean flagHealth = (getHealth() > getMaxHealth() / 3.0F);
/*     */       
/*  98 */       if (flagOwnerNearby && flagTamed && flagSitting && flagHealth) {
/*  99 */         setHappy(true);
/*     */       } else {
/* 101 */         setHappy(false);
/*     */       } 
/* 103 */       if (!flagHasNoTarget)
/*     */       {
/* 105 */         setHappy(false);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 112 */       if (flagSitting || isTraining()) {
/* 113 */         getNavigator().clearPath();
/*     */       }
/* 115 */       if (getAttackTarget() == getOwner()) {
/*     */         
/* 117 */         setAttackTarget(null);
/* 118 */         setRevengeTarget(null);
/* 119 */         getNavigator().clearPath();
/*     */       } 
/*     */       
/* 122 */       if (flagTamed)
/*     */       {
/* 124 */         if (flagSitting) {
/*     */           
/* 126 */           List<PlayerEntity> players = WyHelper.getEntitiesNear(getPosition(), this.world, 3.0D, new Class[] { PlayerEntity.class });
/* 127 */           for (PlayerEntity player : players)
/*     */           {
/* 129 */             if (player != getOwner()) {
/*     */               continue;
/*     */             }
/* 132 */             lookAt(EntityAnchorArgument.Type.EYES, player.getEyePosition(0.0F));
/* 133 */             addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 40, 0, false, false));
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 138 */           if (getDistance((Entity)getOwner()) > 10.0F) {
/* 139 */             getNavigator().tryMoveToEntityLiving((Entity)getOwner(), 1.5D);
/*     */           }
/* 141 */           if (getDistance((Entity)getOwner()) > 80.0F) {
/* 142 */             setPositionAndUpdate(getOwner().getPosX(), getOwner().getPosY(), getOwner().getPosZ());
/*     */           }
/* 144 */           for (CreatureEntity target : WyHelper.getEntitiesNear(getOwner().getPosition(), this.world, 40.0D, CreatureEntity.class)) {
/*     */             
/* 146 */             if (target != null && target.isAlive() && target.getAttackTarget() != null && target.getAttackTarget().equals(getOwner()))
/*     */             {
/* 148 */               setAttackTarget((LivingEntity)target);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damageSource, float amount) {
/* 159 */     super.attackEntityFrom(damageSource, amount);
/*     */     
/* 161 */     Entity entity = damageSource.getTrueSource();
/*     */     
/* 163 */     if (isTamed() && entity instanceof LivingEntity && entity != getOwner()) {
/* 164 */       setAttackTarget((LivingEntity)entity);
/* 165 */     } else if (!isTamed()) {
/*     */       
/* 167 */       if (!this.world.isRemote)
/*     */       {
/* 169 */         if (entity instanceof PlayerEntity) {
/*     */           
/* 171 */           PlayerEntity player = (PlayerEntity)entity;
/*     */           
/* 173 */           if (!player.getHeldItem(player.getActiveHand()).isEmpty()) {
/*     */             
/* 175 */             setEnraged(true);
/* 176 */             for (int i = 0; i < 5; i++)
/*     */             {
/* 178 */               double offsetX = this.rand.nextGaussian() * 0.02D;
/* 179 */               double offsetY = this.rand.nextGaussian() * 0.02D;
/* 180 */               double offsetZ = this.rand.nextGaussian() * 0.02D;
/*     */               
/* 182 */               ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.ANGRY_VILLAGER, getPosX() + offsetX, getPosY() + 1.0D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */             
/*     */             }
/*     */           
/*     */           }
/* 187 */           else if (!isEnraged() && getHealth() < getMaxHealth() / 2.0F) {
/*     */             
/* 189 */             for (int i = 0; i < 5; i++) {
/*     */               
/* 191 */               double offsetX = this.rand.nextGaussian() * 0.02D;
/* 192 */               double offsetY = this.rand.nextGaussian() * 0.02D;
/* 193 */               double offsetZ = this.rand.nextGaussian() * 0.02D;
/*     */               
/* 195 */               ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.HEART, getPosX() + offsetX, getPosY() + 1.0D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */             } 
/* 197 */             setOwner(player.getUniqueID());
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 204 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean processInteract(PlayerEntity player, Hand hand) {
/* 210 */     if (isTamed() && player == getOwner() && !player.world.isRemote && hand == Hand.MAIN_HAND) {
/*     */       
/* 212 */       ItemStack stack = player.getHeldItem((player.getActiveHand() != null) ? player.getActiveHand() : Hand.MAIN_HAND);
/*     */       
/* 214 */       if (!stack.isEmpty() && getHealth() < getMaxHealth()) {
/*     */         
/* 216 */         Optional<Item> food = Arrays.<Item>stream(FOOD).filter(x -> (stack.getItem() == x)).findFirst();
/*     */         
/* 218 */         if (food.isPresent())
/*     */         {
/* 220 */           stack.shrink(1);
/* 221 */           heal(4.0F);
/* 222 */           for (int i = 0; i < 5; i++) {
/*     */             
/* 224 */             double offsetX = this.rand.nextGaussian() * 0.02D;
/* 225 */             double offsetY = this.rand.nextGaussian() * 0.02D;
/* 226 */             double offsetZ = this.rand.nextGaussian() * 0.02D;
/*     */             
/* 228 */             ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.HEART, getPosX() + offsetX, getPosY() + 1.0D + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */           } 
/* 230 */           return true;
/*     */         }
/*     */       
/* 233 */       } else if (stack.isEmpty()) {
/*     */         
/* 235 */         setSiting(!isSitting());
/* 236 */         setAttackTarget((LivingEntity)null);
/* 237 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 247 */     super.writeAdditional(compound);
/* 248 */     if (((Optional)this.dataManager.get(OWNER)).isPresent())
/* 249 */       compound.putString("owner", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString()); 
/* 250 */     compound.putBoolean("isEnraged", ((Boolean)this.dataManager.get(IS_ENRAGED)).booleanValue());
/* 251 */     compound.putBoolean("isSitting", ((Boolean)this.dataManager.get(IS_SITTING)).booleanValue());
/* 252 */     compound.putBoolean("isHappy", ((Boolean)this.dataManager.get(IS_HAPPY)).booleanValue());
/* 253 */     compound.putBoolean("isTraining", ((Boolean)this.dataManager.get(IS_TRAINING)).booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 259 */     super.readAdditional(compound);
/* 260 */     if (!WyHelper.isNullOrEmpty(compound.getString("owner")))
/* 261 */       this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("owner")))); 
/* 262 */     this.dataManager.set(IS_ENRAGED, Boolean.valueOf(compound.getBoolean("isEnraged")));
/* 263 */     this.dataManager.set(IS_SITTING, Boolean.valueOf(compound.getBoolean("isSitting")));
/* 264 */     this.dataManager.set(IS_HAPPY, Boolean.valueOf(compound.getBoolean("isHappy")));
/* 265 */     this.dataManager.set(IS_TRAINING, Boolean.valueOf(compound.getBoolean("isTraining")));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDespawn(double distance) {
/* 271 */     return !isTamed();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTraining() {
/* 276 */     return ((Boolean)this.dataManager.get(IS_TRAINING)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setTraining(boolean value) {
/* 281 */     this.dataManager.set(IS_TRAINING, Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isHappy() {
/* 286 */     return ((Boolean)this.dataManager.get(IS_HAPPY)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHappy(boolean value) {
/* 291 */     this.dataManager.set(IS_HAPPY, Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isSitting() {
/* 296 */     return ((Boolean)this.dataManager.get(IS_SITTING)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSiting(boolean value) {
/* 301 */     this.dataManager.set(IS_SITTING, Boolean.valueOf(value));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnraged() {
/* 306 */     return ((Boolean)this.dataManager.get(IS_ENRAGED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnraged(boolean value) {
/* 311 */     this.dataManager.set(IS_ENRAGED, Boolean.valueOf(value));
/* 312 */     IAttributeInstance attr = getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/* 313 */     attr.removeModifier(RAGE_MODIFIER);
/* 314 */     if (value) {
/* 315 */       attr.applyModifier(RAGE_MODIFIER);
/*     */     }
/*     */   }
/*     */   
/*     */   public void setOwner(UUID uuid) {
/* 320 */     this.dataManager.set(OWNER, Optional.of(uuid));
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getOwner() {
/* 325 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isTamed() {
/* 330 */     return (getOwner() != null);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AgeableEntity createChild(AgeableEntity ageable) {
/* 336 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\KungFuDugongEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */