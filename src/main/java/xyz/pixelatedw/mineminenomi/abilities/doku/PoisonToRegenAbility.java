/*    */ package xyz.pixelatedw.mineminenomi.abilities.doku;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import net.minecraft.potion.Effects;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.PotionPassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ 
/*    */ public class PoisonToRegenAbility
/*    */   extends PotionPassiveAbility {
/* 11 */   public static final PoisonToRegenAbility INSTANCE = new PoisonToRegenAbility();
/*    */ 
/*    */   
/*    */   public PoisonToRegenAbility() {
/* 15 */     super("Poison to Regen", AbilityHelper.getDevilFruitCategory());
/* 16 */     setDescription("Converts powerful poison applied into regeneration");
/* 17 */     hideInGUI(false);
/*    */     
/* 19 */     this.checkPotionEvent = this::checkPotionEvent;
/*    */   }
/*    */ 
/*    */   
/*    */   private boolean checkPotionEvent(PlayerEntity player, EffectInstance effect) {
/* 24 */     if (effect.getPotion().equals(Effects.POISON)) {
/*    */       
/* 26 */       if (!player.isPotionActive(Effects.REGENERATION) && effect.getAmplifier() > 0)
/* 27 */         player.addPotionEffect(new EffectInstance(Effects.REGENERATION, effect.getDuration(), 0)); 
/* 28 */       return false;
/*    */     } 
/*    */     
/* 31 */     return true;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\doku\PoisonToRegenAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */