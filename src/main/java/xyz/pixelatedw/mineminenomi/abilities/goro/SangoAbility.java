/*    */ package xyz.pixelatedw.mineminenomi.abilities.goro;
/*    */ import java.awt.Color;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.util.math.BlockRayTraceResult;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.goro.LightningEntity;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.VoltAmaruZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.RepeaterAbility;
/*    */ 
/*    */ public class SangoAbility extends RepeaterAbility {
/* 18 */   public static final Ability INSTANCE = (Ability)new SangoAbility();
/*    */ 
/*    */   
/*    */   public SangoAbility() {
/* 22 */     super("Sango", AbilityHelper.getDevilFruitCategory());
/* 23 */     setMaxCooldown(15.0D);
/* 24 */     setMaxRepeaterCount(3, 1);
/* 25 */     setDescription("Launches powerful charges of electricity from the hands");
/*    */     
/* 27 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 32 */     BlockRayTraceResult blockRayTraceResult = WyHelper.rayTraceBlocks((Entity)player, 384.0D);
/* 33 */     double beamDistance = Math.sqrt(player.getDistanceSq((blockRayTraceResult.getHitVec()).x, (blockRayTraceResult.getHitVec()).y, (blockRayTraceResult.getHitVec()).z));
/*    */     
/* 35 */     float multiplier = 1.0F;
/* 36 */     if (VoltAmaruZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/* 37 */       multiplier = (float)(multiplier * 1.5D);
/*    */     }
/* 39 */     float damage = 20.0F * multiplier;
/* 40 */     float size = 0.28F * multiplier;
/* 41 */     float length = 50.0F * multiplier;
/*    */     
/* 43 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/* 44 */     LightningEntity bolt = new LightningEntity((Entity)player, length + (float)beamDistance, 20.0F);
/* 45 */     bolt.setAliveTicks(30);
/* 46 */     bolt.setDamage(damage);
/* 47 */     bolt.setExplosion((int)(3.0F * multiplier), true, 0.3F, 10);
/* 48 */     bolt.setSize(size);
/* 49 */     bolt.setBoxSizeDivision(0.22499999403953552D);
/* 50 */     bolt.setAngle(100);
/* 51 */     bolt.setTargetTimeToReset(60);
/* 52 */     bolt.disableExplosionKnockback();
/*    */     
/* 54 */     bolt.setBranches((int)(5.0D + beamDistance / 100.0D));
/* 55 */     int segments = (int)(beamDistance * 0.5D);
/* 56 */     bolt.setSegments((int)(segments + WyHelper.randomWithRange(-segments / 4, segments / 4)));
/* 57 */     player.world.addEntity((Entity)bolt);
/*    */     
/* 59 */     if (damage > 14.0F) {
/*    */       
/* 61 */       long seed = bolt.seed;
/* 62 */       bolt = new LightningEntity((Entity)player, length + (float)beamDistance, 20.0F);
/* 63 */       bolt.seed = seed;
/* 64 */       bolt.setAliveTicks(25);
/* 65 */       bolt.setDamage(0.0F);
/* 66 */       bolt.setExplosion(0, false);
/* 67 */       bolt.setSize(size * 0.9F);
/* 68 */       bolt.setBoxSizeDivision(0.22499999403953552D);
/* 69 */       bolt.setColor(new Color(255, 255, 255, 10));
/* 70 */       bolt.setAngle(100);
/*    */       
/* 72 */       bolt.setBranches((int)(5.0D + beamDistance / 100.0D));
/* 73 */       bolt.setSegments((int)(segments + WyHelper.randomWithRange(-segments / 4, segments / 4)));
/* 74 */       player.world.addEntity((Entity)bolt);
/*    */     } 
/* 76 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\goro\SangoAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */