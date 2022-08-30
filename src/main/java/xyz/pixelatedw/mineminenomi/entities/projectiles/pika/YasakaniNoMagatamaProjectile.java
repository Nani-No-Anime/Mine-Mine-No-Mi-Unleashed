/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.pika;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.client.renderer.Vector3f;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
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
/*    */ public class YasakaniNoMagatamaProjectile extends AbilityProjectileEntity {
/*    */   public YasakaniNoMagatamaProjectile(World world) {
/* 21 */     super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YasakaniNoMagatamaProjectile(EntityType type, World world) {
/* 26 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YasakaniNoMagatamaProjectile(World world, double x, double y, double z) {
/* 31 */     super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public YasakaniNoMagatamaProjectile(World world, LivingEntity player) {
/* 36 */     super(PikaProjectiles.YASAKANI_NO_MAGATAMA, world, player);
/*    */     
/* 38 */     setDamage(4.0F);
/* 39 */     setChangeHurtTime(true);
/*    */     
/* 41 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 42 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 47 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 3.0F);
/* 48 */     explosion.setStaticDamage(4.0F);
/* 49 */     explosion.disableExplosionKnockback();
/* 50 */     explosion.setAlwaysDamage(true);
/* 51 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(3));
/* 52 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 57 */     if (!this.world.isRemote) {
/*    */       
/* 59 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.PIKA);
/* 60 */       data.setLife(30);
/* 61 */       data.setSize(2.5F);
/* 62 */       data.setRotation(Vector3f.YP);
/* 63 */       WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX(), getPosY(), getPosZ());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pika\YasakaniNoMagatamaProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */