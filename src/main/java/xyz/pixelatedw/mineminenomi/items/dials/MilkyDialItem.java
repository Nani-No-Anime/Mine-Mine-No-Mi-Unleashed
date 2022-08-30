/*    */ package xyz.pixelatedw.mineminenomi.items.dials;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.entities.projectiles.extra.MilkyDialProjectile;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class MilkyDialItem
/*    */   extends BlockItem {
/*    */   public MilkyDialItem() {
/* 23 */     super(ModBlocks.MILKY_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 29 */     if (!world.isRemote) {
/*    */       
/* 31 */       MilkyDialProjectile proj = new MilkyDialProjectile(player.world, (LivingEntity)player);
/*    */       
/* 33 */       world.addEntity((Entity)proj);
/* 34 */       proj.shoot((Entity)player, player.rotationPitch, player.rotationYaw, 0.0F, 2.0F, 1.0F);
/*    */       
/* 36 */       player.getCooldownTracker().setCooldown(getItem(), 10);
/* 37 */       player.getHeldItem(hand).shrink(1);
/*    */     } 
/*    */     
/* 40 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
/* 46 */     return super.onBlockPlaced(pos, world, player, itemStack, state);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\dials\MilkyDialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */