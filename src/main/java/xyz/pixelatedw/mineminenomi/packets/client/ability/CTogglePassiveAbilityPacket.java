package xyz.pixelatedw.mineminenomi.packets.client.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.function.Supplier;


public class CTogglePassiveAbilityPacket
{
  private String abilityName;
  private boolean flag;
  
  public CTogglePassiveAbilityPacket() {}
  
  public CTogglePassiveAbilityPacket(Ability ability, boolean flag) {
    this.abilityName = WyHelper.getResourceName(ability.getName());
    this.flag = flag;
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.abilityName.length());
    buffer.writeString(this.abilityName, this.abilityName.length());
    buffer.writeBoolean(this.flag);
  }

  
  public static CTogglePassiveAbilityPacket decode(PacketBuffer buffer) {
    CTogglePassiveAbilityPacket msg = new CTogglePassiveAbilityPacket();
    int len = buffer.readInt();
    msg.abilityName = buffer.readString(len);
    msg.flag = buffer.readBoolean();
    return msg;
  }

  
  public static void handle(CTogglePassiveAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
            ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
            
            IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
            
            Ability ability = ((Ability)GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation("mineminenomi", message.abilityName))).create();
            
            Ability unlockedAbility = abilityDataProps.getUnlockedAbility(ability);
            
            if (unlockedAbility instanceof PassiveAbility) {
              PassiveAbility passive = (PassiveAbility)unlockedAbility;
              passive.setPause(message.flag);
              WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)serverPlayerEntity, (Ability)passive), (LivingEntity)serverPlayerEntity);
            } 
          });
    }
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
}


