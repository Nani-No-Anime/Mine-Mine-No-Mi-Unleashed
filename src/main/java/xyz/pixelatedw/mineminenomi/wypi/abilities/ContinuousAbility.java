/*     */ package xyz.pixelatedw.mineminenomi.wypi.abilities;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.invoke.SerializedLambda;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraftforge.common.MinecraftForge;
/*     */ import net.minecraftforge.eventbus.api.Event;
/*     */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SAnimeScreamPacket;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.events.AbilityUseEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public abstract class ContinuousAbility
/*     */   extends Ability
/*     */ {
/*  22 */   protected int threshold = 0;
/*  23 */   protected int continueTime = 0;
/*     */   protected IOnStartContinuity onStartContinuityEvent = player -> true;
/*     */   protected IOnEndContinuity onEndContinuityEvent = player -> true;
/*     */   protected IDuringContinuity duringContinuityEvent = (player, continuousTime) -> {
/*     */     
/*     */     };
/*     */   protected IOnStopContinuity onStopContinuityEvent = player -> {
/*     */     
/*     */     };
/*     */   
/*     */   public ContinuousAbility(String name, APIConfig.AbilityCategory category) {
/*  34 */     super(name, category);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void use(PlayerEntity player) {
/*  44 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/*  47 */     if (isOnCooldown() && getCooldown() <= 10.0D) {
/*  48 */       stopCooldown(player);
/*     */     }
/*  50 */     AbilityUseEvent event = new AbilityUseEvent(player, this);
/*  51 */     if (MinecraftForge.EVENT_BUS.post(event)) {
/*     */       return;
/*     */     }
/*  54 */     if (!isContinuous()) {
/*     */       
/*  56 */       if (!isOnStandby()) {
/*     */         return;
/*     */       }
/*  59 */       if (this.onStartContinuityEvent.onStartContinuity(player))
/*     */       {
/*  61 */         checkAbilityPool(player, Ability.State.CONTINUOUS);
/*     */         
/*  63 */         if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue() && !(this instanceof PunchAbility)) {
/*  64 */           WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.getEntityId(), getDisplayName()), (LivingEntity)player);
/*     */         }
/*  66 */         startContinuity(player);
/*     */ 
/*     */         
/*  69 */         if (this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility || this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility || this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility)
/*  70 */           WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player); 
/*  71 */         WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
/*     */       
/*     */       }
/*     */     
/*     */     }
/*  76 */     else if (!isStateForced()) {
/*  77 */       endContinuity(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThreshold(double threshold) {
/*  88 */     this.threshold = (int)(threshold * 20.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setThresholdInTicks(int threshold) {
/*  93 */     this.threshold = threshold;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getThreshold() {
/*  98 */     return this.threshold;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setContinueTime(int time) {
/* 103 */     this.continueTime = time * 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getContinueTime() {
/* 108 */     return this.continueTime;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(PlayerEntity player) {
/* 121 */     if (!canUse(player)) {
/*     */       
/* 123 */       stopContinuity(player);
/*     */       
/*     */       return;
/*     */     } 
/* 127 */     player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
/*     */     
/* 129 */     if (isContinuous()) {
/*     */       
/* 131 */       this.continueTime++;
/*     */       
/* 133 */       if ((isClientSide() || !player.world.isRemote) && !isStateForced()) {
/* 134 */         this.duringContinuityEvent.duringContinuity(player, this.continueTime);
/*     */       }
/* 136 */       if (this.threshold > 0 && this.continueTime >= this.threshold) {
/* 137 */         endContinuity(player);
/*     */       }
/*     */     } 
/* 140 */     player.world.getProfiler().endSection();
/*     */   }
/*     */ 
/*     */   
/*     */   public void startContinuity(PlayerEntity player) {
/* 145 */     setState(Ability.State.CONTINUOUS);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endContinuity(PlayerEntity player) {
/* 154 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/* 157 */     if (this.onEndContinuityEvent.onEndContinuity(player))
/*     */     {
/* 159 */       stopContinuity(player);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopContinuity(PlayerEntity player) {
/* 169 */     checkAbilityPool(player, Ability.State.COOLDOWN);
/* 170 */     this.continueTime = 0;
/* 171 */     startCooldown(player);
/* 172 */     if (!player.world.isRemote)
/* 173 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player); 
/* 174 */     this.onStopContinuityEvent.onStopContinuity(player);
/*     */   }
/*     */   
/*     */   public static interface IOnStopContinuity extends Serializable {
/*     */     void onStopContinuity(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IOnEndContinuity extends Serializable {
/*     */     boolean onEndContinuity(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IOnStartContinuity extends Serializable {
/*     */     boolean onStartContinuity(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IDuringContinuity extends Serializable {
/*     */     void duringContinuity(PlayerEntity param1PlayerEntity, int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\ContinuousAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */