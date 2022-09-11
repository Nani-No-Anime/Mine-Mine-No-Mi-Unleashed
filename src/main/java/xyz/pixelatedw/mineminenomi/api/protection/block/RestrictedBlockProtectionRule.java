package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class RestrictedBlockProtectionRule extends BlockProtectionRule {
  public static final RestrictedBlockProtectionRule INSTANCE = new RestrictedBlockProtectionRule();
  
  public RestrictedBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addBannedBlocks(new Block[] { Blocks.BEDROCK, ModBlocks.PONEGLYPH, ModBlocks.BARRIER, ModBlocks.OPE, ModBlocks.OPE_MID, ModBlocks.STRING_MID, ModBlocks.STRING_WALL, ModBlocks.DARKNESS, Blocks.BARRIER, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK });
  }
}


