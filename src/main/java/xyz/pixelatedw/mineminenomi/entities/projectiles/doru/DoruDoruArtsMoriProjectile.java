/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.doru;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class DoruDoruArtsMoriProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public DoruDoruArtsMoriProjectile(World world) {
/* 12 */     super(DoruProjectiles.DORU_DORU_ARTS_MORI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DoruDoruArtsMoriProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public DoruDoruArtsMoriProjectile(World world, double x, double y, double z) {
/* 22 */     super(DoruProjectiles.DORU_DORU_ARTS_MORI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public DoruDoruArtsMoriProjectile(World world, LivingEntity player) {
/* 27 */     super(DoruProjectiles.DORU_DORU_ARTS_MORI, world, player);
/*    */     
/* 29 */     setDamage(20.0F);
/* 30 */     setMaxLife(40);
/* 31 */     setPhysical(false);
/* 32 */     setAffectedByImbuing();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\doru\DoruDoruArtsMoriProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */