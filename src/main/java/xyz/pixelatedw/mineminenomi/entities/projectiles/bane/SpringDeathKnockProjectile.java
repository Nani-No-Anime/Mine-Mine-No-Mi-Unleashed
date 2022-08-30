/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bane;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class SpringDeathKnockProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public SpringDeathKnockProjectile(World world) {
/* 12 */     super(BaneProjectiles.SPRING_DEATH_KNOCK, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpringDeathKnockProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpringDeathKnockProjectile(World world, double x, double y, double z) {
/* 22 */     super(BaneProjectiles.SPRING_DEATH_KNOCK, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public SpringDeathKnockProjectile(World world, LivingEntity player) {
/* 27 */     super(BaneProjectiles.SPRING_DEATH_KNOCK, world, player);
/*    */     
/* 29 */     setDamage(20.0F);
/* 30 */     setMaxLife(5);
/* 31 */     setPhysical(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bane\SpringDeathKnockProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */