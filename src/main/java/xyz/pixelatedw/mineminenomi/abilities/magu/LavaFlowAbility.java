package xyz.pixelatedw.mineminenomi.abilities.magu;

import java.lang.invoke.SerializedLambda;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.CoreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.OreBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class LavaFlowAbility extends ContinuousAbility {
  public static final Ability INSTANCE = (Ability)new LavaFlowAbility();
  public static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)CoreBlockProtectionRule.INSTANCE, (BlockProtectionRule)OreBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE });
  
  private int originY = -1;

  
  public LavaFlowAbility() {
    super("Lava Flow", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(15.0D);
    setThreshold(5.0D);
    setDescription("The user covers their legs into lava creating a path while walking trough it");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    this.originY = player.getPosition().getY() - 5;
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int i) {
    if (this.originY < 0) {
      return;
    }
    BlockPos pos = player.getPosition().down();
    BlockState state = player.world.getBlockState(pos);
    
    boolean isBlockBelowOrigin = (pos.getY() < this.originY);
    boolean areEyesInLava = player.areEyesInFluid(FluidTags.LAVA);
    boolean canPlaceBlocks = (GRIEF_RULE.check(player.world, pos, state) && !areEyesInLava);
    
    if (canPlaceBlocks) {
      AbilityHelper.createFilledSphere(player.world, (int)player.getPosX(), (int)player.getPosY() - 2, (int)player.getPosZ(), 3, Blocks.LAVA, GRIEF_RULE);
    } else if (areEyesInLava && !isBlockBelowOrigin) {
      AbilityHelper.createFilledSphere(player.world, (int)player.getPosX(), (int)player.getPosY() + 1, (int)player.getPosZ(), 3, Blocks.LAVA, GRIEF_RULE);
    } 
  }
}


