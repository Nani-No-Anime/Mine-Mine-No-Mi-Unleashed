package xyz.pixelatedw.mineminenomi.entities.projectiles.hana;
import com.google.common.collect.ImmutableList;

import java.util.Iterator;
import java.util.List;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.BlockParticleData;

import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.abilities.hana.CienFleurStompAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModDamageSource;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;

public class HanaFeetEntity extends AbilityProjectileEntity {
  public HanaFeetEntity(World world) {
    super(HanaProjectiles.FEET, world);
  }

  
  public HanaFeetEntity(World world, LivingEntity player) {
    super(HanaProjectiles.FEET, world, player);
    setMaxLife(35);
    setPassThroughEntities();

    
    this.onBlockImpactEvent = this::onBlockImpactEvent;
  }

  
  public void onBlockImpactEvent(BlockPos pos) {
    if (getThrower() == null) {
      return;
    }
    List<LivingEntity> list = WyHelper.getEntitiesNear(pos, this.world, 5.0D, FactionHelper.getOutsideGroupPredicate(getThrower()), new Class[] { LivingEntity.class });
    list.remove(getThrower());
    Iterator<LivingEntity> iter = list.iterator();
    
    while (iter.hasNext()) {
      
      LivingEntity target = iter.next();
      target.attackEntityFrom((DamageSource)ModDamageSource.causeAbilityDamage(getThrower(), (Ability)CienFleurStompAbility.INSTANCE), 10.0F);
      target.setMotion(target.getMotion().add(0.0D, 1.0D, 0.0D));
      target.velocityChanged = true;
    } 
    
    List<BlockPos> blocks = WyHelper.getNearbyBlocks(getPosition(), (IWorld)this.world, 5, p -> FoliageBlockProtectionRule.INSTANCE.isPresent(this.world.getBlockState(p)), (List)ImmutableList.of(Blocks.AIR));
    for (BlockPos p : blocks) {
      
      BlockState blockState1 = this.world.getBlockState(new BlockPos(p.getX(), p.getY(), p.getZ()));
      for (int i = 0; i < 150; i++) {
        
        double offsetX = WyHelper.randomDouble();
        double offsetY = WyHelper.randomDouble();
        double offsetZ = WyHelper.randomDouble();
        
        ((ServerWorld)this.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState1), p
            
            .getX() + offsetX, p
            .getY() + offsetY, p
            .getZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
      
      AbilityHelper.placeBlockIfAllowed(this.world, p.getX(), p.getY(), p.getZ(), Blocks.AIR, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE);
    } 
    
    BlockState blockState = this.world.getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ()));
    if (blockState.getMaterial().isSolid())
    {
      for (int i = 0; i < 150; i++) {
        
        double x = WyHelper.randomDouble();
        double z = WyHelper.randomDouble();
        
        ((ServerWorld)this.world).spawnParticle(new BlockParticleData(ParticleTypes.BLOCK, blockState), pos
            .getX() + WyHelper.randomWithRange(-3, 3) + x, (pos
            .getY() + 1), pos
            .getZ() + WyHelper.randomWithRange(-3, 3) + z, 1, 0.0D, 0.0D, 0.0D, 0.0D);
      } 
    }
  }
  
  public void onProjectileCollision(AbilityProjectileEntity owner, AbilityProjectileEntity target) {}
}


