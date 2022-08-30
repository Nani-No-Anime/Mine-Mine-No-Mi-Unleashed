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
/*    */ import xyz.pixelatedw.mineminenomi.wypi.abilities.PassiveAbility;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class CTogglePassiveAbilityPacket
/*    */ {
/*    */   private String abilityName;
/*    */   private boolean flag;
/*    */   
/*    */   public CTogglePassiveAbilityPacket() {}
/*    */   
/*    */   public CTogglePassiveAbilityPacket(Ability ability, boolean flag) {
/* 29 */     this.abilityName = WyHelper.getResourceName(ability.getName());
/* 30 */     this.flag = flag;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 35 */     buffer.writeInt(this.abilityName.length());
/* 36 */     buffer.writeString(this.abilityName, this.abilityName.length());
/* 37 */     buffer.writeBoolean(this.flag);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CTogglePassiveAbilityPacket decode(PacketBuffer buffer) {
/* 42 */     CTogglePassiveAbilityPacket msg = new CTogglePassiveAbilityPacket();
/* 43 */     int len = buffer.readInt();
/* 44 */     msg.abilityName = buffer.readString(len);
/* 45 */     msg.flag = buffer.readBoolean();
/* 46 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CTogglePassiveAbilityPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 51 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 53 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             Ability ability = ((Ability)GameRegistry.findRegistry(Ability.class).getValue(new ResourceLocation("mineminenomi", message.abilityName))).create();
/*    */             
/*    */             Ability unlockedAbility = abilityDataProps.getUnlockedAbility(ability);
/*    */             
/*    */             if (unlockedAbility instanceof PassiveAbility) {
/*    */               PassiveAbility passive = (PassiveAbility)unlockedAbility;
/*    */               passive.setPause(message.flag);
/*    */               WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)serverPlayerEntity, (Ability)passive), (LivingEntity)serverPlayerEntity);
/*    */             } 
/*    */           });
/*    */     }
/* 69 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\ability\CTogglePassiveAbilityPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */