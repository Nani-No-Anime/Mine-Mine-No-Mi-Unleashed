package xyz.pixelatedw.mineminenomi.abilities.ori;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.ori.AwaseBaoriProjectile;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;

public class AwaseBaoriAbility extends ContinuousAbility {
  public static final AwaseBaoriAbility INSTANCE = new AwaseBaoriAbility();
  
  private AwaseBaoriProjectile proj;
  private List<BlockPos> placedBlocks = new ArrayList<>();

  
  public AwaseBaoriAbility() {
    super("Awase Baori", AbilityHelper.getDevilFruitCategory());
    setDescription("Launches a short range projectile that creates a small cage around the hit target.");
    setMaxCooldown(10.0D);
    setThreshold(5.0D);
    
    this.onStartContinuityEvent = this::onStartContinuityEvent;
    this.duringContinuityEvent = this::duringContinuityEvent;
    this.onStopContinuityEvent = this::onStopContinuityEvent;
  }

  
  private boolean onStartContinuityEvent(PlayerEntity player) {
    this.proj = new AwaseBaoriProjectile(player.world, (LivingEntity)player);
    player.world.addEntity((Entity)this.proj);
    this.proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
    
    return true;
  }

  
  private void duringContinuityEvent(PlayerEntity player, int timer) {
    if (this.placedBlocks.isEmpty()) {
      
      if (this.proj != null && this.proj.getBlocks() != null) {
        
        this.placedBlocks = this.proj.getBlocks();
        
        return;
      } 
      if (this.proj == null || !this.proj.isAlive()) {
        
        endContinuity(player);
        return;
      } 
    } 
  }

  
  private void onStopContinuityEvent(PlayerEntity player) {
    for (BlockPos pos : this.placedBlocks) {
      
      if (player.world.getBlockState(pos).getBlock() == ModBlocks.ORI_BARS)
        player.world.setBlockState(pos, Blocks.AIR.getDefaultState()); 
    } 
    this.placedBlocks.clear();
  }
}


