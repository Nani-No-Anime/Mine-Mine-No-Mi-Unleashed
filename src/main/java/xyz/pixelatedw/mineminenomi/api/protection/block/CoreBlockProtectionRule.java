/*    */ package xyz.pixelatedw.mineminenomi.api.protection.block;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.Blocks;
/*    */ import net.minecraft.block.material.Material;
/*    */ import xyz.pixelatedw.mineminenomi.api.protection.BlockProtectionRule;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ 
/*    */ public class CoreBlockProtectionRule extends BlockProtectionRule {
/* 10 */   public static final CoreBlockProtectionRule INSTANCE = new CoreBlockProtectionRule();
/*    */   
/*    */   public CoreBlockProtectionRule() {
/* 13 */     super(new BlockProtectionRule[0]);
/* 14 */     addApprovedMaterials(new Material[] { Material.ROCK, Material.IRON, Material.WOOD, Material.SNOW_BLOCK, Material.MISCELLANEOUS, Material.SAND, Material.GLASS, Material.ANVIL, Material.BAMBOO, Material.BAMBOO_SAPLING, Material.CACTUS, Material.CAKE, Material.CARPET, Material.CLAY, Material.CORAL, Material.EARTH, Material.GOURD, Material.PACKED_ICE, Material.ORGANIC, Material.WEB });
/*    */ 
/*    */     
/* 17 */     addApprovedBlocks(new Block[] { ModBlocks.SUNA_SAND, ModBlocks.CANDY, ModBlocks.WAX, ModBlocks.POISON, ModBlocks.DEMON_POISON, Blocks.ICE, Blocks.BLUE_ICE, ModBlocks.SKY_BLOCK, ModBlocks.SPONGE_CAKE });
/* 18 */     addBannedBlocks(RestrictedBlockProtectionRule.INSTANCE.getBannedBlocks());
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\api\protection\block\CoreBlockProtectionRule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */