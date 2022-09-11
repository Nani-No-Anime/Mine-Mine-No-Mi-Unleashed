package xyz.pixelatedw.mineminenomi.abilities.kira;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;

public class CabochonKnuckleAbility extends PunchAbility implements IPunchOverlayAbility {
  public static final CabochonKnuckleAbility INSTANCE = new CabochonKnuckleAbility();
  
  private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DIAMOND_BODY);

  
  public CabochonKnuckleAbility() {
    super("Cabochon Knuckle", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(8.0D);
    setDescription("A diamond covered punch, dealing damage and slight knockback");
    
    this.onHitEntityEvent = this::onHitEvent;
    this.onHitEffectEvent = this::onHitEffectEvent;
  }

  
  private void onHitEffectEvent(PlayerEntity player, LivingEntity target) {
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
    target.setMotion(speed.x, player.getMotion().getY() + 0.1D, speed.z);
    if (target instanceof PlayerEntity) {
      ((ServerPlayerEntity)target).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)target));
    }
  }
  
  private float onHitEvent(PlayerEntity player, LivingEntity target) {
    return 15.0F;
  }


  
  public AbilityOverlay getPunchOverlay(LivingEntity entity) {
    return OVERLAY;
  }
}


