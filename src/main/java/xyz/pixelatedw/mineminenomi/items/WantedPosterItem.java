/*    */ package xyz.pixelatedw.mineminenomi.items;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import net.minecraft.block.BlockState;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.item.Item;
/*    */ import net.minecraft.item.ItemStack;
/*    */ import net.minecraft.item.WallOrFloorItem;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.NBTUtil;
/*    */ import net.minecraft.util.ActionResult;
/*    */ import net.minecraft.util.ActionResultType;
/*    */ import net.minecraft.util.Hand;
/*    */ import net.minecraft.util.math.BlockPos;
/*    */ import net.minecraft.world.World;
/*    */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenWantedPosterScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class WantedPosterItem
/*    */   extends WallOrFloorItem
/*    */ {
/*    */   public WantedPosterItem() {
/* 26 */     super(ModBlocks.WANTED_POSTER, ModBlocks.WANTED_POSTER, (new Item.Properties()).maxStackSize(1));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
/* 32 */     if (!player.world.isRemote && player.getHeldItem(hand).hasTag())
/* 33 */       WyNetwork.sendTo(new SOpenWantedPosterScreenPacket(), player); 
/* 34 */     return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
/* 40 */     if (player != null) {
/*    */       
/* 42 */       WantedPosterTileEntity tileEntity = (WantedPosterTileEntity)world.getTileEntity(pos);
/*    */       
/* 44 */       if (tileEntity != null && itemStack.hasTag()) {
/*    */         
/* 46 */         tileEntity.setEntityName(itemStack.getTag().getString("Name"));
/* 47 */         tileEntity.setUUID(itemStack.getTag().getString("UUID"));
/* 48 */         tileEntity.setPosterBounty(itemStack.getTag().getLong("Bounty") + "");
/* 49 */         tileEntity.setFaction(itemStack.getTag().getString("Faction"));
/* 50 */         tileEntity.setBackground(itemStack.getTag().getString("Background"));
/* 51 */         tileEntity.setIssuedDate(itemStack.getTag().getString("Date"));
/* 52 */         tileEntity.setGameProfile(NBTUtil.readGameProfile((CompoundNBT)itemStack.getTag().get("Owner")));
/* 53 */         tileEntity.markDirty();
/*    */       } 
/*    */     } 
/* 56 */     boolean flag = super.onBlockPlaced(pos, world, player, itemStack, state);
/* 57 */     return flag;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\items\WantedPosterItem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */