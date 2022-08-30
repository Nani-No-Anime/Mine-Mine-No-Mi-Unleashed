/*     */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*     */ 
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.client.Minecraft;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.api.distmarker.Dist;
/*     */ import net.minecraftforge.api.distmarker.OnlyIn;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SUpdateCustomTexturePacket
/*     */ {
/*     */   private int entityId;
/*     */   private String abilityId;
/*  25 */   private String customTexture = "";
/*  26 */   private String displayName = "";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SUpdateCustomTexturePacket(PlayerEntity player, Ability ability) {
/*  34 */     this.entityId = player.getEntityId();
/*  35 */     this.abilityId = WyHelper.getResourceName(ability.getName());
/*  36 */     this.customTexture = ability.getCustomTexture();
/*  37 */     this.displayName = ability.getDisplayName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  42 */     buffer.writeInt(this.entityId);
/*  43 */     int len = this.abilityId.length();
/*  44 */     buffer.writeInt(len);
/*  45 */     buffer.writeString(this.abilityId, len);
/*  46 */     int textureLen = this.customTexture.length();
/*  47 */     buffer.writeInt(textureLen);
/*  48 */     buffer.writeString(this.customTexture, textureLen);
/*  49 */     int displayLen = this.displayName.length();
/*  50 */     buffer.writeInt(displayLen);
/*  51 */     buffer.writeString(this.displayName, displayLen);
/*     */   }
/*     */ 
/*     */   
/*     */   public static SUpdateCustomTexturePacket decode(PacketBuffer buffer) {
/*  56 */     SUpdateCustomTexturePacket msg = new SUpdateCustomTexturePacket();
/*  57 */     msg.entityId = buffer.readInt();
/*  58 */     int len = buffer.readInt();
/*  59 */     msg.abilityId = buffer.readString(len);
/*  60 */     int textureLen = buffer.readInt();
/*  61 */     msg.customTexture = buffer.readString(textureLen);
/*  62 */     int displayLen = buffer.readInt();
/*  63 */     msg.displayName = buffer.readString(displayLen);
/*  64 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(SUpdateCustomTexturePacket message, Supplier<NetworkEvent.Context> ctx) {
/*  69 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*     */     {
/*  71 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  76 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */   
/*     */   public SUpdateCustomTexturePacket() {}
/*     */   
/*     */   public static class ClientHandler {
/*     */     @OnlyIn(Dist.CLIENT)
/*     */     public static void handle(SUpdateCustomTexturePacket message) {
/*  84 */       if (WyHelper.isNullOrEmpty(message.abilityId)) {
/*     */         return;
/*     */       }
/*  87 */       Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
/*     */       
/*  89 */       if (target == null || !(target instanceof PlayerEntity)) {
/*     */         return;
/*     */       }
/*  92 */       ResourceLocation res = new ResourceLocation("mineminenomi", message.abilityId);
/*  93 */       Ability templateAbl = Ability.get(res);
/*     */       
/*  95 */       if (templateAbl == null) {
/*     */         return;
/*     */       }
/*  98 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)target);
/*  99 */       Ability ability = props.getEquippedAbility(templateAbl);
/*     */       
/* 101 */       if (ability == null) {
/*     */         return;
/*     */       }
/* 104 */       ability.setDisplayName(message.displayName);
/* 105 */       ability.setCustomTexture(message.customTexture);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SUpdateCustomTexturePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */