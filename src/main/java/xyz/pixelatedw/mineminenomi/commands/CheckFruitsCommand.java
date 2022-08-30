/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ 
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Date;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.UUID;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.world.World;
/*    */ import net.minecraft.world.server.ServerWorld;
/*    */ import org.apache.commons.lang3.tuple.Pair;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.world.ExtendedWorldData;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CheckFruitsCommand
/*    */ {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 27 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("check_fruits").requires(source -> source.hasPermissionLevel(3));
/*    */     
/* 29 */     builder.executes(context -> checkFruitsInWorld(context));
/*    */     
/* 31 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static int checkFruitsInWorld(CommandContext<CommandSource> context) {
/* 36 */     if (!CommonConfig.INSTANCE.hasOneFruitPerWorldSimpleLogic()) {
/*    */       
/* 38 */       ((CommandSource)context.getSource()).sendErrorMessage((ITextComponent)new StringTextComponent("This command can only be used when the One Fruit per World config option is enabled."));
/* 39 */       return 0;
/*    */     } 
/*    */     
/* 42 */     ServerWorld serverWorld = ((CommandSource)context.getSource()).getWorld();
/* 43 */     ExtendedWorldData worldData = ExtendedWorldData.get((World)serverWorld);
/*    */     
/* 45 */     StringBuilder builder = new StringBuilder();
/*    */     
/* 47 */     builder.append("===============================================\n");
/* 48 */     builder.append("§l§6Devil Fruits in World\n");
/* 49 */     builder.append(worldData.getDevilFruitsInWorld().size() + " ");
/* 50 */     for (String fruit : worldData.getDevilFruitsInWorld())
/*    */     {
/* 52 */       builder.append(fruit + " ");
/*    */     }
/* 54 */     builder.append("\n");
/* 55 */     builder.append("\n");
/*    */     
/* 57 */     builder.append("§l§6Devil Fruits in Player's Inventories\n");
/* 58 */     HashMap<UUID, List<String>> inventories = worldData.getFruitsInInventory();
/* 59 */     for (Map.Entry<UUID, List<String>> entry : inventories.entrySet()) {
/*    */       
/* 61 */       if (((List)entry.getValue()).isEmpty())
/*    */         continue; 
/* 63 */       String playerName = serverWorld.getServer().getPlayerProfileCache().getProfileByUUID(entry.getKey()).getName();
/* 64 */       builder.append(playerName + " - ");
/* 65 */       StringBuilder fruitsString = new StringBuilder();
/* 66 */       for (String fruitName : entry.getValue())
/*    */       {
/* 68 */         fruitsString.append(fruitName + " ");
/*    */       }
/* 70 */       builder.append(fruitsString.toString() + "\n");
/*    */     } 
/* 72 */     builder.append("\n");
/*    */     
/* 74 */     builder.append("§l§6Devil Fruits Eaten by a Player\n");
/* 75 */     for (Map.Entry<UUID, String> entry : (Iterable<Map.Entry<UUID, String>>)worldData.getAteFruits().entrySet()) {
/*    */       
/* 77 */       String playerName = serverWorld.getServer().getPlayerProfileCache().getProfileByUUID(entry.getKey()).getName();
/* 78 */       builder.append(playerName + " : " + (String)entry.getValue() + "\n");
/*    */     } 
/* 80 */     builder.append("\n");
/*    */     
/* 82 */     builder.append("§l§6Logged Out Devil Fruits\n");
/* 83 */     for (Map.Entry<UUID, Pair<Date, List<String>>> entry : (Iterable<Map.Entry<UUID, Pair<Date, List<String>>>>)worldData.getLoggedOutDevilFruits().entrySet()) {
/*    */       
/* 85 */       String playerName = serverWorld.getServer().getPlayerProfileCache().getProfileByUUID(entry.getKey()).getName();
/* 86 */       StringBuilder fruitsString = new StringBuilder();
/* 87 */       for (String fruitName : (entry.getValue()).getValue())
/*    */       {
/* 89 */         fruitsString.append(fruitName + " ");
/*    */       }
/* 91 */       builder.append(((Date)(entry.getValue()).getKey()).toString() + " - " + playerName + " : " + fruitsString.toString() + "\n");
/*    */     } 
/* 93 */     builder.append("===============================================");
/*    */ 
/*    */     
/* 96 */     ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(builder.toString()), false);
/*    */     
/* 98 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\CheckFruitsCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */