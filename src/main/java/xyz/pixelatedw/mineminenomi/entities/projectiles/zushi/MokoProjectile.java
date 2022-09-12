package xyz.pixelatedw.mineminenomi.entities.projectiles.zushi;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class MokoProjectile extends AbilityProjectileEntity {
  public MokoProjectile(World world) {
    super(ZushiProjectiles.MOKO, world);
  }

  
  public MokoProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public MokoProjectile(World world, double x, double y, double z) {
    super(ZushiProjectiles.MOKO, world, x, y, z);
  }

  
  public MokoProjectile(World world, LivingEntity player) {
    super(ZushiProjectiles.MOKO, world, player);
    
    setDamage(10.0F);
    setPassThroughEntities();
    setChangeHurtTime(true);
    setDamageSource(this.bypassingSource);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpact;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onBlockImpact(BlockPos hit) {
    ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
    explosion.setStaticDamage(15.0F);
    explosion.setExplosionSound(true);
    explosion.setDamageOwner(false);
    explosion.setDestroyBlocks(true);
    explosion.setFireAfterExplosion(false);
    explosion.setExplosionSound(false);
    explosion.setSmokeParticles(null);
    explosion.setDamageEntities(true);
    explosion.doExplosion();
  }

  
  private void onEntityImpactEvent(LivingEntity target) {
    for (int x = -1; x < 1; x++) {
      
      for (int z = -1; z < 1; z++) {
        
        if (AbilityHelper.placeBlockIfAllowed(this.world, getPosX(), getPosY(), getPosZ(), Blocks.AIR, DefaultProtectionRules.CORE_FOLIAGE_ORE)) {
          
          target.setMotion(0.0D, -5.0D, 0.0D);
          target.velocityChanged = true;
          target.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 100, 10));
        } 
      } 
    } 
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 25; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU);
        data.setLife(10);
        data.setSize(1.0F);
        
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


