/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.LongArgumentType;
/*    */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*    */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*    */ import com.mojang.brigadier.context.CommandContext;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.Collection;
/*    */ import net.minecraft.command.CommandSource;
/*    */ import net.minecraft.command.Commands;
/*    */ import net.minecraft.command.arguments.EntityArgument;
/*    */ import net.minecraft.entity.LivingEntity;
/*    */ import net.minecraft.entity.player.PlayerEntity;
/*    */ import net.minecraft.entity.player.ServerPlayerEntity;
/*    */ import net.minecraft.util.text.ITextComponent;
/*    */ import net.minecraft.util.text.StringTextComponent;
/*    */ import net.minecraft.util.text.TextFormatting;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class ExtolCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 28 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("extol").requires(source -> source.hasPermissionLevel(2));
/*    */     
/* 30 */     builder
/* 31 */       .then(((RequiredArgumentBuilder)Commands.argument("amount", (ArgumentType)LongArgumentType.longArg(-999999999L, 999999999L))
/* 32 */         .executes(context -> alterExtol(context, LongArgumentType.getLong(context, "amount"), getDefaultCollection(context))))
/* 33 */         .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/* 34 */           .executes(context -> alterExtol(context, LongArgumentType.getLong(context, "amount"), EntityArgument.getPlayers(context, "targets")))));
/*    */     
/* 36 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 41 */     return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
/*    */   }
/*    */ 
/*    */   
/*    */   private static int alterExtol(CommandContext<CommandSource> context, long amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
/* 46 */     for (ServerPlayerEntity player : targets) {
/*    */       
/* 48 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 50 */       entityStatsProps.alterExtol(amount);
/* 51 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (PlayerEntity)player);
/*    */       
/* 53 */       ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0L) ? "+" : "") + amount + " extol for " + player.getDisplayName().getFormattedText()), true);
/*    */     } 
/*    */     
/* 56 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\ExtolCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */