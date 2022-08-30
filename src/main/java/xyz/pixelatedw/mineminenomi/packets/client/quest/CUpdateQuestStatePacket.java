/*     */ package xyz.pixelatedw.mineminenomi.packets.client.quest;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.Optional;
/*     */ import java.util.function.Supplier;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.nbt.CompoundNBT;
/*     */ import net.minecraft.nbt.INBT;
/*     */ import net.minecraft.network.PacketBuffer;
/*     */ import net.minecraft.util.ResourceLocation;
/*     */ import net.minecraftforge.fml.common.registry.GameRegistry;
/*     */ import net.minecraftforge.fml.network.NetworkDirection;
/*     */ import net.minecraftforge.fml.network.NetworkEvent;
/*     */ import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ 
/*     */ public class CUpdateQuestStatePacket
/*     */ {
/*     */   private INBT data;
/*     */   private String questId;
/*     */   
/*     */   public CUpdateQuestStatePacket() {}
/*     */   
/*     */   public CUpdateQuestStatePacket(Quest quest) {
/*  35 */     this.questId = quest.getId();
/*     */   }
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public CUpdateQuestStatePacket(IQuestData props) {
/*  41 */     this.data = (INBT)new CompoundNBT();
/*  42 */     this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void encode(PacketBuffer buffer) {
/*  47 */     int len = this.questId.length();
/*  48 */     buffer.writeInt(len);
/*  49 */     buffer.writeString(this.questId, len);
/*     */   }
/*     */ 
/*     */   
/*     */   public static CUpdateQuestStatePacket decode(PacketBuffer buffer) {
/*  54 */     CUpdateQuestStatePacket msg = new CUpdateQuestStatePacket();
/*  55 */     int len = buffer.readInt();
/*  56 */     msg.questId = buffer.readString(len);
/*  57 */     return msg;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void handle(CUpdateQuestStatePacket message, Supplier<NetworkEvent.Context> ctx) {
/*  62 */     if (((NetworkEvent.Context)ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER)
/*     */     {
/*  64 */       ((NetworkEvent.Context)ctx.get()).enqueueWork(() -> {
/*     */             if (WyHelper.isNullOrEmpty(message.questId)) {
/*     */               return;
/*     */             }
/*     */             
/*     */             ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context)ctx.get()).getSender();
/*     */             
/*     */             Optional<TrainerEntity> trainer = WyHelper.getEntitiesNear(serverPlayerEntity.getPosition(), ((PlayerEntity)serverPlayerEntity).world, 10.0D, new Class[] { TrainerEntity.class }).stream().findFirst();
/*     */             
/*     */             if (!trainer.isPresent()) {
/*     */               return;
/*     */             }
/*     */             
/*     */             IQuestData props = QuestDataCapability.get((PlayerEntity)serverPlayerEntity);
/*     */             
/*     */             Quest quest = (Quest)GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation("mineminenomi", message.questId));
/*     */             
/*     */             if (quest == null || quest.isLocked(props)) {
/*     */               return;
/*     */             }
/*     */             
/*     */             if (!Arrays.<Quest>stream(((TrainerEntity)trainer.get()).getAvailableQuests((PlayerEntity)serverPlayerEntity)).anyMatch((Quest cQuest)-> !cQuest.isComplete() )) {
/*     */               return;
/*     */             }
/*     */             
/*     */             boolean updateClient = false;
/*     */             
/*     */             if (props.hasInProgressQuest(quest) && props.getInProgressQuest(quest).isComplete() && props.getInProgressQuest(quest).triggerCompleteEvent((PlayerEntity)serverPlayerEntity)) {
/*     */               props.addFinishedQuest(quest);
/*     */               
/*     */               props.removeInProgressQuest(quest);
/*     */               
/*     */               updateClient = true;
/*     */             } else if (!props.hasInProgressQuest(quest) && quest.triggerStartEvent((PlayerEntity)serverPlayerEntity)) {
/*     */               props.addInProgressQuest(quest);
/*     */               updateClient = true;
/*     */             } 
/*     */             if (updateClient) {
/*     */               WyNetwork.sendTo(new SSyncQuestDataPacket(serverPlayerEntity.getEntityId(), props), (PlayerEntity)serverPlayerEntity);
/*     */               WyNetwork.sendTo(new SSyncAbilityDataPacket(serverPlayerEntity.getEntityId(), AbilityDataCapability.get((LivingEntity)serverPlayerEntity)), (PlayerEntity)serverPlayerEntity);
/*     */             } 
/*     */           });
/*     */     }
/* 107 */     ((NetworkEvent.Context)ctx.get()).setPacketHandled(true);
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\packets\client\quest\CUpdateQuestStatePacket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */