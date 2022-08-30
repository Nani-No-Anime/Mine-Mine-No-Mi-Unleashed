/*    */ package xyz.pixelatedw.mineminenomi.abilities.ito;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SAnimateHandPacket;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFallDamageBlockingAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class SoraNoMichiAbility extends Ability implements IFallDamageBlockingAbility {
/* 18 */   public static final SoraNoMichiAbility INSTANCE = new SoraNoMichiAbility();
/*    */   
/* 20 */   public int airJumps = 0;
/*    */   
/*    */   private boolean hasFallDamage = true;
/*    */   
/*    */   public SoraNoMichiAbility() {
/* 25 */     super("Sora no Michi", AbilityHelper.getDevilFruitCategory());
/* 26 */     addInPool(new AbilityPool[] { AbilityPool.GEPPO_LIKE });
/* 27 */     setMaxCooldown(1.0D);
/* 28 */     setDescription("The user attaches the strings to clouds, allowing them to move through the air");
/*    */     
/* 30 */     this.onUseEvent = this::onUseEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onUseEvent(PlayerEntity player) {
/* 35 */     if (!AbilityHelper.canUseMomentumAbility(player) || player.getPosY() > player.world.getWorldInfo().getGenerator().getCloudHeight()) {
/* 36 */       return false;
/*    */     }
/* 38 */     ((ServerWorld)player.world).getChunkProvider().sendToTrackingAndSelf((Entity)player, (IPacket)new SAnimateHandPacket((Entity)player, 0));
/*    */     
/* 40 */     if (player.onGround) {
/*    */       
/* 42 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 1.1D, 1.1D);
/* 43 */       player.setMotion(speed.x, 2.4D, speed.z);
/*    */     }
/*    */     else {
/*    */       
/* 47 */       Vec3d speed = WyHelper.propulsion((LivingEntity)player, 2.5D, 2.5D);
/* 48 */       player.setMotion(speed.x, 0.8D, speed.z);
/*    */     } 
/*    */     
/* 51 */     AbilityHelper.setAirJumps(player, this.airJumps + 1);
/* 52 */     player.velocityChanged = true;
/* 53 */     this.hasFallDamage = false;
/*    */     
/* 55 */     if (this.airJumps >= 12) {
/*    */       
/* 57 */       setMaxCooldown(this.airJumps);
/* 58 */       startCooldown(player);
/* 59 */       AbilityHelper.setAirJumps(player, 0);
/* 60 */       return true;
/*    */     } 
/*    */     
/* 63 */     setMaxCooldown(1.0D);
/* 64 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void resetFallDamage(LivingEntity player) {
/* 70 */     if (this.airJumps > 0) {
/*    */       
/* 72 */       setMaxCooldown((this.airJumps / 2));
/* 73 */       startCooldown((PlayerEntity)player);
/* 74 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)player, this), player);
/* 75 */       checkAbilityPool((PlayerEntity)player, Ability.State.COOLDOWN);
/*    */     } 
/*    */     
/* 78 */     this.hasFallDamage = true;
/* 79 */     AbilityHelper.setAirJumps((PlayerEntity)player, 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasFallDamage() {
/* 85 */     return this.hasFallDamage;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ito\SoraNoMichiAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */