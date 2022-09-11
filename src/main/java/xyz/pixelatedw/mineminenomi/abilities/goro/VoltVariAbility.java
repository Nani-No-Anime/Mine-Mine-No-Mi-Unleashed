package xyz.pixelatedw.mineminenomi.abilities.goro;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.VoltVariProjectile;
import xyz.pixelatedw.mineminenomi.particles.effects.goro.GenericUseLightningEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class VoltVariAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new VoltVariAbility();
  private static final GenericUseLightningEffect PARTICLES = new GenericUseLightningEffect();
  
  private int power = 0;

  
  public VoltVariAbility() {
    super("Volt Vari", AbilityHelper.getDevilFruitCategory());
    setDescription("The user discharges variable amounts of electricity, in the form of a concentrated ball or a discharge");
    setMaxCooldown(10.0D);
    setMaxChargeTime(5.0D);
    setCancelable();
    
    this.duringChargingEvent = this::duringChargingEvent;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }

  
  private void duringChargingEvent(PlayerEntity player, int chargeTimer) {
    this.power = chargeTimer;
    if (chargeTimer % 5 == 0) {
      PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), 0.0D, 0.0D, 0.0D);
    }
    AbilityHelper.slowEntityFall((LivingEntity)player);
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    float truePower = Math.abs(this.power - getMaxChargeTime());
    
    VoltVariProjectile projectile = new VoltVariProjectile(player.world, (LivingEntity)player, truePower);
    projectile.setSize(truePower / 4.0F);
    player.world.addEntity((Entity)projectile);
    projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 1.0F);
    
    setMaxCooldown((truePower / 10.0F));
    return true;
  }
}


