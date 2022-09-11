package xyz.pixelatedw.mineminenomi.entities.projectiles.hie;

import java.lang.invoke.SerializedLambda;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class IceBlockPartisanProjectile extends AbilityProjectileEntity {
  public IceBlockPartisanProjectile(World world) {
    super(HieProjectiles.ICE_BLOCK_PARTISAN, world);
  }

  
  public IceBlockPartisanProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public IceBlockPartisanProjectile(World world, double x, double y, double z) {
    super(HieProjectiles.ICE_BLOCK_PARTISAN, world, x, y, z);
  }

  
  public IceBlockPartisanProjectile(World world, LivingEntity player) {
    super(HieProjectiles.ICE_BLOCK_PARTISAN, world, player);
    
    setDamage(9.0F);
    setMaxLife(40);
    setPhysical(false);
    setAffectedByImbuing();
    setChangeHurtTime(true);
    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }


  
  private void onEntityImpactEvent(LivingEntity entity) {
    AbilityHelper.addFrostbite(entity, getThrower(), 40);
  }

  
  private void onBlockImpactEvent(BlockPos hit) {
    AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), hit.getY(), hit.getZ(), Blocks.BLUE_ICE, DefaultProtectionRules.CORE_FOLIAGE_ORE_LIQUID);
  }

  
  private void onTickEvent() {
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 5; i++) {
        
        double offsetX = WyHelper.randomDouble() / 2.0D;
        double offsetY = WyHelper.randomDouble() / 2.0D;
        double offsetZ = WyHelper.randomDouble() / 2.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.HIE);
        data.setLife(2);
        data.setSize(1.5F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


