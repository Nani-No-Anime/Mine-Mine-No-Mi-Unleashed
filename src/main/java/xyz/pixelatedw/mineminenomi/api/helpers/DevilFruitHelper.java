package xyz.pixelatedw.mineminenomi.api.helpers;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.loot.LootContext;
import net.minecraft.world.storage.loot.LootParameterSets;
import net.minecraft.world.storage.loot.LootTable;
import net.minecraftforge.registries.ForgeRegistries;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class DevilFruitHelper {
  public static final ResourceLocation TIER_1_FRUITS = new ResourceLocation("mineminenomi", "dfboxes/wooden_box");
  public static final ResourceLocation TIER_2_FRUITS = new ResourceLocation("mineminenomi", "dfboxes/iron_box");
  public static final ResourceLocation TIER_3_FRUITS = new ResourceLocation("mineminenomi", "dfboxes/golden_box");
  
  private static final String[][] ZOAN_MODELS = new String[][] { { "ushi_ushi_bison", "bison" }, { "tori_tori_phoenix", "phoenix" }, { "ushi_ushi_giraffe", "giraffe" }, { "zou_zou_mammoth", "mammoth" }, { "hito_hito_daibutsu", "daibutsu" }, { "neko_neko_leopard", "leopard" }, { "ryu_ryu_pteranodon", "pteranodon" }, { "ryu_ryu_brachiosaurus", "brachiosaurus" }, { "sara_sara_axolotl", "axolotl" }, { "ryu_ryu_allosaurus", "allosaurus" } };













  
  @Nullable
  public static AkumaNoMiItem oneFruitPerWorldCheck(World world, @Nullable Item devilFruitItem) {
    if (devilFruitItem == null || !(devilFruitItem instanceof AkumaNoMiItem)) {
      return null;
    }
    AkumaNoMiItem fruit = (AkumaNoMiItem)devilFruitItem;
    
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      return fruit;
    }
    ExtendedWorldData worldProps = ExtendedWorldData.get(world);
    int tier = fruit.getTier();
    
    boolean isAvailable = !worldProps.isDevilFruitInWorld(fruit);
    
    if (isAvailable) {
      
      worldProps.addDevilFruitInWorld(fruit);
      return fruit;
    } 



    
    List<AkumaNoMiItem> fruitsLeft = (List<AkumaNoMiItem>)WyHelper.shuffle(ModValues.devilfruits).stream().filter(f -> (f.getTier() == tier && !worldProps.isDevilFruitInWorld(f))).collect(Collectors.toList());
    
    if (fruitsLeft.size() > 0) {
      
      fruit = fruitsLeft.get(0);
      worldProps.addDevilFruitInWorld(fruit);
      return fruit;
    } 

    
    return null;
  }

  
  @Nullable
  public static Item rouletteDevilFruits(World world, int tier) {
    Random rand = new Random();
    
    boolean hasUpgradeChance = (rand.nextInt(100) + rand.nextDouble() < 5.0D);
    
    ResourceLocation pool = TIER_1_FRUITS;
    if (tier == 1)
    {
      if (hasUpgradeChance) {
        pool = TIER_2_FRUITS;
      } else {
        pool = TIER_1_FRUITS;
      }  } 
    if (tier == 2) {
      
      if (hasUpgradeChance) {
        pool = TIER_3_FRUITS;
      } else {
        pool = TIER_2_FRUITS;
      } 
    } else if (tier == 3) {
      pool = TIER_3_FRUITS;
    } 
    LootTable lootTable = world.getServer().getLootTableManager().getLootTableFromLocation(pool);
    LootContext.Builder builder = new LootContext.Builder((ServerWorld)world);
    
    LootContext context = builder.build(LootParameterSets.EMPTY);
    List<ItemStack> stacks = lootTable.generate(context);
    
    if (stacks.isEmpty()) {
      return null;
    }
    return ((ItemStack)stacks.get(0)).getItem();
  }

  
  public static String getDevilFruitKey(AkumaNoMiItem fruit) {
    String key = "";

    
    try {
      key = WyHelper.getResourceName(fruit.getDevilFruitName()).replace("_no_mi", "").replace(":", "").replace(".", "").replace(",", "").replace("model_", "");
    }
    catch (ClassCastException ex) {
      
      System.out.println("Item " + (new ItemStack((IItemProvider)fruit)).getDisplayName() + " could not be converted into a key, the resulted key was " + key + "");
      ex.printStackTrace();
      key = "ERROR";
    } 
    
    return key;
  }

  
  public static ItemStack getDevilFruitItem(String key) {
    String model = "";
    String fullModel = "";
    
    for (String[] s : ZOAN_MODELS) {
      
      if (key.equals(s[0])) {
        
        model = s[1];
        fullModel = "_model_" + model;
        
        break;
      } 
    } 
    if (key.equals("yamidummy")) {
      key = "yami_yami";
    }
    String finalName = (!WyHelper.isNullOrEmpty(model) ? key.replace("_" + model, "") : key) + "_no_mi" + fullModel;
    
    return new ItemStack((IItemProvider)ForgeRegistries.ITEMS.getValue(new ResourceLocation("mineminenomi", finalName)));
  }

  
  public static boolean hasDFLimitInInventory(PlayerEntity player) {
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
      return false;
    }
    int inventoryDevilFruits = 0;
    ArrayList<ItemStack> slots = new ArrayList<>();
    slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
    slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
    for (ItemStack invStack : slots) {
      
      if (invStack != null && invStack.getItem() instanceof AkumaNoMiItem) {
        
        inventoryDevilFruits++;
        if (inventoryDevilFruits >= CommonConfig.INSTANCE.getInventoryLimitForFruits()) {
          break;
        }
      } 
    } 
    if (inventoryDevilFruits >= CommonConfig.INSTANCE.getInventoryLimitForFruits()) {
      return true;
    }
    return false;
  }
  
  public static boolean kairosekiChecks(LivingEntity entity) {
    if (entity instanceof PlayerEntity) {
      PlayerEntity playerEntity = (PlayerEntity)entity;
      return AbilityHelper.isNearbyKairoseki(playerEntity);
    } 
    return WyHelper.isBlockNearby(entity, 1, new Block[] { ModBlocks.KAIROSEKI, ModBlocks.KAIROSEKI_ORE, ModBlocks.KAIROSEKI_BARS });
  }

  
  public static void vanillaFlightThreshold(LivingEntity entity, int height) {
    if (entity.getPosY() > height) {
      entity.setMotion(entity.getMotion()
          .add(0.0D, 0.25D * (height / 5.0F), 0.0D)
          .mul(1.0D, -0.15D, 1.0D));
      entity.velocityChanged = true;
    } 
  }
  
  public static boolean isFlyingAtMaxHeight(PlayerEntity player, double maxDifference) {
    return (maxDifference > getDifferenceToFloor(player));
  }
  
  public static double getDifferenceToFloor(PlayerEntity player) {
    Vec3d startVec = player.getPositionVec();
    Vec3d endVec = startVec.add(0.0D, -256.0D, 0.0D);
    BlockRayTraceResult blockRayTraceResult = player.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, (Entity)player));
    return (startVec.subtract(blockRayTraceResult.getHitVec())).y;
  }
}


