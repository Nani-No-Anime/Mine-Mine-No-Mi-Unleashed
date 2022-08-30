/*    */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*    */ import java.awt.Color;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.VenomDemonZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.IParallelContinuousAbility;
/*    */ 
/*    */ public class DokuFuguAbility extends ContinuousAbility implements IParallelContinuousAbility, IBodyOverlayAbility {
/* 17 */   public static final DokuFuguAbility INSTANCE = new DokuFuguAbility();
/* 18 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(1.0F, 1.0F, 1.0F, 0.6F));
/*    */ 
/*    */   
/*    */   public DokuFuguAbility() {
/* 22 */     super("Doku Fugu", AbilityHelper.getDevilFruitCategory());
/* 23 */     setMaxCooldown(30.0D);
/* 24 */     setThreshold(60.0D);
/* 25 */     setDescription("The user covers themselves in poison creating a thin protective layer to damage");
/*    */     
/* 27 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 28 */     this.duringContinuityEvent = this::duringContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 33 */     return !VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player);
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuityEvent(PlayerEntity player, int i) {
/* 38 */     if (VenomDemonZoanInfo.INSTANCE.isActive((LivingEntity)player)) {
/*    */       
/* 40 */       endContinuity(player);
/*    */       
/*    */       return;
/*    */     } 
/* 44 */     player.addPotionEffect(new EffectInstance(ModEffects.PHYSICAL_MOVING_GUARD, 2, 2, false, false));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getBodyOverlay() {
/* 50 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\DokuFuguAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */