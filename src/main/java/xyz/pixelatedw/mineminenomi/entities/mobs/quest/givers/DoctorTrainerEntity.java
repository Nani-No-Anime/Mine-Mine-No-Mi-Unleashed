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
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DoctorTrainerEntity extends TrainerEntity implements IHakiTrainer {
/*  37 */   private static final String[] DEFAULT_TEXTURES = new String[] { "doctor1", "doctor2" };
/*     */ 
/*     */   
/*     */   public DoctorTrainerEntity(World world) {
/*  41 */     super(ModEntities.DOCTOR_TRAINER, world, DEFAULT_TEXTURES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerGoals() {
/*  47 */     super.registerGoals();
/*  48 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/*  49 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  50 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  51 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  52 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  54 */     this.targetSelector.addGoal(2, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  60 */     super.registerAttributes();
/*  61 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  62 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23999999463558197D);
/*  63 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
/*  64 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
/*  65 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */     
/*  67 */     setDoriki(10.0D + WyHelper.randomWithRange(0, 5) + getThreat());
/*  68 */     setBelly(10.0D + WyHelper.randomWithRange(0, 10));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  74 */     super.registerData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawn(IWorld world, SpawnReason reason) {
/*  80 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/*  87 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/*  89 */     if (this.rand.nextDouble() < 0.4D) {
/*     */       
/*  91 */       ItemStack medicBag = new ItemStack((IItemProvider)ModArmors.MEDIC_BAG);
/*  92 */       setItemStackToSlot(EquipmentSlotType.CHEST, medicBag);
/*     */     } 
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
/* 104 */     if (entityProps.isDoctor()) {
/* 105 */       availableQuests.addAll(Arrays.asList(ModQuests.DOCTOR_TRIALS));
/*     */     }
/* 107 */     Quest[] quests = new Quest[availableQuests.size()];
/* 108 */     return availableQuests.<Quest>toArray(quests);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HakiType getTrainingHaki() {
/* 114 */     return HakiType.KENBUNSHOKU;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\DoctorTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */