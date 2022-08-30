/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;
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
/*    */ public class NoseFancyCannonProjectile extends AbilityProjectileEntity {
/*    */   public NoseFancyCannonProjectile(World world) {
/* 16 */     super(BomuProjectiles.NOSE_FANCY_CANNON, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NoseFancyCannonProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public NoseFancyCannonProjectile(World world, double x, double y, double z) {
/* 26 */     super(BomuProjectiles.NOSE_FANCY_CANNON, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public NoseFancyCannonProjectile(World world, LivingEntity player) {
/* 31 */     super(BomuProjectiles.NOSE_FANCY_CANNON, world, player);
/*    */     
/* 33 */     setDamage(10.0F);
/* 34 */     setPhysical(false);
/* 35 */     setMaxLife(32);
/* 36 */     setAffectedByImbuing();
/*    */     
/* 38 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 43 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
/* 44 */     explosion.setStaticDamage(15.0F);
/* 45 */     explosion.setExplosionSound(true);
/* 46 */     explosion.setDamageOwner(false);
/* 47 */     explosion.setDestroyBlocks(true);
/* 48 */     explosion.setFireAfterExplosion(false);
/* 49 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 50 */     explosion.setDamageEntities(true);
/* 51 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bomu\NoseFancyCannonProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */