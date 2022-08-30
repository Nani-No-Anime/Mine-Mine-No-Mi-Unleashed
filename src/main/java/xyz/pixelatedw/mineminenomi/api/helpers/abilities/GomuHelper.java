/*    */ package xyz.pixelatedw.mineminenomi.api.helpers.abilities;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearFourthAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearSecondAbility;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.gomu.GearThirdAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class GomuHelper
/*    */ {
/*    */   public static boolean canActivateGear(IAbilityData props, Ability gear) {
/* 13 */     if (gear.equals(GearSecondAbility.INSTANCE)) {
/*    */       
/* 15 */       if (hasGearThirdActive(props) || hasGearFourthActive(props)) {
/* 16 */         return false;
/*    */       }
/* 18 */     } else if (gear.equals(GearThirdAbility.INSTANCE)) {
/*    */       
/* 20 */       if (hasGearSecondActive(props) || hasGearFourthActive(props)) {
/* 21 */         return false;
/*    */       }
/* 23 */     } else if (gear.equals(GearFourthAbility.INSTANCE)) {
/*    */       
/* 25 */       if (hasGearThirdActive(props) || hasGearSecondActive(props)) {
/* 26 */         return false;
/*    */       }
/*    */     } 
/* 29 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean hasGearSecondActive(IAbilityData props) {
/* 34 */     Ability ability = props.getEquippedAbility((Ability)GearSecondAbility.INSTANCE);
/* 35 */     boolean isActive = (ability != null && ability.isContinuous());
/* 36 */     return isActive;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean hasGearThirdActive(IAbilityData props) {
/* 41 */     Ability ability = props.getEquippedAbility((Ability)GearThirdAbility.INSTANCE);
/* 42 */     boolean isActive = (ability != null && ability.isContinuous());
/* 43 */     return isActive;
/*    */   }
/*    */ 
/*    */   
/*    */   public static boolean hasGearFourthActive(IAbilityData props) {
/* 48 */     Ability ability = props.getEquippedAbility((Ability)GearFourthAbility.INSTANCE);
/* 49 */     boolean isActive = (ability != null && ability.isContinuous());
/* 50 */     return isActive;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\helpers\abilities\GomuHelper.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */