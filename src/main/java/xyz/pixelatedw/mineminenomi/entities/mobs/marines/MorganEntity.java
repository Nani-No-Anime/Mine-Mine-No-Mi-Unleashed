/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.world.BossInfo;
/*     */ import net.minecraft.world.Difficulty;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerBossInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.CleaveAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.ShockwaveAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class MorganEntity extends AbstractMarineEntity {
/*  25 */   private ServerBossInfo bossInfo = null;
/*     */ 
/*     */   
/*     */   public MorganEntity(World world) {
/*  29 */     super(ModEntities.MORGAN, world);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  35 */     boolean isHardDifficulty = (this.world.getDifficulty().getId() >= Difficulty.HARD.getId());
/*     */     
/*  37 */     super.registerGoals();
/*  38 */     this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/*  39 */     this.goalSelector.addGoal(2, (Goal)(new CleaveAttackGoal(this, 100, 5, 4)).setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.ordinal()).setKnockbackPower(2.0F));
/*  40 */     this.goalSelector.addGoal(2, (Goal)(new ShockwaveAttackGoal(this, 100, 4, isHardDifficulty)).setAnimationId(OPEntity.Animation.SHOCKWAVE.ordinal()));
/*     */ 
/*     */     
/*  43 */     setDoriki(30.0D + WyHelper.randomWithRange(0, 10) + getThreat());
/*  44 */     setBelly(50.0D + WyHelper.randomWithRange(0, 5));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  50 */     super.registerAttributes();
/*  51 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
/*  52 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23999999463558197D);
/*  53 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
/*  54 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
/*  55 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.7D);
/*  56 */     getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(3.0D);
/*  57 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
/*  58 */     getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue(1.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  64 */     if (this.bossInfo != null) {
/*  65 */       this.bossInfo.setPercent(getHealth() / getMaxHealth());
/*     */     }
/*  67 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTrackingPlayer(ServerPlayerEntity player) {
/*  73 */     super.addTrackingPlayer(player);
/*  74 */     if (this.bossInfo != null) {
/*  75 */       this.bossInfo.addPlayer(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeTrackingPlayer(ServerPlayerEntity player) {
/*  81 */     super.removeTrackingPlayer(player);
/*  82 */     if (this.bossInfo != null) {
/*  83 */       this.bossInfo.removePlayer(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  90 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  92 */     if (reason == SpawnReason.EVENT) {
/*  93 */       this.bossInfo = (ServerBossInfo)(new ServerBossInfo(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
/*     */     }
/*  95 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDespawn(double distance) {
/* 101 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\marines\MorganEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */