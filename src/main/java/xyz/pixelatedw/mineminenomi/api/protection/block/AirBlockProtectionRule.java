/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class AirBlockProtectionRule
/*    */   extends BlockProtectionRule {
/*  8 */   public static final AirBlockProtectionRule INSTANCE = new AirBlockProtectionRule();
/*    */   
/*    */   public AirBlockProtectionRule() {
/* 11 */     super(new BlockProtectionRule[0]);
/* 12 */     addApprovedMaterials(new Material[] { Material.AIR });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\AirBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */