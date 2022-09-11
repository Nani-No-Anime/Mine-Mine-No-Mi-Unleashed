package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class CoreBlockProtectionRule extends BlockProtectionRule {
  public static final CoreBlockProtectionRule INSTANCE = new CoreBlockProtectionRule();
  
  public CoreBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addApprovedMaterials(new Material[] { Material.ROCK, Material.IRON, Material.WOOD, Material.SNOW_BLOCK, Material.MISCELLANEOUS, Material.SAND, Material.GLASS, Material.ANVIL, Material.BAMBOO, Material.BAMBOO_SAPLING, Material.CACTUS, Material.CAKE, Material.CARPET, Material.CLAY, Material.CORAL, Material.EARTH, Material.GOURD, Material.PACKED_ICE, Material.ORGANIC, Material.WEB });

    
    addApprovedBlocks(new Block[] { ModBlocks.SUNA_SAND, ModBlocks.CANDY, ModBlocks.WAX, ModBlocks.POISON, ModBlocks.DEMON_POISON, Blocks.ICE, Blocks.BLUE_ICE, ModBlocks.SKY_BLOCK, ModBlocks.SPONGE_CAKE });
    addBannedBlocks(RestrictedBlockProtectionRule.INSTANCE.getBannedBlocks());
  }
}


