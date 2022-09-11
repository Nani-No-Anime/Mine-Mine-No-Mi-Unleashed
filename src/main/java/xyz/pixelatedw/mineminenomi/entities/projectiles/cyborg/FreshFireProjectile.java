package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class FreshFireProjectile extends AbilityProjectileEntity {
  public FreshFireProjectile(World world) {
    super(CyborgProjectiles.FRESH_FIRE, world);
  }

  
  public FreshFireProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public FreshFireProjectile(World world, double x, double y, double z) {
    super(CyborgProjectiles.FRESH_FIRE, world, x, y, z);
  }

  
  public FreshFireProjectile(World world, LivingEntity player) {
    super(CyborgProjectiles.FRESH_FIRE, world, player);
    
    setDamage(1.0F);
    setPassThroughEntities();
    setMaxLife(15);
    setChangeHurtTime(true);
    setHurtTime(15);
    setDamageSource(DamageSource.IN_FIRE);

    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 10);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      hitEntity.setFire(4);
    }
  }
  
  private void onBlockImpactEvent(BlockPos hit) {
    AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), Blocks.FIRE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
  }

  
  private void onTickEvent() {
    for (int i = 0; i < 10; i++) {
      
      double offsetX = WyHelper.randomDouble() / 2.0D;
      double offsetY = WyHelper.randomDouble() / 2.0D;
      double offsetZ = WyHelper.randomDouble() / 2.0D;
      
      GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
      data.setLife(5);
      data.setSize(0.7F);
      WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
    } 
  }
}


