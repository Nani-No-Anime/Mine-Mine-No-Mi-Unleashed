/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ope;
/*    */ 
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GammaKnifeProjectile
/*    */   extends AbilityProjectileEntity
/*    */ {
/*    */   public GammaKnifeProjectile(World world) {
/* 12 */     super(OpeProjectiles.GAMMA_KNIFE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GammaKnifeProjectile(EntityType type, World world) {
/* 17 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GammaKnifeProjectile(World world, double x, double y, double z) {
/* 22 */     super(OpeProjectiles.GAMMA_KNIFE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GammaKnifeProjectile(World world, LivingEntity player) {
/* 27 */     super(OpeProjectiles.GAMMA_KNIFE, world, player);
/*    */     
/* 29 */     setDamage(70.0F);
/* 30 */     setDamageSource(this.bypassingSource);
/* 31 */     setMaxLife(4);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ope\GammaKnifeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */