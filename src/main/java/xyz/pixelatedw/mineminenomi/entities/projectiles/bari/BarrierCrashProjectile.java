/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bari;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BarrierCrashProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BarrierCrashProjectile(World world) {
/* 12 */     super(BariProjectiles.BARRIER_CRASH, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrierCrashProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrierCrashProjectile(World world, double x, double y, double z) {
/* 22 */     super(BariProjectiles.BARRIER_CRASH, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarrierCrashProjectile(World world, LivingEntity player) {
/* 27 */     super(BariProjectiles.BARRIER_CRASH, world, player);
/*    */     
/* 29 */     setDamage(20.0F);
/* 30 */     setMaxLife(10);
/* 31 */     setPhysical(false);
/* 32 */     setAffectedByImbuing();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bari\BarrierCrashProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */