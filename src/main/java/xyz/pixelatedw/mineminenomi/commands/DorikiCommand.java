package xyz.pixelatedw.mineminenomi.commands;

import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.util.Collection;

public class DorikiCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("doriki").requires(source -> source.hasPermissionLevel(2));
    
    int min = -CommonConfig.INSTANCE.getDorikiLimit();
    int max = CommonConfig.INSTANCE.getDorikiLimit();
    
    builder
      .then(((RequiredArgumentBuilder)Commands.argument("amount", (ArgumentType)IntegerArgumentType.integer(min, max))
        .executes(context -> alterDoriki(context, IntegerArgumentType.getInteger(context, "amount"), getDefaultCollection(context))))
        .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
          .executes(context -> alterDoriki(context, IntegerArgumentType.getInteger(context, "amount"), EntityArgument.getPlayers(context, "targets")))));
    
    dispatcher.register(builder);
  }

  
  private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
    return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
  }

  
  private static int alterDoriki(CommandContext<CommandSource> context, int amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
    for (ServerPlayerEntity player : targets) {
      
      IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
      
      entityStatsProps.alterDoriki(amount);
      WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (PlayerEntity)player);
      
      DorikiEvent e = new DorikiEvent((PlayerEntity)player, amount);
      MinecraftForge.EVENT_BUS.post((Event)e);
      
      ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0) ? "+" : "") + amount + " doriki for " + player.getDisplayName().getFormattedText()), true);
    } 
    
    return 1;
  }
}


