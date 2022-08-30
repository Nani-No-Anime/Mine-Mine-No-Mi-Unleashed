/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Foods;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UltraColaItem
/*    */   extends Item
/*    */ {
/*    */   private static final int ULTRA_COLA_BONUS = 20;
/*    */   public static final int COLA_REFILL = 50;
/*    */   
/*    */   public UltraColaItem() {
/* 30 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16).food(Foods.APPLE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 36 */     player.setActiveHand(hand);
/* 37 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
/* 43 */     if (!world.isRemote && entity instanceof PlayerEntity) {
/*    */       
/* 45 */       PlayerEntity player = (PlayerEntity)entity;
/* 46 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 48 */       if (props.isCyborg()) {
/*    */         
/* 50 */         if (props.getUltraCola() <= 19) {
/*    */           
/* 52 */           props.addUltraCola(1);
/* 53 */           props.alterMaxCola(20);
/* 54 */           props.alterCola(25);
/*    */         }
/*    */         else {
/*    */           
/* 58 */           player.addPotionEffect(new EffectInstance(Effects.REGENERATION, 100, 0, false, false));
/* 59 */           player.addPotionEffect(new EffectInstance(Effects.STRENGTH, 200, 0, false, false));
/* 60 */           player.addPotionEffect(new EffectInstance(Effects.SPEED, 200, 0, false, false));
/* 61 */           props.alterCola(50);
/*    */         } 
/*    */       } else {
/*    */         
/* 65 */         player.addPotionEffect(new EffectInstance(Effects.SPEED, 250, 0));
/*    */       } 
/* 67 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/* 68 */       if (!player.isCreative()) {
/* 69 */         itemStack.shrink(1);
/*    */       }
/*    */     } 
/* 72 */     return itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public UseAction getUseAction(ItemStack stack) {
/* 78 */     return UseAction.DRINK;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\UltraColaItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */