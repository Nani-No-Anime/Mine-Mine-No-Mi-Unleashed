/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class ClientBlockProtectionRule extends BlockProtectionRule {
/*  8 */   public static final ClientBlockProtectionRule INSTANCE = new ClientBlockProtectionRule();
/*    */   
/*    */   public ClientBlockProtectionRule() {
/* 11 */     super(new BlockProtectionRule[0]);
/* 12 */     addApprovedBlocks(new Block[] { Blocks.BLUE_STAINED_GLASS, Blocks.RED_STAINED_GLASS });
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\ClientBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */