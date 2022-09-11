package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class AllBlockProtectionRule extends BlockProtectionRule {
  public static final AllBlockProtectionRule INSTANCE = new AllBlockProtectionRule();
  
  public AllBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    ForgeRegistries.BLOCKS.forEach(block -> addApprovedBlocks(new Block[] { block }));
  }
}


