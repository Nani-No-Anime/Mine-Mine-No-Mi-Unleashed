package xyz.pixelatedw.mineminenomi.entities.projectiles.beta;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class BetaBetaChainProjectile extends AbilityProjectileEntity {
  public BetaBetaChainProjectile(World world) {
    super(BetaProjectiles.BETA_BETA_CHAIN, world);
  }

  
  public BetaBetaChainProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public BetaBetaChainProjectile(World world, double x, double y, double z) {
    super(BetaProjectiles.BETA_BETA_CHAIN, world, x, y, z);
  }

  
  public BetaBetaChainProjectile(World world, LivingEntity player) {
    super(BetaProjectiles.BETA_BETA_CHAIN, world, player);
    
    setDamage(4.0F);
    setLife(30);
    setPhysical(true);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    if (getLife() >= getMaxLife()) {
      return;
    }
    PlayerEntity player = (PlayerEntity)getThrower();
    
    double powerX = Math.abs(hit.getX() - player.getPosX());
    double powerY = Math.min((hit.getY() - player.getPosY()) * 2.0D, 2.0D);
    double powerZ = Math.abs(hit.getZ() - player.getPosZ());
    
    Vec3d dirVec = player.getLookVec().mul(powerX, powerY, powerZ);
    player.setMotion(dirVec.x * 0.25D, 0.3D + dirVec.y, dirVec.z * 0.25D);
    ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
  }
}


