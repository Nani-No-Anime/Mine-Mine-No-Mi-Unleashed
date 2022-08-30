/*    */ package xyz.pixelatedw.mineminenomi.packets.server.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.client.Minecraft;
/*    */ import net.minecraft.client.entity.player.ClientPlayerEntity;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.api.distmarker.Dist;
/*    */ import net.minecraftforge.api.distmarker.OnlyIn;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ 
/*    */ 
/*    */ public class SChangeCombatBarPacket
/*    */ {
/*    */   private int combatBar;
/*    */   
/*    */   public SChangeCombatBarPacket() {}
/*    */   
/*    */   public SChangeCombatBarPacket(int combatBar) {
/* 23 */     this.combatBar = combatBar;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 28 */     buffer.writeInt(this.combatBar);
/*    */   }
/*    */ 
/*    */   
/*    */   public static SChangeCombatBarPacket decode(PacketBuffer buffer) {
/* 33 */     SChangeCombatBarPacket msg = new SChangeCombatBarPacket();
/* 34 */     msg.combatBar = buffer.readInt();
/* 35 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(SChangeCombatBarPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 40 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_CLIENT)
/*    */     {
/* 42 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> ClientHandler.handle(message));
/*    */     }
/*    */ 
/*    */ 
/*    */     
/* 47 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ 
/*    */   
/*    */   public static class ClientHandler
/*    */   {
/*    */     @OnlyIn(Dist.CLIENT)
/*    */     public static void handle(SChangeCombatBarPacket message) {
/* 55 */       ClientPlayerEntity clientPlayerEntity = (Minecraft.getInstance()).player;
/* 56 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)clientPlayerEntity);
/*    */       
/* 58 */       abilityProps.setCombatBarSet(message.combatBar);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\server\ability\SChangeCombatBarPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */