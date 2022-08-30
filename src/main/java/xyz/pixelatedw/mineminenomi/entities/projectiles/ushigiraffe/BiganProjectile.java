/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ushigiraffe;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ @Deprecated
/*    */ public class BiganProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BiganProjectile(World world) {
/* 13 */     super(UshiGiraffeProjectiles.BIGAN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BiganProjectile(EntityType type, World world) {
/* 18 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BiganProjectile(World world, double x, double y, double z) {
/* 23 */     super(UshiGiraffeProjectiles.BIGAN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BiganProjectile(World world, LivingEntity player) {
/* 28 */     super(UshiGiraffeProjectiles.BIGAN, world, player);
/* 29 */     setPhysical(true);
/* 30 */     setMaxLife(16);
/* 31 */     setDamage(22.0F);
/* 32 */     setCanGetStuckInGround();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectile\\ushigiraffe\BiganProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */