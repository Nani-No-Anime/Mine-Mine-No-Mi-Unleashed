/*    */ package xyz.pixelatedw.mineminenomi.abilities.beta;
/*    */ import java.awt.Color;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.beta.BetaBetaChainProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchTriggerAbility;
/*    */ 
/*    */ public class BetaBetaChainAbility extends PunchTriggerAbility {
/* 17 */   public static final BetaBetaChainAbility INSTANCE = new BetaBetaChainAbility();
/*    */   
/* 19 */   private LightningEntity bolt = null;
/*    */ 
/*    */   
/*    */   public BetaBetaChainAbility() {
/* 23 */     super("Beta Beta Chain", AbilityHelper.getDevilFruitCategory());
/* 24 */     setMaxCooldown(6.0D);
/* 25 */     setThreshold(1.0D);
/* 26 */     setDescription("The user shoots a mucus chain which will propel the user towards where it hit, or grab an entity towards the user");
/*    */     
/* 28 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 29 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 34 */     BlockRayTraceResult trace = WyHelper.rayTraceBlocks((Entity)player, 1.0D);
/* 35 */     if (this.bolt == null) {
/*    */       
/* 37 */       LightningEntity bolt = new LightningEntity((Entity)player, (trace.getHitVec()).x, (trace.getHitVec()).y - (player.getEyeHeight() / 3.0F), (trace.getHitVec()).z, player.rotationYaw, player.rotationPitch, 90.0F, 3.0F);
/*    */       
/* 39 */       bolt.setColor(new Color(55, 135, 0, 50));
/* 40 */       bolt.disableEnergyEffect();
/* 41 */       bolt.setAliveTicks(getThreshold() * 2);
/* 42 */       bolt.setDamage(0.0F);
/* 43 */       bolt.setSize(0.1F);
/* 44 */       bolt.setBranches(1);
/* 45 */       bolt.setSegments(1);
/* 46 */       bolt.disableLightningMimic();
/* 47 */       this.bolt = bolt;
/* 48 */       player.world.addEntity((Entity)bolt);
/*    */     }
/*    */     else {
/*    */       
/* 52 */       Direction dir = Direction.fromAngle(player.rotationYaw);
/* 53 */       Vec3d hitVec = trace.getHitVec().add(dir.getXOffset(), dir.getYOffset(), dir.getZOffset());
/* 54 */       this.bolt.setLocationAndAngles(hitVec.x, hitVec.y, hitVec.z, player.rotationYaw, player.rotationPitch);
/*    */     } 
/*    */     
/* 57 */     AbilityHelper.slowEntityFall((LivingEntity)player, 2);
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 62 */     if (this.bolt != null) {
/*    */       
/* 64 */       this.bolt.remove();
/* 65 */       this.bolt = null;
/*    */     } 
/*    */     
/* 68 */     AbilityHelper.slowEntityFall((LivingEntity)player, 10);
/* 69 */     BetaBetaChainProjectile projectile = new BetaBetaChainProjectile(player.world, (LivingEntity)player);
/* 70 */     player.world.addEntity((Entity)projectile);
/* 71 */     projectile.setMotion(0.0D, 0.0D, 0.0D);
/* 72 */     projectile.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 4.0F, 0.0F);
/*    */     
/* 74 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\beta\BetaBetaChainAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */