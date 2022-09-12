package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;

import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntityTextureWeight;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedMeleeAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.RunAwayGoal;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;

public class PirateWithSwordEntity extends AbstractPirateEntity {
  private static final String[] DEFAULT_TEXTURES = new String[] { "pirate1", "pirate2", "pirate3", "pirate4", "pirate5" };
  private static final String[] FISHMAN_TEXTURES = new String[] { "fishman_pirate1", "fishman_pirate2", "fishman_pirate3" };

  
  public PirateWithSwordEntity(World world) {
    super(ModEntities.PIRATE_WITH_SWORD, world, DEFAULT_TEXTURES);
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
    
    ItemStack randomSword = new ItemStack((IItemProvider)PIRATE_SWORDS[this.rand.nextInt(PIRATE_SWORDS.length)]);
    setItemStackToSlot(EquipmentSlotType.MAINHAND, randomSword);
    
    IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
    if (props.isFishman()) {
      
      this.textures = OPEntityTextureWeight.fromList(FISHMAN_TEXTURES);
      chooseTexture();
    } 
    
    return spawnData;
  }


  
  protected ITextComponent getProfessionName() {
    ItemStack equippedItem = getItemStackFromSlot(EquipmentSlotType.MAINHAND);
    if (equippedItem != null && equippedItem.getItem() instanceof net.minecraft.item.AxeItem)
      return (ITextComponent)new StringTextComponent("Pirate with Axe"); 
    return super.getProfessionName();
  }
}


