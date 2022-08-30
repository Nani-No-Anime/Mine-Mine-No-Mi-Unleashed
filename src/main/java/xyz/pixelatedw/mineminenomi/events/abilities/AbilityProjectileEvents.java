/*    */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*    */ 
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileBlockEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class AbilityProjectileEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onBlockCheck(ProjectileBlockEvent event) {
/* 16 */     if (event.getProjectile() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.NormalBulletProjectile || event.getProjectile() instanceof xyz.pixelatedw.mineminenomi.entities.projectiles.extra.KairosekiBulletProjectile)
/*    */     {
/* 18 */       event.setCanBlock(true);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityProjectileEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */