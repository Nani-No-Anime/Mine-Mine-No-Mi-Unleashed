/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.horo;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class TokuHollowProjectile extends AbilityProjectileEntity {
/*    */   public TokuHollowProjectile(World world) {
/* 18 */     super(HoroProjectiles.TOKU_HOLLOW, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TokuHollowProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public TokuHollowProjectile(World world, double x, double y, double z) {
/* 28 */     super(HoroProjectiles.TOKU_HOLLOW, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public TokuHollowProjectile(World world, LivingEntity player) {
/* 33 */     super(HoroProjectiles.TOKU_HOLLOW, world, player);
/*    */     
/* 35 */     setDamage(25.0F);
/*    */     
/* 37 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.NAUSEA, 350, 1), new EffectInstance(Effects.SLOWNESS, 350, 1) });
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 42 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 47 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, pos.getX(), pos.getY(), pos.getZ(), 7.0F);
/* 48 */     explosion.setStaticDamage(35.0F);
/* 49 */     explosion.setExplosionSound(true);
/* 50 */     explosion.setDamageOwner(false);
/* 51 */     explosion.setDestroyBlocks(true);
/* 52 */     explosion.setFireAfterExplosion(false);
/* 53 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(7));
/* 54 */     explosion.setDamageEntities(true);
/* 55 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\horo\TokuHollowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */