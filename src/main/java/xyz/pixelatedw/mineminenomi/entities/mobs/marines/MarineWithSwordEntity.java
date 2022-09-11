package xyz.pixelatedw.mineminenomi.entities.mobs.marines;
import javax.annotation.Nullable;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayGoal;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class MarineWithSwordEntity extends AbstractMarineEntity {
  private static final String[] DEFAULT_TEXTURES = new String[] { "marine1", "marine2", "marine3", "marine4", "marine5" };

  
  public MarineWithSwordEntity(World world) {
    super(ModEntities.MARINE_WITH_SWORD, world, DEFAULT_TEXTURES);
  }


  
  protected void registerGoals() {
    super.registerGoals();
    if (getRNG().nextInt(10) > 0)
      this.goalSelector.addGoal(0, (Goal)new RunAwayGoal(this, 1.5D)); 
    this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(-1.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(4.0D);
    
    setDoriki(10.0D + WyHelper.randomWithRange(0, 5));
    setBelly(5.0D + WyHelper.randomWithRange(0, 5));
  }


  
  protected void registerData() {
    super.registerData();
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    ItemStack randomSword = new ItemStack((IItemProvider)MARINE_SWORDS[this.rand.nextInt(MARINE_SWORDS.length)]);
    setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
    
    return spawnData;
  }
}


