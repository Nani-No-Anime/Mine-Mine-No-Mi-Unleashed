package xyz.pixelatedw.mineminenomi.entities.projectiles.ito;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class OverheatProjectile extends AbilityProjectileEntity {
  public OverheatProjectile(World world) {
    super(ItoProjectiles.OVERHEAT, world);
  }

  
  public OverheatProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public OverheatProjectile(World world, double x, double y, double z) {
    super(ItoProjectiles.OVERHEAT, world, x, y, z);
  }

  
  public OverheatProjectile(World world, LivingEntity player) {
    super(ItoProjectiles.OVERHEAT, world, player);
    
    setDamage(60.0F);
    setMaxLife(50);
    setPassThroughEntities();
    setPhysical(false);
    setAffectedByImbuing();
    setCanGetStuckInGround();
    
    this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
    SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 10);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      hitEntity.setFire(10);
    }
  }
  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
    explosion.setStaticDamage(20.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(true);
    explosion.setSmokeParticles(null);
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }
}


