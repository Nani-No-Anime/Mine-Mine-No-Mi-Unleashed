package xyz.pixelatedw.mineminenomi.entities.mobs.animals;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.bandits.AbstractBanditEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.JumpAttackGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.lapahn.LapahnRageGoal;
import xyz.pixelatedw.mineminenomi.entities.mobs.marines.AbstractMarineEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.AbstractPirateEntity;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.zou.GreatStompParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import java.util.List;
import java.util.UUID;

public class LapahnEntity extends OPEntity {
  private static final AttributeModifier RAGE_MODIFIER = (new AttributeModifier(UUID.fromString("4b03a4b4-1eb5-464a-8312-0f9079044462"), "Rage Mode Multiplier", 10.0D, AttributeModifier.Operation.ADDITION)).setSaved(false);
  private static final DataParameter<Boolean> IS_ENRAGED = EntityDataManager.createKey(LapahnEntity.class, DataSerializers.BOOLEAN);
  private static final ParticleEffect PARTICLES = (ParticleEffect)new GreatStompParticleEffect();

  
  public LapahnEntity(World world) {
    super(ModEntities.LAPAHN, world);
    this.goalSelector.addGoal(0, (Goal)new SwimGoal(this));
    this.goalSelector.addGoal(0, (Goal)new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(1, (Goal)new JumpAttackGoal(this));
    this.goalSelector.addGoal(1, (Goal)new LapahnRageGoal(this));
    this.goalSelector.addGoal(3, (Goal)new WaterAvoidingRandomWalkingGoal(this, 0.8D));
    this.goalSelector.addGoal(4, (Goal)new LookAtGoal(this, PlayerEntity.class, 8.0F));
    this.goalSelector.addGoal(4, (Goal)new LookRandomlyGoal(this));
    
    this.targetSelector.addGoal(1, (Goal)new HurtByTargetGoal(this, new Class[0]));
    this.targetSelector.addGoal(2, (Goal)new NearestAttackableTargetGoal(this, PlayerEntity.class, true));
    this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal(this, AbstractPirateEntity.class, true));
    this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal(this, AbstractBanditEntity.class, true));
    this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal(this, AbstractMarineEntity.class, true));
    this.targetSelector.addGoal(3, (Goal)new NearestAttackableTargetGoal(this, HumandrillEntity.class, true));
  }



  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(55.0D);
    getAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.20000000298023224D);
    getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(50.0D);
    
    setDoriki(10.0D + WyHelper.randomWithRange(0, 5));
    setBelly(0.0D);
  }


  
  protected void registerData() {
    super.registerData();
    getDataManager().register(IS_ENRAGED, Boolean.valueOf(false));
  }


  
  public void writeAdditional(CompoundNBT compound) {
    super.writeAdditional(compound);
    compound.putBoolean("isEnraged", ((Boolean)this.dataManager.get(IS_ENRAGED)).booleanValue());
  }


  
  public void readAdditional(CompoundNBT compound) {
    super.readAdditional(compound);
    this.dataManager.set(IS_ENRAGED, Boolean.valueOf(compound.getBoolean("isEnraged")));
  }

  
  public boolean isEnraged() {
    return ((Boolean)this.dataManager.get(IS_ENRAGED)).booleanValue();
  }

  
  public void setEnraged(boolean value) {
    this.dataManager.set(IS_ENRAGED, Boolean.valueOf(value));
    IAttributeInstance attr = getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);
    attr.removeModifier(RAGE_MODIFIER);
    attr.applyModifier(RAGE_MODIFIER);
  }


  
  public boolean onLivingFall(float distance, float damageMultiplier) {
    if (distance > 5.0F && !this.world.isRemote) {
      
      PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
      List<LivingEntity> targets = WyHelper.getEntitiesNear(getPosition(), this.world, 5.0D);
      targets.remove(this);
      
      float damage = 2.0F + (float)getAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
      for (LivingEntity entity : targets) {
        
        if (!(entity instanceof LapahnEntity)) {
          
          entity.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this), damage);
          entity.setMotion(0.0D, 0.5D, 0.0D);
          entity.velocityChanged = true;
        } 
      } 
      
      return false;
    } 
    
    return super.onLivingFall(distance, damageMultiplier);
  }
}


