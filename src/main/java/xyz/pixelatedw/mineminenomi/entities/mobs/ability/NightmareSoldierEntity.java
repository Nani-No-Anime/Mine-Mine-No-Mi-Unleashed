/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.ability;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.monster.MonsterEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.potion.Effects;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.abilities.kage.NightmareSoldiersAbility;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedHurtByTargetGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.projectiles.kage.KageProjectiles;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ public class NightmareSoldierEntity extends OPEntity {
/*  42 */   private static final DataParameter<Optional<UUID>> OWNER = EntityDataManager.createKey(NightmareSoldierEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
/*     */   
/*  44 */   private static final String[] TEXTURES = new String[] { "marine1", "marine2", "marine3", "marine4", "marine5", "pirate1", "pirate2", "pirate3", "pirate4", "pirate5", "fishman_pirate1", "fishman_pirate2", "fishman_pirate3", "bandit1", "bandit2", "bandit3" };
/*     */ 
/*     */   
/*     */   public NightmareSoldierEntity(World world) {
/*  48 */     super(KageProjectiles.NIGHTMARE_SOLDIER, world, TEXTURES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  54 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal(this));
/*  55 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  56 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  57 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0F));
/*  58 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, AbstractMarineEntity.class, 8.0F));
/*  59 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, AbstractPirateEntity.class, 8.0F));
/*  60 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, AbstractBanditEntity.class, 8.0F));
/*  61 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  70 */     super.registerAttributes();
/*  71 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
/*  72 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
/*  73 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22499999403953552D);
/*  74 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
/*  75 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
/*  76 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  82 */     super.registerData();
/*  83 */     this.dataManager.register(OWNER, Optional.empty());
/*  84 */     EntityStatsCapability.get((LivingEntity)this).setHeart(false);
/*  85 */     EntityStatsCapability.get((LivingEntity)this).setShadow(false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean attackEntityFrom(DamageSource damageSource, float damageValue) {
/*  91 */     if (damageSource.getTrueSource() != null && damageSource.getTrueSource() instanceof PlayerEntity && damageSource.getTrueSource() == getOwner()) {
/*  92 */       return false;
/*     */     }
/*  94 */     return super.attackEntityFrom(damageSource, damageValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 100 */     if (!this.world.isRemote) {
/*     */       
/* 102 */       if (getOwner() == null || !getOwner().isAlive()) {
/*     */         
/* 104 */         remove();
/*     */         
/*     */         return;
/*     */       } 
/* 108 */       if (getDistance((Entity)getOwner()) > 10.0F) {
/* 109 */         getNavigator().tryMoveToEntityLiving((Entity)getOwner(), 1.5D);
/*     */       }
/* 111 */       if (getDistance((Entity)getOwner()) > 128.0F) {
/* 112 */         setPositionAndUpdate(getOwner().getPosX(), getOwner().getPosY(), getOwner().getPosZ());
/*     */       }
/* 114 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)getOwner());
/* 115 */       NightmareSoldiersAbility ability = (NightmareSoldiersAbility)props.getEquippedAbility((Ability)NightmareSoldiersAbility.INSTANCE);
/* 116 */       List<LivingEntity> attackList = WyHelper.getEntitiesNear(getPosition(), this.world, 10.0D, FactionHelper.getOutsideGroupPredicate((LivingEntity)getOwner()), new Class[0]);
/* 117 */       attackList.remove(getOwner());
/* 118 */       attackList.removeIf(e -> e instanceof NightmareSoldierEntity);
/* 119 */       LivingEntity target = attackList.stream().findFirst().orElse(null);
/*     */       
/* 121 */       if (ability == null) {
/* 122 */         remove();
/*     */       }
/* 124 */       if (getAttackTarget() == getOwner() || getAttackTarget() instanceof NightmareSoldierEntity) {
/* 125 */         setAttackTarget(null);
/*     */       }
/* 127 */       if (target != null) {
/* 128 */         setAttackTarget(target);
/*     */       }
/*     */     } 
/* 131 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeAdditional(CompoundNBT compound) {
/* 137 */     super.writeAdditional(compound);
/* 138 */     if (this.dataManager.get(OWNER) != null) {
/* 139 */       compound.putString("OwnerUUID", ((UUID)((Optional<UUID>)this.dataManager.get(OWNER)).get()).toString());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void readAdditional(CompoundNBT compound) {
/* 145 */     super.readAdditional(compound);
/* 146 */     this.dataManager.set(OWNER, Optional.of(UUID.fromString(compound.getString("OwnerUUID"))));
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOwner(LivingEntity owner) {
/* 151 */     this.dataManager.set(OWNER, Optional.of(owner.getUniqueID()));
/* 152 */     IEntityStats stats = EntityStatsCapability.get((LivingEntity)this);
/* 153 */     stats.setFaction(EntityStatsCapability.get(owner).getFaction());
/*     */     
/* 155 */     Predicate<Entity> factionScope = FactionHelper.getOutsideGroupPredicate((LivingEntity)this);
/* 156 */     Predicate<Entity> invisibleEntity = entity -> (entity instanceof LivingEntity) ? (!((LivingEntity)entity).isPotionActive(Effects.INVISIBILITY)) : false;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     if (factionScope != null) {
/*     */       
/* 164 */       this.targetSelector.addGoal(1, (Goal)new ImprovedHurtByTargetGoal(this, factionScope, new Class[0]));
/* 165 */       this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, OPEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
/* 166 */       this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
/* 167 */       this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public PlayerEntity getOwner() {
/* 173 */     return ((Optional)getDataManager().get(OWNER)).isPresent() ? this.world.getPlayerByUuid(((Optional<UUID>)getDataManager().get(OWNER)).get()) : null;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\ability\NightmareSoldierEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */