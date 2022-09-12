package xyz.pixelatedw.mineminenomi.packets.client;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenAbilitySelectionScreenPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

import java.util.function.Supplier;

public class CRequestSyncAbilityDataPacket
{
  private boolean openScreen;
  
  public CRequestSyncAbilityDataPacket() {}
  
  public CRequestSyncAbilityDataPacket(boolean openScreen) {
    this.openScreen = openScreen;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.openScreen);
  }

  
  public static CRequestSyncAbilityDataPacket decode(PacketBuffer buffer) {
    CRequestSyncAbilityDataPacket msg = new CRequestSyncAbilityDataPacket();
    msg.openScreen = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(CRequestSyncAbilityDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IAbilityData props = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
            
            WyNetwork.sendTo(new SSyncAbilityDataPacket(serverPlayerEntity.getEntityId(), props), (PlayerEntity)serverPlayerEntity);
            if (message.openScreen) {
              WyNetwork.sendTo(new SOpenAbilitySelectionScreenPacket(), (PlayerEntity)serverPlayerEntity);
            }
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


