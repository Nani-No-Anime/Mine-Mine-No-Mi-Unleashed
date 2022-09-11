package xyz.pixelatedw.mineminenomi.entities.mobs.quest.givers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
import xyz.pixelatedw.mineminenomi.api.entities.ai.IHakiUser;
import xyz.pixelatedw.mineminenomi.api.entities.ai.ISwordsman;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class SwordsmanTrainerEntity extends TrainerEntity implements IHakiTrainer, IHakiUser, ISwordsman {
  private static final String[] DEFAULT_TEXTURES = new String[] { "dojosensei1", "dojosensei2", "dojosensei3" };
  
  protected Item[] swords = new Item[] { (Item)ModWeapons.SANDAI_KITETSU, (Item)ModWeapons.NIDAI_KITETSU, (Item)ModWeapons.WADO_ICHIMONJI, Items.DIAMOND_SWORD };

  
  public SwordsmanTrainerEntity(World world) {
    super(ModEntities.SWORDSMAN_TRAINER, world, DEFAULT_TEXTURES);
  }


  
  public void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
    this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
    
    this.targetSelector.addGoal(1, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractPirateEntity.class, true));
    this.targetSelector.addGoal(1, (Goal)new NearestAttackableTargetGoal((MobEntity)this, AbstractBanditEntity.class, true));
    this.targetSelector.addGoal(2, (Goal)new HurtByTargetGoal(this, new Class[0]));
    
    addSwordsmanAbilities((OPEntity)this, 4);
    addBusoshokuHaki((OPEntity)this, 100);
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(80.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(2.0D);
    
    setDoriki(20.0D + WyHelper.randomWithRange(0, 10) + getThreat());
    setBelly(20.0D + WyHelper.randomWithRange(0, 20));
  }


  
  protected void registerData() {
    super.registerData();
  }


  
  public boolean canSpawn(IWorld world, SpawnReason reason) {
    return true;
  }


  
  protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {}

  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    ItemStack randomSword = new ItemStack((IItemProvider)this.swords[this.rand.nextInt(this.swords.length)]);
    randomSword.getOrCreateTag().putBoolean("isClone", true);
    setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
    
    return spawnData;
  }


  
  public Quest[] getAvailableQuests(PlayerEntity player) {
    IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
    IQuestData questProps = QuestDataCapability.get(player);
    List<Quest> availableQuests = new ArrayList<>();
    
    if (entityProps.isSwordsman()) {
      availableQuests.addAll(Arrays.asList(ModQuests.SWORDSMAN_TRIALS));
    }













    
    Quest[] quests = new Quest[availableQuests.size()];
    return availableQuests.<Quest>toArray(quests);
  }


  
  public HakiType getTrainingHaki() {
    return HakiType.IMBUING;
  }
}


