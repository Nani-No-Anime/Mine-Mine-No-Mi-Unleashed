/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.saraaxolotl;
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
/*    */ public class PoisonSpitProjectile extends AbilityProjectileEntity {
/*    */   public PoisonSpitProjectile(World world) {
/* 18 */     super(SaraAxolotlProjectiles.POISON_SPIT, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PoisonSpitProjectile(EntityType type, World world) {
/* 23 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public PoisonSpitProjectile(World world, double x, double y, double z) {
/* 28 */     super(SaraAxolotlProjectiles.POISON_SPIT, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public PoisonSpitProjectile(World world, LivingEntity player) {
/* 33 */     super(SaraAxolotlProjectiles.POISON_SPIT, world, player);
/*    */     
/* 35 */     setDamage(8.0F);
/* 36 */     setPhysical(false);
/* 37 */     setMaxLife(10);
/*    */     
/* 39 */     this.onEntityImpactEvent = this::onEntityImpactEvent;
/* 40 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onEntityImpactEvent(LivingEntity hitEntity) {
/* 45 */     hitEntity.addPotionEffect(new EffectInstance(Effects.POISON, 200, 0));
/* 46 */     this.onBlockImpactEvent.onImpact(hitEntity.getPosition());
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 51 */     if (!this.world.isRemote)
/*    */     {
/* 53 */       for (int i = 0; i < 7; i++) {
/*    */         
/* 55 */         double offsetX = WyHelper.randomDouble() / 6.0D;
/* 56 */         double offsetY = WyHelper.randomDouble() / 6.0D;
/* 57 */         double offsetZ = WyHelper.randomDouble() / 6.0D;
/*    */         
/* 59 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.DOKU_PINK);
/* 60 */         data.setLife(5);
/* 61 */         data.setSize(0.7F);
/* 62 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\saraaxolotl\PoisonSpitProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */