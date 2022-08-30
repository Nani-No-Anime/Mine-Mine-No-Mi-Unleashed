/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.wara;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class StrawProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public StrawProjectile(World world) {
/* 12 */     super(WaraProjectiles.STRAW_PROJECTILE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public StrawProjectile(EntityType entity, World world) {
/* 17 */     super(entity, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public StrawProjectile(World world, double x, double y, double z) {
/* 22 */     super(WaraProjectiles.STRAW_PROJECTILE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public StrawProjectile(World world, LivingEntity p) {
/* 27 */     super(WaraProjectiles.STRAW_PROJECTILE, world, p);
/* 28 */     setDamage(10.0F);
/* 29 */     setPhysical(false);
/* 30 */     setMaxLife(8);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\wara\StrawProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */