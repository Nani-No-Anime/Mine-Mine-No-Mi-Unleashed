/*    */ package xyz.pixelatedw.mineminenomi.blocks;
/*    */ 
/*    */ import net.minecraft.block.Block;
/*    */ import net.minecraft.block.OreBlock;
/*    */ import net.minecraft.block.material.Material;
/*    */ import net.minecraftforge.common.ToolType;
/*    */ 
/*    */ public class KairosekiOreBlock
/*    */   extends OreBlock
/*    */ {
/*    */   public KairosekiOreBlock() {
/* 12 */     super(Block.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).harvestLevel(0).hardnessAndResistance(8.0F));
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\blocks\KairosekiOreBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */