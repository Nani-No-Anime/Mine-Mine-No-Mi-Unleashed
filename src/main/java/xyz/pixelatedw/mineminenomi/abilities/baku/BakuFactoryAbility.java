package xyz.pixelatedw.mineminenomi.abilities.baku;

import java.lang.invoke.SerializedLambda;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class BakuFactoryAbility
  extends ContinuousAbility {
  public static final BakuFactoryAbility INSTANCE = new BakuFactoryAbility();
  
  private BlockState previousBlock;

  
  public BakuFactoryAbility() {
    super("Baku Factory", AbilityHelper.getDevilFruitCategory());
    setDescription("Allows the user to craft items and blocks without the need for a Crafting Table.");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onEndContinuityEvent = this::onEndContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    BlockPos pos = player.getPosition();
    this.previousBlock = player.world.getBlockState(pos);
    player.world.setBlockState(pos, Blocks.CRAFTING_TABLE.getDefaultState());
    player.openContainer(player.world.getBlockState(pos).getContainer(player.world, pos));
    player.world.setBlockState(pos, this.previousBlock);
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int time) {
    if (!(player.openContainer instanceof net.minecraft.inventory.container.WorkbenchContainer)) {
      endContinuity(player);
    }
  }
  
  private boolean onEndContinuityEvent(PlayerEntity player) {
    return true;
  }
}


