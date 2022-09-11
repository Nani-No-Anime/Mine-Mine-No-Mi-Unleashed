package xyz.pixelatedw.mineminenomi.abilities.suna;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.suna.SablesProjectile;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;

public class SablesAbility extends ChargeableAbility {
  public static final Ability INSTANCE = (Ability)new SablesAbility();
  
  private SablesProjectile proj = null;
  private float maxMultiplier = 0.0F;

  
  public SablesAbility() {
    super("Sables", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(18.0D);
    setMaxChargeTime(6.0D);
    setDescription("The user launches a compressed sandstorm at the opponent, which sends them flying");
    
    this.onStartChargingEvent = this::onStartChargingEvent;
    this.duringChargingEvent = this::duringContinuity;
    this.onEndChargingEvent = this::onEndChargingEvent;
  }
  
  private boolean onStartChargingEvent(PlayerEntity player) {
    this.proj = null;
    this.maxMultiplier = (SunaHelper.isFruitBoosted(player) ? 2 : 1);
    return !player.isWet();
  }

  
  private boolean onEndChargingEvent(PlayerEntity player) {
    if (this.proj.isAlive())
      this.proj.shoot((Entity)player, 0.0F, 0.0F, 0.0F, 2.0F, 0.0F); 
    return true;
  }

  
  private void duringContinuity(PlayerEntity player, int i) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 100.0D);
    
    if (this.proj == null) {
      
      this.proj = new SablesProjectile(player.world, (LivingEntity)player);
      this.proj.setPosition(player.getPosX(), player.getPosY() + 10.0D, player.getPosZ());
      player.world.addEntity((Entity)this.proj);
    } 
    
    if (!this.proj.isAlive() || this.proj == null) {
      
      this.proj = null;
      endCharging(player);
    } 
    
    float time = getChargeTime() / getMaxChargeTime();
    float multiplier = 1.0F - time;
    this.proj.mult = this.maxMultiplier * multiplier;
    double distance = Math.sqrt(this.proj.getDistanceSq(mop.getHitVec()));
    if (mop.getType().equals(RayTraceResult.Type.BLOCK) && distance < 100.0D)
      this.proj.vector = mop.getHitVec().add(0.0D, 10.0D, 0.0D); 
  }
}


