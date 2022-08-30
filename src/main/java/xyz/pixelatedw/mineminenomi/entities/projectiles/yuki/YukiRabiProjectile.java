/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.yuki;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class YukiRabiProjectile extends AbilityProjectileEntity {
/*    */   public YukiRabiProjectile(World world) {
/* 18 */     super(YukiProjectiles.YUKI_RABI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YukiRabiProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public YukiRabiProjectile(World world, double x, double y, double z) {
/* 28 */     super(YukiProjectiles.YUKI_RABI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public YukiRabiProjectile(World world, LivingEntity player) {
/* 33 */     super(YukiProjectiles.YUKI_RABI, world, player);
/*    */     
/* 35 */     setDamage(3.5F);
/* 36 */     setChangeHurtTime(true);
/* 37 */     setPhysical(false);
/* 38 */     setAffectedByImbuing();
/*    */     
/* 40 */     this.onTickEvent = this::onTickEvent;
/* 41 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity entity) {
/* 46 */     AbilityHelper.addFrostbite(entity, getThrower(), 15);
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 51 */     if (!this.world.isRemote)
/*    */     {
/* 53 */       for (int i = 0; i < 2; i++) {
/*    */         ParticleType<GenericParticleData> particle;
/* 55 */         double offsetX = WyHelper.randomDouble() / 5.0D;
/* 56 */         double offsetY = WyHelper.randomDouble() / 5.0D;
/* 57 */         double offsetZ = WyHelper.randomDouble() / 5.0D;
/*    */ 
/*    */         
/* 60 */         if (i % 2 == 0) {
/* 61 */           particle = ModParticleTypes.YUKI2;
/*    */         } else {
/* 63 */           particle = ModParticleTypes.YUKI;
/*    */         } 
/* 65 */         GenericParticleData data = new GenericParticleData(particle);
/* 66 */         data.setLife(20);
/* 67 */         data.setSize(1.3F);
/* 68 */         data.setMotion(0.0D, -0.02D, 0.0D);
/* 69 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + 0.25D + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\yuki\YukiRabiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */