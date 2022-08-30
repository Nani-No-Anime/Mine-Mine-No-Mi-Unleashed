/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.gomu;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.ParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.gomu.GearSecondParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class GomuGomuNoRocketProjectile extends AbilityProjectileEntity {
/* 18 */   private static final ParticleEffect PARTICLES = (ParticleEffect)new GearSecondParticleEffect();
/*    */   
/*    */   public boolean gear2 = false;
/*    */   
/*    */   public GomuGomuNoRocketProjectile(World world) {
/* 23 */     super(GomuProjectiles.GOMU_GOMU_NO_ROCKET, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoRocketProjectile(EntityType type, World world) {
/* 28 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoRocketProjectile(World world, double x, double y, double z) {
/* 33 */     super(GomuProjectiles.GOMU_GOMU_NO_ROCKET, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public GomuGomuNoRocketProjectile(World world, LivingEntity player) {
/* 38 */     super(GomuProjectiles.GOMU_GOMU_NO_ROCKET, world, player);
/*    */     
/* 40 */     setDamage(4.0F);
/* 41 */     setLife(24);
/* 42 */     setPhysical(true);
/*    */     
/* 44 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 49 */     if (getLife() >= getMaxLife()) {
/*    */       return;
/*    */     }
/* 52 */     PlayerEntity player = (PlayerEntity)getThrower();
/*    */     
/* 54 */     double powerX = Math.abs(hit.getX() - player.getPosX()) / 2.5D;
/* 55 */     double powerY = (hit.getY() - player.getPosY()) / 3.0D;
/* 56 */     double powerZ = Math.abs(hit.getZ() - player.getPosZ()) / 2.5D;
/*    */     
/* 58 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, powerX, powerZ);
/* 59 */     player.setMotion(speed.x, powerY, speed.z);
/* 60 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 61 */     if (this.gear2)
/*    */     {
/* 63 */       if (this.ticksExisted % 2 == 0)
/* 64 */         PARTICLES.spawn(this.world, getPosX(), getPosY(), getPosZ(), 0.0D, 0.0D, 0.0D); 
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\gomu\GomuGomuNoRocketProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */