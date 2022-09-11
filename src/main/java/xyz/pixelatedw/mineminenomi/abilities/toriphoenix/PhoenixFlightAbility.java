package xyz.pixelatedw.mineminenomi.abilities.toriphoenix;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PhoenixFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class PhoenixFlightAbility extends PassiveAbility implements IFormRequiredAbility {
  public static final PhoenixFlightAbility INSTANCE = new PhoenixFlightAbility();

  
  public PhoenixFlightAbility() {
    super("Phoenix Flight", AbilityHelper.getDevilFruitCategory());
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  public void duringPassiveEvent(PlayerEntity player) {
    IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || AbilityHelper.isNearbyKairoseki(player)) {
      return;
    }
    PhoenixFlyPointAbility flyPointAbility = (PhoenixFlyPointAbility)abilityProps.getEquippedAbility((Ability)PhoenixFlyPointAbility.INSTANCE);
    TenseiNoSoenAbility tenseiNoSoenAbility = (TenseiNoSoenAbility)abilityProps.getEquippedAbility((Ability)TenseiNoSoenAbility.INSTANCE);
    
    boolean isTenseiActive = (tenseiNoSoenAbility != null && tenseiNoSoenAbility.isCharging() && tenseiNoSoenAbility.getChargeTime() < 20);
    
    if (flyPointAbility == null || !flyPointAbility.isContinuous() || isTenseiActive) {
      return;
    }
    boolean isSprinting = player.isSprinting();
    float maxSpeed = isSprinting ? 2.0F : 1.25F;
    float acceleration = isSprinting ? 0.014F : 0.007F;
    if (player.getActivePotionEffect(ModEffects.FATIGUE_EFFECT) != null) {
      maxSpeed /= (1 + Math.min(player.getActivePotionEffect(ModEffects.FATIGUE_EFFECT).getAmplifier(), 3));
    }
    FujiazamiAbility fujizami = (FujiazamiAbility)abilityProps.getEquippedAbility((Ability)FujiazamiAbility.INSTANCE);
    
    acceleration *= (flyPointAbility.speed > 0.0F) ? (1.0F - flyPointAbility.speed / maxSpeed) : 1.0F;
    acceleration = (player.moveForward > 0.0F && !player.collided) ? acceleration : (-maxSpeed / 10.0F);
    flyPointAbility.speed = MathHelper.clamp(flyPointAbility.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 5.0F) : 0.0F, maxSpeed);
    float speed = flyPointAbility.speed;
    
    if (fujizami != null && fujizami.isContinuous()) {
      flyPointAbility.speed = speed = 0.0F;
    }
    int d1 = player.onGround ? 1 : 0;
    int d2 = (player.moveForward > 0.0F) ? 1 : 0;
    int d3 = 0;
    int d4 = (maxSpeed / 3.0F >= speed || d2 == 0) ? 1 : 0;
    
    Vec3d vec = player.getLookVec();
    double x = vec.x * speed * (1 - d1) * d2 + d3 * (player.getMotion()).z;
    double y = (d1 * 0.65F) + vec.y * speed * (1 - d1) * d2 + (d3 * -0.5F) + d4 * Math.cos((player.ticksExisted / 4.0F)) / 5.0D;
    double z = vec.z * speed * (1 - d1) * d2 + d3 * (player.getMotion()).z;
    player.setMotion(x, y, z);
    
    if (player.getPosY() > (player.world.getMaxHeight() - 1)) {
      player.setMotion(0.0D, -3.0D, 0.0D);
    }
    player.fallDistance = 0.0F;
  }


  
  public ZoanInfo[] getRequiredForms(PlayerEntity player) {
    return new ZoanInfo[] { (ZoanInfo)PhoenixFlyZoanInfo.INSTANCE };
  }
}


