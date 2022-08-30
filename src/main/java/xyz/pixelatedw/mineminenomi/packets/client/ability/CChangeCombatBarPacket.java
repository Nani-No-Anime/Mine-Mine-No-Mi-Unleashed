/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SChangeCombatBarPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class CChangeCombatBarPacket
/*    */ {
/* 18 */   private int dir = 0;
/*    */ 
/*    */   
/*    */   public CChangeCombatBarPacket() {}
/*    */   
/*    */   public CChangeCombatBarPacket(int dir) {
/* 24 */     this.dir = dir;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 29 */     buffer.writeInt(this.dir);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CChangeCombatBarPacket decode(PacketBuffer buffer) {
/* 34 */     CChangeCombatBarPacket msg = new CChangeCombatBarPacket();
/* 35 */     msg.dir = buffer.readInt();
/* 36 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CChangeCombatBarPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 41 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 43 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */ 
/*    */             
/*    */             IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */ 
/*    */             
/*    */             if (message.dir == 0) {
/*    */               if ((abilityProps.getCombatBarSet() + 1) * 8 < 8 * CommonConfig.INSTANCE.getAbilityBars()) {
/*    */                 abilityProps.nextCombatBarSet();
/*    */               } else {
/*    */                 abilityProps.setCombatBarSet(0);
/*    */               } 
/*    */             } else if (abilityProps.getCombatBarSet() > 0) {
/*    */               abilityProps.prevCombatBarSet();
/*    */             } else {
/*    */               abilityProps.setCombatBarSet(CommonConfig.INSTANCE.getAbilityBars() - 1);
/*    */             } 
/*    */ 
/*    */             
/*    */             WyNetwork.sendTo(new SChangeCombatBarPacket(abilityProps.getCombatBarSet()), (PlayerEntity)serverPlayerEntity);
/*    */           });
/*    */     }
/*    */     
/* 67 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CChangeCombatBarPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */