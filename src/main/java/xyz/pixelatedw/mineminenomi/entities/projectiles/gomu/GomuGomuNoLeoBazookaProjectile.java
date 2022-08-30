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
/*    */ public class GomuGomuNoLeoBazookaProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public GomuGomuNoLeoBazookaProjectile(World world) {
/* 14 */     super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoLeoBazookaProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoLeoBazookaProjectile(World world, double x, double y, double z) {
/* 24 */     super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoLeoBazookaProjectile(World world, LivingEntity player) {
/* 29 */     super(GomuProjectiles.GOMU_GOMU_NO_LEO_BAZOOKA, world, player);
/*    */     
/* 31 */     setDamage(70.0F);
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
/* 42 */     Vec3d speed = WyHelper.propulsion(getThrower(), 7.0D, 7.0D);
/* 43 */     hitEntity.setMotion(speed.x, 0.8D, speed.z);
/* 44 */     hitEntity.velocityChanged = true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoLeoBazookaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */