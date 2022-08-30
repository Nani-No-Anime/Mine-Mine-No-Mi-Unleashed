/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.cyborg;
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
/*    */ public class RadicalBeamProjectile extends AbilityProjectileEntity {
/*    */   public RadicalBeamProjectile(World world) {
/* 16 */     super(CyborgProjectiles.RADICAL_BEAM, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RadicalBeamProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RadicalBeamProjectile(World world, double x, double y, double z) {
/* 26 */     super(CyborgProjectiles.RADICAL_BEAM, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public RadicalBeamProjectile(World world, LivingEntity player) {
/* 31 */     super(CyborgProjectiles.RADICAL_BEAM, world, player);
/*    */     
/* 33 */     setDamage(50.0F);
/* 34 */     setMaxLife(15);
/* 35 */     setArmorPiercing();
/* 36 */     setCanGetStuckInGround();
/* 37 */     setPassThroughEntities();
/*    */     
/* 39 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 44 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
/* 45 */     explosion.setStaticDamage(25.0F);
/* 46 */     explosion.setFireAfterExplosion(true);
/* 47 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 48 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\cyborg\RadicalBeamProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */