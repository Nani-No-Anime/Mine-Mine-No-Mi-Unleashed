/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
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
/*    */ public class RenpatsuNamariBoshiProjectile extends AbilityProjectileEntity {
/*    */   public RenpatsuNamariBoshiProjectile(World world) {
/* 16 */     super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RenpatsuNamariBoshiProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RenpatsuNamariBoshiProjectile(World world, double x, double y, double z) {
/* 26 */     super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public RenpatsuNamariBoshiProjectile(World world, LivingEntity player) {
/* 31 */     super(SniperProjectiles.RENPATSU_NAMARI_BOSHI, world, player);
/*    */     
/* 33 */     setDamage(9.0F);
/* 34 */     setPhysical(false);
/* 35 */     setAffectedByImbuing();
/*    */     
/* 37 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 42 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
/* 43 */     explosion.setStaticDamage(12.0F);
/* 44 */     explosion.setExplosionSound(true);
/* 45 */     explosion.setDamageOwner(false);
/* 46 */     explosion.setDestroyBlocks(true);
/* 47 */     explosion.setFireAfterExplosion(false);
/* 48 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/* 49 */     explosion.setDamageEntities(true);
/* 50 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\RenpatsuNamariBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */