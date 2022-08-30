/*    */ package xyz.pixelatedw.mineminenomi.abilities.suna;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SEntityVelocityPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.math.AxisAlignedBB;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IMultiTargetAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.particles.effects.suna.SablesParticleEffect;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class GrandeSablesAbility extends ContinuousAbility implements IMultiTargetAbility {
/* 20 */   public static final GrandeSablesAbility INSTANCE = new GrandeSablesAbility();
/*    */   
/* 22 */   private static final SablesParticleEffect PARTICLES = new SablesParticleEffect();
/*    */   
/* 24 */   private Vec3d look = null;
/*    */ 
/*    */   
/*    */   public GrandeSablesAbility() {
/* 28 */     super("Grande Sables", AbilityHelper.getDevilFruitCategory());
/* 29 */     setDescription("Surrounds the user into a sand tornado, increasing their speed and pulling all nearby entities towards it.");
/* 30 */     setMaxCooldown(10.0D);
/* 31 */     setThreshold(2.5D);
/* 32 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 33 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 38 */     if (i < 3) {
/*    */       return;
/*    */     }
/* 41 */     Vec3d speed = player.getLook(1.0F).mul(1.2D, 1.2D, 1.2D);
/* 42 */     player.setMotion(speed.x, speed.y, speed.z);
/*    */     
/* 44 */     PARTICLES.mult = 0.8F;
/* 45 */     PARTICLES.spawn(player.world, player.getPosX(), player.getPosY(), player.getPosZ(), (player.getMotion()).x, (player.getMotion()).y, (player.getMotion()).z);
/* 46 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/*    */     
/* 48 */     float growthXZ = 8.0F;
/* 49 */     float growthY = 20.0F;
/*    */     
/* 51 */     AxisAlignedBB box = (new AxisAlignedBB(new BlockPos(player.getPositionVec()))).grow(growthXZ, growthY, growthXZ);
/* 52 */     for (Entity entity : player.world.getEntitiesWithinAABB(Entity.class, box, e -> (e != player))) {
/*    */       
/* 54 */       entity.setMotion((entity.getMotion()).x + (player.getPosX() - entity.getPosX()) / 20.0D, 
/* 55 */           (entity.getMotion()).y + (player.getPosY() - entity.getPosY()) / 20.0D, 
/* 56 */           (entity.getMotion()).z + (player.getPosZ() - entity.getPosZ()) / 20.0D);
/* 57 */       entity.velocityChanged = true;
/*    */       
/* 59 */       if (player.getDistance(entity) < 2.0F) {
/* 60 */         entity.attackEntityFrom(DamageSource.FLY_INTO_WALL, 8.0F);
/*    */       }
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 66 */     if (!AbilityHelper.canUseMomentumAbility(player) || player.isWet()) {
/* 67 */       return false;
/*    */     }
/* 69 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/* 70 */     clearTargets();
/*    */     
/* 72 */     Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 73 */     player.setMotion(speed.x, 2.0D, speed.z);
/*    */     
/* 75 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SEntityVelocityPacket((Entity)player));
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\suna\GrandeSablesAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */