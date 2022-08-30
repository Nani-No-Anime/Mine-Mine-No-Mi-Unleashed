/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.ExplosionAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoElephantGunProjectile extends AbilityProjectileEntity {
/*    */   public GomuGomuNoElephantGunProjectile(World world) {
/* 15 */     super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoElephantGunProjectile(EntityType type, World world) {
/* 20 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoElephantGunProjectile(World world, double x, double y, double z) {
/* 25 */     super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoElephantGunProjectile(World world, LivingEntity player) {
/* 30 */     super(GomuProjectiles.GOMU_GOMU_NO_ELEPHANT_GUN, world, player);
/*    */     
/* 32 */     setDamage(24.0F);
/* 33 */     setMaxLife(12);
/* 34 */     setPhysical(true);
/* 35 */     setCanGetStuckInGround();
/* 36 */     setPassThroughEntities();
/*    */     
/* 38 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 43 */     ExplosionAbility explosion = AbilityHelper.newExplosion((Entity)getThrower(), this.world, hit.getX(), hit.getY(), hit.getZ(), 2.0F);
/* 44 */     explosion.setStaticDamage(8.0F);
/* 45 */     explosion.setExplosionSound(true);
/* 46 */     explosion.setDamageOwner(false);
/* 47 */     explosion.setDestroyBlocks(true);
/* 48 */     explosion.setFireAfterExplosion(false);
/* 49 */     explosion.setSmokeParticles(null);
/* 50 */     explosion.setDamageEntities(false);
/* 51 */     explosion.doExplosion();
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoElephantGunProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */