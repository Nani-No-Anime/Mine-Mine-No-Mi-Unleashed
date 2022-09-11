package xyz.pixelatedw.mineminenomi.commands;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.LongArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
import xyz.pixelatedw.mineminenomi.init.ModI18n;
import xyz.pixelatedw.mineminenomi.init.ModItems;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class PouchCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("pouch").requires(source -> source.hasPermissionLevel(0));
    
    builder
      .then(Commands.literal("ALL")
        .executes(context -> createBellyPouch(context, 999999999L)));
    
    builder
      .then(Commands.argument("amount", (ArgumentType)LongArgumentType.longArg(1L, 999999999L))
        .executes(context -> createBellyPouch(context, LongArgumentType.getLong(context, "amount"))));
    
    dispatcher.register(builder);
  }

  
  private static int createBellyPouch(CommandContext<CommandSource> context, long amount) {
    if (amount <= 0L) {
      return 1;
    }
    
    try {
      ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
      
      if (WyHelper.hasInventoryFull((PlayerEntity)player)) {
        
        player.sendMessage((ITextComponent)new TranslationTextComponent(ModI18n.COMMAND_POUCH_MESSAGE_INVENTORY_FULL, new Object[0]));
        return 1;
      } 
      
      IEntityStats props = EntityStatsCapability.get((LivingEntity)player);
      
      if (props.getBelly() <= 0L) {
        return 1;
      }
      if (props.getBelly() - amount >= 0L) {
        props.alterBelly(-amount);
      } else {
        
        amount = props.getBelly();
        props.alterBelly(-amount);
      } 
      
      ItemStack pouch = new ItemStack((IItemProvider)ModItems.BELLY_POUCH);
      pouch.getOrCreateTag().putLong("belly", amount);
      
      player.inventory.addItemStackToInventory(pouch);
      WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), props), (PlayerEntity)player);
    }
    catch (CommandSyntaxException e) {
      
      e.printStackTrace();
    } 
    
    return 1;
  }
}


