/*    */ package xyz.pixelatedw.mineminenomi.items.dials;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.projectile.SmallFireballEntity;
/*    */ import net.minecraft.item.BlockItem;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.util.math.Vec3d;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModCreativeTabs;
/*    */ 
/*    */ public class FlameDialItem extends BlockItem {
/*    */   public FlameDialItem() {
/* 23 */     super(ModBlocks.FLAME_DIAL, (new Item.Properties()).group(ModCreativeTabs.MISC).maxStackSize(16));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 29 */     if (!world.isRemote)
/*    */     {
/* 31 */       if (!player.isSneaking()) {
/*    */         
/* 33 */         Vec3d look = player.getLookVec();
/* 34 */         SmallFireballEntity fireball = new SmallFireballEntity(world, (LivingEntity)player, 1.0D, 1.0D, 1.0D);
/* 35 */         fireball.setPosition(player
/* 36 */             .getPosX(), player
/* 37 */             .getPosY() + 1.5D, player
/* 38 */             .getPosZ());
/* 39 */         fireball.accelerationX = look.x * 0.2D;
/* 40 */         fireball.accelerationY = look.y * 0.2D;
/* 41 */         fireball.accelerationZ = look.z * 0.2D;
/* 42 */         world.addEntity((Entity)fireball);
/*    */         
/* 44 */         player.getCooldownTracker().setCooldown(getItem(), 10);
/* 45 */         player.getHeldItem(hand).shrink(1);
/*    */       } 
/*    */     }
/*    */     
/* 49 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
/* 55 */     return super.onBlockPlaced(pos, world, player, itemStack, state);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\dials\FlameDialItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */