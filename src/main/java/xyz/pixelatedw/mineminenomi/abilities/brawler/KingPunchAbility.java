package xyz.pixelatedw.mineminenomi.abilities.brawler;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.brawler.KingPunchProjectile;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.particles.effects.brawler.KingPunchChargeParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KingPunchAbility extends ChargeableAbility {
  public static final KingPunchAbility INSTANCE = new KingPunchAbility();
  
  private static final KingPunchChargeParticleEffect PARTICLES = new KingPunchChargeParticleEffect();
  
  private boolean cancelled = false;
  
  public KingPunchAbility() {
    super("King Punch", AbilityHelper.getStyleCategory());
    setDescription("A punch of exceptionally concentrated strength, capable of releasing an immense amount of air pressure, but needs a long time to fully charge");
    setMaxCooldown(50.0D);
    setMaxChargeTime(50.0D);
    setCancelable();
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
    this.duringChargingEvent = this::duringChargingEvent;
  }

  
  private boolean onStartChargingEvent(PlayerEntity player) {
    this.cancelled = false;
    return true;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    if (!AbilityHelper.canUseBrawlerAbilities((LivingEntity)player)) {
      
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ABILITY_MESSAGE_NEED_FIST, new Object[0]));
      this.cancelled = true;
      endCharging(player);
    } 
    
    if (player.hurtResistantTime > 0) {
      
      this.cancelled = true;
      endCharging(player);
    } 
    
    player.addPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 5, 1));
    
    if (chargeTimer % 2 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
  }
  
  private boolean onEndChargingEvent(PlayerEntity player) {
    int charge = getMaxChargeTime() - getChargeTime();
    
    if (this.cancelled || charge < 300) {
      return true;
    }
    ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
    
    KingPunchProjectile kingPunchProjectile = new KingPunchProjectile(player.world, (LivingEntity)player);
    kingPunchProjectile.setDamage(charge / 12.0F);
    ((AbilityProjectileEntity)kingPunchProjectile).onBlockImpactEvent = (pos -> {
                 //error: cannot find symbol fix
                 AbilityProjectileEntity proj= kingPunchProjectile;
        ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)player, player.world, proj.getPosX(), proj.getPosY(), proj.getPosZ(), 1.0F + charge / 70.0F);
        
        explosion.setStaticDamage(0.0F);
        explosion.setExplosionSound(false);
        explosion.setDamageOwner(false);
        explosion.setDestroyBlocks(true);
        explosion.setFireAfterExplosion(false);
        explosion.setDamageEntities(false);
        explosion.doExplosion();
      });
    player.world.addEntity((Entity)kingPunchProjectile);
    kingPunchProjectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    player.removePotionEffect(ModEffects.MOVEMENT_BLOCKED);
    
    int cooldown = (int)Math.round(charge / 20.0D) + 5;
    setMaxCooldown(cooldown);
    return true;
  }
}


