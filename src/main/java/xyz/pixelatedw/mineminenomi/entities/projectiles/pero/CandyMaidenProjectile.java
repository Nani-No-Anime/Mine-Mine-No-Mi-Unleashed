/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pero;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class CandyMaidenProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public CandyMaidenProjectile(World world) {
/* 11 */     super(PeroProjectiles.CANDY_MAIDEN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyMaidenProjectile(EntityType type, World world) {
/* 16 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyMaidenProjectile(World world, double x, double y, double z) {
/* 21 */     super(PeroProjectiles.CANDY_MAIDEN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public CandyMaidenProjectile(World world, LivingEntity player) {
/* 26 */     super(PeroProjectiles.CANDY_MAIDEN, world, player);
/*    */     
/* 28 */     setPassThroughEntities();
/* 29 */     setPhysical(false);
/* 30 */     setAffectedByImbuing();
/* 31 */     setDamage(35.0F);
/* 32 */     setMaxLife(40);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pero\CandyMaidenProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */