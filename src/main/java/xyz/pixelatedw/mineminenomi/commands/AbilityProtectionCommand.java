/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ 
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.arguments.BoolArgumentType;
/*     */ import com.mojang.brigadier.arguments.IntegerArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.block.BlockState;
/*     */ import net.minecraft.block.Blocks;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.math.BlockPos;
/*     */ import net.minecraft.util.math.Vec3d;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.Style;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import net.minecraft.util.text.event.ClickEvent;
/*     */ import net.minecraft.world.World;
/*     */ import net.minecraft.world.server.ServerWorld;
/*     */ import xyz.pixelatedw.mineminenomi.blocks.tileentities.AblProtectionTileEntity;
/*     */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*     */ import xyz.pixelatedw.mineminenomi.init.ModBlocks;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SViewProtectionPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ 
/*     */ public class AbilityProtectionCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/*  33 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("abilityprotection").requires(source -> source.hasPermissionLevel(3));
/*     */     
/*  35 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/*  36 */       .then(Commands.literal("new")
/*  37 */         .then(Commands.argument("size", (ArgumentType)IntegerArgumentType.integer(1, 100))
/*  38 */           .executes(context -> createProtection(context, IntegerArgumentType.getInteger(context, "size"))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  44 */       .then(Commands.literal("resize")
/*  45 */         .then(Commands.argument("id", (ArgumentType)IntegerArgumentType.integer())
/*  46 */           .then(Commands.argument("size", (ArgumentType)IntegerArgumentType.integer(1, 100))
/*  47 */             .executes(context -> resizeProtection(context, IntegerArgumentType.getInteger(context, "id"), IntegerArgumentType.getInteger(context, "size")))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  53 */       .then(Commands.literal("view")
/*  54 */         .then(Commands.argument("state", (ArgumentType)BoolArgumentType.bool())
/*  55 */           .executes(context -> viewProtection(context, BoolArgumentType.getBool(context, "state"))))))
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  61 */       .then(Commands.literal("list")
/*  62 */         .executes(context -> listProtections(context))))
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  67 */       .then(Commands.literal("remove")
/*  68 */         .then(Commands.argument("id", (ArgumentType)IntegerArgumentType.integer())
/*  69 */           .executes(context -> deleteProtection(context, IntegerArgumentType.getInteger(context, "id")))));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  76 */     dispatcher.register(builder);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int resizeProtection(CommandContext<CommandSource> context, int id, int size) throws CommandSyntaxException {
/*  81 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
/*  82 */     ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
/*  83 */     ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
/*  84 */     id--;
/*     */     
/*  86 */     int[][] area = worldData.getAllRestrictions().get(id);
/*     */     
/*  88 */     int[] minPos = area[0];
/*  89 */     int[] maxPos = area[1];
/*     */     
/*  91 */     int midX = (minPos[0] + maxPos[0]) / 2;
/*  92 */     int midY = (minPos[1] + maxPos[1]) / 2;
/*  93 */     int midZ = (minPos[2] + maxPos[2]) / 2;
/*  94 */     BlockPos pos = new BlockPos(midX, midY, midZ);
/*     */     
/*  96 */     BlockState state = serverWorld.getBlockState(pos);
/*  97 */     if (state.getBlock() == ModBlocks.ABILITY_PROTECTION) {
/*     */       
/*  99 */       AblProtectionTileEntity tile = (AblProtectionTileEntity)serverWorld.getTileEntity(pos);
/* 100 */       tile.resizeProtection((PlayerEntity)player, pos, id, size);
/*     */     } 
/*     */     
/* 103 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int deleteProtection(CommandContext<CommandSource> context, int id) throws CommandSyntaxException {
/* 108 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
/* 109 */     ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
/* 110 */     id--;
/*     */     
/* 112 */     int[][] area = worldData.getAllRestrictions().get(id);
/*     */     
/* 114 */     int[] minPos = area[0];
/* 115 */     int[] maxPos = area[1];
/*     */     
/* 117 */     int midX = (minPos[0] + maxPos[0]) / 2;
/* 118 */     int midY = (minPos[1] + maxPos[1]) / 2;
/* 119 */     int midZ = (minPos[2] + maxPos[2]) / 2;
/* 120 */     BlockPos pos = new BlockPos(midX, midY, midZ);
/*     */     
/* 122 */     serverWorld.setBlockState(pos, Blocks.AIR.getDefaultState());
/* 123 */     worldData.removeRestrictedArea(id);
/*     */     
/* 125 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int listProtections(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 130 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
/* 131 */     ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
/*     */     
/* 133 */     ITextComponent list = (new StringTextComponent("Protection Sites: \n\n")).setStyle((new Style()).setColor(TextFormatting.GREEN));
/*     */     
/* 135 */     int i = 1;
/* 136 */     for (int[][] area : worldData.getAllRestrictions()) {
/*     */       
/* 138 */       int[] minPos = area[0];
/* 139 */       int[] maxPos = area[1];
/*     */       
/* 141 */       if (minPos.length <= 0 || maxPos.length <= 0) {
/*     */         continue;
/*     */       }
/* 144 */       int midX = (minPos[0] + maxPos[0]) / 2;
/* 145 */       int midY = (minPos[1] + maxPos[1]) / 2;
/* 146 */       int midZ = (minPos[2] + maxPos[2]) / 2;
/*     */ 
/*     */       
/* 149 */       Style style = (new Style()).setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp @p " + midX + " " + midY + " " + midZ));
/* 150 */       list.appendSibling((new StringTextComponent(i + ". x=" + midX + ", y=" + midY + ", z=" + midZ + "\n")).setStyle(style));
/* 151 */       i++;
/*     */     } 
/*     */     
/* 154 */     ((CommandSource)context.getSource()).sendFeedback(list, true);
/*     */     
/* 156 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int viewProtection(CommandContext<CommandSource> context, boolean state) throws CommandSyntaxException {
/* 161 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
/* 162 */     ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
/*     */     
/* 164 */     ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
/*     */     
/* 166 */     for (int[][] area : worldData.getAllRestrictions()) {
/*     */       
/* 168 */       int[] minPos = area[0];
/* 169 */       int[] maxPos = area[1];
/*     */       
/* 171 */       if (minPos.length <= 0 || maxPos.length <= 0) {
/*     */         continue;
/*     */       }
/* 174 */       int midX = (minPos[0] + maxPos[0]) / 2;
/* 175 */       int midY = (minPos[1] + maxPos[1]) / 2;
/* 176 */       int midZ = (minPos[2] + maxPos[2]) / 2;
/* 177 */       int[] midPoint = { midX, midY, midZ };
/*     */       
/* 179 */       AblProtectionTileEntity te = (AblProtectionTileEntity)serverWorld.getTileEntity(new BlockPos(midPoint[0], midPoint[1], midPoint[2]));
/*     */       
/* 181 */       if (te == null || !(te instanceof AblProtectionTileEntity)) {
/*     */         continue;
/*     */       }
/* 184 */       WyNetwork.sendTo(new SViewProtectionPacket(state, midPoint, te.getSize()), (PlayerEntity)player);
/*     */     } 
/* 186 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int createProtection(CommandContext<CommandSource> context, int size) throws CommandSyntaxException {
/* 191 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
/* 192 */     ServerPlayerEntity player = ((CommandSource)context.getSource()).asPlayer();
/* 193 */     Vec3d vec = ((CommandSource)context.getSource()).getPos();
/* 194 */     BlockPos pos = new BlockPos(vec);
/*     */     
/* 196 */     serverWorld.setBlockState(pos, ModBlocks.ABILITY_PROTECTION.getDefaultState());
/* 197 */     ((AblProtectionTileEntity)serverWorld.getTileEntity(pos)).setupProtection((PlayerEntity)player, pos, size);
/*     */     
/* 199 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\AbilityProtectionCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */