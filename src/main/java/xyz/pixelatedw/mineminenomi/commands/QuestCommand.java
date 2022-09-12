package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.api.commands.QuestArgument;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class QuestCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("quest").requires(source -> source.hasPermissionLevel(3));
    
    ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
      .then(Commands.literal("finish")
        .then(Commands.argument("quest", (ArgumentType)QuestArgument.quest())
          .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
            .executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target")))))))
      .then(Commands.literal("give")
        .then(Commands.argument("quest", (ArgumentType)QuestArgument.quest())
          .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
            .executes(context -> giveQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target")))))))
      .then(Commands.literal("unfinish")
        .then(Commands.argument("quest", (ArgumentType)QuestArgument.quest())
          .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
            .executes(context -> unfinishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target")))))))
      .then(Commands.literal("remove")
        .then(Commands.argument("quest", (ArgumentType)QuestArgument.quest())
          .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
            .executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))));
    
    dispatcher.register(builder);
  }

  
  private static int unfinishQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player) {
    IQuestData props = QuestDataCapability.get((PlayerEntity)player);
    IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
    
    if (props.hasFinishedQuest(quest)) {
      
      props.removeFinishedQuest(quest);
      AbilityHelper.validateStyleMoves((PlayerEntity)player);
      WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityData), (PlayerEntity)player);
    } else {
      
      player.sendMessage((ITextComponent)new StringTextComponent("You haven't finished this quest!"));
    } 
    return 1;
  }

  
  private static int finishQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player) {
    IQuestData props = QuestDataCapability.get((PlayerEntity)player);
    
    if (props.hasInProgressQuest(quest) && quest.triggerCompleteEvent((PlayerEntity)player)) {
      
      props.addFinishedQuest(quest);
      props.removeInProgressQuest(quest);
      WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
    }
    else if (!props.hasInProgressQuest(quest)) {
      player.sendMessage((ITextComponent)new StringTextComponent("You don't have this quest!"));
    } 
    return 1;
  }

  
  private static int giveQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player) {
    IQuestData props = QuestDataCapability.get((PlayerEntity)player);
    
    if (!props.hasInProgressQuest(quest)) {
      
      props.addInProgressQuest(quest.create());
      WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
    } else {
      
      player.sendMessage((ITextComponent)new StringTextComponent("You aleady have this quest!"));
    } 
    return 1;
  }

  
  private static int removeQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player) {
    IQuestData props = QuestDataCapability.get((PlayerEntity)player);
    
    if (props.hasInProgressQuest(quest)) {
      
      props.removeInProgressQuest(quest);
      props.removeFinishedQuest(quest);
      WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
    } else {
      
      player.sendMessage((ITextComponent)new StringTextComponent("You don't have this quest!"));
    } 
    return 1;
  }
}


