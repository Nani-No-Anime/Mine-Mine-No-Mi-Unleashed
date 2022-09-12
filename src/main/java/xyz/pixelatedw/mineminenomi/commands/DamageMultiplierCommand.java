package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;

public class DamageMultiplierCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("damagem").requires(source -> source.hasPermissionLevel(2));
    
    builder
      .then(Commands.argument("multiplier", (ArgumentType)DoubleArgumentType.doubleArg(0.0D, 10.0D))
        .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
          .executes(context -> applyMultiplier(context, DoubleArgumentType.getDouble(context, "multiplier"), EntityArgument.getPlayer(context, "target")))));
    
    dispatcher.register(builder);
  }

  
  private static int applyMultiplier(CommandContext<CommandSource> context, double multiplier, ServerPlayerEntity player) {
    IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
    
    props.setDamageMultiplier(multiplier);
    
    ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "Damage Multiplier set to " + props.getDamageMultiplier() + " for " + player.getDisplayName().getFormattedText()), true);
    
    return 1;
  }
}


