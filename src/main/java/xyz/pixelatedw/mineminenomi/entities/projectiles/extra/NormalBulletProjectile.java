/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ 
/*    */ public class NormalBulletProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public NormalBulletProjectile(World world) {
/* 13 */     super(ExtraProjectiles.NORMAL_BULLET, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NormalBulletProjectile(EntityType type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NormalBulletProjectile(World world, double x, double y, double z) {
/* 23 */     super(ExtraProjectiles.NORMAL_BULLET, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public NormalBulletProjectile(World world, LivingEntity player) {
/* 28 */     super(ExtraProjectiles.NORMAL_BULLET, world, player);
/* 29 */     setPhysical(false);
/* 30 */     setAffectedByImbuing();
/* 31 */     setDamage(8.0F);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\NormalBulletProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */