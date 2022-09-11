package xyz.pixelatedw.mineminenomi.events.devilfruits;

import java.util.HashMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModValues;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetFruitSeedsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetRandomizedFruitsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;



public class RandomFruitEvents
{
  @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
  public static class Client
  {
    public static boolean HAS_RANDOMIZED_FRUIT = false;
    public static boolean DIRTY = false;
    public static HashMap<Integer, Long> FRUIT_SEEDS = new HashMap<>();
  }

  
  @EventBusSubscriber(modid = "mineminenomi")
  public static class Common
  {
    @SubscribeEvent
    @OnlyIn(Dist.CLIENT)
    public static void onTooltipRendered(ItemTooltipEvent event) {
      if (RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT && event.getItemStack() != null && event.getItemStack().getItem() instanceof AkumaNoMiItem) {
        
        ItemStack stack = event.getItemStack();
        boolean isFound = stack.getOrCreateTag().getBoolean("deciphered");
        
        event.getToolTip().clear();
        
        if (isFound) {
          ITextComponent iTextComponent = (new StringTextComponent("")).appendSibling(event.getItemStack().getDisplayName()).applyTextStyle((event.getItemStack().getRarity()).color);
                    event.getToolTip().add(iTextComponent);
        }else{
                    TranslationTextComponent translationTextComponent = new TranslationTextComponent(ModI18n.ITEM_GENERIC_FRUIT, new Object[0]);
                    event.getToolTip().add(translationTextComponent);
                }
        
      } 
    }

    
    @SubscribeEvent
    public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
      if (CommonConfig.INSTANCE.getRandomizedFruits() && event.getEntity() instanceof PlayerEntity && !(event.getEntity()).world.isRemote) {
        
        PlayerEntity player = (PlayerEntity)event.getEntity();
        
        WyNetwork.sendTo(new SSetRandomizedFruitsPacket(CommonConfig.INSTANCE.getRandomizedFruits()), player);

        
        HashMap<Integer, Long> seeds = new HashMap<>();
        for (AkumaNoMiItem fruit : ModValues.devilfruits) {
          
          int key = fruit.getTranslationKey().hashCode();
          long seed = player.world.getSeed() + key;
          seeds.put(Integer.valueOf(key), Long.valueOf(seed));
        } 
        WyNetwork.sendTo(new SSetFruitSeedsPacket(seeds), player);
      } 
    }

    
    @SubscribeEvent
    public static void onPlayerUsesItem(PlayerInteractEvent.RightClickItem event) {
      if (!(event.getEntityLiving() instanceof PlayerEntity)) {
        return;
      }
      PlayerEntity player = (PlayerEntity)event.getEntityLiving();
      ItemStack stack = event.getItemStack();
      
      if (stack.isEmpty() || stack.getItem() != Items.PAPER || stack.getTag() == null || stack.getTag().isEmpty() || player.world.isRemote) {
        return;
      }
      if (stack.getChildTag("fruitClues") == null) {
        return;
      }
      if (!CommonConfig.INSTANCE.getRandomizedFruits()) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS, new Object[0]));
        
        return;
      } 
      CompoundNBT nbt = stack.getChildTag("fruitClues");
      String key = nbt.getString("key");
      DFEncyclopediaEntry entry = DFEncyclopediaEntry.of(nbt);
      ItemsHelper.updateEncyclopediae(player, key, entry);
      stack.shrink(1);
      ItemStack fruit = DevilFruitHelper.getDevilFruitItem(key);
      player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_GAINED_FRUIT_CLUE, new Object[] { fruit.getDisplayName().getFormattedText() }));
    }
  }
}


