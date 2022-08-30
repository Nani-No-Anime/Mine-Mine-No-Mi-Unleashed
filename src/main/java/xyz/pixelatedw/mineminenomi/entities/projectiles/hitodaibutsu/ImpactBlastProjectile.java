/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.hitodaibutsu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.hitodaibutsu.ImpactWaveParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class ImpactBlastProjectile extends AbilityProjectileEntity {
/* 14 */   public static final ParticleEffect PARTICLES = (ParticleEffect)new ImpactWaveParticleEffect();
/*    */ 
/*    */   
/*    */   public ImpactBlastProjectile(World world) {
/* 18 */     super(HitoDaibutsuProjectiles.IMPACT_BLAST, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImpactBlastProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImpactBlastProjectile(World world, double x, double y, double z) {
/* 28 */     super(HitoDaibutsuProjectiles.IMPACT_BLAST, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public ImpactBlastProjectile(World world, LivingEntity player) {
/* 33 */     super(HitoDaibutsuProjectiles.IMPACT_BLAST, world, player);
/* 34 */     setDamage(60.0F);
/* 35 */     setPhysical(false);
/* 36 */     setAffectedByImbuing();
/* 37 */     setMaxLife(20);
/* 38 */     setCollisionSize(5.0D);
/* 39 */     setPassThroughBlocks();
/* 40 */     setPassThroughEntities();
/*    */     
/* 42 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 47 */     if (this.ticksExisted > 0) {
/*    */       
/* 49 */       if (this.ticksExisted % 2 == 0) {
/*    */         
/* 51 */         ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, getPosX(), getPosY(), getPosZ(), 5.0F);
/* 52 */         explosion.setHeightDifference(45);
/* 53 */         explosion.setStaticDamage(40.0F * (getLife() / getMaxLife()));
/* 54 */         explosion.doExplosion();
/*    */       } 
/*    */       
/* 57 */       if (this.ticksExisted % 5 == 0)
/*    */       {
/* 59 */         PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), (getMotion()).x, (getMotion()).y, (getMotion()).z);
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\hitodaibutsu\ImpactBlastProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */