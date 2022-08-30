/*    */ package xyz.pixelatedw.mineminenomi.wypi.abilities;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ 
/*    */ public class PunchTriggerAbility
/*    */   extends ContinuousAbility
/*    */ {
/*    */   protected IOnHit onSwingEvent = player -> true;
/*    */   public boolean released = false;
/*    */   private boolean shouldStopAfterUsing = false;
/*    */   
/*    */   public PunchTriggerAbility(String name, APIConfig.AbilityCategory category) {
/* 17 */     super(name, category);
/*    */   }
/*    */ 
/*    */   
/*    */   public void startContinuity(PlayerEntity player) {
/* 22 */     this.released = false;
/* 23 */     super.startContinuity(player);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void tick(PlayerEntity player) {
/* 29 */     if (isOnCooldown() || !isContinuous()) {
/* 30 */       this.released = false;
/*    */     }
/* 32 */     if (!canUse(player)) {
/*    */       
/* 34 */       endContinuity(player);
/*    */       
/*    */       return;
/*    */     } 
/* 38 */     player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
/*    */     
/* 40 */     if (isContinuous()) {
/*    */       
/* 42 */       this.continueTime++;
/* 43 */       if ((isClientSide() || !player.world.isRemote) && !isStateForced()) {
/* 44 */         this.duringContinuityEvent.duringContinuity(player, this.continueTime);
/*    */       }
/* 46 */       if (player.isSwingInProgress && !this.released) {
/*    */         
/* 48 */         this.released = true;
/* 49 */         this.onSwingEvent.onHitEntity(player);
/* 50 */         if (this.shouldStopAfterUsing) {
/* 51 */           endContinuity(player);
/*    */         }
/*    */       } 
/* 54 */       if (this.released && !player.isSwingInProgress && !this.shouldStopAfterUsing) {
/* 55 */         this.released = false;
/*    */       }
/* 57 */       if (this.threshold > 0 && this.continueTime >= this.threshold) {
/* 58 */         endContinuity(player);
/*    */       }
/*    */     } 
/* 61 */     player.world.getProfiler().endSection();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void stopAfterUsage(boolean value) {
/* 71 */     this.shouldStopAfterUsing = value;
/*    */   }
/*    */   
/*    */   public static interface IOnHit extends Serializable {
/*    */     boolean onHitEntity(PlayerEntity param1PlayerEntity);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\PunchTriggerAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */