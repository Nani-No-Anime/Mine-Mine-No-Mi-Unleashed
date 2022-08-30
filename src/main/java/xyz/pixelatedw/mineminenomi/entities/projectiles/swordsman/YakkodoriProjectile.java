/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.swordsman;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.damagesource.ModIndirectEntityDamageSource;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class YakkodoriProjectile extends AbilityProjectileEntity {
/*    */   public YakkodoriProjectile(World world) {
/* 17 */     super(SwordsmanProjectiles.YAKKODORI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YakkodoriProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YakkodoriProjectile(World world, double x, double y, double z) {
/* 27 */     super(SwordsmanProjectiles.YAKKODORI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public YakkodoriProjectile(World world, LivingEntity player) {
/* 32 */     super(SwordsmanProjectiles.YAKKODORI, world, player);
/*    */     
/* 34 */     setDamage(15.0F);
/* 35 */     setMaxLife(10);
/* 36 */     setCanGetStuckInGround();
/* 37 */     setPhysical(false);
/* 38 */     setAffectedByImbuing();
/*    */     
/* 40 */     this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
/* 41 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 46 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 1.0F);
/* 47 */     explosion.setStaticDamage(5.0F);
/* 48 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 49 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\swordsman\YakkodoriProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */