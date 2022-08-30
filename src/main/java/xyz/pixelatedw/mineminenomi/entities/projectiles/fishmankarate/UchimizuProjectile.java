/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.WaterExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class UchimizuProjectile extends AbilityProjectileEntity {
/*    */   public UchimizuProjectile(World world) {
/* 19 */     super(FishmanKarateProjectiles.UCHIMIZU, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public UchimizuProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public UchimizuProjectile(World world, double x, double y, double z) {
/* 29 */     super(FishmanKarateProjectiles.UCHIMIZU, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public UchimizuProjectile(World world, LivingEntity player) {
/* 34 */     super(FishmanKarateProjectiles.UCHIMIZU, world, player);
/*    */     
/* 36 */     setDamage(2.0F);
/* 37 */     setMaxLife(30);
/* 38 */     setChangeHurtTime(true);
/* 39 */     setHurtTime(10);
/* 40 */     setPhysical(false);
/* 41 */     setAffectedByImbuing();
/*    */     
/* 43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 44 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 49 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 1.0F);
/* 50 */     explosion.setExplosionSound(false);
/* 51 */     explosion.setDamageOwner(false);
/* 52 */     explosion.disableExplosionKnockback();
/* 53 */     explosion.setDestroyBlocks(false);
/* 54 */     explosion.setFireAfterExplosion(false);
/* 55 */     explosion.setSmokeParticles((ParticleEffect)new WaterExplosionParticleEffect());
/* 56 */     explosion.setDamageEntities(false);
/* 57 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 62 */     if (!this.world.isRemote)
/*    */     {
/* 64 */       for (int i = 0; i < 2; i++) {
/*    */         
/* 66 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 67 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 68 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 70 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.FISHING, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 71 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.BUBBLE, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\UchimizuProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */