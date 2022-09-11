package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import xyz.pixelatedw.mineminenomi.api.events.IssueBountyEvent;
import xyz.pixelatedw.mineminenomi.api.helpers.FactionHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;

public class IssueBountyCommand
{
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = Commands.literal("issuebounty");
    
    builder
      .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
        .then(Commands.argument("bounty", (ArgumentType)LongArgumentType.longArg())
          .executes(context -> issueBounty(context, EntityArgument.getPlayer(context, "target"), LongArgumentType.getLong(context, "bounty")))));
    
    dispatcher.register(builder);
  }


  
  private static int issueBounty(CommandContext<CommandSource> context, ServerPlayerEntity player, long bounty) {
    try {
      ServerPlayerEntity serverPlayerEntity = ((CommandSource)context.getSource()).asPlayer();
      
      IEntityStats propz = EntityStatsCapability.get((LivingEntity)serverPlayerEntity);
      IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
      
      IssueBountyEvent event = new IssueBountyEvent((PlayerEntity)player, (PlayerEntity)serverPlayerEntity, bounty);
      MinecraftForge.EVENT_BUS.post(event);
      
      if (propz.isMarine() && ((event.getResult() == Event.Result.DEFAULT && propz.hasMarineRank(FactionHelper.MarineRank.CAPTAIN)) || event.getResult() == Event.Result.ALLOW)) {
        
        if (props.isPirate() || props.isBandit() || props.isRevolutionary())
        {
          if (bounty <= props.getBounty()) {
            serverPlayerEntity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_ONLY_UP, new Object[0]));
            return 1;
          } 
          long costOfAdding = bounty - props.getBounty();
          if (propz.getBelly() > costOfAdding) {
            propz.setBelly(propz.getBelly() - costOfAdding);
            props.setBounty(bounty);
          } else {
            serverPlayerEntity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_NOT_ENOUGH_BELLY, new Object[0]));
            return 1;
          
          }
        
        }
        else
        {
          serverPlayerEntity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_TARGET_REQUIREMENTS, new Object[0]));
        }
      
      } else {
        
        serverPlayerEntity.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_ISSUEBOUNTY_MESSAGE_REQUIREMENTS, new Object[0]));
      }
    
    } catch (CommandSyntaxException e) {
      
      e.printStackTrace();
    } 
    
    return 1;
  }
}


