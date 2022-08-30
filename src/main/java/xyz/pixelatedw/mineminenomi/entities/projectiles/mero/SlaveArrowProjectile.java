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
/*    */ public class SlaveArrowProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public SlaveArrowProjectile(World world) {
/* 14 */     super(MeroProjectiles.SLAVE_ARROW, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SlaveArrowProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SlaveArrowProjectile(World world, double x, double y, double z) {
/* 24 */     super(MeroProjectiles.SLAVE_ARROW, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public SlaveArrowProjectile(World world, LivingEntity player) {
/* 29 */     super(MeroProjectiles.SLAVE_ARROW, world, player);
/*    */     
/* 31 */     setDamage(1.6F);
/* 32 */     setChangeHurtTime(true);
/* 33 */     setMaxLife(28);
/*    */     
/* 35 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.LOVESTRUCK, 100, 0) });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mero\SlaveArrowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */