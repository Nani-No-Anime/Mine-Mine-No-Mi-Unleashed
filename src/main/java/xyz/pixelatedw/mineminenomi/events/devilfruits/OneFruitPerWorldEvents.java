/*     */ package xyz.pixelatedw.mineminenomi.events.devilfruits;
/*     */ 

/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.client.gui.screen.inventory.ContainerScreen;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.item.ItemEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.PlayerInventory;
/*     */ import net.minecraft.inventory.IInventory;
/*     */ import net.minecraft.inventory.container.PlayerContainer;
/*     */ import net.minecraft.inventory.container.Slot;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.ListNBT;
/*     */ import net.minecraft.tileentity.TileEntity;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.client.event.GuiScreenEvent;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemExpireEvent;
/*     */ import net.minecraftforge.event.entity.item.ItemTossEvent;
/*     */ import net.minecraftforge.event.entity.living.LivingDeathEvent;
/*     */ import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerContainerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.event.world.BlockEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import net.minecraftforge.registries.ForgeRegistries;
/*     */ import org.apache.commons.lang3.tuple.Pair;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @EventBusSubscriber(modid = "mineminenomi")
/*     */ public class OneFruitPerWorldEvents
/*     */ {
/*  72 */   private static final Pair<ResourceLocation, ResourceLocation> OFFHAND_TEXTURE = Pair.of(PlayerContainer.LOCATION_BLOCKS_TEXTURE, PlayerContainer.EMPTY_ARMOR_SLOT_SHIELD);
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onRightClickEntity(PlayerInteractEvent.EntityInteractSpecific event) {
/*  77 */     if (!(event.getWorld()).isRemote && event.getTarget() instanceof net.minecraft.entity.item.ArmorStandEntity && event.getItemStack().getItem() instanceof AkumaNoMiItem && CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*  78 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onBreak(BlockEvent.BreakEvent event) {
/*  84 */     boolean hasShears = (event.getPlayer().getHeldItem(event.getPlayer().getActiveHand()).getItem() == Items.SHEARS);
/*     */     
/*  86 */     if (CommonConfig.INSTANCE.getDevilFruitDropsFromLeavesChance() > 0.0D && event.getState().getBlock() instanceof net.minecraft.block.LeavesBlock && !hasShears) {
/*     */       
/*  88 */       double chance = WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble();
/*     */       
/*  90 */       if (chance < CommonConfig.INSTANCE.getDevilFruitDropsFromLeavesChance()) {
/*     */         
/*  92 */         AkumaNoMiItem df = ModValues.devilfruits.get((int)WyHelper.randomWithRange(0, ModValues.devilfruits.size() - 1));
/*     */         
/*  94 */         df = DevilFruitHelper.oneFruitPerWorldCheck((World)event.getWorld(), (Item)df);
/*     */         
/*  96 */         if (df != null) {
/*     */           
/*  98 */           event.getWorld().addEntity((Entity)new ItemEntity((World)event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack((IItemProvider)df)));
/*  99 */           ExtendedWorldData.get((event.getPlayer()).world).addDevilFruitInWorld(df);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onExpire(ItemExpireEvent event) {
/* 108 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() && event.getEntityItem().getItem().getItem() instanceof AkumaNoMiItem) {
/*     */       
/* 110 */       ItemStack itemStack = event.getEntityItem().getItem();
/* 111 */       AkumaNoMiItem item = (AkumaNoMiItem)itemStack.getItem();
/* 112 */       ExtendedWorldData worldData = ExtendedWorldData.get((event.getEntityItem()).world);
/*     */       
/* 114 */       worldData.removeDevilFruitInWorld(item);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void mouseClickEvent(GuiScreenEvent.MouseClickedEvent.Pre event) {
/* 122 */     if ((Minecraft.getInstance()).player == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*     */       return;
/*     */     }
/* 125 */     if (event.getGui() instanceof ContainerScreen) {
/*     */       
/* 127 */       Slot slotUnderMouse = ((ContainerScreen)event.getGui()).getSlotUnderMouse();
/* 128 */       if (slotUnderMouse != null && slotUnderMouse.getBackground() != null && slotUnderMouse.getBackground().equals(OFFHAND_TEXTURE)) {
/*     */         
/* 130 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 135 */     ItemStack cap = (Minecraft.getInstance()).player.inventory.getItemStack();
/* 136 */     if (!(cap.getItem() instanceof AkumaNoMiItem) || event.getGui() instanceof net.minecraft.client.gui.screen.inventory.CreativeScreen) {
/*     */       return;
/*     */     }
/* 139 */     if (!(event.getGui() instanceof net.minecraft.client.gui.screen.inventory.InventoryScreen)) {
/* 140 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @OnlyIn(Dist.CLIENT)
/*     */   @SubscribeEvent
/*     */   public static void mouseReleaseEvent(GuiScreenEvent.MouseReleasedEvent.Pre event) {
/* 147 */     if ((Minecraft.getInstance()).player == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*     */       return;
/*     */     }
/* 150 */     if (event.getGui() instanceof ContainerScreen) {
/*     */       
/* 152 */       Slot slotUnderMouse = ((ContainerScreen)event.getGui()).getSlotUnderMouse();
/* 153 */       if (slotUnderMouse != null && slotUnderMouse.getBackground() != null && slotUnderMouse.getBackground().equals(OFFHAND_TEXTURE)) {
/*     */         
/* 155 */         event.setCanceled(true);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 160 */     ItemStack cap = (Minecraft.getInstance()).player.inventory.getItemStack();
/* 161 */     if (!(cap.getItem() instanceof AkumaNoMiItem) || event.getGui() instanceof net.minecraft.client.gui.screen.inventory.CreativeScreen) {
/*     */       return;
/*     */     }
/* 164 */     if (!(event.getGui() instanceof net.minecraft.client.gui.screen.inventory.InventoryScreen)) {
/* 165 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onContainerOpen(PlayerContainerEvent.Open event) {
/* 171 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic() && event.getContainer() instanceof net.minecraft.inventory.container.ShulkerBoxContainer) {
/*     */       
/* 173 */       PlayerEntity player = event.getPlayer();
/* 174 */       for (int i = 0; i < player.inventory.mainInventory.size(); i++) {
/*     */         
/* 176 */         ItemStack stack = (ItemStack)player.inventory.mainInventory.get(i);
/* 177 */         if (stack != null && stack.getItem() instanceof AkumaNoMiItem) {
/*     */           
/* 179 */           event.getPlayer().dropItem(stack.copy(), false);
/* 180 */           stack.shrink(stack.getCount());
/*     */         } 
/*     */       } 
/*     */       
/* 184 */       dropFruitsFromNearbyContainers(event.getPlayer());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onContainerClose(PlayerContainerEvent.Close event) {
/* 191 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic() && !(event.getContainer() instanceof PlayerContainer)) {
/*     */       
/* 193 */       int containerSlots = (event.getContainer()).inventorySlots.size() - (event.getPlayer()).inventory.mainInventory.size();
/* 194 */       for (int i = 0; i < containerSlots; i++) {
/*     */         
/* 196 */         Slot slot = (event.getContainer()).inventorySlots.get(i);
/* 197 */         if (slot.getHasStack() && slot.getStack().getItem() instanceof AkumaNoMiItem) {
/*     */           
/* 199 */           event.getPlayer().dropItem(slot.getStack().copy(), false);
/* 200 */           slot.decrStackSize(1);
/*     */         } 
/*     */       } 
/*     */       
/* 204 */       dropFruitsFromNearbyContainers(event.getPlayer());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onRightClick(PlayerInteractEvent.EntityInteract event) {
/* 211 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic() && event.getTarget() instanceof net.minecraft.entity.item.ItemFrameEntity && !event.getItemStack().isEmpty() && event.getItemStack().getItem() instanceof AkumaNoMiItem)
/*     */     {
/* 213 */       event.setCanceled(true);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onPlayerDeath(LivingDeathEvent event) {
/* 220 */     if (event.getEntityLiving() instanceof PlayerEntity) {
/*     */       
/* 222 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/* 223 */       IDevilFruit fruitProps = DevilFruitCapability.get((LivingEntity)player);
/* 224 */       ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/*     */       
/* 226 */       if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */         
/* 228 */         if (!fruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || YomiZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*     */           
/* 230 */           worldData.removeAteDevilFruit(player);
/* 231 */           worldData.removeDevilFruitInWorld(fruitProps.getDevilFruit());
/*     */         } 
/*     */         
/* 234 */         if (fruitProps.hasYamiPower()) {
/*     */           
/* 236 */           String yamiString = DevilFruitHelper.getDevilFruitKey(ModAbilities.YAMI_YAMI_NO_MI);
/* 237 */           worldData.removeDevilFruitInWorld(yamiString);
/*     */         } 
/*     */         
/* 240 */         ArrayList<ItemStack> slots = new ArrayList<>();
/* 241 */         slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
/* 242 */         slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
/* 243 */         for (ItemStack invStack : slots) {
/*     */           
/* 245 */           if (invStack != null && invStack.getItem() instanceof AkumaNoMiItem)
/*     */           {
/* 247 */             worldData.removeDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)invStack.getItem()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 257 */     if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).isRemote)
/*     */     {
/* 259 */       if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */         
/* 261 */         PlayerEntity player = (PlayerEntity)event.getEntity();
/* 262 */         ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
/*     */         
/* 264 */         if (worldProps != null) {
/*     */           
/* 266 */           checkForCachedInactivity(player.world, worldProps);
/* 267 */           boolean playerCheck = checkForInactivityAtLogin(player, worldProps);
/* 268 */           if (playerCheck) {
/*     */             
/* 270 */             worldProps.removeLoggedOutFruit(player.getUniqueID());
/* 271 */             player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_OFPW_INACTIVITY, new Object[0]));
/*     */           } 
/*     */         } 
/*     */         
/* 275 */         if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*     */           
/* 277 */           dropFruitsFromNearbyContainers((PlayerEntity)event.getEntity());
/*     */           
/* 279 */           boolean isDFUser = DevilFruitCapability.get((LivingEntity)player).hasDevilFruit();
/*     */           
/* 281 */           int fruitsFound = 0;
/* 282 */           ArrayList<ItemStack> slots = new ArrayList<>();
/* 283 */           slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
/* 284 */           slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
/* 285 */           for (ItemStack invStack : slots) {
/*     */             
/* 287 */             if (!invStack.isEmpty() && invStack.getItem() instanceof AkumaNoMiItem) {
/*     */               
/* 289 */               String fruitKey = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)invStack.getItem());
/*     */ 
/*     */               
/* 292 */               if (isDFUser && CommonConfig.INSTANCE.getUnableToPickDFAsUser()) {
/*     */                 
/* 294 */                 worldProps.removeDevilFruitInInventory(player.getUniqueID(), fruitKey);
/* 295 */                 worldProps.removeDevilFruitInWorld(fruitKey);
/* 296 */                 invStack.shrink(1);
/*     */               } 
/*     */ 
/*     */               
/* 300 */               for (String fruit : worldProps.getAteFruits().values()) {
/*     */                 
/* 302 */                 if (fruit.equalsIgnoreCase(fruitKey)) {
/*     */                   
/* 304 */                   System.out.println(player.getDisplayName().getFormattedText() + " had an already in use fruit, " + fruitKey + ", which was removed from their inventory!");
/* 305 */                   invStack.shrink(1);
/*     */                 } 
/*     */               } 
/*     */               
/* 309 */               if (invStack.getCount() <= 0) {
/*     */                 continue;
/*     */               }
/*     */               
/* 313 */               fruitsFound++;
/* 314 */               if (fruitsFound > CommonConfig.INSTANCE.getInventoryLimitForFruits()) {
/*     */                 
/* 316 */                 worldProps.removeDevilFruitInInventory(player.getUniqueID(), fruitKey);
/* 317 */                 worldProps.removeDevilFruitInWorld(fruitKey);
/* 318 */                 invStack.shrink(1);
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 324 */         for (ItemStack invStack : player.inventory.mainInventory) {
/*     */           
/* 326 */           if (invStack != null && invStack.getItem() instanceof AkumaNoMiItem)
/*     */           {
/* 328 */             worldProps.addDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)invStack.getItem()));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onEntityLeavesWorld(PlayerEvent.PlayerLoggedOutEvent event) {
/* 338 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       
/* 340 */       PlayerEntity player = event.getPlayer();
/* 341 */       IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)player);
/* 342 */       ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
/*     */       
/* 344 */       List<String> fruits = new ArrayList<>();
/*     */       
/* 346 */       if (dfProps.hasDevilFruit()) {
/* 347 */         fruits.add(dfProps.getDevilFruit());
/*     */       }
/* 349 */       if (dfProps.hasYamiPower()) {
/*     */         
/* 351 */         String yamiString = DevilFruitHelper.getDevilFruitKey(ModAbilities.YAMI_YAMI_NO_MI);
/* 352 */         fruits.add(yamiString);
/*     */       } 
/*     */       
/* 355 */       PlayerInventory inv = player.inventory;
/*     */       
/* 357 */       for (ItemStack stack : inv.mainInventory) {
/*     */         
/* 359 */         if (stack.getItem() instanceof AkumaNoMiItem) {
/*     */           
/* 361 */           String key = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)stack.getItem());
/* 362 */           fruits.add(key);
/*     */         } 
/*     */       } 
/*     */       
/* 366 */       if (!fruits.isEmpty()) {
/* 367 */         worldData.addLoggedOutFruit(player.getUniqueID(), fruits);
/*     */       }
/*     */     } 
/* 370 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic())
/*     */     {
/* 372 */       dropFruitsFromNearbyContainers((PlayerEntity)event.getEntity());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onItemPickedUp(EntityItemPickupEvent event) {
/* 379 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
/*     */       
/* 381 */       PlayerEntity player = event.getPlayer();
/* 382 */       ItemStack stack = event.getItem().getItem();
/*     */       
/* 384 */       if (stack.getItem() == Items.SHULKER_BOX && stack.hasTag()) {
/*     */         
/* 386 */         ListNBT items = stack.getOrCreateTag().getCompound("BlockEntityTag").getList("Items", 10);
/* 387 */         for (int i = 0; i < items.size(); i++) {
/*     */           
/* 389 */           CompoundNBT itemNBT = items.getCompound(i);
/* 390 */           String itemId = itemNBT.getString("id");
/*     */           
/* 392 */           Item item = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
/* 393 */           if (item != null)
/*     */           {
/*     */             
/* 396 */             if (item instanceof AkumaNoMiItem)
/* 397 */               items.remove(i); 
/*     */           }
/*     */         } 
/* 400 */       } else if (stack.getItem() instanceof AkumaNoMiItem) {
/*     */         
/* 402 */         if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit() && CommonConfig.INSTANCE.getUnableToPickDFAsUser()) {
/*     */           
/* 404 */           event.setCanceled(true);
/*     */           
/*     */           return;
/*     */         } 
/* 408 */         if (DevilFruitHelper.hasDFLimitInInventory(player)) {
/* 409 */           event.setCanceled(true);
/*     */         } else {
/*     */           
/* 412 */           ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
/* 413 */           worldProps.addDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)stack.getItem()));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @SubscribeEvent
/*     */   public static void onDevilFruitDropped(ItemTossEvent event) {
/* 422 */     if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*     */       
/* 424 */       PlayerEntity player = event.getPlayer();
/* 425 */       ItemStack stack = event.getEntityItem().getItem();
/* 426 */       ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
/*     */       
/* 428 */       if (stack.getItem() instanceof AkumaNoMiItem)
/*     */       {
/* 430 */         worldProps.removeDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)stack.getItem()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void dropFruitsFromNearbyContainers(PlayerEntity player) {
/* 437 */     List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities((LivingEntity)player, 40);
/*     */     
/* 439 */     for (BlockPos pos : blockPosList) {
/*     */       
/* 441 */       TileEntity te = player.world.getTileEntity(pos);
/*     */       
/* 443 */       if (te instanceof IInventory)
/*     */       {
/* 445 */         for (int i = 0; i < ((IInventory)te).getSizeInventory(); i++) {
/*     */           
/* 447 */           ItemStack stack = ((IInventory)te).getStackInSlot(i);
/* 448 */           if (stack != null && stack.getItem() instanceof AkumaNoMiItem) {
/*     */             
/* 450 */             player.dropItem(stack.copy(), false);
/* 451 */             stack.shrink(stack.getCount());
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean checkForCachedInactivity(World world, ExtendedWorldData worldProps) {
/* 460 */     if (CommonConfig.INSTANCE.getDaysForInactivity() == 0) {
/* 461 */       return false;
/*     */     }
/* 463 */     for (Map.Entry<UUID, Pair<Date, List<String>>> entry : (Iterable<Map.Entry<UUID, Pair<Date, List<String>>>>)worldProps.getLoggedOutDevilFruits().entrySet()) {
/*     */       
/* 465 */       UUID playerUUID = entry.getKey();
/* 466 */       Pair<Date, List<String>> pair = entry.getValue();
/*     */       
/* 468 */       if (pair != null) {
/*     */         
/* 470 */         Date date = (Date)pair.getKey();
/* 471 */         long diff = WyHelper.getDaysSince(date);
/*     */         
/* 473 */         if (diff >= CommonConfig.INSTANCE.getDaysForInactivity()) {
/*     */           
/* 475 */           boolean flag = false;
/* 476 */           List<String> fruits = new ArrayList<>((Collection<? extends String>)pair.getValue());
/*     */           
/* 478 */           if (!fruits.isEmpty()) {
/*     */             
/* 480 */             for (Map.Entry<UUID, String> ateEntry : (Iterable<Map.Entry<UUID, String>>)worldProps.getAteFruits().entrySet()) {
/*     */               
/* 482 */               if (!((UUID)ateEntry.getKey()).equals(playerUUID)) {
/*     */                 continue;
/*     */               }
/* 485 */               worldProps.removeDevilFruitInWorld(ateEntry.getValue());
/* 486 */               worldProps.removeAteDevilFruit(playerUUID);
/* 487 */               fruits.remove(ateEntry.getValue());
/* 488 */               flag = true;
/*     */             } 
/*     */ 
/*     */             
/* 492 */             for (Map.Entry<UUID, List<String>> invEntry : (Iterable<Map.Entry<UUID, List<String>>>)worldProps.getFruitsInInventory().entrySet()) {
/*     */               
/* 494 */               if (!((UUID)invEntry.getKey()).equals(entry.getKey())) {
/*     */                 continue;
/*     */               }
/* 497 */               worldProps.removeDevilFruitsInWorld(invEntry.getValue());
/* 498 */               worldProps.removeDevilFruitsInInventory(entry.getKey(), invEntry.getValue());
/* 499 */               fruits.removeAll(invEntry.getValue());
/* 500 */               flag = true;
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 505 */           return flag;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 510 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean checkForInactivityAtLogin(PlayerEntity player, ExtendedWorldData worldProps) {
/* 515 */     if (CommonConfig.INSTANCE.getDaysForInactivity() == 0) {
/* 516 */       return false;
/*     */     }
/* 518 */     Pair<Date, List<String>> pair = worldProps.getLoggedOutPlayer(player.getUniqueID());
/*     */     
/* 520 */     if (pair != null) {
/*     */       
/* 522 */       Date date = (Date)pair.getKey();
/* 523 */       long diff = WyHelper.getDaysSince(date);
/*     */       
/* 525 */       if (diff >= CommonConfig.INSTANCE.getDaysForInactivity()) {
/*     */         
/* 527 */         boolean flag = false;
/* 528 */         List<String> fruits = new ArrayList<>((Collection<? extends String>)pair.getValue());
/*     */         
/* 530 */         if (!fruits.isEmpty()) {
/*     */           
/* 532 */           IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)player);
/* 533 */           IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */           
/* 535 */           String yamiString = DevilFruitHelper.getDevilFruitKey(ModAbilities.YAMI_YAMI_NO_MI);
/* 536 */           String dfKey = dfProps.getDevilFruit();
/* 537 */           if (fruits.contains(dfKey)) {
/*     */             
/* 539 */             dfProps.setDevilFruit("");
/* 540 */             dfProps.setZoanPoint("");
/* 541 */             abilityProps.clearUnlockedAbilities(AbilityHelper.getDevilFruitCategory());
/* 542 */             abilityProps.clearEquippedAbilities(AbilityHelper.getDevilFruitCategory());
/* 543 */             WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), dfProps), player);
/* 544 */             worldProps.removeDevilFruitInWorld(dfKey);
/* 545 */             worldProps.removeAteDevilFruit(player);
/* 546 */             fruits.remove(dfKey);
/* 547 */             flag = true;
/*     */           } 
/*     */           
/* 550 */           if (fruits.contains(yamiString)) {
/*     */             
/* 552 */             dfProps.setYamiPower(false);
/* 553 */             abilityProps.clearUnlockedAbilities(AbilityHelper.getDevilFruitCategory());
/* 554 */             abilityProps.clearEquippedAbilities(AbilityHelper.getDevilFruitCategory());
/* 555 */             WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), dfProps), player);
/* 556 */             worldProps.removeDevilFruitInWorld(yamiString);
/* 557 */             worldProps.removeAteDevilFruit(player);
/* 558 */             fruits.remove(yamiString);
/* 559 */             flag = true;
/*     */           } 
/*     */           
/* 562 */           ArrayList<ItemStack> slots = new ArrayList<>();
/* 563 */           slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
/* 564 */           slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
/* 565 */           for (ItemStack stack : slots) {
/*     */             
/* 567 */             if (stack != null && stack.getItem() instanceof AkumaNoMiItem) {
/*     */               
/* 569 */               String key = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)stack.getItem());
/*     */               
/* 571 */               if (fruits.contains(key)) {
/*     */                 
/* 573 */                 stack.shrink(1);
/* 574 */                 worldProps.removeDevilFruitInWorld(key);
/* 575 */                 worldProps.removeDevilFruitInInventory(player.getUniqueID(), key);
/* 576 */                 fruits.remove(key);
/* 577 */                 flag = true;
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 583 */         return flag;
/*     */       } 
/*     */ 
/*     */       
/* 587 */       worldProps.removeLoggedOutFruit(player.getUniqueID());
/* 588 */       return false;
/*     */     } 
/*     */ 
/*     */     
/* 592 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\devilfruits\OneFruitPerWorldEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */