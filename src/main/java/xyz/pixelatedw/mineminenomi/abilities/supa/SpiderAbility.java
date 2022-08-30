/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityPool;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModEffects;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class SpiderAbility
/*    */   extends ContinuousAbility implements IBodyOverlayAbility {
/* 17 */   public static final SpiderAbility INSTANCE = new SpiderAbility();
/* 18 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.DOKU_COATING).setColor(new Color(33, 26, 26, 158));
/*    */ 
/*    */   
/*    */   public SpiderAbility() {
/* 22 */     super("Spider", AbilityHelper.getDevilFruitCategory());
/* 23 */     setThreshold(15.0D);
/* 24 */     setDescription("Hardens the user's body to protect themselves, but they're unable to move");
/*    */     
/* 26 */     addInPool(new AbilityPool[] { AbilityPool.TEKKAI_LIKE });
/* 27 */     this.onStartContinuityEvent = this::onStartContinuityEvent;
/* 28 */     this.duringContinuityEvent = this::duringContinuity;
/* 29 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */   
/*    */   private boolean onStartContinuityEvent(PlayerEntity player) {
/* 33 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 38 */     player.addPotionEffect(new EffectInstance(ModEffects.GUARDING, 2, 5, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 43 */     int cooldown = (int)Math.round(this.continueTime / 15.0D);
/* 44 */     setMaxCooldown(cooldown);
/* 45 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getBodyOverlay() {
/* 51 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\SpiderAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */