package xyz.pixelatedw.mineminenomi.packets.server.ability;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;

import java.util.function.Supplier;


public class SUpdateEquippedAbilityPacket
{
  private int entityId;
  private int abilityId;
  private String customTexture = "";
  
  private boolean isStateForced;
  private int abilityType = 0;

  
  private CompoundNBT extraData;

  
  private double cooldown;
  
  private double maxCooldown;
  
  private double disableTicks;
  
  private int state;
  
  private double continueTime;
  
  private double threshold;
  
  private double chargeTime;
  
  private double maxChargeTime;
  
  private boolean isPaused;

  
  public SUpdateEquippedAbilityPacket(PlayerEntity player, Ability ability) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    this.entityId = player.getEntityId();
    this.abilityId = props.getEquippedAbilitySlot(ability);
    this.customTexture = ability.getCustomTexture();
    this.state = ability.getState().ordinal();
    this.isStateForced = ability.isStateForced();
    
    if (ability instanceof IExtraUpdateData) {
      this.extraData = ((IExtraUpdateData)ability).getExtraData();
    }
    if (this.state == Ability.State.COOLDOWN.ordinal()) {
      
      this.cooldown = ability.getCooldown() / 20.0D;
      this.maxCooldown = ability.getMaxCooldown() / 20.0D;
    } 
    
    if (ability instanceof ContinuousAbility) {
      
      this.abilityType = 1;
      this.continueTime = (((ContinuousAbility)ability).getContinueTime() / 20);
      this.threshold = (((ContinuousAbility)ability).getThreshold() / 20);
    }
    else if (ability instanceof ChargeableAbility) {
      
      this.abilityType = 2;
      this.chargeTime = (((ChargeableAbility)ability).getChargeTime() / 20);
      this.maxChargeTime = (((ChargeableAbility)ability).getMaxChargeTime() / 20);
    }
    else if (ability instanceof PassiveAbility) {
      
      this.abilityType = 3;
      this.isPaused = ((PassiveAbility)ability).isPaused();
    } 
  }

  
  public SUpdateEquippedAbilityPacket(PlayerEntity player, Ability ability, Ability.State state, double[] values) {
    IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
    
    this.entityId = player.getEntityId();
    this.abilityId = props.getEquippedAbilitySlot(ability);
    this.customTexture = ability.getCustomTexture();
    this.state = state.ordinal();
    this.isStateForced = ability.isStateForced();
    
    if (ability instanceof IExtraUpdateData) {
      this.extraData = ((IExtraUpdateData)ability).getExtraData();
    }
    if (state == Ability.State.COOLDOWN) {
      
      this.cooldown = values[0];
      this.maxCooldown = values[1];
    }
    else if (state == Ability.State.DISABLED) {
      
      this.disableTicks = values[0];
    }
    else if (state == Ability.State.CONTINUOUS) {
      
      this.abilityType = 1;
      this.continueTime = values[0];
      this.threshold = values[1];
    }
    else if (state == Ability.State.CHARGING) {
      
      this.abilityType = 2;
      this.chargeTime = values[0];
      this.maxChargeTime = values[1];
    } 
  }

  
  public void encode(PacketBuffer buffer) {
    buffer.writeInt(this.entityId);
    buffer.writeInt(this.abilityId);
    int textureLen = this.customTexture.length();
    buffer.writeInt(textureLen);
    buffer.writeString(this.customTexture, textureLen);
    buffer.writeInt(this.abilityType);
    buffer.writeBoolean(this.isStateForced);
    
    buffer.writeBoolean((this.extraData != null));
    if (this.extraData != null) {
      buffer.writeCompoundTag(this.extraData);
    }
    buffer.writeDouble(this.cooldown);
    buffer.writeDouble(this.maxCooldown);
    buffer.writeDouble(this.disableTicks);
    buffer.writeInt(this.state);
    
    if (this.abilityType == 1) {
      
      buffer.writeDouble(this.continueTime);
      buffer.writeDouble(this.threshold);
    }
    else if (this.abilityType == 2) {
      
      buffer.writeDouble(this.chargeTime);
      buffer.writeDouble(this.maxChargeTime);
    }
    else if (this.abilityType == 3) {
      
      buffer.writeBoolean(this.isPaused);
    } 
  }

  
  public static SUpdateEquippedAbilityPacket decode(PacketBuffer buffer) {
    SUpdateEquippedAbilityPacket msg = new SUpdateEquippedAbilityPacket();
    msg.entityId = buffer.readInt();
    msg.abilityId = buffer.readInt();
    int textureLen = buffer.readInt();
    msg.customTexture = buffer.readString(textureLen);
    msg.abilityType = buffer.readInt();
    msg.isStateForced = buffer.readBoolean();
    
    boolean hasExtraData = buffer.readBoolean();
    if (hasExtraData) {
      msg.extraData = buffer.readCompoundTag();
    }
    msg.cooldown = buffer.readDouble();
    msg.maxCooldown = buffer.readDouble();
    msg.disableTicks = buffer.readDouble();
    msg.state = buffer.readInt();
    
    if (msg.abilityType == 1) {
      
      msg.continueTime = buffer.readDouble();
      msg.threshold = buffer.readDouble();
    }
    else if (msg.abilityType == 2) {
      
      msg.chargeTime = buffer.readDouble();
      msg.maxChargeTime = buffer.readDouble();
    }
    else if (msg.abilityType == 3) {
      
      msg.isPaused = buffer.readBoolean();
    } 
    
    return msg;
  }

  
  public static void handle(SUpdateEquippedAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
    if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
    {
      ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
    }


    
    ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
  }
  
  public SUpdateEquippedAbilityPacket() {}
  
  public static class ClientHandler {
    @OnlyIn(Dist.CLIENT)
    public static void handle(SUpdateEquippedAbilityPacket message) {
      Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
      if (target == null || !(target instanceof PlayerEntity) || message.abilityId < 0) {
        return;
      }
      IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
      Ability ability = props.getEquippedAbility(message.abilityId);
      
      if (ability == null) {
        return;
      }
      Ability.State state = Ability.State.values()[message.state];
      ability.setCustomTexture(message.customTexture);
      ability.setState(state);
      
      ability.setForcedState(message.isStateForced);
      
      if (message.extraData != null && ability instanceof IExtraUpdateData) {
        ((IExtraUpdateData)ability).setExtraData(message.extraData);
      }
      if (state == Ability.State.COOLDOWN) {
        
        ability.setCooldown(message.cooldown);
        ability.setMaxCooldown(message.maxCooldown);
      }
      else if (state == Ability.State.DISABLED) {
        
        ability.setDisableTicks(message.disableTicks);
      } 
      
      if (ability instanceof ContinuousAbility) {
        
        ((ContinuousAbility)ability).setContinueTime((int)message.continueTime);
        ((ContinuousAbility)ability).setThreshold((int)message.threshold);
      }
      else if (ability instanceof ChargeableAbility) {
        
        ((ChargeableAbility)ability).setChargeTime((int)message.chargeTime);
        ((ChargeableAbility)ability).setMaxChargeTime(message.maxChargeTime);
      }
      else if (ability instanceof PassiveAbility) {
        
        ((PassiveAbility)ability).setPause(message.isPaused);
      } 
    }
  }
}


