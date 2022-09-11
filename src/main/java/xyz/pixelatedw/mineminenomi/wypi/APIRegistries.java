package xyz.pixelatedw.mineminenomi.wypi;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;



public class APIRegistries
{
  static {
    make(new ResourceLocation(APIConfig.projectId, "abilities"), Ability.class);
    make(new ResourceLocation(APIConfig.projectId, "quests"), Quest.class);
  }
  
  public static final IForgeRegistry<Ability> ABILITIES = RegistryManager.ACTIVE.getRegistry(Ability.class);
  public static final IForgeRegistry<Quest> QUESTS = RegistryManager.ACTIVE.getRegistry(Quest.class);

  
  public static <T extends net.minecraftforge.registries.IForgeRegistryEntry<T>> void make(ResourceLocation name, Class<T> type) {
    (new RegistryBuilder()).setName(name).setType(type).setMaxID(2147483646).create();
  }
}


