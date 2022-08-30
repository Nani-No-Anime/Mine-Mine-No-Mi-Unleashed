/*    */ package xyz.pixelatedw.mineminenomi.packets.client.crew;
/*    */ 
/*    */ import java.util.function.Supplier;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.network.PacketBuffer;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraft.util.text.TranslationTextComponent;
/*    */ import net.minecraftforge.fml.network.NetworkDirection;
/*    */ import net.minecraftforge.fml.network.NetworkEvent;
/*    */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModI18n;
/*    */ import xyz.pixelatedw.mineminenomi.init.ModItems;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ 
/*    */ public class CCreateCrewPacket
/*    */ {
/*    */   private String name;
/*    */   
/*    */   public CCreateCrewPacket() {}
/*    */   
/*    */   public CCreateCrewPacket(String name) {
/* 32 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void encode(PacketBuffer buffer) {
/* 37 */     buffer.writeInt(this.name.length());
/* 38 */     buffer.writeString(this.name);
/*    */   }
/*    */ 
/*    */   
/*    */   public static CCreateCrewPacket decode(PacketBuffer buffer) {
/* 43 */     CCreateCrewPacket msg = new CCreateCrewPacket();
/* 44 */     int len = buffer.readInt();
/* 45 */     msg.name = buffer.readString(len);
/* 46 */     return msg;
/*    */   }
/*    */ 
/*    */   
/*    */   public static void handle(CCreateCrewPacket message, Supplier<NetworkEvent.Context> ctx) {
/* 51 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*    */     {
/* 53 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*    */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*    */             
/*    */             IEntityStats props = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
/*    */             
/*    */             ExtendedWorldData worldProps = ExtendedWorldData.get(((PlayerEntity)serverPlayerEntity).world);
/* 59 */             boolean hasSakeBottle = (!serverPlayerEntity.getHeldItemMainhand().isEmpty() && serverPlayerEntity.getHeldItemMainhand().getItem().equals(ModItems.SAKE_BOTTLE));
/*    */             
/*    */             boolean isAlreadyInCrew = (worldProps.getCrewWithMember(serverPlayerEntity.getUniqueID()) != null);
/*    */             
/*    */             if (!hasSakeBottle || isAlreadyInCrew || !props.isPirate()) {
/*    */               return;
/*    */             }
/*    */             
/*    */             Crew crew = new Crew(message.name, (LivingEntity)serverPlayerEntity);
/*    */             
/*    */             worldProps.addCrew(crew);
/*    */             
/*    */             crew.create(((PlayerEntity)serverPlayerEntity).world);
/*    */             if (CommonConfig.INSTANCE.isCrewWorldMessageEnabled()) {
/*    */               TranslationTextComponent newCrewMsg = new TranslationTextComponent(ModI18n.CREW_MESSAGE_NEW_CREW, new Object[] { message.name });
/*    */               for (PlayerEntity target : ((PlayerEntity)serverPlayerEntity).world.getPlayers()) {
/*    */                 target.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.GOLD + newCrewMsg.getFormattedText()));
/*    */               }
/*    */             } 
/*    */             WyNetwork.sendToAll(new SSyncWorldDataPacket(worldProps));
/*    */           });
/*    */     }
/* 81 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\crew\CCreateCrewPacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */