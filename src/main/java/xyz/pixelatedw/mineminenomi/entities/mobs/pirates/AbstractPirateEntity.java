package xyz.pixelatedw.mineminenomi.entities.mobs.pirates;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.potion.Effects;
import net.minecraft.util.IItemProvider;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedHurtByTargetGoal;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.function.Predicate;

public abstract class AbstractPirateEntity extends OPEntity {
  protected static final Item[] PIRATE_SWORDS = new Item[] { (Item)ModWeapons.PIRATE_CUTLASS, Items.IRON_SWORD, Items.STONE_SWORD, Items.STONE_AXE, Items.IRON_AXE };
  private static final DataParameter<String> CREW = EntityDataManager.createKey(AbstractPirateEntity.class, DataSerializers.STRING);

  
  protected AbstractPirateEntity(EntityType<? extends MobEntity> type, World world) {
    this(type, world, (String[])null);
  }

  
  protected AbstractPirateEntity(EntityType<? extends MobEntity> type, World world, String[] textures) {
    super(type, world, textures);
  }


  
  protected void registerGoals() {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
    props.setFaction("pirate");
    if (this.world.getRandom().nextInt(10) < 3) {
      
      props.setRace("fishman");
      queueEntityDataUpdate();
    } 
    
    ((GroundPathNavigator)getNavigator()).setBreakDoors(true);
    
    this.goalSelector.addGoal(0, (Goal)new SwimGoal(this));
    this.goalSelector.addGoal(0, (Goal)new OpenDoorGoal(this, true));
    this.goalSelector.addGoal(2, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(3, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(3, (Goal)new LookRandomlyGoal(this));
    
    Predicate<Entity> factionScope = FactionHelper.getOutsideGroupPredicate((LivingEntity)this);
    Predicate<Entity> invisibleEntity = entity -> (entity instanceof LivingEntity) ? (!((LivingEntity)entity).isPotionActive(Effects.INVISIBILITY)) : false;



    
    this.targetSelector.addGoal(1, (Goal)new ImprovedHurtByTargetGoal(this, factionScope, new Class[0]));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, OPEntity.class, 10, true, false, factionScope.and(invisibleEntity)));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, PlayerEntity.class, 10, true, false, factionScope.and(invisibleEntity)));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, MonsterEntity.class, true, true));
  }


  
  protected void registerData() {
    super.registerData();
    getDataManager().register(CREW, "");
  }


  
  public void writeAdditional(CompoundNBT nbt) {
    super.writeAdditional(nbt);
    nbt.putString("crew", getCrew());
  }


  
  public void readAdditional(CompoundNBT nbt) {
    super.readAdditional(nbt);
    setCrew(nbt.getString("crew"));
  }


  
  protected boolean isDespawnPeaceful() {
    return true;
  }


  
  public boolean canDespawn(double distance) {
    if (distance > 1024.0D) {
      return true;
    }
    return false;
  }

  
  public String getCrew() {
    return (String)getDataManager().get(CREW);
  }

  
  public void setCrew(String crew) {
    getDataManager().set(CREW, crew);
  }


  
  protected void dropInventory() {
    super.dropInventory();
    if (getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == ModArmors.MH5_GAS_MASK && WyHelper.randomDouble() < 0.2D)
      entityDropItem((IItemProvider)ModArmors.MH5_GAS_MASK); 
  }
}


