/*     */ package xyz.pixelatedw.mineminenomi.api.helpers;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockRayTraceResult;
/*     */ import net.minecraft.util.math.RayTraceContext;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import net.minecraft.world.storage.loot.LootContext;
/*     */ import net.minecraft.world.storage.loot.LootParameterSets;
/*     */ import net.minecraft.world.storage.loot.LootTable;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ 
/*     */ public class DevilFruitHelper {
/*  35 */   public static final ResourceLocation TIER_1_FRUITS = new ResourceLocation("mineminenomi", "dfboxes/wooden_box");
/*  36 */   public static final ResourceLocation TIER_2_FRUITS = new ResourceLocation("mineminenomi", "dfboxes/iron_box");
/*  37 */   public static final ResourceLocation TIER_3_FRUITS = new ResourceLocation("mineminenomi", "dfboxes/golden_box");
/*     */   
/*  39 */   private static final String[][] ZOAN_MODELS = new String[][] { { "ushi_ushi_bison", "bison" }, { "tori_tori_phoenix", "phoenix" }, { "ushi_ushi_giraffe", "giraffe" }, { "zou_zou_mammoth", "mammoth" }, { "hito_hito_daibutsu", "daibutsu" }, { "neko_neko_leopard", "leopard" }, { "ryu_ryu_pteranodon", "pteranodon" }, { "ryu_ryu_brachiosaurus", "brachiosaurus" }, { "sara_sara_axolotl", "axolotl" }, { "ryu_ryu_allosaurus", "allosaurus" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static AkumaNoMiItem oneFruitPerWorldCheck(World world, @Nullable Item devilFruitItem) {
/*  56 */     if (devilFruitItem == null || !(devilFruitItem instanceof AkumaNoMiItem)) {
/*  57 */       return null;
/*     */     }
/*  59 */     AkumaNoMiItem fruit = (AkumaNoMiItem)devilFruitItem;
/*     */     
/*  61 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*  62 */       return fruit;
/*     */     }
/*  64 */     ExtendedWorldData worldProps = ExtendedWorldData.get(world);
/*  65 */     int tier = fruit.getTier();
/*     */     
/*  67 */     boolean isAvailable = !worldProps.isDevilFruitInWorld(fruit);
/*     */     
/*  69 */     if (isAvailable) {
/*     */       
/*  71 */       worldProps.addDevilFruitInWorld(fruit);
/*  72 */       return fruit;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  78 */     List<AkumaNoMiItem> fruitsLeft = (List<AkumaNoMiItem>)WyHelper.shuffle(ModValues.devilfruits).stream().filter(f -> (f.getTier() == tier && !worldProps.isDevilFruitInWorld(f))).collect(Collectors.toList());
/*     */     
/*  80 */     if (fruitsLeft.size() > 0) {
/*     */       
/*  82 */       fruit = fruitsLeft.get(0);
/*  83 */       worldProps.addDevilFruitInWorld(fruit);
/*  84 */       return fruit;
/*     */     } 
/*     */ 
/*     */     
/*  88 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Item rouletteDevilFruits(World world, int tier) {
/*  94 */     Random rand = new Random();
/*     */     
/*  96 */     boolean hasUpgradeChance = (rand.nextInt(100) + rand.nextDouble() < 5.0D);
/*     */     
/*  98 */     ResourceLocation pool = TIER_1_FRUITS;
/*  99 */     if (tier == 1)
/*     */     {
/* 101 */       if (hasUpgradeChance) {
/* 102 */         pool = TIER_2_FRUITS;
/*     */       } else {
/* 104 */         pool = TIER_1_FRUITS;
/*     */       }  } 
/* 106 */     if (tier == 2) {
/*     */       
/* 108 */       if (hasUpgradeChance) {
/* 109 */         pool = TIER_3_FRUITS;
/*     */       } else {
/* 111 */         pool = TIER_2_FRUITS;
/*     */       } 
/* 113 */     } else if (tier == 3) {
/* 114 */       pool = TIER_3_FRUITS;
/*     */     } 
/* 116 */     LootTable lootTable = world.getServer().getLootTableManager().getLootTableFromLocation(pool);
/* 117 */     LootContext.Builder builder = new LootContext.Builder((ServerWorld)world);
/*     */     
/* 119 */     LootContext context = builder.build(LootParameterSets.EMPTY);
/* 120 */     List<ItemStack> stacks = lootTable.generate(context);
/*     */     
/* 122 */     if (stacks.isEmpty()) {
/* 123 */       return null;
/*     */     }
/* 125 */     return ((ItemStack)stacks.get(0)).getItem();
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getDevilFruitKey(AkumaNoMiItem fruit) {
/* 130 */     String key = "";
/*     */ 
/*     */     
/*     */     try {
/* 134 */       key = WyHelper.getResourceName(fruit.getDevilFruitName()).replace("_no_mi", "").replace(":", "").replace(".", "").replace(",", "").replace("model_", "");
/*     */     }
/* 136 */     catch (ClassCastException ex) {
/*     */       
/* 138 */       System.out.println("Item " + (new ItemStack((IItemProvider)fruit)).getDisplayName() + " could not be converted into a key, the resulted key was " + key + "");
/* 139 */       ex.printStackTrace();
/* 140 */       key = "ERROR";
/*     */     } 
/*     */     
/* 143 */     return key;
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack getDevilFruitItem(String key) {
/* 148 */     String model = "";
/* 149 */     String fullModel = "";
/*     */     
/* 151 */     for (String[] s : ZOAN_MODELS) {
/*     */       
/* 153 */       if (key.equals(s[0])) {
/*     */         
/* 155 */         model = s[1];
/* 156 */         fullModel = "_model_" + model;
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 161 */     if (key.equals("yamidummy")) {
/* 162 */       key = "yami_yami";
/*     */     }
/* 164 */     String finalName = (!WyHelper.isNullOrEmpty(model) ? key.replace("_" + model, "") : key) + "_no_mi" + fullModel;
/*     */     
/* 166 */     return new ItemStack((IItemProvider)ForgeRegistries.ITEMS.getValue(new ResourceLocation("mineminenomi", finalName)));
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean hasDFLimitInInventory(PlayerEntity player) {
/* 171 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/* 172 */       return false;
/*     */     }
/* 174 */     int inventoryDevilFruits = 0;
/* 175 */     ArrayList<ItemStack> slots = new ArrayList<>();
/* 176 */     slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
/* 177 */     slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
/* 178 */     for (ItemStack invStack : slots) {
/*     */       
/* 180 */       if (invStack != null && invStack.getItem() instanceof AkumaNoMiItem) {
/*     */         
/* 182 */         inventoryDevilFruits++;
/* 183 */         if (inventoryDevilFruits >= CommonConfig.INSTANCE.getInventoryLimitForFruits()) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */     } 
/* 188 */     if (inventoryDevilFruits >= CommonConfig.INSTANCE.getInventoryLimitForFruits()) {
/* 189 */       return true;
/*     */     }
/* 191 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean kairosekiChecks(LivingEntity entity) {
/* 195 */     if (entity instanceof PlayerEntity) {
/* 196 */       PlayerEntity playerEntity = (PlayerEntity)entity;
/* 197 */       return AbilityHelper.isNearbyKairoseki(playerEntity);
/*     */     } 
/* 199 */     return WyHelper.isBlockNearby(entity, 1, new Block[] { ModBlocks.KAIROSEKI, ModBlocks.KAIROSEKI_ORE, ModBlocks.KAIROSEKI_BARS });
/*     */   }
/*     */ 
/*     */   
/*     */   public static void vanillaFlightThreshold(LivingEntity entity, int height) {
/* 204 */     if (entity.getPosY() > height) {
/* 205 */       entity.setMotion(entity.getMotion()
/* 206 */           .add(0.0D, 0.25D * (height / 5.0F), 0.0D)
/* 207 */           .mul(1.0D, -0.15D, 1.0D));
/* 208 */       entity.velocityChanged = true;
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isFlyingAtMaxHeight(PlayerEntity player, double maxDifference) {
/* 213 */     return (maxDifference > getDifferenceToFloor(player));
/*     */   }
/*     */   
/*     */   public static double getDifferenceToFloor(PlayerEntity player) {
/* 217 */     Vec3d startVec = player.getPositionVec();
/* 218 */     Vec3d endVec = startVec.add(0.0D, -256.0D, 0.0D);
/* 219 */     BlockRayTraceResult blockRayTraceResult = player.world.rayTraceBlocks(new RayTraceContext(startVec, endVec, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.ANY, (Entity)player));
/* 220 */     return (startVec.subtract(blockRayTraceResult.getHitVec())).y;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\DevilFruitHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */