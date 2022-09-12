package xyz.pixelatedw.mineminenomi.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import xyz.pixelatedw.mineminenomi.api.crew.Crew;
import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
import xyz.pixelatedw.mineminenomi.api.quests.Quest;
import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
import xyz.pixelatedw.mineminenomi.challenges.Challenge;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
import xyz.pixelatedw.mineminenomi.init.*;
import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
import xyz.pixelatedw.mineminenomi.world.spawners.AmbushSpawner;
import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
import xyz.pixelatedw.mineminenomi.wypi.APIRegistries;
import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FGCommand {
  public static boolean NO_COOLDOWN = false;
  
  public static void register(CommandDispatcher<CommandSource> dispatcher) {
    LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("fg").requires(source -> source.hasPermissionLevel(0));
    
    builder
      .then(Commands.literal("info")
        .executes(context -> showInfo(context, ((CommandSource)context.getSource()).asPlayer())));
    
    builder
      .then(((LiteralArgumentBuilder)Commands.literal("check_haki")
        .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
          .executes(context -> checkHakiStats(context, EntityArgument.getPlayer(context, "target")))))
        .executes(context -> checkHakiStats(context, ((CommandSource)context.getSource()).asPlayer())));
    
    builder
      .then(((LiteralArgumentBuilder)Commands.literal("check_crews")
        .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
          .executes(context -> checkCrews(context, EntityArgument.getPlayer(context, "target")))))
        .executes(context -> checkCrews(context, ((CommandSource)context.getSource()).asPlayer())));
    
    builder
      .then(Commands.literal("check_bounties")
        .executes(context -> checkBounties(context)));
    
    builder
      .then(Commands.literal("check_structures")
        .executes(context -> checkStructures(context, ((CommandSource)context.getSource()).asPlayer())));
    
    builder
      .then(Commands.literal("turn_sword_clone")
        .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
          .executes(context -> turnSwordInClone(context, EntityArgument.getPlayer(context, "target")))));
    
    builder
      .then(((LiteralArgumentBuilder)Commands.literal("challenges")
        .then(Commands.literal("unlock_all")
          .executes(context -> challenges(context, ((CommandSource)context.getSource()).asPlayer(), 1))))
        .then(Commands.literal("lock_all")
          .executes(context -> challenges(context, ((CommandSource)context.getSource()).asPlayer(), -1))));
    
    builder
      .then(Commands.literal("drop_package")
        .executes(context -> dropPackage(context, ((CommandSource)context.getSource()).asPlayer())));
    
    builder
      .then(Commands.literal("save_structure")
        .then(Commands.argument("from", (ArgumentType)BlockPosArgument.blockPos())
          .then(Commands.argument("to", (ArgumentType)BlockPosArgument.blockPos())
            .then(Commands.argument("name", (ArgumentType)StringArgumentType.word())
              .executes(context -> saveStructure(context, BlockPosArgument.getBlockPos(context, "from"), BlockPosArgument.getBlockPos(context, "to"), StringArgumentType.getString(context, "name"), ((CommandSource)context.getSource()).asPlayer()))))));
    
    builder
      .then(Commands.literal("load_structure")
        .executes(context -> loadStructure(context, ((CommandSource)context.getSource()).asPlayer())));
    
    builder
      .then(Commands.literal("hardcore")
        .executes(context -> harcoreLoadout(context, ((CommandSource)context.getSource()).asPlayer())));
    
    builder
      .then(Commands.literal("no_cooldowns")
        .executes(context -> {
            NO_COOLDOWN = !NO_COOLDOWN;
            
            ((CommandSource)context.getSource()).asPlayer().sendMessage((ITextComponent)new StringTextComponent((NO_COOLDOWN ? "Enabled" : "Disabled") + " No Cooldown Mode"));
            return 1;
          }));
    builder
      .then(Commands.literal("ambush")
        .executes(context -> {
            ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
            
            if (EntityStatsCapability.get((LivingEntity)player).getBounty() < 1000L) {
              EntityStatsCapability.get((LivingEntity)player).alterBounty(2000L);
            }
            
            AmbushSpawner spawner = new AmbushSpawner();
            spawner.spawn(((CommandSource)context.getSource()).getWorld());
            return 1;
          }));
    builder
      .then(Commands.literal("wipe")
        .then(Commands.literal("quests")
          .executes(ctx -> {
              ServerPlayerEntity player = ((CommandSource)ctx.getSource()).asPlayer();
              
              IQuestData questData = QuestDataCapability.get((PlayerEntity)player);
              
              Iterator<Quest> quests = questData.getFinishedQuests().iterator();
              while (quests.hasNext()) {
                questData.removeFinishedQuest(quests.next());
              }
              return 1;
            })));
    builder
      .then(((LiteralArgumentBuilder)Commands.literal("stress_test")
        .then(Commands.literal("spawners")
          .executes(context -> stressTestSpawners(context, ((CommandSource)context.getSource()).asPlayer()))))
        .then(Commands.literal("blocks")
          .executes(context -> stressTestBlocks(context, ((CommandSource)context.getSource()).asPlayer()))));
    
    dispatcher.register(builder);
  }

  
  private static int stressTestBlocks(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    long startTime = System.currentTimeMillis();
    int i = 0;
    
    try {
      for (int x = -50; x < 50; x++) {
        
        for (int y = -50; y < 50; y++) {
          
          for (int z = -50; z < 50; z++)
          {
            WyHelper.setBlockStateInChunk(player.world, player.getPosition().add(x, y, z), Blocks.DIRT.getDefaultState(), 2);
            i++;
          }
        
        } 
      } 
    } catch (Exception e) {
      
      e.printStackTrace();
    } 
    long stopTime = System.currentTimeMillis();
    System.out.println("Finished generating " + i + " blocks after " + (stopTime - startTime) + " millis");
    
    return 1;
  }

  
  private static int stressTestSpawners(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    ServerWorld world = player.getServerWorld();
    
    for (int i = -3; i < 3; i++) {
      
      for (int j = -3; j < 3; j++) {
        
        BlockPos pos = player.getPosition().add(i, 0, j);
        world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState());
        CustomSpawnerTileEntity spawner = (CustomSpawnerTileEntity)world.getTileEntity(pos);
        spawner.setSpawnerLimit(5);
        spawner.setSpawnerMob(ModEntities.MARINE_WITH_SWORD);
      } 
    } 
    
    return 1;
  }

  
  private static int checkStructures(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    int[] arr = StructuresHelper.STRUCTURES_COUNT;
    int sum = 0;
    
    for (int i : arr)
    {
      sum += i;
    }
    
    StringBuilder builder = new StringBuilder();
    builder.append("========SHIPS==================================\n\n");
    builder.append("Small Pirate Ships: " + arr[0] + "\n");
    builder.append("Medium Pirate Ships: " + arr[1] + "\n");
    builder.append("Large Pirate Ships: " + arr[2] + "\n");
    builder.append("Marine Battleships: " + arr[3] + "\n");
    builder.append("Ghost Ships: " + arr[4] + "\n\n");
    builder.append("========LAND STRUCTURES========================\n\n");
    builder.append("Marine Small Base: " + arr[5] + "\n");
    builder.append("Marine Large Base: " + arr[6] + "\n");
    builder.append("Bandit Small Base: " + arr[7] + "\n");
    builder.append("Bandit Large Base: " + arr[8] + "\n");
    builder.append("Dojos: " + arr[9] + "\n");
    builder.append("Marine Camp: " + arr[12] + "\n");
    builder.append("Bandit Camp: " + arr[13] + "\n");
    builder.append("Marine Watch Tower: " + arr[10] + "\n");
    builder.append("========SKY STRUCTURES========================\n\n");
    builder.append("Sky Islands: " + arr[11] + "\n");
    builder.append("Total: " + sum + "\n");
    builder.append("===============================================");
    
    ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(builder.toString()), true);
    
    return 1;
  }

  
  private static int dropPackage(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    BountyHelper.issueBountyForAllPlayers(player.world);
    WantedPosterPackageEntity posterPackage = new WantedPosterPackageEntity(player.world);
    posterPackage.setPositionAndRotation(player.getPosX(), player.getPosY() + 5.0D, player.getPosZ(), 0.0F, 0.0F);
    player.world.addEntity((Entity)posterPackage);
    return 1;
  }

  
  private static int harcoreLoadout(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)ModAbilities.YOMI_YOMI_NO_MI));
    player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)Blocks.DIRT, 64));
    player.getFoodStats().setFoodLevel(0);
    player.getFoodStats().setFoodSaturationLevel(0.0F);
    return 1;
  }

  
  private static String convertToCSV(String[] data) {
    return Stream.<String>of(data).map(FGCommand::escapeSpecialCharacters).collect(Collectors.joining(","));
  }

  
  private static String escapeSpecialCharacters(String data) {
    String escapedData = data.replaceAll("\\R", " ");
    if (data.contains(",") || data.contains("\"") || data.contains("'")) {
      
      data = data.replace("\"", "\"\"");
      escapedData = "\"" + data + "\"";
    } 
    return escapedData;
  }

  
  private static String getOriginFruit(Ability ability) {
    for (AkumaNoMiItem fruit : ModValues.devilfruits) {
      
      for (Ability abl : fruit.abilities) {
        
        if (abl.equals(ability)) {
          return fruit.getDevilFruitName();
        }
      } 
    } 
    return "";
  }

  
  private static int showInfo(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    player.sendMessage((ITextComponent)new StringTextComponent("==========INFO=========="));
    player.sendMessage((ITextComponent)new StringTextComponent("A total of " + ModValues.devilfruits.size() + " Devil Fruits have been registered"));
    player.sendMessage((ITextComponent)new StringTextComponent("A total of " + APIRegistries.ABILITIES.getEntries().size() + " abilities have been registered"));
    
    List<Ability> noDescriptions = (List<Ability>)APIRegistries.ABILITIES.getValues().stream().filter(abl -> (abl.getDescription() == null && !abl.isHideInGUI())).collect(Collectors.toList());






















    
    List<Ability> noTextures = (List<Ability>)APIRegistries.ABILITIES.getValues().stream().filter(abl -> { if (!abl.isHideInGUI()) { String texture; if (abl.hasCustomTexture()) { texture = WyHelper.getResourceName(abl.getCustomTexture()); } else { texture = WyHelper.getResourceName(abl.getName()); }  try { boolean hasFile = (new File(APIConfig.getResourceFolderPath() + "/assets/" + "mineminenomi" + "/textures/abilities/" + texture + ".png")).exists(); if (!hasFile) return true;  } catch (Exception e) { e.printStackTrace(); return false; }  }  return false; }).collect(Collectors.toList());
    
    StringBuilder builder = new StringBuilder();
    builder.append("\n§a " + noDescriptions.size() + " abilities without descriptions:§r\n");
    for (Ability abl : noDescriptions)
    {
      builder.append("  " + abl.getDisplayName() + "\n");
    }
    player.sendMessage((ITextComponent)new StringTextComponent(builder.toString()));
    
    builder = new StringBuilder();
    builder.append("§a " + noTextures.size() + " abilities without textures:§r\n");
    for (Ability abl : noTextures)
    {
      builder.append("  " + abl.getDisplayName() + "\n");
    }
    player.sendMessage((ITextComponent)new StringTextComponent(builder.toString()));






















    
    return 1;
  }


  
  private static int loadStructure(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    WyHelper.loadNBTStructure((ServerWorld)player.world, "marine/small_base", player.getPosition());
    player.sendMessage((ITextComponent)new StringTextComponent("Done"));
    return 1;
  }

  
  private static int saveStructure(CommandContext<CommandSource> context, BlockPos from, BlockPos to, String name, ServerPlayerEntity player) {
    for (int i = from.getX(); i < to.getX(); i++) {
      
      for (int j = from.getY(); j < to.getY(); j++) {
        
        for (int k = from.getZ(); k < to.getZ(); k++) {
          
          BlockPos pos = new BlockPos(i, j, k);
          BlockState state = player.world.getBlockState(pos);
          
          if (state.has((IProperty)BlockStateProperties.WATERLOGGED)) {
            
            state = (BlockState)state.with((IProperty)BlockStateProperties.WATERLOGGED, Boolean.valueOf(false));
            player.world.setBlockState(pos, state);
          } 
          
          if (state.getBlock() == Blocks.WATER || state.getBlock() == Blocks.STRUCTURE_VOID)
          {
            player.world.setBlockState(pos, Blocks.AIR.getDefaultState(), 18);
          }
          
          if (state.getBlock() == Blocks.BARREL) {
            
            Direction dir = (Direction)state.get((IProperty)BlockStateProperties.FACING);
            BlockState newState = ModBlocks.DESIGN_BARREL.getDefaultState();
            newState = (BlockState)newState.with((IProperty)BlockStateProperties.FACING, (Comparable)dir);
            player.world.setBlockState(pos, newState);
          } 
        } 
      } 
    } 
    if (player.world instanceof ServerWorld) {
      
      BlockPos size = to.subtract((Vec3i)from);
      System.out.println("From: /tp " + from.getX() + " " + from.getY() + " " + from.getZ());
      System.out.println("To: /tp " + to.getX() + " " + to.getY() + " " + to.getZ());
      System.out.println("size: " + size);
      List<Block> bannedBlocks = new ArrayList<>();
      bannedBlocks.add(Blocks.WATER);
      WyHelper.saveNBTStructure((ServerWorld)player.world, name, from, size, bannedBlocks);
      
      player.sendMessage((ITextComponent)new StringTextComponent("Done"));
    } 
    return 1;
  }

  
  private static int challenges(CommandContext<CommandSource> context, ServerPlayerEntity player, int op) {
    IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
    
    Challenge[] challenges = { ModChallenges.GIN, ModChallenges.DON_KRIEG, ModChallenges.MORGAN };
    for (Challenge ch : challenges) {
      
      if (op == 1) {
        
        if (!props.hasChallenge(ch)) {
          props.addChallenge(ch);
        }
      } else if (op == -1) {
        
        if (props.hasChallenge(ch)) {
          
          ch.setComplete(false);
          props.removeChallenge(ch);
        } 
      } 
    } 
    
    WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), props), (PlayerEntity)player);
    
    return 1;
  }

  
  private static int checkBounties(CommandContext<CommandSource> source) {
    ServerWorld serverWorld = ((CommandSource)source.getSource()).getWorld();
    ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
    
    StringBuilder sb = new StringBuilder();
    
    for (Map.Entry<String, Long> entry : (Iterable<Map.Entry<String, Long>>)worldData.getAllBounties().entrySet()) {
      
      UUID uuid = UUID.fromString(entry.getKey());
      PlayerEntity target = serverWorld.getPlayerByUuid(uuid);
      sb.append(target.getDisplayName().getFormattedText() + " " + entry.getValue());
    } 
    ((CommandSource)source.getSource()).sendFeedback((ITextComponent)new StringTextComponent(sb.toString()), true);
    
    return 1;
  }

  
  private static int checkCrews(CommandContext<CommandSource> source, ServerPlayerEntity target) {
    ExtendedWorldData worldData = ExtendedWorldData.get(target.world);
    
    StringBuilder builder = new StringBuilder();
    
    for (Crew crew : worldData.getCrews()) {
      
      builder.append("\n=================\n" + crew.getName() + "\n");
      builder.append("Members: \n");
      for (Crew.Member member : crew.getMembers())
      {
        builder.append(" " + member.getUsername() + (member.isCaptain() ? " (Captain)" : "") + "\n");
      }
      builder.append("Details: \n");
      for (JollyRogerElement elem : crew.getJollyRoger().getDetails()) {
        
        if (elem != null)
        {
          builder.append(" " + elem.getTexture() + "\n"); } 
      } 
    } 
    target.sendMessage((ITextComponent)new StringTextComponent(builder.toString()));
    
    return 1;
  }

  
  private static int turnSwordInClone(CommandContext<CommandSource> context, ServerPlayerEntity player) {
    ItemStack heldStack = player.getHeldItemMainhand();
    
    if (!heldStack.isEmpty()) {
      heldStack.getOrCreateTag().putBoolean("isClone", true);
    }
    return 1;
  }

  
  private static int checkHakiStats(CommandContext<CommandSource> context, ServerPlayerEntity target) {
    IHakiData props = HakiDataCapability.get((LivingEntity)target);
    
    target.sendMessage((ITextComponent)new StringTextComponent("Imbuing: " + props.getBusoshokuImbuingHakiExp()));
    target.sendMessage((ITextComponent)new StringTextComponent("Hardening: " + props.getBusoshokuHardeningHakiExp()));
    target.sendMessage((ITextComponent)new StringTextComponent("Observation: " + props.getKenbunshokuHakiExp()));
    target.sendMessage((ITextComponent)new StringTextComponent("Total: " + HakiHelper.getTotalHakiExp((LivingEntity)target)));
    
    if (WyDebug.isDebug()) {
      props.setHakiOveruse(0);
    }
    return 1;
  }
}


