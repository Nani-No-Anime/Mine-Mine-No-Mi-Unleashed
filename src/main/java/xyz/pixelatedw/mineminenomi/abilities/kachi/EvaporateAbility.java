package xyz.pixelatedw.mineminenomi.abilities.kachi;
import java.lang.invoke.SerializedLambda;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.SnowLayerBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.kachi.EvaporateParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class EvaporateAbility extends ContinuousAbility {
  public static final EvaporateAbility INSTANCE = new EvaporateAbility();





  
  private static final BlockProtectionRule GRIEF_RULE;





  
  static {
    GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE, (BlockProtectionRule)SnowLayerBlockProtectionRule.INSTANCE })).addReplaceRules((world, pos, state) -> { if (state.has((IProperty)BlockStateProperties.WATERLOGGED)) { world.setBlockState(pos, (BlockState)state.with((IProperty)BlockStateProperties.WATERLOGGED, Boolean.valueOf(false))); return true; }  if (state.getMaterial() == Material.ICE || state.getMaterial() == Material.PACKED_ICE || state.getBlock() == Blocks.KELP || state.getBlock() == Blocks.SEAGRASS || state.getBlock() == Blocks.TALL_SEAGRASS) { world.setBlockState(pos, Blocks.WATER.getDefaultState()); return true; }  return false; }).addBannedBlocks(new Block[] { Blocks.LAVA });
  } private static final ParticleEffect PARTICLES = (ParticleEffect)new EvaporateParticleEffect();

  
  public EvaporateAbility() {
    super("Evaporate", AbilityHelper.getDevilFruitCategory());
    setDescription("Evaporates the water around the user");
    setMaxCooldown(12.0D);
    setThreshold(6.0D);
    
    this.duringContinuityEvent = this::duringContinuity;
  }

  
  private void duringContinuity(PlayerEntity player, int passiveTimer) {
    List<BlockPos> coords = AbilityHelper.createFilledSphere(player.getEntityWorld(), (int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ(), 6, Blocks.AIR, GRIEF_RULE);
    
    for (BlockPos pos : coords) {
      
      if (player.getEntityWorld().getBlockState(new BlockPos(pos.getX(), pos.getY(), pos.getZ())).getBlock() == Blocks.AIR)
        PARTICLES.spawn(player.world, pos.getX(), pos.getY(), pos.getZ(), 0.0D, 0.0D, 0.0D); 
    } 
  }
}


