package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class SablesPesadoProjectile extends AbilityProjectileEntity {
  public SablesPesadoProjectile(World world) {
    super(SunaProjectiles.SABLES_PESADO, world);
  }

  
  public SablesPesadoProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public SablesPesadoProjectile(World world, double x, double y, double z) {
    super(SunaProjectiles.SABLES_PESADO, world, x, y, z);
  }

  
  public SablesPesadoProjectile(World world, LivingEntity player) {
    super(SunaProjectiles.SABLES_PESADO, world, player);
    
    setDamage(50.0F);
    setMaxLife(50);
    setPhysical(false);
    setAffectedByImbuing();
    setArmorPiercing();
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion(getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 6.0F);
    explosion.setStaticDamage(35.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(7));
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote) {
      int i;
      for (i = 0; i < 20; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA);
        data.setLife(6);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
      
      for (i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
        data.setLife(4);
        data.setSize(1.2F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    } 
  }
}


