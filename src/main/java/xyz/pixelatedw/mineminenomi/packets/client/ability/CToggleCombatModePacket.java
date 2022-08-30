/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ 
/*    */ 
/*    */ public class CToggleCombatModePacket
/*    */ {
/*    */   private boolean combatMode = false;
/*    */   
/*    */   public CToggleCombatModePacket() {}
/*    */   
/*    */   public CToggleCombatModePacket(boolean combatMode) {
/* 20 */     this.combatMode = combatMode;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 25 */     buffer.writeBoolean(this.combatMode);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CToggleCombatModePacket decode(PacketBuffer buffer) {
/* 30 */     CToggleCombatModePacket msg = new CToggleCombatModePacket();
/* 31 */     msg.combatMode = buffer.readBoolean();
/* 32 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CToggleCombatModePacket message, Supplier<NetworkEvent.Context> ctx) {
/* 37 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 39 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             props.setCombatMode(message.combatMode);
/*    */           });
/*    */     }
/* 47 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CToggleCombatModePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */