package xyz.pixelatedw.mineminenomi.entities.projectiles.electro;
import java.lang.invoke.SerializedLambda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.electro.ElectricalLunaParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class ElectricalLunaProjectile extends AbilityProjectileEntity {
  private static final ParticleEffect PARTICLES = (ParticleEffect)new ElectricalLunaParticleEffect();

  
  public ElectricalLunaProjectile(World world) {
    super(ElectroProjectiles.ELECTRICAL_LUNA, world);
  }

  
  public ElectricalLunaProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public ElectricalLunaProjectile(World world, double x, double y, double z) {
    super(ElectroProjectiles.ELECTRICAL_LUNA, world, x, y, z);
  }

  
  public ElectricalLunaProjectile(World world, LivingEntity player) {
    super(ElectroProjectiles.ELECTRICAL_LUNA, world, player);
    
    setMaxLife(20);
    setPassThroughEntities();
    setDamageSource(ModDamageSource.LIGHTNING_BOLT.causeEntityDamageFromSource((Entity)player));
    
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onTickEvent() {
    if (this.ticksExisted < 0) {
      return;
    }
    PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D);
  }
}


