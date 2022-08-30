/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.baku;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BeroCannonProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BeroCannonProjectile(World world) {
/* 12 */     super(BakuProjectiles.BERO_CANNON, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BeroCannonProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BeroCannonProjectile(World world, double x, double y, double z) {
/* 22 */     super(BakuProjectiles.BERO_CANNON, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BeroCannonProjectile(World world, LivingEntity player) {
/* 27 */     super(BakuProjectiles.BERO_CANNON, world, player);
/*    */     
/* 29 */     setDamage(20.0F);
/* 30 */     setMaxLife(50);
/* 31 */     setGravity(0.01F);
/* 32 */     setPhysical(false);
/* 33 */     setAffectedByImbuing();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\baku\BeroCannonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */