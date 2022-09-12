package xyz.pixelatedw.mineminenomi.entities.mobs;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class SkypieanCivilianEntity extends OPEntity {
  private static final String[] DEFAULT_TEXTURES = new String[] { "skypiean_civilian1", "skypiean_civilian2", "skypiean_civilian3" };

  
  public SkypieanCivilianEntity(World world) {
    super(ModEntities.SKYPIEAN_CIVILIAN, world, DEFAULT_TEXTURES);
  }


  
  protected void registerGoals() {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)this);
    props.setFaction("civilian");
    
    this.goalSelector.addGoal(1, (Goal)new SwimGoal(this));
    this.goalSelector.addGoal(2, (Goal)new OpenDoorGoal(this, false));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(5, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(5, (Goal)new LookRandomlyGoal(this));
    
    this.targetSelector.addGoal(0, (Goal)new HurtByTargetGoal(this, new Class[0]));
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(10.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    getAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(0.0D);
  }
}


