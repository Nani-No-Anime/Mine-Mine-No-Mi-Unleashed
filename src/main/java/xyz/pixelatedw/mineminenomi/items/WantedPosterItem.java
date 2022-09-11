package xyz.pixelatedw.mineminenomi.items;

import javax.annotation.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.WantedPosterTileEntity;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenWantedPosterScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;


public class WantedPosterItem
  extends WallOrFloorItem
{
  public WantedPosterItem() {
    super(ModBlocks.WANTED_POSTER, ModBlocks.WANTED_POSTER, (new Item.Properties()).maxStackSize(1));
  }


  
  public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
    if (!player.world.isRemote && player.getHeldItem(hand).hasTag())
      WyNetwork.sendTo(new SOpenWantedPosterScreenPacket(), player); 
    return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
  }


  
  protected boolean onBlockPlaced(BlockPos pos, World world, @Nullable PlayerEntity player, ItemStack itemStack, BlockState state) {
    if (player != null) {
      
      WantedPosterTileEntity tileEntity = (WantedPosterTileEntity)world.getTileEntity(pos);
      
      if (tileEntity != null && itemStack.hasTag()) {
        
        tileEntity.setEntityName(itemStack.getTag().getString("Name"));
        tileEntity.setUUID(itemStack.getTag().getString("UUID"));
        tileEntity.setPosterBounty(itemStack.getTag().getLong("Bounty") + "");
        tileEntity.setFaction(itemStack.getTag().getString("Faction"));
        tileEntity.setBackground(itemStack.getTag().getString("Background"));
        tileEntity.setIssuedDate(itemStack.getTag().getString("Date"));
        tileEntity.setGameProfile(NBTUtil.readGameProfile((CompoundNBT)itemStack.getTag().get("Owner")));
        tileEntity.markDirty();
      } 
    } 
    boolean flag = super.onBlockPlaced(pos, world, player, itemStack, state);
    return flag;
  }
}


