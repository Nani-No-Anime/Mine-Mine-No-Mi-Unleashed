/*    */ package xyz.pixelatedw.mineminenomi.abilities.supa;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IPunchOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PunchAbility;
/*    */ 
/*    */ public class SparClawAbility
/*    */   extends PunchAbility implements IPunchOverlayAbility {
/* 14 */   public static final SparClawAbility INSTANCE = new SparClawAbility();
/*    */   
/* 16 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setColor(new Color(113, 121, 126));
/*    */ 
/*    */   
/*    */   public SparClawAbility() {
/* 20 */     super("Spar Claw", AbilityHelper.getDevilFruitCategory());
/* 21 */     setMaxCooldown(1.0D);
/* 22 */     setThreshold(50.0D);
/* 23 */     setDescription("Turns the undersides of the user's fingers into blades to slash opponents with");
/*    */     
/* 25 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/* 26 */     this.onHitEntityEvent = this::onHitEntityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private float onHitEntityEvent(PlayerEntity player, LivingEntity target) {
/* 31 */     return 15.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 36 */     setMaxCooldown((2.0F + this.continueTime / 60.0F));
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public AbilityOverlay getPunchOverlay(LivingEntity player) {
/* 42 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\supa\SparClawAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */