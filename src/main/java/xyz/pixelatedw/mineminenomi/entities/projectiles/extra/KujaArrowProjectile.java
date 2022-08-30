/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class KujaArrowProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public KujaArrowProjectile(World world) {
/* 12 */     super(ExtraProjectiles.KUJA_ARROW, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KujaArrowProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KujaArrowProjectile(World world, double x, double y, double z) {
/* 22 */     super(ExtraProjectiles.KUJA_ARROW, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public KujaArrowProjectile(World world, LivingEntity player) {
/* 27 */     super(ExtraProjectiles.KUJA_ARROW, world, player);
/* 28 */     setDamage(15.0F);
/* 29 */     setPhysical(false);
/* 30 */     setAffectedByImbuing();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\KujaArrowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */