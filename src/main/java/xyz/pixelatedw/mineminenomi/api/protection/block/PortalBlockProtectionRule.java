package xyz.pixelatedw.mineminenomi.api.protection.block;

import net.minecraft.block.material.Material;
import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;

public class PortalBlockProtectionRule
  extends BlockProtectionRule {
  public static final PortalBlockProtectionRule INSTANCE = new PortalBlockProtectionRule();
  
  public PortalBlockProtectionRule() {
    super(new BlockProtectionRule[0]);
    addApprovedMaterials(new Material[] { Material.PORTAL });
  }
}


