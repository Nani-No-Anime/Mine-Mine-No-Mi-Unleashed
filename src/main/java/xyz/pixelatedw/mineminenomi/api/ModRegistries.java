package xyz.pixelatedw.mineminenomi.api;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.wypi.APIRegistries;



import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
@EventBusSubscriber(modid = "mineminenomi")
public class ModRegistries
{
  static {
    APIRegistries.make(new ResourceLocation("mineminenomi", "jolly_roger_elements"), JollyRogerElement.class);
    APIRegistries.make(new ResourceLocation("mineminenomi", "challenges"), Challenge.class);
  }
  
  public static final IForgeRegistry<JollyRogerElement> JOLLY_ROGER_ELEMENTS = RegistryManager.ACTIVE.getRegistry(JollyRogerElement.class);
  public static final IForgeRegistry<Challenge> CHALLENGES = RegistryManager.ACTIVE.getRegistry(Challenge.class);
}


