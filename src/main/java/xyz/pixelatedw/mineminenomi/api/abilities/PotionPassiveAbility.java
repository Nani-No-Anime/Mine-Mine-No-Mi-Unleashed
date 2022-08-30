/*    */ package xyz.pixelatedw.mineminenomi.api.abilities;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.potion.EffectInstance;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ public abstract class PotionPassiveAbility
/*    */   extends PassiveAbility
/*    */ {
/*    */   protected ICheckPotionEvent checkPotionEvent = (player, effect) -> true;
/*    */   
/*    */   public PotionPassiveAbility(String name, APIConfig.AbilityCategory category) {
/* 14 */     super(name, category);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean check(PlayerEntity user, EffectInstance effect) {
/* 20 */     return this.checkPotionEvent.checkPotion(user, effect);
/*    */   }
/*    */   
/*    */   public static interface ICheckPotionEvent {
/*    */     boolean checkPotion(PlayerEntity param1PlayerEntity, EffectInstance param1EffectInstance);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\abilities\PotionPassiveAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */