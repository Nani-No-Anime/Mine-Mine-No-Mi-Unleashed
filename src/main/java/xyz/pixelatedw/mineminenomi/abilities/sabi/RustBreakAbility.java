package xyz.pixelatedw.mineminenomi.abilities.sabi;
import com.google.common.collect.ImmutableList;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IWorld;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
import xyz.pixelatedw.mineminenomi.init.ModTags;
import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.sabi.RustTouchParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class RustBreakAbility extends Ability {
  public static final RustBreakAbility INSTANCE = new RustBreakAbility();
  
  private static final ParticleEffect PARTICLES = (ParticleEffect)new RustTouchParticleEffect();

  
  public RustBreakAbility() {
    super("Rust Break", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(20.0D);
    setDescription("Rusts iron blocks");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
    
    if (player.getDistanceSq(mop.getHitVec()) > 50.0D) {
      return false;
    }
    BlockPos tracePos = new BlockPos(mop.getHitVec());
    List<BlockPos> rustyBlocks = WyHelper.getNearbyBlocks(tracePos, (IWorld)player.world, 1, p -> player.world.getBlockState(p).getBlock().isIn(ModTags.Blocks.RUSTY), (List)ImmutableList.of(Blocks.AIR));
    
    for (BlockPos pos : rustyBlocks) {
      
      for (int i = 0; i < 55; i++) {
        
        double offsetX = 0.5D + WyHelper.randomDouble() / 2.0D;
        double offsetY = 0.5D + WyHelper.randomDouble() / 2.0D;
        double offsetZ = 0.5D + WyHelper.randomDouble() / 2.0D;
        
        double motionX = this.random.nextGaussian() * 0.02D;
        double motionY = this.random.nextGaussian() * 0.02D;
        double motionZ = this.random.nextGaussian() * 0.02D;
        
        GenericParticleData data = new GenericParticleData(ModParticleTypes.RUST);
        data.setMotion(motionX, motionY, motionZ);
        data.setLife(30);
        data.setSize(4.0F);
        WyHelper.spawnParticles(data, (ServerWorld)player.world, pos.getX() + offsetX, pos.getY() + offsetY, pos.getZ() + offsetZ);
        
        ((ServerWorld)player.world).spawnParticle((IParticleData)ParticleTypes.POOF, pos.getX() + 0.5D, pos.getY(), pos.getZ() + 0.5D, 1, motionX, motionY, motionZ, 0.05000000074505806D);
      } 
      
      boolean isIngot = this.random.nextBoolean();
      for (int j = 0; j < this.random.nextInt(3); j++) {
        
        ItemStack stack = isIngot ? new ItemStack((IItemProvider)Items.IRON_INGOT) : new ItemStack((IItemProvider)Items.IRON_NUGGET);
        ItemEntity item = new ItemEntity(player.world, pos.getX(), pos.getY(), pos.getZ(), stack);
        player.world.addEntity((Entity)item);
      } 
      
      player.world.removeBlock(pos, false);
    } 
    
    return true;
  }
}


