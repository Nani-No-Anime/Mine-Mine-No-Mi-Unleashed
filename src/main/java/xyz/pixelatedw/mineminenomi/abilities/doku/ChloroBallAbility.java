package xyz.pixelatedw.mineminenomi.abilities.doku;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.ChloroBallProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.doku.DemonChloroBallProjectile;
import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class ChloroBallAbility extends RepeaterAbility {
  public static final ChloroBallAbility INSTANCE = new ChloroBallAbility();

  
  public ChloroBallAbility() {
    super("Chloro Ball", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(9.0D);
    setMaxRepeaterCount(1, 5);
    setDescription("The user spits a bubble made of poison towards the enemy, which also leaves poison on the ground");
    
    this.onUseEvent = this::onUseEvent;
  }


  
  private boolean onUseEvent(PlayerEntity player) {
    ChloroBallProjectile chloroBallProjectile;
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    Ability dokuFuguAbility = props.getEquippedAbility((Ability)DokuFuguAbility.INSTANCE);
             AbilityProjectileEntity projectile;
    
    if (VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
      DemonChloroBallProjectile demonChloroBallProjectile = new DemonChloroBallProjectile(player.world, (LivingEntity)player);
               projectile=demonChloroBallProjectile;
    } else if (dokuFuguAbility != null && dokuFuguAbility.isContinuous()) {
      chloroBallProjectile = new ChloroBallProjectile(player.world, (LivingEntity)player);
                projectile=chloroBallProjectile;
    } else {
      chloroBallProjectile = new ChloroBallProjectile(player.world, (LivingEntity)player);
                projectile=chloroBallProjectile;
    } 
    player.world.addEntity((Entity)projectile);
    projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }

  
  public void enableVenomDemoMode() {
    setCustomTexture("chloro_ball_venom");
    setDisplayName("Demon Chloro Ball");
    setMaxCooldown(10.0D);
    setMaxRepeaterCount(5, 3);
  }

  
  public void disableVenomDemoMode() {
    setCustomTexture("");
    setDisplayName("Chloro Ball");
    setMaxCooldown(9.0D);
    setMaxRepeaterCount(1, 5);
  }
}


