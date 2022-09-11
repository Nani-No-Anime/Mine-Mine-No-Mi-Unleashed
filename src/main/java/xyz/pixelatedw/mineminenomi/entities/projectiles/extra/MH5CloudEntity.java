package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModArmors;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.extra.MH5GasParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class MH5CloudEntity
  extends EntityCloud {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new MH5GasParticleEffect();

  
  public MH5CloudEntity(World world) {
    super(world);
  }


  
  public void remove() {
    for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(getPosition(), this.world, 100.0D)) {
      
      ItemStack stack = target.getItemStackFromSlot(EquipmentSlotType.HEAD);
      if (stack == null || stack.getItem() != ModArmors.MH5_GAS_MASK)
      {
        target.attackEntityFrom(DamageSource.MAGIC, target.getHealth());
      }
    } 
    
    super.remove();
  }


  
  public void tick() {
    super.tick();
    if (!this.world.isRemote)
    {
      if (this.ticksExisted % 2 == 0)
        PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
    }
  }
}


