package xyz.pixelatedw.mineminenomi.packets.client.crew;

import java.util.UUID;
import java.util.function.Supplier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRoger;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class CUpdateJollyRogerPacket
{
  private JollyRoger jollyRoger = new JollyRoger();


  
  public CUpdateJollyRogerPacket() {}

  
  public CUpdateJollyRogerPacket(JollyRoger jollyRoger) {
    this.jollyRoger = jollyRoger;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeCompoundTag(this.jollyRoger.write());
  }

  
  public static CUpdateJollyRogerPacket decode(PacketBuffer buffer) {
    CUpdateJollyRogerPacket msg = new CUpdateJollyRogerPacket();
    msg.jollyRoger.read(buffer.readCompoundTag());
    return msg;
  }

  
  public static void handle(CUpdateJollyRogerPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            UUID uuid = serverPlayerEntity.getUniqueID();
            
            ExtendedWorldData worldData = ExtendedWorldData.get(((PlayerEntity)serverPlayerEntity).world);
            Crew crew = worldData.getCrewWithCaptain(uuid);
            if (crew != null) {
              worldData.updateCrewJollyRoger(crew, message.jollyRoger);
            }
            WyNetwork.sendToAll(new SSyncWorldDataPacket(worldData));
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


