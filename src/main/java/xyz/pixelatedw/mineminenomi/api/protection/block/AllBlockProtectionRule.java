/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraftforge.registries.ForgeRegistries;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ 
/*    */ public class AllBlockProtectionRule extends BlockProtectionRule {
/*  8 */   public static final AllBlockProtectionRule INSTANCE = new AllBlockProtectionRule();
/*    */   
/*    */   public AllBlockProtectionRule() {
/* 11 */     super(new BlockProtectionRule[0]);
/* 12 */     ForgeRegistries.BLOCKS.forEach(block -> addApprovedBlocks(new Block[] { block }));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\AllBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */