/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class CRemoveAbilityPacket
/*    */ {
/*    */   private int slot;
/*    */   
/*    */   public CRemoveAbilityPacket() {}
/*    */   
/*    */   public CRemoveAbilityPacket(int id) {
/* 25 */     this.slot = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 30 */     buffer.writeInt(this.slot);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CRemoveAbilityPacket decode(PacketBuffer buffer) {
/* 35 */     CRemoveAbilityPacket msg = new CRemoveAbilityPacket();
/* 36 */     msg.slot = buffer.readInt();
/* 37 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CRemoveAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 42 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 44 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             Ability ability = abilityDataProps.getEquippedAbility(message.slot);
/*    */             if (ability == null) {
/*    */               return;
/*    */             }
/*    */             if (ability instanceof ContinuousAbility && ((ContinuousAbility)ability).isContinuous()) {
/*    */               ((ContinuousAbility)ability).endContinuity((PlayerEntity)serverPlayerEntity);
/*    */             } else if (ability instanceof ChargeableAbility && ((ChargeableAbility)ability).isCharging()) {
/*    */               ((ChargeableAbility)ability).endCharging((PlayerEntity)serverPlayerEntity);
/*    */             } else if (ability.isOnCooldown()) {
/*    */               ability.stopCooldown((PlayerEntity)serverPlayerEntity);
/*    */             } 
/*    */             abilityDataProps.setEquippedAbility(message.slot, null);
/*    */             if (abilityDataProps.getEquippedAbilitySlot(ability) >= 0) {
/*    */               WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)serverPlayerEntity, ability), (LivingEntity)serverPlayerEntity);
/*    */             }
/*    */           });
/*    */     }
/* 66 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CRemoveAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */