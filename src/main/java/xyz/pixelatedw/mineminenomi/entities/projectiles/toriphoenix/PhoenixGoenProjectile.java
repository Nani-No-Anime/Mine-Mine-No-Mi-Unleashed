/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.toriphoenix;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class PhoenixGoenProjectile extends AbilityProjectileEntity {
/*    */   private Vec3d lookVec;
/*    */   
/*    */   public PhoenixGoenProjectile(World world) {
/* 19 */     super(ToriPhoenixProjectiles.PHOENIX_GOEN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PhoenixGoenProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PhoenixGoenProjectile(World world, double x, double y, double z) {
/* 29 */     super(ToriPhoenixProjectiles.PHOENIX_GOEN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public PhoenixGoenProjectile(World world, LivingEntity player, Vec3d lookVec) {
/* 34 */     super(ToriPhoenixProjectiles.PHOENIX_GOEN, world, player);
/*    */     
/* 36 */     setDamage(10.0F);
/* 37 */     setCanGetStuckInGround();
/* 38 */     setMaxLife(30);
/* 39 */     setChangeHurtTime(true);
/*    */     
/* 41 */     this.lookVec = lookVec;
/* 42 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 43 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity target) {
/* 48 */     Vec3d speed = this.lookVec.mul(1.5D, 0.0D, 1.5D);
/* 49 */     target.setMotion(speed.x, 0.15D, speed.z);
/* 50 */     target.velocityChanged = true;
/* 51 */     target.fallDistance = 0.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 56 */     float mult = getLife() / getMaxLife() * 1.25F;
/* 57 */     for (int i = 0; i < 25.0F * mult; i++) {
/*    */       
/* 59 */       double offsetX = WyHelper.randomDouble() * mult;
/* 60 */       double offsetY = WyHelper.randomDouble() * mult;
/* 61 */       double offsetZ = WyHelper.randomDouble() * mult;
/*    */       
/* 63 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.BLUE_FLAME);
/* 64 */       data.setLife(8);
/* 65 */       data.setSize(3.0F * mult);
/* 66 */       WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\toriphoenix\PhoenixGoenProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */