package xyz.pixelatedw.mineminenomi.effects;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.EffectType;
import net.minecraft.util.ResourceLocation;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.effects.OverlayEffect;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class StickyEffect extends OverlayEffect {
  public StickyEffect() {
    super(EffectType.HARMFUL, WyHelper.hexToRGB("#000000").getRGB());
  }


  
  public boolean isReady(int duration, int amplifier) {
    return true;
  }

  
  public void performEffect(LivingEntity entity, int amplifier) {
    if (entity.isBurning() && entity.ticksExisted > 0) {
      
      entity.extinguish();
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)entity, entity.world, entity.getPosX(), entity.getPosY(), entity.getPosZ(), 6.0F);
      explosion.setExplosionSound(true);
      explosion.setDamageOwner(true);
      explosion.setDestroyBlocks(true);
      explosion.setFireAfterExplosion(true);
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(6));
      explosion.setDamageEntities(true);
      explosion.setStaticDamage(100.0F);
      explosion.doExplosion();
      entity.removePotionEffect(ModEffects.STICKY);
    } 
  }


  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }


  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }


  
  public float[] getOverlayColor() {
    return new float[] { 0.62F, 0.78F, 0.0F, 0.95F };
  }


  
  public boolean hasBodyOverlayColor() {
    return true;
  }


  
  public boolean isBlockingRotations() {
    return true;
  }

  
  public ResourceLocation getResourceLocation(int duration) {
    return null;
  }


  
  public Block getBlockOverlay() {
    return null;
  }
}


