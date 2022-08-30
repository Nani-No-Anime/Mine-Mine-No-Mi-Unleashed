/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.bomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BreezeBreathBombProjectile extends AbilityProjectileEntity {
/* 14 */   private int tick = 0;
/*    */ 
/*    */   
/*    */   public BreezeBreathBombProjectile(World world) {
/* 18 */     super(BomuProjectiles.BREEZE_BREATH_BOMB, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BreezeBreathBombProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BreezeBreathBombProjectile(World world, double x, double y, double z) {
/* 28 */     super(BomuProjectiles.BREEZE_BREATH_BOMB, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BreezeBreathBombProjectile(World world, LivingEntity player) {
/* 33 */     super(BomuProjectiles.BREEZE_BREATH_BOMB, world, player);
/*    */     
/* 35 */     setPhysical(false);
/* 36 */     setDamage(15.0F);
/* 37 */     setMaxLife(26);
/* 38 */     setPassThroughEntities();
/*    */     
/* 40 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 45 */     if (this.tick > 0) {
/*    */       
/* 47 */       BlockPos pos = getPosition();
/* 48 */       ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, pos.getX(), pos.getY(), pos.getZ(), 2.0F);
/* 49 */       explosion.setHeightDifference(30);
/* 50 */       explosion.setStaticDamage(12.0F);
/* 51 */       explosion.setExplosionSound(true);
/* 52 */       explosion.setDamageOwner(false);
/* 53 */       explosion.setDestroyBlocks(true);
/* 54 */       explosion.setFireAfterExplosion(false);
/* 55 */       explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 56 */       explosion.setDamageEntities(true);
/* 57 */       explosion.doExplosion();
/*    */     } 
/* 59 */     this.tick++;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\bomu\BreezeBreathBombProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */