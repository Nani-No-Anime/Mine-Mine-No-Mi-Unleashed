/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.noro;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class NoroNoroBeamProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public NoroNoroBeamProjectile(World world) {
/* 14 */     super(NoroProjectiles.NORO_NORO_BEAM, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NoroNoroBeamProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NoroNoroBeamProjectile(World world, double x, double y, double z) {
/* 24 */     super(NoroProjectiles.NORO_NORO_BEAM, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public NoroNoroBeamProjectile(World world, LivingEntity player) {
/* 29 */     super(NoroProjectiles.NORO_NORO_BEAM, world, player);
/*    */     
/* 31 */     setMaxLife(10);
/* 32 */     setCollisionSize(1.25D);
/*    */     
/* 34 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 39 */     if (hitEntity.isPotionActive(ModEffects.NORO_SLOWNESS)) {
/*    */       
/* 41 */       int newTimer = hitEntity.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getDuration() + 240;
/* 42 */       int noroSlowness = Math.min(9, hitEntity.getActivePotionEffect(ModEffects.NORO_SLOWNESS).getAmplifier() + 1);
/*    */       
/* 44 */       hitEntity.removePotionEffect(ModEffects.NORO_SLOWNESS);
/* 45 */       hitEntity.addPotionEffect(new EffectInstance(ModEffects.NORO_SLOWNESS, newTimer, noroSlowness));
/*    */     }
/*    */     else {
/*    */       
/* 49 */       hitEntity.addPotionEffect(new EffectInstance(ModEffects.NORO_SLOWNESS, 240, 0));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\noro\NoroNoroBeamProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */