/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.fishmankarate;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.WaterExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class YarinamiProjectile extends AbilityProjectileEntity {
/*    */   public YarinamiProjectile(World world) {
/* 19 */     super(FishmanKarateProjectiles.YARINAMI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YarinamiProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YarinamiProjectile(World world, double x, double y, double z) {
/* 29 */     super(FishmanKarateProjectiles.YARINAMI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public YarinamiProjectile(World world, LivingEntity player) {
/* 34 */     super(FishmanKarateProjectiles.YARINAMI, world, player);
/*    */     
/* 36 */     setDamage(30.0F);
/* 37 */     setPassThroughEntities();
/* 38 */     setPhysical(false);
/* 39 */     setAffectedByImbuing();
/* 40 */     setMaxLife(30);
/*    */     
/* 42 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 43 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 48 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 4.0F);
/* 49 */     explosion.setStaticDamage(15.0F);
/* 50 */     explosion.setExplosionSound(false);
/* 51 */     explosion.setDamageOwner(false);
/* 52 */     explosion.setDestroyBlocks(true);
/* 53 */     explosion.setFireAfterExplosion(false);
/* 54 */     explosion.setSmokeParticles((ParticleEffect)new WaterExplosionParticleEffect());
/* 55 */     explosion.setDamageEntities(false);
/* 56 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 61 */     if (!this.world.isRemote)
/*    */     {
/* 63 */       for (int i = 0; i < 15; i++) {
/*    */         
/* 65 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 66 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 67 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 69 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.FISHING, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 70 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.BUBBLE, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\fishmankarate\YarinamiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */