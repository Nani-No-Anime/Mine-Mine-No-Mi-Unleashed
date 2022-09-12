package xyz.pixelatedw.mineminenomi.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.Collection;

public class BellyCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("belly").requires(source -> source.hasPermissionLevel(2));
    
    builder
      .then(((RequiredArgumentBuilder)Commands.argument("amount", (ArgumentType)LongArgumentType.longArg(-999999999L, 999999999L))
        .executes(context -> alterBelly(context, LongArgumentType.getLong(context, "amount"), getDefaultCollection(context))))
        .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
          .executes(context -> alterBelly(context, LongArgumentType.getLong(context, "amount"), EntityArgument.getPlayers(context, "targets")))));
    
    dispatcher.register(builder);
  }

  
  private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
    return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
  }

  
  private static int alterBelly(CommandContext<CommandSource> context, long amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
    for (ServerPlayerEntity player : targets) {
      
      IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
      
      entityStatsProps.alterBelly(amount);
      WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (PlayerEntity)player);
      
      ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0L) ? "+" : "") + amount + " belly for " + player.getDisplayName().getFormattedText()), true);
    } 

    
    return 1;
  }
}


