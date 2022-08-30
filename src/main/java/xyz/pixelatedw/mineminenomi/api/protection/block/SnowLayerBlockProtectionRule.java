/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModMaterials;
/*    */ 
/*    */ public class SnowLayerBlockProtectionRule
/*    */   extends BlockProtectionRule {
/*  9 */   public static final SnowLayerBlockProtectionRule INSTANCE = new SnowLayerBlockProtectionRule();
/*    */   
/*    */   public SnowLayerBlockProtectionRule() {
/* 12 */     super(new BlockProtectionRule[0]);
/* 13 */     addApprovedMaterials(new Material[] { Material.SNOW, Material.SNOW_BLOCK, ModMaterials.HARDENED_SNOW_BLOCK });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\SnowLayerBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */