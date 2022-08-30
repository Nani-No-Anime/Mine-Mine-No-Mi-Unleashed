/*    */ package xyz.pixelatedw.mineminenomi.abilities.pero;
/*    */ 
/*    */ import java.lang.invoke.SerializedLambda;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityOverlay;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IBodyOverlayAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModResources;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class CandyArmorAbility extends ContinuousAbility implements IBodyOverlayAbility {
/* 15 */   public static final CandyArmorAbility INSTANCE = new CandyArmorAbility();
/*    */   
/* 17 */   private static final AbilityOverlay OVERLAY = (new AbilityOverlay()).setTexture(ModResources.CANDY_ARMOR).setColor(WyHelper.hexToRGB("#FFFFFF99"));
/*    */ 
/*    */   
/*    */   public CandyArmorAbility() {
/* 21 */     super("Candy Armor", AbilityHelper.getDevilFruitCategory());
/* 22 */     setThreshold(15.0D);
/* 23 */     setDescription("Coat yourself with a hard candy armor, which boosts defense, but reduces mobility");
/*    */     
/* 25 */     this.duringContinuityEvent = this::duringContinuity;
/* 26 */     this.onEndContinuityEvent = this::onEndContinuityEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private void duringContinuity(PlayerEntity player, int passiveTimer) {
/* 31 */     player.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 20, 2, false, false));
/* 32 */     player.addPotionEffect(new EffectInstance(Effects.SLOWNESS, 20, 2, false, false));
/* 33 */     player.addPotionEffect(new EffectInstance(Effects.MINING_FATIGUE, 20, 1, false, false));
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean onEndContinuityEvent(PlayerEntity player) {
/* 38 */     int cooldown = (int)Math.round(this.continueTime / 12.0D) + 2;
/* 39 */     setMaxCooldown(cooldown);
/*    */     
/* 41 */     player.removePotionEffect(Effects.RESISTANCE);
/* 42 */     player.removePotionEffect(Effects.SLOWNESS);
/* 43 */     player.removePotionEffect(Effects.MINING_FATIGUE);
/* 44 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public AbilityOverlay getBodyOverlay() {
/* 50 */     return OVERLAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\pero\CandyArmorAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */