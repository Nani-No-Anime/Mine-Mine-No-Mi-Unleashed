package xyz.pixelatedw.mineminenomi.entities.mobs.goals.abilities.donkrieg;

import java.util.List;
import net.minecraft.command.arguments.EntityAnchorArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.entities.mobs.OPEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.PirateWithSwordEntity;
import xyz.pixelatedw.mineminenomi.entities.mobs.pirates.kriegpirates.DonKriegEntity;
import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.MH5CloudEntity;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class MH5Goal extends Goal {
  private DonKriegEntity entity;
  private int duration = 0;
  
  private static final int TIMER = 1500;

  
  public MH5Goal(DonKriegEntity entity) {
    this.entity = entity;
  }



  
  public boolean shouldExecute() {
    if (this.entity.getAttackTarget() == null) {
      return false;
    }
    if (this.entity.getHealth() > WyHelper.percentage(30.0D, this.entity.getMaxHealth())) {
      return false;
    }
    if (this.entity.isMH5Active()) {
      return false;
    }
    if (this.entity.challengeSpawnPosition == null) {
      return false;
    }
    return true;
  }


  
  public void startExecuting() {
    this.entity.triggerMH5Phase();
    if (this.entity.isPotionActive(ModEffects.MOVEMENT_BLOCKED))
      this.entity.removePotionEffect(ModEffects.MOVEMENT_BLOCKED); 
    this.entity.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 1500, 0, false, false));
    BlockPos pos = this.entity.challengeSpawnPosition.add(-6, 2, -36);
    this.entity.setPositionAndRotation(pos.getX(), pos.getY(), pos.getZ(), 0.0F, 0.0F);
    
    this.entity.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModArmors.MH5_GAS_MASK));
  }


  
  public void tick() {
    if (this.entity.getAttackTarget() == null) {
      return;
    }
    this.entity.lookAt(EntityAnchorArgument.Type.EYES, this.entity.getAttackTarget().getPositionVec());
    
    if (this.entity.getAttackTarget().getDistance((Entity)this.entity) <= 5.0F) {
      
      this.entity.setAnimation(OPEntity.Animation.CLEAVE_ATTACK.ordinal());
      List<LivingEntity> targets = WyHelper.getEntitiesNear(this.entity.getPosition(), this.entity.world, 4.0D, new Class[] { LivingEntity.class });
      targets.remove(this.entity);
      float damage = (float)this.entity.getAttributes().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getValue();
      for (LivingEntity target : targets) {
        
        target.attackEntityFrom(DamageSource.causeMobDamage((LivingEntity)this.entity), damage);
        double x = target.getPosX() - this.entity.getPosX();
        double z;
        for (z = target.getPosZ() - this.entity.getPosZ(); x * x + z * z < 1.0E-4D; z = (Math.random() - Math.random()) * 0.01D)
        {
          x = (Math.random() - Math.random()) * 0.01D;
        }
        target.knockBack((Entity)target, 5.0F, -x, -z);
      } 
    } 
    
    if (this.duration % 20 == 0)
    {
      this.entity.setAnimation(OPEntity.Animation.NONE.getId());
    }
    
    if (this.duration % 200 == 0) {
      
      int iRand = this.entity.world.rand.nextInt(2);
      int iDifficulty = (this.entity.world.getDifficulty().getId() == 3) ? 5 : 0;
      for (int i = 0; i < 5 + iRand + iDifficulty; i++) {
        
        BlockPos pos = WyHelper.findOnGroundSpawnLocation(this.entity.world, ModEntities.PIRATE_WITH_SWORD, this.entity.getAttackTarget().getPosition(), 15, 10);
        if (pos == null)
          return; 
        PirateWithSwordEntity pirate = (PirateWithSwordEntity)ModEntities.PIRATE_WITH_SWORD.spawn(this.entity.world, null, null, pos, SpawnReason.EVENT, true, true);
        if (i % 3 == 0) {
          pirate.setItemStackToSlot(EquipmentSlotType.HEAD, new ItemStack((IItemProvider)ModArmors.MH5_GAS_MASK));
        }
      } 
    } 
  }

  
  public boolean shouldContinueExecuting() {
    this.duration++;
    return (this.duration < 1500);
  }


  
  public void resetTask() {
    this.duration = 0;
    
    MH5CloudEntity cloud = new MH5CloudEntity(this.entity.world);
    cloud.setLife(100);
    cloud.setLocationAndAngles(this.entity.getAttackTarget().getPosX(), this.entity.getAttackTarget().getPosY() + 1.0D, this.entity.getAttackTarget().getPosZ(), 0.0F, 0.0F);
    cloud.setMotion(0.0D, 0.0D, 0.0D);
    this.entity.world.addEntity((Entity)cloud);
  }
}


