package xyz.pixelatedw.mineminenomi.commands;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Collection;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.commands.HakiTypeArgument;
import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import net.minecraft.util.text.TextFormatting;

public class HakiExpCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("hakiexp").requires(source -> source.hasPermissionLevel(2));
    
    int max = CommonConfig.INSTANCE.getHakiExpLimit();
    
    builder
      .then(Commands.argument("type", (ArgumentType)HakiTypeArgument.hakiType())
        .then(((RequiredArgumentBuilder)Commands.argument("amount", (ArgumentType)FloatArgumentType.floatArg(-max, max))
          .executes(context -> alterHakiExp(context, (HakiType)context.getArgument("type", HakiType.class), FloatArgumentType.getFloat(context, "amount"), getDefaultCollection(context))))
          .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
            .executes(context -> alterHakiExp(context, (HakiType)context.getArgument("type", HakiType.class), FloatArgumentType.getFloat(context, "amount"), EntityArgument.getPlayers(context, "targets"))))));
    
    dispatcher.register(builder);
  }

  
  private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
    return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
  }

  
  private static int alterHakiExp(CommandContext<CommandSource> context, HakiType hakiType, float amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
    for (ServerPlayerEntity player : targets) {
      
      IHakiData props = HakiDataCapability.get((LivingEntity)player);
      
      if (hakiType == HakiType.IMBUING) {
        props.alterBusoshokuImbuingHakiExp(amount);
      } else if (hakiType == HakiType.HARDENING) {
        props.alterBusoshokuHardeningHakiExp(amount);
      } else if (hakiType == HakiType.KENBUNSHOKU) {
        props.alterKenbunshokuHakiExp(amount);
      }  WyNetwork.sendTo(new SSyncHakiDataPacket(player.getEntityId(), props), (PlayerEntity)player);
      
      HakiExpEvent e = new HakiExpEvent((PlayerEntity)player, amount, hakiType);
      if (MinecraftForge.EVENT_BUS.post((Event)e)) {
        return 1;
      }
      ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0.0F) ? "+" : "") + amount + " haki exp for " + player.getDisplayName().getFormattedText()), true);
    } 

    
    return 1;
  }
}


