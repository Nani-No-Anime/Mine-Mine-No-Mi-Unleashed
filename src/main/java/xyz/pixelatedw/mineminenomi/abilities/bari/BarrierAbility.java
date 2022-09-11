package xyz.pixelatedw.mineminenomi.abilities.bari;


import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.FoliageBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class BarrierAbility extends ContinuousAbility {
  public static final BarrierAbility INSTANCE = new BarrierAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = (new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE, (BlockProtectionRule)FoliageBlockProtectionRule.INSTANCE })).setBypassGriefRule();
  private List<BlockPos> posList = new ArrayList<>();

  
  public BarrierAbility() {
    super("Barrier", AbilityHelper.getDevilFruitCategory());
    setThreshold(30.0D);
    setDescription("The user creates an impenetrable wall in front of themselves, which shields them from attacks");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    Direction dir = Direction.getFacingDirections((Entity)player)[0];
    
    if (this.posList.isEmpty()) {
      
      if (dir == Direction.NORTH)
        this.posList.addAll(AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() - 4.0D, 3, 4, 1, ModBlocks.BARRIER, GRIEF_RULE)); 
      if (dir == Direction.SOUTH)
        this.posList.addAll(AbilityHelper.createFilledCube(player.world, player.getPosX(), player.getPosY(), player.getPosZ() + 4.0D, 3, 4, 1, ModBlocks.BARRIER, GRIEF_RULE)); 
      if (dir == Direction.EAST)
        this.posList.addAll(AbilityHelper.createFilledCube(player.world, player.getPosX() + 4.0D, player.getPosY(), player.getPosZ(), 1, 4, 3, ModBlocks.BARRIER, GRIEF_RULE)); 
      if (dir == Direction.WEST) {
        this.posList.addAll(AbilityHelper.createFilledCube(player.world, player.getPosX() - 4.0D, player.getPosY(), player.getPosZ(), 1, 4, 3, ModBlocks.BARRIER, GRIEF_RULE));
      }
    } 
    return true;
  }

  
  private boolean onStopContinuityEvent(PlayerEntity player) {
    for (BlockPos pos : this.posList) {
      
      Block currentBlock = player.world.getBlockState(pos).getBlock();
      if (currentBlock == ModBlocks.BARRIER)
        player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
    } 
    this.posList = new ArrayList<>();
    
    int cooldown = 1 + (int)Math.round(this.continueTime / 50.0D);
    setMaxCooldown(cooldown);
    
    return true;
  }
}


