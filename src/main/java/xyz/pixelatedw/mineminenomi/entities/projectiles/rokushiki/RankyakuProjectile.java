/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;
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
/*    */ public class RankyakuProjectile extends AbilityProjectileEntity {
/*    */   public RankyakuProjectile(World world) {
/* 16 */     super(RokushikiProjectiles.RANKYAKU, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RankyakuProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RankyakuProjectile(World world, double x, double y, double z) {
/* 26 */     super(RokushikiProjectiles.RANKYAKU, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public RankyakuProjectile(World world, LivingEntity entity) {
/* 31 */     super(RokushikiProjectiles.RANKYAKU, world, entity);
/*    */     
/* 33 */     setDamage(30.0F);
/* 34 */     setMaxLife(40);
/* 35 */     setCanGetStuckInGround();
/* 36 */     setPassThroughEntities();
/* 37 */     setPhysical(false);
/* 38 */     setAffectedByImbuing();
/*    */     
/* 40 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 45 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), 3.0F);
/* 46 */     explosion.setStaticDamage(15.0F);
/* 47 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 48 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\rokushiki\RankyakuProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */