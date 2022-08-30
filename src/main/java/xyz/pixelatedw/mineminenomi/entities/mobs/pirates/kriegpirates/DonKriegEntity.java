/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.datasync.DataParameter;
/*     */ import net.minecraft.network.datasync.DataSerializers;
/*     */ import net.minecraft.network.datasync.EntityDataManager;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.world.BossInfo;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerBossInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.CleaveAttackGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg.BakudanGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg.DaisensoGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg.GunArrayGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg.MH5Goal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAttributes;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DonKriegEntity extends AbstractKriegPirateEntity {
/*  36 */   private ServerBossInfo bossInfo = null;
/*     */   
/*  38 */   private static final DataParameter<Boolean> DAISENSO_PHASE = EntityDataManager.createKey(DonKriegEntity.class, DataSerializers.BOOLEAN);
/*  39 */   private static final DataParameter<Boolean> MH5_PHASE = EntityDataManager.createKey(DonKriegEntity.class, DataSerializers.BOOLEAN);
/*     */   
/*     */   public CleaveAttackGoal fistCleaveAttack;
/*     */   
/*     */   public BlockPos challengeSpawnPosition;
/*     */   
/*     */   public static final String ANIM_GUN_ARRAY_NAME = "GUN_ARRAY";
/*     */   public static final int ANIM_GUN_ARRAY_ID = 120;
/*     */   public static final String ANIM_MH5_NAME = "MH5";
/*     */   public static final int ANIM_MH5_ID = 121;
/*     */   
/*     */   public DonKriegEntity(World world) {
/*  51 */     super(ModEntities.DON_KRIEG, world);
/*  52 */     setCrew("Krieg Pirates");
/*     */     
/*  54 */     OPEntity.Animation.create("GUN_ARRAY", 120);
/*  55 */     OPEntity.Animation.create("MH5", 121);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  61 */     super.registerData();
/*  62 */     getDataManager().register(DAISENSO_PHASE, Boolean.valueOf(false));
/*  63 */     getDataManager().register(MH5_PHASE, Boolean.valueOf(false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  69 */     this.fistCleaveAttack = (new CleaveAttackGoal((OPEntity)this, 80, 4, 6)).setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.ordinal());
/*     */     
/*  71 */     super.registerGoals();
/*  72 */     this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
/*  73 */     this.goalSelector.addGoal(1, (Goal)this.fistCleaveAttack);
/*  74 */     this.goalSelector.addGoal(2, (Goal)new GunArrayGoal(this, (int)WyHelper.randomWithRange(60, 80), 10));
/*  75 */     this.goalSelector.addGoal(2, (Goal)new BakudanGoal(this, (int)WyHelper.randomWithRange(120, 140), 10));
/*  76 */     this.goalSelector.addGoal(3, (Goal)new DaisensoGoal(this));
/*  77 */     this.goalSelector.addGoal(4, (Goal)new MH5Goal(this));
/*     */     
/*  79 */     setDoriki(100.0D + WyHelper.randomWithRange(0, 20) + getThreat());
/*  80 */     setBelly(20.0D + WyHelper.randomWithRange(0, 5));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  86 */     super.registerAttributes();
/*  87 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
/*  88 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.2199999988079071D);
/*  89 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
/*  90 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
/*  91 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8D);
/*  92 */     getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(2.0D);
/*  93 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20.0D);
/*  94 */     getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue(1.5D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 100 */     if (this.bossInfo != null)
/* 101 */       this.bossInfo.setPercent(getHealth() / getMaxHealth()); 
/* 102 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTrackingPlayer(ServerPlayerEntity player) {
/* 108 */     super.addTrackingPlayer(player);
/* 109 */     if (this.bossInfo != null) {
/* 110 */       this.bossInfo.addPlayer(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeTrackingPlayer(ServerPlayerEntity player) {
/* 116 */     super.removeTrackingPlayer(player);
/* 117 */     if (this.bossInfo != null) {
/* 118 */       this.bossInfo.removePlayer(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 125 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/* 127 */     setItemStackToSlot(EquipmentSlotType.CHEST, new ItemStack((IItemProvider)ModArmors.WOOTZ_STEEL_ARMOR));
/*     */     
/* 129 */     if (reason == SpawnReason.EVENT) {
/* 130 */       this.bossInfo = (ServerBossInfo)(new ServerBossInfo(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
/*     */     }
/* 132 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDespawn(double distance) {
/* 138 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isDaisensoActive() {
/* 143 */     return ((Boolean)this.dataManager.get(DAISENSO_PHASE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isMH5Active() {
/* 148 */     return ((Boolean)this.dataManager.get(MH5_PHASE)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerDaisensoPhase() {
/* 153 */     this.dataManager.set(DAISENSO_PHASE, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void triggerMH5Phase() {
/* 158 */     this.dataManager.set(MH5_PHASE, Boolean.valueOf(true));
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\kriegpirates\DonKriegEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */