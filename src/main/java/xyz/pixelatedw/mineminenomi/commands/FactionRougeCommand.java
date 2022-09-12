package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;

public class FactionRougeCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("gorogue").requires(source -> source.hasPermissionLevel(0));
    
    builder.executes(context -> goRogue(context, ((CommandSource)context.getSource()).asPlayer()));
    
    dispatcher.register(builder);
  }

  
  private static int goRogue(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    boolean rogue = EntityStatsCapability.get((LivingEntity)player).isRogue();
    EntityStatsCapability.get((LivingEntity)player).setRogue(!rogue);
    player.sendMessage((ITextComponent)new StringTextComponent((rogue ? "Disabled" : "Enabled") + " rogue mode!"));
    return 1;
  }
}


