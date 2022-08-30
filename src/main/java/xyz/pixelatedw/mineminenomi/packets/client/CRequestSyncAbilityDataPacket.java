/*    */ package xyz.pixelatedw.mineminenomi.packets.client;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ui.SOpenAbilitySelectionScreenPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*    */ 
/*    */ public class CRequestSyncAbilityDataPacket
/*    */ {
/*    */   private boolean openScreen;
/*    */   
/*    */   public CRequestSyncAbilityDataPacket() {}
/*    */   
/*    */   public CRequestSyncAbilityDataPacket(boolean openScreen) {
/* 23 */     this.openScreen = openScreen;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeBoolean(this.openScreen);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CRequestSyncAbilityDataPacket decode(PacketBuffer buffer) {
/* 33 */     CRequestSyncAbilityDataPacket msg = new CRequestSyncAbilityDataPacket();
/* 34 */     msg.openScreen = buffer.readBoolean();
/* 35 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CRequestSyncAbilityDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData props = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             WyNetwork.sendTo(new SSyncAbilityDataPacket(serverPlayerEntity.getEntityId(), props), (PlayerEntity)serverPlayerEntity);
/*    */             if (message.openScreen) {
/*    */               WyNetwork.sendTo(new SOpenAbilitySelectionScreenPacket(), (PlayerEntity)serverPlayerEntity);
/*    */             }
/*    */           });
/*    */     }
/* 53 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\CRequestSyncAbilityDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */