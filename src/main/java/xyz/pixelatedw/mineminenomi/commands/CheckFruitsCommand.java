package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import org.apache.commons.lang3.tuple.Pair;
import xyz.pixelatedw.mineminenomi.config.CommonConfig;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;



public class CheckFruitsCommand
{
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("check_fruits").requires(source -> source.hasPermissionLevel(3));
    
    builder.executes(context -> checkFruitsInWorld(context));
    
    dispatcher.register(builder);
  }

  
  private static int checkFruitsInWorld(CommandContext<CommandSource> context) {
    if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
      
      ((CommandSource)context.getSource()).sendErrorMessage((ITextComponent)new StringTextComponent("This command can only be used when the One Fruit per World config option is enabled."));
      return 0;
    } 
    
    ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
    ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
    
    StringBuilder builder = new StringBuilder();
    
    builder.append("===============================================\n");
    builder.append("§l§6Devil Fruits in World\n");
    builder.append(worldData.getDevilFruitsInWorld().size() + " ");
    for (String fruit : worldData.getDevilFruitsInWorld())
    {
      builder.append(fruit + " ");
    }
    builder.append("\n");
    builder.append("\n");
    
    builder.append("§l§6Devil Fruits in Player's Inventories\n");
    HashMap<UUID, List<String>> inventories = worldData.getFruitsInInventory();
    for (Map.Entry<UUID, List<String>> entry : inventories.entrySet()) {
      
      if (((List)entry.getValue()).isEmpty())
        continue; 
      String playerName = serverWorld.getServer().getPlayerProfileCache().getProfileByUUID(entry.getKey()).getName();
      builder.append(playerName + " - ");
      StringBuilder fruitsString = new StringBuilder();
      for (String fruitName : entry.getValue())
      {
        fruitsString.append(fruitName + " ");
      }
      builder.append(fruitsString.toString() + "\n");
    } 
    builder.append("\n");
    
    builder.append("§l§6Devil Fruits Eaten by a Player\n");
    for (Map.Entry<UUID, String> entry : (Iterable<Map.Entry<UUID, String>>)worldData.getAteFruits().entrySet()) {
      
      String playerName = serverWorld.getServer().getPlayerProfileCache().getProfileByUUID(entry.getKey()).getName();
      builder.append(playerName + " : " + (String)entry.getValue() + "\n");
    } 
    builder.append("\n");
    
    builder.append("§l§6Logged Out Devil Fruits\n");
    for (Map.Entry<UUID, Pair<Date, List<String>>> entry : (Iterable<Map.Entry<UUID, Pair<Date, List<String>>>>)worldData.getLoggedOutDevilFruits().entrySet()) {
      
      String playerName = serverWorld.getServer().getPlayerProfileCache().getProfileByUUID(entry.getKey()).getName();
      StringBuilder fruitsString = new StringBuilder();
      for (String fruitName : (entry.getValue()).getValue())
      {
        fruitsString.append(fruitName + " ");
      }
      builder.append(((Date)(entry.getValue()).getKey()).toString() + " - " + playerName + " : " + fruitsString.toString() + "\n");
    } 
    builder.append("===============================================");

    
    ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(builder.toString()), false);
    
    return 1;
  }
}


