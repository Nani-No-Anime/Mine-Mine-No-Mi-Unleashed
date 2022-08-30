/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BarjanProjectile extends AbilityProjectileEntity {
/*    */   public BarjanProjectile(World world) {
/* 18 */     super(SunaProjectiles.BARJAN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarjanProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarjanProjectile(World world, double x, double y, double z) {
/* 28 */     super(SunaProjectiles.BARJAN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BarjanProjectile(World world, LivingEntity player) {
/* 33 */     super(SunaProjectiles.BARJAN, world, player);
/*    */     
/* 35 */     setDamage(30.0F);
/* 36 */     setMaxLife(15);
/* 37 */     setPhysical(false);
/* 38 */     setAffectedByImbuing();
/* 39 */     setCanGetStuckInGround();
/* 40 */     setPassThroughEntities();
/*    */     
/* 42 */     this.withEffects = (() -> new EffectInstance[] { new EffectInstance(Effects.WITHER, 100, 3, false, false), new EffectInstance(Effects.HUNGER, 150, 4, false, false), new EffectInstance(Effects.WEAKNESS, 150, 0, false, false), new EffectInstance(Effects.SLOWNESS, 150, 0, false, false), new EffectInstance(Effects.MINING_FATIGUE, 150, 0, false, false) });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 49 */     this.onEntityImpactEvent = this::onEntityImpact;
/* 50 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onEntityImpact(LivingEntity entity) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 62 */     for (int i = 0; i < 5; i++) {
/*    */       
/* 64 */       double offsetX = WyHelper.randomDouble();
/* 65 */       double offsetY = WyHelper.randomDouble() / 4.0D;
/* 66 */       double offsetZ = WyHelper.randomDouble();
/*    */       
/* 68 */       GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
/* 69 */       data.setLife(4);
/* 70 */       data.setSize(1.4F);
/* 71 */       WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\BarjanProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */