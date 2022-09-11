package xyz.pixelatedw.mineminenomi.abilities.yami;


import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.entities.projectiles.yami.LiberationProjectile;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class LiberationAbility extends Ability {
  public static final LiberationAbility INSTANCE = new LiberationAbility();
  
  private int liberationCount = 0;

  
  public LiberationAbility() {
    super("Liberation", AbilityHelper.getDevilFruitCategory());
    setDescription("The user sucks up any Darkness they placed, then expells everything sucked up at the target location");
    setMaxCooldown(4.0D);
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    if (this.liberationCount > 0) {
      
      RayTraceResult mop = WyHelper.rayTraceBlocksAndEntities((Entity)player);
      
      double x = (mop.getHitVec()).x;
      double y = (mop.getHitVec()).y;
      double z = (mop.getHitVec()).z;
      
      while (this.liberationCount > 0)
      {
        LiberationProjectile proj = new LiberationProjectile(player.world, (LivingEntity)player);
        proj.setLocationAndAngles(x + WyHelper.randomWithRange(-3, 3), y + 14.0D + WyHelper.randomWithRange(0, 4), z + WyHelper.randomWithRange(-3, 3), 0.0F, 0.0F);
        proj.setMotion(0.0D, -0.7D - player.world.rand.nextDouble(), 0.0D);
        player.world.addEntity((Entity)proj);
        this.liberationCount--;
      }
    
    } else {
      
      int libCount = 0;
      
      for (int x = -40; x < 40; x++) {
        
        for (int y = -40; y < 40; y++) {
          
          for (int z = -40; z < 40; z++) {
            
            BlockPos pos = new BlockPos((int)player.getPosX() + x, (int)player.getPosY() + y, (int)player.getPosZ() + z);
            if (player.world.getBlockState(pos).getBlock() == ModBlocks.DARKNESS) {
              
              player.world.setBlockState(pos, Blocks.AIR.getDefaultState());
              libCount++;
              if (libCount % 10 == 0) {
                this.liberationCount++;
              }
            } 
          } 
        } 
      } 
    } 
    return true;
  }
}


