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
/*    */ public class MiniHollowProjectile extends AbilityProjectileEntity {
/*    */   public MiniHollowProjectile(World world) {
/* 18 */     super(HoroProjectiles.MINI_HOLLOW, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MiniHollowProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public MiniHollowProjectile(World world, double x, double y, double z) {
/* 28 */     super(HoroProjectiles.MINI_HOLLOW, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public MiniHollowProjectile(World world, LivingEntity player) {
/* 33 */     super(HoroProjectiles.MINI_HOLLOW, world, player);
/*    */     
/* 35 */     setDamage(2.5F);
/* 36 */     setChangeHurtTime(true);
/*    */     
/* 38 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.NAUSEA, 150, 0), new EffectInstance(Effects.SLOWNESS, 150, 0) });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 45 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 50 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, pos.getX(), pos.getY(), pos.getZ(), 1.0F);
/* 51 */     explosion.setStaticDamage(7.5F);
/* 52 */     explosion.setExplosionSound(true);
/* 53 */     explosion.setDamageOwner(false);
/* 54 */     explosion.setDestroyBlocks(true);
/* 55 */     explosion.setFireAfterExplosion(false);
/* 56 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 57 */     explosion.setDamageEntities(false);
/* 58 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\horo\MiniHollowProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */