/*     */ package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.entity.CreatureEntity;
/*     */ import net.minecraft.entity.ILivingEntityData;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.MobEntity;
/*     */ import net.minecraft.entity.SharedMonsterAttributes;
/*     */ import net.minecraft.entity.SpawnReason;
/*     */ import net.minecraft.entity.ai.goal.Goal;
/*     */ import net.minecraft.entity.ai.goal.HurtByTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.LookAtGoal;
/*     */ import net.minecraft.entity.ai.goal.LookRandomlyGoal;
/*     */ import net.minecraft.entity.ai.goal.MeleeAttackGoal;
/*     */ import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IHakiUser;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class BlackLegTrainerEntity extends TrainerEntity implements IHakiTrainer, IHakiUser {
/*  42 */   private static final String[] DEFAULT_TEXTURES = new String[] { "black_leg_trainer1", "black_leg_trainer2" };
/*     */ 
/*     */   
/*     */   public BlackLegTrainerEntity(World world) {
/*  46 */     super(ModEntities.BLACK_LEG_TRAINER, world, DEFAULT_TEXTURES);
/*  47 */     EntityStatsCapability.get((LivingEntity)this).setFightingStyle("black_leg");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerGoals() {
/*  53 */     super.registerGoals();
/*  54 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/*  55 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  56 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  57 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  58 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  60 */     this.targetSelector.addGoal(1, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractPirateEntity.class, true));
/*  61 */     this.targetSelector.addGoal(1, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractBanditEntity.class, true));
/*  62 */     this.targetSelector.addGoal(2, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*     */     
/*  64 */     addBusoshokuHaki(this, 100);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  70 */     super.registerAttributes();
/*  71 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  72 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
/*  73 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(12.0D);
/*  74 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
/*  75 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */     
/*  77 */     setDoriki(20.0D + WyHelper.randomWithRange(0, 10) + getThreat());
/*  78 */     setBelly(20.0D + WyHelper.randomWithRange(0, 20));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  84 */     super.registerData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawn(IWorld world, SpawnReason reason) {
/*  90 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  97 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  99 */     setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.CIGAR_LESS));
/*     */     
/* 101 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Quest[] getAvailableQuests(PlayerEntity player) {
/* 107 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/* 108 */     List<Quest> availableQuests = new ArrayList<>();
/*     */     
/* 110 */     if (entityProps.isBlackLeg()) {
/* 111 */       availableQuests.addAll(Arrays.asList(ModQuests.BLACK_LEG_TRIALS));
/*     */     }
/* 113 */     Quest[] quests = new Quest[availableQuests.size()];
/* 114 */     return availableQuests.<Quest>toArray(quests);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HakiType getTrainingHaki() {
/* 120 */     return HakiType.HARDENING;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\BlackLegTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */