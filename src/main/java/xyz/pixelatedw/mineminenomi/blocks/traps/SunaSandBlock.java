package xyz.pixelatedw.mineminenomi.blocks.traps;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

public class SunaSandBlock
  extends TrapAbilityBlock
{
  public SunaSandBlock() {
    super(Block.Properties.create(Material.SAND).doesNotBlockMovement().hardnessAndResistance(5.0F, 3.0F).noDrops().harvestTool(ToolType.SHOVEL));
    setDamageDealt(4);
    setHorizontalFallSpeed(0.3D);
    setVerticalFallSpeed(0.15D);
    setPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 80, 1, false, false, false));
  }

  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.SUNA_SUNA_NO_MI;
  }
}


