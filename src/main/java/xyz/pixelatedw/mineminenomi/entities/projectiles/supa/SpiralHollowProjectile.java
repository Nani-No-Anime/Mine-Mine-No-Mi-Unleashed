/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.supa;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class SpiralHollowProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public SpiralHollowProjectile(World world) {
/* 12 */     super(SupaProjectiles.SPIRAL_HOLLOW, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpiralHollowProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpiralHollowProjectile(World world, double x, double y, double z) {
/* 22 */     super(SupaProjectiles.SPIRAL_HOLLOW, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpiralHollowProjectile(World world, LivingEntity player) {
/* 27 */     super(SupaProjectiles.SPIRAL_HOLLOW, world, player);
/*    */     
/* 29 */     setDamage(30.0F);
/* 30 */     setAffectedByImbuing();
/* 31 */     setPhysical(false);
/* 32 */     setMaxLife(10);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\supa\SpiralHollowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */