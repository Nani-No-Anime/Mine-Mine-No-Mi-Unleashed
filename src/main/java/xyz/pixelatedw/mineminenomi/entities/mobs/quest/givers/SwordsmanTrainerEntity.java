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
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.DamageSource;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.world.DifficultyInstance;
/*     */ import net.minecraft.world.IWorld;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.IHakiUser;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.ai.ISwordsman;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
/*     */ import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModQuests;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModWeapons;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class SwordsmanTrainerEntity extends TrainerEntity implements IHakiTrainer, IHakiUser, ISwordsman {
/*  47 */   private static final String[] DEFAULT_TEXTURES = new String[] { "dojosensei1", "dojosensei2", "dojosensei3" };
/*     */   
/*  49 */   protected Item[] swords = new Item[] { (Item)ModWeapons.SANDAI_KITETSU, (Item)ModWeapons.NIDAI_KITETSU, (Item)ModWeapons.WADO_ICHIMONJI, Items.DIAMOND_SWORD };
/*     */ 
/*     */   
/*     */   public SwordsmanTrainerEntity(World world) {
/*  53 */     super(ModEntities.SWORDSMAN_TRAINER, world, DEFAULT_TEXTURES);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerGoals() {
/*  59 */     super.registerGoals();
/*  60 */     this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
/*  61 */     this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
/*  62 */     this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
/*  63 */     this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
/*  64 */     this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
/*     */     
/*  66 */     this.targetSelector.addGoal(1, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractPirateEntity.class, true));
/*  67 */     this.targetSelector.addGoal(1, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractBanditEntity.class, true));
/*  68 */     this.targetSelector.addGoal(2, (Goal)new HurtByTargetGoal(this, new Class[0]));
/*     */     
/*  70 */     addSwordsmanAbilities((OPEntity)this, 4);
/*  71 */     addBusoshokuHaki((OPEntity)this, 100);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerAttributes() {
/*  77 */     super.registerAttributes();
/*  78 */     getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
/*  79 */     getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
/*  80 */     getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
/*  81 */     getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
/*  82 */     getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
/*     */     
/*  84 */     setDoriki(20.0D + WyHelper.randomWithRange(0, 10) + getThreat());
/*  85 */     setBelly(20.0D + WyHelper.randomWithRange(0, 20));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void registerData() {
/*  91 */     super.registerData();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canSpawn(IWorld world, SpawnReason reason) {
/*  97 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {}
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
/* 107 */     spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
/*     */     
/* 109 */     ItemStack randomSword = new ItemStack((IItemProvider)this.swords[this.rand.nextInt(this.swords.length)]);
/* 110 */     randomSword.getOrCreateTag().putBoolean("isClone", true);
/* 111 */     setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
/*     */     
/* 113 */     return spawnData;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Quest[] getAvailableQuests(PlayerEntity player) {
/* 119 */     IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
/* 120 */     IQuestData questProps = QuestDataCapability.get(player);
/* 121 */     List<Quest> availableQuests = new ArrayList<>();
/*     */     
/* 123 */     if (entityProps.isSwordsman()) {
/* 124 */       availableQuests.addAll(Arrays.asList(ModQuests.SWORDSMAN_TRIALS));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     Quest[] quests = new Quest[availableQuests.size()];
/* 141 */     return availableQuests.<Quest>toArray(quests);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public HakiType getTrainingHaki() {
/* 147 */     return HakiType.IMBUING;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\mobs\quest\givers\SwordsmanTrainerEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */