/*    */ package xyz.pixelatedw.mineminenomi.abilities.kage;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ 
/*    */ public class KageGiriAbility
/*    */   extends PassiveAbility {
/*  8 */   public static final KageGiriAbility INSTANCE = new KageGiriAbility();
/*    */ 
/*    */   
/*    */   public KageGiriAbility() {
/* 12 */     super("Kage Giri", AbilityHelper.getDevilFruitCategory());
/* 13 */     setDescription("Allows the user to cut an enemy's shadow using a pair of Scissors");
/* 14 */     hideInGUI(false);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\kage\KageGiriAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */