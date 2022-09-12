package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;

import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.entities.ai.IBrawler;
import xyz.pixelatedw.mineminenomi.api.entities.ai.IHakiUser;
import xyz.pixelatedw.mineminenomi.api.entities.ai.IRokushikiUser;
import xyz.pixelatedw.mineminenomi.api.entities.ai.ISwordsman;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.GapCloserGoal;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;

public class PirateCaptainEntity extends AbstractPirateEntity implements IRokushikiUser, ISwordsman, IBrawler, IHakiUser {
  private static final String[] DEFAULT_TEXTURES = new String[] { "pirate_captain1", "pirate_captain2", "pirate_captain3", "pirate_captain4", "pirate_captain5" };

  
  public PirateCaptainEntity(World world) {
    super(ModEntities.PIRATE_CAPTAIN, world, DEFAULT_TEXTURES);
  }


  
  protected void registerGoals() {
    super.registerGoals();
    this.goalSelector.addGoal(1, (Goal)new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(2, (Goal)new GapCloserGoal(this));
    addRokushikiAbilities(this, 2);
    addSwordsmanAbilities(this, 2);
    addBrawlerAbilities(this, 3);
    addBusoshokuHaki(this, 30);

    
    setDoriki(20.0D + WyHelper.randomWithRange(0, 10) + getThreat());
    setBelly(30.0D + WyHelper.randomWithRange(0, 20));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(WyHelper.randomWithRange(12, 15));
  }


  
  protected void registerData() {
    super.registerData();
  }


  
  @Nullable
  public ILivingEntityData onInitialSpawn(IWorld world, DifficultyInstance difficulty, SpawnReason reason, @Nullable ILivingEntityData spawnData, @Nullable CompoundNBT dataTag) {
    spawnData = super.onInitialSpawn(world, difficulty, reason, spawnData, dataTag);
    
    if (this.rand.nextDouble() < 0.4D) {
      
      ItemStack capeStack = new ItemStack((IItemProvider)ModArmors.PIRATE_CAPTAIN_CAPE);
      setItemStackToSlot(EquipmentSlotType.CHEST, capeStack);
    } 
    
    if (this.rand.nextDouble() < 0.8D) {
      
      ItemStack randomSword = new ItemStack((IItemProvider)PIRATE_SWORDS[this.rand.nextInt(PIRATE_SWORDS.length)]);
      setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
      
      if (this.rand.nextDouble() < 0.2D) {
        setItemStackToSlot(EquipmentSlotType.OFFHAND, randomSword);
      }
    } 
    if (this.rand.nextDouble() < 0.7D)
    {
      if (this.rand.nextDouble() < 0.3D) {
        setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.CIGAR));
      } else {
        setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModItems.CIGAR_LESS));
      } 
    }
    return spawnData;
  }
}


