/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.animals;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.entity.CreatureEntity;
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
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.JumpAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.lapahn.LapahnRageGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class LapahnEntity extends OPEntity {
/*  37 */   private static final AttributeModifier RAGE_MODIFIER = (new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Rage Mode Multiplier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
/*  38 */   private static final DataParameter<Boolean> IS_ENRAGED = EntityDataManager.createKey(LapahnEntity.class, DataSerializers.BOOLEAN);
/*  39 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GreatStompParticleEffect();
/*     */ 
/*     */   
/*     */   public LapahnEntity(World world) {
/*  43 */     super(ModEntities.LAPAHN, world);
/*  44 */     this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this));
/*  45 */     this.goalSelector.addGoal(0, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  46 */     this.goalSelector.addGoal(1, (Goal)new JumpAttackGoal(this));
/*  47 */     this.goalSelector.addGoal(1, (Goal)new LapahnRageGoal(this));
/*  48 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  49 */     this.goalSelector.addGoal(4, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  50 */     this.goalSelector.addGoal(4, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  52 */     this.targetSelector.addGoal(1, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*  53 */     this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, true));
/*  54 */     this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractPirateEntity.class, true));
/*  55 */     this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractBanditEntity.class, true));
/*  56 */     this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractMarineEntity.class, true));
/*  57 */     this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal((MobEntity)this, HumandrillEntity.class, true));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  64 */     super.registerAttributes();
/*  65 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(55.0D);
/*  66 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
/*  67 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
/*  68 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
/*     */     
/*  70 */     setDoriki(10.0D + WyHelper.randomWithRange(0, 5));
/*  71 */     setBelly(0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  77 */     super.registerData();
/*  78 */     getDataManager().register(IS_ENRAGED, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/*  84 */     super.writeAdditional(compound);
/*  85 */     compound.putBoolean("isEnraged", ((Boolean)this.dataManager.get(IS_ENRAGED)).booleanValue());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/*  91 */     super.readAdditional(compound);
/*  92 */     this.dataManager.set(IS_ENRAGED, Boolean.valueOf(compound.getBoolean("isEnraged")));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEnraged() {
/*  97 */     return ((Boolean)this.dataManager.get(IS_ENRAGED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setEnraged(boolean value) {
/* 102 */     this.dataManager.set(IS_ENRAGED, Boolean.valueOf(value));
/* 103 */     IAttributeInstance attr = getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
/* 104 */     attr.removeModifier(RAGE_MODIFIER);
/* 105 */     attr.applyModifier(RAGE_MODIFIER);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean onLivingFall(float distance, float damageMultiplier) {
/* 111 */     if (distance > 5.0F && !this.world.isRemote) {
/*     */       
/* 113 */       PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
/* 114 */       List<LivingEntity> targets = WyHelper.getEntitiesNear(getPosition(), this.world, 5.0D);
/* 115 */       targets.remove(this);
/*     */       
/* 117 */       float damage = 2.0F + (float)getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
/* 118 */       for (LivingEntity entity : targets) {
/*     */         
/* 120 */         if (!(entity instanceof LapahnEntity)) {
/*     */           
/* 122 */           entity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this), damage);
/* 123 */           entity.setMotion(0.0D, 0.5D, 0.0D);
/* 124 */           entity.velocityChanged = true;
/*     */         } 
/*     */       } 
/*     */       
/* 128 */       return false;
/*     */     } 
/*     */     
/* 131 */     return super.onLivingFall(distance, damageMultiplier);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\animals\LapahnEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */