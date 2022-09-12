package xyz.pixelatedw.mineminenomi.abilities.goro;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.entities.zoan.VoltAmaruZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;

import java.awt.*;

public class SangoAbility extends RepeaterAbility {
  public static final Ability INSTANCE = (Ability)new SangoAbility();

  
  public SangoAbility() {
    super("Sango", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(15.0D);
    setMaxRepeaterCount(3, 1);
    setDescription("Launches powerful charges of electricity from the hands");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)player, 384.0D);
    double beamDistance = Math.sqrt(player.getDistanceSq((blockRayTraceResult.getHitVec()).x, (blockRayTraceResult.getHitVec()).y, (blockRayTraceResult.getHitVec()).z));
    
    float multiplier = 1.0F;
    if (VoltAmaruZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      multiplier = (float)(multiplier * 1.5D);
    }
    float damage = 20.0F * multiplier;
    float size = 0.28F * multiplier;
    float length = 50.0F * multiplier;
    
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    LightningEntity bolt = new LightningEntity((Entity)player, length + (float)beamDistance, 20.0F);
    bolt.setAliveTicks(30);
    bolt.setDamage(damage);
    bolt.setExplosion((int)(3.0F * multiplier), true, 0.3F, 10);
    bolt.setSize(size);
    bolt.setBoxSizeDivision(0.22499999403953552D);
    bolt.setAngle(100);
    bolt.setTargetTimeToReset(60);
    bolt.disableExplosionKnockback();
    
    bolt.setBranches((int)(5.0D + beamDistance / 100.0D));
    int segments = (int)(beamDistance * 0.5D);
    bolt.setSegments((int)(segments + WyHelper.randomWithRange(-segments / 4, segments / 4)));
    player.world.addEntity((Entity)bolt);
    
    if (damage > 14.0F) {
      
      long seed = bolt.seed;
      bolt = new LightningEntity((Entity)player, length + (float)beamDistance, 20.0F);
      bolt.seed = seed;
      bolt.setAliveTicks(25);
      bolt.setDamage(0.0F);
      bolt.setExplosion(0, false);
      bolt.setSize(size * 0.9F);
      bolt.setBoxSizeDivision(0.22499999403953552D);
      bolt.setColor(new Color(255, 255, 255, 10));
      bolt.setAngle(100);
      
      bolt.setBranches((int)(5.0D + beamDistance / 100.0D));
      bolt.setSegments((int)(segments + WyHelper.randomWithRange(-segments / 4, segments / 4)));
      player.world.addEntity((Entity)bolt);
    } 
    return true;
  }
}


