/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.QuestArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.helpers.abilities.AbilityHelper;
/*     */ import xyz.pixelatedw.mineminenomi.api.quests.Quest;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.IQuestData;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.quests.QuestDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.SSyncQuestDataPacket;
import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class QuestCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/*  27 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("quest").requires(source -> source.hasPermissionLevel(3));
/*     */     
/*  29 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/*  30 */       .then(Commands.literal("finish")
/*  31 */         .then(Commands.argument("quest", (ArgumentType)QuestArgument.quest())
/*  32 */           .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/*  33 */             .executes(context -> finishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target")))))))
/*  34 */       .then(Commands.literal("give")
/*  35 */         .then(Commands.argument("quest", (ArgumentType)QuestArgument.quest())
/*  36 */           .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/*  37 */             .executes(context -> giveQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target")))))))
/*  38 */       .then(Commands.literal("unfinish")
/*  39 */         .then(Commands.argument("quest", (ArgumentType)QuestArgument.quest())
/*  40 */           .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/*  41 */             .executes(context -> unfinishQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target")))))))
/*  42 */       .then(Commands.literal("remove")
/*  43 */         .then(Commands.argument("quest", (ArgumentType)QuestArgument.quest())
/*  44 */           .then(Commands.argument("target", (ArgumentType)EntityArgument.player())
/*  45 */             .executes(context -> removeQuest(context, QuestArgument.getQuest(context, "quest"), EntityArgument.getPlayer(context, "target"))))));
/*     */     
/*  47 */     dispatcher.register(builder);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int unfinishQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player) {
/*  52 */     IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*  53 */     IAbilityData abilityData = AbilityDataCapability.get((LivingEntity)player);
/*     */     
/*  55 */     if (props.hasFinishedQuest(quest)) {
/*     */       
/*  57 */       props.removeFinishedQuest(quest);
/*  58 */       AbilityHelper.validateStyleMoves((PlayerEntity)player);
/*  59 */       WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*  60 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityData), (PlayerEntity)player);
/*     */     } else {
/*     */       
/*  63 */       player.sendMessage((ITextComponent)new StringTextComponent("You haven't finished this quest!"));
/*     */     } 
/*  65 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int finishQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player) {
/*  70 */     IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*     */     
/*  72 */     if (props.hasInProgressQuest(quest) && quest.triggerCompleteEvent((PlayerEntity)player)) {
/*     */       
/*  74 */       props.addFinishedQuest(quest);
/*  75 */       props.removeInProgressQuest(quest);
/*  76 */       WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*     */     }
/*  78 */     else if (!props.hasInProgressQuest(quest)) {
/*  79 */       player.sendMessage((ITextComponent)new StringTextComponent("You don't have this quest!"));
/*     */     } 
/*  81 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int giveQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player) {
/*  86 */     IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*     */     
/*  88 */     if (!props.hasInProgressQuest(quest)) {
/*     */       
/*  90 */       props.addInProgressQuest(quest.create());
/*  91 */       WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*     */     } else {
/*     */       
/*  94 */       player.sendMessage((ITextComponent)new StringTextComponent("You aleady have this quest!"));
/*     */     } 
/*  96 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int removeQuest(CommandContext<CommandSource> context, Quest quest, ServerPlayerEntity player) {
/* 101 */     IQuestData props = QuestDataCapability.get((PlayerEntity)player);
/*     */     
/* 103 */     if (props.hasInProgressQuest(quest)) {
/*     */       
/* 105 */       props.removeInProgressQuest(quest);
/* 106 */       props.removeFinishedQuest(quest);
/* 107 */       WyNetwork.sendTo(new SSyncQuestDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*     */     } else {
/*     */       
/* 110 */       player.sendMessage((ITextComponent)new StringTextComponent("You don't have this quest!"));
/*     */     } 
/* 112 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\QuestCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */