package xyz.pixelatedw.mineminenomi.packets.client.quest;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.network.NetworkDirection;
import net.minecraftforge.fml.network.NetworkEvent;
import xyz.pixelatedw.mineminenomi.api.entities.TrainerEntity;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Supplier;

public class CUpdateQuestStatePacket {
    private INBT data;
    private String questId;

    public CUpdateQuestStatePacket() {}

    public CUpdateQuestStatePacket(Quest quest) { this.questId = quest.getId(); }

    @Deprecated
    public CUpdateQuestStatePacket(IQuestData props) {
        this.data = (INBT) new CompoundNBT();
        this.data = QuestDataCapability.INSTANCE.getStorage().writeNBT(QuestDataCapability.INSTANCE, props, null);
    }

    public void encode(PacketBuffer buffer) {
        int len = this.questId.length();
        buffer.writeInt(len);
        buffer.writeString(this.questId, len);
    }

    public static CUpdateQuestStatePacket decode(PacketBuffer buffer) {
        CUpdateQuestStatePacket msg = new CUpdateQuestStatePacket();
        int len = buffer.readInt();
        msg.questId = buffer.readString(len);
        return msg;
    }

    public static void handle(CUpdateQuestStatePacket message, Supplier<NetworkEvent.Context> ctx) {
        if (((NetworkEvent.Context) ctx.get()).getDirection() == NetworkDirection.PLAY_TO_SERVER) {
            ((NetworkEvent.Context) ctx.get()).enqueueWork(() -> {
                if (WyHelper.isNullOrEmpty(message.questId)) {
                    return;
                }
                ServerPlayerEntity serverPlayerEntity = ((NetworkEvent.Context) ctx.get()).getSender();
                Optional<TrainerEntity> trainer = WyHelper.getEntitiesNear(serverPlayerEntity.getPosition(), ((PlayerEntity) serverPlayerEntity).world, 10.0D, new Class[] { TrainerEntity.class }).stream().findFirst();
                if (!trainer.isPresent()) {
                    return;
                }
                IQuestData props = QuestDataCapability.get((PlayerEntity) serverPlayerEntity);
                Quest quest = (Quest) GameRegistry.findRegistry(Quest.class).getValue(new ResourceLocation("mineminenomi", message.questId));
                if (quest == null || quest.isLocked(props)) {
                    return;
                }
                if (Arrays.stream(((TrainerEntity) trainer.get()).getAvailableQuests(serverPlayerEntity)).anyMatch((q) -> {
                    return q.equals(quest);
                })) {
                    return;
                }
                boolean updateClient = false;
                if (props.hasInProgressQuest(quest) && props.getInProgressQuest(quest).isComplete() && props.getInProgressQuest(quest).triggerCompleteEvent((PlayerEntity) serverPlayerEntity)) {
                    props.addFinishedQuest(quest);
                    props.removeInProgressQuest(quest);
                    updateClient = true;
                } else if (!props.hasInProgressQuest(quest) && quest.triggerStartEvent((PlayerEntity) serverPlayerEntity)) {
                    props.addInProgressQuest(quest);
                    updateClient = true;
                }
                if (updateClient) {
                    WyNetwork.sendTo(new SSyncQuestDataPacket(serverPlayerEntity.getEntityId(), props), (PlayerEntity) serverPlayerEntity);
                    WyNetwork.sendTo(new SSyncAbilityDataPacket(serverPlayerEntity.getEntityId(), AbilityDataCapability.get((LivingEntity) serverPlayerEntity)), (PlayerEntity) serverPlayerEntity);
                }
            });
        }
        ((NetworkEvent.Context) ctx.get()).setPacketHandled(true);
    }
}