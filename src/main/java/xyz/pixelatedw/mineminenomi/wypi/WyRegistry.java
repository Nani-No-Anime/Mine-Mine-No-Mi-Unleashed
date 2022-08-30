/*     */ package xyz.pixelatedw.mineminenomi.wypi;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.enchantment.Enchantment;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.EntityClassification;
/*     */ import net.minecraft.entity.EntityType;
/*     */ import net.minecraft.inventory.container.ContainerType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemGroup;
/*     */ import net.minecraft.particles.ParticleType;
/*     */ import net.minecraft.potion.Effect;
/*     */ import net.minecraft.tileentity.TileEntityType;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.SoundEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.gen.feature.Feature;
/*     */ import net.minecraftforge.common.ModDimension;
/*     */ import net.minecraftforge.fml.RegistryObject;
/*     */ import net.minecraftforge.fml.network.FMLPlayMessages;
/*     */ import net.minecraftforge.registries.DeferredRegister;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.objectives.Objective;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WyRegistry
/*     */ {
/*  38 */   private static HashMap<String, String> langMap = new HashMap<>();
/*     */ 
/*     */   
/*     */   public static HashMap<String, String> getLangMap() {
/*  42 */     return langMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, APIConfig.projectId);
/*  49 */   public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, APIConfig.projectId);
/*  50 */   public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, APIConfig.projectId);
/*  51 */   public static final DeferredRegister<Ability> ABILITIES = DeferredRegister.create(APIRegistries.ABILITIES, APIConfig.projectId);
/*  52 */   public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, APIConfig.projectId);
/*  53 */   public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, APIConfig.projectId);
/*  54 */   public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, APIConfig.projectId);
/*  55 */   public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, APIConfig.projectId);
/*  56 */   public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, APIConfig.projectId);
/*  57 */   public static final DeferredRegister<Quest> QUESTS = DeferredRegister.create(APIRegistries.QUESTS, APIConfig.projectId);
/*  58 */   public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, APIConfig.projectId);
/*  59 */   public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, APIConfig.projectId);
/*  60 */   public static final DeferredRegister<ModDimension> DIMENSIONS = DeferredRegister.create(ForgeRegistries.MOD_DIMENSIONS, APIConfig.projectId);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String registerName(String key, String localizedName) {
/*  67 */     langMap.put(key, localizedName);
/*  68 */     return key;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerParticleType(ParticleType<?> type, String localizedName) {
/*  73 */     String resourceName = WyHelper.getResourceName(localizedName);
/*     */     
/*  75 */     PARTICLE_TYPES.register(resourceName, () -> type);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Feature<?> registerFeature(Feature<?> feature, String localizedName) {
/*  80 */     String resourceName = WyHelper.getResourceName(localizedName);
/*     */     
/*  82 */     FEATURES.register(resourceName, () -> feature);
/*     */     
/*  84 */     return feature;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Effect registerEffect(Effect effect, String localizedName) {
/*  89 */     String resourceName = WyHelper.getResourceName(localizedName);
/*  90 */     return registerEffect(effect, localizedName, resourceName);
/*     */   }
/*     */ 
/*     */   
/*     */   public static Effect registerEffect(Effect effect, String localizedName, String resourceKey) {
/*  95 */     String resourceName = WyHelper.getResourceName(resourceKey);
/*  96 */     langMap.put("effect." + APIConfig.projectId + "." + resourceName, localizedName);
/*     */     
/*  98 */     EFFECTS.register(resourceName, () -> effect);
/*     */     
/* 100 */     return effect;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Enchantment registerEnchantment(Enchantment enchantment, String localizedName) {
/* 105 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 106 */     langMap.put("enchantment." + APIConfig.projectId + "." + resourceName, localizedName);
/*     */     
/* 108 */     ENCHANTMENTS.register(resourceName, () -> enchantment);
/*     */     
/* 110 */     return enchantment;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Quest registerQuest(Quest quest) {
/* 115 */     String resourceName = WyHelper.getResourceName(quest.getId());
/* 116 */     langMap.put("quest." + APIConfig.projectId + "." + resourceName, quest.getTitle());
/*     */     
/* 118 */     for (Objective obj : quest.getObjectives())
/*     */     {
/* 120 */       langMap.put("quest.objective." + APIConfig.projectId + "." + obj.getId(), obj.getTitle());
/*     */     }
/*     */     
/* 123 */     QUESTS.register(resourceName, () -> quest);
/*     */     
/* 125 */     return quest;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Ability registerAbility(Ability ability) {
/* 130 */     String resourceName = WyHelper.getResourceName(ability.getName());
/* 131 */     langMap.put(ability.getI18nKey(), ability.getName());
/*     */     
/* 133 */     ResourceLocation key = new ResourceLocation(APIConfig.projectId, resourceName);
/* 134 */     RegistryObject<Ability> ret = RegistryObject.of(key, APIRegistries.ABILITIES);
/* 135 */     if (!ABILITIES.getEntries().contains(ret)) {
/* 136 */       ABILITIES.register(resourceName, () -> ability);
/*     */     }
/* 138 */     return ability;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Item registerItem(Item item, String localizedName) {
/* 143 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 144 */     langMap.put("item." + APIConfig.projectId + "." + resourceName, localizedName);
/*     */     
/* 146 */     ITEMS.register(resourceName, () -> item);
/*     */     
/* 148 */     return item;
/*     */   }
/*     */ 
/*     */   
/*     */   public static SoundEvent registerSound(String localizedName) {
/* 153 */     String resourceName = WyHelper.getResourceName(localizedName);
/*     */     
/* 155 */     SoundEvent sound = new SoundEvent(new ResourceLocation(APIConfig.projectId, resourceName));
/*     */     
/* 157 */     SOUNDS.register(resourceName, () -> sound);
/*     */     
/* 159 */     return sound;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Item registerSpawnEggItem(EntityType type, String localizedEntityName, int backgroundColor, int foregroundColor) {
/* 164 */     String entityResName = WyHelper.getResourceName(localizedEntityName);
/* 165 */     ModdedSpawnEggItem egg = new ModdedSpawnEggItem(() -> type, backgroundColor, foregroundColor, (new Item.Properties()).group(ItemGroup.MISC));
/*     */     
/* 167 */     String resourceName = entityResName + "_spawn_egg";
/* 168 */     String localizedName = "Spawn " + localizedEntityName;
/*     */     
/* 170 */     langMap.put("item." + APIConfig.projectId + "." + resourceName, localizedName);
/*     */     
/* 172 */     ITEMS.register(resourceName, () -> egg);
/*     */     
/* 174 */     return (Item)egg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Block registerBlock(Block block, String localizedName) {
/* 179 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 180 */     langMap.put("block." + APIConfig.projectId + "." + resourceName, localizedName);
/*     */     
/* 182 */     BLOCKS.register(resourceName, () -> block);
/*     */     
/* 184 */     return block;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends net.minecraft.tileentity.TileEntity> TileEntityType.Builder<T> createTileEntity(Supplier<T> factory, Block... blocks) {
/* 189 */     TileEntityType.Builder<T> type = TileEntityType.Builder.create(factory, blocks);
/*     */     
/* 191 */     return type;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends net.minecraft.tileentity.TileEntity> void registerTileEntity(TileEntityType<T> type, String localizedName) {
/* 196 */     String resourceName = WyHelper.getResourceName(localizedName);
/*     */     
/* 198 */     TILE_ENTITIES.register(resourceName, () -> type);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> EntityType.Builder<T> createEntityType(Function<World, T> func) {
/* 203 */     return createEntityType(func, EntityClassification.MISC);
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> EntityType.Builder<T> createEntityType(Function<World, T> func, EntityClassification classification) {
/* 208 */     EntityType.Builder<T> builder = EntityType.Builder.create((entityType, world) -> func.apply(world), classification);
/*     */     
/* 210 */     builder.setTrackingRange(128).setShouldReceiveVelocityUpdates(true).setUpdateInterval(1).setCustomClientFactory((entity, world) -> func.apply(world)).size(0.6F, 1.8F);
/*     */     
/* 212 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> EntityType.Builder<T> createFastEntityType(EntityType.IFactory<T> factory) {
/* 217 */     EntityType.Builder<T> builder = EntityType.Builder.create(factory, EntityClassification.MISC);
/*     */     
/* 219 */     builder.setTrackingRange(128).setShouldReceiveVelocityUpdates(true).setUpdateInterval(3).size(0.6F, 1.8F);
/*     */     
/* 221 */     return builder;
/*     */   }
/*     */ 
/*     */   
/*     */   public static <T extends Entity> void registerEntityType(EntityType<T> type, String localizedName) {
/* 226 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 227 */     langMap.put("entity." + APIConfig.projectId + "." + resourceName, localizedName);
/*     */     
/* 229 */     ENTITY_TYPES.register(resourceName, () -> type);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerContainerType(ContainerType<?> type, String localizedName) {
/* 234 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 235 */     langMap.put("container." + APIConfig.projectId + "." + resourceName, localizedName);
/*     */     
/* 237 */     CONTAINER_TYPES.register(resourceName, () -> type);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void registerDimension(ModDimension dimension, String localizedName) {
/* 242 */     String resourceName = WyHelper.getResourceName(localizedName);
/* 243 */     dimension.setRegistryName(ModResources.DIMENSION_TYPE_CHALLENGES);
/* 244 */     langMap.put("dimension." + APIConfig.projectId + "." + resourceName, localizedName);
/*     */     
/* 246 */     DIMENSIONS.register(resourceName, () -> dimension);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\WyRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */