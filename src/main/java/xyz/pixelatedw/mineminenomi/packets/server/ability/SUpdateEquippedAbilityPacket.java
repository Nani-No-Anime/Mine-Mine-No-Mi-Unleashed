/*     */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ public class SUpdateEquippedAbilityPacket
/*     */ {
/*     */   private int entityId;
/*     */   private int abilityId;
/*  27 */   private String customTexture = "";
/*     */   
/*     */   private boolean isStateForced;
/*  30 */   private int abilityType = 0;
/*     */ 
/*     */   
/*     */   private CompoundNBT extraData;
/*     */ 
/*     */   
/*     */   private double cooldown;
/*     */   
/*     */   private double maxCooldown;
/*     */   
/*     */   private double disableTicks;
/*     */   
/*     */   private int state;
/*     */   
/*     */   private double continueTime;
/*     */   
/*     */   private double threshold;
/*     */   
/*     */   private double chargeTime;
/*     */   
/*     */   private double maxChargeTime;
/*     */   
/*     */   private boolean isPaused;
/*     */ 
/*     */   
/*     */   public SUpdateEquippedAbilityPacket(PlayerEntity player, Ability ability) {
/*  56 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  58 */     this.entityId = player.getEntityId();
/*  59 */     this.abilityId = props.getEquippedAbilitySlot(ability);
/*  60 */     this.customTexture = ability.getCustomTexture();
/*  61 */     this.state = ability.getState().ordinal();
/*  62 */     this.isStateForced = ability.isStateForced();
/*     */     
/*  64 */     if (ability instanceof IExtraUpdateData) {
/*  65 */       this.extraData = ((IExtraUpdateData)ability).getExtraData();
/*     */     }
/*  67 */     if (this.state == Ability.State.COOLDOWN.ordinal()) {
/*     */       
/*  69 */       this.cooldown = ability.getCooldown() / 20.0D;
/*  70 */       this.maxCooldown = ability.getMaxCooldown() / 20.0D;
/*     */     } 
/*     */     
/*  73 */     if (ability instanceof ContinuousAbility) {
/*     */       
/*  75 */       this.abilityType = 1;
/*  76 */       this.continueTime = (((ContinuousAbility)ability).getContinueTime() / 20);
/*  77 */       this.threshold = (((ContinuousAbility)ability).getThreshold() / 20);
/*     */     }
/*  79 */     else if (ability instanceof ChargeableAbility) {
/*     */       
/*  81 */       this.abilityType = 2;
/*  82 */       this.chargeTime = (((ChargeableAbility)ability).getChargeTime() / 20);
/*  83 */       this.maxChargeTime = (((ChargeableAbility)ability).getMaxChargeTime() / 20);
/*     */     }
/*  85 */     else if (ability instanceof PassiveAbility) {
/*     */       
/*  87 */       this.abilityType = 3;
/*  88 */       this.isPaused = ((PassiveAbility)ability).isPaused();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public SUpdateEquippedAbilityPacket(PlayerEntity player, Ability ability, Ability.State state, double[] values) {
/*  94 */     IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  96 */     this.entityId = player.getEntityId();
/*  97 */     this.abilityId = props.getEquippedAbilitySlot(ability);
/*  98 */     this.customTexture = ability.getCustomTexture();
/*  99 */     this.state = state.ordinal();
/* 100 */     this.isStateForced = ability.isStateForced();
/*     */     
/* 102 */     if (ability instanceof IExtraUpdateData) {
/* 103 */       this.extraData = ((IExtraUpdateData)ability).getExtraData();
/*     */     }
/* 105 */     if (state == Ability.State.COOLDOWN) {
/*     */       
/* 107 */       this.cooldown = values[0];
/* 108 */       this.maxCooldown = values[1];
/*     */     }
/* 110 */     else if (state == Ability.State.DISABLED) {
/*     */       
/* 112 */       this.disableTicks = values[0];
/*     */     }
/* 114 */     else if (state == Ability.State.CONTINUOUS) {
/*     */       
/* 116 */       this.abilityType = 1;
/* 117 */       this.continueTime = values[0];
/* 118 */       this.threshold = values[1];
/*     */     }
/* 120 */     else if (state == Ability.State.CHARGING) {
/*     */       
/* 122 */       this.abilityType = 2;
/* 123 */       this.chargeTime = values[0];
/* 124 */       this.maxChargeTime = values[1];
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/* 130 */     buffer.writeInt(this.entityId);
/* 131 */     buffer.writeInt(this.abilityId);
/* 132 */     int textureLen = this.customTexture.length();
/* 133 */     buffer.writeInt(textureLen);
/* 134 */     buffer.writeString(this.customTexture, textureLen);
/* 135 */     buffer.writeInt(this.abilityType);
/* 136 */     buffer.writeBoolean(this.isStateForced);
/*     */     
/* 138 */     buffer.writeBoolean((this.extraData != null));
/* 139 */     if (this.extraData != null) {
/* 140 */       buffer.writeCompoundTag(this.extraData);
/*     */     }
/* 142 */     buffer.writeDouble(this.cooldown);
/* 143 */     buffer.writeDouble(this.maxCooldown);
/* 144 */     buffer.writeDouble(this.disableTicks);
/* 145 */     buffer.writeInt(this.state);
/*     */     
/* 147 */     if (this.abilityType == 1) {
/*     */       
/* 149 */       buffer.writeDouble(this.continueTime);
/* 150 */       buffer.writeDouble(this.threshold);
/*     */     }
/* 152 */     else if (this.abilityType == 2) {
/*     */       
/* 154 */       buffer.writeDouble(this.chargeTime);
/* 155 */       buffer.writeDouble(this.maxChargeTime);
/*     */     }
/* 157 */     else if (this.abilityType == 3) {
/*     */       
/* 159 */       buffer.writeBoolean(this.isPaused);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static SUpdateEquippedAbilityPacket decode(PacketBuffer buffer) {
/* 165 */     SUpdateEquippedAbilityPacket msg = new SUpdateEquippedAbilityPacket();
/* 166 */     msg.entityId = buffer.readInt();
/* 167 */     msg.abilityId = buffer.readInt();
/* 168 */     int textureLen = buffer.readInt();
/* 169 */     msg.customTexture = buffer.readString(textureLen);
/* 170 */     msg.abilityType = buffer.readInt();
/* 171 */     msg.isStateForced = buffer.readBoolean();
/*     */     
/* 173 */     boolean hasExtraData = buffer.readBoolean();
/* 174 */     if (hasExtraData) {
/* 175 */       msg.extraData = buffer.readCompoundTag();
/*     */     }
/* 177 */     msg.cooldown = buffer.readDouble();
/* 178 */     msg.maxCooldown = buffer.readDouble();
/* 179 */     msg.disableTicks = buffer.readDouble();
/* 180 */     msg.state = buffer.readInt();
/*     */     
/* 182 */     if (msg.abilityType == 1) {
/*     */       
/* 184 */       msg.continueTime = buffer.readDouble();
/* 185 */       msg.threshold = buffer.readDouble();
/*     */     }
/* 187 */     else if (msg.abilityType == 2) {
/*     */       
/* 189 */       msg.chargeTime = buffer.readDouble();
/* 190 */       msg.maxChargeTime = buffer.readDouble();
/*     */     }
/* 192 */     else if (msg.abilityType == 3) {
/*     */       
/* 194 */       msg.isPaused = buffer.readBoolean();
/*     */     } 
/*     */     
/* 197 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(SUpdateEquippedAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 202 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*     */     {
/* 204 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 209 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */   
/*     */   public SUpdateEquippedAbilityPacket() {}
/*     */   
/*     */   public static class ClientHandler {
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public static void handle(SUpdateEquippedAbilityPacket message) {
/* 217 */       Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
/* 218 */       if (target == null || !(target instanceof PlayerEntity) || message.abilityId < 0) {
/*     */         return;
/*     */       }
/* 221 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/* 222 */       Ability ability = props.getEquippedAbility(message.abilityId);
/*     */       
/* 224 */       if (ability == null) {
/*     */         return;
/*     */       }
/* 227 */       Ability.State state = Ability.State.values()[message.state];
/* 228 */       ability.setCustomTexture(message.customTexture);
/* 229 */       ability.setState(state);
/*     */       
/* 231 */       ability.setForcedState(message.isStateForced);
/*     */       
/* 233 */       if (message.extraData != null && ability instanceof IExtraUpdateData) {
/* 234 */         ((IExtraUpdateData)ability).setExtraData(message.extraData);
/*     */       }
/* 236 */       if (state == Ability.State.COOLDOWN) {
/*     */         
/* 238 */         ability.setCooldown(message.cooldown);
/* 239 */         ability.setMaxCooldown(message.maxCooldown);
/*     */       }
/* 241 */       else if (state == Ability.State.DISABLED) {
/*     */         
/* 243 */         ability.setDisableTicks(message.disableTicks);
/*     */       } 
/*     */       
/* 246 */       if (ability instanceof ContinuousAbility) {
/*     */         
/* 248 */         ((ContinuousAbility)ability).setContinueTime((int)message.continueTime);
/* 249 */         ((ContinuousAbility)ability).setThreshold((int)message.threshold);
/*     */       }
/* 251 */       else if (ability instanceof ChargeableAbility) {
/*     */         
/* 253 */         ((ChargeableAbility)ability).setChargeTime((int)message.chargeTime);
/* 254 */         ((ChargeableAbility)ability).setMaxChargeTime(message.maxChargeTime);
/*     */       }
/* 256 */       else if (ability instanceof PassiveAbility) {
/*     */         
/* 258 */         ((PassiveAbility)ability).setPause(message.isPaused);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateEquippedAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */