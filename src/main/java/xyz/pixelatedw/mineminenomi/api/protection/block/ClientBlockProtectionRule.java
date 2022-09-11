package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class ClientBlockProtectionRule extends BlockProtectionRule {
  public static final ClientBlockProtectionRule INSTANCE = new ClientBlockProtectionRule();
  
  public ClientBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addApprovedBlocks(new Block[] { Blocks.BLUE_STAINED_GLASS, Blocks.RED_STAINED_GLASS });
  }
}


