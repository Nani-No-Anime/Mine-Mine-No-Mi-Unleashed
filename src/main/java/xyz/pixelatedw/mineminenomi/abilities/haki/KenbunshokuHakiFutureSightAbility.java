/*    */ package xyz.pixelatedw.mineminenomi.abilities.haki;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.util.SoundCategory;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IHakiAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModSounds;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class KenbunshokuHakiFutureSightAbility extends ContinuousAbility implements IHakiAbility, IParallelContinuousAbility {
/* 19 */   public static final KenbunshokuHakiFutureSightAbility INSTANCE = new KenbunshokuHakiFutureSightAbility();
/*    */   
/* 21 */   public float maxProtection = 0.0F;
/* 22 */   protected float protection = 0.0F;
/*    */ 
/*    */   
/*    */   public KenbunshokuHakiFutureSightAbility() {
/* 26 */     super("Kenbunshoku Haki: Future Sight", AbilityHelper.getHakiCategory());
/* 27 */     setDescription("Using Observation Haki allows the user to see a short period into the future to avoid attacks");
/*    */     
/* 29 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 30 */     this.duringContinuityEvent = this::duringContinuityEvent;
/* 31 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 36 */     if (!HakiHelper.canEnableHaki(player)) {
/* 37 */       return false;
/*    */     }
/* 39 */     this.protection = this.maxProtection = calculateMaxProtection(player);
/* 40 */     player.world.playSound(null, player.getPosition(), ModSounds.KENBUNSHOKU_HAKI_SFX, SoundCategory.PLAYERS, 1.0F, 1.0F);
/*    */     
/* 42 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 47 */     boolean isOnMaxOveruse = HakiHelper.checkForHakiOveruse(player, 2);
/*    */     
/* 49 */     if (isOnMaxOveruse || 0.0F > this.protection) {
/*    */       
/* 51 */       int time = Math.round(40.0F + 60.0F * (1.0F - this.maxProtection / 200.0F));
/* 52 */       setMaxCooldown(time);
/* 53 */       endContinuity(player);
/*    */     } 
/*    */     
/* 56 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*    */     
/* 58 */     if (hakiProps.getHakiOveruse() == 0 && this.maxProtection > this.protection) {
/* 59 */       this.protection = Math.min(this.protection + 5.0F, this.maxProtection);
/*    */     }
/*    */   }
/*    */   
/*    */   private float calculateMaxProtection(PlayerEntity player) {
/* 64 */     IEntityStats sprops = EntityStatsCapability.get((LivingEntity)player);
/* 65 */     IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*    */     
/* 67 */     float dorikiPower = sprops.getDoriki() / 1000.0F;
/* 68 */     float hakiPower = hakiProps.getKenbunshokuHakiExp() * 0.75F;
/* 69 */     float percentageOveruse = 1.0F - hakiProps.getHakiOveruse() / HakiHelper.getMaxOveruse(player) / 2.0F;
/*    */     
/* 71 */     return (15.0F + dorikiPower + hakiPower) * percentageOveruse;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 76 */     if (this.protection > 0.0F)
/* 77 */       setMaxCooldown((5.0F + 30.0F * (1.0F - this.protection / 200.0F))); 
/* 78 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void reduceProtection(float amount) {
/* 83 */     this.protection -= amount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public HakiType getType() {
/* 89 */     return HakiType.KENBUNSHOKU;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\haki\KenbunshokuHakiFutureSightAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */