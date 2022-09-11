package xyz.pixelatedw.mineminenomi.packets.server.ability;

import java.util.function.Supplier;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;







public class SUpdateExtraDataPacket
{
  private int entityId;
  private String abilityId;
  private CompoundNBT extraData;
  private boolean isEquipped;
  
  public SUpdateExtraDataPacket() {}
  
  public SUpdateExtraDataPacket(PlayerEntity player, Ability ability) {
    this.entityId = player.getEntityId();
    this.abilityId = WyHelper.getResourceName(ability.getName());
    this.isEquipped = true;
    
    if (ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility) {
      this.isEquipped = false;
    }
    if (ability instanceof IExtraUpdateData) {
      this.extraData = ((IExtraUpdateData)ability).getExtraData();
    }
  }
  
  public void encode(PacketBuffer buffer) {
    buffer.writeBoolean(this.isEquipped);
    buffer.writeInt(this.entityId);
    int len = this.abilityId.length();
    buffer.writeInt(len);
    buffer.writeString(this.abilityId, len);
    buffer.writeBoolean((this.extraData != null));
    if (this.extraData != null) {
      buffer.writeCompoundTag(this.extraData);
    }
  }
  
  public static SUpdateExtraDataPacket decode(PacketBuffer buffer) {
    SUpdateExtraDataPacket msg = new SUpdateExtraDataPacket();
    msg.isEquipped = buffer.readBoolean();
    msg.entityId = buffer.readInt();
    int len = buffer.readInt();
    msg.abilityId = buffer.readString(len);
    boolean hasExtraData = buffer.readBoolean();
    if (hasExtraData)
      msg.extraData = buffer.readCompoundTag(); 
    return msg;
  }

  
  public static void handle(SUpdateExtraDataPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }

  
  public static class ClientHandler
  {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SUpdateExtraDataPacket message) {
      if (WyHelper.isNullOrEmpty(message.abilityId)) {
        return;
      }
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      
      if (target == null || !(target instanceof PlayerEntity)) {
        return;
      }
      ResourceLocation res = new ResourceLocation("mineminenomi", message.abilityId);
      Ability templateAbl = Ability.get(res);
      
      if (templateAbl == null) {
        return;
      }
      IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
      Ability ability = null;
      boolean isEquipped = message.isEquipped;
      if (isEquipped) {
        ability = props.getEquippedAbility(templateAbl);
      } else {
        ability = props.getUnlockedAbility(templateAbl);
      } 
      if (ability == null) {
        return;
      }
      if (message.extraData != null && ability instanceof IExtraUpdateData)
        ((IExtraUpdateData)ability).setExtraData(message.extraData); 
    }
  }
}


