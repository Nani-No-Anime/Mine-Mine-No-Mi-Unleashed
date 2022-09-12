package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.potion.Effects;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class SmokeEffect extends Effect {
  private int smokeTime = 0;

  
  public SmokeEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }

  
  public boolean isReady(int duration, int amplifier) {
    if (duration == 1) {
      
      this.smokeTime = 0;
      return false;
    } 
    
    return true;
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    if (entity.world.isRemote) {
      return;
    }
    for (int i = 0; i < 80; i++) {
      
      double offsetX = WyHelper.randomDouble() / 2.0D;
      double offsetY = 1.0D + WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble() / 2.0D;
      
      ParticleType<GenericParticleData> particle = ModParticleTypes.MOKU2;
      if (i % 3 == 0) {
        particle = ModParticleTypes.MOKU;
      }
      GenericParticleData data = new GenericParticleData(particle);
      data.setLife((int)WyHelper.randomWithRange(1, 10));
      data.setSize((float)WyHelper.randomWithRange(0, 4));
      WyHelper.spawnParticles(data, (ServerWorld)entity.world, entity.getPosX() + offsetX, entity.getPosY() + offsetY, entity.getPosZ() + offsetZ);
    } 
    
    if (entity.isWet())
    {
      entity.removePotionEffect(this);
    }
    this.smokeTime++;
    
    if (this.smokeTime > 100) {
      
      entity.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, this.smokeTime / 100, false, false));
      if (this.smokeTime > 200)
      {
        entity.addPotionEffect(new EffectInstance(Effects.BLINDNESS, 100, this.smokeTime / 200, false, false));
      }
    } 
  }
}


