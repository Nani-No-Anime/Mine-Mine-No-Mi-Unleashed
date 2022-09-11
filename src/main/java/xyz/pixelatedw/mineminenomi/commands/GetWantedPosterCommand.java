package xyz.pixelatedw.mineminenomi.commands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import xyz.pixelatedw.mineminenomi.api.helpers.ItemsHelper;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncWorldDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class GetWantedPosterCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("getwantedposter").requires(source -> source.hasPermissionLevel(2));
    
    builder
      .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
        .executes(context -> giveWantedPoster(context, EntityArgument.getPlayer(context, "target"))));





    
    dispatcher.register(builder);
  }

  
  private static int giveWantedPoster(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
    ExtendedWorldData worldData = ExtendedWorldData.get(player.world);
    
    worldData.issueBounty(player.getUniqueID().toString(), entityStatsProps.getBounty());
    
    if (WyDebug.isDebug()) {
      player.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] A new bounty was issued on your name!"));
    }
    ItemStack posterStack = new ItemStack((IItemProvider)ModBlocks.WANTED_POSTER);
    
    CompoundNBT data = ItemsHelper.setWantedData(player.world, player.getUniqueID().toString(), worldData.getBounty(player.getUniqueID().toString()));
    
    if (data.isEmpty()) {
      
      player.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.RED + "New Wanted Posters can only be generated for online players!"));
      return 1;
    } 
    
    posterStack.setTag(data);
    player.inventory.addItemStackToInventory(posterStack);
    
    WyNetwork.sendToAllTrackingAndSelf(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (LivingEntity)player);
    WyNetwork.sendToAllTrackingAndSelf(new SSyncWorldDataPacket(worldData), (LivingEntity)player);
    
    return 1;
  }
}


