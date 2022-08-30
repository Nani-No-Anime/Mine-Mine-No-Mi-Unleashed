/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
/*    */ 
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
/*    */ public class CannonBallProjectile extends AbilityProjectileEntity {
/*    */   public CannonBallProjectile(World world) {
/* 17 */     super(ExtraProjectiles.CANNON_BALL, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CannonBallProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public CannonBallProjectile(World world, double x, double y, double z) {
/* 27 */     super(ExtraProjectiles.CANNON_BALL, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public CannonBallProjectile(World world, LivingEntity entity) {
/* 32 */     super(ExtraProjectiles.CANNON_BALL, world, entity);
/* 33 */     setDamage(14.0F);
/* 34 */     setMaxLife(40);
/* 35 */     setGravity(0.01F);
/* 36 */     setPhysical(false);
/* 37 */     setAffectedByImbuing();
/*    */     
/* 39 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 44 */     if (this.ticksExisted < 0) {
/*    */       return;
/*    */     }
/* 47 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
/* 48 */     explosion.setStaticDamage(8.0F);
/* 49 */     explosion.setDestroyBlocks(true);
/* 50 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 51 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\CannonBallProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */