/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bara;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BaraBaraHoProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BaraBaraHoProjectile(World world) {
/* 12 */     super(BaraProjectiles.BARA_BARA_HO, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaraBaraHoProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaraBaraHoProjectile(World world, double x, double y, double z) {
/* 22 */     super(BaraProjectiles.BARA_BARA_HO, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BaraBaraHoProjectile(World world, LivingEntity player) {
/* 27 */     super(BaraProjectiles.BARA_BARA_HO, world, player);
/*    */     
/* 29 */     setDamage(6.0F);
/* 30 */     setMaxLife(12);
/* 31 */     setPhysical(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bara\BaraBaraHoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */