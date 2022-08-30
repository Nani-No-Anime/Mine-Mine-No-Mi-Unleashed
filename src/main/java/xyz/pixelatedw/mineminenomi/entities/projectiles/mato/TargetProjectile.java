/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mato;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TargetProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public TargetProjectile(World world) {
/* 12 */     super(MatoProjectiles.TARGET_PROJECTILE, world);
/*    */   }
/*    */   
/*    */   public TargetProjectile(EntityType entity, World world) {
/* 16 */     super(entity, world);
/*    */   }
/*    */   
/*    */   public TargetProjectile(World world, double x, double y, double z) {
/* 20 */     super(MatoProjectiles.TARGET_PROJECTILE, world, x, y, z);
/*    */   }
/*    */   
/*    */   public TargetProjectile(World world, LivingEntity p) {
/* 24 */     super(MatoProjectiles.TARGET_PROJECTILE, world, p);
/* 25 */     setMaxLife(128);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mato\TargetProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */