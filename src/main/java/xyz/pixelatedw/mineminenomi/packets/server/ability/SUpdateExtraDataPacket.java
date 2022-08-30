/*     */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.IExtraUpdateData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SUpdateExtraDataPacket
/*     */ {
/*     */   private int entityId;
/*     */   private String abilityId;
/*     */   private CompoundNBT extraData;
/*     */   private boolean isEquipped;
/*     */   
/*     */   public SUpdateExtraDataPacket() {}
/*     */   
/*     */   public SUpdateExtraDataPacket(PlayerEntity player, Ability ability) {
/*  37 */     this.entityId = player.getEntityId();
/*  38 */     this.abilityId = WyHelper.getResourceName(ability.getName());
/*  39 */     this.isEquipped = true;
/*     */     
/*  41 */     if (ability instanceof xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility) {
/*  42 */       this.isEquipped = false;
/*     */     }
/*  44 */     if (ability instanceof IExtraUpdateData) {
/*  45 */       this.extraData = ((IExtraUpdateData)ability).getExtraData();
/*     */     }
/*     */   }
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  50 */     buffer.writeBoolean(this.isEquipped);
/*  51 */     buffer.writeInt(this.entityId);
/*  52 */     int len = this.abilityId.length();
/*  53 */     buffer.writeInt(len);
/*  54 */     buffer.writeString(this.abilityId, len);
/*  55 */     buffer.writeBoolean((this.extraData != null));
/*  56 */     if (this.extraData != null) {
/*  57 */       buffer.writeCompoundTag(this.extraData);
/*     */     }
/*     */   }
/*     */   
/*     */   public static SUpdateExtraDataPacket decode(PacketBuffer buffer) {
/*  62 */     SUpdateExtraDataPacket msg = new SUpdateExtraDataPacket();
/*  63 */     msg.isEquipped = buffer.readBoolean();
/*  64 */     msg.entityId = buffer.readInt();
/*  65 */     int len = buffer.readInt();
/*  66 */     msg.abilityId = buffer.readString(len);
/*  67 */     boolean hasExtraData = buffer.readBoolean();
/*  68 */     if (hasExtraData)
/*  69 */       msg.extraData = buffer.readCompoundTag(); 
/*  70 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(SUpdateExtraDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/*  75 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*     */     {
/*  77 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  82 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ 
/*     */   
/*     */   public static class ClientHandler
/*     */   {
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public static void handle(SUpdateExtraDataPacket message) {
/*  90 */       if (WyHelper.isNullOrEmpty(message.abilityId)) {
/*     */         return;
/*     */       }
/*  93 */       Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
/*     */       
/*  95 */       if (target == null || !(target instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/*  98 */       ResourceLocation res = new ResourceLocation("mineminenomi", message.abilityId);
/*  99 */       Ability templateAbl = Ability.get(res);
/*     */       
/* 101 */       if (templateAbl == null) {
/*     */         return;
/*     */       }
/* 104 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/* 105 */       Ability ability = null;
/* 106 */       boolean isEquipped = message.isEquipped;
/* 107 */       if (isEquipped) {
/* 108 */         ability = props.getEquippedAbility(templateAbl);
/*     */       } else {
/* 110 */         ability = props.getUnlockedAbility(templateAbl);
/*     */       } 
/* 112 */       if (ability == null) {
/*     */         return;
/*     */       }
/* 115 */       if (message.extraData != null && ability instanceof IExtraUpdateData)
/* 116 */         ((IExtraUpdateData)ability).setExtraData(message.extraData); 
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateExtraDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */