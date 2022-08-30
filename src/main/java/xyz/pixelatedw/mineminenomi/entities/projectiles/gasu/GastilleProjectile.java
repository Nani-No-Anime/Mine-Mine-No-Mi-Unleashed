/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GastilleProjectile extends AbilityProjectileEntity {
/*    */   public GastilleProjectile(World world) {
/* 23 */     super(GasuProjectiles.GASTILLE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GastilleProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GastilleProjectile(World world, double x, double y, double z) {
/* 33 */     super(GasuProjectiles.GASTILLE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GastilleProjectile(World world, LivingEntity player) {
/* 38 */     super(GasuProjectiles.GASTILLE, world, player);
/*    */     
/* 40 */     setDamage(30.0F);
/* 41 */     setDamageSource(DamageSource.IN_FIRE);
/* 42 */     setPassThroughEntities();
/* 43 */     setCanGetStuckInGround();
/*    */     
/* 45 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.POISON, 200, 3) });
/*    */ 
/*    */ 
/*    */     
/* 49 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 50 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 55 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 5.0F);
/* 56 */     explosion.setStaticDamage(28.0F);
/* 57 */     explosion.setExplosionSound(true);
/* 58 */     explosion.setDamageOwner(false);
/* 59 */     explosion.setDestroyBlocks(true);
/* 60 */     explosion.setFireAfterExplosion(true);
/* 61 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/* 62 */     explosion.setDamageEntities(true);
/* 63 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 68 */     if (!this.world.isRemote)
/*    */     {
/* 70 */       for (int i = 0; i < 2; i++) {
/*    */         
/* 72 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/* 73 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/* 74 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */         
/* 76 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU2);
/* 77 */         data.setLife(5);
/* 78 */         data.setSize(0.8F);
/* 79 */         data.setColor(0.4F, 0.7F, 1.0F);
/* 80 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gasu\GastilleProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */