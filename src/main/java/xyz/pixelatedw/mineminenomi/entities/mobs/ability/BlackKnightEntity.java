/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import net.minecraft.enchantment.EnchantmentHelper;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.math.MathHelper;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BlackKnightEntity extends OPEntity {
/*  41 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(BlackKnightEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*     */   public boolean isAggressive = true;
/*  43 */   public List<LivingEntity> forcedTargets = new ArrayList<>();
/*     */ 
/*     */   
/*     */   public BlackKnightEntity(World world) {
/*  47 */     super(ModEntities.BLACK_KNIGHT, world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  53 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/*  54 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  55 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  56 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  57 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  59 */     this.targetSelector.addGoal(2, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  65 */     super.registerAttributes();
/*  66 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
/*  67 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  68 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
/*  69 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0D);
/*  70 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
/*  71 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  77 */     super.registerData();
/*  78 */     this.dataManager.register(OWNER, Optional.empty());
/*  79 */     EntityStatsCapability.get((LivingEntity)this).setHeart(false);
/*  80 */     EntityStatsCapability.get((LivingEntity)this).setShadow(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damageSource, float damageValue) {
/*  86 */     if (damageSource.getTrueSource() != null && damageSource.getTrueSource() instanceof PlayerEntity && damageSource.getTrueSource() == getOwner()) {
/*  87 */       return false;
/*     */     }
/*  89 */     return super.attackEntityFrom(damageSource, damageValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityAsMob(Entity target) {
/*  95 */     float damage = (float)getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/*  96 */     int knockback = 0;
/*     */     
/*  98 */     if (target instanceof LivingEntity) {
/*     */       
/* 100 */       damage += EnchantmentHelper.getModifierForCreature(getHeldItemMainhand(), ((LivingEntity)target).getCreatureAttribute());
/* 101 */       knockback = (int)(knockback + EnchantmentHelper.getKnockbackModifier((LivingEntity)this));
/*     */     } 
/*     */     
/* 104 */     boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this), damage);
/*     */     
/* 106 */     if (flag)
/*     */     {
/* 108 */       if (knockback > 0) {
/*     */         
/* 110 */         target.addVelocity((-MathHelper.sin(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F), 0.1D, (MathHelper.cos(this.rotationYaw * 3.1415927F / 180.0F) * knockback * 0.5F));
/* 111 */         setMotion(getMotion().mul(0.6D, 1.0D, 0.6D));
/*     */       } 
/*     */     }
/*     */     
/* 115 */     return flag;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 121 */     if (!this.world.isRemote) {
/*     */       
/* 123 */       PlayerEntity owner = getOwner();
/*     */       
/* 125 */       if (owner == null) {
/*     */         
/* 127 */         remove();
/*     */         
/*     */         return;
/*     */       } 
/* 131 */       if (getDistance((Entity)owner) > 10.0F) {
/* 132 */         getNavigator().tryMoveToEntityLiving((Entity)owner, 1.5D);
/*     */       }
/* 134 */       if (getDistance((Entity)owner) > 80.0F) {
/* 135 */         setPositionAndUpdate(owner.getPosX(), owner.getPosY(), owner.getPosZ());
/*     */       }
/* 137 */       IEntityStats ownerProps = EntityStatsCapability.get((LivingEntity)owner);
/* 138 */       IDevilFruit ownerDFProps = DevilFruitCapability.get((LivingEntity)owner);
/* 139 */       List<LivingEntity> targetsList = this.isAggressive ? WyHelper.getEntitiesNear(getPosition(), this.world, 10.0D, new Class[] { PlayerEntity.class, AbstractMarineEntity.class, AbstractPirateEntity.class, MonsterEntity.class }) : (!this.forcedTargets.isEmpty() ? this.forcedTargets : new ArrayList<>());
/* 140 */       LivingEntity target = null;
/*     */       
/* 142 */       if (!ownerDFProps.getDevilFruit().equalsIgnoreCase("ito_ito")) {
/* 143 */         remove();
/*     */       }
/* 145 */       if (!targetsList.isEmpty() && (getAttackTarget() == null || !getAttackTarget().isAlive())) {
/*     */         
/* 147 */         if (targetsList.contains(owner)) {
/* 148 */           targetsList.remove(owner);
/*     */         }
/* 150 */         if (ownerProps.isMarine()) {
/* 151 */           targetsList = (List<LivingEntity>)targetsList.stream().filter(x -> !(x instanceof AbstractMarineEntity)).collect(Collectors.toList());
/*     */         }
/* 153 */         target = targetsList.stream().findFirst().orElse(null);
/*     */       } 
/*     */       
/* 156 */       if (target != null) {
/* 157 */         setAttackTarget(target);
/*     */       }
/* 159 */       if (!this.forcedTargets.isEmpty()) {
/*     */         
/* 161 */         Iterator<LivingEntity> it = this.forcedTargets.iterator();
/* 162 */         while (it.hasNext()) {
/*     */           
/* 164 */           LivingEntity forcedTarget = it.next();
/* 165 */           if (forcedTarget == null || !forcedTarget.isAlive()) {
/* 166 */             it.remove();
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 171 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 177 */     super.writeAdditional(compound);
/* 178 */     if (this.dataManager.get(OWNER) != null)
/*     */     {
/* 180 */       compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 187 */     super.readAdditional(compound);
/* 188 */     this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/* 193 */     this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
/* 194 */     EntityStatsCapability.get((LivingEntity)this).setFaction(EntityStatsCapability.get(owner).getFaction());
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getOwner() {
/* 199 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   public UUID getOwnerUUID() {
/* 204 */     return ((Optional<UUID>)getDataManager().get(OWNER)).get();
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\BlackKnightEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */