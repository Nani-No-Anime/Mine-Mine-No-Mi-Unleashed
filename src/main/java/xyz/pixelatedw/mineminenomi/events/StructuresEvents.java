/*    */ package xyz.pixelatedw.mineminenomi.events;
/*    */ 
/*    */ import net.minecraftforge.event.entity.EntityJoinWorldEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class StructuresEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onEntityJoinWorld(EntityJoinWorldEvent event) {
/* 16 */     if (event.getEntity() instanceof net.minecraft.entity.player.PlayerEntity)
/*    */     {
/*    */       
/* 19 */       StructuresHelper.SPAWNED_STRUCTURES.clear();
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\StructuresEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */