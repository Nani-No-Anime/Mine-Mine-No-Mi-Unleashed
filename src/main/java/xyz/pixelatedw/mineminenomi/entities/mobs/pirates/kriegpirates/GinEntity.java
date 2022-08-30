/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.BossInfo;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerBossInfo;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.BlockGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GapCloserGoal;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.RetreatAndFlintlockGoal;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class GinEntity extends AbstractKriegPirateEntity {
/*  28 */   private ServerBossInfo bossInfo = null;
/*     */ 
/*     */   
/*     */   public GinEntity(World world) {
/*  32 */     super(ModEntities.GIN, world);
/*  33 */     setCrew("Krieg Pirates");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerGoals() {
/*  39 */     super.registerGoals();
/*  40 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  41 */     this.goalSelector.addGoal(2, (Goal)new GapCloserGoal((OPEntity)this, 2.0D, 3));
/*  42 */     this.goalSelector.addGoal(2, (Goal)new BlockGoal((OPEntity)this, (int)WyHelper.randomWithRange(40, 60), 4, 6));
/*  43 */     this.goalSelector.addGoal(3, (Goal)new RetreatAndFlintlockGoal((OPEntity)this, 100, 50));
/*     */ 
/*     */     
/*  46 */     setDoriki(40.0D + WyHelper.randomWithRange(0, 20) + getThreat());
/*  47 */     setBelly(5.0D + WyHelper.randomWithRange(0, 5));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  53 */     super.registerAttributes();
/*  54 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(60.0D);
/*  55 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23999999463558197D);
/*  56 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
/*  57 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D);
/*  58 */     getAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.5D);
/*  59 */     getAttribute(SharedMonsterAttributes.ATTACK_KNOCKBACK).setBaseValue(2.0D);
/*  60 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  66 */     if (this.bossInfo != null)
/*  67 */       this.bossInfo.setPercent(getHealth() / getMaxHealth()); 
/*  68 */     super.tick();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTrackingPlayer(ServerPlayerEntity player) {
/*  74 */     super.addTrackingPlayer(player);
/*  75 */     if (this.bossInfo != null) {
/*  76 */       this.bossInfo.addPlayer(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeTrackingPlayer(ServerPlayerEntity player) {
/*  82 */     super.removeTrackingPlayer(player);
/*  83 */     if (this.bossInfo != null) {
/*  84 */       this.bossInfo.removePlayer(player);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  91 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  93 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.TONFA));
/*  94 */     setItemStackToSlot(EquipmentSlotType.OFFHAND, new ItemStack((IItemProvider)ModWeapons.TONFA));
/*     */     
/*  96 */     if (reason == SpawnReason.EVENT) {
/*  97 */       this.bossInfo = (ServerBossInfo)(new ServerBossInfo(getDisplayName(), BossInfo.Color.RED, BossInfo.Overlay.PROGRESS)).setDarkenSky(false);
/*     */     }
/*  99 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canDespawn(double distance) {
/* 105 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\pirates\kriegpirates\GinEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */