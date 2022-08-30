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
/*    */ public class SanbyakurokujuPoundHoProjectile extends AbilityProjectileEntity {
/*    */   public SanbyakurokujuPoundHoProjectile(World world) {
/* 17 */     super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SanbyakurokujuPoundHoProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SanbyakurokujuPoundHoProjectile(World world, double x, double y, double z) {
/* 27 */     super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public SanbyakurokujuPoundHoProjectile(World world, LivingEntity player) {
/* 32 */     super(SwordsmanProjectiles.SANBYAKUROKUJU_POUND_HO, world, player);
/*    */     
/* 34 */     setDamage(25.0F);
/* 35 */     setMaxLife(10);
/* 36 */     setPassThroughEntities();
/* 37 */     setPhysical(false);
/* 38 */     setAffectedByImbuing();
/*    */     
/* 40 */     this.source = (DamageSource)(new ModIndirectEntityDamageSource("ability_projectile", (Entity)this, (Entity)getThrower())).setProjectile().markDamageAsSlash();
/* 41 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 46 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
/* 47 */     explosion.setStaticDamage(10.0F);
/* 48 */     explosion.setExplosionSound(true);
/* 49 */     explosion.setDamageOwner(false);
/* 50 */     explosion.setDestroyBlocks(true);
/* 51 */     explosion.setFireAfterExplosion(false);
/* 52 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 53 */     explosion.setDamageEntities(true);
/* 54 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\swordsman\SanbyakurokujuPoundHoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */