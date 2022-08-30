/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.PaneBlock;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ 
/*    */ public class CustomBarsBlock
/*    */   extends PaneBlock {
/*    */   public CustomBarsBlock() {
/* 11 */     this(Block.Properties.create(Material.IRON).hardnessAndResistance(5.0F, 6.0F).harvestTool(ToolType.PICKAXE));
/*    */   }
/*    */ 
/*    */   
/*    */   public CustomBarsBlock(Block.Properties props) {
/* 16 */     super(props);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\CustomBarsBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */