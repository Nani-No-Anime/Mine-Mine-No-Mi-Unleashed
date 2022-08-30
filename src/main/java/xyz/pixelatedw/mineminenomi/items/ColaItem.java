/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Foods;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.UseAction;
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
/*    */ public class ColaItem
/*    */   extends Item
/*    */ {
/*    */   public static final int COLA_REFILL = 25;
/*    */   
/*    */   public ColaItem() {
/* 26 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16).food(Foods.APPLE));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 32 */     if (!world.isRemote);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     player.setActiveHand(hand);
/* 50 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
/* 56 */     if (!world.isRemote && entity instanceof PlayerEntity) {
/*    */       
/* 58 */       PlayerEntity player = (PlayerEntity)entity;
/* 59 */       IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 61 */       if (props.isCyborg())
/*    */       {
/* 63 */         if (props.getCola() + 25 > props.getMaxCola()) {
/* 64 */           props.setCola(props.getMaxCola());
/*    */         } else {
/* 66 */           props.alterCola(25);
/*    */         } 
/*    */       }
/* 69 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), player);
/* 70 */       if (!player.isCreative()) {
/* 71 */         itemStack.shrink(1);
/*    */       }
/*    */     } 
/* 74 */     return itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public UseAction getUseAction(ItemStack stack) {
/* 80 */     return UseAction.DRINK;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\ColaItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */