package xyz.pixelatedw.mineminenomi.abilities.suna;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class GrandeSablesAbility extends ContinuousAbility implements IMultiTargetAbility {
  public static final GrandeSablesAbility INSTANCE = new GrandeSablesAbility();
  
  private static final SablesParticleEffect PARTICLES = new SablesParticleEffect();
  
  private Vec3d look = null;

  
  public GrandeSablesAbility() {
    super("Grande Sables", AbilityHelper.getDevilFruitCategory());
    setDescription("Surrounds the user into a sand tornado, increasing their speed and pulling all nearby entities towards it.");
    setMaxCooldown(10.0D);
    setThreshold(2.5D);
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (i < 3) {
      return;
    }
    Vec3d speed = player.getLook(1.0F).mul(1.2D, 1.2D, 1.2D);
    player.setMotion(speed.x, speed.y, speed.z);
    
    PARTICLES.mult = 0.8F;
    PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), (player.getMotion()).x, (player.getMotion()).y, (player.getMotion()).z);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    
    float growthXZ = 8.0F;
    float growthY = 20.0F;
    
    AxisAlignedBB box = (new AxisAlignedBB(new BlockPos(player.getPositionVec()))).grow(growthXZ, growthY, growthXZ);
    for (Entity entity : player.world.getEntitiesWithinAABB(Entity.class, box, e -> (e != player))) {
      
      entity.setMotion((entity.getMotion()).x + (player.getPosX() - entity.getPosX()) / 20.0D, 
          (entity.getMotion()).y + (player.getPosY() - entity.getPosY()) / 20.0D, 
          (entity.getMotion()).z + (player.getPosZ() - entity.getPosZ()) / 20.0D);
      entity.velocityChanged = true;
      
      if (player.getDistance(entity) < 2.0F) {
        entity.attackEntityFrom(DamageSource.FLY_INTO_WALL, 8.0F);
      }
    } 
  }
  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (!AbilityHelper.canUseMomentumAbility(player) || player.isWet()) {
      return false;
    }
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
    clearTargets();
    
    Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
    player.setMotion(speed.x, 2.0D, speed.z);
    
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
    return true;
  }
}


