/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suke;
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
/*    */ public class ShishaNoTeProjectile extends AbilityProjectileEntity {
/*    */   public ShishaNoTeProjectile(World world) {
/* 16 */     super(SukeProjectiles.SHISHA_NO_TE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ShishaNoTeProjectile(EntityType type, World world) {
/* 21 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ShishaNoTeProjectile(World world, double x, double y, double z) {
/* 26 */     super(SukeProjectiles.SHISHA_NO_TE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public ShishaNoTeProjectile(World world, LivingEntity player) {
/* 31 */     super(SukeProjectiles.SHISHA_NO_TE, world, player);
/*    */     
/* 33 */     setDamage(18.0F);
/* 34 */     setPhysical(false);
/* 35 */     setAffectedByImbuing();
/*    */     
/* 37 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 42 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
/* 43 */     explosion.setStaticDamage(8.0F);
/* 44 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(2));
/* 45 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suke\ShishaNoTeProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */