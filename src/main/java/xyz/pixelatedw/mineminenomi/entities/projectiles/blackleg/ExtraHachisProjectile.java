/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.blackleg;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class ExtraHachisProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public ExtraHachisProjectile(World world) {
/* 12 */     super(BlackLegProjectiles.EXTRA_HACHIS, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ExtraHachisProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ExtraHachisProjectile(World world, double x, double y, double z) {
/* 22 */     super(BlackLegProjectiles.EXTRA_HACHIS, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public ExtraHachisProjectile(World world, LivingEntity player) {
/* 27 */     super(BlackLegProjectiles.EXTRA_HACHIS, world, player);
/*    */     
/* 29 */     setDamage(8.0F);
/* 30 */     setMaxLife(4);
/* 31 */     setPhysical(true);
/* 32 */     setChangeHurtTime(true);
/* 33 */     setHurtTime(5);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\blackleg\ExtraHachisProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */