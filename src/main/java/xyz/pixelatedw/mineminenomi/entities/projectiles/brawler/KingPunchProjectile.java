/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.brawler;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class KingPunchProjectile extends AbilityProjectileEntity {
/*    */   public KingPunchProjectile(World world) {
/* 15 */     super(BrawlerProjectiles.KING_PUNCH, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KingPunchProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public KingPunchProjectile(World world, double x, double y, double z) {
/* 25 */     super(BrawlerProjectiles.KING_PUNCH, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public KingPunchProjectile(World world, LivingEntity player) {
/* 30 */     super(BrawlerProjectiles.KING_PUNCH, world, player);
/*    */     
/* 32 */     setPhysical(true);
/* 33 */     setCollisionSize(4.0D);
/* 34 */     setMaxLife(30);
/* 35 */     setPassThroughEntities();
/* 36 */     setCanGetStuckInGround();
/* 37 */     setDamageSource(this.bypassingSource);
/*    */     
/* 39 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 44 */     if (!this.world.isRemote)
/*    */     {
/* 46 */       for (int i = 0; i < 20; i++) {
/*    */         
/* 48 */         double offsetX = WyHelper.randomDouble() / 1.25D;
/* 49 */         double offsetY = WyHelper.randomDouble() / 1.25D;
/* 50 */         double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */         
/* 52 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.POOF, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 53 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.EXPLOSION_EMITTER, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\brawler\KingPunchProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */