package xyz.pixelatedw.mineminenomi.packets.client.trade;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.CurrencyHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.entities.mobs.SkypieanTraderEntity;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class CSellToTraderPacket
{
  private int traderId;
  private int amount;
  
  public CSellToTraderPacket() {}
  
  public CSellToTraderPacket(int traderId, int amount) {
    this.traderId = traderId;
    this.amount = amount;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.traderId);
    buffer.writeInt(this.amount);
  }

  
  public static CSellToTraderPacket decode(PacketBuffer buffer) {
    CSellToTraderPacket msg = new CSellToTraderPacket();
    msg.traderId = buffer.readInt();
    msg.amount = buffer.readInt();
    return msg;
  }

  
  public static void handle(CSellToTraderPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity player = ((NetworkEvent.Context)ctx.get()).getSender();
            
            Entity target = player.world.getEntityByID(message.traderId);
            
            if (player.getDistanceSq(target) > 25.0D) {
              return;
            }
            
            if (target != null && target instanceof SkypieanTraderEntity) {
              SkypieanTraderEntity trader = (SkypieanTraderEntity)target;
              
              IEntityStats entityData = EntityStatsCapability.get((LivingEntity)player);
              
              int dirtBlocksAvailable = 0;
              
              List<ItemStack> markedStacks = new ArrayList<>();
              
              for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
                ItemStack stack = player.inventory.getStackInSlot(i);
                
                if (stack != null && !stack.isEmpty() && stack.getItem() == Blocks.DIRT.asItem()) {
                  dirtBlocksAvailable += stack.getCount();
                  
                  if (!markedStacks.contains(stack)) {
                    markedStacks.add(stack);
                  }
                } 
              } 
              
              dirtBlocksAvailable = MathHelper.clamp(dirtBlocksAvailable, 0, message.amount);
              
              if (dirtBlocksAvailable >= message.amount) {
                int markedBlocks = dirtBlocksAvailable;
                
                for (ItemStack stack : markedStacks) {
                  if (markedBlocks <= 0) {
                    break;
                  }
                  int stackSize = stack.getCount();
                  if (markedBlocks - stackSize >= 0) {
                    markedBlocks -= stackSize;
                    stack.shrink(stackSize);
                    continue;
                  } 
                  int blocksNeeded = markedBlocks;
                  markedBlocks -= blocksNeeded;
                  stack.shrink(blocksNeeded);
                } 
                trader.removeTradesLeft(message.amount);
                long value = CurrencyHelper.getExtolFromBelly(dirtBlocksAvailable);
                entityData.alterExtol(value);
                WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityData), (PlayerEntity)player);
              } 
            } 
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


