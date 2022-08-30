/*    */ package xyz.pixelatedw.mineminenomi.commands;
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.brigadier.CommandDispatcher;
/*    */ import com.mojang.brigadier.arguments.ArgumentType;
/*    */ import com.mojang.brigadier.arguments.FloatArgumentType;
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
/*    */ import net.minecraftforge.common.MinecraftForge;
/*    */ import net.minecraftforge.eventbus.api.Event;
/*    */ import xyz.pixelatedw.mineminenomi.api.commands.HakiTypeArgument;
/*    */ import xyz.pixelatedw.mineminenomi.api.enums.HakiType;
/*    */ import xyz.pixelatedw.mineminenomi.api.events.HakiExpEvent;
/*    */ import xyz.pixelatedw.mineminenomi.config.CommonConfig;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*    */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*    */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncHakiDataPacket;
/*    */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
import net.minecraft.util.text.TextFormatting;
/*    */ 
/*    */ public class HakiExpCommand {
/*    */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/* 32 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("hakiexp").requires(source -> source.hasPermissionLevel(2));
/*    */     
/* 34 */     int max = CommonConfig.INSTANCE.getHakiExpLimit();
/*    */     
/* 36 */     builder
/* 37 */       .then(Commands.argument("type", (ArgumentType)HakiTypeArgument.hakiType())
/* 38 */         .then(((RequiredArgumentBuilder)Commands.argument("amount", (ArgumentType)FloatArgumentType.floatArg(-max, max))
/* 39 */           .executes(context -> alterHakiExp(context, (HakiType)context.getArgument("type", HakiType.class), FloatArgumentType.getFloat(context, "amount"), getDefaultCollection(context))))
/* 40 */           .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/* 41 */             .executes(context -> alterHakiExp(context, (HakiType)context.getArgument("type", HakiType.class), FloatArgumentType.getFloat(context, "amount"), EntityArgument.getPlayers(context, "targets"))))));
/*    */     
/* 43 */     dispatcher.register(builder);
/*    */   }
/*    */ 
/*    */   
/*    */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/* 48 */     return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
/*    */   }
/*    */ 
/*    */   
/*    */   private static int alterHakiExp(CommandContext<CommandSource> context, HakiType hakiType, float amount, Collection<ServerPlayerEntity> targets) throws CommandSyntaxException {
/* 53 */     for (ServerPlayerEntity player : targets) {
/*    */       
/* 55 */       IHakiData props = HakiDataCapability.get((LivingEntity)player);
/*    */       
/* 57 */       if (hakiType == HakiType.IMBUING) {
/* 58 */         props.alterBusoshokuImbuingHakiExp(amount);
/* 59 */       } else if (hakiType == HakiType.HARDENING) {
/* 60 */         props.alterBusoshokuHardeningHakiExp(amount);
/* 61 */       } else if (hakiType == HakiType.KENBUNSHOKU) {
/* 62 */         props.alterKenbunshokuHakiExp(amount);
/* 63 */       }  WyNetwork.sendTo(new SSyncHakiDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*    */       
/* 65 */       HakiExpEvent e = new HakiExpEvent((PlayerEntity)player, amount, hakiType);
/* 66 */       if (MinecraftForge.EVENT_BUS.post((Event)e)) {
/* 67 */         return 1;
/*    */       }
/* 69 */       ((CommandSource)context.getSource()).sendFeedback((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + ((amount > 0.0F) ? "+" : "") + amount + " haki exp for " + player.getDisplayName().getFormattedText()), true);
/*    */     } 
/*    */ 
/*    */     
/* 73 */     return 1;
/*    */   }
/*    */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\HakiExpCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */