package xyz.pixelatedw.mineminenomi.packets.server.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.function.Supplier;


public class SChangeCombatBarPacket
{
  private int combatBar;
  
  public SChangeCombatBarPacket() {}
  
  public SChangeCombatBarPacket(int combatBar) {
    this.combatBar = combatBar;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.combatBar);
  }

  
  public static SChangeCombatBarPacket decode(PacketBuffer buffer) {
    SChangeCombatBarPacket msg = new SChangeCombatBarPacket();
    msg.combatBar = buffer.readInt();
    return msg;
  }

  
  public static void handle(SChangeCombatBarPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SChangeCombatBarPacket message) {
      ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
      
      abilityProps.setCombatBarSet(message.combatBar);
    }
  }
}


