/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class NegativeHollowProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public NegativeHollowProjectile(World world) {
/* 15 */     super(HoroProjectiles.NEGATIVE_HOLLOW, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NegativeHollowProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NegativeHollowProjectile(World world, double x, double y, double z) {
/* 25 */     super(HoroProjectiles.NEGATIVE_HOLLOW, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public NegativeHollowProjectile(World world, LivingEntity player) {
/* 30 */     super(HoroProjectiles.NEGATIVE_HOLLOW, world, player);
/*    */     
/* 32 */     setDamage(10.0F);
/*    */     
/* 34 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.NAUSEA, 200, 1), new EffectInstance(Effects.SLOWNESS, 200, 1), new EffectInstance(ModEffects.NEGATIVE, 300, 1) });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\horo\NegativeHollowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */