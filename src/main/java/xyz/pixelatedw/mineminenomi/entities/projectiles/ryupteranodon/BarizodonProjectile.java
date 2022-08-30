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
/*    */ public class BarizodonProjectile extends AbilityProjectileEntity {
/*    */   public BarizodonProjectile(World world) {
/* 16 */     super(RyuPteranodonProjectiles.BARIZODON, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarizodonProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarizodonProjectile(World world, double x, double y, double z) {
/* 26 */     super(RyuPteranodonProjectiles.BARIZODON, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarizodonProjectile(World world, LivingEntity player) {
/* 31 */     super(RyuPteranodonProjectiles.BARIZODON, world, player);
/*    */     
/* 33 */     setDamage(10.0F);
/* 34 */     setPassThroughEntities();
/* 35 */     setMaxLife(32);
/*    */     
/* 37 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 42 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
/* 43 */     explosion.setStaticDamage(5.0F);
/* 44 */     explosion.disableExplosionKnockback();
/* 45 */     explosion.setDestroyBlocks(true);
/* 46 */     explosion.setFireAfterExplosion(false);
/* 47 */     explosion.setExplosionSound(false);
/* 48 */     explosion.setDamageEntities(false);
/* 49 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 50 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\ryupteranodon\BarizodonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */