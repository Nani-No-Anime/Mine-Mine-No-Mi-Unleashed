package xyz.pixelatedw.mineminenomi.effects;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModRenderTypes;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

public class ShinzoMassageEffect extends GuardingEffect {
  int duration;
  
  public ShinzoMassageEffect() {
    super(false);


    
    this.duration = 0;
    this.reduceSpeedAfterHit = true;
  }
  public boolean isReady(int duration, int amplifier) {
    this.duration = duration;
    return (duration % 20 == 0);
  }


  
  public void performEffect(LivingEntity entity, int amplifier) {
    if (entity instanceof net.minecraft.entity.player.PlayerEntity && !entity.world.isRemote) {
      
      if (this.duration % 100 == 0)
        entity.world.playMovingSound(null, (Entity)entity, ModSounds.LONG_ELECTRIC_DISCHARGE_SFX, SoundCategory.PLAYERS, 0.5F, 0.5F); 
      entity.world.playMovingSound(null, (Entity)entity, ModSounds.HEART_BEAT_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
      
      if (this.duration > 500) {
        
        List<LivingEntity> list = WyHelper.getEntitiesNear(entity.getPosition(), entity.world, 8.0D, FactionHelper.getOutsideGroupPredicate(entity), new Class[] { LivingEntity.class });
        list.remove(entity);
        
        list.stream().filter(e -> e.attackEntityFrom((DamageSource)ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)entity), 12.0F)).forEachOrdered(e -> {
              entity.removePotionEffect(ModEffects.PARALYSIS);
              
              Vec3d speed = e.getLook(1.0F).mul(-1.0D, -1.0D, -1.0D).mul(3.0D, 0.0D, 3.0D);
              
              e.setMotion(speed.x, 1.0D, speed.z);
              e.velocityChanged = true;
            });
        int range = 64;
        for (int j = 0; j < range; j++) {
          
          float boltSize = 8.0F;

          
          LightningEntity bolt = new LightningEntity((Entity)entity, entity.getPosX(), entity.getPosY() + 0.5D, entity.getPosZ(), (float)WyHelper.randomWithRange(0, 360), (float)WyHelper.randomWithRange(-90, 90), boltSize, boltSize);
          
          bolt.setAngle(20);
          bolt.setAliveTicks(20);
          bolt.setDamage(0.0F);
          bolt.setExplosion(0, false);
          bolt.setSize(boltSize / 800.0F);
          bolt.setBranches(3);
          bolt.setSegments(10);
          bolt.disableLightningMimic();
          entity.world.addEntity((Entity)bolt);
        } 
      } 
    } 
  }




  
  public float[] getOverlayColor() {
    return new float[] { 0.0F, 0.8F, 1.0F, (float)(0.5D + WyHelper.randomWithRange(-1, 1) / 10.0D) };
  }

  
  public boolean shouldRender(EffectInstance effect) {
    return false;
  }

  
  public boolean shouldRenderHUD(EffectInstance effect) {
    return false;
  }

  
  public boolean hasBodyOverlayColor() {
    return true;
  }

  
  public Block getBlockOverlay() {
    return null;
  }

  
  public boolean isBlockingRotations() {
    return false;
  }

  
  public ResourceLocation getResourceLocation(int duration) {
    return null;
  }
  
  @OnlyIn(Dist.CLIENT)
  public RenderType getRenderType() {
    return ModRenderTypes.ENERGY;
  }
}


