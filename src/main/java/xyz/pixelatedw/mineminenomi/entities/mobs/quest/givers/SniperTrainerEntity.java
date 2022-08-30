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
/*     */ import net.minecraft.entity.ai.goal.SwimGoal;
/*     */ import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.ISniper;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SniperTrainerEntity extends TrainerEntity implements IHakiTrainer, ISniper {
/*  38 */   private static final String[] DEFAULT_TEXTURES = new String[] { "bow_master1", "bow_master2" };
/*     */ 
/*     */   
/*     */   public SniperTrainerEntity(World world) {
/*  42 */     super(ModEntities.SNIPER_TRAINER, world, DEFAULT_TEXTURES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerGoals() {
/*  48 */     super.registerGoals();
/*  49 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/*  50 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal((CreatureEntity)this, 1.0D, true));
/*  51 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal((CreatureEntity)this, 0.8D));
/*  52 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  53 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  55 */     this.targetSelector.addGoal(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
/*     */     
/*  57 */     addSniperAbilities((OPEntity)this, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  63 */     super.registerAttributes();
/*  64 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  65 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25999999046325684D);
/*  66 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
/*  67 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(70.0D);
/*  68 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */     
/*  70 */     setDoriki(20.0D + WyHelper.randomWithRange(0, 10) + getThreat());
/*  71 */     setBelly(20.0D + WyHelper.randomWithRange(0, 20));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  77 */     super.registerData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawn(IWorld world, SpawnReason reason) {
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  90 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  92 */     ItemStack randomSword = new ItemStack((IItemProvider)Items.BOW);
/*  93 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
/*     */     
/*  95 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Quest[] getAvailableQuests(PlayerEntity player) {
/* 101 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/* 102 */     List<Quest> availableQuests = new ArrayList<>();
/*     */     
/* 104 */     if (entityProps.isSniper()) {
/* 105 */       availableQuests.addAll(Arrays.asList(ModQuests.SNIPER_TRIALS));
/*     */     }
/* 107 */     Quest[] quests = new Quest[availableQuests.size()];
/* 108 */     return availableQuests.<Quest>toArray(quests);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HakiType getTrainingHaki() {
/* 114 */     return HakiType.IMBUING;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\SniperTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */