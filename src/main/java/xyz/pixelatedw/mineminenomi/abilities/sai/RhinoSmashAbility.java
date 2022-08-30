/*    */ package xyz.pixelatedw.mineminenomi.abilities.sai;
/*    */ 
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import xyz.pixelatedw.mineminenomi.abilities.RunningSmashAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.abilities.IFormRequiredAbility;
/*    */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*    */ import xyz.pixelatedw.mineminenomi.api.morph.ZoanInfo;
/*    */ import xyz.pixelatedw.mineminenomi.entities.zoan.SaiWalkZoanInfo;
/*    */ 
/*    */ public class RhinoSmashAbility
/*    */   extends RunningSmashAbility implements IFormRequiredAbility {
/* 12 */   public static final RhinoSmashAbility INSTANCE = new RhinoSmashAbility();
/*    */ 
/*    */   
/*    */   public RhinoSmashAbility() {
/* 16 */     super("Rhino Smash", AbilityHelper.getDevilFruitCategory(), 1.5D, 4.0F);
/* 17 */     setDescription("Running into enemies deals damage and knocks them back");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ZoanInfo[] getRequiredForms(PlayerEntity player) {
/* 23 */     return new ZoanInfo[] { (ZoanInfo)SaiWalkZoanInfo.INSTANCE };
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\abilities\sai\RhinoSmashAbility.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */