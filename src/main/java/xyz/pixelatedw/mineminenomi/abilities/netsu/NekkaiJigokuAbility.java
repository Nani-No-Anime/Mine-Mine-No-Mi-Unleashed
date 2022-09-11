package xyz.pixelatedw.mineminenomi.abilities.netsu;

import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class NekkaiJigokuAbility
  extends ContinuousAbility implements IParallelContinuousAbility {
  public static final NekkaiJigokuAbility INSTANCE = new NekkaiJigokuAbility();

  
  public NekkaiJigokuAbility() {
    super("Nekkai Jigoku", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setThreshold(30.0D);
    setDescription("Boils water around the user, damaging entities inside it");
    
    this.duringContinuityEvent = this::duringContinuity;
  }

  
  private void duringContinuity(PlayerEntity player, int i) {
    int radius = 30;
    
    List<LivingEntity> mobs = WyHelper.getEntitiesNear(player.getPosition(), player.world, radius);
    List<BlockPos> blocks = WyHelper.getNearbyBlocks((LivingEntity)player, radius);
    
    for (LivingEntity entity : mobs) {
      
      if (entity.isInWater())
      {
        entity.attackEntityFrom(DamageSource.ON_FIRE, 2.0F);
      }
    } 
    
    for (BlockPos blockPos : blocks) {
      
      BlockPos blockUp = new BlockPos(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ());
      if (player.world.getBlockState(blockPos).getBlock() == Blocks.WATER && player.world.getBlockState(blockUp).getBlock() == Blocks.AIR && i % 5 == 0)
      {
        WyHelper.spawnParticles(ParticleTypes.BUBBLE, (ServerWorld)player.world, blockPos.getX() + WyHelper.randomDouble() / 2.0D, blockPos.getY() + 0.8D, blockPos.getZ() + WyHelper.randomDouble() / 2.0D);
      }
    } 
  }
}


