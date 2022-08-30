/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.ryupteranodon;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TempuraudonProjectile extends AbilityProjectileEntity {
/*    */   public TempuraudonProjectile(World world) {
/* 16 */     super(RyuPteranodonProjectiles.TEMPURAUDON, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TempuraudonProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TempuraudonProjectile(World world, double x, double y, double z) {
/* 26 */     super(RyuPteranodonProjectiles.TEMPURAUDON, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public TempuraudonProjectile(World world, LivingEntity player) {
/* 31 */     super(RyuPteranodonProjectiles.TEMPURAUDON, world, player);
/*    */     
/* 33 */     setDamage(35.0F);
/* 34 */     setPassThroughEntities();
/* 35 */     setMaxLife(32);
/* 36 */     setArmorPiercing();
/*    */     
/* 38 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 43 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
/* 44 */     explosion.setStaticDamage(15.0F);
/* 45 */     explosion.disableExplosionKnockback();
/* 46 */     explosion.setDestroyBlocks(true);
/* 47 */     explosion.setFireAfterExplosion(false);
/* 48 */     explosion.setExplosionSound(false);
/* 49 */     explosion.setDamageEntities(false);
/* 50 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 51 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ryupteranodon\TempuraudonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */