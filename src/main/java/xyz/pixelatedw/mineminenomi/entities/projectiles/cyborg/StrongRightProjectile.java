/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class StrongRightProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public StrongRightProjectile(World world) {
/* 12 */     super(CyborgProjectiles.STRONG_RIGHT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public StrongRightProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public StrongRightProjectile(World world, double x, double y, double z) {
/* 22 */     super(CyborgProjectiles.STRONG_RIGHT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public StrongRightProjectile(World world, LivingEntity player) {
/* 27 */     super(CyborgProjectiles.STRONG_RIGHT, world, player);
/*    */     
/* 29 */     setPhysical(true);
/* 30 */     setDamage(20.0F);
/* 31 */     setMaxLife(7);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\StrongRightProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */