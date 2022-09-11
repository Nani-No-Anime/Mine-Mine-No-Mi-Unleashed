package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.init.ModBlocks;
import xyz.pixelatedw.mineminenomi.packets.server.ability.SViewProtectionPacket;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

public class AbilityProtectionCommand {
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("abilityprotection").requires(source -> source.hasPermissionLevel(3));
    
    ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
      .then(Commands.literal("new")
        .then(Commands.argument("size", (ArgumentType)IntegerArgumentType.integer(1, 100))
          .executes(context -> createProtection(context, IntegerArgumentType.getInteger(context, "size"))))))




      
      .then(Commands.literal("resize")
        .then(Commands.argument("id", (ArgumentType)IntegerArgumentType.integer())
          .then(Commands.argument("size", (ArgumentType)IntegerArgumentType.integer(1, 100))
            .executes(context -> resizeProtection(context, IntegerArgumentType.getInteger(context, "id"), IntegerArgumentType.getInteger(context, "size")))))))




      
      .then(Commands.literal("view")
        .then(Commands.argument("state", (ArgumentType)BoolArgumentType.bool())
          .executes(context -> viewProtection(context, BoolArgumentType.getBool(context, "state"))))))




      
      .then(Commands.literal("list")
        .executes(context -> listProtections(context))))



      
      .then(Commands.literal("remove")
        .then(Commands.argument("id", (ArgumentType)IntegerArgumentType.integer())
          .executes(context -> deleteProtection(context, IntegerArgumentType.getInteger(context, "id")))));





    
    dispatcher.register(builder);
  }

  
  private static int resizeProtection(CommandContext<CommandSource> context, int id, int size) throws CommandSyntaxException {
    ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
    ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
    ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
    id--;
    
    int[][] area = worldData.getAllRestrictions().get(id);
    
    int[] minPos = area[0];
    int[] maxPos = area[1];
    
    int midX = (minPos[0] + maxPos[0]) / 2;
    int midY = (minPos[1] + maxPos[1]) / 2;
    int midZ = (minPos[2] + maxPos[2]) / 2;
    BlockPos pos = new BlockPos(midX, midY, midZ);
    
    BlockState state = serverWorld.getBlockState(pos);
    if (state.getBlock() == ModBlocks.ABILITY_PROTECTION) {
      
      AblProtectionTileEntity tile = (AblProtectionTileEntity)serverWorld.getTileEntity(pos);
      tile.resizeProtection((PlayerEntity)player, pos, id, size);
    } 
    
    return 1;
  }

  
  private static int deleteProtection(CommandContext<CommandSource> context, int id) throws CommandSyntaxException {
    ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
    ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
    id--;
    
    int[][] area = worldData.getAllRestrictions().get(id);
    
    int[] minPos = area[0];
    int[] maxPos = area[1];
    
    int midX = (minPos[0] + maxPos[0]) / 2;
    int midY = (minPos[1] + maxPos[1]) / 2;
    int midZ = (minPos[2] + maxPos[2]) / 2;
    BlockPos pos = new BlockPos(midX, midY, midZ);
    
    serverWorld.setBlockState(pos, Blocks.AIR.getDefaultState());
    worldData.removeRestrictedArea(id);
    
    return 1;
  }

  
  private static int listProtections(CommandContext<CommandSource> context) throws CommandSyntaxException {
    ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
    ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
    
    ITextComponent list = (new StringTextComponent("Protection Sites: \n\n")).setStyle((new Style()).setColor(TextFormatting.GREEN));
    
    int i = 1;
    for (int[][] area : worldData.getAllRestrictions()) {
      
      int[] minPos = area[0];
      int[] maxPos = area[1];
      
      if (minPos.length <= 0 || maxPos.length <= 0) {
        continue;
      }
      int midX = (minPos[0] + maxPos[0]) / 2;
      int midY = (minPos[1] + maxPos[1]) / 2;
      int midZ = (minPos[2] + maxPos[2]) / 2;

      
      Style style = (new Style()).setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @p " + midX + " " + midY + " " + midZ));
      list.appendSibling((new StringTextComponent(i + ". x=" + midX + ", y=" + midY + ", z=" + midZ + "\n")).setStyle(style));
      i++;
    } 
    
    ((CommandSource)context.getSource()).sendFeedback(list, true);
    
    return 1;
  }

  
  private static int viewProtection(CommandContext<CommandSource> context, boolean state) throws CommandSyntaxException {
    ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
    ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
    
    ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
    
    for (int[][] area : worldData.getAllRestrictions()) {
      
      int[] minPos = area[0];
      int[] maxPos = area[1];
      
      if (minPos.length <= 0 || maxPos.length <= 0) {
        continue;
      }
      int midX = (minPos[0] + maxPos[0]) / 2;
      int midY = (minPos[1] + maxPos[1]) / 2;
      int midZ = (minPos[2] + maxPos[2]) / 2;
      int[] midPoint = { midX, midY, midZ };
      
      AblProtectionTileEntity te = (AblProtectionTileEntity)serverWorld.getTileEntity(new BlockPos(midPoint[0], midPoint[1], midPoint[2]));
      
      if (te == null || !(te instanceof AblProtectionTileEntity)) {
        continue;
      }
      WyNetwork.sendTo(new SViewProtectionPacket(state, midPoint, te.getSize()), (PlayerEntity)player);
    } 
    return 1;
  }

  
  private static int createProtection(CommandContext<CommandSource> context, int size) throws CommandSyntaxException {
    ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
    ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
    Vec3d vec = ((CommandSource)context.getSource()).getPos();
    BlockPos pos = new BlockPos(vec);
    
    serverWorld.setBlockState(pos, ModBlocks.ABILITY_PROTECTION.getDefaultState());
    ((AblProtectionTileEntity)serverWorld.getTileEntity(pos)).setupProtection((PlayerEntity)player, pos, size);
    
    return 1;
  }
}


