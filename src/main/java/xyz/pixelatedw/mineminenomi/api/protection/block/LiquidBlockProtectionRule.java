/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class LiquidBlockProtectionRule extends BlockProtectionRule {
/*  9 */   public static final LiquidBlockProtectionRule INSTANCE = new LiquidBlockProtectionRule();
/*    */   
/*    */   public LiquidBlockProtectionRule() {
/* 12 */     super(new BlockProtectionRule[0]);
/* 13 */     addApprovedBlocks(new Block[] { Blocks.SEAGRASS });
/* 14 */     addApprovedMaterials(new Material[] { Material.WATER, Material.LAVA });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\LiquidBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */