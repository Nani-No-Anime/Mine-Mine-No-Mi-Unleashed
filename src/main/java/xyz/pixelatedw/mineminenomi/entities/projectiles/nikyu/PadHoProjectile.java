/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.nikyu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class PadHoProjectile extends AbilityProjectileEntity {
/*    */   public PadHoProjectile(World world) {
/* 18 */     super(NikyuProjectiles.PAD_HO, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PadHoProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PadHoProjectile(World world, double x, double y, double z) {
/* 28 */     super(NikyuProjectiles.PAD_HO, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public PadHoProjectile(World world, LivingEntity player) {
/* 33 */     super(NikyuProjectiles.PAD_HO, world, player);
/*    */     
/* 35 */     setDamage(15.0F);
/* 36 */     setPhysical(false);
/* 37 */     setAffectedByImbuing();
/*    */     
/* 39 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 40 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 45 */     this.onBlockImpactEvent.onImpact(target.getPosition());
/*    */     
/* 47 */     if (getDamage() > 10.0F) {
/*    */       
/* 49 */       Vec3d speed = target.getLook(1.0F).mul(-1.0D, -1.0D, -1.0D).mul(WyHelper.randomWithRange(4, 6), WyHelper.randomWithRange(1, 3), WyHelper.randomWithRange(4, 6));
/* 50 */       target.setMotion(speed.x, speed.y, speed.z);
/* 51 */       target.velocityChanged = true;
/* 52 */       target.fallDistance = 0.0F;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 58 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), getDamage() / 5.0F);
/* 59 */     explosion.setStaticDamage(getDamage() / 3.0F);
/* 60 */     explosion.setExplosionSound(true);
/* 61 */     explosion.setDamageOwner(false);
/* 62 */     explosion.setDestroyBlocks(true);
/* 63 */     explosion.setFireAfterExplosion(false);
/* 64 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 65 */     explosion.setDamageEntities(false);
/* 66 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\nikyu\PadHoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */