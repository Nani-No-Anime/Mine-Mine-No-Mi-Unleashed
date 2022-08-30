/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.zou;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TrunkShotProjectile
/*    */   extends AbilityProjectileEntity {
/*    */   public TrunkShotProjectile(World world) {
/* 14 */     super(ZouProjectiles.TRUNK_SHOT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TrunkShotProjectile(EntityType type, World world) {
/* 19 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TrunkShotProjectile(World world, double x, double y, double z) {
/* 24 */     super(ZouProjectiles.TRUNK_SHOT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public TrunkShotProjectile(World world, LivingEntity player) {
/* 29 */     super(ZouProjectiles.TRUNK_SHOT, world, player);
/*    */     
/* 31 */     setDamage(24.0F);
/* 32 */     setMaxLife(10);
/* 33 */     setPhysical(true);
/*    */     
/* 35 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 40 */     target.addPotionEffect(new EffectInstance(Effects.WEAKNESS, 100, 1));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\zou\TrunkShotProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */