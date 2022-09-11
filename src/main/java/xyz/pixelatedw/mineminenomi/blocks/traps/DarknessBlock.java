package xyz.pixelatedw.mineminenomi.blocks.traps;

import net.minecraft.block.Block;
import net.minecraft.potion.EffectInstance;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModEffects;
import xyz.pixelatedw.mineminenomi.init.ModMaterials;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;

public class DarknessBlock
  extends TrapAbilityBlock
{
  public DarknessBlock() {
    super(Block.Properties.create(ModMaterials.DARKNESS_MATERIAL).doesNotBlockMovement().hardnessAndResistance(-1.0F, 10000.0F).noDrops());
    setDamageDealt(6);
    setHorizontalFallSpeed(0.005D);
    setVerticalFallSpeed(0.3D);
    setPotionEffect(new EffectInstance(ModEffects.MOVEMENT_BLOCKED, 80, 0, false, false, false));
  }

  
  public AkumaNoMiItem getDevilFruit() {
    return ModAbilities.YAMI_YAMI_NO_MI;
  }
}


