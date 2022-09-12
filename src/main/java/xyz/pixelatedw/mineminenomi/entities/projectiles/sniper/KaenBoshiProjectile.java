package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class KaenBoshiProjectile extends AbilityProjectileEntity {
  public KaenBoshiProjectile(World world) {
    super(SniperProjectiles.KAEN_BOSHI, world);
  }

  
  public KaenBoshiProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public KaenBoshiProjectile(World world, double x, double y, double z) {
    super(SniperProjectiles.KAEN_BOSHI, world, x, y, z);
  }

  
  public KaenBoshiProjectile(World world, LivingEntity player) {
    super(SniperProjectiles.KAEN_BOSHI, world, player);
    
    setDamage(8.0F);
    setPhysical(false);
    setAffectedByImbuing();
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    if (hitEntity instanceof CreeperEntity) {
      
      ((CreeperEntity)hitEntity).ignite();
      
      return;
    } 
    SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 10);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      hitEntity.setFire(10);
    }
  }
  
  private void onBlockImpactEvent(BlockPos hit) {
    if (this.world.getBlockState(hit).getBlock() == ModBlocks.OIL_SPILL) {
      
      ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
      explosion.setStaticDamage(10.0F);
      explosion.setFireAfterExplosion(true);
      explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
      
      explosion.doExplosion();
      
      return;
    } 
    AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), Blocks.FIRE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble() / 5.0D;
        double offsetY = WyHelper.randomDouble() / 5.0D;
        double offsetZ = WyHelper.randomDouble() / 5.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
        data.setLife(10);
        data.setSize(0.5F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + 0.2D + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


