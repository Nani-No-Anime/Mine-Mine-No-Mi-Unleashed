package xyz.pixelatedw.mineminenomi.wypi;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.particles.ParticleType;
import net.minecraft.potion.Effect;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.Feature;
import net.minecraftforge.common.ModDimension;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
import xyz.pixelatedw.mineminenomi.init.ModResources;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;

import java.util.HashMap;
import java.util.function.Function;
import java.util.function.Supplier;






public class WyRegistry
{
  private static HashMap<String, String> langMap = new HashMap<>();

  
  public static HashMap<String, String> getLangMap() {
    return langMap;
  }



  
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, APIConfig.projectId);
  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, APIConfig.projectId);
  public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, APIConfig.projectId);
  public static final DeferredRegister<Ability> ABILITIES = DeferredRegister.create(APIRegistries.ABILITIES, APIConfig.projectId);
  public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, APIConfig.projectId);
  public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, APIConfig.projectId);
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, APIConfig.projectId);
  public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, APIConfig.projectId);
  public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, APIConfig.projectId);
  public static final DeferredRegister<Quest> QUESTS = DeferredRegister.create(APIRegistries.QUESTS, APIConfig.projectId);
  public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, APIConfig.projectId);
  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, APIConfig.projectId);
  public static final DeferredRegister<ModDimension> DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, APIConfig.projectId);




  
  public static String registerName(String key, String localizedName) {
    langMap.put(key, localizedName);
    return key;
  }

  
  public static void registerParticleType(ParticleType<?> type, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    
    PARTICLE_TYPES.register(resourceName, () -> type);
  }

  
  public static Feature<?> registerFeature(Feature<?> feature, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    
    FEATURES.register(resourceName, () -> feature);
    
    return feature;
  }

  
  public static Effect registerEffect(Effect effect, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    return registerEffect(effect, localizedName, resourceName);
  }

  
  public static Effect registerEffect(Effect effect, String localizedName, String resourceKey) {
    String resourceName = WyHelper.getResourceName(resourceKey);
    langMap.put("effect." + APIConfig.projectId + "." + resourceName, localizedName);
    
    EFFECTS.register(resourceName, () -> effect);
    
    return effect;
  }

  
  public static Enchantment registerEnchantment(Enchantment enchantment, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    langMap.put("enchantment." + APIConfig.projectId + "." + resourceName, localizedName);
    
    ENCHANTMENTS.register(resourceName, () -> enchantment);
    
    return enchantment;
  }

  
  public static Quest registerQuest(Quest quest) {
    String resourceName = WyHelper.getResourceName(quest.getId());
    langMap.put("quest." + APIConfig.projectId + "." + resourceName, quest.getTitle());
    
    for (Objective obj : quest.getObjectives())
    {
      langMap.put("quest.objective." + APIConfig.projectId + "." + obj.getId(), obj.getTitle());
    }
    
    QUESTS.register(resourceName, () -> quest);
    
    return quest;
  }

  
  public static Ability registerAbility(Ability ability) {
    String resourceName = WyHelper.getResourceName(ability.getName());
    langMap.put(ability.getI18nKey(), ability.getName());
    
    ResourceLocation key = new ResourceLocation(APIConfig.projectId, resourceName);
    RegistryObject<Ability> ret = RegistryObject.of(key, APIRegistries.ABILITIES);
    if (!ABILITIES.getEntries().contains(ret)) {
      ABILITIES.register(resourceName, () -> ability);
    }
    return ability;
  }

  
  public static Item registerItem(Item item, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    langMap.put("item." + APIConfig.projectId + "." + resourceName, localizedName);
    
    ITEMS.register(resourceName, () -> item);
    
    return item;
  }

  
  public static SoundEvent registerSound(String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    
    SoundEvent sound = new SoundEvent(new ResourceLocation(APIConfig.projectId, resourceName));
    
    SOUNDS.register(resourceName, () -> sound);
    
    return sound;
  }

  
  public static Item registerSpawnEggItem(EntityType type, String localizedEntityName, int backgroundColor, int foregroundColor) {
    String entityResName = WyHelper.getResourceName(localizedEntityName);
    ModdedSpawnEggItem egg = new ModdedSpawnEggItem(() -> type, backgroundColor, foregroundColor, (new Item.Properties()).group(ItemGroup.MISC));
    
    String resourceName = entityResName + "_spawn_egg";
    String localizedName = "Spawn " + localizedEntityName;
    
    langMap.put("item." + APIConfig.projectId + "." + resourceName, localizedName);
    
    ITEMS.register(resourceName, () -> egg);
    
    return (Item)egg;
  }

  
  public static Block registerBlock(Block block, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    langMap.put("block." + APIConfig.projectId + "." + resourceName, localizedName);
    
    BLOCKS.register(resourceName, () -> block);
    
    return block;
  }

  
  public static <T extends net.minecraft.tileentity.TileEntity> TileEntityType.Builder<T> createTileEntity(Supplier<T> factory, Block... blocks) {
    TileEntityType.Builder<T> type = TileEntityType.Builder.create(factory, blocks);
    
    return type;
  }

  
  public static <T extends net.minecraft.tileentity.TileEntity> void registerTileEntity(TileEntityType<T> type, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    
    TILE_ENTITIES.register(resourceName, () -> type);
  }

  
  public static <T extends Entity> EntityType.Builder<T> createEntityType(Function<World, T> func) {
    return createEntityType(func, EntityClassification.MISC);
  }

  
  public static <T extends Entity> EntityType.Builder<T> createEntityType(Function<World, T> func, EntityClassification classification) {
    EntityType.Builder<T> builder = EntityType.Builder.create((entityType, world) -> func.apply(world), classification);
    
    builder.setTrackingRange(128).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setCustomClientFactory((entity, world) -> func.apply(world)).size(0.6F, 1.8F);
    
    return builder;
  }

  
  public static <T extends Entity> EntityType.Builder<T> createFastEntityType(EntityType.IFactory<T> factory) {
    EntityType.Builder<T> builder = EntityType.Builder.create(factory, EntityClassification.MISC);
    
    builder.setTrackingRange(128).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).size(0.6F, 1.8F);
    
    return builder;
  }

  
  public static <T extends Entity> void registerEntityType(EntityType<T> type, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    langMap.put("entity." + APIConfig.projectId + "." + resourceName, localizedName);
    
    ENTITY_TYPES.register(resourceName, () -> type);
  }

  
  public static void registerContainerType(ContainerType<?> type, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    langMap.put("container." + APIConfig.projectId + "." + resourceName, localizedName);
    
    CONTAINER_TYPES.register(resourceName, () -> type);
  }

  
  public static void registerDimension(ModDimension dimension, String localizedName) {
    String resourceName = WyHelper.getResourceName(localizedName);
    dimension.setRegistryName(ModResources.DIMENSION_TYPE_CHALLENGES);
    langMap.put("dimension." + APIConfig.projectId + "." + resourceName, localizedName);
    
    DIMENSIONS.register(resourceName, () -> dimension);
  }
}


