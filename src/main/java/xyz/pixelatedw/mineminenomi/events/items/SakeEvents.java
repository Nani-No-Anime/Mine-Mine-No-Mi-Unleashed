/*    */ package xyz.pixelatedw.mineminenomi.events.items;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.inventory.EquipmentSlotType;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraftforge.event.entity.player.PlayerInteractEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.items.SakeCupItem;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class SakeEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onPlayerInteract(PlayerInteractEvent.EntityInteractSpecific event) {
/* 22 */     if (!(event.getTarget() instanceof PlayerEntity)) {
/*    */       return;
/*    */     }
/* 25 */     PlayerEntity player = event.getPlayer();
/*    */     
/* 27 */     if (player.getHeldItemMainhand().getItem() != ModItems.SAKE_BOTTLE) {
/*    */       return;
/*    */     }
/* 30 */     PlayerEntity target = (PlayerEntity)event.getTarget();
/*    */     
/* 32 */     if (target.getHeldItemMainhand().getItem() != ModItems.SAKE_CUP) {
/*    */       return;
/*    */     }
/* 35 */     IEntityStats props = EntityStatsCapability.get((LivingEntity)target);
/* 36 */     props.setFaction("pirate");
/* 37 */     ItemStack itemStack = target.getHeldItemMainhand();
/* 38 */     ((SakeCupItem)itemStack.getItem()).setLeader(itemStack, player);
/* 39 */     player.getHeldItemMainhand().damageItem(1, (LivingEntity)player, user -> user.sendBreakAnimation(EquipmentSlotType.MAINHAND));
/*    */ 
/*    */     
/* 42 */     event.setCanceled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\items\SakeEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */