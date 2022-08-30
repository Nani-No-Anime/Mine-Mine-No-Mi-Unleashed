/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.sniper;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.EntityCloud;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.sniper.KemuriBoshiParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class KemuriBoshiProjectile extends AbilityProjectileEntity {
/* 17 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new KemuriBoshiParticleEffect();
/*    */ 
/*    */   
/*    */   public KemuriBoshiProjectile(World world) {
/* 21 */     super(SniperProjectiles.KEMURI_BOSHI, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KemuriBoshiProjectile(EntityType type, World world) {
/* 26 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KemuriBoshiProjectile(World world, double x, double y, double z) {
/* 31 */     super(SniperProjectiles.KEMURI_BOSHI, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public KemuriBoshiProjectile(World world, LivingEntity player) {
/* 36 */     super(SniperProjectiles.KEMURI_BOSHI, world, player);
/*    */     
/* 38 */     setDamage(3.0F);
/* 39 */     setPhysical(false);
/* 40 */     setAffectedByImbuing();
/*    */     
/* 42 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 47 */     KemuriBoshiCloudEntity smokeCloud = new KemuriBoshiCloudEntity(this.world);
/* 48 */     smokeCloud.setLife(100);
/* 49 */     smokeCloud.setLocationAndAngles(getPosX(), getPosY() + 1.0D, getPosZ(), 0.0F, 0.0F);
/* 50 */     smokeCloud.setMotion(0.0D, 0.0D, 0.0D);
/* 51 */     smokeCloud.setThrower(getThrower());
/* 52 */     this.world.addEntity((Entity)smokeCloud);
/*    */   }
/*    */   
/*    */   public static class KemuriBoshiCloudEntity
/*    */     extends EntityCloud
/*    */   {
/*    */     public KemuriBoshiCloudEntity(World world) {
/* 59 */       super(world);
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public void tick() {
/* 65 */       super.tick();
/* 66 */       if (!this.world.isRemote) {
/*    */         
/* 68 */         for (LivingEntity target : WyHelper.<LivingEntity>getEntitiesNear(getPosition(), this.world, 6.0D)) {
/*    */           
/* 70 */           if (getThrower() != target && !target.isPotionActive(Effects.POISON)) {
/* 71 */             target.addPotionEffect(new EffectInstance(Effects.POISON, 80, 1));
/*    */           }
/*    */         } 
/* 74 */         if (this.ticksExisted % 2 == 0)
/* 75 */           KemuriBoshiProjectile.PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\sniper\KemuriBoshiProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */