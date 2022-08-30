/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.extra;
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
/*    */ public class AxeDialProjectile extends AbilityProjectileEntity {
/*    */   public AxeDialProjectile(World world) {
/* 16 */     super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public AxeDialProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public AxeDialProjectile(World world, double x, double y, double z) {
/* 26 */     super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public AxeDialProjectile(World world, LivingEntity player) {
/* 31 */     super(ExtraProjectiles.AXE_DIAL_PROJECTILE, world, player);
/*    */     
/* 33 */     setDamage(20.0F);
/* 34 */     setMaxLife(20);
/* 35 */     setPhysical(false);
/*    */     
/* 37 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos pos) {
/* 42 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, pos.getX(), pos.getY(), pos.getZ(), 4.0F);
/* 43 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(4));
/* 44 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\extra\AxeDialProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */