package xyz.pixelatedw.mineminenomi.abilities.bari;

import java.lang.invoke.SerializedLambda;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.block.AirBlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class BarrierBallAbility extends ContinuousAbility {
  public static final BarrierBallAbility INSTANCE = new BarrierBallAbility();
  
  private static final BlockProtectionRule GRIEF_RULE = new BlockProtectionRule(new BlockProtectionRule[] { (BlockProtectionRule)AirBlockProtectionRule.INSTANCE });
  private List<BlockPos> posList = new ArrayList<>();

  
  public BarrierBallAbility() {
    super("Barrier Ball", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(10.0D);
    setThreshold(15.0D);
    setDescription("The user creates a spherical barrier where they're pointing\n\n§2SHIFT-USE§r: Creates the barrier around the user");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (this.posList.isEmpty()) {
      
      RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player, 64.0D);
      World world = player.world;
      if (player.isSneaking()) {
        
        this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ(), 5, ModBlocks.BARRIER, GRIEF_RULE));
        this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ(), 6, ModBlocks.BARRIER, GRIEF_RULE));
      }
      else if (mop != null) {
        
        this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)(mop.getHitVec()).x, (int)(mop.getHitVec()).y, (int)(mop.getHitVec()).z, 5, ModBlocks.BARRIER, GRIEF_RULE));
        this.posList.addAll(AbilityHelper.createEmptySphere(world, (int)(mop.getHitVec()).x, (int)(mop.getHitVec()).y, (int)(mop.getHitVec()).z, 6, ModBlocks.BARRIER, GRIEF_RULE));
      } 
    } 
    
    return true;
  }

  
  private void onStopContinuityEvent(PlayerEntity player) {
    for (BlockPos pos : this.posList) {
      
      if (player.world.getBlockState(pos).getBlock() == ModBlocks.BARRIER)
        player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
    } 
    this.posList.clear();
  }
}


