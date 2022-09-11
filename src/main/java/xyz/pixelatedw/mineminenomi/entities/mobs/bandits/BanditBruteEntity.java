package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;
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
import xyz.pixelatedw.mineminenomi.api.entities.ai.IBrawler;
import xyz.pixelatedw.mineminenomi.api.entities.ai.IHakiUser;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.CleaveAttackGoal;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class BanditBruteEntity extends AbstractBanditEntity implements IBrawler, IHakiUser {
  private static final String[] DEFAULT_TEXTURES = new String[] { "bandit1", "bandit2", "bandit3" };

  
  public BanditBruteEntity(World world) {
    super(ModEntities.BANDIT_BRUTE, world, DEFAULT_TEXTURES);
  }


  
  protected void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new ImprovedMeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(2, (Goal)(new CleaveAttackGoal(this, 100, 5, 4)).setAnimationId(OPEntity.Animation.CLEAVE_ATTACK.ordinal()));
    addBrawlerAbilities(this, 1);
    addBusoshokuHaki(this, 15);

    
    setDoriki(15.0D + WyHelper.randomWithRange(0, 12) + getThreat());
    setBelly(5.0D + WyHelper.randomWithRange(0, 5));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(WyHelper.randomWithRange(8, 12));
    getAttribute(ModAttributes.ATTACK_RANGE).setBaseValue(1.5D);
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    if (this.rand.nextDouble() < 0.4D) {
      setItemStackToSlot(EquipmentSlotType.MAINHAND, new ItemStack((IItemProvider)ModWeapons.MACE));
    }
    return spawnData;
  }
}


