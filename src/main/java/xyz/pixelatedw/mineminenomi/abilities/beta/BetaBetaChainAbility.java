package xyz.pixelatedw.mineminenomi.abilities.beta;
import java.awt.Color;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.beta.BetaBetaChainProjectile;
import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;

public class BetaBetaChainAbility extends PunchTriggerAbility {
  public static final BetaBetaChainAbility INSTANCE = new BetaBetaChainAbility();
  
  private LightningEntity bolt = null;

  
  public BetaBetaChainAbility() {
    super("Beta Beta Chain", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(6.0D);
    setThreshold(1.0D);
    setDescription("The user shoots a mucus chain which will propel the user towards where it hit, or grab an entity towards the user");
    
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    BlockRayTraceResult trace = WyHelper.rayTraceBlocks((Entity)player, 1.0D);
    if (this.bolt == null) {
      
      LightningEntity bolt = new LightningEntity((Entity)player, (trace.getHitVec()).x, (trace.getHitVec()).y - (player.getEyeHeight() / 3.0F), (trace.getHitVec()).z, player.rotationYaw, player.rotationPitch, 90.0F, 3.0F);
      
      bolt.setColor(new Color(55, 135, 0, 50));
      bolt.disableEnergyEffect();
      bolt.setAliveTicks(getThreshold() * 2);
      bolt.setDamage(0.0F);
      bolt.setSize(0.1F);
      bolt.setBranches(1);
      bolt.setSegments(1);
      bolt.disableLightningMimic();
      this.bolt = bolt;
      player.world.addEntity((Entity)bolt);
    }
    else {
      
      Direction dir = Direction.fromAngle(player.rotationYaw);
      Vec3d hitVec = trace.getHitVec().add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
      this.bolt.setLocationAndAngles(hitVec.x, hitVec.y, hitVec.z, player.rotationYaw, player.rotationPitch);
    } 
    
    AbilityHelper.slowEntityFall((LivingEntity)player, 2);
  }

  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    if (this.bolt != null) {
      
      this.bolt.remove();
      this.bolt = null;
    } 
    
    AbilityHelper.slowEntityFall((LivingEntity)player, 10);
    BetaBetaChainProjectile projectile = new BetaBetaChainProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)projectile);
    projectile.setMotion(0.0D, 0.0D, 0.0D);
    projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 0.0F);
    
    return true;
  }
}


