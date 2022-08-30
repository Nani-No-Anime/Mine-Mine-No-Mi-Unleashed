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
/*    */ public class GomuGomuNoJetBazookaProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public GomuGomuNoJetBazookaProjectile(World world) {
/* 14 */     super(GomuProjectiles.GOMU_GOMU_NO_JET_BAZOOKA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetBazookaProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetBazookaProjectile(World world, double x, double y, double z) {
/* 24 */     super(GomuProjectiles.GOMU_GOMU_NO_JET_BAZOOKA, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoJetBazookaProjectile(World world, LivingEntity player) {
/* 29 */     super(GomuProjectiles.GOMU_GOMU_NO_JET_BAZOOKA, world, player);
/*    */     
/* 31 */     setDamage(30.0F);
/* 32 */     setMaxLife(5);
/* 33 */     setPhysical(true);
/* 34 */     setHurtTime(10);
/*    */     
/* 36 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 41 */     Vec3d speed = WyHelper.propulsion(getThrower(), 4.5D, 4.5D);
/* 42 */     hitEntity.setMotion(speed.x, 0.2D, speed.z);
/* 43 */     hitEntity.velocityChanged = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoJetBazookaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */