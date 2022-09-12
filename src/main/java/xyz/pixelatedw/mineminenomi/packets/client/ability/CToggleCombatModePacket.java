package xyz.pixelatedw.mineminenomi.packets.client.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

import java.util.function.Supplier;


public class CToggleCombatModePacket
{
  private boolean combatMode = false;
  
  public CToggleCombatModePacket() {}
  
  public CToggleCombatModePacket(boolean combatMode) {
    this.combatMode = combatMode;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.combatMode);
  }

  
  public static CToggleCombatModePacket decode(PacketBuffer buffer) {
    CToggleCombatModePacket msg = new CToggleCombatModePacket();
    msg.combatMode = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(CToggleCombatModePacket message, Supplier<NetworkEvent.Context> ctx) {
    
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
            
            props.setCombatMode(message.combatMode);
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


