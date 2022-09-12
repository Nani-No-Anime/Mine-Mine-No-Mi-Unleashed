package xyz.pixelatedw.mineminenomi.abilities.blackleg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg.ExtraHachisProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.blackleg.ExtraHachiParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class ExtraHachisAbility extends RepeaterAbility {
  public static final ExtraHachisAbility INSTANCE = new ExtraHachisAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new ExtraHachiParticleEffect();
  
  private boolean diableJambeMode = false;

  
  public ExtraHachisAbility() {
    super("Extra Hachis", AbilityHelper.getStyleCategory());
    setMaxCooldown(12.0D);
    setMaxRepeaterCount(20, 2);
    setDescription("Launches a rapid barrage of kicks");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    ExtraHachisProjectile extraHachisProjectile = new ExtraHachisProjectile(player.world, (LivingEntity)player);
    int projectileSpace = 2;
    float speed = 2.0F;
    
    if (this.diableJambeMode) {
      
      extraHachisProjectile.setDamage(10.0F);
      ((AbilityProjectileEntity)extraHachisProjectile).onEntityImpactEvent = (entity -> entity.setFire(2));


      
      ((AbilityProjectileEntity)extraHachisProjectile).onTickEvent = (() -> PARTICLES.spawn(player.world, extraHachisProjectile.getPosX(), extraHachisProjectile.getPosY(), extraHachisProjectile.getPosZ(), 0.0D, 0.0D, 0.0D));
    } 


    
    extraHachisProjectile.setLocationAndAngles(player
        .getPosX() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), player
        .getPosY() + 1.5D + WyHelper.randomWithRange(0, projectileSpace) + WyHelper.randomDouble(), player
        .getPosZ() + WyHelper.randomWithRange(-projectileSpace, projectileSpace) + WyHelper.randomDouble(), 0.0F, 0.0F);
    
    player.world.addEntity((Entity)extraHachisProjectile);
    extraHachisProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, speed, 3.0F);
    
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    return true;
  }

  
  public void enableDiableJambeMode() {
    setDisplayName("Poêle à Frire");
    setCustomTexture("poele_a_frire");
    setMaxCooldown(15.0D);
    this.diableJambeMode = true;
  }

  
  public void disableDiableJambeMode() {
    setDisplayName(null);
    setCustomTexture("");
    setMaxCooldown(12.0D);
    this.diableJambeMode = false;
  }
}


