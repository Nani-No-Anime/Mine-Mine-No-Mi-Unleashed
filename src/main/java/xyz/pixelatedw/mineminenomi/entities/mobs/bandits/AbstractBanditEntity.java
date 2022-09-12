package xyz.pixelatedw.mineminenomi.entities.mobs.bandits;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.pathfinding.GroundPathNavigator;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.ImprovedHurtByTargetGoal;
import xyz.pixelatedw.mineminenomi.init.ModWeapons;

import java.util.function.Predicate;

public abstract class AbstractBanditEntity
  extends OPEntity {
  protected static final Item[] BANDIT_SWORDS = new Item[] { (Item)ModWeapons.BANDIT_KNIFE, (Item)ModWeapons.PIRATE_CUTLASS, Items.IRON_SWORD, Items.STONE_SWORD, Items.STONE_AXE, Items.IRON_AXE };

  
  protected AbstractBanditEntity(EntityType<? extends MobEntity> type, World world) {
    this(type, world, (String[])null);
  }

  
  protected AbstractBanditEntity(EntityType<? extends MobEntity> type, World world, String[] textures) {
    super(type, world, textures);
  }


  
  protected void registerGoals() {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
    props.setFaction("bandit");
    
    ((GroundPathNavigator)getNavigator()).setBreakDoors(true);
    
    this.goalSelector.addGoal(0, (Goal)new SwimGoal(this));
    this.goalSelector.addGoal(0, (Goal)new OpenDoorGoal(this, true));
    this.goalSelector.addGoal(2, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(3, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(3, (Goal)new LookRandomlyGoal(this));
    
    Predicate<Entity> factionScope = FactionHelper.getOutsideGroupPredicate((LivingEntity)this);
    Predicate<Entity> invisibleEntity = entity -> (entity instanceof LivingEntity) ? (!((LivingEntity)entity).isPotionActive(Effects.INVISIBILITY)) : false;



    
    this.targetSelector.addGoal(1, (Goal)new ImprovedHurtByTargetGoal(this, factionScope, new Class[0]));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, OPEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, PlayerEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, MonsterEntity.class, true, true));
  }


  
  protected void registerData() {
    super.registerData();
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
}


