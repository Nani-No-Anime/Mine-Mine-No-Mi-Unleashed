package xyz.pixelatedw.mineminenomi.init;

import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.world.ChallengesModDimension;
import xyz.pixelatedw.mineminenomi.wypi.WyRegistry;

@EventBusSubscriber(modid = "mineminenomi", bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModDimensions
{
  public static final ModDimension CHALLENGES = (ModDimension)new ChallengesModDimension();

  
  static {
    WyRegistry.registerDimension(CHALLENGES, "Challenges");
  }
}


