/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoPistolProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public GomuGomuNoPistolProjectile(World world) {
/* 12 */     super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoPistolProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoPistolProjectile(World world, double x, double y, double z) {
/* 22 */     super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoPistolProjectile(World world, LivingEntity player) {
/* 27 */     super(GomuProjectiles.GOMU_GOMU_NO_PISTOL, world, player);
/*    */     
/* 29 */     setDamage(6.0F);
/* 30 */     setMaxLife(9);
/* 31 */     setPhysical(true);
/* 32 */     setChangeHurtTime(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoPistolProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */