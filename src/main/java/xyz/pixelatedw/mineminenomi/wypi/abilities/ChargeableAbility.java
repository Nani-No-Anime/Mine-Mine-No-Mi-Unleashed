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
/*     */ 
/*     */ public abstract class ChargeableAbility
/*     */   extends Ability
/*     */ {
/*     */   private int chargeTime;
/*     */   private int maxChargeTime;
/*     */   private boolean isCancelable;
/*     */   protected IOnStartCharging onStartChargingEvent = player -> true;
/*     */   protected IOnEndCharging onEndChargingEvent = player -> true;
/*     */   protected IDuringCharging duringChargingEvent = (player, chargeTime) -> {
/*     */     
/*     */     };
/*     */   
/*     */   public ChargeableAbility(String name, APIConfig.AbilityCategory category) {
/*  33 */     super(name, category);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void use(PlayerEntity player) {
/*  42 */     if (player.world.isRemote) {
/*     */       return;
/*     */     }
/*  45 */     if (isOnCooldown() && getCooldown() <= 10.0D) {
/*  46 */       stopCooldown(player);
/*     */     }
/*  48 */     AbilityUseEvent event = new AbilityUseEvent(player, this);
/*  49 */     if (MinecraftForge.EVENT_BUS.post(event)) {
/*     */       return;
/*     */     }
/*  52 */     if (isCharging() && this.chargeTime > 0) {
/*     */       
/*  54 */       endCharging(player);
/*     */     }
/*  56 */     else if (isOnStandby()) {
/*     */       
/*  58 */       if (this.onStartChargingEvent.onStartCharging(player)) {
/*     */         
/*  60 */         checkAbilityPool(player, Ability.State.CHARGING);
/*     */         
/*  62 */         if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue() && (getChargedShouts()).length > 1) {
/*  63 */           WyNetwork.sendToAllTrackingAndSelf(new SAnimeScreamPacket(player.getEntityId(), getChargedShouts()[0] + "..."), (LivingEntity)player);
/*     */         }
/*  65 */         this.chargeTime = this.maxChargeTime;
/*  66 */         startCharging(player);
/*     */ 
/*     */         
/*  69 */         if (this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IAnimatedAbility || this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility || this instanceof xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility)
/*  70 */           WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), AbilityDataCapability.get((LivingEntity)player)), (LivingEntity)player); 
/*  71 */         WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxChargeTime(double seconds) {
/*  81 */     this.maxChargeTime = (int)(seconds * 20.0D);
/*  82 */     this.chargeTime = this.maxChargeTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxChargeTime() {
/*  87 */     return this.maxChargeTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getChargeTime() {
/*  92 */     return this.chargeTime;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setChargeTime(int time) {
/*  97 */     this.chargeTime = time * 20;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCancelable() {
/* 102 */     this.isCancelable = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCancelable() {
/* 107 */     return this.isCancelable;
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
/*     */   public void charging(PlayerEntity player) {
/* 120 */     if (!canUse(player)) {
/*     */       
/* 122 */       stopCharging(player);
/*     */       
/*     */       return;
/*     */     } 
/* 126 */     player.world.getProfiler().startSection(WyHelper.getResourceName(getName()));
/*     */     
/* 128 */     if (isCharging() && this.chargeTime > 0) {
/*     */       
/* 130 */       this.chargeTime = (int)(this.chargeTime - 1.0D * getTimeProgression());
/* 131 */       if (!player.world.isRemote && !isStateForced()) {
/* 132 */         this.duringChargingEvent.duringCharging(player, this.chargeTime);
/*     */       }
/* 134 */     } else if (isCharging() && this.chargeTime <= 0) {
/*     */       
/* 136 */       endCharging(player);
/*     */     } 
/*     */     
/* 139 */     player.world.getProfiler().endSection();
/*     */   }
/*     */ 
/*     */   
/*     */   public void startCharging(PlayerEntity player) {
/* 144 */     setState(Ability.State.CHARGING);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void endCharging(PlayerEntity player) {
/* 153 */     if (player.world.isRemote)
/*     */       return; 
/* 155 */     if (!isStateForced() && this.onEndChargingEvent.onEndCharging(player)) {
/*     */       
/* 157 */       if (((Boolean)CommonConfig.INSTANCE.animeScreaming.get()).booleanValue()) {
/* 158 */         WyNetwork.sendToAllTrackingAndSelf(((getChargedShouts()).length > 1) ? new SAnimeScreamPacket(player.getEntityId(), getChargedShouts()[1]) : new SAnimeScreamPacket(player.getEntityId(), getChargedShouts()[0]), (LivingEntity)player);
/*     */       }
/* 160 */       stopCharging(player);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void stopCharging(PlayerEntity player) {
/* 170 */     checkAbilityPool(player, Ability.State.COOLDOWN);
/* 171 */     setForcedState(false);
/* 172 */     this.chargeTime = this.maxChargeTime;
/* 173 */     startCooldown(player);
/* 174 */     if (!player.world.isRemote) {
/* 175 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
/*     */     }
/*     */   }
/*     */   
/*     */   public String[] getChargedShouts() {
/* 180 */     String[] nameSplit = getDisplayName().split(" ");
/* 181 */     int midPoint = (int)Math.ceil(nameSplit.length / 2.0D);
/* 182 */     StringBuilder sb = new StringBuilder();
/* 183 */     for (int i = 0; i < midPoint; i++)
/*     */     {
/* 185 */       sb.append(nameSplit[i] + " ");
/*     */     }
/* 187 */     String firstShout = sb.toString().replaceAll("[:-]", "");
/* 188 */     sb = new StringBuilder();
/* 189 */     for (int j = midPoint; j < nameSplit.length; j++)
/*     */     {
/* 191 */       sb.append(nameSplit[j] + " ");
/*     */     }
/* 193 */     String secondShout = sb.toString().replaceAll("[:-]", "");
/*     */     
/* 195 */     (new String[2])[0] = firstShout; (new String[2])[1] = secondShout; (new String[1])[0] = firstShout; return (secondShout.length() > 0) ? new String[2] : new String[1];
/*     */   }
/*     */   
/*     */   public static interface IOnEndCharging extends Serializable {
/*     */     boolean onEndCharging(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IOnStartCharging extends Serializable {
/*     */     boolean onStartCharging(PlayerEntity param1PlayerEntity);
/*     */   }
/*     */   
/*     */   public static interface IDuringCharging extends Serializable {
/*     */     void duringCharging(PlayerEntity param1PlayerEntity, int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\ChargeableAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */