/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.kage;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BrickBatProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public BrickBatProjectile(World world) {
/* 12 */     super(KageProjectiles.BRICK_BAT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BrickBatProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BrickBatProjectile(World world, double x, double y, double z) {
/* 22 */     super(KageProjectiles.BRICK_BAT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BrickBatProjectile(World world, LivingEntity player) {
/* 27 */     super(KageProjectiles.BRICK_BAT, world, player);
/*    */     
/* 29 */     setDamage(2.0F);
/* 30 */     setPassThroughEntities();
/* 31 */     setChangeHurtTime(true);
/* 32 */     setPhysical(false);
/* 33 */     setAffectedByImbuing();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\kage\BrickBatProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */