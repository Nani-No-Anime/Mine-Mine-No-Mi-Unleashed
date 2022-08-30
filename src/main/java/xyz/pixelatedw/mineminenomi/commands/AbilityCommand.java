/*     */ package xyz.pixelatedw.mineminenomi.commands;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.brigadier.CommandDispatcher;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.Collection;
/*     */ import net.minecraft.command.CommandSource;
/*     */ import net.minecraft.command.Commands;
/*     */ import net.minecraft.command.arguments.EntityArgument;
/*     */ import net.minecraft.entity.LivingEntity;
/*     */ import net.minecraft.entity.player.PlayerEntity;
/*     */ import net.minecraft.entity.player.ServerPlayerEntity;
/*     */ import net.minecraft.util.text.ITextComponent;
/*     */ import net.minecraft.util.text.StringTextComponent;
/*     */ import net.minecraft.util.text.TextFormatting;
/*     */ import xyz.pixelatedw.mineminenomi.api.abilities.AbilityUnlock;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.AbilityArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.commands.AbilityGroupArgument;
/*     */ import xyz.pixelatedw.mineminenomi.api.enums.AbilityCommandGroup;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.HakiDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.data.entity.haki.IHakiData;
/*     */ import xyz.pixelatedw.mineminenomi.packets.server.ability.SUpdateEquippedAbilityPacket;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.abilities.Ability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.AbilityDataCapability;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.data.ability.IAbilityData;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.debug.WyDebug;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.WyNetwork;
/*     */ import xyz.pixelatedw.mineminenomi.wypi.network.packets.server.SSyncAbilityDataPacket;
/*     */ 
/*     */ public class AbilityCommand {
/*     */   public static void register(CommandDispatcher<CommandSource> dispatcher) {
/*  35 */     LiteralArgumentBuilder<CommandSource> builder = (LiteralArgumentBuilder<CommandSource>)Commands.literal("ability").requires(source -> source.hasPermissionLevel(3));
/*     */     
/*  37 */     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)builder
/*  38 */       .then(Commands.literal("give")
/*  39 */         .then(((RequiredArgumentBuilder)Commands.argument("ability", (ArgumentType)AbilityArgument.ability())
/*  40 */           .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/*  41 */             .executes(context -> addAbility(context, AbilityArgument.getAbility(context, "ability"), EntityArgument.getPlayers(context, "targets")))))
/*     */           
/*  43 */           .executes(context -> addAbility(context, AbilityArgument.getAbility(context, "ability"), getDefaultCollection(context))))))
/*     */ 
/*     */       
/*  46 */       .then(Commands.literal("remove")
/*  47 */         .then(((RequiredArgumentBuilder)Commands.argument("ability", (ArgumentType)new AbilityArgument())
/*  48 */           .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/*  49 */             .executes(context -> removeAbility(context, AbilityArgument.getAbility(context, "ability"), EntityArgument.getPlayers(context, "targets")))))
/*     */           
/*  51 */           .executes(context -> removeAbility(context, AbilityArgument.getAbility(context, "ability"), getDefaultCollection(context))))))
/*     */ 
/*     */       
/*  54 */       .then(Commands.literal("unlock_group")
/*  55 */         .then(Commands.argument("group", (ArgumentType)AbilityGroupArgument.abilityGroup())
/*  56 */           .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/*  57 */             .executes(context -> abilityGroup(context, (AbilityCommandGroup)context.getArgument("group", AbilityCommandGroup.class), 1, EntityArgument.getPlayers(context, "targets")))))))
/*  58 */       .then(Commands.literal("lock_group")
/*  59 */         .then(Commands.argument("group", (ArgumentType)AbilityGroupArgument.abilityGroup())
/*  60 */           .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/*  61 */             .executes(context -> abilityGroup(context, (AbilityCommandGroup)context.getArgument("group", AbilityCommandGroup.class), -1, EntityArgument.getPlayers(context, "targets")))))))
/*  62 */       .then(((LiteralArgumentBuilder)Commands.literal("reset_cooldown")
/*  63 */         .then(Commands.argument("targets", (ArgumentType)EntityArgument.players())
/*  64 */           .executes(context -> resetCooldown(context, EntityArgument.getPlayers(context, "targets")))))
/*     */         
/*  66 */         .executes(context -> resetCooldown(context, getDefaultCollection(context))));
/*     */ 
/*     */     
/*  69 */     dispatcher.register(builder);
/*     */   }
/*     */ 
/*     */   
/*     */   private static int abilityGroup(CommandContext<CommandSource> context, AbilityCommandGroup group, int op, Collection<ServerPlayerEntity> players) {
/*  74 */     for (ServerPlayerEntity player : players) {
/*     */       
/*  76 */       IAbilityData abilityProps = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/*  78 */       for (Ability abl : group.getAbilities()) {
/*     */         
/*  80 */         if (op == 1) {
/*     */           
/*  82 */           abl.setUnlockType(AbilityUnlock.COMMAND);
/*  83 */           abilityProps.addUnlockedAbility(abl);
/*     */           continue;
/*     */         } 
/*  86 */         abilityProps.removeUnlockedAbility(abl);
/*     */       } 
/*     */       
/*  89 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), abilityProps), (PlayerEntity)player);
/*     */     } 
/*  91 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static Collection<ServerPlayerEntity> getDefaultCollection(CommandContext<CommandSource> context) throws CommandSyntaxException {
/*  96 */     return Lists.newArrayList(new ServerPlayerEntity[] { ((CommandSource)context.getSource()).asPlayer() });
/*     */   }
/*     */ 
/*     */   
/*     */   private static int resetCooldown(CommandContext<CommandSource> context, Collection<ServerPlayerEntity> players) {
/* 101 */     for (ServerPlayerEntity player : players) {
/*     */       
/* 103 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/* 104 */       IHakiData hakiProps = HakiDataCapability.get((LivingEntity)player);
/*     */       
/* 106 */       for (Ability ability : props.getEquippedAbilities()) {
/*     */         
/* 108 */         if (ability != null) {
/*     */ 
/*     */           
/* 111 */           ability.setForcedState(false);
/*     */           
/* 113 */           if (ability.isOnCooldown()) {
/* 114 */             ability.stopCooldown((PlayerEntity)player);
/*     */           }
/* 116 */           hakiProps.setHakiOveruse(0);
/*     */           
/* 118 */           WyNetwork.sendToAllTrackingAndSelf(new SUpdateEquippedAbilityPacket((PlayerEntity)player, ability), (LivingEntity)player);
/*     */         } 
/*     */       } 
/*     */     } 
/* 122 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int addAbility(CommandContext<CommandSource> context, Ability ability, Collection<ServerPlayerEntity> targets) {
/* 127 */     for (ServerPlayerEntity player : targets) {
/*     */       
/* 129 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 131 */       ability.setUnlockType(AbilityUnlock.COMMAND);
/* 132 */       props.addUnlockedAbility(ability);
/*     */       
/* 134 */       if (WyDebug.isDebug()) {
/* 135 */         player.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + ability.getName() + " unlocked for " + player.getName().getFormattedText()));
/*     */       }
/* 137 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*     */     } 
/*     */     
/* 140 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   private static int removeAbility(CommandContext<CommandSource> context, Ability ability, Collection<ServerPlayerEntity> targets) {
/* 145 */     for (ServerPlayerEntity player : targets) {
/*     */       
/* 147 */       IAbilityData props = AbilityDataCapability.get((LivingEntity)player);
/*     */       
/* 149 */       props.removeUnlockedAbility(ability);
/*     */       
/* 151 */       if (WyDebug.isDebug()) {
/* 152 */         player.sendMessage((ITextComponent)new StringTextComponent(TextFormatting.GREEN + "" + TextFormatting.ITALIC + "[DEBUG] " + ability.getName() + " removed for " + player.getName().getFormattedText()));
/*     */       }
/* 154 */       WyNetwork.sendTo(new SSyncAbilityDataPacket(player.getEntityId(), props), (PlayerEntity)player);
/*     */     } 
/*     */     
/* 157 */     return 1;
/*     */   }
/*     */ }


/* Location:              C:\Users\4tuto\curseforge\minecraft\Instances\incontrol\mods\mine-mine-no-mi-1.15.2-0.8.1.jar!\xyz\pixelatedw\mineminenomi\commands\AbilityCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */