/*    */ package xyz.pixelatedw.mineminenomi.packets.client.ability;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.ResourceLocation;
/*    */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class CEquipAbilityPacket
/*    */ {
/*    */   private int slot;
/*    */   private String abilityName;
/*    */   
/*    */   public CEquipAbilityPacket() {}
/*    */   
/*    */   public CEquipAbilityPacket(int id, Ability ability) {
/* 28 */     this.slot = id;
/* 29 */     this.abilityName = WyHelper.getResourceName(ability.getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 34 */     buffer.writeInt(this.slot);
/* 35 */     buffer.writeInt(this.abilityName.length());
/* 36 */     buffer.writeString(this.abilityName, this.abilityName.length());
/*    */   }
/*    */ 
/*    */   
/*    */   public static CEquipAbilityPacket decode(PacketBuffer buffer) {
/* 41 */     CEquipAbilityPacket msg = new CEquipAbilityPacket();
/* 42 */     msg.slot = buffer.readInt();
/* 43 */     int len = buffer.readInt();
/* 44 */     msg.abilityName = buffer.readString(len);
/* 45 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CEquipAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 50 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 52 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             Ability ability = ((Ability)GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation("mineminenomi", message.abilityName))).create();
/*    */             
/*    */             abilityDataProps.setEquippedAbility(message.slot, ability);
/*    */             WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)serverPlayerEntity, ability), (LivingEntity)serverPlayerEntity);
/*    */           });
/*    */     }
/* 63 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CEquipAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */