package xyz.pixelatedw.mineminenomi.abilities.baku;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.api.protection.DefaultProtectionRules;
import xyz.pixelatedw.mineminenomi.particles.effects.baku.BakuMunchParticleEffect;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

public class BakuMunchAbility extends Ability {
  public static final BakuMunchAbility INSTANCE = new BakuMunchAbility();
  public static final BlockProtectionRule GRIEF_RULE = DefaultProtectionRules.CORE_FOLIAGE_ORE;
  
  private static final BakuMunchParticleEffect PARTICLES = new BakuMunchParticleEffect();

  
  public BakuMunchAbility() {
    super("Baku Munch", AbilityHelper.getDevilFruitCategory());
    setMaxCooldown(3.0D);
    setDescription("Allows the user to eat a big chunk of blocks in front of him, obtaining all of them as blocks in their inventory");
    
    this.onUseEvent = this::onUseEvent;
  }

  
  private boolean onUseEvent(PlayerEntity player) {
    BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)player, 16.0D);
    if (MathHelper.sqrt(player.getDistanceSq((blockRayTraceResult.getHitVec()).x, (blockRayTraceResult.getHitVec()).y, (blockRayTraceResult.getHitVec()).z)) < 5.0F)
    {
      for (int x = -2; x < 2; x++) {
        
        for (int y = 0; y < 3; y++) {
          
          for (int z = -2; z < 2; z++) {
            
            int posX = (int)(blockRayTraceResult.getHitVec()).x + x;
            int posY = (int)(blockRayTraceResult.getHitVec()).y - y;
            int posZ = (int)(blockRayTraceResult.getHitVec()).z + z;
            Block tempBlock = player.world.getBlockState(new BlockPos(posX, posY, posZ)).getBlock();
            if (AbilityHelper.placeBlockIfAllowed(player.world, posX, posY, posZ, Blocks.AIR, GRIEF_RULE)) {
              
              player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)tempBlock));
              PARTICLES.spawn(player.world, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
            } 
          } 
        } 
      } 
    }
    
    return true;
  }
}


