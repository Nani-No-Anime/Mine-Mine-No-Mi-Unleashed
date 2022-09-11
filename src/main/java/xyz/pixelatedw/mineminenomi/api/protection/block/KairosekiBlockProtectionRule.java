package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.Block;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;

public class KairosekiBlockProtectionRule extends BlockProtectionRule {
  public static final KairosekiBlockProtectionRule INSTANCE = new KairosekiBlockProtectionRule();
  
  public KairosekiBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addBannedBlocks(new Block[] { ModBlocks.KAIROSEKI, ModBlocks.KAIROSEKI_ORE, ModBlocks.KAIROSEKI_BARS });
  }
}


