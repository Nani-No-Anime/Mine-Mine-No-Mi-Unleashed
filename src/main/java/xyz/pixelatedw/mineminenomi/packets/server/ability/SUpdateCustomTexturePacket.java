package xyz.pixelatedw.mineminenomi.packets.server.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.function.Supplier;



public class SUpdateCustomTexturePacket
{
  private int entityId;
  private String abilityId;
  private String customTexture = "";
  private String displayName = "";





  
  public SUpdateCustomTexturePacket(PlayerEntity player, Ability ability) {
    this.entityId = player.getEntityId();
    this.abilityId = WyHelper.getResourceName(ability.getName());
    this.customTexture = ability.getCustomTexture();
    this.displayName = ability.getDisplayName();
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    int len = this.abilityId.length();
    buffer.writeInt(len);
    buffer.writeString(this.abilityId, len);
    int textureLen = this.customTexture.length();
    buffer.writeInt(textureLen);
    buffer.writeString(this.customTexture, textureLen);
    int displayLen = this.displayName.length();
    buffer.writeInt(displayLen);
    buffer.writeString(this.displayName, displayLen);
  }

  
  public static SUpdateCustomTexturePacket decode(PacketBuffer buffer) {
    SUpdateCustomTexturePacket msg = new SUpdateCustomTexturePacket();
    msg.entityId = buffer.readInt();
    int len = buffer.readInt();
    msg.abilityId = buffer.readString(len);
    int textureLen = buffer.readInt();
    msg.customTexture = buffer.readString(textureLen);
    int displayLen = buffer.readInt();
    msg.displayName = buffer.readString(displayLen);
    return msg;
  }

  
  public static void handle(SUpdateCustomTexturePacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
  
  public SUpdateCustomTexturePacket() {}
  
  public static class ClientHandler {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SUpdateCustomTexturePacket message) {
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
      Ability ability = props.getEquippedAbility(templateAbl);
      
      if (ability == null) {
        return;
      }
      ability.setDisplayName(message.displayName);
      ability.setCustomTexture(message.customTexture);
    }
  }
}


