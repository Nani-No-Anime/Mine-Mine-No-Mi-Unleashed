/*    */ package xyz.pixelatedw.mineminenomi.abilities.zushi;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.IPacket;
/*    */ import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.DevilFruitHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class AbareHimatsuriAbility extends ContinuousAbility implements IParallelContinuousAbility {
/* 13 */   public static final AbareHimatsuriAbility INSTANCE = new AbareHimatsuriAbility();
/*    */   
/*    */   private boolean stateChanged = false;
/*    */ 
/*    */   
/*    */   public AbareHimatsuriAbility() {
/* 19 */     super("Abare Himatsuri", AbilityHelper.getDevilFruitCategory());
/* 20 */     setDescription("Makes the user fly using gravity on the ground below you");
/* 21 */     setMaxCooldown(15.0D);
/* 22 */     setThreshold(60.0D);
/*    */     
/* 24 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 25 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 26 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 31 */     if (!player.world.getBlockState(player.getPosition().down()).getMaterial().isSolid()) {
/* 32 */       return false;
/*    */     }
/* 34 */     if (player.isCreative() || player.isSpectator()) {
/* 35 */       return true;
/*    */     }
/* 37 */     player.abilities.allowFlying = true;
/* 38 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */     
/* 40 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int activeTime) {
/* 45 */     player.fallDistance = 0.0F;
/* 46 */     if (!AbilityHelper.canUseMomentumAbility(player)) {
/*    */       
/* 48 */       if (player.abilities.allowFlying)
/*    */       {
/* 50 */         player.abilities.allowFlying = false;
/* 51 */         player.abilities.isFlying = false;
/* 52 */         this.stateChanged = true;
/*    */       
/*    */       }
/*    */     
/*    */     }
/* 57 */     else if (!player.abilities.allowFlying) {
/*    */       
/* 59 */       player.abilities.allowFlying = true;
/* 60 */       this.stateChanged = true;
/*    */     } 
/*    */     
/* 63 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */     
/* 65 */     boolean canFly = DevilFruitHelper.isFlyingAtMaxHeight(player, 48.0D);
/* 66 */     if (player.abilities.isFlying) {
/* 67 */       DevilFruitHelper.vanillaFlightThreshold((LivingEntity)player, canFly ? 256 : ((int)player.getPosY() - 1));
/*    */     }
/* 69 */     if (player.isSprinting()) {
/* 70 */       player.setMotion(player.getMotion().mul(0.69D, 1.0D, 0.69D));
/* 71 */       player.setSprinting(false);
/* 72 */       this.stateChanged = true;
/*    */     } 
/*    */     
/* 75 */     if (this.stateChanged) {
/* 76 */       player.velocityChanged = true;
/* 77 */       this.stateChanged = false;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 83 */     if (player.isCreative() || player.isSpectator()) {
/* 84 */       return true;
/*    */     }
/* 86 */     player.abilities.allowFlying = false;
/* 87 */     player.abilities.isFlying = false;
/* 88 */     ((ServerPlayerEntity)player).connection.sendPacket((IPacket)new SPlayerAbilitiesPacket(player.abilities));
/*    */     
/* 90 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\zushi\AbareHimatsuriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */