package xyz.pixelatedw.mineminenomi.packets.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

import java.util.function.Supplier;

public class CSyncZoanPacket
{
  private int entityId;
  
  public CSyncZoanPacket() {}
  
  public CSyncZoanPacket(int id) {
    this.entityId = id;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
  }

  
  public static CSyncZoanPacket decode(PacketBuffer buffer) {
    CSyncZoanPacket msg = new CSyncZoanPacket();
    msg.entityId = buffer.readInt();
    return msg;
  }

  
  public static void handle(CSyncZoanPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            Entity targetPlayer = ((PlayerEntity)serverPlayerEntity).world.getEntityByID(message.entityId);
            
            if (targetPlayer == null || !(targetPlayer instanceof PlayerEntity)) {
              return;
            }
            
            IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity)targetPlayer);
            IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)targetPlayer);
            WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(targetPlayer.getEntityId(), devilFruitProps), (LivingEntity)targetPlayer);
            WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(targetPlayer.getEntityId(), abilityDataProps), (LivingEntity)targetPlayer);
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


