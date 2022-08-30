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
/*    */ public class ShadowItem
/*    */   extends Item
/*    */ {
/*    */   public ShadowItem() {
/* 26 */     super((new Item.Properties()).group(ModCreativeTabs.MISC).food(Foods.DRIED_KELP));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 32 */     player.setActiveHand(hand);
/* 33 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ItemStack onItemUseFinish(ItemStack itemStack, World world, LivingEntity entity) {
/* 39 */     if (!world.isRemote && entity instanceof PlayerEntity) {
/*    */       
/* 41 */       IEntityStats props = EntityStatsCapability.get(entity);
/* 42 */       if (!props.hasShadow()) {
/*    */         
/* 44 */         props.setShadow(true);
/* 45 */         WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(entity.getEntityId(), props), entity);
/*    */ 
/*    */       
/*    */       }
/* 49 */       else if (entity.getActivePotionEffect(Effects.STRENGTH) == null || entity.getActivePotionEffect(Effects.RESISTANCE) == null) {
/*    */         
/* 51 */         entity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 100, 0, false, false));
/* 52 */         entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 100, 0, false, false));
/*    */       }
/*    */       else {
/*    */         
/* 56 */         int duration = entity.getActivePotionEffect(Effects.STRENGTH).getDuration();
/* 57 */         int amplifier = 0;
/*    */         
/* 59 */         duration += 100;
/*    */         
/* 61 */         if (duration > 500) {
/* 62 */           amplifier = 1;
/*    */         }
/* 64 */         if (duration > 1200) {
/* 65 */           duration = 1200;
/*    */         }
/* 67 */         entity.removePotionEffect(Effects.STRENGTH);
/* 68 */         entity.addPotionEffect(new EffectInstance(Effects.STRENGTH, duration, amplifier));
/*    */         
/* 70 */         duration = entity.getActivePotionEffect(Effects.RESISTANCE).getDuration();
/* 71 */         amplifier = 0;
/*    */         
/* 73 */         duration += 100;
/*    */         
/* 75 */         if (duration > 500) {
/* 76 */           amplifier = 1;
/*    */         }
/* 78 */         if (duration > 1200) {
/* 79 */           duration = 1200;
/*    */         }
/* 81 */         entity.removePotionEffect(Effects.RESISTANCE);
/* 82 */         entity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, duration, amplifier));
/*    */       } 
/*    */ 
/*    */       
/* 86 */       itemStack.shrink(1);
/*    */     } 
/*    */     
/* 89 */     return itemStack;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UseAction getUseAction(ItemStack stack) {
/* 96 */     return UseAction.EAT;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\ShadowItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */