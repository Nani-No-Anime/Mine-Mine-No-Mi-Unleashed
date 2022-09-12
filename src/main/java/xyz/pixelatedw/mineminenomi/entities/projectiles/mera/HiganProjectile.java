package xyz.pixelatedw.mineminenomi.entities.projectiles.mera;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ThrowableEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.events.SetOnFireEvent;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HiganProjectile extends AbilityProjectileEntity {
  public HiganProjectile(World world) {
    super(MeraProjectiles.HIGAN, world);
  }

  
  public HiganProjectile(EntityType type, World world) {
    super(type, world);
  }

  
  public HiganProjectile(World world, double x, double y, double z) {
    super(MeraProjectiles.HIGAN, world, x, y, z);
  }

  
  public HiganProjectile(World world, LivingEntity player) {
    super(MeraProjectiles.HIGAN, world, player);
    
    setDamage(10.0F);
    setDamageSource((DamageSource)ModDamageSource.FIRE.causeIndirectDamageFromSource((ThrowableEntity)this));
    setChangeHurtTime(true);
    
    this.onEntityImpactEvent = this::onEntityImpactEvent;
    this.onBlockImpactEvent = this::onBlockImpactEvent;
    this.onTickEvent = this::onTickEvent;
  }

  
  private void onEntityImpactEvent(LivingEntity hitEntity) {
    SetOnFireEvent event = new SetOnFireEvent(getThrower(), hitEntity, 4);
    if (!MinecraftForge.EVENT_BUS.post(event)) {
      hitEntity.setFire(4);
    }
  }
  
  private void onBlockImpactEvent(BlockPos hit) {
    BlockState state = this.world.getBlockState(hit);
    if (state.getBlock() == Blocks.CAMPFIRE) {
      this.world.setBlockState(hit, (BlockState)state.with((IProperty)BlockStateProperties.LIT, Boolean.valueOf(true)), 11);
    } else {
      AbilityHelper.placeBlockIfAllowed(this.world, hit.getX(), (hit.getY() + 1), hit.getZ(), Blocks.FIRE, (BlockProtectionRule)AirBlockProtectionRule.INSTANCE);
    } 
  }
  
  private void onTickEvent() {
    if (areEyesInFluid(FluidTags.WATER)) {
      
      remove();
      getEntityWorld().addParticle(ParticleTypes.SMOKE, getPosX(), getPosY() + 1.1D, getPosZ(), 0.0D, 0.0D, 0.0D);
    } 
    
    if (!this.world.isRemote)
    {
      for (int i = 0; i < 2; i++) {
        
        double offsetX = WyHelper.randomDouble() / 5.0D;
        double offsetY = WyHelper.randomDouble() / 5.0D;
        double offsetZ = WyHelper.randomDouble() / 5.0D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.MERA);
        data.setLife(10);
        data.setSize(1.3F);
        WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + 0.25D + offsetY, getPosZ() + offsetZ);
      } 
    }
  }
}


