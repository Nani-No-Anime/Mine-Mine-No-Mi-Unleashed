package xyz.pixelatedw.mineminenomi.events.devilfruits;


import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.item.ItemExpireEvent;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerContainerEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.commons.lang3.tuple.Pair;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.entities.zoan.YomiZoanInfo;
import xyz.pixelatedw.mineminenomi.init.ModAbilities;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.*;





@EventBusSubscriber(modid = "mineminenomi")
public class OneFruitPerWorldEvents
{
  private static final Pair<ResourceLocation, ResourceLocation> OFFHAND_TEXTURE = Pair.of(PlayerContainer.LOCATION_BLOCKS_TEXTURE, PlayerContainer.EMPTY_ARMOR_SLOT_SHIELD);

  
  @SubscribeEvent
  public static void onRightClickEntity(PlayerInteractEvent.EntityInteractSpecific event) {
    if (!(event.getWorld()).isRemote && event.getTarget() instanceof net.minecraft.entity.item.ArmorStandEntity && event.getItemStack().getItem() instanceof AkumaNoMiItem && CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public static void onBreak(BlockEvent.BreakEvent event) {
    boolean hasShears = (event.getPlayer().getHeldItem(event.getPlayer().getActiveHand()).getItem() == Items.SHEARS);
    
    if (CommonConfig.INSTANCE.getDevilFruitDropsFromLeavesChance() > 0.0D && event.getState().getBlock() instanceof net.minecraft.block.LeavesBlock && !hasShears) {
      
      double chance = WyHelper.randomWithRange(0, 100) + WyHelper.randomDouble();
      
      if (chance < CommonConfig.INSTANCE.getDevilFruitDropsFromLeavesChance()) {
        
        AkumaNoMiItem df = ModValues.devilfruits.get((int)WyHelper.randomWithRange(0, ModValues.devilfruits.size() - 1));
        
        df = DevilFruitHelper.oneFruitPerWorldCheck((World)event.getWorld(), (Item)df);
        
        if (df != null) {
          
          event.getWorld().addEntity((Entity)new ItemEntity((World)event.getWorld(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ(), new ItemStack((IItemProvider)df)));
          ExtendedWorldData.get((event.getPlayer()).world).addDevilFruitInWorld(df);
        } 
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onExpire(ItemExpireEvent event) {
    if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic() && event.getEntityItem().getItem().getItem() instanceof AkumaNoMiItem) {
      
      ItemStack itemStack = event.getEntityItem().getItem();
      AkumaNoMiItem item = (AkumaNoMiItem)itemStack.getItem();
      ExtendedWorldData worldData = ExtendedWorldData.get((event.getEntityItem()).world);
      
      worldData.removeDevilFruitInWorld(item);
    } 
  }

  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void mouseClickEvent(GuiScreenEvent.MouseClickedEvent.Pre event) {
    if ((Minecraft.getInstance()).player == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
      return;
    }
    if (event.getGui() instanceof ContainerScreen) {
      
      Slot slotUnderMouse = ((ContainerScreen)event.getGui()).getSlotUnderMouse();
      if (slotUnderMouse != null && slotUnderMouse.getBackground() != null && slotUnderMouse.getBackground().equals(OFFHAND_TEXTURE)) {
        
        event.setCanceled(true);
        
        return;
      } 
    } 
    ItemStack cap = (Minecraft.getInstance()).player.inventory.getItemStack();
    if (!(cap.getItem() instanceof AkumaNoMiItem) || event.getGui() instanceof net.minecraft.client.gui.screen.inventory.CreativeScreen) {
      return;
    }
    if (!(event.getGui() instanceof net.minecraft.client.gui.screen.inventory.InventoryScreen)) {
      event.setCanceled(true);
    }
  }
  
  @OnlyIn(Dist.CLIENT)
  @SubscribeEvent
  public static void mouseReleaseEvent(GuiScreenEvent.MouseReleasedEvent.Pre event) {
    if ((Minecraft.getInstance()).player == null || !CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
      return;
    }
    if (event.getGui() instanceof ContainerScreen) {
      
      Slot slotUnderMouse = ((ContainerScreen)event.getGui()).getSlotUnderMouse();
      if (slotUnderMouse != null && slotUnderMouse.getBackground() != null && slotUnderMouse.getBackground().equals(OFFHAND_TEXTURE)) {
        
        event.setCanceled(true);
        
        return;
      } 
    } 
    ItemStack cap = (Minecraft.getInstance()).player.inventory.getItemStack();
    if (!(cap.getItem() instanceof AkumaNoMiItem) || event.getGui() instanceof net.minecraft.client.gui.screen.inventory.CreativeScreen) {
      return;
    }
    if (!(event.getGui() instanceof net.minecraft.client.gui.screen.inventory.InventoryScreen)) {
      event.setCanceled(true);
    }
  }
  
  @SubscribeEvent
  public static void onContainerOpen(PlayerContainerEvent.Open event) {
    if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic() && event.getContainer() instanceof net.minecraft.inventory.container.ShulkerBoxContainer) {
      
      PlayerEntity player = event.getPlayer();
      for (int i = 0; i < player.inventory.mainInventory.size(); i++) {
        
        ItemStack stack = (ItemStack)player.inventory.mainInventory.get(i);
        if (stack != null && stack.getItem() instanceof AkumaNoMiItem) {
          
          event.getPlayer().dropItem(stack.copy(), false);
          stack.shrink(stack.getCount());
        } 
      } 
      
      dropFruitsFromNearbyContainers(event.getPlayer());
    } 
  }

  
  @SubscribeEvent
  public static void onContainerClose(PlayerContainerEvent.Close event) {
    if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic() && !(event.getContainer() instanceof PlayerContainer)) {
      
      int containerSlots = (event.getContainer()).inventorySlots.size() - (event.getPlayer()).inventory.mainInventory.size();
      for (int i = 0; i < containerSlots; i++) {
        
        Slot slot = (event.getContainer()).inventorySlots.get(i);
        if (slot.getHasStack() && slot.getStack().getItem() instanceof AkumaNoMiItem) {
          
          event.getPlayer().dropItem(slot.getStack().copy(), false);
          slot.decrStackSize(1);
        } 
      } 
      
      dropFruitsFromNearbyContainers(event.getPlayer());
    } 
  }

  
  @SubscribeEvent
  public static void onRightClick(PlayerInteractEvent.EntityInteract event) {
    if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic() && event.getTarget() instanceof net.minecraft.entity.item.ItemFrameEntity && !event.getItemStack().isEmpty() && event.getItemStack().getItem() instanceof AkumaNoMiItem)
    {
      event.setCanceled(true);
    }
  }

  
  @SubscribeEvent
  public static void onPlayerDeath(LivingDeathEvent event) {
    if (event.getEntityLiving() instanceof PlayerEntity) {
      
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      IDevilFruit fruitProps = DevilFruitCapability.get((LivingEntity)player);
      ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
      
      if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
        
        if (!fruitProps.hasDevilFruit(ModAbilities.YOMI_YOMI_NO_MI) || YomiZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
          
          worldData.removeAteDevilFruit(player);
          worldData.removeDevilFruitInWorld(fruitProps.getDevilFruit());
        } 
        
        if (fruitProps.hasYamiPower()) {
          
          String yamiString = DevilFruitHelper.getDevilFruitKey(ModAbilities.YAMI_YAMI_NO_MI);
          worldData.removeDevilFruitInWorld(yamiString);
        } 
        
        ArrayList<ItemStack> slots = new ArrayList<>();
        slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
        slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
        for (ItemStack invStack : slots) {
          
          if (invStack != null && invStack.getItem() instanceof AkumaNoMiItem)
          {
            worldData.removeDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)invStack.getItem()));
          }
        } 
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
    if (event.getEntity() instanceof PlayerEntity && !(event.getWorld()).isRemote)
    {
      if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
        
        PlayerEntity player = (PlayerEntity)event.getEntity();
        ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
        
        if (worldProps != null) {
          
          checkForCachedInactivity(player.world, worldProps);
          boolean playerCheck = checkForInactivityAtLogin(player, worldProps);
          if (playerCheck) {
            
            worldProps.removeLoggedOutFruit(player.getUniqueID());
            player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_OFPW_INACTIVITY, new Object[0]));
          } 
        } 
        
