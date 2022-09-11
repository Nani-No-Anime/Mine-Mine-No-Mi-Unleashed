package xyz.pixelatedw.mineminenomi.entities.mobs.quest.objectives;

import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModEntities;

public class SniperTargetEntity
  extends MobEntity
{
  public SniperTargetEntity(World world) {
    super(ModEntities.SNIPER_TARGET, world);
    this.experienceValue = 0;
    setAIMoveSpeed(0.0F);
  }


  
  protected void registerAttributes() {
    super.registerAttributes();
    getAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(1.0D);
  }


  
  public void tick() {
    setMotion(0.0D, -0.1D, 0.0D);
    this.fallDistance = 0.0F;
    
    if ((this.onGround || isInWater() || isInLava()) && !this.world.isRemote) {
      remove();
    }
    super.tick();
  }
}


