/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.mero;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class PistolKissProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public PistolKissProjectile(World world) {
/* 14 */     super(MeroProjectiles.PISTOL_KISS, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PistolKissProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PistolKissProjectile(World world, double x, double y, double z) {
/* 24 */     super(MeroProjectiles.PISTOL_KISS, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public PistolKissProjectile(World world, LivingEntity player) {
/* 29 */     super(MeroProjectiles.PISTOL_KISS, world, player);
/*    */     
/* 31 */     setDamage(4.0F);
/* 32 */     setMaxLife(30);
/*    */     
/* 34 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.LOVESTRUCK, 50, 0) });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mero\PistolKissProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */