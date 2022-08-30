/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*     */ 
/*     */ public class AkumaNoMiBoxItem extends Item {
/*     */   private int tier;
/*     */   
/*     */   public AkumaNoMiBoxItem(int tier) {
/*  23 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(1));
/*  24 */     this.tier = tier;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getKeySlot(PlayerEntity player) {
/*  29 */     if (!player.inventory.hasItemStack(new ItemStack((IItemProvider)ModItems.KEY))) {
/*     */       
/*  31 */       player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.ITEM_MESSAGE_NEED_KEY, new Object[0]));
/*  32 */       return -1;
/*     */     } 
/*     */     
/*  35 */     for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
/*     */       
/*  37 */       ItemStack stack = player.inventory.getStackInSlot(i);
/*  38 */       if (stack != null && !stack.isEmpty() && stack.getItem() == ModItems.KEY) {
/*  39 */         return i;
/*     */       }
/*     */     } 
/*  42 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/*  48 */     if (!world.isRemote) {
/*     */       
/*  50 */       if (hand.equals(Hand.OFF_HAND)) {
/*  51 */         return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
/*     */       }
/*  53 */       int keySlot = getKeySlot(player);
/*     */       
/*  55 */       if (keySlot < 0) {
/*  56 */         return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
/*     */       }
/*  58 */       ItemStack itemStack = player.getHeldItemMainhand();
/*     */       
/*  60 */       player.inventory.decrStackSize(keySlot, 1);
/*  61 */       player.inventory.deleteStack(itemStack);
/*     */       
/*  63 */       Item randomFruit = DevilFruitHelper.rouletteDevilFruits(world, this.tier);
/*  64 */       randomFruit = DevilFruitHelper.oneFruitPerWorldCheck(world, randomFruit);
/*     */       
/*  66 */       if (randomFruit == null) {
/*     */         
/*  68 */         player.inventory.deleteStack(itemStack);
/*  69 */         return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */       } 
/*  71 */       if (randomFruit != null) {
/*     */         
/*  73 */         if (!(randomFruit instanceof AkumaNoMiItem)) {
/*     */           
/*  75 */           player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)randomFruit));
/*  76 */           return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */         } 
/*     */         
/*  79 */         if (DevilFruitHelper.hasDFLimitInInventory(player)) {
/*     */           
/*  81 */           player.dropItem(new ItemStack((IItemProvider)randomFruit), true);
/*  82 */           return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */         } 
/*     */ 
/*     */         
/*  86 */         player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)randomFruit));
/*  87 */         ExtendedWorldData worldProps = ExtendedWorldData.get(player.world);
/*  88 */         worldProps.addDevilFruitInInventory(player.getUniqueID(), DevilFruitHelper.getDevilFruitKey((AkumaNoMiItem)randomFruit));
/*  89 */         return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */       } 
/*     */     } 
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
/* 120 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   public int getTier() {
/* 125 */     return this.tier;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\AkumaNoMiBoxItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */