package xyz.pixelatedw.mineminenomi.commands;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
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
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
import xyz.pixelatedw.mineminenomi.api.commands.AbilityArgument;
import xyz.pixelatedw.mineminenomi.api.commands.AbilityGroupArgument;
import xyz.pixelatedw.mineminenomi.api.enums.AbilityCommandGroup;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class AbilityCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("ability").requires(source -> source.hasPermissionLevel(3));
    
    ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
      .then(Commands.literal("give")
        .then(((RequiredArgumentBuilder)Commands.argument("ability", (ArgumentType)AbilityArgument.ability())
          .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
            .executes(context -> addAbility(context, AbilityArgument.getAbility(context, "ability"), EntityArgument.getPlayers(context, "targets")))))
          
          .executes(context -> addAbility(context, AbilityArgument.getAbility(context, "ability"), getDefaultCollection(context))))))

      
      .then(Commands.literal("remove")
        .then(((RequiredArgumentBuilder)Commands.argument("ability", (ArgumentType)new AbilityArgument())
          .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
            .executes(context -> removeAbility(context, AbilityArgument.getAbility(context, "ability"), EntityArgument.getPlayers(context, "targets")))))
          
          .executes(context -> removeAbility(context, AbilityArgument.getAbility(context, "ability"), getDefaultCollection(context))))))

      
      .then(Commands.literal("unlock_group")
        .then(Commands.argument("group", (ArgumentType)AbilityGroupArgument.abilityGroup())
          .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
            .executes(context -> abilityGroup(context, (AbilityCommandGroup)context.getArgument("group", AbilityCommandGroup.class), 1, EntityArgument.getPlayers(context, "targets")))))))
      .then(Commands.literal("lock_group")
        .then(Commands.argument("group", (ArgumentType)AbilityGroupArgument.abilityGroup())
          .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
            .executes(context -> abilityGroup(context, (AbilityCommandGroup)context.getArgument("group", AbilityCommandGroup.class), -1, EntityArgument.getPlayers(context, "targets")))))))
      .then(((LiteralArgumentBuilder)Commands.literal("reset_cooldown")
        .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
          .executes(context -> resetCooldown(context, EntityArgument.getPlayers(context, "targets")))))
        
        .executes(context -> resetCooldown(context, getDefaultCollection(context))));

    
    dispatcher.register(builder);
  }

  
  private static int abilityGroup(CommandContext<CommandSource> context, AbilityCommandGroup group, int op, Collection<ServerPlayerEntity> players) {
    for (ServerPlayerEntity player : players) {
      
      IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
      
      for (Ability abl : group.getAbilities()) {
        
        if (op == 1) {
          
          abl.setUnlockType(AbilityUnlock.COMMAND);
          abilityProps.addUnlockedAbility(abl);
          continue;
        } 
        abilityProps.removeUnlockedAbility(abl);
      } 
      
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), (PlayerEntity)player);
    } 
    return 1;
  }

  
  private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
    return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
  }

  
  private static int resetCooldown(CommandContext<CommandSource> context, Collection<ServerPlayerEntity> players) {
    for (ServerPlayerEntity player : players) {
      
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
      
      for (Ability ability : props.getEquippedAbilities()) {
        
        if (ability != null) {

          
          ability.setForcedState(false);
          
          if (ability.isOnCooldown()) {
            ability.stopCooldown((PlayerEntity)player);
          }
          hakiProps.setHakiOveruse(0);
          
          WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)player, ability), (LivingEntity)player);
        } 
      } 
    } 
    return 1;
  }

  
  private static int addAbility(CommandContext<CommandSource> context, Ability ability, Collection<ServerPlayerEntity> targets) {
    for (ServerPlayerEntity player : targets) {
      
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      
      ability.setUnlockType(AbilityUnlock.COMMAND);
      props.addUnlockedAbility(ability);
      
      if (WyDebug.isDebug()) {
        player.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + ability.getName() + " unlocked for " + player.getName().getFormattedText()));
      }
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), (PlayerEntity)player);
    } 
    
    return 1;
  }

  
  private static int removeAbility(CommandContext<CommandSource> context, Ability ability, Collection<ServerPlayerEntity> targets) {
    for (ServerPlayerEntity player : targets) {
      
      IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
      
      props.removeUnlockedAbility(ability);
      
      if (WyDebug.isDebug()) {
        player.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + ability.getName() + " removed for " + player.getName().getFormattedText()));
      }
      WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), (PlayerEntity)player);
    } 
    
    return 1;
  }
}


