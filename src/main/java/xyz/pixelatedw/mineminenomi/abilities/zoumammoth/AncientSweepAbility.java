/*    */ package xyz.pixelatedw.mineminenomi.abilities.zoumammoth;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import java.util.List;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.util.DamageSource;
/*    */ import net.minecraft.util.Direction;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.MammothGuardZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.MammothHeavyZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ 
/*    */ public class AncientSweepAbility extends ChargeableAbility implements IFormRequiredAbility {
/* 23 */   public static final AncientSweepAbility INSTANCE = new AncientSweepAbility();
/*    */ 
/*    */   
/*    */   public AncientSweepAbility() {
/* 27 */     super("Ancient Sweep", AbilityHelper.getDevilFruitCategory());
/* 28 */     setDescription("Hits all enemies in a frontal cone with your trunk.");
/* 29 */     setMaxCooldown(8.0D);
/* 30 */     setMaxChargeTime(2.0D);
/*    */     
/* 32 */     this.onEndChargingEvent = this::onEndChargingEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean onEndChargingEvent(PlayerEntity player) {
/* 37 */     float damage = 15.0F;
/* 38 */     double radius = 3.0D;
/*    */     
/* 40 */     if (MammothGuardZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*    */       
/* 42 */       radius *= 2.0D;
/* 43 */       damage += 5.0F;
/*    */     } 
/*    */     
/* 46 */     Vec3d look = player.getLookVec();
/*    */     
/* 48 */     List<LivingEntity> targets = WyHelper.getEntitiesNear(player.getPosition().offset(Direction.getFacingFromVector(look.x, look.y, look.z), 2), player.world, radius, FactionHelper.getOutsideGroupPredicate((LivingEntity)player), new Class[] { LivingEntity.class });
/* 49 */     targets.remove(player);
/*    */     
/* 51 */     for (LivingEntity target : targets) {
/*    */       
/* 53 */       target.attackEntityFrom(DamageSource.causePlayerDamage(player), damage);
/* 54 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 3.0D, 3.0D);
/* 55 */       target.setMotion(speed.x, player.getMotion().getY() + 0.5D, speed.z);
/* 56 */       target.velocityChanged = true;
/*    */     } 
/*    */     
/* 59 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 61 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 67 */     return new ZoanInfo[] { (ZoanInfo)MammothGuardZoanInfo.INSTANCE, (ZoanInfo)MammothHeavyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zoumammoth\AncientSweepAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */