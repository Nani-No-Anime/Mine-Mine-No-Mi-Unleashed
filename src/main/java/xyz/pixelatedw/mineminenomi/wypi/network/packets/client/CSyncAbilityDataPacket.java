package xyz.pixelatedw.mineminenomi.wypi.network.packets.client;

import java.util.function.Supplier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;



public class CSyncAbilityDataPacket
{
  private INBT data;
  
  public CSyncAbilityDataPacket() {}
  
  public CSyncAbilityDataPacket(IAbilityData props) {
    this.data = (INBT)new CompoundNBT();
    this.data = AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, props, null);
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeCompoundTag((CompoundNBT)this.data);
  }

  
  public static CSyncAbilityDataPacket decode(PacketBuffer buffer) {
    CSyncAbilityDataPacket msg = new CSyncAbilityDataPacket();
    msg.data = (INBT)buffer.readCompoundTag();
    return msg;
  }

  
  public static void handle(CSyncAbilityDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IAbilityData props = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
            
            AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, props, null, message.data);
            
            WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(serverPlayerEntity.getEntityId(), props), (LivingEntity)serverPlayerEntity);
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


