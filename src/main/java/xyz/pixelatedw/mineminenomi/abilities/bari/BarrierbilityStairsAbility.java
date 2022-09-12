package xyz.pixelatedw.mineminenomi.abilities.bari;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.bari.BarrierbilityStairsProjectile;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

import java.util.ArrayList;
import java.util.List;

public class BarrierbilityStairsAbility extends ContinuousAbility {
  public static final BarrierbilityStairsAbility INSTANCE = new BarrierbilityStairsAbility();
  
  private List<BlockPos> posList = new ArrayList<>();

  
  public BarrierbilityStairsAbility() {
    super("Barrierbility Stairs", AbilityHelper.getDevilFruitCategory());
    setDescription("By shaping the Barrier, the user can create stairs");
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    if (this.posList.isEmpty()) {
      
      BarrierbilityStairsProjectile proj = new BarrierbilityStairsProjectile(player.world, (LivingEntity)player);
      player.world.addEntity((Entity)proj);
      proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
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

  
  public void fillBlocksList(List<BlockPos> entityList) {
    this.posList.addAll(entityList);
  }
}


