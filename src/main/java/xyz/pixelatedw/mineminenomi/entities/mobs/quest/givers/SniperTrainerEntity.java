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
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
import xyz.pixelatedw.mineminenomi.api.entities.ai.ISniper;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModQuests;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class SniperTrainerEntity extends TrainerEntity implements IHakiTrainer, ISniper {
  private static final String[] DEFAULT_TEXTURES = new String[] { "bow_master1", "bow_master2" };

  
  public SniperTrainerEntity(World world) {
    super(ModEntities.SNIPER_TRAINER, world, DEFAULT_TEXTURES);
  }


  
  public void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new SwimGoal((MobEntity)this));
    this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal((CreatureEntity)this, 1.0D, true));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal((CreatureEntity)this, 0.8D));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal((MobEntity)this));
    
    this.targetSelector.addGoal(2, (Goal)new HurtByTargetGoal((CreatureEntity)this, new Class[0]));
    
    addSniperAbilities((OPEntity)this, 4);
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25999999046325684D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(70.0D);
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


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    ItemStack randomSword = new ItemStack((IItemProvider)Items.BOW);
    setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
    
    return spawnData;
  }


  
  public Quest[] getAvailableQuests(PlayerEntity player) {
    IEntityStats entityProps = EntityStatsCapability.get((LivingEntity)player);
    List<Quest> availableQuests = new ArrayList<>();
    
    if (entityProps.isSniper()) {
      availableQuests.addAll(Arrays.asList(ModQuests.SNIPER_TRIALS));
    }
    Quest[] quests = new Quest[availableQuests.size()];
    return availableQuests.<Quest>toArray(quests);
  }


  
  public HakiType getTrainingHaki() {
    return HakiType.IMBUING;
  }
}


