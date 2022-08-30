/*    */ package xyz.pixelatedw.mineminenomi.entities.projectiles.beta;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.EntityType;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.projectiles.AbilityProjectileEntity;
/*    */ 
/*    */ public class BetaBetaChainProjectile extends AbilityProjectileEntity {
/*    */   public BetaBetaChainProjectile(World world) {
/* 17 */     super(BetaProjectiles.BETA_BETA_CHAIN, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BetaBetaChainProjectile(EntityType type, World world) {
/* 22 */     super(type, world);
/*    */   }
/*    */ 
/*    */   
/*    */   public BetaBetaChainProjectile(World world, double x, double y, double z) {
/* 27 */     super(BetaProjectiles.BETA_BETA_CHAIN, world, x, y, z);
/*    */   }
/*    */ 
/*    */   
/*    */   public BetaBetaChainProjectile(World world, LivingEntity player) {
/* 32 */     super(BetaProjectiles.BETA_BETA_CHAIN, world, player);
/*    */     
/* 34 */     setDamage(4.0F);
/* 35 */     setLife(30);
/* 36 */     setPhysical(true);
/*    */     
/* 38 */     this.onBlockImpactEvent = this::onBlockImpactEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void onBlockImpactEvent(BlockPos hit) {
/* 43 */     if (getLife() >= getMaxLife()) {
/*    */       return;
/*    */     }
/* 46 */     PlayerEntity player = (PlayerEntity)getThrower();
/*    */     
/* 48 */     double powerX = Math.abs(hit.getX() - player.getPosX());
/* 49 */     double powerY = Math.min((hit.getY() - player.getPosY()) * 2.0D, 2.0D);
/* 50 */     double powerZ = Math.abs(hit.getZ() - player.getPosZ());
/*    */     
/* 52 */     Vec3d dirVec = player.getLookVec().mul(powerX, powerY, powerZ);
/* 53 */     player.setMotion(dirVec.x * 0.25D, 0.3D + dirVec.y, dirVec.z * 0.25D);
/* 54 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\entities\projectiles\beta\BetaBetaChainProjectile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */