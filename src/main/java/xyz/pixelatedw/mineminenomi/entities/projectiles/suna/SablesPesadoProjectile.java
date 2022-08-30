/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModParticleTypes;
/*    */ import xyz.pixelatedw.mineminenomi.particles.data.GenericParticleData;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.common.CommonExplosionParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class SablesPesadoProjectile extends AbilityProjectileEntity {
/*    */   public SablesPesadoProjectile(World world) {
/* 20 */     super(SunaProjectiles.SABLES_PESADO, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SablesPesadoProjectile(EntityType type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public SablesPesadoProjectile(World world, double x, double y, double z) {
/* 30 */     super(SunaProjectiles.SABLES_PESADO, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public SablesPesadoProjectile(World world, LivingEntity player) {
/* 35 */     super(SunaProjectiles.SABLES_PESADO, world, player);
/*    */     
/* 37 */     setDamage(50.0F);
/* 38 */     setMaxLife(50);
/* 39 */     setPhysical(false);
/* 40 */     setAffectedByImbuing();
/* 41 */     setArmorPiercing();
/*    */     
/* 43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 44 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 49 */     ExplosionAbility explosion = AbilityHelper.newExplosion(getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 6.0F);
/* 50 */     explosion.setStaticDamage(35.0F);
/* 51 */     explosion.setExplosionSound(true);
/* 52 */     explosion.setDamageOwner(false);
/* 53 */     explosion.setDestroyBlocks(true);
/* 54 */     explosion.setFireAfterExplosion(false);
/* 55 */     explosion.setSmokeParticles((ParticleEffect)new CommonExplosionParticleEffect(7));
/* 56 */     explosion.setDamageEntities(true);
/* 57 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 62 */     if (!this.world.isRemote) {
/*    */       int i;
/* 64 */       for (i = 0; i < 20; i++) {
/*    */         
/* 66 */         double offsetX = WyHelper.randomDouble();
/* 67 */         double offsetY = WyHelper.randomDouble();
/* 68 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 70 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA);
/* 71 */         data.setLife(6);
/* 72 */         data.setSize(1.3F);
/* 73 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */       
/* 76 */       for (i = 0; i < 2; i++) {
/*    */         
/* 78 */         double offsetX = WyHelper.randomDouble();
/* 79 */         double offsetY = WyHelper.randomDouble();
/* 80 */         double offsetZ = WyHelper.randomDouble();
/*    */         
/* 82 */         GenericParticleData data = new GenericParticleData(ModParticleTypes.SUNA2);
/* 83 */         data.setLife(4);
/* 84 */         data.setSize(1.2F);
/* 85 */         WyHelper.spawnParticles(data, (ServerWorld)this.world, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\suna\SablesPesadoProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */