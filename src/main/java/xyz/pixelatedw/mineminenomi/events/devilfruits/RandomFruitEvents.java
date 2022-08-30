/*     */ package xyz.pixelatedw.mineminenomi.events.devilfruits;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.Items;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*     */ import net.minecraftforge.event.entity.player.ItemTooltipEvent;
/*     */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*     */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*     */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*     */ import xyz.pixelatedw.mineminenomi.api.DFEncyclopediaEntry;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetFruitSeedsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.randfruit.SSetRandomizedFruitsPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomFruitEvents
/*     */ {
/*     */   @EventBusSubscriber(modid = "mineminenomi", value = {Dist.CLIENT})
/*     */   public static class Client
/*     */   {
/*     */     public static boolean HAS_RANDOMIZED_FRUIT = false;
/*     */     public static boolean DIRTY = false;
/*  38 */     public static HashMap<Integer, Long> FRUIT_SEEDS = new HashMap<>();
/*     */   }
/*     */ 
/*     */   
/*     */   @EventBusSubscriber(modid = "mineminenomi")
/*     */   public static class Common
/*     */   {
/*     */     @SubscribeEvent
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public static void onTooltipRendered(ItemTooltipEvent event) {
/*  48 */       if (RandomFruitEvents.Client.HAS_RANDOMIZED_FRUIT && event.getItemStack() != null && event.getItemStack().getItem() instanceof AkumaNoMiItem) {
/*     */         
/*  50 */         ItemStack stack = event.getItemStack();
/*  51 */         boolean isFound = stack.getOrCreateTag().getBoolean("deciphered");
/*     */         
/*  53 */         event.getToolTip().clear();
/*  54 */         
/*  55 */         if (isFound) {
/*  56 */           ITextComponent iTextComponent = (new StringTextComponent("")).appendSibling(event.getItemStack().getDisplayName()).applyTextStyle((event.getItemStack().getRarity()).color);
                    event.getToolTip().add(iTextComponent);
/*     */         }else{
                    TranslationTextComponent translationTextComponent = new TranslationTextComponent(ModI18n.ITEM_GENERIC_FRUIT, new Object[0]);
                    event.getToolTip().add(translationTextComponent);
                }
/*  58 */         
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/*  65 */       if (CommonConfig.INSTANCE.getRandomizedFruits() && event.getEntity() instanceof PlayerEntity && !(event.getEntity()).world.isRemote) {
/*     */         
/*  67 */         PlayerEntity player = (PlayerEntity)event.getEntity();
/*     */         
/*  69 */         WyNetwork.sendTo(new SSetRandomizedFruitsPacket(CommonConfig.INSTANCE.getRandomizedFruits()), player);
/*     */ 
/*     */         
/*  72 */         HashMap<Integer, Long> seeds = new HashMap<>();
/*  73 */         for (AkumaNoMiItem fruit : ModValues.devilfruits) {
/*     */           
/*  75 */           int key = fruit.getTranslationKey().hashCode();
/*  76 */           long seed = player.world.getSeed() + key;
/*  77 */           seeds.put(Integer.valueOf(key), Long.valueOf(seed));
/*     */         } 
/*  79 */         WyNetwork.sendTo(new SSetFruitSeedsPacket(seeds), player);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @SubscribeEvent
/*     */     public static void onPlayerUsesItem(PlayerInteractEvent.RightClickItem event) {
/*  86 */       if (!(event.getEntityLiving() instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/*  89 */       PlayerEntity player = (PlayerEntity)event.getEntityLiving();
/*  90 */       ItemStack stack = event.getItemStack();
/*     */       
/*  92 */       if (stack.isEmpty() || stack.getItem() != Items.PAPER || stack.getTag() == null || stack.getTag().isEmpty() || player.world.isRemote) {
/*     */         return;
/*     */       }
/*  95 */       if (stack.getChildTag("fruitClues") == null) {
/*     */         return;
/*     */       }
/*  98 */       if (!CommonConfig.INSTANCE.getRandomizedFruits()) {
/*     */         
/* 100 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.SYSTEM_MESSAGE_RANDOMIZED_FRUITS, new Object[0]));
/*     */         
/*     */         return;
/*     */       } 
/* 104 */       CompoundNBT nbt = stack.getChildTag("fruitClues");
/* 105 */       String key = nbt.getString("key");
/* 106 */       DFEncyclopediaEntry entry = DFEncyclopediaEntry.of(nbt);
/* 107 */       ItemsHelper.updateEncyclopediae(player, key, entry);
/* 108 */       stack.shrink(1);
/* 109 */       ItemStack fruit = DevilFruitHelper.getDevilFruitItem(key);
/* 110 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_GAINED_FRUIT_CLUE, new Object[] { fruit.getDisplayName().getFormattedText() }));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\devilfruits\RandomFruitEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */