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
/*    */ public class AmaterasuProjectile extends AbilityProjectileEntity {
/*    */   public AmaterasuProjectile(World world) {
/* 21 */     super(PikaProjectiles.AMATERASU, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public AmaterasuProjectile(EntityType type, World world) {
/* 26 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public AmaterasuProjectile(World world, double x, double y, double z) {
/* 31 */     super(PikaProjectiles.AMATERASU, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public AmaterasuProjectile(World world, LivingEntity player) {
/* 36 */     super(PikaProjectiles.AMATERASU, world, player);
/*    */     
/* 38 */     setDamage(70.0F);
/* 39 */     setArmorPiercing();
/*    */     
/* 41 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 42 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 47 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), getDamage() / 4.0F);
/* 48 */     explosion.setStaticDamage(getDamage() * 0.75F);
/* 49 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect((int)(getDamage() / 6.0F)));
/* 50 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 55 */     if (!this.world.isRemote) {
/*    */       
/* 57 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.PIKA);
/* 58 */       data.setLife(40);
/* 59 */       data.setSize(10.0F);
/* 60 */       data.setRotation(Vector3f.YP);
/* 61 */       WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX(), getPosY(), getPosZ());
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\pika\AmaterasuProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */