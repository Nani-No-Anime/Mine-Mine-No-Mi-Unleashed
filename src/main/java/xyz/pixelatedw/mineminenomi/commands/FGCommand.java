/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.StringArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.io.File;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import net.minecraft.block.Block;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.BlockPosArgument;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.Entity;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.item.ItemStack;
/*     */ import net.minecraft.state.IProperty;
/*     */ import net.minecraft.state.properties.BlockStateProperties;
/*     */ import net.minecraft.util.Direction;
/*     */ import net.minecraft.util.IItemProvider;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3i;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.Crew;
/*     */ import xyz.pixelatedw.mineminenomi.api.crew.JollyRogerElement;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.BountyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.HakiHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.StructuresHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.CustomSpawnerTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.challenges.Challenge;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.ChallengesDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.challenges.IChallengesData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.entities.WantedPosterPackageEntity;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModAbilities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModChallenges;
import xyz.pixelatedw.mineminenomi.init.ModEntities;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModValues;
/*     */ import xyz.pixelatedw.mineminenomi.items.AkumaNoMiItem;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncChallengeDataPacket;
/*     */ import xyz.pixelatedw.mineminenomi.world.spawners.AmbushSpawner;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIConfig;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.APIRegistries;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.WyHelper;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class FGCommand {
/*     */   public static boolean NO_COOLDOWN = false;
/*     */   
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/*  73 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("fg").requires(source -> source.hasPermissionLevel(0));
/*     */     
/*  75 */     builder
/*  76 */       .then(Commands.literal("info")
/*  77 */         .executes(context -> showInfo(context, ((CommandSource)context.getSource()).asPlayer())));
/*     */     
/*  79 */     builder
/*  80 */       .then(((LiteralArgumentBuilder)Commands.literal("check_haki")
/*  81 */         .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/*  82 */           .executes(context -> checkHakiStats(context, EntityArgument.getPlayer(context, "target")))))
/*  83 */         .executes(context -> checkHakiStats(context, ((CommandSource)context.getSource()).asPlayer())));
/*     */     
/*  85 */     builder
/*  86 */       .then(((LiteralArgumentBuilder)Commands.literal("check_crews")
/*  87 */         .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/*  88 */           .executes(context -> checkCrews(context, EntityArgument.getPlayer(context, "target")))))
/*  89 */         .executes(context -> checkCrews(context, ((CommandSource)context.getSource()).asPlayer())));
/*     */     
/*  91 */     builder
/*  92 */       .then(Commands.literal("check_bounties")
/*  93 */         .executes(context -> checkBounties(context)));
/*     */     
/*  95 */     builder
/*  96 */       .then(Commands.literal("check_structures")
/*  97 */         .executes(context -> checkStructures(context, ((CommandSource)context.getSource()).asPlayer())));
/*     */     
/*  99 */     builder
/* 100 */       .then(Commands.literal("turn_sword_clone")
/* 101 */         .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/* 102 */           .executes(context -> turnSwordInClone(context, EntityArgument.getPlayer(context, "target")))));
/*     */     
/* 104 */     builder
/* 105 */       .then(((LiteralArgumentBuilder)Commands.literal("challenges")
/* 106 */         .then(Commands.literal("unlock_all")
/* 107 */           .executes(context -> challenges(context, ((CommandSource)context.getSource()).asPlayer(), 1))))
/* 108 */         .then(Commands.literal("lock_all")
/* 109 */           .executes(context -> challenges(context, ((CommandSource)context.getSource()).asPlayer(), -1))));
/*     */     
/* 111 */     builder
/* 112 */       .then(Commands.literal("drop_package")
/* 113 */         .executes(context -> dropPackage(context, ((CommandSource)context.getSource()).asPlayer())));
/*     */     
/* 115 */     builder
/* 116 */       .then(Commands.literal("save_structure")
/* 117 */         .then(Commands.argument("from", (ArgumentType)BlockPosArgument.blockPos())
/* 118 */           .then(Commands.argument("to", (ArgumentType)BlockPosArgument.blockPos())
/* 119 */             .then(Commands.argument("name", (ArgumentType)StringArgumentType.word())
/* 120 */               .executes(context -> saveStructure(context, BlockPosArgument.getBlockPos(context, "from"), BlockPosArgument.getBlockPos(context, "to"), StringArgumentType.getString(context, "name"), ((CommandSource)context.getSource()).asPlayer()))))));
/*     */     
/* 122 */     builder
/* 123 */       .then(Commands.literal("load_structure")
/* 124 */         .executes(context -> loadStructure(context, ((CommandSource)context.getSource()).asPlayer())));
/*     */     
/* 126 */     builder
/* 127 */       .then(Commands.literal("hardcore")
/* 128 */         .executes(context -> harcoreLoadout(context, ((CommandSource)context.getSource()).asPlayer())));
/*     */     
/* 130 */     builder
/* 131 */       .then(Commands.literal("no_cooldowns")
/* 132 */         .executes(context -> {
/*     */             NO_COOLDOWN = !NO_COOLDOWN;
/*     */             
/*     */             ((CommandSource)context.getSource()).asPlayer().sendMessage((ITextComponent)new StringTextComponent((NO_COOLDOWN ? "Enabled" : "Disabled") + " No Cooldown Mode"));
/*     */             return 1;
/*     */           }));
/* 138 */     builder
/* 139 */       .then(Commands.literal("ambush")
/* 140 */         .executes(context -> {
/*     */             ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
/*     */             
/*     */             if (EntityStatsCapability.get((LivingEntity)player).getBounty() < 1000L) {
/*     */               EntityStatsCapability.get((LivingEntity)player).alterBounty(2000L);
/*     */             }
/*     */             
/*     */             AmbushSpawner spawner = new AmbushSpawner();
/*     */             spawner.spawn(((CommandSource)context.getSource()).getWorld());
/*     */             return 1;
/*     */           }));
/* 151 */     builder
/* 152 */       .then(Commands.literal("wipe")
/* 153 */         .then(Commands.literal("quests")
/* 154 */           .executes(ctx -> {
/*     */               ServerPlayerEntity player = ((CommandSource)ctx.getSource()).asPlayer();
/*     */               
/*     */               IQuestData questData = QuestDataCapability.get((PlayerEntity)player);
/*     */               
/*     */               Iterator<Quest> quests = questData.getFinishedQuests().iterator();
/*     */               while (quests.hasNext()) {
/*     */                 questData.removeFinishedQuest(quests.next());
/*     */               }
/*     */               return 1;
/*     */             })));
/* 165 */     builder
/* 166 */       .then(((LiteralArgumentBuilder)Commands.literal("stress_test")
/* 167 */         .then(Commands.literal("spawners")
/* 168 */           .executes(context -> stressTestSpawners(context, ((CommandSource)context.getSource()).asPlayer()))))
/* 169 */         .then(Commands.literal("blocks")
/* 170 */           .executes(context -> stressTestBlocks(context, ((CommandSource)context.getSource()).asPlayer()))));
/*     */     
/* 172 */     dispatcher.register(builder);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int stressTestBlocks(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 177 */     long startTime = System.currentTimeMillis();
/* 178 */     int i = 0;
/*     */     
/*     */     try {
/* 181 */       for (int x = -50; x < 50; x++) {
/*     */         
/* 183 */         for (int y = -50; y < 50; y++) {
/*     */           
/* 185 */           for (int z = -50; z < 50; z++)
/*     */           {
/* 187 */             WyHelper.setBlockStateInChunk(player.world, player.getPosition().add(x, y, z), Blocks.DIRT.getDefaultState(), 2);
/* 188 */             i++;
/*     */           }
/*     */         
/*     */         } 
/*     */       } 
/* 193 */     } catch (Exception e) {
/*     */       
/* 195 */       e.printStackTrace();
/*     */     } 
/* 197 */     long stopTime = System.currentTimeMillis();
/* 198 */     System.out.println("Finished generating " + i + " blocks after " + (stopTime - startTime) + " millis");
/*     */     
/* 200 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int stressTestSpawners(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 205 */     ServerWorld world = player.getServerWorld();
/*     */     
/* 207 */     for (int i = -3; i < 3; i++) {
/*     */       
/* 209 */       for (int j = -3; j < 3; j++) {
/*     */         
/* 211 */         BlockPos pos = player.getPosition().add(i, 0, j);
/* 212 */         world.setBlockState(pos, ModBlocks.CUSTOM_SPAWNER.getDefaultState());
/* 213 */         CustomSpawnerTileEntity spawner = (CustomSpawnerTileEntity)world.getTileEntity(pos);
/* 214 */         spawner.setSpawnerLimit(5);
/* 215 */         spawner.setSpawnerMob(ModEntities.MARINE_WITH_SWORD);
/*     */       } 
/*     */     } 
/*     */     
/* 219 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkStructures(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 224 */     int[] arr = StructuresHelper.STRUCTURES_COUNT;
/* 225 */     int sum = 0;
/*     */     
/* 227 */     for (int i : arr)
/*     */     {
/* 229 */       sum += i;
/*     */     }
/*     */     
/* 232 */     StringBuilder builder = new StringBuilder();
/* 233 */     builder.append("========SHIPS==================================\n\n");
/* 234 */     builder.append("Small Pirate Ships: " + arr[0] + "\n");
/* 235 */     builder.append("Medium Pirate Ships: " + arr[1] + "\n");
/* 236 */     builder.append("Large Pirate Ships: " + arr[2] + "\n");
/* 237 */     builder.append("Marine Battleships: " + arr[3] + "\n");
/* 238 */     builder.append("Ghost Ships: " + arr[4] + "\n\n");
/* 239 */     builder.append("========LAND STRUCTURES========================\n\n");
/* 240 */     builder.append("Marine Small Base: " + arr[5] + "\n");
/* 241 */     builder.append("Marine Large Base: " + arr[6] + "\n");
/* 242 */     builder.append("Bandit Small Base: " + arr[7] + "\n");
/* 243 */     builder.append("Bandit Large Base: " + arr[8] + "\n");
/* 244 */     builder.append("Dojos: " + arr[9] + "\n");
/* 245 */     builder.append("Marine Camp: " + arr[12] + "\n");
/* 246 */     builder.append("Bandit Camp: " + arr[13] + "\n");
/* 247 */     builder.append("Marine Watch Tower: " + arr[10] + "\n");
/* 248 */     builder.append("========SKY STRUCTURES========================\n\n");
/* 249 */     builder.append("Sky Islands: " + arr[11] + "\n");
/* 250 */     builder.append("Total: " + sum + "\n");
/* 251 */     builder.append("===============================================");
/*     */     
/* 253 */     ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(builder.toString()), true);
/*     */     
/* 255 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int dropPackage(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 260 */     BountyHelper.issueBountyForAllPlayers(player.world);
/* 261 */     WantedPosterPackageEntity posterPackage = new WantedPosterPackageEntity(player.world);
/* 262 */     posterPackage.setPositionAndRotation(player.getPosX(), player.getPosY() + 5.0D, player.getPosZ(), 0.0F, 0.0F);
/* 263 */     player.world.addEntity((Entity)posterPackage);
/* 264 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int harcoreLoadout(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 269 */     player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)ModAbilities.YOMI_YOMI_NO_MI));
/* 270 */     player.inventory.addItemStackToInventory(new ItemStack((IItemProvider)Blocks.DIRT, 64));
/* 271 */     player.getFoodStats().setFoodLevel(0);
/* 272 */     player.getFoodStats().setFoodSaturationLevel(0.0F);
/* 273 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String convertToCSV(String[] data) {
/* 278 */     return Stream.<String>of(data).map(FGCommand::escapeSpecialCharacters).collect(Collectors.joining(","));
/*     */   }
/*     */ 
/*     */   
/*     */   private static String escapeSpecialCharacters(String data) {
/* 283 */     String escapedData = data.replaceAll("\\R", " ");
/* 284 */     if (data.contains(",") || data.contains("\"") || data.contains("'")) {
/*     */       
/* 286 */       data = data.replace("\"", "\"\"");
/* 287 */       escapedData = "\"" + data + "\"";
/*     */     } 
/* 289 */     return escapedData;
/*     */   }
/*     */ 
/*     */   
/*     */   private static String getOriginFruit(Ability ability) {
/* 294 */     for (AkumaNoMiItem fruit : ModValues.devilfruits) {
/*     */       
/* 296 */       for (Ability abl : fruit.abilities) {
/*     */         
/* 298 */         if (abl.equals(ability)) {
/* 299 */           return fruit.getDevilFruitName();
/*     */         }
/*     */       } 
/*     */     } 
/* 303 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   private static int showInfo(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 308 */     player.sendMessage((ITextComponent)new StringTextComponent("==========INFO=========="));
/* 309 */     player.sendMessage((ITextComponent)new StringTextComponent("A total of " + ModValues.devilfruits.size() + " Devil Fruits have been registered"));
/* 310 */     player.sendMessage((ITextComponent)new StringTextComponent("A total of " + APIRegistries.ABILITIES.getEntries().size() + " abilities have been registered"));
/*     */     
/* 312 */     List<Ability> noDescriptions = (List<Ability>)APIRegistries.ABILITIES.getValues().stream().filter(abl -> (abl.getDescription() == null && !abl.isHideInGUI())).collect(Collectors.toList());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 336 */     List<Ability> noTextures = (List<Ability>)APIRegistries.ABILITIES.getValues().stream().filter(abl -> { if (!abl.isHideInGUI()) { String texture; if (abl.hasCustomTexture()) { texture = WyHelper.getResourceName(abl.getCustomTexture()); } else { texture = WyHelper.getResourceName(abl.getName()); }  try { boolean hasFile = (new File(APIConfig.getResourceFolderPath() + "/assets/" + "mineminenomi" + "/textures/abilities/" + texture + ".png")).exists(); if (!hasFile) return true;  } catch (Exception e) { e.printStackTrace(); return false; }  }  return false; }).collect(Collectors.toList());
/*     */     
/* 338 */     StringBuilder builder = new StringBuilder();
/* 339 */     builder.append("\n§a " + noDescriptions.size() + " abilities without descriptions:§r\n");
/* 340 */     for (Ability abl : noDescriptions)
/*     */     {
/* 342 */       builder.append("  " + abl.getDisplayName() + "\n");
/*     */     }
/* 344 */     player.sendMessage((ITextComponent)new StringTextComponent(builder.toString()));
/*     */     
/* 346 */     builder = new StringBuilder();
/* 347 */     builder.append("§a " + noTextures.size() + " abilities without textures:§r\n");
/* 348 */     for (Ability abl : noTextures)
/*     */     {
/* 350 */       builder.append("  " + abl.getDisplayName() + "\n");
/*     */     }
/* 352 */     player.sendMessage((ITextComponent)new StringTextComponent(builder.toString()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 376 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int loadStructure(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 382 */     WyHelper.loadNBTStructure((ServerWorld)player.world, "marine/small_base", player.getPosition());
/* 383 */     player.sendMessage((ITextComponent)new StringTextComponent("Done"));
/* 384 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int saveStructure(CommandContext<CommandSource> context, BlockPos from, BlockPos to, String name, ServerPlayerEntity player) {
/* 389 */     for (int i = from.getX(); i < to.getX(); i++) {
/*     */       
/* 391 */       for (int j = from.getY(); j < to.getY(); j++) {
/*     */         
/* 393 */         for (int k = from.getZ(); k < to.getZ(); k++) {
/*     */           
/* 395 */           BlockPos pos = new BlockPos(i, j, k);
/* 396 */           BlockState state = player.world.getBlockState(pos);
/*     */           
/* 398 */           if (state.has((IProperty)BlockStateProperties.WATERLOGGED)) {
/*     */             
/* 400 */             state = (BlockState)state.with((IProperty)BlockStateProperties.WATERLOGGED, Boolean.valueOf(false));
/* 401 */             player.world.setBlockState(pos, state);
/*     */           } 
/*     */           
/* 404 */           if (state.getBlock() == Blocks.WATER || state.getBlock() == Blocks.STRUCTURE_VOID)
/*     */           {
/* 406 */             player.world.setBlockState(pos, Blocks.AIR.getDefaultState(), 18);
/*     */           }
/*     */           
/* 409 */           if (state.getBlock() == Blocks.BARREL) {
/*     */             
/* 411 */             Direction dir = (Direction)state.get((IProperty)BlockStateProperties.FACING);
/* 412 */             BlockState newState = ModBlocks.DESIGN_BARREL.getDefaultState();
/* 413 */             newState = (BlockState)newState.with((IProperty)BlockStateProperties.FACING, (Comparable)dir);
/* 414 */             player.world.setBlockState(pos, newState);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 419 */     if (player.world instanceof ServerWorld) {
/*     */       
/* 421 */       BlockPos size = to.subtract((Vec3i)from);
/* 422 */       System.out.println("From: /tp " + from.getX() + " " + from.getY() + " " + from.getZ());
/* 423 */       System.out.println("To: /tp " + to.getX() + " " + to.getY() + " " + to.getZ());
/* 424 */       System.out.println("size: " + size);
/* 425 */       List<Block> bannedBlocks = new ArrayList<>();
/* 426 */       bannedBlocks.add(Blocks.WATER);
/* 427 */       WyHelper.saveNBTStructure((ServerWorld)player.world, name, from, size, bannedBlocks);
/*     */       
/* 429 */       player.sendMessage((ITextComponent)new StringTextComponent("Done"));
/*     */     } 
/* 431 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int challenges(CommandContext<CommandSource> context, ServerPlayerEntity player, int op) {
/* 436 */     IChallengesData props = ChallengesDataCapability.get((PlayerEntity)player);
/*     */     
/* 438 */     Challenge[] challenges = { ModChallenges.GIN, ModChallenges.DON_KRIEG, ModChallenges.MORGAN };
/* 439 */     for (Challenge ch : challenges) {
/*     */       
/* 441 */       if (op == 1) {
/*     */         
/* 443 */         if (!props.hasChallenge(ch)) {
/* 444 */           props.addChallenge(ch);
/*     */         }
/* 446 */       } else if (op == -1) {
/*     */         
/* 448 */         if (props.hasChallenge(ch)) {
/*     */           
/* 450 */           ch.setComplete(false);
/* 451 */           props.removeChallenge(ch);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 456 */     WyNetwork.sendTo(new SSyncChallengeDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*     */     
/* 458 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkBounties(CommandContext<CommandSource> source) {
/* 463 */     ServerWorld serverWorld = ((CommandSource)source.getSource()).getWorld();
/* 464 */     ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
/*     */     
/* 466 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 468 */     for (Map.Entry<String, Long> entry : (Iterable<Map.Entry<String, Long>>)worldData.getAllBounties().entrySet()) {
/*     */       
/* 470 */       UUID uuid = UUID.fromString(entry.getKey());
/* 471 */       PlayerEntity target = serverWorld.getPlayerByUuid(uuid);
/* 472 */       sb.append(target.getDisplayName().getFormattedText() + " " + entry.getValue());
/*     */     } 
/* 474 */     ((CommandSource)source.getSource()).sendFeedback((ITextComponent)new StringTextComponent(sb.toString()), true);
/*     */     
/* 476 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkCrews(CommandContext<CommandSource> source, ServerPlayerEntity target) {
/* 481 */     ExtendedWorldData worldData = ExtendedWorldData.get(target.world);
/*     */     
/* 483 */     StringBuilder builder = new StringBuilder();
/*     */     
/* 485 */     for (Crew crew : worldData.getCrews()) {
/*     */       
/* 487 */       builder.append("\n=================\n" + crew.getName() + "\n");
/* 488 */       builder.append("Members: \n");
/* 489 */       for (Crew.Member member : crew.getMembers())
/*     */       {
/* 491 */         builder.append(" " + member.getUsername() + (member.isCaptain() ? " (Captain)" : "") + "\n");
/*     */       }
/* 493 */       builder.append("Details: \n");
/* 494 */       for (JollyRogerElement elem : crew.getJollyRoger().getDetails()) {
/*     */         
/* 496 */         if (elem != null)
/*     */         {
/* 498 */           builder.append(" " + elem.getTexture() + "\n"); } 
/*     */       } 
/*     */     } 
/* 501 */     target.sendMessage((ITextComponent)new StringTextComponent(builder.toString()));
/*     */     
/* 503 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int turnSwordInClone(CommandContext<CommandSource> context, ServerPlayerEntity player) {
/* 508 */     ItemStack heldStack = player.getHeldItemMainhand();
/*     */     
/* 510 */     if (!heldStack.isEmpty()) {
/* 511 */       heldStack.getOrCreateTag().putBoolean("isClone", true);
/*     */     }
/* 513 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int checkHakiStats(CommandContext<CommandSource> context, ServerPlayerEntity target) {
/* 518 */     IHakiData props = HakiDataCapability.get((LivingEntity)target);
/*     */     
/* 520 */     target.sendMessage((ITextComponent)new StringTextComponent("Imbuing: " + props.getBusoshokuImbuingHakiExp()));
/* 521 */     target.sendMessage((ITextComponent)new StringTextComponent("Hardening: " + props.getBusoshokuHardeningHakiExp()));
/* 522 */     target.sendMessage((ITextComponent)new StringTextComponent("Observation: " + props.getKenbunshokuHakiExp()));
/* 523 */     target.sendMessage((ITextComponent)new StringTextComponent("Total: " + HakiHelper.getTotalHakiExp((LivingEntity)target)));
/*     */     
/* 525 */     if (WyDebug.isDebug()) {
/* 526 */       props.setHakiOveruse(0);
/*     */     }
/* 528 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\FGCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */