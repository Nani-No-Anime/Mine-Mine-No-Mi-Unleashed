package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class WashedEffect
  extends Effect {
  public WashedEffect() {
    super(EffectType.BENEFICIAL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }

  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }

  
  public void performEffect(LivingEntity entity, int amplifier) {
    if (entity.world.isRemote) {
      return;
    }
    for (int i = 0; i < 15; i++) {
      
      double offsetX = WyHelper.randomDouble() / 2.0D;
      double offsetY = WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble() / 2.0D;
      
      ParticleType<GenericParticleData> particle = ModParticleTypes.AWA;
      if (i % 3 == 0) {
        particle = ModParticleTypes.AWA3;
      }
      GenericParticleData data = new GenericParticleData(particle);
      data.setLife(7);
      data.setSize(1.3F);
      WyHelper.spawnParticles(data, (ServerWorld)entity.world, entity.getPosX() + offsetX, entity.getPosY() + 1.0D + offsetY, entity.getPosZ() + offsetZ);
    } 
    
    if (entity.isWet())
    {
      entity.removePotionEffect(this);
    }
  }
}


