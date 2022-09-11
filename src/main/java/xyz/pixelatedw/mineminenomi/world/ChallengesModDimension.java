package xyz.pixelatedw.mineminenomi.world;

import java.util.function.BiFunction;
import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;



public class ChallengesModDimension
  extends ModDimension
{
  public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
    return ChallengesDimension::new;
  }
}


