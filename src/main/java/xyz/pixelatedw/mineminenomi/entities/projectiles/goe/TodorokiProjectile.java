/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.goe;
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
/*    */ public class TodorokiProjectile extends AbilityProjectileEntity {
/*    */   public TodorokiProjectile(World world) {
/* 16 */     super(GoeProjectiles.TODOROKI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TodorokiProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TodorokiProjectile(World world, double x, double y, double z) {
/* 26 */     super(GoeProjectiles.TODOROKI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public TodorokiProjectile(World world, LivingEntity player) {
/* 31 */     super(GoeProjectiles.TODOROKI, world, player);
/*    */     
/* 33 */     setDamage(15.0F);
/* 34 */     setMaxLife(15);
/* 35 */     setDamageSource(this.bypassingSource);
/* 36 */     setPassThroughEntities();
/* 37 */     setCanGetStuckInGround();
/* 38 */     setCollisionSize(2.5D);
/*    */     
/* 40 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 45 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
/* 46 */     explosion.setStaticDamage(15.0F);
/* 47 */     explosion.setExplosionSound(true);
/* 48 */     explosion.setDamageOwner(false);
/* 49 */     explosion.setDestroyBlocks(true);
/* 50 */     explosion.setFireAfterExplosion(false);
/* 51 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/* 52 */     explosion.setDamageEntities(true);
/* 53 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\goe\TodorokiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */