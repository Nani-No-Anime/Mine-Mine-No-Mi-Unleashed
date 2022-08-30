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
/*    */ import xyz.pixelatedw.mineminenomi.api.events.DorikiEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.EntityStatsCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.entitystats.IEntityStats;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncEntityStatsPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*    */ 
/*    */ public class DorikiCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 30 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("doriki").requires(source -> source.hasPermissionLevel(2));
/*    */     
/* 32 */     int min = -CommonConfig.INSTANCE.getDorikiLimit();
/* 33 */     int max = CommonConfig.INSTANCE.getDorikiLimit();
/*    */     
/* 35 */     builder
/* 36 */       .then(((RequiredArgumentBuilder)Commands.argument("amount", (ArgumentType)IntegerArgumentType.integer(min, max))
/* 37 */         .executes(context -> alterDoriki(context, IntegerArgumentType.getInteger(context, "amount"), getDefaultCollection(context))))
/* 38 */         .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/* 39 */           .executes(context -> alterDoriki(context, IntegerArgumentType.getInteger(context, "amount"), EntityArgument.getPlayers(context, "targets")))));
/*    */     
/* 41 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 46 */     return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
/*    */   }
/*    */ 
/*    */   
/*    */   private static int alterDoriki(CommandContext<CommandSource> context, int amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
/* 51 */     for (ServerPlayerEntity player : targets) {
/*    */       
/* 53 */       IEntityStats entityStatsProps = EntityStatsCapability.get((LivingEntity)player);
/*    */       
/* 55 */       entityStatsProps.alterDoriki(amount);
/* 56 */       WyNetwork.sendTo(new SSyncEntityStatsPacket(player.getEntityId(), entityStatsProps), (PlayerEntity)player);
/*    */       
/* 58 */       DorikiEvent e = new DorikiEvent((PlayerEntity)player, amount);
/* 59 */       MinecraftForge.EVENT_BUS.post((Event)e);
/*    */       
/* 61 */       ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0) ? "+" : "") + amount + " doriki for " + player.getDisplayName().getFormattedText()), true);
/*    */     } 
/*    */     
/* 64 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\DorikiCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */