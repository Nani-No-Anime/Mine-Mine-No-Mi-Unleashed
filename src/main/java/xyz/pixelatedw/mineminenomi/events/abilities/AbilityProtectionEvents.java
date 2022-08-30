/*    */ package xyz.pixelatedw.mineminenomi.events.abilities;
/*    */ 
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.EntityRayTraceResult;
/*    */ import net.minecraft.util.math.RayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraftforge.event.world.ExplosionEvent;
/*    */ import net.minecraftforge.eventbus.api.SubscribeEvent;
/*    */ import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.ProjectileHitEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ @EventBusSubscriber(modid = "mineminenomi")
/*    */ public class AbilityProtectionEvents
/*    */ {
/*    */   @SubscribeEvent
/*    */   public static void onExplosionDetonate(ExplosionEvent.Detonate event) {
/* 21 */     if (!(event.getWorld()).isRemote) {
/*    */       
/* 23 */       ExtendedWorldData worldData = ExtendedWorldData.get(event.getWorld());
/*    */       
/* 25 */       Vec3d pos = event.getExplosion().getPosition();
/* 26 */       if (worldData.isInsideRestrictedArea((int)pos.x, (int)pos.y, (int)pos.z)) {
/*    */         
/* 28 */         event.getAffectedBlocks().clear();
/* 29 */         event.getAffectedEntities().clear();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   @SubscribeEvent
/*    */   public static void onAbilityHit(ProjectileHitEvent event) {
/* 37 */     if (event.getHit().getType() == RayTraceResult.Type.ENTITY) {
/*    */       
/* 39 */       EntityRayTraceResult entityHit = (EntityRayTraceResult)event.getHit();
/*    */       
/* 41 */       if (entityHit.getEntity() instanceof LivingEntity) {
/*    */         
/* 43 */         LivingEntity hitEntity = (LivingEntity)entityHit.getEntity();
/*    */         
/* 45 */         if (AbilityHelper.checkForRestriction(hitEntity.world, (int)hitEntity.getPosX(), (int)hitEntity.getPosY(), (int)hitEntity.getPosZ()))
/*    */         {
/* 47 */           event.setCanceled(true);
/*    */         }
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\events\abilities\AbilityProtectionEvents.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */