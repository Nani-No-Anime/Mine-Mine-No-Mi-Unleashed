package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.IPacket;
import net.minecraft.network.play.server.SPlayerAbilitiesPacket;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityEvent;
import xyz.pixelatedw.mineminenomi.abilities.SpecialFlyAbility;
import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.DevilFruitCapability;
import xyz.pixelatedw.mineminenomi.data.entity.devilfruit.IDevilFruit;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncDevilFruitPacket;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SRecalculateEyeHeightPacket;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ChargeableAbility;
import xyz.pixelatedw.mineminenomi.wypi.abilities.ContinuousAbility;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;

public class RemoveDFCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>) Commands.literal("removedf").requires(source -> source.hasPermissionLevel(2));

    ((LiteralArgumentBuilder) builder.executes(context -> removesDF(context, ((CommandSource) context.getSource()).asPlayer())))
        .then(Commands.argument("target", EntityArgument.player()).executes(context -> removesDF(context, EntityArgument.getPlayer(context, "target"))));

    dispatcher.register(builder);
  }

  private static int removesDF(CommandContext<CommandSource> context, ServerPlayerEntity player) throws CommandSyntaxException {
    try {
      IDevilFruit devilFruitProps = DevilFruitCapability.get((LivingEntity) player);
      IAbilityData abilityDataProps = AbilityDataCapability.get((LivingEntity) player);
      ExtendedWorldData worldData = ExtendedWorldData.get(player.world);

      worldData.removeAteDevilFruit((PlayerEntity) player);
      worldData.removeDevilFruitInWorld(devilFruitProps.getDevilFruit());

      devilFruitProps.setDevilFruit("");
      devilFruitProps.setLogia(false);
      devilFruitProps.setZoanPoint("");
      devilFruitProps.setYamiPower(false);

      for (Ability ability : abilityDataProps.getEquippedAbilities(APIConfig.AbilityCategory.ALL)) {

        if (ability != null) {

          if (ability instanceof ContinuousAbility)
            ((ContinuousAbility) ability).stopContinuity((PlayerEntity) player);
          if (ability instanceof ChargeableAbility) {
            ((ChargeableAbility) ability).stopCharging((PlayerEntity) player);
          } else {
            ability.stopCooldown((PlayerEntity) player);
          }
        }
      }
      if (CommonConfig.INSTANCE.isSpecialFlyingEnabled() && abilityDataProps.hasUnlockedAbility((Ability) SpecialFlyAbility.INSTANCE) && !player.isCreative() && !player.isSpectator()) {

        player.abilities.allowFlying = false;
        player.abilities.isFlying = false;
        player.connection.sendPacket((IPacket) new SPlayerAbilitiesPacket(player.abilities));
      }

      abilityDataProps.clearUnlockedAbilities(AbilityHelper.getDevilFruitCategory());
      abilityDataProps.clearEquippedAbilities(AbilityHelper.getDevilFruitCategory());

      if (WyDebug.isDebug()) {
        player.clearActivePotions();
      }
      WyNetwork.sendToAllTrackingAndSelf(new SSyncDevilFruitPacket(player.getEntityId(), devilFruitProps), (LivingEntity) player);
      WyNetwork.sendToAllTrackingAndSelf(new SSyncAbilityDataPacket(player.getEntityId(), abilityDataProps), (LivingEntity) player);

      MinecraftForge.EVENT_BUS.post(new EntityEvent.EyeHeight((Entity) player, player.getPose(), player.getSize(player.getPose()), player.getHeight()));
      WyNetwork.sendToAllTrackingAndSelf(new SRecalculateEyeHeightPacket(player.getEntityId()), (LivingEntity) player);
      player.recalculateSize();

      // [todo : figure this out]
      player.getAttributes().getAllAttributes().stream().forEach(attr -> attr.func_225505_c_().stream().forEach((mod) -> {
        attr.removeModifier(mod);
      }));

      ((CommandSource) context.getSource()).sendFeedback((ITextComponent) new StringTextComponent(TextFormatting.GREEN + "Removed Devil Fruit for " + player.getDisplayName().getFormattedText()), true);
    } catch (Exception e) {

      e.printStackTrace();
    }

    return 1;
  }
}