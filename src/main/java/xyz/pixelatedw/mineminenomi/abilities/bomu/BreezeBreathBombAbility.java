/*    */ package xyz.pixelatedw.mineminenomi.abilities.bomu;
/*    */ 
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ 
/*    */ public class BreezeBreathBombAbility
/*    */   extends ContinuousAbility
/*    */ {
/*  9 */   public static final BreezeBreathBombAbility INSTANCE = new BreezeBreathBombAbility();
/*    */ 
/*    */   
/*    */   public BreezeBreathBombAbility() {
/* 13 */     super("Breeze Breath Bomb", AbilityHelper.getDevilFruitCategory());
/* 14 */     setDescription("Load a gun with explosive breath and shoot a chain explosion");
/* 15 */     setMaxCooldown(15.0D);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\bomu\BreezeBreathBombAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */