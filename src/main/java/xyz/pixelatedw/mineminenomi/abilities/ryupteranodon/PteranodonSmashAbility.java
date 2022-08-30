/*    */ package xyz.pixelatedw.mineminenomi.abilities.ryupteranodon;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.RunningSmashAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.PteranodonFlyZoanInfo;
/*    */ 
/*    */ public class PteranodonSmashAbility
/*    */   extends RunningSmashAbility implements IFormRequiredAbility {
/* 12 */   public static final PteranodonSmashAbility INSTANCE = new PteranodonSmashAbility();
/*    */ 
/*    */   
/*    */   public PteranodonSmashAbility() {
/* 16 */     super("Pteranodon Smash", AbilityHelper.getDevilFruitCategory(), 1.9D, 4.0F);
/* 17 */     setDescription("Flying into enemies at great speeds deals damage and knocks them back");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 23 */     return new ZoanInfo[] { (ZoanInfo)PteranodonFlyZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\ryupteranodon\PteranodonSmashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */