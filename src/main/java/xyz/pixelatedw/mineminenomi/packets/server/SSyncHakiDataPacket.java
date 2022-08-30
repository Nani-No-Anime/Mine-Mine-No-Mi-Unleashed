/*    */ package xyz.pixelatedw.mineminenomi.packets.server;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.entity.Entity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.nbt.CompoundNBT;
/*    */ import net.minecraft.nbt.INBT;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SSyncHakiDataPacket
/*    */ {
/*    */   private int entityId;
/*    */   private INBT data;
/*    */   
/*    */   public SSyncHakiDataPacket() {}
/*    */   
/*    */   public SSyncHakiDataPacket(int entityId, IHakiData props) {
/* 27 */     this.data = (INBT)new CompoundNBT();
/* 28 */     this.data = HakiDataCapability.INSTANCE.getStorage().writeNBT(HakiDataCapability.INSTANCE, props, null);
/* 29 */     this.entityId = entityId;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.writeInt(this.entityId);
/* 35 */     buffer.writeCompoundTag((CompoundNBT)this.data);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SSyncHakiDataPacket decode(PacketBuffer buffer) {
/* 40 */     SSyncHakiDataPacket msg = new SSyncHakiDataPacket();
/* 41 */     msg.entityId = buffer.readInt();
/* 42 */     msg.data = (INBT)buffer.readCompoundTag();
/* 43 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SSyncHakiDataPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 48 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 50 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 55 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SSyncHakiDataPacket message) {
/* 63 */       Entity target = (Minecraft.getInstance()).world.getEntityByID(message.entityId);
/* 64 */       if (target == null || !(target instanceof LivingEntity)) {
/*    */         return;
/*    */       }
/* 67 */       IHakiData props = HakiDataCapability.get((LivingEntity)target);
/* 68 */       HakiDataCapability.INSTANCE.getStorage().readNBT(HakiDataCapability.INSTANCE, props, null, message.data);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\SSyncHakiDataPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */