/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class CoupDeVentProjectile extends AbilityProjectileEntity {
/*    */   public CoupDeVentProjectile(World world) {
/* 16 */     super(CyborgProjectiles.COUP_DE_VENT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CoupDeVentProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CoupDeVentProjectile(World world, double x, double y, double z) {
/* 26 */     super(CyborgProjectiles.COUP_DE_VENT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public CoupDeVentProjectile(World world, LivingEntity player) {
/* 31 */     super(CyborgProjectiles.COUP_DE_VENT, world, player);
/*    */     
/* 33 */     setDamage(15.0F);
/* 34 */     setPassThroughEntities();
/* 35 */     setMaxLife(15);
/* 36 */     setPhysical(false);
/*    */     
/* 38 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 39 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 44 */     double xPower = WyHelper.randomWithRange(-10, 10);
/* 45 */     if (xPower >= 0.0D) {
/* 46 */       xPower += 10.0D;
/*    */     } else {
/* 48 */       xPower -= 10.0D;
/*    */     } 
/* 50 */     double zPower = WyHelper.randomWithRange(-10, 10);
/* 51 */     if (zPower >= 0.0D) {
/* 52 */       zPower += 10.0D;
/*    */     } else {
/* 54 */       zPower -= 10.0D;
/*    */     } 
/* 56 */     target.setPosition(target.getPosX(), target.getPosY() + 10.0D, target.getPosZ());
/* 57 */     target.setMotion(xPower, 2.5D, zPower);
/* 58 */     target.velocityChanged = true;
/* 59 */     target.fallDistance = 0.0F;
/*    */     
/* 61 */     target.attackEntityFrom(DamageSource.causeMobDamage(getThrower()), 15.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 66 */     for (int i = 0; i < 25; i++) {
/*    */       
/* 68 */       double offsetX = WyHelper.randomDouble() * 1.2D;
/* 69 */       double offsetY = WyHelper.randomDouble() * 1.2D;
/* 70 */       double offsetZ = WyHelper.randomDouble() * 1.2D;
/*    */       
/* 72 */       ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.END_ROD, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 73 */       if (i % 5 == 0)
/* 74 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.FLASH, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D); 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\CoupDeVentProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */