/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.IntegerArgumentType;
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
import net.minecraft.util.text.TextFormatting;
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.LoyaltyEvent;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class LoyaltyCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 29 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("loyalty").requires(source -> source.hasPermissionLevel(2));
/*    */     
/* 31 */     int min = -100;
/* 32 */     int max = 100;
/*    */     
/* 34 */     builder
/* 35 */       .then(((RequiredArgumentBuilder)Commands.argument("amount", (ArgumentType)IntegerArgumentType.integer(min, max))
/* 36 */         .executes(context -> alterLoyalty(context, IntegerArgumentType.getInteger(context, "amount"), getDefaultCollection(context))))
/* 37 */         .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/* 38 */           .executes(context -> alterLoyalty(context, IntegerArgumentType.getInteger(context, "amount"), EntityArgument.getPlayers(context, "targets")))));
/*    */     
/* 40 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 45 */     return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
/*    */   }
/*    */ 
/*    */   
/*    */   private static int alterLoyalty(CommandContext<CommandSource> context, int amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
/* 50 */     for (ServerPlayerEntity player : targets) {
/*    */       
/* 52 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 54 */       entityStatsProps.alterLoyalty(amount);
/* 55 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (PlayerEntity)player);
/*    */       
/* 57 */       LoyaltyEvent e = new LoyaltyEvent((PlayerEntity)player, amount);
/* 58 */       MinecraftForge.EVENT_BUS.post((Event)e);
/*    */       
/* 60 */       ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0) ? "+" : "") + amount + " loyalty for " + player.getDisplayName().getFormattedText()), true);
/*    */     } 
/*    */     
/* 63 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\LoyaltyCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */