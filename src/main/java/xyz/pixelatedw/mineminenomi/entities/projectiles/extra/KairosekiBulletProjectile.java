/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class KairosekiBulletProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public KairosekiBulletProjectile(World world) {
/* 14 */     super(ExtraProjectiles.KAIROSEKI_BULLET, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KairosekiBulletProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KairosekiBulletProjectile(World world, double x, double y, double z) {
/* 24 */     super(ExtraProjectiles.KAIROSEKI_BULLET, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public KairosekiBulletProjectile(World world, LivingEntity player) {
/* 29 */     super(ExtraProjectiles.KAIROSEKI_BULLET, world, player);
/* 30 */     setPhysical(false);
/* 31 */     setDamage(8.0F);
/*    */     
/* 33 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.ABILITY_OFF, 20, 2) });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\KairosekiBulletProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */