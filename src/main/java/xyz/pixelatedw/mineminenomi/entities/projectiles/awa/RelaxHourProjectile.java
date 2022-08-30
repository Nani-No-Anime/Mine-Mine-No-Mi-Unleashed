/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.awa;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleType;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class RelaxHourProjectile extends AbilityProjectileEntity {
/*    */   public RelaxHourProjectile(World world) {
/* 19 */     super(AwaProjectiles.RELAX_HOUR, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RelaxHourProjectile(EntityType type, World world) {
/* 24 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RelaxHourProjectile(World world, double x, double y, double z) {
/* 29 */     super(AwaProjectiles.RELAX_HOUR, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public RelaxHourProjectile(World world, LivingEntity player) {
/* 34 */     super(AwaProjectiles.RELAX_HOUR, world, player);
/* 35 */     setDamage(3.0F);
/* 36 */     setMaxLife(40);
/* 37 */     setPhysical(false);
/* 38 */     setAffectedByImbuing();
/* 39 */     setChangeHurtTime(true);
/*    */     
/* 41 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 42 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 47 */     hitEntity.addPotionEffect(new EffectInstance(ModEffects.WASHED, 200, 0));
/* 48 */     this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 53 */     if (!this.world.isRemote) {
/*    */       int i;
/* 55 */       for (i = 0; i < 15; i++) {
/*    */         
/* 57 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 58 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 59 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 61 */         ParticleType<GenericParticleData> particle = ModParticleTypes.AWA;
/* 62 */         if (i % 3 == 0) {
/* 63 */           particle = ModParticleTypes.AWA3;
/*    */         }
/* 65 */         GenericParticleData data = new GenericParticleData(particle);
/* 66 */         data.setLife(7);
/* 67 */         data.setSize(1.3F);
/* 68 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */       
/* 71 */       for (i = 0; i < 5; i++) {
/*    */         
/* 73 */         double offsetX = WyHelper.randomDouble() / 2.0D;
/* 74 */         double offsetY = WyHelper.randomDouble() / 2.0D;
/* 75 */         double offsetZ = WyHelper.randomDouble() / 2.0D;
/*    */         
/* 77 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.AWA_FOAM);
/* 78 */         data.setLife(7);
/* 79 */         data.setSize(1.3F);
/* 80 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\awa\RelaxHourProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */