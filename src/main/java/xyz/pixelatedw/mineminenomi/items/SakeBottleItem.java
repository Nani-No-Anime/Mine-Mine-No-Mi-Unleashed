/*     */ package xyz.pixelatedw.mineminenomi.items;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.inventory.EquipmentSlotType;
/*     */ import net.minecraft.item.Item;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.item.UseAction;
/*     */ import net.minecraft.potion.EffectInstance;
/*     */ import net.minecraft.util.ActionResult;
/*     */ import net.minecraft.util.ActionResultType;
/*     */ import net.minecraft.util.Hand;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.TranslationTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModFoods;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenNewCrewScreenPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class SakeBottleItem
/*     */   extends Item {
/*     */   public SakeBottleItem() {
/*  30 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).defaultMaxDamage(5).food(ModFoods.ALCOHOL));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/*  36 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*  37 */     if (world.isRemote) {
/*  38 */       return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
/*     */     }
/*  40 */     if (player.isSneaking() && props.isPirate()) {
/*     */       
/*  42 */       ExtendedWorldData worldProps = ExtendedWorldData.get(world);
/*  43 */       boolean isInCrew = (worldProps.getCrewWithMember(player.getUniqueID()) != null);
/*  44 */       if (isInCrew) {
/*     */         
/*  46 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_ALREADY_IN_CREW, new Object[0]));
/*  47 */         return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
/*     */       } 
/*     */       
/*  50 */       if (props.getBounty() < CommonConfig.INSTANCE.getBountyRequirementForCrews()) {
/*     */         
/*  52 */         player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.CREW_MESSAGE_BOUNTY_REQUIREMENT, new Object[0]));
/*  53 */         return new ActionResult(ActionResultType.FAIL, player.getHeldItem(hand));
/*     */       } 
/*     */       
/*  56 */       WyNetwork.sendTo(new SOpenNewCrewScreenPacket(), player);
/*     */     }
/*     */     else {
/*     */       
/*  60 */       player.setActiveHand(hand);
/*     */     } 
/*     */     
/*  63 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
/*  68 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
/*  74 */     if (!world.isRemote && entity instanceof PlayerEntity) {
/*     */       
/*  76 */       PlayerEntity player = (PlayerEntity)entity;
/*     */       
/*  78 */       if (entity.isPotionActive(ModEffects.DRUNK)) {
/*     */         
/*  80 */         EffectInstance effect = entity.getActivePotionEffect(ModEffects.DRUNK);
/*     */         
/*  82 */         int amp = effect.getAmplifier();
/*  83 */         int duration = effect.getDuration();
/*     */         
/*  85 */         if (amp < 4) {
/*  86 */           amp++;
/*     */         }
/*  88 */         entity.removePotionEffect(ModEffects.DRUNK);
/*  89 */         entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, duration + 200, amp));
/*     */       }
/*     */       else {
/*     */         
/*  93 */         entity.addPotionEffect(new EffectInstance(ModEffects.DRUNK, 400, 0));
/*     */       } 
/*     */       
/*  96 */       if (!player.isCreative()) {
/*  97 */         itemStack.damageItem(1, entity, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 102 */     return itemStack;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public UseAction getUseAction(ItemStack stack) {
/* 108 */     return UseAction.DRINK;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\SakeBottleItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */