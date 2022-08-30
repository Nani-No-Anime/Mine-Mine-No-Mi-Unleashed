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
/*    */ public class MeroMeroMellowProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public MeroMeroMellowProjectile(World world) {
/* 14 */     super(MeroProjectiles.MERO_MERO_MELLOW, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MeroMeroMellowProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MeroMeroMellowProjectile(World world, double x, double y, double z) {
/* 24 */     super(MeroProjectiles.MERO_MERO_MELLOW, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public MeroMeroMellowProjectile(World world, LivingEntity player) {
/* 29 */     super(MeroProjectiles.MERO_MERO_MELLOW, world, player);
/*    */     
/* 31 */     setDamage(10.0F);
/* 32 */     setPassThroughEntities();
/* 33 */     setMaxLife(30);
/*    */     
/* 35 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(ModEffects.LOVESTRUCK, 200, 1) });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\mero\MeroMeroMellowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */