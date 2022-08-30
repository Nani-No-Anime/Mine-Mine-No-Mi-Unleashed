/*     */ package xyz.pixelatedw.mineminenomi.wypi.abilities;
/*     */ 
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public abstract class RepeaterAbility
/*     */   extends ContinuousAbility implements IParallelContinuousAbility {
/*     */   private int repeaterCount;
/*     */   protected int maxRepeaterCount;
/*     */   protected int repeaterInterval;
/*     */   
/*     */   public RepeaterAbility(String name, APIConfig.AbilityCategory category) {
/*  17 */     super(name, category);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void repeater(PlayerEntity player, int passiveTimer) {
/*  25 */     if (this.repeaterCount > 0 && passiveTimer % this.repeaterInterval == 0)
/*     */     {
/*  27 */       if (this.onUseEvent.onUse(player)) {
/*  28 */         this.repeaterCount--;
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMaxRepeaterCount(int count, int interval) {
/*  37 */     this.maxRepeaterCount = count;
/*  38 */     this.repeaterCount = this.maxRepeaterCount;
/*  39 */     this.repeaterInterval = interval;
/*  40 */     int threshold = (int)Math.ceil(((this.repeaterCount * this.repeaterInterval) / 20.0F));
/*  41 */     if (this.repeaterInterval == 0)
/*  42 */       threshold = -1; 
/*  43 */     setThreshold(threshold);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRepeaterCount(int count) {
/*  48 */     this.repeaterCount = count;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int getMaxRepeaterCount() {
/*  54 */     return this.maxRepeaterCount;
/*     */   }
/*     */   
/*     */   public int getRepeaterCount() {
/*  58 */     return this.repeaterCount;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getRepeaterInterval() {
/*  63 */     return this.repeaterInterval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void use(PlayerEntity player) {
/*  71 */     super.use(player);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick(PlayerEntity player) {
/*  77 */     if (isContinuous()) {
/*     */       
/*  79 */       if (ExtendedWorldData.get(player.world).isInsideRestrictedArea((int)player.getPosX(), (int)player.getPosY(), (int)player.getPosZ())) {
/*     */         
/*  81 */         endContinuity(player);
/*     */         
/*     */         return;
/*     */       } 
/*  85 */       this.continueTime++;
/*  86 */       if (!player.world.isRemote) {
/*  87 */         this.duringContinuityEvent.duringContinuity(player, this.continueTime);
/*  88 */         repeater(player, this.continueTime);
/*     */       } 
/*     */       
/*  91 */       if (getThreshold() != 0 && this.continueTime >= getThreshold()) {
/*  92 */         endContinuity(player);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void endContinuity(PlayerEntity player) {
/*  99 */     if (player.world.isRemote)
/*     */       return; 
/* 101 */     if (this.onEndContinuityEvent.onEndContinuity(player)) {
/*     */       
/* 103 */       this.continueTime = 0;
/* 104 */       this.repeaterCount = this.maxRepeaterCount;
/* 105 */       startCooldown(player);
/* 106 */       WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket(player, this), (LivingEntity)player);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\wypi\abilities\RepeaterAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */