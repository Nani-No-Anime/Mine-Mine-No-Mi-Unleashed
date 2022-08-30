/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoGrizzlyMagnumProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public GomuGomuNoGrizzlyMagnumProjectile(World world) {
/* 14 */     super(GomuProjectiles.GOMU_GOMU_NO_GRIZZLY_MAGNUM, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoGrizzlyMagnumProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoGrizzlyMagnumProjectile(World world, double x, double y, double z) {
/* 24 */     super(GomuProjectiles.GOMU_GOMU_NO_GRIZZLY_MAGNUM, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoGrizzlyMagnumProjectile(World world, LivingEntity player) {
/* 29 */     super(GomuProjectiles.GOMU_GOMU_NO_GRIZZLY_MAGNUM, world, player);
/*    */     
/* 31 */     setDamage(40.0F);
/* 32 */     setMaxLife(10);
/* 33 */     setPhysical(true);
/* 34 */     setPassThroughEntities();
/* 35 */     setHurtTime(10);
/*    */     
/* 37 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 42 */     Vec3d speed = WyHelper.propulsion(getThrower(), 5.0D, 5.0D);
/* 43 */     hitEntity.setMotion(speed.x, 0.5D, speed.z);
/* 44 */     hitEntity.velocityChanged = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoGrizzlyMagnumProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */