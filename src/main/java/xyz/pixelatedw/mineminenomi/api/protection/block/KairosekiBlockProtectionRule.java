/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class KairosekiBlockProtectionRule extends BlockProtectionRule {
/*  8 */   public static final KairosekiBlockProtectionRule INSTANCE = new KairosekiBlockProtectionRule();
/*    */   
/*    */   public KairosekiBlockProtectionRule() {
/* 11 */     super(new BlockProtectionRule[0]);
/* 12 */     addBannedBlocks(new Block[] { ModBlocks.KAIROSEKI, ModBlocks.KAIROSEKI_ORE, ModBlocks.KAIROSEKI_BARS });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\KairosekiBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */