/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gasu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.Objects;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gasu.ShinokuniAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ 
/*    */ public class BigGastilleProjectile extends AbilityProjectileEntity {
/*    */   public BigGastilleProjectile(World world) {
/* 26 */     super(GasuProjectiles.BIG_GASTILLE, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BigGastilleProjectile(EntityType type, World world) {
/* 31 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BigGastilleProjectile(World world, double x, double y, double z) {
/* 36 */     super(GasuProjectiles.BIG_GASTILLE, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BigGastilleProjectile(World world, LivingEntity player) {
/* 41 */     super(GasuProjectiles.BIG_GASTILLE, world, player);
/*    */     
/* 43 */     setDamage(70.0F);
/* 44 */     setDamageSource(DamageSource.IN_FIRE);
/*    */     
/* 46 */     this.onEntityImpactEvent = this::onEntityImpact;
/* 47 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 48 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpact(LivingEntity entity) {
/* 53 */     ((ShinokuniAbility)AbilityDataCapability.get(Objects.<LivingEntity>requireNonNull(getThrower())).getEquippedAbility((Ability)ShinokuniAbility.INSTANCE)).applyEffects((PlayerEntity)getThrower(), entity);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 58 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 8.0F);
/* 59 */     explosion.setStaticDamage(28.0F);
/* 60 */     explosion.setExplosionSound(true);
/* 61 */     explosion.setDamageOwner(false);
/* 62 */     explosion.setDestroyBlocks(true);
/* 63 */     explosion.setFireAfterExplosion(true);
/* 64 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(5));
/* 65 */     explosion.setDamageEntities(true);
/* 66 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 71 */     if (!this.world.isRemote)
/*    */     {
/* 73 */       for (int i = 0; i < 2; i++) {
/*    */         
/* 75 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/* 76 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/* 77 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */         
/* 79 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.GASU2);
/* 80 */         data.setLife(5);
/* 81 */         data.setSize(0.8F);
/* 82 */         data.setColor(0.4F, 0.7F, 1.0F);
/* 83 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gasu\BigGastilleProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */