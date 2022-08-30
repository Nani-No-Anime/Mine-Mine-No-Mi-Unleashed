/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class RestrictedBlockProtectionRule extends BlockProtectionRule {
/*  9 */   public static final RestrictedBlockProtectionRule INSTANCE = new RestrictedBlockProtectionRule();
/*    */   
/*    */   public RestrictedBlockProtectionRule() {
/* 12 */     super(new BlockProtectionRule[0]);
/* 13 */     addBannedBlocks(new Block[] { Blocks.BEDROCK, ModBlocks.PONEGLYPH, ModBlocks.BARRIER, ModBlocks.OPE, ModBlocks.OPE_MID, ModBlocks.STRING_MID, ModBlocks.STRING_WALL, ModBlocks.DARKNESS, Blocks.BARRIER, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\RestrictedBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */