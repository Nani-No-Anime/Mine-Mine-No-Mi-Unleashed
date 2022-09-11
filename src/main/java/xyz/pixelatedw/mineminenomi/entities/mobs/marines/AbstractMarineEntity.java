package xyz.pixelatedw.mineminenomi.entities.mobs.marines;

import java.util.function.Predicate;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.OpenDoorGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
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

public abstract class AbstractMarineEntity
  extends OPEntity {
  protected static final Item[] MARINE_SWORDS = new Item[] { (Item)ModWeapons.MARINE_SWORD, Items.IRON_SWORD, Items.STONE_SWORD };

  
  protected AbstractMarineEntity(EntityType<? extends MobEntity> type, World world) {
    this(type, world, (String[])null);
  }

  
  protected AbstractMarineEntity(EntityType<? extends MobEntity> type, World world, String[] textures) {
    super(type, world, textures);
  }


  
  protected void registerGoals() {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
    props.setFaction("marine");
    
    ((GroundPathNavigator)getNavigator()).setBreakDoors(true);
    
    this.goalSelector.addGoal(0, (Goal)new SwimGoal((MobEntity)this));
    this.goalSelector.addGoal(0, (Goal)new OpenDoorGoal((MobEntity)this, true));
    this.goalSelector.addGoal(2, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(3, (Goal)new LookAtGoal((MobEntity)this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(3, (Goal)new LookRandomlyGoal((MobEntity)this));
    
    Predicate<Entity> factionScope = FactionHelper.getOutsideGroupPredicate((LivingEntity)this);
    Predicate<Entity> invisibleEntity = entity -> (entity instanceof LivingEntity) ? (!((LivingEntity)entity).isPotionActive(Effects.INVISIBILITY)) : false;



    
    this.targetSelector.addGoal(1, (Goal)new ImprovedHurtByTargetGoal(this, factionScope, new Class[0]));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, OPEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, PlayerEntity.class, 10, true, true, factionScope.and(invisibleEntity)));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal((MobEntity)this, MonsterEntity.class, true, true));
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


