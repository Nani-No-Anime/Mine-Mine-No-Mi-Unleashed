/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class HardThrowableProtectionRule extends BlockProtectionRule {
/*  9 */   public static final HardThrowableProtectionRule INSTANCE = new HardThrowableProtectionRule();
/*    */   
/*    */   public HardThrowableProtectionRule() {
/* 12 */     super(new BlockProtectionRule[0]);
/* 13 */     addBannedBlocks(new Block[] { Blocks.COAL_BLOCK, Blocks.DIAMOND_BLOCK, Blocks.EMERALD_BLOCK, Blocks.OBSIDIAN, Blocks.IRON_BLOCK, Blocks.ANVIL, Blocks.ENCHANTING_TABLE, Blocks.ENDER_CHEST, Blocks.WATER, Blocks.LAVA, Blocks.BEACON, Blocks.DRAGON_EGG, Blocks.SLIME_BLOCK, Blocks.HONEY_BLOCK });
/*    */ 
/*    */     
/* 16 */     addApprovedMaterials(new Material[] { Material.IRON, Material.PORTAL });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\HardThrowableProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */