/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.artofweather;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GustSwordProjectile extends AbilityProjectileEntity {
/*    */   public GustSwordProjectile(World world) {
/* 18 */     super(ArtOfWeatherProjectiles.GUST_SWORD, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GustSwordProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GustSwordProjectile(World world, double x, double y, double z) {
/* 28 */     super(ArtOfWeatherProjectiles.GUST_SWORD, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GustSwordProjectile(World world, LivingEntity player) {
/* 33 */     super(ArtOfWeatherProjectiles.GUST_SWORD, world, player);
/*    */     
/* 35 */     setDamage(2.0F);
/* 36 */     setPhysical(false);
/*    */     
/* 38 */     this.onTickEvent = this::onTickEvent;
/* 39 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 44 */     Vec3d speed = WyHelper.propulsion(getThrower(), 4.0D, 4.0D);
/*    */     
/* 46 */     hitEntity.attackEntityFrom(this.source, 15.0F);
/* 47 */     hitEntity.setMotion(speed.x, 0.2D, speed.z);
/* 48 */     hitEntity.velocityChanged = true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 53 */     if (!this.world.isRemote)
/*    */     {
/* 55 */       for (int i = 0; i < 2; i++) {
/*    */         ParticleType<GenericParticleData> particle;
/* 57 */         double offsetX = WyHelper.randomDouble() / 3.0D;
/* 58 */         double offsetY = WyHelper.randomDouble() / 3.0D;
/* 59 */         double offsetZ = WyHelper.randomDouble() / 3.0D;
/*    */ 
/*    */         
/* 62 */         if (i % 2 == 0) {
/* 63 */           particle = ModParticleTypes.MOKU;
/*    */         } else {
/* 65 */           particle = ModParticleTypes.MOKU2;
/*    */         } 
/* 67 */         GenericParticleData data = new GenericParticleData(particle);
/* 68 */         data.setLife(10);
/* 69 */         data.setSize(1.5F);
/* 70 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\artofweather\GustSwordProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */