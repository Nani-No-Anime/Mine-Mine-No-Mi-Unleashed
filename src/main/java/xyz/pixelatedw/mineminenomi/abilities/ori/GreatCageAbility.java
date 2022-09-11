package xyz.pixelatedw.mineminenomi.abilities.ori;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.LiquidBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;

public class GreatCageAbility
  extends ContinuousAbility implements IParallelContinuousAbility {
  public static final GreatCageAbility INSTANCE = new GreatCageAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE, (BlockProtectionRule)LiquidBlockProtectionRule.INSTANCE })).setBypassGriefRule();
  protected List<BlockPos> blockList = new ArrayList<>();

  
  public GreatCageAbility() {
    super("Great Cage", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setDescription("Creates a big cage trapping the user and all nearby entities in it.");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (this.blockList.isEmpty()) {
      this.blockList.addAll(AbilityHelper.createEmptyCube(player.world, ((int)player.getPosX() - 1), (int)player.getPosY(), ((int)player.getPosZ() - 1), 20, 20, 20, ModBlocks.ORI_BARS, GRIEF_RULE));
    }
    return true;
  }

  
  private void onStopContinuityEvent(PlayerEntity player) {
    for (BlockPos pos : this.blockList) {
      
      Block currentBlock = player.world.getBlockState(pos).getBlock();
      if (currentBlock == ModBlocks.ORI_BARS)
        player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
    } 
    this.blockList.clear();
  }
}