        if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
          
          dropFruitsFromNearbyContainers((PlayerEntity)event.getEntity());
          
          boolean isDFUser = DevilFruitCapability.get((LivingEntity)player).hasDevilFruit();
          
          int fruitsFound = 0;
          ArrayList<ItemStack> slots = new ArrayList<>();
          slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
          slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
          for (ItemStack invStack : slots) {
            
            if (!invStack.isEmpty() && invStack.getItem() instanceof AkumaNoMiItem) {
              
              String fruitKey = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)invStack.getItem());

              
              if (isDFUser && CommonConfig.INSTANCE.getUnableToPickDFAsUser()) {
                
                worldProps.removeDevilFruitInInventory(player.getUniqueID(), fruitKey);
                worldProps.removeDevilFruitInWorld(fruitKey);
                invStack.shrink(1);
              } 

              
              for (String fruit : worldProps.getAteFruits().values()) {
                
                if (fruit.equalsIgnoreCase(fruitKey)) {
                  
                  System.out.println(player.getDisplayName().getFormattedText() + " had an already in use fruit, " + fruitKey + ", which was removed from their inventory!");
                  invStack.shrink(1);
                } 
              } 
              
              if (invStack.getCount() <= 0) {
                continue;
              }
              
              fruitsFound++;
              if (fruitsFound > CommonConfig.INSTANCE.getInventoryLimitForFruits()) {
                
                worldProps.removeDevilFruitInInventory(player.getUniqueID(), fruitKey);
                worldProps.removeDevilFruitInWorld(fruitKey);
                invStack.shrink(1);
              } 
            } 
          } 
        } 
        
        for (ItemStack invStack : player.inventory.mainInventory) {
          
          if (invStack != null && invStack.getItem() instanceof AkumaNoMiItem)
          {
            worldProps.addDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)invStack.getItem()));
          }
        } 
      } 
    }
  }

  
  @SubscribeEvent
  public static void onEntityLeavesWorld(PlayerEvent.PlayerLoggedOutEvent event) {
    if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      
      PlayerEntity player = event.getPlayer();
      IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)player);
      ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
      
      List<String> fruits = new ArrayList<>();
      
      if (dfProps.hasDevilFruit()) {
        fruits.add(dfProps.getDevilFruit());
      }
      if (dfProps.hasYamiPower()) {
        
        String yamiString = DevilFruitHelper.getDevilFruitKey(ModAbilities.YAMI_YAMI_NO_MI);
        fruits.add(yamiString);
      } 
      
      PlayerInventory inv = player.inventory;
      
      for (ItemStack stack : inv.mainInventory) {
        
        if (stack.getItem() instanceof AkumaNoMiItem) {
          
          String key = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)stack.getItem());
          fruits.add(key);
        } 
      } 
      
      if (!fruits.isEmpty()) {
        worldData.addLoggedOutFruit(player.getUniqueID(), fruits);
      }
    } 
    if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic())
    {
      dropFruitsFromNearbyContainers((PlayerEntity)event.getEntity());
    }
  }

  
  @SubscribeEvent
  public static void onItemPickedUp(EntityItemPickupEvent event) {
    if (CommonConfig.INSTANCE.hasOneFruitPerWorldExtendedLogic()) {
      
      PlayerEntity player = event.getPlayer();
      ItemStack stack = event.getItem().getItem();
      
      if (stack.getItem() == Items.SHULKER_BOX && stack.hasTag()) {
        
        ListNBT items = stack.getOrCreateTag().getCompound("BlockEntityTag").getList("Items", 10);
        for (int i = 0; i < items.size(); i++) {
          
          CompoundNBT itemNBT = items.getCompound(i);
          String itemId = itemNBT.getString("id");
          
          Item item = (Item)ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemId));
          if (item != null)
          {
            
            if (item instanceof AkumaNoMiItem)
              items.remove(i); 
          }
        } 
      } else if (stack.getItem() instanceof AkumaNoMiItem) {
        
        if (DevilFruitCapability.get((LivingEntity)player).hasDevilFruit() && CommonConfig.INSTANCE.getUnableToPickDFAsUser()) {
          
          event.setCanceled(true);
          
          return;
        } 
        if (DevilFruitHelper.hasDFLimitInInventory(player)) {
          event.setCanceled(true);
        } else {
          
          ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
          worldProps.addDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)stack.getItem()));
        } 
      } 
    } 
  }

  
  @SubscribeEvent
  public static void onDevilFruitDropped(ItemTossEvent event) {
    if (CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      
      PlayerEntity player = event.getPlayer();
      ItemStack stack = event.getEntityItem().getItem();
      ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
      
      if (stack.getItem() instanceof AkumaNoMiItem)
      {
        worldProps.removeDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)stack.getItem()));
      }
    } 
  }

  
  private static void dropFruitsFromNearbyContainers(PlayerEntity player) {
    List<BlockPos> blockPosList = WyHelper.getNearbyTileEntities((LivingEntity)player, 40);
    
    for (BlockPos pos : blockPosList) {
      
      TileEntity te = player.world.getTileEntity(pos);
      
      if (te instanceof IInventory)
      {
        for (int i = 0; i < ((IInventory)te).getSizeInventory(); i++) {
          
          ItemStack stack = ((IInventory)te).getStackInSlot(i);
          if (stack != null && stack.getItem() instanceof AkumaNoMiItem) {
            
            player.dropItem(stack.copy(), false);
            stack.shrink(stack.getCount());
          } 
        } 
      }
    } 
  }

  
  private static boolean checkForCachedInactivity(World world, ExtendedWorldData worldProps) {
    if (CommonConfig.INSTANCE.getDaysForInactivity() == 0) {
      return false;
    }
    for (Map.Entry<UUID, Pair<Date, List<String>>> entry : (Iterable<Map.Entry<UUID, Pair<Date, List<String>>>>)worldProps.getLoggedOutDevilFruits().entrySet()) {
      
      UUID playerUUID = entry.getKey();
      Pair<Date, List<String>> pair = entry.getValue();
      
      if (pair != null) {
        
        Date date = (Date)pair.getKey();
        long diff = WyHelper.getDaysSince(date);
        
        if (diff >= CommonConfig.INSTANCE.getDaysForInactivity()) {
          
          boolean flag = false;
          List<String> fruits = new ArrayList<>((Collection<? extends String>)pair.getValue());
          
          if (!fruits.isEmpty()) {
            
            for (Map.Entry<UUID, String> ateEntry : (Iterable<Map.Entry<UUID, String>>)worldProps.getAteFruits().entrySet()) {
              
              if (!((UUID)ateEntry.getKey()).equals(playerUUID)) {
                continue;
              }
              worldProps.removeDevilFruitInWorld(ateEntry.getValue());
              worldProps.removeAteDevilFruit(playerUUID);
              fruits.remove(ateEntry.getValue());
              flag = true;
            } 

            
            for (Map.Entry<UUID, List<String>> invEntry : (Iterable<Map.Entry<UUID, List<String>>>)worldProps.getFruitsInInventory().entrySet()) {
              
              if (!((UUID)invEntry.getKey()).equals(entry.getKey())) {
                continue;
              }
              worldProps.removeDevilFruitsInWorld(invEntry.getValue());
              worldProps.removeDevilFruitsInInventory(entry.getKey(), invEntry.getValue());
              fruits.removeAll(invEntry.getValue());
              flag = true;
            } 
          } 

          
          return flag;
        } 
      } 
    } 
    
    return false;
  }

  
  private static boolean checkForInactivityAtLogin(PlayerEntity player, ExtendedWorldData worldProps) {
    if (CommonConfig.INSTANCE.getDaysForInactivity() == 0) {
      return false;
    }
    Pair<Date, List<String>> pair = worldProps.getLoggedOutPlayer(player.getUniqueID());
    
    if (pair != null) {
      
      Date date = (Date)pair.getKey();
      long diff = WyHelper.getDaysSince(date);
      
      if (diff >= CommonConfig.INSTANCE.getDaysForInactivity()) {
        
        boolean flag = false;
        List<String> fruits = new ArrayList<>((Collection<? extends String>)pair.getValue());
        
        if (!fruits.isEmpty()) {
          
          IDevilFruit dfProps = DevilFruitCapability.get((LivingEntity)player);
          IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
          
          String yamiString = DevilFruitHelper.getDevilFruitKey(ModAbilities.YAMI_YAMI_NO_MI);
          String dfKey = dfProps.getDevilFruit();
          if (fruits.contains(dfKey)) {
            
            dfProps.setDevilFruit("");
            dfProps.setZoanPoint("");
            abilityProps.clearUnlockedAbilities(AbilityHelper.getDevilFruitCategory());
            abilityProps.clearEquippedAbilities(AbilityHelper.getDevilFruitCategory());
            WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), dfProps), player);
            worldProps.removeDevilFruitInWorld(dfKey);
            worldProps.removeAteDevilFruit(player);
            fruits.remove(dfKey);
            flag = true;
          } 
          
          if (fruits.contains(yamiString)) {
            
            dfProps.setYamiPower(false);
            abilityProps.clearUnlockedAbilities(AbilityHelper.getDevilFruitCategory());
            abilityProps.clearEquippedAbilities(AbilityHelper.getDevilFruitCategory());
            WyNetwork.sendTo(new SSyncDevilFruitPacket(player.getEntityId(), dfProps), player);
            worldProps.removeDevilFruitInWorld(yamiString);
            worldProps.removeAteDevilFruit(player);
            fruits.remove(yamiString);
            flag = true;
          } 
          
          ArrayList<ItemStack> slots = new ArrayList<>();
          slots.addAll((Collection<? extends ItemStack>)player.inventory.mainInventory);
          slots.addAll((Collection<? extends ItemStack>)player.inventory.offHandInventory);
          for (ItemStack stack : slots) {
            
            if (stack != null && stack.getItem() instanceof AkumaNoMiItem) {
              
              String key = DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)stack.getItem());
              
              if (fruits.contains(key)) {
                
                stack.shrink(1);
                worldProps.removeDevilFruitInWorld(key);
                worldProps.removeDevilFruitInInventory(player.getUniqueID(), key);
                fruits.remove(key);
                flag = true;
              } 
            } 
          } 
        } 
        
        return flag;
      } 

      
      worldProps.removeLoggedOutFruit(player.getUniqueID());
      return false;
    } 

    
    return false;
  }
}


