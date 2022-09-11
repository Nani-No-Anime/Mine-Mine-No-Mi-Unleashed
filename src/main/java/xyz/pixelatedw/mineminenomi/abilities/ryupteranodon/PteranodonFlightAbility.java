package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAttributes;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

public class PteranodonFlightAbility extends PassiveAbility implements IFormRequiredAbility {
  public static final PteranodonFlightAbility INSTANCE = new PteranodonFlightAbility();

  
  public PteranodonFlightAbility() {
    super("Pteranodon Flight", AbilityHelper.getDevilFruitCategory());
    this.duringPassiveEvent = this::duringPassiveEvent;
  }

  
  public void duringPassiveEvent(PlayerEntity player) {
    IAbilityData AbilityProps = AbilityDataCapability.get((LivingEntity)player);
    
    if (player.getAttribute(ModAttributes.JUMP_HEIGHT).getValue() < 0.0D || AbilityHelper.isNearbyKairoseki(player)) {
      return;
    }
    PteranodonFlyPointAbility ability = (PteranodonFlyPointAbility)AbilityProps.getEquippedAbility((Ability)PteranodonFlyPointAbility.INSTANCE);
    
    if (ability == null || !ability.isContinuous()) {
      return;
    }
    boolean isSprinting = player.isSprinting();
    
    float maxSpeed = isSprinting ? 2.1F : 1.5F;
    float acceleration = isSprinting ? 0.012F : 0.006F;
    
    TempuraudonAbility tempura = (TempuraudonAbility)AbilityProps.getEquippedAbility((Ability)TempuraudonAbility.INSTANCE);
    TankyudonAbility tankyudonAbility = (TankyudonAbility)AbilityProps.getEquippedAbility((Ability)TankyudonAbility.INSTANCE);
    
    acceleration *= (ability.speed > 0.0F) ? (1.0F - ability.speed / maxSpeed) : 1.0F;
    acceleration = (player.moveForward > 0.0F && !player.collided) ? acceleration : (-maxSpeed / 10.0F);
    ability.speed = MathHelper.clamp(ability.speed + acceleration, (acceleration > 0.0F) ? (maxSpeed / 5.0F) : 0.0F, maxSpeed);
    float speed = ability.speed;
    
    if (tempura != null && tempura.isCharging()) {
      ability.speed = speed = 0.0F;
    } else if (tankyudonAbility != null && tankyudonAbility.canGrab()) {
      speed++;
    } 
    int d1 = player.onGround ? 1 : 0;
    int d2 = (player.moveForward > 0.0F || (tankyudonAbility != null && tankyudonAbility.canGrab())) ? 1 : 0;
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
    return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE };
  }
}


