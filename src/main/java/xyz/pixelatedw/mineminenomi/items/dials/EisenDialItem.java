/*    */ package xyz.pixelatedw.mineminenomi.items.dials;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ 
/*    */ public class EisenDialItem
/*    */   extends BlockItem
/*    */ {
/*    */   public EisenDialItem() {
/* 19 */     super(ModBlocks.EISEN_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
/* 25 */     return super.onBlockPlaced(pos, world, player, itemStack, state);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\dials\EisenDialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */