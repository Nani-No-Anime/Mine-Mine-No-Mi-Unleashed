/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.rokushiki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.particles.IParticleData;
/*    */ import net.minecraft.particles.ParticleTypes;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class RokuoganProjectile extends AbilityProjectileEntity {
/* 16 */   private float damage = 60.0F;
/*    */ 
/*    */   
/*    */   public RokuoganProjectile(World world) {
/* 20 */     super(RokushikiProjectiles.ROKUOGAN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RokuoganProjectile(EntityType type, World world) {
/* 25 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public RokuoganProjectile(World world, double x, double y, double z) {
/* 30 */     super(RokushikiProjectiles.ROKUOGAN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public RokuoganProjectile(World world, LivingEntity entity) {
/* 35 */     super(RokushikiProjectiles.ROKUOGAN, world, entity);
/*    */     
/* 37 */     setMaxLife(5);
/* 38 */     setDamage(60.0F);
/* 39 */     setCanGetStuckInGround();
/* 40 */     setPassThroughEntities();
/* 41 */     setPhysical(false);
/*    */     
/* 43 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/* 44 */     this.onTickEvent = this::onTickEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 49 */     ExplosionAbility explosion = AbilityHelper.newExplosion(this, this.world, getPosX(), getPosY(), getPosZ(), this.damage / 7.0F);
/* 50 */     explosion.setExplosionSound(true);
/* 51 */     explosion.setDamageOwner(false);
/* 52 */     explosion.setDestroyBlocks(true);
/* 53 */     explosion.setFireAfterExplosion(false);
/* 54 */     explosion.setDamageEntities(false);
/* 55 */     explosion.doExplosion();
/*    */   }
/*    */ 
/*    */   
/*    */   private void onTickEvent() {
/* 60 */     if (!this.world.isRemote)
/*    */     {
/* 62 */       for (int i = 0; i < 20; i++) {
/*    */         
/* 64 */         double offsetX = WyHelper.randomDouble() / 1.25D;
/* 65 */         double offsetY = WyHelper.randomDouble() / 1.25D;
/* 66 */         double offsetZ = WyHelper.randomDouble() / 1.25D;
/*    */         
/* 68 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.POOF, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, -0.1D);
/* 69 */         ((ServerWorld)this.world).spawnParticle((IParticleData)ParticleTypes.EXPLOSION_EMITTER, getPosX() + offsetX, getPosY() + offsetY, getPosZ() + offsetZ, 1, 0.0D, 0.0D, 0.0D, 0.1D);
/*    */       } 
/*    */     }
/*    */     
/* 73 */     if (this.ticksExisted % 2 == 0) {
/* 74 */       reduceDamage();
/*    */     }
/*    */   }
/*    */   
/*    */   private void reduceDamage() {
/* 79 */     this.damage -= 10.0F;
/* 80 */     setDamage(this.damage);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\rokushiki\RokuoganProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */