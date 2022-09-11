package xyz.pixelatedw.mineminenomi.init;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;

public class ModMaterials
{
  public static final Material DARKNESS_MATERIAL = (new Material.Builder(MaterialColor.BLACK)).build();
  public static final Material KAIROSEKI_MATERIAL = (new Material.Builder(MaterialColor.GRAY)).build();
  public static final Material HARDENED_SNOW_BLOCK = new Material(MaterialColor.SNOW, false, true, false, true, false, true, false, PushReaction.BLOCK);
}


