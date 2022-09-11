package xyz.pixelatedw.mineminenomi.abilities.horu;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModSounds;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.horu.WinkExplosionEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.horu.WinkParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class DeathWinkAbility extends Ability {
  public static final DeathWinkAbility INSTANCE = new DeathWinkAbility();
  
  private static final ParticleEffect WINK = (ParticleEffect)new WinkParticleEffect();

  
  public DeathWinkAbility() {
    super("Death Wink", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(6.0D);
    setDescription("The user winks really hard creating a shockwave; Ganmen Seicho boosts it's power");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    player.world.playSound(null, player.getPosition(), ModSounds.DEATH_WINK_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
    EntityRayTraceResult trace = WyHelper.rayTraceEntities((Entity)player, 1.0D);
    WINK.spawn(player.world, trace.getHitVec().getX(), player.getPosY(), trace.getHitVec().getZ(), 0.0D, 0.0D, 0.0D);
    
    int power = player.isPotionActive(ModEffects.GANMEN_SEICHO_HORMONE) ? 3 : 2;
    boolean createExplosion = true;
    
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, (8 * power));
    
    double x = (mop.getHitVec()).x;
    double y = (mop.getHitVec()).y;
    double z = (mop.getHitVec()).z;
    
    if (mop instanceof EntityRayTraceResult) {
      Entity e = ((EntityRayTraceResult)mop).getEntity();
      if (e instanceof AbilityProjectileEntity && (
        (AbilityProjectileEntity)e).getDamage() < (power * 5)) {
        createExplosion = false;
        e.setMotion(-(e.getMotion()).x, (e.getMotion()).y, -(e.getMotion()).x);
      } 
    } 

    
    if (createExplosion) {
      
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, x, y, z, (1 + power));
      explosion.setStaticDamage((power * 10));
      explosion.setExplosionSound(false);
      explosion.setSmokeParticles((ParticleEffect)new WinkExplosionEffect(2));
      explosion.doExplosion();
      
      double distance = Math.sqrt(player.getDistanceSq(x, y, z));
      if (distance < 0.5D) {
        
        player.setMotion(WyHelper.randomWithRange(-1, 1) * 0.5D * power, 1.5D * power, WyHelper.randomWithRange(-1, 1) * 0.5D * power);
        ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
      } 
    } 
    
    return true;
  }
}


